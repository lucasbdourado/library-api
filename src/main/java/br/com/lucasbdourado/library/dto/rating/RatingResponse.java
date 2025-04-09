package br.com.lucasbdourado.library.dto.rating;

import java.util.UUID;

public record RatingResponse(UUID id, short rating, String description)
{
}
