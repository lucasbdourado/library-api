package br.com.lucasbdourado.library.dto.booklanguage;

import java.util.UUID;

public record BookLanguageResponse(UUID id, String language, String description)
{
}
