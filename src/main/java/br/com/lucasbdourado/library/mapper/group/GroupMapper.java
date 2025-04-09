package br.com.lucasbdourado.library.mapper.group;

import br.com.lucasbdourado.library.dto.group.GroupResponse;
import br.com.lucasbdourado.library.dto.operation.OperationResponse;
import br.com.lucasbdourado.library.entity.group.Group;
import br.com.lucasbdourado.library.entity.operation.Operation;
import br.com.lucasbdourado.library.mapper.operation.OperationMapper;
import java.util.List;

public class GroupMapper
{

	public static GroupResponse toResponse(Group group)
	{
		List<Operation> operationList = group.getOperationList();

		List<OperationResponse> operationResponseList = operationList.stream()
			.map(OperationMapper::toResponse).toList();

		return new GroupResponse(group.getId(), group.getDescription(), operationResponseList);
	}
}
