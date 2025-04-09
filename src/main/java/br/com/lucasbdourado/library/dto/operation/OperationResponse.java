package br.com.lucasbdourado.library.dto.operation;

import br.com.lucasbdourado.library.entity.operation.OperationStatus;
import java.util.UUID;

public record OperationResponse(UUID id, String description, OperationStatus status)
{
}
