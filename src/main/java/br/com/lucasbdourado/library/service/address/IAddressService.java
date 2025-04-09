package br.com.lucasbdourado.library.service.address;

import br.com.lucasbdourado.library.entity.address.Address;
import java.util.List;
import java.util.UUID;

public interface IAddressService
{
	List<Address> findAll();

	Address findById(UUID id);

	Address persist(Address addressPayload);

	Address update(UUID id, Address addressPayload);

	void delete(UUID id);
}
