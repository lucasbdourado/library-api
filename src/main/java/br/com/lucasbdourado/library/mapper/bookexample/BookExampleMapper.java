package br.com.lucasbdourado.library.mapper.bookexample;

import br.com.lucasbdourado.library.dto.bookexample.BookExampleResponse;
import br.com.lucasbdourado.library.dto.loan.LoanResponse;
import br.com.lucasbdourado.library.entity.bookexample.BookExample;
import br.com.lucasbdourado.library.mapper.loan.LoanMapper;

public class BookExampleMapper
{

	public static BookExampleResponse toResponse(BookExample bookExample)
	{
		return new BookExampleResponse(bookExample.getId(), bookExample.getTitle(),
			bookExample.getAquisitionDate(), bookExample.getObservation());
	}
}
