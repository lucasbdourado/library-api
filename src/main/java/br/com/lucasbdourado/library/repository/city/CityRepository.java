package br.com.lucasbdourado.library.repository.city;

import br.com.lucasbdourado.library.entity.city.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long>
{
}
