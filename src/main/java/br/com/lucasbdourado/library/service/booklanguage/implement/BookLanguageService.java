package br.com.lucasbdourado.library.service.booklanguage.implement;

import br.com.lucasbdourado.library.entity.bookgender.BookGender;
import br.com.lucasbdourado.library.entity.booklanguage.BookLanguage;
import br.com.lucasbdourado.library.repository.bookgender.BookGenderRepository;
import br.com.lucasbdourado.library.repository.booklanguage.BookLanguageRepository;
import br.com.lucasbdourado.library.service.booklanguage.IBookLanguageService;
import br.com.lucasbdourado.library.service.generic.implement.GenericBaseService;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class BookLanguageService extends GenericBaseService<BookLanguage, UUID> implements IBookLanguageService
{
	private final BookLanguageRepository repository;

	public BookLanguageService(BookLanguageRepository repository)
	{
		this.repository = repository;
	}

	@Override
	protected JpaRepository<BookLanguage, UUID> getRepository()
	{
		return repository;
	}
}
