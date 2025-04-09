package br.com.lucasbdourado.library.repository.address;

import br.com.lucasbdourado.library.entity.address.Address;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID>
{
}
