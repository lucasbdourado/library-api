package br.com.lucasbdourado.library.service.rating.implement;

import br.com.lucasbdourado.library.entity.rating.Rating;
import br.com.lucasbdourado.library.exception.NotFoundException;
import br.com.lucasbdourado.library.repository.rating.RatingRepository;
import br.com.lucasbdourado.library.service.rating.IRatingService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class RatingService implements IRatingService
{
	private static final String NOT_FOUND = "Not Found";

	private final RatingRepository repository;

	public RatingService(RatingRepository repository)
	{
		this.repository = repository;
	}

	@Override
	public List<Rating> findAll()
	{
		return repository.findAll();
	}

	@Override
	public Rating findById(UUID id)
	{
		return repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND));
	}

	@Override
	public Rating persist(Rating ratingPayload)
	{
		Rating rating = new Rating();

		BeanUtils.copyProperties(ratingPayload, rating);

		return repository.save(rating);
	}

	@Override
	public Rating update(UUID id, Rating rating)
	{
		Rating savedRating = repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND));

		BeanUtils.copyProperties(rating, savedRating, "id", "creationDate");

		return repository.save(savedRating);
	}

	@Override
	public void delete(UUID id) throws NotFoundException
	{
		Rating rating = repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND));

		repository.delete(rating);
	}
}
