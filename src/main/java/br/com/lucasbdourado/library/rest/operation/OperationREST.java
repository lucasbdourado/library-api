package br.com.lucasbdourado.library.rest.operation;

import br.com.lucasbdourado.library.entity.operation.Operation;
import br.com.lucasbdourado.library.exception.NotFoundException;
import br.com.lucasbdourado.library.service.operation.IOperationService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/operations")
public class OperationREST
{

	private static final String NOT_FOUND = "Not Found";

	@Autowired
	private final IOperationService service;

	public OperationREST(IOperationService service)
	{
		this.service = service;
	}

	@GetMapping({"/", ""})
	@io.swagger.v3.oas.annotations.Operation(summary = "Listar todas as operações", description = "Retorna uma lista com todas as operações.")
	public ResponseEntity<Object> getOperationList()
	{
		try
		{
			List<Operation> operationList = service.findAll();

			return ResponseEntity.ok().body(operationList);
		}
		catch (Exception e)
		{

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping("/{id}")
	@io.swagger.v3.oas.annotations.Operation(summary = "Buscar uma operação pelo ID", description = "Retorna a operação pelo Id, caso não encontre nenhum, retorna que não foi encontrado.")
	public ResponseEntity<Object> getById(@PathVariable UUID id)
	{
		try
		{
			Operation operation = service.findById(id);

			return ResponseEntity.ok().body(operation);
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
	@io.swagger.v3.oas.annotations.Operation(summary = "Criar uma operação")
	public ResponseEntity<Object> create(@RequestBody Operation operationPayload)
	{
		try
		{

			return ResponseEntity.ok().body(service.persist(operationPayload));
		}
		catch (Exception e)
		{

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	@io.swagger.v3.oas.annotations.Operation(summary = "Atualizar uma operação")
	public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody Operation operationPayload)
	{
		try
		{

			return ResponseEntity.ok().body(service.update(id, operationPayload));
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
	@io.swagger.v3.oas.annotations.Operation(summary = "Deletar uma operação pelo Id")
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
