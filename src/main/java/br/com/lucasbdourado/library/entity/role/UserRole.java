package br.com.lucasbdourado.library.entity.role;

public enum UserRole
{
	USER_ROLE("user"),
	ADMIN_ROLE("admin");

	private String role;

	UserRole(String role){
		this.role = role;
	}

	public String getRole() {
		return role;
	}
}
