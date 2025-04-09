package br.com.lucasbdourado.library.service.customer.implement;

import br.com.lucasbdourado.library.entity.address.Address;
import br.com.lucasbdourado.library.entity.customer.Customer;
import br.com.lucasbdourado.library.entity.group.Group;
import br.com.lucasbdourado.library.entity.library.Library;
import br.com.lucasbdourado.library.entity.user.User;
import br.com.lucasbdourado.library.exception.NotFoundException;
import br.com.lucasbdourado.library.repository.address.AddressRepository;
import br.com.lucasbdourado.library.repository.customer.CustomerRepository;
import br.com.lucasbdourado.library.repository.group.GroupRepository;
import br.com.lucasbdourado.library.repository.library.LibraryRepository;
import br.com.lucasbdourado.library.repository.user.UserRepository;
import br.com.lucasbdourado.library.service.customer.ICustomerService;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService implements ICustomerService
{
	private static final String NOT_FOUND = "Not Found";

	private final CustomerRepository repository;

	private final AddressRepository addressRepository;

	private final GroupRepository groupRepository;

	private final LibraryRepository libraryRepository;

	private final UserRepository userRepository;

	public CustomerService(CustomerRepository repository, AddressRepository addressRepository,
		GroupRepository groupRepository, LibraryRepository libraryRepository,
		UserRepository userRepository)
	{
		this.repository = repository;
		this.addressRepository = addressRepository;
		this.groupRepository = groupRepository;
		this.libraryRepository = libraryRepository;
		this.userRepository = userRepository;
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
		Customer savedCustomer = repository.findById(id)
			.orElseThrow(() -> new NotFoundException(NOT_FOUND));

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
