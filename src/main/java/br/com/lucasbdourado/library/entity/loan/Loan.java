package br.com.lucasbdourado.library.entity.loan;

import br.com.lucasbdourado.library.entity.customer.Customer;
import br.com.lucasbdourado.library.entity.library.Library;
import br.com.lucasbdourado.library.entity.loanitem.LoanItem;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

@Entity
public class Loan
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "library_id")
	private Library library;

	@OneToMany(mappedBy = "loan", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<LoanItem> loanItemList = new ArrayList<>();

	private GregorianCalendar loanDate;

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

	public Library getLibrary()
	{
		return library;
	}

	public void setLibrary(Library library)
	{
		this.library = library;
	}

	public List<LoanItem> getLoanItemList()
	{
		return loanItemList;
	}

	public void setLoanItemList(List<LoanItem> loanItemList)
	{
		this.loanItemList = loanItemList;
	}

	public GregorianCalendar getLoanDate()
	{
		return loanDate;
	}

	public void setLoanDate(GregorianCalendar loanDate)
	{
		this.loanDate = loanDate;
	}
}
