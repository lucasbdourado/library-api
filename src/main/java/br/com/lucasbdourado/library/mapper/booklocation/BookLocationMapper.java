package br.com.lucasbdourado.library.mapper.booklocation;

import br.com.lucasbdourado.library.dto.booklocation.BookLocationResponse;
import br.com.lucasbdourado.library.entity.booklocation.BookLocation;

public class BookLocationMapper
{

	public static BookLocationResponse toResponse(BookLocation bookLocation)
	{
		return new BookLocationResponse(bookLocation.getId(), bookLocation.getDescription());
	}
}
