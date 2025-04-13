package br.com.lucasbdourado.library.rest.reservation;

import br.com.lucasbdourado.library.dto.reservation.ReservationResponse;
import br.com.lucasbdourado.library.entity.reservation.Reservation;
import br.com.lucasbdourado.library.exception.NotFoundException;
import br.com.lucasbdourado.library.mapper.reservation.ReservationMapper;
import br.com.lucasbdourado.library.service.reservation.IReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
@Tag(name = "Reservas", description = "Operações relacionadas às reservas")
public class ReservationREST
{
	private static final String NOT_FOUND = "Not Found";

	private final IReservationService service;

	public ReservationREST(IReservationService service)
	{
		this.service = service;
	}

	@GetMapping({"/", ""})
	@Operation(summary = "Listar todas as reservas", description = "Retorna uma lista com todas as reservas.")
	public ResponseEntity<Object> getReservationList()
	{
		try
		{
			List<Reservation> reservationList = service.findAll();

			List<ReservationResponse> reservationResponseList = reservationList.stream().map(ReservationMapper::toResponse)
				.toList();

			return ResponseEntity.ok().body(reservationResponseList);
		}
		catch (Exception e)
		{

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping("/{id}")
	@Operation(summary = "Buscar um reserva pelo ID", description = "Retorna a reserva pelo Id, caso não encontre nenhum, retorna que não foi encontrada.")
	public ResponseEntity<Object> getById(@PathVariable UUID id)
	{
		try
		{
			Reservation reservation = service.findById(id);

			ReservationResponse reservationResponse = ReservationMapper.toResponse(reservation);

			return ResponseEntity.ok().body(reservationResponse);
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
	@Operation(summary = "Criar uma reserva")
	public ResponseEntity<Object> create(@RequestBody Reservation reservationPayload)
	{
		try
		{
			Reservation reservation = service.persist(reservationPayload);

			ReservationResponse reservationResponse = ReservationMapper.toResponse(reservation);

			return ResponseEntity.ok().body(reservationResponse);
		}
		catch (Exception e)
		{

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualizar uma reserva")
	public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody Reservation reservationPayload)
	{
		try
		{
			Reservation reservation = service.update(id, reservationPayload);

			ReservationResponse reservationResponse = ReservationMapper.toResponse(reservation);

			return ResponseEntity.ok().body(reservationResponse);
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
	@Operation(summary = "Deletar uma reserva pelo Id")
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
