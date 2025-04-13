package br.com.lucasbdourado.library.entity.loanitem;

import br.com.lucasbdourado.library.entity.bookexample.BookExample;
import br.com.lucasbdourado.library.entity.customer.Customer;
import br.com.lucasbdourado.library.entity.loan.Loan;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class LoanItem
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "loan_id")
	private Loan loan;

	@ManyToOne
	@JoinColumn(name = "book_example_id")
	private BookExample bookExample;

	@Enumerated(EnumType.STRING)
	private LoanStatus status;

	// opcional, se quiser manter o histórico de quem pegou:
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	public UUID getId()
	{
		return id;
	}

	public void setId(UUID id)
	{
		this.id = id;
	}

	public Loan getLoan()
	{
		return loan;
	}

	public void setLoan(Loan loan)
	{
		this.loan = loan;
	}

	public BookExample getBookExample()
	{
		return bookExample;
	}

	public void setBookExample(BookExample bookExample)
	{
		this.bookExample = bookExample;
	}

	public LoanStatus getStatus()
	{
		return status;
	}

	public void setStatus(LoanStatus status)
	{
		this.status = status;
	}

	public Customer getCustomer()
	{
		return customer;
	}

	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}
}
