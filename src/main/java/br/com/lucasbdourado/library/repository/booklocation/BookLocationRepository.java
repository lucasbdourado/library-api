package br.com.lucasbdourado.library.repository.booklocation;

import br.com.lucasbdourado.library.entity.booklocation.BookLocation;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookLocationRepository extends JpaRepository<BookLocation, UUID>
{
}
