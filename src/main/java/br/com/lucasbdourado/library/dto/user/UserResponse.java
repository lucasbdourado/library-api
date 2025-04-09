package br.com.lucasbdourado.library.dto.user;

import br.com.lucasbdourado.library.entity.role.UserRole;
import java.util.UUID;

public record UserResponse(UUID id, String email, String username, boolean active,
                           UserRole role)
{
}
