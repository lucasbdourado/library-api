package br.com.lucasbdourado.library.service.bookexample.implement;

import br.com.lucasbdourado.library.entity.bookexample.BookExample;
import br.com.lucasbdourado.library.repository.bookexample.BookExampleRepository;
import br.com.lucasbdourado.library.service.bookexample.IBookExampleService;
import br.com.lucasbdourado.library.service.generic.implement.GenericBaseService;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class BookExampleService extends GenericBaseService<BookExample, UUID> implements IBookExampleService
{
	private final BookExampleRepository repository;

	public BookExampleService(BookExampleRepository repository)
	{
		this.repository = repository;
	}

	@Override
	protected JpaRepository<BookExample, UUID> getRepository()
	{
		return repository;
	}
}
