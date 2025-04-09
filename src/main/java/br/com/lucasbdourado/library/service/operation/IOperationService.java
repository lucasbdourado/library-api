package br.com.lucasbdourado.library.service.operation;

import br.com.lucasbdourado.library.entity.operation.Operation;
import java.util.List;
import java.util.UUID;

public interface IOperationService
{
	List<Operation> findAll();

	Operation findById(UUID id);

	Operation persist(Operation operationPayload);

	Operation update(UUID id, Operation operationPayload);

	void delete(UUID id);
}
