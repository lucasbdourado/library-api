package br.com.lucasbdourado.library.service.customer.implement;

import br.com.lucasbdourado.library.entity.customer.Customer;
import br.com.lucasbdourado.library.exception.NotFoundException;
import br.com.lucasbdourado.library.repository.customer.CustomerRepository;
import br.com.lucasbdourado.library.service.customer.ICustomerService;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService
{
	private static final String NOT_FOUND = "Not Found";

	@Autowired
	private final CustomerRepository repository;

	public CustomerService(CustomerRepository repository)
	{
		this.repository = repository;
	}

	@Override
	public List<Customer> findAll()
	{
		return repository.findAll();
	}

	@Override
	public Customer findById(UUID id)
	{
		return repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND));
	}

	@Override
	public Customer persist(Customer customerPayload)
	{
		Customer customer = new Customer();

		BeanUtils.copyProperties(customerPayload, customer);

		customer.setCreationDate(new GregorianCalendar());
		customer.setUpdateDate(new GregorianCalendar());

		return repository.save(customer);
	}

	@Override
	public Customer update(UUID id, Customer customer)
	{
		Customer savedCustomer = repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND));

		BeanUtils.copyProperties(customer, savedCustomer, "id", "creationDate");

		savedCustomer.setUpdateDate(new GregorianCalendar());

		return repository.save(savedCustomer);
	}

	@Override
	public void delete(UUID id) throws NotFoundException
	{
		Customer customer = repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND));

		repository.delete(customer);
	}
}
