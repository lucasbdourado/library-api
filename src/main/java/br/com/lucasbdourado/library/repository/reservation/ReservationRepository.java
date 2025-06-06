package br.com.lucasbdourado.library.repository.reservation;

import br.com.lucasbdourado.library.entity.reservation.Reservation;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID>
{
}
