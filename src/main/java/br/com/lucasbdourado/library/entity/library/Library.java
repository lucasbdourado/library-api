package br.com.lucasbdourado.library.entity.library;

import br.com.lucasbdourado.library.entity.address.Address;
import br.com.lucasbdourado.library.entity.loan.Loan;
import br.com.lucasbdourado.library.entity.reservation.Reservation;
import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Library
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String name;

	@OneToOne
	private Address address;

	private String phone;

	@OneToMany(mappedBy = "library", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Reservation> reservationList;

	@OneToMany(mappedBy = "library", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Loan> loanList;

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

	public List<Reservation> getReservationList()
	{
		return reservationList;
	}

	public void setReservationList(List<Reservation> reservationList)
	{
		this.reservationList = reservationList;
	}

	public List<Loan> getLoanList()
	{
		return loanList;
	}

	public void setLoanList(List<Loan> loanList)
	{
		this.loanList = loanList;
	}
}
