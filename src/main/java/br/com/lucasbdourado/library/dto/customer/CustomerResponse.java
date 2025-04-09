package br.com.lucasbdourado.library.dto.customer;

import br.com.lucasbdourado.library.dto.address.AddressResponse;
import br.com.lucasbdourado.library.entity.customer.Gender;
import br.com.lucasbdourado.library.entity.customer.Indentity;
import br.com.lucasbdourado.library.entity.group.Group;
import br.com.lucasbdourado.library.entity.library.Library;
import br.com.lucasbdourado.library.entity.user.User;
import java.util.UUID;

public record CustomerResponse(UUID id, String name, String phone, Gender gender, Indentity indentity,
                               String identityNumber, AddressResponse address, Group group, Library library,
                               User user)
{
}
