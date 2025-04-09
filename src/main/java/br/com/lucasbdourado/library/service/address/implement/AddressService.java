package br.com.lucasbdourado.library.service.address.implement;

import br.com.lucasbdourado.library.entity.address.Address;
import br.com.lucasbdourado.library.entity.city.City;
import br.com.lucasbdourado.library.entity.neighborhood.Neighborhood;
import br.com.lucasbdourado.library.entity.state.State;
import br.com.lucasbdourado.library.exception.NotFoundException;
import br.com.lucasbdourado.library.repository.address.AddressRepository;
import br.com.lucasbdourado.library.repository.city.CityRepository;
import br.com.lucasbdourado.library.repository.neighborhood.NeighborhoodRepository;
import br.com.lucasbdourado.library.repository.state.StateRepository;
import br.com.lucasbdourado.library.service.address.IAddressService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class AddressService implements IAddressService
{

	private static final String NOT_FOUND = "Not Found";

	private final AddressRepository repository;

	private final StateRepository stateRepository;

	private final CityRepository cityRepository;

	private final NeighborhoodRepository neighborhoodRepository;

	public AddressService(AddressRepository repository, StateRepository stateRepository,
		CityRepository cityRepository, NeighborhoodRepository neighborhoodRepository)
	{
		this.repository = repository;
		this.stateRepository = stateRepository;
		this.cityRepository = cityRepository;
		this.neighborhoodRepository = neighborhoodRepository;
	}

	@Override
	public List<Address> findAll()
	{
		return repository.findAll();
	}

	@Override
	public Address findById(UUID id)
	{
		return repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND));
	}

	@Override
	public Address persist(Address addressPayload)
	{
		Address address = new Address();

		Long stateId = addressPayload.getState().getId();

		State state = stateRepository.getReferenceById(stateId);

		Long cityId = addressPayload.getCity().getId();

		City city = cityRepository.getReferenceById(cityId);

		Long neighborhoodId = addressPayload.getNeighborhood().getId();

		Neighborhood neighborhood = neighborhoodRepository.getReferenceById(neighborhoodId);

		BeanUtils.copyProperties(addressPayload, address, "state", "city", "neighborhood");

		address.setState(state);

		address.setCity(city);

		address.setNeighborhood(neighborhood);

		return repository.save(address);
	}

	@Override
	public Address update(UUID id, Address address)
	{
		Address savedAddress = repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND));

		BeanUtils.copyProperties(address, savedAddress, "id", "creationDate");

		return repository.save(savedAddress);
	}

	@Override
	public void delete(UUID id) throws NotFoundException
	{
		Address address = repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND));

		repository.delete(address);
	}
}
