package br.com.lucasbdourado.library.entity.bookgender;

import br.com.lucasbdourado.library.entity.book.Book;
import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class BookGender
{

	@Id
	@GeneratedValue
	private UUID id;

	private String name;

	private String description;

	@OneToMany(mappedBy = "gender", cascade = CascadeType.ALL)
	private List<Book> book;

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

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public List<Book> getBook()
	{
		return book;
	}

	public void setBook(List<Book> book)
	{
		this.book = book;
	}
}
