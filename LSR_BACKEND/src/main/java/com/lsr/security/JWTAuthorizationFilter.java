package com.lsr.security;

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

public class JWTAuthorizationFilter extends OncePerRequestFilter {

	private String jwtHeader;

	private String jwtSecret;

	private String jwtPrefix;

	public JWTAuthorizationFilter(String jwtHeader, String jwtSecret, String jwtPrefix) {
		this.jwtHeader = jwtHeader;
		this.jwtSecret = jwtSecret;
		this.jwtPrefix = jwtPrefix;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		try {
			if (checkJWTToken(request)) {
				//String errMsg = validateParamHeader(request);
				//if(errMsg == null) {
					Claims claims = validateToken(request);
					if (claims.get("authorities") != null) {
						setUpSpringAuthentication(claims);
					} else {
						SecurityContextHolder.clearContext();
					}
				/*}else {
					SecurityContextHolder.clearContext();
					HashMap<String, Object> map = new HashMap<>();
				    map.put("status","0");
				    map.put("message", errMsg);
				    
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					//response.setCharacterEncoding("UTF-8");
			        //response.setContentType(MediaType.APPLICATION_JSON.getType());
			        response.getWriter().write(new JSONObject(map).toString());
					return;
				}*/
			} else {
				SecurityContextHolder.clearContext();
			}
			chain.doFilter(request, response);
		} catch (ExpiredJwtException e) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			(response).sendError(HttpServletResponse.SC_FORBIDDEN, "Expired JWT");

		} catch (UnsupportedJwtException e) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			(response).sendError(HttpServletResponse.SC_FORBIDDEN, "Unsupported JWT");

		} catch (MalformedJwtException e) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			(response).sendError(HttpServletResponse.SC_FORBIDDEN, "Malformed JWT");

		} 
	}
	
	/*private String validateParamHeader(HttpServletRequest request) {
		String jwtToken = request.getHeader(jwtHeader).replace(jwtPrefix, "");
		String json =  (String)CommonMethod.getUserInfo(jwtToken,jwtSecret,jwtPrefix);
		UserInfo userInfo = CommonMethod.jsonToObjectInfo(json);
		
		if(!StringUtils.isEmpty(request.getHeader("client-source-type"))) {
			String clientSourceType = request.getHeader("client-source-type");
			if(!clientSourceType.equals(userInfo.getClientSourceType())) {
				return "Param header client-source-type tidak sesuai ";
			}
		}else {
			return "Param header client-source-type harus diisi ";
		}
		
		if(!StringUtils.isEmpty(request.getHeader("user-type"))) {
			String userType = request.getHeader("user-type");
			if(!userType.equals(userInfo.getUserType())) {
				return "Param header user-type tidak sesuai ";
			}
		}else {
			return "Param header user-type harus diisi ";
		}
		
		return null;
	}*/

	private Claims validateToken(HttpServletRequest request) {
		String jwtToken = request.getHeader(jwtHeader).replace(jwtPrefix, "");
		return Jwts.parser().setSigningKey(jwtSecret.getBytes()).parseClaimsJws(jwtToken).getBody();
	}

	/**
	 * Authentication method in Spring flow
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

	private boolean checkJWTToken(HttpServletRequest request) {
		String authenticationHeader = request.getHeader(jwtHeader);
		if (authenticationHeader == null || !authenticationHeader.startsWith(jwtPrefix)) {
			return false;
		}else {
			return true;
		}
	}

}