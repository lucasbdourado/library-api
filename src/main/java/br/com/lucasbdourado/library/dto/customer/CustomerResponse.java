package br.com.lucasbdourado.library.dto.customer;

import br.com.lucasbdourado.library.dto.address.AddressResponse;
import br.com.lucasbdourado.library.dto.group.GroupResponse;
import br.com.lucasbdourado.library.dto.library.LibraryResponse;
import br.com.lucasbdourado.library.dto.user.UserResponse;
import br.com.lucasbdourado.library.entity.customer.Gender;
import br.com.lucasbdourado.library.entity.customer.Indentity;
import java.util.UUID;

public record CustomerResponse(UUID id, String name, String phone, Gender gender, Indentity indentity,
                               String identityNumber, AddressResponse address, GroupResponse group,
                               LibraryResponse library, UserResponse user)
{
}
