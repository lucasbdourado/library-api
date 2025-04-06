package br.com.lucasbdourado.library.service.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTService
{
	private final long expirationMillis = 500000L;

	private final JwtParser parser;

	private final SecretKey key;

	public JWTService(@Value("${jwt.secret}") String secret)
	{
		this.key = Keys.hmacShaKeyFor(secret.getBytes());
		this.parser = Jwts.parser().verifyWith(key).build();
	}

	public String generateToken(String username)
	{
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + expirationMillis);

		return Jwts.builder().subject(username).issuedAt(now).expiration(expiryDate).signWith(key)
			.compact();
	}

	public String extractEmail(String token)
	{
		try
		{
			return extractClaim(token, Claims::getSubject);
		}
		catch (ExpiredJwtException | SecurityException e)
		{
			return "";
		}
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver)
	{
		Claims claims = parser.parseSignedClaims(token).getPayload();

		return claimsResolver.apply(claims);
	}

	public boolean isTokenValid(String token, String username)
	{
		String extractedUsername = extractEmail(token);

		return extractedUsername.equals(username) && !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token)
	{
		return extractClaim(token, Claims::getExpiration).before(new Date());
	}
}
