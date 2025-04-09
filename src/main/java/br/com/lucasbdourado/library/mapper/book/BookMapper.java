package br.com.lucasbdourado.library.mapper.book;

import br.com.lucasbdourado.library.dto.book.BookResponse;
import br.com.lucasbdourado.library.dto.publisher.PublisherResponse;
import br.com.lucasbdourado.library.entity.book.Book;
import br.com.lucasbdourado.library.mapper.publisher.PublisherMapper;

public class BookMapper
{

	public static BookResponse toResponse(Book book)
	{
		PublisherResponse publisherResponse = PublisherMapper.toResponse(book.getPublisher());

		return new BookResponse(book.getId(), book.getName(), book.getCode(), book.getIsbn(),
			book.getPublishDate(), book.getEdition(), publisherResponse,  book.getQuantity(), book.getPages(),
			book.getCollection(), book.getDescription(), book.getSeries());
	}
}
