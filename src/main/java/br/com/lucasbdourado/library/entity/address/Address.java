package br.com.lucasbdourado.library.entity.address;

import br.com.lucasbdourado.library.entity.city.City;
import br.com.lucasbdourado.library.entity.neighborhood.Neighborhood;
import br.com.lucasbdourado.library.entity.state.State;
import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.UUID;

@Entity
public class Address implements Serializable
{
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private UUID id;

	private String country;

	@ManyToOne
	private State state;

	@ManyToOne
	private City city;

	@ManyToOne
	private Neighborhood neighborhood;

	private String zip;

	private String street;

	private String number;

	private GregorianCalendar creationDate;

	private GregorianCalendar updateDate;

	@PrePersist
	public void updateCreationDateAndUpdateDate()
	{
		this.setCreationDate(new GregorianCalendar());
		this.setUpdateDate(new GregorianCalendar());
	}

	@PreUpdate
	public void updateUpdateDate()
	{
		this.setUpdateDate(new GregorianCalendar());
	}

	public UUID getId()
	{
		return id;
	}

	public void setId(UUID id)
	{
		this.id = id;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	public State getState()
	{
		return state;
	}

	public void setState(State state)
	{
		this.state = state;
	}

	public City getCity()
	{
		return city;
	}

	public void setCity(City city)
	{
		this.city = city;
	}

	public Neighborhood getNeighborhood()
	{
		return neighborhood;
	}

	public void setNeighborhood(Neighborhood neighborhood)
	{
		this.neighborhood = neighborhood;
	}

	public String getZip()
	{
		return zip;
	}

	public void setZip(String zip)
	{
		this.zip = zip;
	}

	public String getStreet()
	{
		return street;
	}

	public void setStreet(String street)
	{
		this.street = street;
	}

	public String getNumber()
	{
		return number;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	public GregorianCalendar getCreationDate()
	{
		return creationDate;
	}

	public void setCreationDate(GregorianCalendar creationDate)
	{
		this.creationDate = creationDate;
	}

	public GregorianCalendar getUpdateDate()
	{
		return updateDate;
	}

	public void setUpdateDate(GregorianCalendar updateDate)
	{
		this.updateDate = updateDate;
	}
}
