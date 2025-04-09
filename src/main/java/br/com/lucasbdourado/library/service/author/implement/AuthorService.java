package br.com.lucasbdourado.library.service.author.implement;

import br.com.lucasbdourado.library.entity.author.Author;
import br.com.lucasbdourado.library.exception.NotFoundException;
import br.com.lucasbdourado.library.repository.author.AuthorRepository;
import br.com.lucasbdourado.library.service.author.IAuthorService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class AuthorService implements IAuthorService
{
	private static final String NOT_FOUND = "Not Found";

	private final AuthorRepository repository;

	public AuthorService(AuthorRepository repository)
	{
		this.repository = repository;
	}

	@Override
	public List<Author> findAll()
	{
		return repository.findAll();
	}

	@Override
	public Author findById(UUID id)
	{
		return repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND));
	}

	@Override
	public Author persist(Author authorPayload)
	{
		Author author = new Author();

		BeanUtils.copyProperties(authorPayload, author);

		return repository.save(author);
	}

	@Override
	public Author update(UUID id, Author author)
	{
		Author savedAuthor = repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND));

		BeanUtils.copyProperties(author, savedAuthor, "id", "creationDate");

		return repository.save(savedAuthor);
	}

	@Override
	public void delete(UUID id) throws NotFoundException
	{
		Author author = repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND));

		repository.delete(author);
	}
}
