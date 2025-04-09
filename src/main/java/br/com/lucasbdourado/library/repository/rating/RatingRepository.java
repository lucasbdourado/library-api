package br.com.lucasbdourado.library.repository.rating;

import br.com.lucasbdourado.library.entity.rating.Rating;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, UUID>
{
}
