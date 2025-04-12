package br.com.lucasbdourado.library.entity.bookexample;

import br.com.lucasbdourado.library.entity.book.Book;
import jakarta.persistence.*;
import java.util.GregorianCalendar;
import java.util.UUID;

@Entity
public class BookExample
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String title;

	private GregorianCalendar aquisitionDate;

	private String observation;

	private StatusBookExample status = StatusBookExample.DISPONIBLE;

	@ManyToOne
	private Book book;

	public UUID getId()
	{
		return id;
	}

	public void setId(UUID id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public GregorianCalendar getAquisitionDate()
	{
		return aquisitionDate;
	}

	public void setAquisitionDate(GregorianCalendar aquisitionDate)
	{
		this.aquisitionDate = aquisitionDate;
	}

	public String getObservation()
	{
		return observation;
	}

	public void setObservation(String observation)
	{
		this.observation = observation;
	}

	public StatusBookExample getStatus()
	{
		return status;
	}

	public void setStatus(StatusBookExample status)
	{
		this.status = status;
	}

	public Book getBook()
	{
		return book;
	}

	public void setBook(Book book)
	{
		this.book = book;
	}
}
