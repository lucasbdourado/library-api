package br.com.lucasbdourado.library.service.rating.implement;

import br.com.lucasbdourado.library.entity.rating.Rating;
import br.com.lucasbdourado.library.exception.NotFoundException;
import br.com.lucasbdourado.library.repository.rating.RatingRepository;
import br.com.lucasbdourado.library.service.generic.implement.GenericBaseService;
import br.com.lucasbdourado.library.service.rating.IRatingService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class RatingService extends GenericBaseService<Rating, UUID> implements IRatingService
{
	private static final String NOT_FOUND = "Not Found";

	private final RatingRepository repository;

	public RatingService(RatingRepository repository)
	{
		this.repository = repository;
	}

	@Override
	protected JpaRepository<Rating, UUID> getRepository()
	{
		return repository;
	}

	@Override
	public List<Rating> findAll()
	{
		return super.findAll();
	}

	@Override
	public Rating findById(UUID id)
	{
		return super.findById(id);
	}

	@Override
	public Rating persist(Rating ratingPayload)
	{
		return super.persist(ratingPayload);
	}

	@Override
	public Rating update(UUID id, Rating rating)
	{
		return super.update(id, rating);
	}

	@Override
	public void delete(UUID id) throws NotFoundException
	{
		super.delete(id);
	}
}
