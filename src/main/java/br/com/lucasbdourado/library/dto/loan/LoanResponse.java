package br.com.lucasbdourado.library.dto.loan;

import java.util.GregorianCalendar;
import java.util.UUID;

public record LoanResponse(UUID id, GregorianCalendar loanDate)
{
}
