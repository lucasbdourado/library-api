package br.com.lucasbdourado.library.service.customer.implement;

import br.com.lucasbdourado.library.entity.address.Address;
import br.com.lucasbdourado.library.entity.customer.Customer;
import br.com.lucasbdourado.library.exception.NotFoundException;
import br.com.lucasbdourado.library.repository.address.AddressRepository;
import br.com.lucasbdourado.library.repository.customer.CustomerRepository;
import br.com.lucasbdourado.library.service.customer.ICustomerService;
import br.com.lucasbdourado.library.service.generic.implement.GenericBaseService;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService extends GenericBaseService<Customer, UUID> implements ICustomerService
{
	private static final String NOT_FOUND = "Not Found";

	private final CustomerRepository repository;

	private final AddressRepository addressRepository;

	public CustomerService(CustomerRepository repository, AddressRepository addressRepository)
	{
		this.repository = repository;
		this.addressRepository = addressRepository;
	}

	@Override
	protected JpaRepository<Customer, UUID> getRepository()
	{
		return repository;
	}

	@Override
	public List<Customer> findAll()
	{
		return super.findAll();
	}

	@Override
	public Customer findById(UUID id)
	{
		return super.findById(id);
	}

	@Override
	@Transactional
	public Customer persist(Customer customerPayload)
	{
		Customer customer = new Customer();

		BeanUtils.copyProperties(customerPayload, customer);

		customer.setCreationDate(new GregorianCalendar());
		customer.setUpdateDate(new GregorianCalendar());

		UUID uuid = customer.getAddress().getId();

		Address address = addressRepository.getReferenceById(uuid);

		customer.setAddress(address);

		return repository.save(customer);
	}

	@Override
	public Customer update(UUID id, Customer customer)
	{
		return super.update(id, customer);
	}

	@Override
	public void delete(UUID id) throws NotFoundException
	{
		super.delete(id);
	}
}
