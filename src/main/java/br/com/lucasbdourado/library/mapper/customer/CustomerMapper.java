package br.com.lucasbdourado.library.mapper.customer;

import br.com.lucasbdourado.library.dto.address.AddressResponse;
import br.com.lucasbdourado.library.dto.customer.CustomerResponse;
import br.com.lucasbdourado.library.entity.customer.Customer;
import br.com.lucasbdourado.library.mapper.address.AddressMapper;

public class CustomerMapper
{
	public static CustomerResponse toResponse(Customer customer)
	{
		AddressResponse addressResponse = AddressMapper.toResponse(customer.getAddress());

		return new CustomerResponse(customer.getId(), customer.getName(), customer.getPhone(),
			customer.getGender(), customer.getIndentity(), customer.getIdentityNumber(), addressResponse,
			customer.getGroup(), customer.getLibrary(), customer.getUser());
	}
}
