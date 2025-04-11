package br.com.lucasbdourado.library.service.book.implement;

import br.com.lucasbdourado.library.entity.book.Book;
import br.com.lucasbdourado.library.exception.NotFoundException;
import br.com.lucasbdourado.library.repository.book.BookRepository;
import br.com.lucasbdourado.library.service.book.IBookService;
import br.com.lucasbdourado.library.service.generic.implement.GenericBaseService;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService extends GenericBaseService<Book, UUID> implements IBookService
{
	private final BookRepository repository;

	public BookService(BookRepository repository)
	{
		this.repository = repository;
	}

	@Override
	protected JpaRepository<Book, UUID> getRepository()
	{
		return repository;
	}

	@Override
	public List<Book> findAll()
	{
		return super.findAll();
	}

	@Override
	public Book findById(UUID id)
	{
		return super.findById(id);
	}

	@Override
	public Book persist(Book bookPayload)
	{
		return super.persist(bookPayload);
	}

	@Override
	public Book update(UUID id, Book book)
	{
		return super.update(id, book);
	}

	@Override
	public void delete(UUID id) throws NotFoundException
	{
		super.delete(id);
	}
}
