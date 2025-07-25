package com.fhce.egovf.service.impl;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.fhce.egovf.service.jwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class jwtServiceImpl implements jwtService{
	
private String secretKey="413F4428472B4B6250655368566D5870337336763979244226452948484D6351"; 
	
	public String generateToken(UserDetails userDetails) {
		int min = 100;
		int duracion=min*60*1000;
		
		return Jwts.builder().setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+duracion))
				.signWith(getSiginKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	public String generateRefreshToken(Map<String,Object> extraClaims, UserDetails userDetails) {
		
		return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+604800000))
				.signWith(getSiginKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	private  Key getSiginKey() {
		byte [] key = Decoders.BASE64.decode(this.secretKey);
		return (Keys.hmacShaKeyFor(key));
	}
	private <T> T extractClaim(String token, Function<Claims,T> claimsResolvers) {
		final Claims claims = extractAllClaims(token);
		return claimsResolvers.apply(claims);
	}
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSiginKey()).build().parseClaimsJws(token).getBody();
	}
	public String extractUserName(String token) {
		return (extractClaim(token,Claims::getSubject));
	}
	public boolean isTokenValid(String token,UserDetails userDetails) {
		final String username = extractUserName(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	private boolean isTokenExpired(String token) {
		return extractClaim(token,Claims::getExpiration).before(new Date());
	}

}
