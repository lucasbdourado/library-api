package br.com.lucasbdourado.library.mapper.operation;

import br.com.lucasbdourado.library.dto.operation.OperationResponse;
import br.com.lucasbdourado.library.entity.operation.Operation;

public class OperationMapper
{

	public static OperationResponse toResponse(Operation operation)
	{
		return new OperationResponse(operation.getId(), operation.getDescription(),
			operation.getStatus());
	}
}
