package br.com.lucasbdourado.library.service.publisher.implement;

import br.com.lucasbdourado.library.entity.publisher.Publisher;
import br.com.lucasbdourado.library.exception.NotFoundException;
import br.com.lucasbdourado.library.repository.publisher.PublisherRepository;
import br.com.lucasbdourado.library.service.publisher.IPublisherService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PublisherService implements IPublisherService
{
	private static final String NOT_FOUND = "Not Found";

	private final PublisherRepository repository;

	public PublisherService(PublisherRepository repository)
	{
		this.repository = repository;
	}

	@Override
	public List<Publisher> findAll()
	{
		return repository.findAll();
	}

	@Override
	public Publisher findById(UUID id)
	{
		return repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND));
	}

	@Override
	public Publisher persist(Publisher publisherPayload)
	{
		Publisher publisher = new Publisher();

		BeanUtils.copyProperties(publisherPayload, publisher);

		return repository.save(publisher);
	}

	@Override
	public Publisher update(UUID id, Publisher publisher)
	{
		Publisher savedPublisher = repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND));

		BeanUtils.copyProperties(publisher, savedPublisher, "id", "creationDate");

		return repository.save(savedPublisher);
	}

	@Override
	public void delete(UUID id) throws NotFoundException
	{
		Publisher publisher = repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND));

		repository.delete(publisher);
	}
}
