package br.com.lucasbdourado.library.dto.book;

import br.com.lucasbdourado.library.dto.publisher.PublisherResponse;
import java.util.GregorianCalendar;
import java.util.UUID;

public record BookResponse(UUID id, String name, String code, String isbn, GregorianCalendar publishDate,
                           byte edition, PublisherResponse publisher, int quantity, short pages, String collection,
                           String description, String series)
{
}
