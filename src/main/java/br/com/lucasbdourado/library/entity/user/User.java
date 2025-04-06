package br.com.lucasbdourado.library.entity.user;

import br.com.lucasbdourado.library.entity.customer.Customer;
import jakarta.persistence.*;
import java.util.GregorianCalendar;
import java.util.UUID;

@Entity
public class User
{
	@Id
	@GeneratedValue
	private UUID id;

	private String email;

	private String password;

	private boolean active;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Customer customer;

	private GregorianCalendar creationDate;

	private GregorianCalendar lastLoginDate;

	private GregorianCalendar updateDate;

	public UUID getId()
	{
		return id;
	}

	public void setId(UUID id)
	{
		this.id = id;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public boolean isActive()
	{
		return active;
	}

	public void setActive(boolean active)
	{
		this.active = active;
	}

	public Customer getCustomer()
	{
		return customer;
	}

	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}

	public GregorianCalendar getCreationDate()
	{
		return creationDate;
	}

	public void setCreationDate(GregorianCalendar creationDate)
	{
		this.creationDate = creationDate;
	}

	public GregorianCalendar getLastLoginDate()
	{
		return lastLoginDate;
	}

	public void setLastLoginDate(GregorianCalendar lastLoginDate)
	{
		this.lastLoginDate = lastLoginDate;
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
