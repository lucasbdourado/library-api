package br.com.lucasbdourado.library.repository.state;

import br.com.lucasbdourado.library.entity.state.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Long>
{
}
