package br.com.lucasbdourado.library.rest.publisher;

import br.com.lucasbdourado.library.dto.publisher.PublisherResponse;
import br.com.lucasbdourado.library.entity.publisher.Publisher;
import br.com.lucasbdourado.library.exception.NotFoundException;
import br.com.lucasbdourado.library.mapper.publisher.PublisherMapper;
import br.com.lucasbdourado.library.service.publisher.IPublisherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publishers")
@Tag(name = "Editora", description = "Operações relacionadas às editoras")
public class PublisherREST
{

	private static final String NOT_FOUND = "Not Found";

	private final IPublisherService service;

	public PublisherREST(IPublisherService service)
	{
		this.service = service;
	}

	@GetMapping({"/", ""})
	@Operation(summary = "Listar todas as editoras", description = "Retorna uma lista com todas as editoras.")
	public ResponseEntity<Object> getPublisherList()
	{
		try
		{
			List<Publisher> publisherList = service.findAll();

			List<PublisherResponse> publisherResponseList = publisherList.stream().map(PublisherMapper::toResponse)
				.toList();

			return ResponseEntity.ok().body(publisherResponseList);
		}
		catch (Exception e)
		{

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping("/{id}")
	@Operation(summary = "Buscar um editora pelo ID", description = "Retorna a editora pelo Id, caso não encontre nenhum, retorna que não foi encontrada.")
	public ResponseEntity<Object> getById(@PathVariable UUID id)
	{
		try
		{
			Publisher publisher = service.findById(id);

			PublisherResponse publisherResponse = PublisherMapper.toResponse(publisher);

			return ResponseEntity.ok().body(publisherResponse);
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
	@Operation(summary = "Criar uma editora")
	public ResponseEntity<Object> create(@RequestBody Publisher publisherPayload)
	{
		try
		{
			Publisher publisher = service.persist(publisherPayload);

			PublisherResponse publisherResponse = PublisherMapper.toResponse(publisher);

			return ResponseEntity.ok().body(publisherResponse);
		}
		catch (Exception e)
		{

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualizar uma editora")
	public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody Publisher publisherPayload)
	{
		try
		{
			Publisher publisher = service.update(id, publisherPayload);

			PublisherResponse publisherResponse = PublisherMapper.toResponse(publisher);

			return ResponseEntity.ok().body(publisherResponse);
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
	@Operation(summary = "Deletar uma editora pelo Id")
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
