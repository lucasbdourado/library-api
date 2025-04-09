package br.com.lucasbdourado.library.dto.address;

import br.com.lucasbdourado.library.dto.state.StateResponse;
import java.util.UUID;

public record AddressResponse(UUID id, String country, StateResponse state, String city, String neighborhood,
                              String zip, String street, String number)
{
}
