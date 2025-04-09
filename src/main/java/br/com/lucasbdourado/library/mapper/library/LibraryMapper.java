package br.com.lucasbdourado.library.mapper.library;

import br.com.lucasbdourado.library.dto.address.AddressResponse;
import br.com.lucasbdourado.library.dto.library.LibraryResponse;
import br.com.lucasbdourado.library.entity.library.Library;
import br.com.lucasbdourado.library.mapper.address.AddressMapper;

public class LibraryMapper
{

	public static LibraryResponse toResponse(Library library)
	{
		AddressResponse addressResponse = AddressMapper.toResponse(library.getAddress());

		return new LibraryResponse(library.getId(), library.getName(), addressResponse,
			library.getPhone());
	}
}
