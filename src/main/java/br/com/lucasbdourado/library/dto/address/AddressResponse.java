package br.com.lucasbdourado.library.dto.address;

import br.com.lucasbdourado.library.dto.city.CityResponse;
import br.com.lucasbdourado.library.dto.neighborhood.NeighborhoodResponse;
import br.com.lucasbdourado.library.dto.state.StateResponse;
import java.util.UUID;

public record AddressResponse(UUID id, String country, StateResponse state, CityResponse city, NeighborhoodResponse neighborhood,
                              String zip, String street, String number)
{
}
