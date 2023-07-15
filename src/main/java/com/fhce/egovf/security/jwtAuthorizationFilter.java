package com.fhce.egovf.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;


public class jwtAuthorizationFilter  extends OncePerRequestFilter{

	private final String HEADER="Authorization";
	private final String PREFIX="Bearer ";
	private final String SECRET="j@mes";
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			if(existeToken(request,response)) {
				Claims claims= validarToken(request);
				if(claims.get("authorities")!= null) {
					setUpSringAuth(claims);
				}
				else {
					SecurityContextHolder.clearContext();
				}
			}
			else {
				SecurityContextHolder.clearContext();
			}
			filterChain.doFilter(request, response);
		}catch(ExpiredJwtException | UnsupportedJwtException | MalformedJwtException ex ) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			((HttpServletResponse)response).sendError(HttpServletResponse.SC_FORBIDDEN,ex.getMessage());
			return;
		}
	}
	private void setUpSringAuth(Claims claims) {
		
		List<String>authorities = (List)claims.get("authorities");
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(),null,
				authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
		SecurityContextHolder.getContext().setAuthentication(auth);
		
	}
	private Claims validarToken(HttpServletRequest request) {
		String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
		return Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(jwtToken).getBody();
	}
	public boolean existeToken(HttpServletRequest request, HttpServletResponse response) {
		String authHeader = request.getHeader(HEADER);
		if(authHeader==null || !authHeader.startsWith(PREFIX)) {
			return false;
		}
		else 
			return true;
	}

}
