package br.com.lucasbdourado.library.entity.author;

import br.com.lucasbdourado.library.entity.book.Book;
import jakarta.persistence.*;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

@Entity
public class Author
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String name;

	@ManyToMany
	@JoinTable(
		name = "book_author",
		joinColumns = @JoinColumn(name = "author_id"),
		inverseJoinColumns = @JoinColumn(name = "book_id")
	)
	private List<Book> bookList;

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

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public List<Book> getBookList()
	{
		return bookList;
	}

	public void setBookList(List<Book> bookList)
	{
		this.bookList = bookList;
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
