package br.com.lucasbdourado.library.rest.loan;

import br.com.lucasbdourado.library.dto.loan.LoanResponse;
import br.com.lucasbdourado.library.entity.loan.Loan;
import br.com.lucasbdourado.library.exception.NotFoundException;
import br.com.lucasbdourado.library.mapper.loan.LoanMapper;
import br.com.lucasbdourado.library.service.loan.ILoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loans")
@Tag(name = "Emprestimos", description = "Operações relacionadas às operações de emprestimos de livros")
public class LoanREST
{
	private static final String NOT_FOUND = "Not Found";

	private final ILoanService service;

	public LoanREST(ILoanService service)
	{
		this.service = service;
	}

	@GetMapping({"/", ""})
	@Operation(summary = "Listar todos os emprestimos", description = "Retorna uma lista com todos os emprestimos.")
	public ResponseEntity<Object> getLoanList()
	{
		try
		{
			List<Loan> loanList = service.findAll();

			List<LoanResponse> loanResponseList = loanList.stream().map(
				LoanMapper::toResponse).toList();

			return ResponseEntity.ok().body(loanResponseList);
		}
		catch (Exception e)
		{

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping("/{id}")
	@Operation(summary = "Buscar uma emprestimo pelo ID", description = "Retorna a emprestimo pelo Id, caso não encontre nenhum, retorna que não foi encontrado.")
	public ResponseEntity<Object> getById(@PathVariable UUID id)
	{
		try
		{
			Loan loan = service.findById(id);

			LoanResponse loanResponse = LoanMapper.toResponse(loan);

			return ResponseEntity.ok().body(loanResponse);
		}
		catch (NotFoundException e)
		{

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND);
		}
		catch (Exception e)
		{

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PostMapping({"/", ""})
	@Operation(summary = "Criar uma emprestimo")
	public ResponseEntity<Object> create(@RequestBody Loan loanPayload)
	{
		try
		{
			Loan loan = service.persist(loanPayload);

			LoanResponse loanResponse = LoanMapper.toResponse(loan);

			return ResponseEntity.ok().body(loanResponse);
		}
		catch (Exception e)
		{

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualizar uma emprestimo")
	public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody Loan loanPayload)
	{
		try
		{
			Loan loan = service.update(id, loanPayload);

			LoanResponse loanResponse = LoanMapper.toResponse(loan);

			return ResponseEntity.ok().body(loanResponse);
		}
		catch (NotFoundException e)
		{

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND);
		}
		catch (Exception e)
		{

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deletar uma emprestimo pelo Id")
	public ResponseEntity<Object> delete(@PathVariable UUID id)
	{
		try
		{
			service.delete(id);

			return ResponseEntity.ok().body("Deletado com sucesso!");
		}
		catch (NotFoundException e)
		{

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND);
		}
		catch (Exception e)
		{

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
