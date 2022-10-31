package com.lsr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.lsr.security.JWTAuthorizationFilter;
import com.lsr.service.user.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
	    // securedEnabled = true,
	    // jsr250Enabled = true,
	    prePostEnabled = true)
class WebSecurityConfig extends WebSecurityConfigurerAdapter  {
	@Autowired
	UserDetailsServiceImpl userDetailsService;
	  

	@Value("${jwt.header}")
    private String jwtHeader;
	
	@Value("${jwt.secret}")
    private String jwtSecret;
	
	@Value("${jwt.prefix}")
    private String jwtPrefix;
    
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.cors().and().
		//mengatur mana url yang boleh di access
		//anyMathcher itu yang bisa di akses bebas dengan syarat
		http.csrf().disable().cors().and()
		    // access dengan username dan password
			.addFilterAfter(new JWTAuthorizationFilter(jwtHeader,jwtSecret,jwtPrefix), 
			 UsernamePasswordAuthenticationFilter.class).authorizeRequests()
			.antMatchers(HttpMethod.POST,"/api/lsr_enhance/main/v1/login").permitAll()
			//tidak perlu menggunakan user dan pass
			.antMatchers(HttpMethod.POST,"/api/lsr_enhance/main/v1/register").permitAll()
			//.antMatchers(HttpMethod.POST,"/api/lsr_enhance/main/v1/registration/**").permitAll()
			.antMatchers(HttpMethod.GET,"/api/lsr_enhance/main/v1/confirm").permitAll()
			.antMatchers(HttpMethod.POST,"/api/lsr_enhance/main/v1/refreshToken").permitAll()
			.anyRequest().authenticated();
//			.and()
//            .formLogin();
		
//		http.csrf().disable().cors().disable().authorizeRequests().antMatchers(HttpMethod.POST,"/api/lsr_enhance/main/v1/login").permitAll()
//						.and()
//						.addFilterAfter(new JWTAuthorizationFilter(jwtHeader,jwtSecret,jwtPrefix), 
//								// access dengan username dan password
//								 UsernamePasswordAuthenticationFilter.class).authorizeRequests()
//								.antMatchers(HttpMethod.POST,"/api/lsr_enhance/main/v1/login").permitAll()	
//								.antMatchers(HttpMethod.POST,"/api/lsr_enhance/main/v1/refreshToken").permitAll()
//								.anyRequest().authenticated();
	    
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2/**","/v2/api-docs", "/configuration/**", "/swagger-resources/**",  "/swagger-ui.html", "/webjars/**", "/api-docs/**");
//		 web.ignoring().antMatchers("/api/auth/**","/v2/api-docs", 
//		            "/configuration/ui", 
//		            "/swagger-resources", 
//		            "/configuration/security",
//		            "/swagger-ui.html", 
//		            "/webjars/**",
//		            "/favicon.ico",
//		            "/**/*.png",
//		            "/**/*.gif",
//		            "/**/*.svg",
//		            "/**/*.jpg",
//		            "/**/*.html",
//		            "/**/*.css",
//		            "/**/*.js");
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}
	
	
	  @Override 
	 public void configure(AuthenticationManagerBuilder
	  authenticationManagerBuilder) throws Exception {
	  authenticationManagerBuilder.userDetailsService(userDetailsService).
	  passwordEncoder(passwordEncoder());
	 }
	 
	
	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception { auth.authenticationProvider(authenticationProvider()); }
	 */

	
	@Bean
	  public PasswordEncoder passwordEncoder() {
		
	    return new BCryptPasswordEncoder();
	  }
	
		/*
		 * @Bean public DaoAuthenticationProvider authenticationProvider() {
		 * DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		 * authProvider.setUserDetailsService(userDetailsService);
		 * authProvider.setPasswordEncoder(passwordEncoder()); return authProvider; }
		 */
	
//	  @SuppressWarnings("deprecation")
//	  @Bean
//	  public static NoOpPasswordEncoder passwordEncoder() {
//	  return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//	  }
	//@Bean
    //public WebMvcConfigurer configurer() {
        //return new WebMvcConfigurer() {
           // @Override
            //public void addCorsMappings(CorsRegistry registry) {
            	//registry.addMapping("/**")
				//.allowedOrigins("*")
				//.allowedMethods("GET", "PUT", "POST", "PATCH", "DELETE", "OPTIONS");
            //}
        //};
    //}
	
	
}