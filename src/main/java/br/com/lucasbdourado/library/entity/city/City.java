package br.com.lucasbdourado.library.entity.city;

import br.com.lucasbdourado.library.entity.neighborhood.Neighborhood;
import br.com.lucasbdourado.library.entity.state.State;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class City
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Long code;

	private String name;

	@ManyToOne
	@JsonIgnore
	private State state;

	@OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Neighborhood> neighborhoodList = new ArrayList<>();

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

	public State getState()
	{
		return state;
	}

	public void setState(State state)
	{
		this.state = state;
	}

	public List<Neighborhood> getNeighborhoodList()
	{
		return neighborhoodList;
	}

	public void setNeighborhoodList(List<Neighborhood> neighborhoodList)
	{
		this.neighborhoodList = neighborhoodList;
	}
}
