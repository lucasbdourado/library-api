package br.com.lucasbdourado.library.service.publisher;

import br.com.lucasbdourado.library.entity.publisher.Publisher;
import java.util.List;
import java.util.UUID;

public interface IPublisherService
{
	List<Publisher> findAll();

	Publisher findById(UUID id);

	Publisher persist(Publisher publisherPayload);

	Publisher update(UUID id, Publisher publisherPayload);

	void delete(UUID id);
}
