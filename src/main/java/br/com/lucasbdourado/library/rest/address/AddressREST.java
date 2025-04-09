package br.com.lucasbdourado.library.rest.address;

import br.com.lucasbdourado.library.dto.address.AddressResponse;
import br.com.lucasbdourado.library.dto.city.CityResponse;
import br.com.lucasbdourado.library.dto.neighborhood.NeighborhoodResponse;
import br.com.lucasbdourado.library.dto.state.StateResponse;
import br.com.lucasbdourado.library.entity.address.Address;
import br.com.lucasbdourado.library.entity.city.City;
import br.com.lucasbdourado.library.entity.neighborhood.Neighborhood;
import br.com.lucasbdourado.library.entity.state.State;
import br.com.lucasbdourado.library.exception.NotFoundException;
import br.com.lucasbdourado.library.service.address.IAddressService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressREST
{

	private static final String NOT_FOUND = "Not Found";

	private final IAddressService service;

	public AddressREST(IAddressService service)
	{
		this.service = service;
	}

	@GetMapping({"/", ""})
	@Operation(summary = "Listar todos os endereços", description = "Retorna uma lista com todos os endereços.")
	public ResponseEntity<Object> getAddressList()
	{
		try
		{
			List<Address> entityList = service.findAll();

			return ResponseEntity.ok().body(entityList);
		}
		catch (Exception e)
		{

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping("/{id}")
	@Operation(summary = "Buscar um endereço pelo ID", description = "Retorna o endereço pelo Id, caso não encontre nenhum, retorna que não foi encontrado.")
	public ResponseEntity<Object> getAddressById(@PathVariable UUID id)
	{
		try
		{
			Address address = service.findById(id);

			return ResponseEntity.ok().body(address);
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
	@Operation(summary = "Criar um endereço")
	public ResponseEntity<Object> create(@RequestBody Address addressPayload)
	{
		try
		{
			Address address = service.persist(addressPayload);

			State state = address.getState();

			City city = address.getCity();

			Neighborhood neighborhood = address.getNeighborhood();

			StateResponse stateResponse = new StateResponse(state.getId(), state.getCode(),
				state.getName(), state.getStateAcronym());

			CityResponse cityResponse = new CityResponse(city.getId(), city.getCode(), city.getName());

			NeighborhoodResponse neighborhoodResponse = new NeighborhoodResponse(neighborhood.getId(),
				neighborhood.getCode(), neighborhood.getName(), neighborhood.getStateAcronym());

			AddressResponse addressResponse = new AddressResponse(address.getId(), address.getCountry(),
				stateResponse, cityResponse, neighborhoodResponse, address.getZip(),
				address.getStreet(), address.getNumber());

			return ResponseEntity.ok().body(addressResponse);
		}
		catch (Exception e)
		{

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualizar um endereço")
	public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody Address addressPayload)
	{
		try
		{

			return ResponseEntity.ok().body(service.update(id, addressPayload));
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
	@Operation(summary = "Deletar um endereço pelo Id")
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
