package com.agreggio.challenge.birras.santander.common.configuration;

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
import io.jsonwebtoken.Jwts;

import com.agreggio.challenge.birras.santander.common.constant.JwtConstant;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

			if (existJWTToken(request, response)) {
				Claims claims = validateToken(request);
				if (claims.get("authorities") != null) {
					setUpSpringAuthentication(claims);
				} else {
					SecurityContextHolder.clearContext();
				}
			}
			chain.doFilter(request, response);

	}	

	private Claims validateToken(HttpServletRequest request) {
		String jwtToken = request.getHeader(JwtConstant.HEADER).replace(JwtConstant.PREFIX, "");
		return Jwts.parser().setSigningKey(JwtConstant.SECRET.getBytes()).parseClaimsJws(jwtToken).getBody();
	}

	/**
	 * Metodo para autenticarnos dentro del flujo de Spring
	 * 
	 * @param claims
	 */
	private void setUpSpringAuthentication(Claims claims) {
		@SuppressWarnings("unchecked")
		List<String> authorities = (List<String>) claims.get("authorities");

		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
				authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
		SecurityContextHolder.getContext().setAuthentication(auth);

	}

	private boolean existJWTToken(HttpServletRequest request, HttpServletResponse res) {
		String authenticationHeader = request.getHeader(JwtConstant.HEADER);
		return authenticationHeader != null && authenticationHeader.startsWith(JwtConstant.PREFIX);
	}
}
