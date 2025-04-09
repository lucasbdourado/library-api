package br.com.lucasbdourado.library.service.group;

import br.com.lucasbdourado.library.entity.group.Group;
import java.util.List;
import java.util.UUID;

public interface IGroupService
{
	List<Group> findAll();

	Group findById(UUID id);

	Group persist(Group groupPayload);

	Group update(UUID id, Group groupPayload);

	void delete(UUID id);
}
