package br.com.lucasbdourado.library.dto.library;

import br.com.lucasbdourado.library.dto.address.AddressResponse;
import java.util.UUID;

public record LibraryResponse(UUID id, String name, AddressResponse address, String phone)
{
}
