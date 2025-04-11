package br.com.lucasbdourado.library.service.generic.implement;

import br.com.lucasbdourado.library.exception.GenericConstructorException;
import br.com.lucasbdourado.library.exception.NotFoundException;
import br.com.lucasbdourado.library.service.generic.GenericBaseServiceInterface;
import java.lang.reflect.Constructor;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.core.ResolvableType;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class GenericBaseService<T, ID> implements GenericBaseServiceInterface<T, ID>
{
	private final Class<T> clazz;

	@SuppressWarnings("unchecked")
	public GenericBaseService()
	{
		this.clazz = (Class<T>) ResolvableType.forClass(getClass()).as(GenericBaseService.class)
			.getGeneric(0).resolve();
	}

	protected abstract JpaRepository<T, ID> getRepository();

	protected T createNewInstance(T entity) throws GenericConstructorException
	{
		try
		{
			Constructor<T> constructor = clazz.getConstructor();

			return constructor.newInstance();
		}
		catch (Exception e)
		{
			throw new GenericConstructorException("Não foi possível criar a instância da classe", e);
		}
	}

	protected String[] getIgnoredPropertiesOnUpdate()
	{
		return new String[]{"id"};
	}

	@Override
	public List<T> findAll()
	{
		return getRepository().findAll();
	}

	@Override
	public T findById(ID id)
	{
		return getRepository().findById(id).orElseThrow(() -> new NotFoundException("Entity not found"));
	}

	@Override
	public T persist(T entity)
	{
		T savedEntity = createNewInstance(entity);

		BeanUtils.copyProperties(entity, savedEntity, getIgnoredPropertiesOnUpdate());

		return getRepository().save(entity);
	}

	@Override
	public T update(ID id, T entity)
	{
		T savedEntity = findById(id);

		if (!getRepository().existsById(id))
		{
			throw new NotFoundException("Entity not found");
		}

		BeanUtils.copyProperties(entity, savedEntity, getIgnoredPropertiesOnUpdate());

		return getRepository().save(savedEntity);
	}

	@Override
	public void delete(ID id)
	{
		T entity = findById(id);

		getRepository().delete(entity);
	}
}
