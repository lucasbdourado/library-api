package br.com.lucasbdourado.library.entity.publisher;

import br.com.lucasbdourado.library.entity.address.Address;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Publisher
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String name;

	@OneToOne
	private Address address;

	private String phone;

	private String email;

	public UUID getId()
	{
		return id;
	}

	public void setId(UUID id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Address getAddress()
	{
		return address;
	}

	public void setAddress(Address address)
	{
		this.address = address;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}
}
