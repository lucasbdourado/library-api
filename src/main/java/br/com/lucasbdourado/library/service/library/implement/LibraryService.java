package br.com.lucasbdourado.library.service.library.implement;

import br.com.lucasbdourado.library.entity.library.Library;
import br.com.lucasbdourado.library.exception.NotFoundException;
import br.com.lucasbdourado.library.repository.address.AddressRepository;
import br.com.lucasbdourado.library.repository.library.LibraryRepository;
import br.com.lucasbdourado.library.service.library.ILibraryService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class LibraryService implements ILibraryService
{

	private static final String NOT_FOUND = "Not Found";

	private final LibraryRepository repository;

	public LibraryService(LibraryRepository repository)
	{
		this.repository = repository;
	}

	@Override
	public List<Library> findAll()
	{
		return repository.findAll();
	}

	@Override
	public Library findById(UUID id)
	{
		return repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND));
	}

	@Override
	public Library persist(Library libraryPayload)
	{
		Library library = new Library();

		BeanUtils.copyProperties(libraryPayload, library);

		return repository.save(library);
	}

	@Override
	public Library update(UUID id, Library library)
	{
		Library savedLibrary = repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND));

		BeanUtils.copyProperties(library, savedLibrary, "id", "address");

		return repository.save(savedLibrary);
	}

	@Override
	public void delete(UUID id) throws NotFoundException
	{
		Library library = repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND));

		repository.delete(library);
	}
}
