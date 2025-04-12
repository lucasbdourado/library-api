package br.com.lucasbdourado.library.rest.bookexample;

import br.com.lucasbdourado.library.dto.bookexample.BookExampleResponse;
import br.com.lucasbdourado.library.entity.bookexample.BookExample;
import br.com.lucasbdourado.library.exception.NotFoundException;
import br.com.lucasbdourado.library.mapper.bookexample.BookExampleMapper;
import br.com.lucasbdourado.library.service.bookexample.IBookExampleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book-example")
@Tag(name = "Explemplos de Livros", description = "Todas as exemplos de livros relacionadas a exemplos de livros")
public class BookExampleREST
{
	private static final String NOT_FOUND = "Not Found";

	private final IBookExampleService service;

	public BookExampleREST(IBookExampleService service)
	{
		this.service = service;
	}

	@GetMapping({"/", ""})
	@Operation(summary = "Listar todas as exemplos de livros", description = "Retorna um lista com todas as exemplos de livros.")
	public ResponseEntity<Object> getBookExampleList()
	{
		try
		{
			List<BookExample> bookGenderList = service.findAll();

			List<BookExampleResponse> bookGenderResponseList = bookGenderList.stream().map(
				BookExampleMapper::toResponse).toList();

			return ResponseEntity.ok().body(bookGenderResponseList);
		}
		catch (Exception e)
		{

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping("/{id}")
	@Operation(summary = "Buscar um exemplo de livro pelo ID", description = "Retorna a exemplo de livro pelo Id, caso não encontre nenhum, retorna que não foi encontrado.")
	public ResponseEntity<Object> getById(@PathVariable UUID id)
	{
		try
		{
			BookExample bookGender = service.findById(id);

			BookExampleResponse bookGenderResponse = BookExampleMapper.toResponse(bookGender);

			return ResponseEntity.ok().body(bookGenderResponse);
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
	@Operation(summary = "Criar um exemplo de livro")
	public ResponseEntity<Object> create(@RequestBody BookExample bookGenderPayload)
	{
		try
		{
			BookExample bookGender = service.persist(bookGenderPayload);

			BookExampleResponse bookGenderResponse = BookExampleMapper.toResponse(bookGender);

			return ResponseEntity.ok().body(bookGenderResponse);
		}
		catch (Exception e)
		{

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualizar um exemplo de livro")
	public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody BookExample bookGenderPayload)
	{
		try
		{
			BookExample bookGender = service.update(id, bookGenderPayload);

			BookExampleResponse bookGenderResponse = BookExampleMapper.toResponse(bookGender);

			return ResponseEntity.ok().body(bookGenderResponse);
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
	@Operation(summary = "Deletar um exemplo de livro pelo Id")
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
