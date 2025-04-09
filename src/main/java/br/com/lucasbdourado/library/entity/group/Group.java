package br.com.lucasbdourado.library.entity.group;

import br.com.lucasbdourado.library.entity.operation.Operation;
import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "`group`")
public class Group
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String description;

	@ManyToMany
	@JoinTable(
		name = "group_operation",
		joinColumns = @JoinColumn(name = "group_id"),
		inverseJoinColumns = @JoinColumn(name = "operation_id")
	)
	private List<Operation> operationList;

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

	public List<Operation> getOperationList()
	{
		return operationList;
	}

	public void setOperationList(List<Operation> operationList)
	{
		this.operationList = operationList;
	}
}
