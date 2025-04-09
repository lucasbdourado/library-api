package br.com.lucasbdourado.library.service.author;

import br.com.lucasbdourado.library.entity.author.Author;
import java.util.List;
import java.util.UUID;

public interface IAuthorService
{
	List<Author> findAll();

	Author findById(UUID id);

	Author persist(Author authorPayload);

	Author update(UUID id, Author authorPayload);

	void delete(UUID id);
}
