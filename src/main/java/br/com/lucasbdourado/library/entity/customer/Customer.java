package br.com.lucasbdourado.library.entity.customer;

import br.com.lucasbdourado.library.entity.address.Address;
import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.UUID;

@Entity
public class Customer implements Serializable
{
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String name;

	@Column(unique = true)
	private String email;

	private String phone;

	private Gender gender;

	private Indentity indentity;

	private String identityNumber;

	@OneToOne(cascade = CascadeType.ALL)
	private Address address;

	private GregorianCalendar creationDate;

	private GregorianCalendar updateDate;

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

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public Gender getGender()
	{
		return gender;
	}

	public void setGender(Gender gender)
	{
		this.gender = gender;
	}

	public Indentity getIndentity()
	{
		return indentity;
	}

	public void setIndentity(Indentity indentity)
	{
		this.indentity = indentity;
	}

	public String getIdentityNumber()
	{
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber)
	{
		this.identityNumber = identityNumber;
	}

	public Address getAddress()
	{
		return address;
	}

	public void setAddress(Address address)
	{
		this.address = address;
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
