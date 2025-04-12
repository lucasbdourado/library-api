package br.com.lucasbdourado.library.dto.bookexample;

import java.util.GregorianCalendar;
import java.util.UUID;

public record BookExampleResponse(UUID id, String title, GregorianCalendar aquisitionDate,
                                  String observation)
{
}
