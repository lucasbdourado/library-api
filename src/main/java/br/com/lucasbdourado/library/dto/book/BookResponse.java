package br.com.lucasbdourado.library.dto.book;

import br.com.lucasbdourado.library.dto.author.AuthorResponse;
import br.com.lucasbdourado.library.dto.bookgender.BookGenderResponse;
import br.com.lucasbdourado.library.dto.booklanguage.BookLanguageResponse;
import br.com.lucasbdourado.library.dto.publisher.PublisherResponse;
import br.com.lucasbdourado.library.dto.rating.RatingResponse;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

public record BookResponse(UUID id, String name, String code, String isbn, GregorianCalendar publishDate,
                           byte edition, BookGenderResponse gender, PublisherResponse publisher,
                           int quantity, List<AuthorResponse> authorList,
                           List<RatingResponse> ratingList, BookLanguageResponse language, short pages,
                           String collection, String description, String series)
{
}
