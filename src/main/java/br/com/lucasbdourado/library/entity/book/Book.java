package br.com.lucasbdourado.library.entity.book;

import br.com.lucasbdourado.library.entity.publisher.Publisher;
import jakarta.persistence.*;
import java.util.GregorianCalendar;
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
	private Publisher publisher;

	private int quantity;

	//private PaymentMethod paymentMethod;

	private short pages;

	private String collection;

	@Column(length = 1000)
	private String description;

	private String series;

	//private String imageURL;

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
