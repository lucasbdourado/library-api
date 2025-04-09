package br.com.lucasbdourado.library.repository.neighborhood;

import br.com.lucasbdourado.library.entity.neighborhood.Neighborhood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NeighborhoodRepository extends JpaRepository<Neighborhood, Long>
{
}
