package br.com.lucasbdourado.library.dto.publisher;

import br.com.lucasbdourado.library.dto.address.AddressResponse;
import java.util.UUID;

public record PublisherResponse(UUID id, String name, AddressResponse address, String phone, String email)
{
}
