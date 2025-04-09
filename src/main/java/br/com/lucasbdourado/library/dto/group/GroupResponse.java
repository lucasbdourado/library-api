package br.com.lucasbdourado.library.dto.group;

import br.com.lucasbdourado.library.dto.operation.OperationResponse;
import java.util.List;
import java.util.UUID;

public record GroupResponse(UUID id, String description, List<OperationResponse> operationList)
{
}
