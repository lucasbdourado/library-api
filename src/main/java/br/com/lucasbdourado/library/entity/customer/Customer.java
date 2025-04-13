package br.com.lucasbdourado.library.entity.customer;

import br.com.lucasbdourado.library.entity.address.Address;
import br.com.lucasbdourado.library.entity.group.Group;
import br.com.lucasbdourado.library.entity.library.Library;
import br.com.lucasbdourado.library.entity.loan.Loan;
import br.com.lucasbdourado.library.entity.reservation.Reservation;
import br.com.lucasbdourado.library.entity.user.User;
import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
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

	private String phone;

	private Gender gender;

	private Indentity indentity;

	@Column(unique = true)
	private String identityNumber;

	@OneToOne(cascade = CascadeType.ALL)
	private Address address;

	@OneToOne
	private Group group;

	@OneToOne
	private Library library;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Reservation> reservationList = new ArrayList<>();

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Loan> loanList;

	private GregorianCalendar creationDate;

	private GregorianCalendar updateDate;

	@PrePersist
	protected void onCreate() {
		this.creationDate = new GregorianCalendar();
		this.updateDate = new GregorianCalendar();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updateDate = new GregorianCalendar();
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

	public Group getGroup()
	{
		return group;
	}

	public void setGroup(Group group)
	{
		this.group = group;
	}

	public Library getLibrary()
	{
		return library;
	}

	public void setLibrary(Library library)
	{
		this.library = library;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
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
