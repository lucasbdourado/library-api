package br.com.lucasbdourado.library.repository.group;

import br.com.lucasbdourado.library.entity.group.Group;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, UUID>
{
}
