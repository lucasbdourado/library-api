package br.com.lucasbdourado.library.service.generic;

import java.util.List;

public interface GenericBaseServiceInterface<T, ID>
{
	List<T> findAll();

	T findById(ID id);

	T persist(T entity);

	T update(ID id, T entity);

	void delete(ID id);
}
