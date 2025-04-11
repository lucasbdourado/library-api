package br.com.lucasbdourado.library.rest.booklanguage;

import br.com.lucasbdourado.library.dto.booklanguage.BookLanguageResponse;
import br.com.lucasbdourado.library.entity.booklanguage.BookLanguage;
import br.com.lucasbdourado.library.exception.NotFoundException;
import br.com.lucasbdourado.library.mapper.booklanguage.BookLanguageMapper;
import br.com.lucasbdourado.library.service.booklanguage.IBookLanguageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book-languages")
@Tag(name = "Idioma de Livros", description = "Operações relacionadas às operações de idioma de livros")
public class BookLanguageREST
{
	private static final String NOT_FOUND = "Not Found";

	private final IBookLanguageService service;

	public BookLanguageREST(IBookLanguageService service)
	{
		this.service = service;
	}

	@GetMapping({"/", ""})
	@Operation(summary = "Listar todas as operações", description = "Retorna uma lista com todas as operações.")
	public ResponseEntity<Object> getBookLanguageList()
	{
		try
		{
			List<BookLanguage> bookLanguageList = service.findAll();

			List<BookLanguageResponse> bookLanguageResponseList = bookLanguageList.stream().map(
				BookLanguageMapper::toResponse).toList();

			return ResponseEntity.ok().body(bookLanguageResponseList);
		}
		catch (Exception e)
		{

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping("/{id}")
	@Operation(summary = "Buscar uma operação pelo ID", description = "Retorna a operação pelo Id, caso não encontre nenhum, retorna que não foi encontrado.")
	public ResponseEntity<Object> getById(@PathVariable UUID id)
	{
		try
		{
			BookLanguage bookLanguage = service.findById(id);

			BookLanguageResponse bookLanguageResponse = BookLanguageMapper.toResponse(bookLanguage);

			return ResponseEntity.ok().body(bookLanguageResponse);
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
	@Operation(summary = "Criar uma operação")
	public ResponseEntity<Object> create(@RequestBody BookLanguage bookLanguagePayload)
	{
		try
		{
			BookLanguage bookLanguage = service.persist(bookLanguagePayload);

			BookLanguageResponse bookLanguageResponse = BookLanguageMapper.toResponse(bookLanguage);

			return ResponseEntity.ok().body(bookLanguageResponse);
		}
		catch (Exception e)
		{

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualizar uma operação")
	public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody BookLanguage bookLanguagePayload)
	{
		try
		{
			BookLanguage bookLanguage = service.update(id, bookLanguagePayload);

			BookLanguageResponse bookLanguageResponse = BookLanguageMapper.toResponse(bookLanguage);

			return ResponseEntity.ok().body(bookLanguageResponse);
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
	@Operation(summary = "Deletar uma operação pelo Id")
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
