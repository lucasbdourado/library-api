package br.com.lucasbdourado.library.entity.booklanguage;

import br.com.lucasbdourado.library.entity.book.Book;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class BookLanguage
{
	@Id
	@GeneratedValue
	private UUID id;

	private String language;

	private String description;

	@OneToMany(mappedBy = "language", cascade = CascadeType.ALL)
	private List<Book> bookList = new ArrayList<>();

	public UUID getId()
	{
		return id;
	}

	public void setId(UUID id)
	{
		this.id = id;
	}

	public String getLanguage()
	{
		return language;
	}

	public void setLanguage(String language)
	{
		this.language = language;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public List<Book> getBookList()
	{
		return bookList;
	}

	public void setBookList(List<Book> bookList)
	{
		this.bookList = bookList;
	}
}
