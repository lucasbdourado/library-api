package br.com.lucasbdourado.library.mapper.publisher;

import br.com.lucasbdourado.library.dto.address.AddressResponse;
import br.com.lucasbdourado.library.dto.publisher.PublisherResponse;
import br.com.lucasbdourado.library.entity.publisher.Publisher;
import br.com.lucasbdourado.library.mapper.address.AddressMapper;

public class PublisherMapper
{

	public static PublisherResponse toResponse(Publisher publisher)
	{
		AddressResponse addressResponse = AddressMapper.toResponse(publisher.getAddress());

		return new PublisherResponse(publisher.getId(), publisher.getName(), addressResponse,
			publisher.getPhone(), publisher.getEmail());
	}
}
