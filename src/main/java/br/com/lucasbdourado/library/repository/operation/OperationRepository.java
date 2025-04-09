package br.com.lucasbdourado.library.repository.operation;

import br.com.lucasbdourado.library.entity.operation.Operation;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<Operation, UUID>
{
}
