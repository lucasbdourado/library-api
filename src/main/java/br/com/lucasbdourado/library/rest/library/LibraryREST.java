package br.com.lucasbdourado.library.rest.library;

import br.com.lucasbdourado.library.entity.library.Library;
import br.com.lucasbdourado.library.exception.NotFoundException;
import br.com.lucasbdourado.library.service.library.ILibraryService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/libraries")
public class LibraryREST
{
	private static final String NOT_FOUND = "Not Found";

	private final ILibraryService service;

	public LibraryREST(ILibraryService service)
	{
		this.service = service;
	}

	@GetMapping({"/", ""})
	@Operation(summary = "Listar todos os bibliotecas", description = "Retorna uma lista com todos os bibliotecas.")
	public ResponseEntity<Object> getLibraryList()
	{
		try
		{
			List<Library> libraryList = service.findAll();

			return ResponseEntity.ok().body(libraryList);
		}
		catch (Exception e)
		{

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping("/{id}")
	@Operation(summary = "Buscar uma biblioteca pelo ID", description = "Retorna a biblioteca pelo Id, caso não encontre nenhum, retorna que não foi encontrada.")
	public ResponseEntity<Object> getLibraryById(@PathVariable UUID id)
	{
		try
		{
			Library library = service.findById(id);

			return ResponseEntity.ok().body(library);
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
	@Operation(summary = "Criar uma biblioteca")
	public ResponseEntity<Object> create(@RequestBody Library libraryPayload)
	{
		try
		{

			return ResponseEntity.ok().body(service.persist(libraryPayload));
		}
		catch (Exception e)
		{

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualizar uma biblioteca")
	public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody Library libraryPayload)
	{
		try
		{

			return ResponseEntity.ok().body(service.update(id, libraryPayload));
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
	@Operation(summary = "Deletar uma biblioteca pelo Id")
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
