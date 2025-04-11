package br.com.lucasbdourado.library.service.operation.implement;

import br.com.lucasbdourado.library.entity.operation.Operation;
import br.com.lucasbdourado.library.exception.NotFoundException;
import br.com.lucasbdourado.library.repository.operation.OperationRepository;
import br.com.lucasbdourado.library.service.generic.implement.GenericBaseService;
import br.com.lucasbdourado.library.service.operation.IOperationService;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class OperationService extends GenericBaseService<Operation, UUID> implements IOperationService
{
	private final OperationRepository repository;

	public OperationService(OperationRepository repository)
	{
		this.repository = repository;
	}

	@Override
	protected JpaRepository<Operation, UUID> getRepository()
	{
		return repository;
	}

	@Override
	public List<Operation> findAll()
	{
		return super.findAll();
	}

	@Override
	public Operation findById(UUID id)
	{
		return super.findById(id);
	}

	@Override
	public Operation persist(Operation operationPayload)
	{
		return super.persist(operationPayload);
	}

	@Override
	public Operation update(UUID id, Operation operation)
	{
		return super.update(id, operation);
	}

	@Override
	public void delete(UUID id) throws NotFoundException
	{
		super.delete(id);
	}
}
