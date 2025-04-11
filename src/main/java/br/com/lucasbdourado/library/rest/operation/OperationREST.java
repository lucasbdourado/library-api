package br.com.lucasbdourado.library.rest.operation;

import br.com.lucasbdourado.library.dto.operation.OperationResponse;
import br.com.lucasbdourado.library.entity.operation.Operation;
import br.com.lucasbdourado.library.exception.NotFoundException;
import br.com.lucasbdourado.library.mapper.operation.OperationMapper;
import br.com.lucasbdourado.library.service.operation.IOperationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/operations")
@Tag(name = "Operações", description = "Operações relacionadas às operações de grupos")
public class OperationREST
{
	private static final String NOT_FOUND = "Not Found";

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

			List<OperationResponse> operationResponseList = operationList.stream().map(OperationMapper::toResponse).toList();

			return ResponseEntity.ok().body(operationResponseList);
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

			OperationResponse operationResponse = OperationMapper.toResponse(operation);

			return ResponseEntity.ok().body(operationResponse);
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
			Operation operation = service.persist(operationPayload);

			OperationResponse operationResponse = OperationMapper.toResponse(operation);

			return ResponseEntity.ok().body(operationResponse);
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
			Operation operation = service.update(id, operationPayload);

			OperationResponse operationResponse = OperationMapper.toResponse(operation);

			return ResponseEntity.ok().body(operationResponse);
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
