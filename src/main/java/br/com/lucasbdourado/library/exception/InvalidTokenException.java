package br.com.lucasbdourado.library.exception;

public class InvalidTokenException extends RuntimeException
{
	public InvalidTokenException(String message)
	{
		super(message);
	}
}
