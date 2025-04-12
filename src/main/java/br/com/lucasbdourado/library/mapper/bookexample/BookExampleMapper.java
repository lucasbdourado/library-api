package br.com.lucasbdourado.library.mapper.bookexample;

import br.com.lucasbdourado.library.dto.bookexample.BookExampleResponse;
import br.com.lucasbdourado.library.entity.bookexample.BookExample;

public class BookExampleMapper
{

	public static BookExampleResponse toResponse(BookExample bookExample)
	{
		return new BookExampleResponse(bookExample.getId(), bookExample.getTitle(),
			bookExample.getAquisitionDate(), bookExample.getObservation());
	}
}
