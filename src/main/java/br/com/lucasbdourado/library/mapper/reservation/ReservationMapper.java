package br.com.lucasbdourado.library.mapper.reservation;

import br.com.lucasbdourado.library.dto.reservation.ReservationResponse;
import br.com.lucasbdourado.library.entity.reservation.Reservation;

public class ReservationMapper
{

	public static ReservationResponse toResponse(Reservation reservation)
	{
		return new ReservationResponse(reservation.getId(), reservation.getReservationDate(),
			reservation.getStatus(), reservation.isReserved());
	}
}
