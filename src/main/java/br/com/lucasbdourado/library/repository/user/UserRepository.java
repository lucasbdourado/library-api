package br.com.lucasbdourado.library.repository.user;

import br.com.lucasbdourado.library.entity.user.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>
{
	Optional<User> findByEmail(String email);

	Boolean existsByEmail(String email);
}
