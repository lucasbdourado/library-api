package br.com.lucasbdourado.library.service.operation.implement;

import br.com.lucasbdourado.library.entity.operation.Operation;
import br.com.lucasbdourado.library.exception.NotFoundException;
import br.com.lucasbdourado.library.repository.operation.OperationRepository;
import br.com.lucasbdourado.library.service.operation.IOperationService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class OperationService implements IOperationService
{

	private static final String NOT_FOUND = "Not Found";

	private final OperationRepository repository;

	public OperationService(OperationRepository repository)
	{
		this.repository = repository;
	}

	@Override
	public List<Operation> findAll()
	{
		return repository.findAll();
	}

	@Override
	public Operation findById(UUID id)
	{
		return repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND));
	}

	@Override
	public Operation persist(Operation operationPayload)
	{
		Operation operation = new Operation();

		BeanUtils.copyProperties(operationPayload, operation);

		return repository.save(operation);
	}

	@Override
	public Operation update(UUID id, Operation operation)
	{
		Operation savedOperation = repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND));

		BeanUtils.copyProperties(operation, savedOperation, "id", "creationDate");

		return repository.save(savedOperation);
	}

	@Override
	public void delete(UUID id) throws NotFoundException
	{
		Operation operation = repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND));

		repository.delete(operation);
	}
}
