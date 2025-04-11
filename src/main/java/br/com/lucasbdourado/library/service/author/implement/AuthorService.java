package br.com.lucasbdourado.library.service.author.implement;

import br.com.lucasbdourado.library.entity.author.Author;
import br.com.lucasbdourado.library.exception.NotFoundException;
import br.com.lucasbdourado.library.repository.author.AuthorRepository;
import br.com.lucasbdourado.library.service.author.IAuthorService;
import br.com.lucasbdourado.library.service.generic.implement.GenericBaseService;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorService extends GenericBaseService<Author, UUID> implements IAuthorService
{
	private final AuthorRepository repository;

	public AuthorService(AuthorRepository repository)
	{
		this.repository = repository;
	}

	@Override
	protected JpaRepository<Author, UUID> getRepository() {
		return repository;
	}

	@Override
	public List<Author> findAll()
	{
		return super.findAll();
	}

	@Override
	public Author findById(UUID id)
	{
		return super.findById(id);
	}

	@Override
	public Author persist(Author authorPayload)
	{
		return super.persist(authorPayload);
	}

	@Override
	public Author update(UUID id, Author author)
	{
		return super.update(id, author);
	}

	@Override
	public void delete(UUID id) throws NotFoundException
	{
		super.delete(id);
	}
}
