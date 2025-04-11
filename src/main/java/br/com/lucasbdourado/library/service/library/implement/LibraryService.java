package br.com.lucasbdourado.library.service.library.implement;

import br.com.lucasbdourado.library.entity.library.Library;
import br.com.lucasbdourado.library.exception.NotFoundException;
import br.com.lucasbdourado.library.repository.library.LibraryRepository;
import br.com.lucasbdourado.library.service.generic.implement.GenericBaseService;
import br.com.lucasbdourado.library.service.library.ILibraryService;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class LibraryService extends GenericBaseService<Library, UUID> implements ILibraryService
{
	private final LibraryRepository repository;

	public LibraryService(LibraryRepository repository)
	{
		this.repository = repository;
	}

	@Override
	protected JpaRepository<Library, UUID> getRepository()
	{
		return repository;
	}

	@Override
	public List<Library> findAll()
	{
		return super.findAll();
	}

	@Override
	public Library findById(UUID id)
	{
		return super.findById(id);
	}

	@Override
	public Library persist(Library libraryPayload)
	{
		return super.persist(libraryPayload);
	}

	@Override
	public Library update(UUID id, Library library)
	{
		return super.update(id, library);
	}

	@Override
	public void delete(UUID id) throws NotFoundException
	{
		super.delete(id);
	}
}
