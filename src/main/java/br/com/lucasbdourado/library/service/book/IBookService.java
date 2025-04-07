package br.com.lucasbdourado.library.service.book;

import br.com.lucasbdourado.library.entity.book.Book;
import java.util.List;
import java.util.UUID;

public interface IBookService
{
	List<Book> findAll();

	Book findById(UUID id);

	Book persist(Book bookPayload);

	Book update(UUID id, Book bookPayload);

	void delete(UUID id);
}
