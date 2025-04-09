package br.com.lucasbdourado.library.rest.author;

import br.com.lucasbdourado.library.dto.author.AuthorResponse;
import br.com.lucasbdourado.library.entity.author.Author;
import br.com.lucasbdourado.library.exception.NotFoundException;
import br.com.lucasbdourado.library.mapper.author.AuthorMapper;
import br.com.lucasbdourado.library.service.author.IAuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
@Tag(name = "Autores", description = "Operações relacionadas aos autores")
public class AuthorREST
{
	private static final String NOT_FOUND = "Not Found";

	private final IAuthorService service;

	public AuthorREST(IAuthorService service)
	{
		this.service = service;
	}

	@GetMapping({"/", ""})
	@Operation(summary = "Listar todos os autores", description = "Retorna uma lista com todos as autores.")
	public ResponseEntity<Object> getAuthorList()
	{
		try
		{
			List<Author> authorList = service.findAll();

			List<AuthorResponse> authorResponseList = authorList.stream().map(AuthorMapper::toResponse)
				.toList();

			return ResponseEntity.ok().body(authorResponseList);
		}
		catch (Exception e)
		{

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping("/{id}")
	@Operation(summary = "Buscar um autor pelo ID", description = "Retorna o autor pelo Id, caso não encontre nenhum, retorna que não foi encontrada.")
	public ResponseEntity<Object> getById(@PathVariable UUID id)
	{
		try
		{
			Author author = service.findById(id);

			AuthorResponse authorResponse = AuthorMapper.toResponse(author);

			return ResponseEntity.ok().body(authorResponse);
		}
		catch (NotFoundException e)
		{

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND);
		}
		catch (Exception e)
		{

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PostMapping({"/", ""})
	@Operation(summary = "Criar um autor")
	public ResponseEntity<Object> create(@RequestBody Author authorPayload)
	{
		try
		{
			Author author = service.persist(authorPayload);

			AuthorResponse authorResponse = AuthorMapper.toResponse(author);

			return ResponseEntity.ok().body(authorResponse);
		}
		catch (Exception e)
		{

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualizar um autor")
	public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody Author authorPayload)
	{
		try
		{
			Author author = service.update(id, authorPayload);

			AuthorResponse authorResponse = AuthorMapper.toResponse(author);

			return ResponseEntity.ok().body(authorResponse);
		}
		catch (NotFoundException e)
		{

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND);
		}
		catch (Exception e)
		{

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deletar um autor pelo Id")
	public ResponseEntity<Object> delete(@PathVariable UUID id)
	{
		try
		{
			service.delete(id);

			return ResponseEntity.ok().body("Deletado com sucesso!");
		}
		catch (NotFoundException e)
		{

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND);
		}
		catch (Exception e)
		{

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
