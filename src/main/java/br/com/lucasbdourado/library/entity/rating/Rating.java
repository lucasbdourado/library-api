package br.com.lucasbdourado.library.entity.rating;

import br.com.lucasbdourado.library.entity.book.Book;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Rating
{
	@Id
	@GeneratedValue
	private UUID id;

	private short rating;

	private String description;

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

	public short getRating()
	{
		return rating;
	}

	public void setRating(short rating)
	{
		this.rating = rating;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
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
