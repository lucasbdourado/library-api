package br.com.lucasbdourado.library.rest.booklocation;

import br.com.lucasbdourado.library.dto.booklocation.BookLocationResponse;
import br.com.lucasbdourado.library.entity.booklocation.BookLocation;
import br.com.lucasbdourado.library.exception.NotFoundException;
import br.com.lucasbdourado.library.mapper.booklocation.BookLocationMapper;
import br.com.lucasbdourado.library.service.booklocation.IBookLocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book-locations")
@Tag(name = "Localização de Livros", description = "Operações relacionadas às operações de localização de livros")
public class BookLocationREST
{
	private static final String NOT_FOUND = "Not Found";

	private final IBookLocationService service;

	public BookLocationREST(IBookLocationService service)
	{
		this.service = service;
	}

	@GetMapping({"/", ""})
	@Operation(summary = "Listar todas as operações", description = "Retorna uma lista com todas as operações.")
	public ResponseEntity<Object> getBookLocationList()
	{
		try
		{
			List<BookLocation> bookLocationList = service.findAll();

			List<BookLocationResponse> bookLocationResponseList = bookLocationList.stream().map(
				BookLocationMapper::toResponse).toList();

			return ResponseEntity.ok().body(bookLocationResponseList);
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
			BookLocation bookLocation = service.findById(id);

			BookLocationResponse bookLocationResponse = BookLocationMapper.toResponse(bookLocation);

			return ResponseEntity.ok().body(bookLocationResponse);
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
	public ResponseEntity<Object> create(@RequestBody BookLocation bookLocationPayload)
	{
		try
		{
			BookLocation bookLocation = service.persist(bookLocationPayload);

			BookLocationResponse bookLocationResponse = BookLocationMapper.toResponse(bookLocation);

			return ResponseEntity.ok().body(bookLocationResponse);
		}
		catch (Exception e)
		{

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualizar uma operação")
	public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody BookLocation bookLocationPayload)
	{
		try
		{
			BookLocation bookLocation = service.update(id, bookLocationPayload);

			BookLocationResponse bookLocationResponse = BookLocationMapper.toResponse(bookLocation);

			return ResponseEntity.ok().body(bookLocationResponse);
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
