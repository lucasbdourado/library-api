package br.com.lucasbdourado.library.mapper.customer;

import br.com.lucasbdourado.library.dto.address.AddressResponse;
import br.com.lucasbdourado.library.dto.customer.CustomerResponse;
import br.com.lucasbdourado.library.dto.group.GroupResponse;
import br.com.lucasbdourado.library.dto.library.LibraryResponse;
import br.com.lucasbdourado.library.dto.loan.LoanResponse;
import br.com.lucasbdourado.library.dto.user.UserResponse;
import br.com.lucasbdourado.library.entity.customer.Customer;
import br.com.lucasbdourado.library.mapper.address.AddressMapper;
import br.com.lucasbdourado.library.mapper.group.GroupMapper;
import br.com.lucasbdourado.library.mapper.library.LibraryMapper;
import br.com.lucasbdourado.library.mapper.loan.LoanMapper;
import br.com.lucasbdourado.library.mapper.user.UserMapper;
import java.util.List;

public class CustomerMapper
{
	public static CustomerResponse toResponse(Customer customer)
	{
		AddressResponse addressResponse = AddressMapper.toResponse(customer.getAddress());

		GroupResponse groupResponse = GroupMapper.toResponse(customer.getGroup());

		LibraryResponse libraryResponse = LibraryMapper.toResponse(customer.getLibrary());

		UserResponse userResponse = UserMapper.toResponse(customer.getUser());

		List<LoanResponse> loanResponseList = customer.getLoanList().stream().map(LoanMapper::toResponse).toList();

		return new CustomerResponse(customer.getId(), customer.getName(), customer.getPhone(),
			customer.getGender(), customer.getIndentity(), customer.getIdentityNumber(), addressResponse,
			groupResponse, libraryResponse, userResponse, loanResponseList);
	}
}
