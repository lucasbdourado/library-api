package br.com.lucasbdourado.library.mapper.rating;

import br.com.lucasbdourado.library.dto.rating.RatingResponse;
import br.com.lucasbdourado.library.entity.rating.Rating;

public class RatingMapper
{

	public static RatingResponse toResponse(Rating rating)
	{
		return new RatingResponse(rating.getId(), rating.getRating(), rating.getDescription());
	}
}
