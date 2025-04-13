package br.com.lucasbdourado.library.service.loan.implement;

import br.com.lucasbdourado.library.entity.loan.Loan;
import br.com.lucasbdourado.library.repository.loan.LoanRepository;
import br.com.lucasbdourado.library.service.generic.implement.GenericBaseService;
import br.com.lucasbdourado.library.service.loan.ILoanService;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class LoanService extends GenericBaseService<Loan, UUID> implements ILoanService
{
	private final LoanRepository repository;

	public LoanService(LoanRepository repository)
	{
		this.repository = repository;
	}

	@Override
	protected JpaRepository<Loan, UUID> getRepository()
	{
		return repository;
	}
}
