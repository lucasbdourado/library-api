package br.com.lucasbdourado.library.entity.book;

import br.com.lucasbdourado.library.entity.author.Author;
import br.com.lucasbdourado.library.entity.bookexample.BookExample;
import br.com.lucasbdourado.library.entity.bookgender.BookGender;
import br.com.lucasbdourado.library.entity.booklanguage.BookLanguage;
import br.com.lucasbdourado.library.entity.publisher.Publisher;
import br.com.lucasbdourado.library.entity.rating.Rating;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

@Entity
public class Book
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String name;

	private String code;

	private String isbn;

	private GregorianCalendar publishDate;

	private byte edition;

	@ManyToOne
	private BookGender gender;

	@ManyToOne
	private Publisher publisher;

	private int quantity;

	@ManyToMany(mappedBy = "bookList")
	private List<Author> authorList;

	@OneToMany(mappedBy = "rating", cascade = CascadeType.ALL)
	private List<Rating> ratingList = new ArrayList<>();

	@ManyToOne
	private BookLanguage language;

	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
	private List<BookExample> bookExampleList = new ArrayList<>();

	//private PaymentMethod paymentMethod;

	private short pages;

	private String collection;

	@Column(length = 1000)
	private String description;

	private String series;

	//private String imageURL;

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

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getIsbn()
	{
		return isbn;
	}

	public void setIsbn(String isbn)
	{
		this.isbn = isbn;
	}

	public GregorianCalendar getPublishDate()
	{
		return publishDate;
	}

	public void setPublishDate(GregorianCalendar publishDate)
	{
		this.publishDate = publishDate;
	}

	public byte getEdition()
	{
		return edition;
	}

	public void setEdition(byte edition)
	{
		this.edition = edition;
	}

	public BookGender getGender()
	{
		return gender;
	}

	public void setGender(BookGender gender)
	{
		this.gender = gender;
	}

	public Publisher getPublisher()
	{
		return publisher;
	}

	public void setPublisher(Publisher publisher)
	{
		this.publisher = publisher;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	public List<Author> getAuthorList()
	{
		return authorList;
	}

	public void setAuthorList(List<Author> authorList)
	{
		this.authorList = authorList;
	}

	public List<Rating> getRatingList()
	{
		return ratingList;
	}

	public void setRatingList(List<Rating> ratingList)
	{
		this.ratingList = ratingList;
	}

	public BookLanguage getLanguage()
	{
		return language;
	}

	public void setLanguage(BookLanguage language)
	{
		this.language = language;
	}

	public List<BookExample> getBookExampleList()
	{
		return bookExampleList;
	}

	public void setBookExampleList(List<BookExample> bookExampleList)
	{
		this.bookExampleList = bookExampleList;
	}

	public short getPages()
	{
		return pages;
	}

	public void setPages(short pages)
	{
		this.pages = pages;
	}

	public String getCollection()
	{
		return collection;
	}

	public void setCollection(String collection)
	{
		this.collection = collection;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getSeries()
	{
		return series;
	}

	public void setSeries(String series)
	{
		this.series = series;
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
