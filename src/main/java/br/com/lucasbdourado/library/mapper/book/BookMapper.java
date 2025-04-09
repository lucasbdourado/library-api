package br.com.lucasbdourado.library.mapper.book;

import br.com.lucasbdourado.library.dto.book.BookResponse;
import br.com.lucasbdourado.library.entity.book.Book;

public class BookMapper
{

	public static BookResponse toResponse(Book book)
	{
		return new BookResponse(book.getId(), book.getName(), book.getCode(), book.getIsbn(),
			book.getPublishDate(), book.getEdition(), book.getQuantity(), book.getPages(),
			book.getCollection(), book.getDescription(), book.getSeries());
	}
}
