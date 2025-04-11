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
import br.com.lucasbdourado.library.service.generic.implement.GenericBaseService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService extends GenericBaseService<Address, UUID> implements IAddressService
{
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
	protected JpaRepository<Address, UUID> getRepository() {
		return repository;
	}

	@Override
	public List<Address> findAll()
	{
		return super.findAll();
	}

	@Override
	public Address findById(UUID id)
	{
		return super.findById(id);
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
		return super.update(id, address);
	}

	@Override
	public void delete(UUID id) throws NotFoundException
	{
		super.delete(id);
	}
}
