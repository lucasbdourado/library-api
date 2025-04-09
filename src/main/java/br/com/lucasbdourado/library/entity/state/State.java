package br.com.lucasbdourado.library.entity.state;

import br.com.lucasbdourado.library.entity.city.City;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

	private String stateAcronym;

	@OneToMany(mappedBy = "state", cascade = CascadeType.ALL)
	@JsonIgnore
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

	public String getStateAcronym()
	{
		return stateAcronym;
	}

	public void setStateAcronym(String stateAcronym)
	{
		this.stateAcronym = stateAcronym;
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
