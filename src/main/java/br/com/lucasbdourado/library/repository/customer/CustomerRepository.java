package br.com.lucasbdourado.library.repository.customer;

import br.com.lucasbdourado.library.entity.customer.Customer;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID>
{
}
