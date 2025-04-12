package br.com.lucasbdourado.library.service.booklocation.implement;

import br.com.lucasbdourado.library.entity.booklocation.BookLocation;
import br.com.lucasbdourado.library.repository.booklocation.BookLocationRepository;
import br.com.lucasbdourado.library.service.booklocation.IBookLocationService;
import br.com.lucasbdourado.library.service.generic.implement.GenericBaseService;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class BookLocationService extends GenericBaseService<BookLocation, UUID> implements IBookLocationService
{
	private final BookLocationRepository repository;

	public BookLocationService(BookLocationRepository repository)
	{
		this.repository = repository;
	}

	@Override
	protected JpaRepository<BookLocation, UUID> getRepository()
	{
		return repository;
	}
}
