package br.com.lucasbdourado.library.mapper.author;

import br.com.lucasbdourado.library.dto.author.AuthorResponse;
import br.com.lucasbdourado.library.entity.author.Author;

public class AuthorMapper
{

	public static AuthorResponse toResponse(Author author)
	{
		return new AuthorResponse(author.getId(), author.getName());
	}
}
