package br.com.lucasbdourado.library.service.library;

import br.com.lucasbdourado.library.entity.library.Library;
import java.util.List;
import java.util.UUID;

public interface ILibraryService
{
	List<Library> findAll();

	Library findById(UUID id);

	Library persist(Library libraryPayload);

	Library update(UUID id, Library libraryPayload);

	void delete(UUID id);
}
