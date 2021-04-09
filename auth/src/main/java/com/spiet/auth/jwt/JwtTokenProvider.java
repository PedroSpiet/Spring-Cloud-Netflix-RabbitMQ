package com.spiet.auth.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import com.spiet.auth.entity.Permission;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class JwtTokenProvider {
	@Value("${security.jwt.token.secret-key}")
	private String secretKey;
	
	@Value("${security.jwt.token.expire-lenght}")
	private Long expire;

	@Qualifier("userService")
	@Autowired
	private UserDetailsService userDetailService;

	@PostConstruct
	protected void init() {
		this.secretKey = Base64.getEncoder().encodeToString(this.secretKey.getBytes());
	}
	
	public String createToken(String username, List<String> permission) {
		Claims claim = Jwts.claims().setSubject(username);
		claim.put("roles", permission);
		
		Date now = new Date();
		Date validate = new Date(now.getTime() + this.expire);
		
		return Jwts.builder().setClaims(claim).setIssuedAt(now).setExpiration(validate)
				.signWith(SignatureAlgorithm.ES256, this.secretKey).compact();
	}

	public Authentication getAuthentication(String token) {
		UserDetails userDetail = this.userDetailService.loadUserByUsername(getUsername(token));
		return new UsernamePasswordAuthenticationToken(userDetail, "", userDetail.getAuthorities());
	}

	private String getUsername(String token) {
		return Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token).getBody().getSubject();
	}

	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		else {
			return null;
		}
	}

	public boolean validateToken(String token) {
		try {
			Jws<Claims> claim = Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token);

			if (claim.getBody().getExpiration().before(new Date())) {
				return false;
			}

			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}
}
