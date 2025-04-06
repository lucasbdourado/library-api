package br.com.lucasbdourado.library.entity.user;

import br.com.lucasbdourado.library.entity.customer.Customer;
import br.com.lucasbdourado.library.entity.role.UserRole;
import jakarta.persistence.*;
import java.util.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class User implements UserDetails
{
	@Id
	@GeneratedValue
	private UUID id;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false, unique = true)
	private String username;

	private String password;

	private boolean active = true;

	private UserRole role = UserRole.USER_ROLE;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Customer customer;

	private GregorianCalendar creationDate;

	private GregorianCalendar lastLoginDate;

	private GregorianCalendar updateDate;

	public UUID getId()
	{
		return id;
	}

	public void setId(UUID id)
	{
		this.id = id;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public boolean isActive()
	{
		return active;
	}

	public void setActive(boolean active)
	{
		this.active = active;
	}

	public Customer getCustomer()
	{
		return customer;
	}

	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}

	public GregorianCalendar getCreationDate()
	{
		return creationDate;
	}

	public void setCreationDate(GregorianCalendar creationDate)
	{
		this.creationDate = creationDate;
	}

	public GregorianCalendar getLastLoginDate()
	{
		return lastLoginDate;
	}

	public void setLastLoginDate(GregorianCalendar lastLoginDate)
	{
		this.lastLoginDate = lastLoginDate;
	}

	public GregorianCalendar getUpdateDate()
	{
		return updateDate;
	}

	public void setUpdateDate(GregorianCalendar updateDate)
	{
		this.updateDate = updateDate;
	}

	@Override
	public boolean isAccountNonExpired()
	{
		return UserDetails.super.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked()
	{
		return UserDetails.super.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired()
	{
		return UserDetails.super.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled()
	{
		return UserDetails.super.isEnabled();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		if (Objects.requireNonNull(role) == UserRole.ADMIN_ROLE)
		{
			return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
				new SimpleGrantedAuthority("ROLE_USER"));
		}
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}
}
