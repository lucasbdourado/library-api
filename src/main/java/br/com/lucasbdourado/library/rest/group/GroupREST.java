package br.com.lucasbdourado.library.rest.group;

import br.com.lucasbdourado.library.entity.group.Group;
import br.com.lucasbdourado.library.exception.NotFoundException;
import br.com.lucasbdourado.library.service.group.IGroupService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/groups")
public class GroupREST
{

	private static final String NOT_FOUND = "Not Found";

	private final IGroupService service;

	public GroupREST(IGroupService service)
	{
		this.service = service;
	}

	@GetMapping({"/", ""})
	@Operation(summary = "Listar todos os grupos", description = "Retorna uma lista com todos os grupos.")
	public ResponseEntity<Object> getGroupList()
	{
		try
		{
			List<Group> groupList = service.findAll();

			return ResponseEntity.ok().body(groupList);
		}
		catch (Exception e)
		{

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping("/{id}")
	@Operation(summary = "Buscar um cliente pelo ID", description = "Retorna o grupo pelo Id, caso não encontre nenhum, retorna que não foi encontrado.")
	public ResponseEntity<Object> getById(@PathVariable UUID id)
	{
		try
		{
			Group group = service.findById(id);

			return ResponseEntity.ok().body(group);
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
	@Operation(summary = "Criar um grupo")
	public ResponseEntity<Object> create(@RequestBody Group groupPayload)
	{
		try
		{

			return ResponseEntity.ok().body(service.persist(groupPayload));
		}
		catch (Exception e)
		{

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualizar um grupo")
	public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody Group groupPayload)
	{
		try
		{

			return ResponseEntity.ok().body(service.update(id, groupPayload));
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
	@Operation(summary = "Deletar um grupo pelo Id")
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
