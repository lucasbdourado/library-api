package br.com.lucasbdourado.library.service.group.implement;

import br.com.lucasbdourado.library.entity.group.Group;
import br.com.lucasbdourado.library.entity.operation.Operation;
import br.com.lucasbdourado.library.exception.NotFoundException;
import br.com.lucasbdourado.library.repository.group.GroupRepository;
import br.com.lucasbdourado.library.repository.operation.OperationRepository;
import br.com.lucasbdourado.library.service.group.IGroupService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class GroupService implements IGroupService
{
	private static final String NOT_FOUND = "Not Found";

	private final GroupRepository repository;

	private final OperationRepository operationRepository;

	public GroupService(GroupRepository repository, OperationRepository operationRepository)
	{
		this.repository = repository;
		this.operationRepository = operationRepository;
	}

	@Override
	public List<Group> findAll()
	{
		return repository.findAll();
	}

	@Override
	public Group findById(UUID id)
	{
		return repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND));
	}

	@Override
	public Group persist(Group groupPayload)
	{
		Group group = new Group();

		BeanUtils.copyProperties(groupPayload, group);

		operationRepository.saveAll(group.getOperationList());

		return repository.save(group);
	}

	@Override
	public Group update(UUID id, Group group)
	{
		Group savedGroup = repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND));

		BeanUtils.copyProperties(group, savedGroup, "id", "creationDate");

		group.getOperationList().forEach(operation ->
		{
			Operation savedOperation = operationRepository.findById(operation.getId()).orElseThrow(() -> new NotFoundException(NOT_FOUND));

			BeanUtils.copyProperties(operation, savedOperation, "id", "creationDate");

			operationRepository.save(savedOperation);
		});

		return repository.save(savedGroup);
	}

	@Override
	public void delete(UUID id) throws NotFoundException
	{
		Group group = repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND));

		repository.delete(group);
	}
}
