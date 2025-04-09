package br.com.lucasbdourado.library.repository.author;

import br.com.lucasbdourado.library.entity.author.Author;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, UUID>
{
}
