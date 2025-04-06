package br.com.lucasbdourado.library.exception;

public class UnauthorizedException extends RuntimeException
{
	public UnauthorizedException(String message)
	{
		super(message);
	}
}
