package br.com.lucasbdourado.library.repository.library;

import br.com.lucasbdourado.library.entity.library.Library;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends JpaRepository<Library, UUID>
{
}
