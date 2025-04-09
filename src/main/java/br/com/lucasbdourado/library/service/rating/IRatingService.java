package br.com.lucasbdourado.library.service.rating;

import br.com.lucasbdourado.library.entity.rating.Rating;
import java.util.List;
import java.util.UUID;

public interface IRatingService
{
	List<Rating> findAll();

	Rating findById(UUID id);

	Rating persist(Rating ratingPayload);

	Rating update(UUID id, Rating ratingPayload);

	void delete(UUID id);
}
