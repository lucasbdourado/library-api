package br.com.lucasbdourado.library.entity.state;

import br.com.lucasbdourado.library.entity.city.City;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class State
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String code;

	private String name;

	private String acronym;

	@OneToMany(mappedBy = "state", cascade = CascadeType.ALL)
	private List<City> cities = new ArrayList<>();

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
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

	public String getAcronym()
	{
		return acronym;
	}

	public void setAcronym(String acronym)
	{
		this.acronym = acronym;
	}

	public List<City> getCities()
	{
		return cities;
	}

	public void setCities(List<City> cities)
	{
		this.cities = cities;
	}
}
