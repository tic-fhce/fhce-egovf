package com.fhce.egovf.service;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


public interface jwtService {
	String extractUserName(String token);
	String generateToken(UserDetails userDetails);
	boolean isTokenValid(String token,UserDetails userDetails);
	String generateRefreshToken(Map<String,Object> extraClaims, UserDetails userDetails);

}
