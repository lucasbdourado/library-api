package br.com.lucasbdourado.library.mapper.booklanguage;

import br.com.lucasbdourado.library.dto.booklanguage.BookLanguageResponse;
import br.com.lucasbdourado.library.entity.booklanguage.BookLanguage;

public class BookLanguageMapper
{

	public static BookLanguageResponse toResponse(BookLanguage bookLanguage)
	{
		return new BookLanguageResponse(bookLanguage.getId(), bookLanguage.getLanguage(),
			bookLanguage.getDescription());
	}
}
