package br.com.lucasbdourado.library.rest.customer;

import br.com.lucasbdourado.library.dto.address.AddressResponse;
import br.com.lucasbdourado.library.dto.city.CityResponse;
import br.com.lucasbdourado.library.dto.customer.CustomerResponse;
import br.com.lucasbdourado.library.dto.neighborhood.NeighborhoodResponse;
import br.com.lucasbdourado.library.dto.state.StateResponse;
import br.com.lucasbdourado.library.entity.address.Address;
import br.com.lucasbdourado.library.entity.city.City;
import br.com.lucasbdourado.library.entity.customer.Customer;
import br.com.lucasbdourado.library.entity.neighborhood.Neighborhood;
import br.com.lucasbdourado.library.entity.state.State;
import br.com.lucasbdourado.library.exception.NotFoundException;
import br.com.lucasbdourado.library.service.customer.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@Tag(name = "Clientes", description = "Operações relacionadas aos clientes")
public class CustomerREST
{

	private static final String NOT_FOUND = "Not Found";

	private final ICustomerService service;

	public CustomerREST(ICustomerService service)
	{
		this.service = service;
	}

	@GetMapping({"/", ""})
	@Operation(summary = "Listar todos os clientes", description = "Retorna uma lista com todos os clientes.")
	public ResponseEntity<Object> getCustomerList()
	{
		try
		{
			List<Customer> customerList = service.findAll();

			return ResponseEntity.ok().body(customerList);
		}
		catch (Exception e)
		{

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping("/{id}")
	@Operation(summary = "Buscar um cliente pelo ID", description = "Retorna o cliente pelo Id, caso não encontre nenhum, retorna que não foi encontrado.")
	public ResponseEntity<Object> getById(@PathVariable UUID id)
	{
		try
		{
			Customer customer = service.findById(id);

			return ResponseEntity.ok().body(customer);
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
	@Operation(summary = "Criar um cliente")
	public ResponseEntity<Object> create(@RequestBody Customer customerPayload)
	{
		try
		{
			Customer customer = service.persist(customerPayload);

			Address address = customer.getAddress();

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

			CustomerResponse customerResponse = new CustomerResponse(customer.getId(), customer.getName(),
				customer.getPhone(), customer.getGender(), customer.getIndentity(), customer.getIdentityNumber(), addressResponse,
				customer.getGroup(), customer.getLibrary(), customer.getUser());

			return ResponseEntity.ok().body(customerResponse);
		}
		catch (Exception e)
		{

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualizar um cliente")
	public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody Customer customerPayload)
	{
		try
		{

			return ResponseEntity.ok().body(service.update(id, customerPayload));
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
	@Operation(summary = "Deletar um cliente pelo Id")
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
