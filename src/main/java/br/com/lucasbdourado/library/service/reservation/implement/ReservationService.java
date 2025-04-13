package br.com.lucasbdourado.library.service.reservation.implement;

import br.com.lucasbdourado.library.entity.reservation.Reservation;
import br.com.lucasbdourado.library.repository.reservation.ReservationRepository;
import br.com.lucasbdourado.library.service.generic.implement.GenericBaseService;
import br.com.lucasbdourado.library.service.reservation.IReservationService;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservationService extends GenericBaseService<Reservation, UUID> implements IReservationService
{
	private final ReservationRepository repository;

	public ReservationService(ReservationRepository repository)
	{
		this.repository = repository;
	}

	@Override
	protected JpaRepository<Reservation, UUID> getRepository()
	{
		return repository;
	}
}
