package br.com.lucasbdourado.library.repository.publisher;

import br.com.lucasbdourado.library.entity.publisher.Publisher;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, UUID>
{
}
