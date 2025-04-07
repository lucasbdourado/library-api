package br.com.lucasbdourado.library.repository.book;

import br.com.lucasbdourado.library.entity.book.Book;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID>
{
}
