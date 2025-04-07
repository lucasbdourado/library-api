package br.com.lucasbdourado.library.service.customer;

import br.com.lucasbdourado.library.entity.customer.Customer;
import java.util.List;
import java.util.UUID;

public interface ICustomerService
{
	List<Customer> findAll();

	Customer findById(UUID id);

	Customer persist(Customer customer);

	Customer update(UUID id, Customer customer);

	void delete(UUID id);
}
