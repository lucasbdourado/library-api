package br.com.lucasbdourado.library.dto.reservation;

import br.com.lucasbdourado.library.entity.reservation.ReservationStatus;
import java.util.GregorianCalendar;
import java.util.UUID;

public record ReservationResponse(UUID id, GregorianCalendar reservationDate, ReservationStatus status, boolean reserved)
{
}
