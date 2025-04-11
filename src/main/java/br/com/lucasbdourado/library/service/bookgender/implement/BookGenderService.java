package br.com.lucasbdourado.library.service.bookgender.implement;

import br.com.lucasbdourado.library.entity.bookgender.BookGender;
import br.com.lucasbdourado.library.repository.bookgender.BookGenderRepository;
import br.com.lucasbdourado.library.service.bookgender.IBookGenderService;
import br.com.lucasbdourado.library.service.generic.implement.GenericBaseService;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class BookGenderService extends GenericBaseService<BookGender, UUID> implements IBookGenderService
{
	private final BookGenderRepository repository;

	public BookGenderService(BookGenderRepository repository)
	{
		this.repository = repository;
	}

	@Override
	protected JpaRepository<BookGender, UUID> getRepository()
	{
		return repository;
	}
}
