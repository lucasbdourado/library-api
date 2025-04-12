package br.com.lucasbdourado.library.entity.neighborhood;

import br.com.lucasbdourado.library.entity.city.City;
import jakarta.persistence.*;

@Entity
public class Neighborhood
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long code;

	private String name;

	private String stateAcronym;

	@ManyToOne
	private City city;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getCode()
	{
		return code;
	}

	public void setCode(Long code)
	{
		this.code = code;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getStateAcronym()
	{
		return stateAcronym;
	}

	public void setStateAcronym(String stateAcronym)
	{
		this.stateAcronym = stateAcronym;
	}

	public City getCity()
	{
		return city;
	}

	public void setCity(City city)
	{
		this.city = city;
	}
}
