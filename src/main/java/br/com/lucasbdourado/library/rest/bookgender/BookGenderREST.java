package br.com.lucasbdourado.library.rest.bookgender;

import br.com.lucasbdourado.library.dto.bookgender.BookGenderResponse;
import br.com.lucasbdourado.library.entity.bookgender.BookGender;
import br.com.lucasbdourado.library.exception.NotFoundException;
import br.com.lucasbdourado.library.mapper.bookgender.BookGenderMapper;
import br.com.lucasbdourado.library.service.bookgender.IBookGenderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book-genders")
@Tag(name = "Genêros de Livros", description = "Operações relacionadas às operações de gêneros de livros")
public class BookGenderREST
{
	private static final String NOT_FOUND = "Not Found";

	private final IBookGenderService service;

	public BookGenderREST(IBookGenderService service)
	{
		this.service = service;
	}

	@GetMapping({"/", ""})
	@Operation(summary = "Listar todas as operações", description = "Retorna uma lista com todas as operações.")
	public ResponseEntity<Object> getBookGenderList()
	{
		try
		{
			List<BookGender> bookGenderList = service.findAll();

			List<BookGenderResponse> bookGenderResponseList = bookGenderList.stream().map(BookGenderMapper::toResponse).toList();

			return ResponseEntity.ok().body(bookGenderResponseList);
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
			BookGender bookGender = service.findById(id);

			BookGenderResponse bookGenderResponse = BookGenderMapper.toResponse(bookGender);

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
	@Operation(summary = "Criar uma operação")
	public ResponseEntity<Object> create(@RequestBody BookGender bookGenderPayload)
	{
		try
		{
			BookGender bookGender = service.persist(bookGenderPayload);

			BookGenderResponse bookGenderResponse = BookGenderMapper.toResponse(bookGender);

			return ResponseEntity.ok().body(bookGenderResponse);
		}
		catch (Exception e)
		{

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualizar uma operação")
	public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody BookGender bookGenderPayload)
	{
		try
		{
			BookGender bookGender = service.update(id, bookGenderPayload);

			BookGenderResponse bookGenderResponse = BookGenderMapper.toResponse(bookGender);

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
