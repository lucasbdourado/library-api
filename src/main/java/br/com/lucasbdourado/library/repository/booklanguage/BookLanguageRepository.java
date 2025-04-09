package br.com.lucasbdourado.library.repository.booklanguage;

import br.com.lucasbdourado.library.entity.booklanguage.BookLanguage;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookLanguageRepository extends JpaRepository<BookLanguage, UUID>
{
}
