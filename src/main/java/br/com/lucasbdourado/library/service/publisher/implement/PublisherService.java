package br.com.lucasbdourado.library.service.publisher.implement;

import br.com.lucasbdourado.library.entity.publisher.Publisher;
import br.com.lucasbdourado.library.exception.NotFoundException;
import br.com.lucasbdourado.library.repository.publisher.PublisherRepository;
import br.com.lucasbdourado.library.service.generic.implement.GenericBaseService;
import br.com.lucasbdourado.library.service.publisher.IPublisherService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PublisherService extends GenericBaseService<Publisher, UUID> implements IPublisherService
{
	private static final String NOT_FOUND = "Not Found";

	private final PublisherRepository repository;

	public PublisherService(PublisherRepository repository)
	{
		this.repository = repository;
	}

	@Override
	protected JpaRepository<Publisher, UUID> getRepository()
	{
		return repository;
	}

	@Override
	public List<Publisher> findAll()
	{
		return super.findAll();
	}

	@Override
	public Publisher findById(UUID id)
	{
		return super.findById(id);
	}

	@Override
	public Publisher persist(Publisher publisherPayload)
	{
		return super.persist(publisherPayload);
	}

	@Override
	public Publisher update(UUID id, Publisher publisher)
	{
		return super.update(id, publisher);
	}

	@Override
	public void delete(UUID id) throws NotFoundException
	{
		super.delete(id);
	}
}
