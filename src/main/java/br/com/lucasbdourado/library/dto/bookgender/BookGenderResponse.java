package br.com.lucasbdourado.library.dto.bookgender;

import java.util.UUID;

public record BookGenderResponse(UUID id, String name, String description)
{
}