package br.com.lucasbdourado.library.dto.neighborhood;


import com.fasterxml.jackson.annotation.JsonProperty;

public record NeighborhoodJsonDTO(
	@JsonProperty("Id") Long id,
	@JsonProperty("Codigo") Long code,
	@JsonProperty("Nome") String name,
	@JsonProperty("Uf") String stateAcronym)
{
}
