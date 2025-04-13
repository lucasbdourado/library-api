package br.com.lucasbdourado.library.mapper.loan;

import br.com.lucasbdourado.library.dto.loan.LoanResponse;
import br.com.lucasbdourado.library.entity.loan.Loan;

public class LoanMapper
{

	public static LoanResponse toResponse(Loan loan)
	{
		return new LoanResponse(loan.getId(), loan.getLoanDate());
	}
}
