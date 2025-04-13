package br.com.lucasbdourado.library.repository.loan;

import br.com.lucasbdourado.library.entity.loan.Loan;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, UUID>
{
}
