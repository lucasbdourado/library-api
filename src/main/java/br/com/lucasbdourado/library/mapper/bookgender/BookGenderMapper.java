package br.com.lucasbdourado.library.mapper.bookgender;

import br.com.lucasbdourado.library.dto.bookgender.BookGenderResponse;
import br.com.lucasbdourado.library.entity.bookgender.BookGender;

public class BookGenderMapper
{

	public static BookGenderResponse toResponse(BookGender bookGender)
	{
		return new BookGenderResponse(bookGender.getId(), bookGender.getName(), bookGender.getDescription());
	}
}
