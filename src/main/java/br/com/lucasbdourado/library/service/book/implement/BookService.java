package br.com.lucasbdourado.library.service.book.implement;

import br.com.lucasbdourado.library.entity.book.Book;
import br.com.lucasbdourado.library.exception.NotFoundException;
import br.com.lucasbdourado.library.repository.book.BookRepository;
import br.com.lucasbdourado.library.service.book.IBookService;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService implements IBookService
{
	private static final String NOT_FOUND = "Not Found";

	@Autowired
	private final BookRepository repository;

	public BookService(BookRepository repository)
	{
		this.repository = repository;
	}

	@Override
	public List<Book> findAll()
	{
		return repository.findAll();
	}

	@Override
	public Book findById(UUID id)
	{
		return repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND));
	}

	@Override
	public Book persist(Book bookPayload)
	{
		Book book = new Book();

		BeanUtils.copyProperties(bookPayload, book);

		book.setCreationDate(new GregorianCalendar());
		book.setUpdateDate(new GregorianCalendar());

		return repository.save(book);
	}

	@Override
	public Book update(UUID id, Book book)
	{
		Book savedBook = repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND));

		BeanUtils.copyProperties(book, savedBook, "id", "creationDate");

		savedBook.setUpdateDate(new GregorianCalendar());

		return repository.save(savedBook);
	}

	@Override
	public void delete(UUID id) throws NotFoundException
	{
		Book book = repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND));

		repository.delete(book);
	}
}
