package br.com.lucasbdourado.library.repository.bookexample;

import br.com.lucasbdourado.library.entity.bookexample.BookExample;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookExampleRepository extends JpaRepository<BookExample, UUID>
{
}
