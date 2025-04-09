package br.com.lucasbdourado.library.rest.rating;

import br.com.lucasbdourado.library.dto.rating.RatingResponse;
import br.com.lucasbdourado.library.entity.rating.Rating;
import br.com.lucasbdourado.library.exception.NotFoundException;
import br.com.lucasbdourado.library.mapper.rating.RatingMapper;
import br.com.lucasbdourado.library.service.rating.IRatingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
@Tag(name = "Classificações", description = "Operações relacionadas às classificações / avaliações")
public class RatingREST
{

	private static final String NOT_FOUND = "Not Found";

	private final IRatingService service;

	public RatingREST(IRatingService service)
	{
		this.service = service;
	}

	@GetMapping({"/", ""})
	@Operation(summary = "Listar todas as editoras", description = "Retorna uma lista com todas as editoras.")
	public ResponseEntity<Object> getRatingList()
	{
		try
		{
			List<Rating> ratingList = service.findAll();

			List<RatingResponse> ratingResponseList = ratingList.stream().map(RatingMapper::toResponse)
				.toList();

			return ResponseEntity.ok().body(ratingResponseList);
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
			Rating rating = service.findById(id);

			RatingResponse ratingResponse = RatingMapper.toResponse(rating);

			return ResponseEntity.ok().body(ratingResponse);
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
	public ResponseEntity<Object> create(@RequestBody Rating ratingPayload)
	{
		try
		{
			Rating rating = service.persist(ratingPayload);

			RatingResponse ratingResponse = RatingMapper.toResponse(rating);

			return ResponseEntity.ok().body(ratingResponse);
		}
		catch (Exception e)
		{

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualizar uma editora")
	public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody Rating ratingPayload)
	{
		try
		{
			Rating rating = service.update(id, ratingPayload);

			RatingResponse ratingResponse = RatingMapper.toResponse(rating);

			return ResponseEntity.ok().body(ratingResponse);
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
