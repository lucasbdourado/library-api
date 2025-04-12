package br.com.lucasbdourado.library.entity.operation;

import br.com.lucasbdourado.library.entity.group.Group;
import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Operation
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String description;

	private OperationStatus status = OperationStatus.STORED;

	@ManyToMany(mappedBy = "operationList")
	private List<Group> groups;

	public UUID getId()
	{
		return id;
	}

	public void setId(UUID id)
	{
		this.id = id;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public OperationStatus getStatus()
	{
		return status;
	}

	public void setStatus(OperationStatus status)
	{
		this.status = status;
	}

	public List<Group> getGroups()
	{
		return groups;
	}

	public void setGroups(List<Group> groups)
	{
		this.groups = groups;
	}
}
