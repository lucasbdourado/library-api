package br.com.lucasbdourado.library.entity.reservation;

import br.com.lucasbdourado.library.entity.bookexample.BookExample;
import br.com.lucasbdourado.library.entity.customer.Customer;
import br.com.lucasbdourado.library.entity.library.Library;
import jakarta.persistence.*;
import java.util.GregorianCalendar;
import java.util.UUID;

@Entity
public class Reservation
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@ManyToOne
	private Customer customer;

	@ManyToOne
	private BookExample bookExample;

	@ManyToOne
	private Library library;

	private GregorianCalendar reservationDate;

	private ReservationStatus status = ReservationStatus.RESERVED;

	private boolean reserved;

	public UUID getId()
	{
		return id;
	}

	public void setId(UUID id)
	{
		this.id = id;
	}

	public Customer getCustomer()
	{
		return customer;
	}

	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}

	public BookExample getBookExample()
	{
		return bookExample;
	}

	public void setBookExample(BookExample bookExample)
	{
		this.bookExample = bookExample;
	}

	public Library getLibrary()
	{
		return library;
	}

	public void setLibrary(Library library)
	{
		this.library = library;
	}

	public GregorianCalendar getReservationDate()
	{
		return reservationDate;
	}

	public void setReservationDate(GregorianCalendar reservationDate)
	{
		this.reservationDate = reservationDate;
	}

	public ReservationStatus getStatus()
	{
		return status;
	}

	public void setStatus(ReservationStatus status)
	{
		this.status = status;
	}

	public boolean isReserved()
	{
		return reserved;
	}

	public void setReserved(boolean reserved)
	{
		this.reserved = reserved;
	}
}
