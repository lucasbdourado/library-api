package br.com.lucasbdourado.library.mapper.book;

import br.com.lucasbdourado.library.dto.author.AuthorResponse;
import br.com.lucasbdourado.library.dto.book.BookResponse;
import br.com.lucasbdourado.library.dto.bookexample.BookExampleResponse;
import br.com.lucasbdourado.library.dto.bookgender.BookGenderResponse;
import br.com.lucasbdourado.library.dto.booklanguage.BookLanguageResponse;
import br.com.lucasbdourado.library.dto.publisher.PublisherResponse;
import br.com.lucasbdourado.library.dto.rating.RatingResponse;
import br.com.lucasbdourado.library.entity.book.Book;
import br.com.lucasbdourado.library.mapper.author.AuthorMapper;
import br.com.lucasbdourado.library.mapper.bookexample.BookExampleMapper;
import br.com.lucasbdourado.library.mapper.bookgender.BookGenderMapper;
import br.com.lucasbdourado.library.mapper.booklanguage.BookLanguageMapper;
import br.com.lucasbdourado.library.mapper.publisher.PublisherMapper;
import br.com.lucasbdourado.library.mapper.rating.RatingMapper;
import java.util.List;

public class BookMapper
{

	public static BookResponse toResponse(Book book)
	{
		PublisherResponse publisherResponse = PublisherMapper.toResponse(book.getPublisher());

		List<AuthorResponse> authorResponseList = book.getAuthorList().stream()
			.map(AuthorMapper::toResponse).toList();

		List<RatingResponse> ratingResponseList = book.getRatingList().stream()
			.map(RatingMapper::toResponse).toList();

		BookGenderResponse bookGenderResponse = BookGenderMapper.toResponse(book.getGender());

		BookLanguageResponse bookLanguageResponse = BookLanguageMapper.toResponse(book.getLanguage());

		List<BookExampleResponse> bookExampleResponseList = book.getBookExampleList().stream()
			.map(BookExampleMapper::toResponse).toList();

		return new BookResponse(book.getId(), book.getName(), book.getCode(), book.getIsbn(),
			book.getPublishDate(), book.getEdition(), bookGenderResponse, publisherResponse,
			book.getQuantity(), authorResponseList, ratingResponseList, bookLanguageResponse,
			bookExampleResponseList, book.getPages(), book.getCollection(), book.getDescription(),
			book.getSeries());
	}
}
