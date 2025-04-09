package br.com.lucasbdourado.library.mapper.address;

import br.com.lucasbdourado.library.dto.address.AddressResponse;
import br.com.lucasbdourado.library.dto.city.CityResponse;
import br.com.lucasbdourado.library.dto.neighborhood.NeighborhoodResponse;
import br.com.lucasbdourado.library.dto.state.StateResponse;
import br.com.lucasbdourado.library.entity.address.Address;
import br.com.lucasbdourado.library.entity.city.City;
import br.com.lucasbdourado.library.entity.neighborhood.Neighborhood;
import br.com.lucasbdourado.library.entity.state.State;

public class AddressMapper
{
	public static AddressResponse toResponse(Address address)
	{
		State state = address.getState();

		City city = address.getCity();

		Neighborhood neighborhood = address.getNeighborhood();

		StateResponse stateResponse = new StateResponse(state.getId(),
			state.getCode(), state.getName(),
			state.getStateAcronym());

		CityResponse cityResponse = new CityResponse(city.getId(),
			city.getCode(), city.getName());

		NeighborhoodResponse neighborhoodResponse = new NeighborhoodResponse(
			neighborhood.getId(), neighborhood.getCode(),
			neighborhood.getName(), neighborhood.getStateAcronym());

		return new AddressResponse(address.getId(), address.getCountry(), stateResponse, cityResponse,
			neighborhoodResponse, address.getZip(), address.getStreet(), address.getNumber());
	}
}
