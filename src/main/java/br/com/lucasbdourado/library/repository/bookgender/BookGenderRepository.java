package br.com.lucasbdourado.library.repository.bookgender;

import br.com.lucasbdourado.library.entity.bookgender.BookGender;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookGenderRepository extends JpaRepository<BookGender, UUID>
{
}
