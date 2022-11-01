package com.lsr.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class PropertiesConfig {

	@Autowired
    private Environment env;
	
	@Bean(name = "jwtHeader")
	public String jwtHeader() {
		return env.getProperty("jwt.header");
	}
	
	@Bean(name = "jwtSecret")
	public String jwtSecret() {
		return env.getProperty("jwt.secret");
	}
	
	@Bean(name = "jwtRefreshSecret")
	public String jwtRefreshSecret() {
		return env.getProperty("jwt.refresh.secret");
	}
	
	@Bean(name = "jwtPrefix")
	public String jwtPrefix() {
		return env.getProperty("jwt.prefix");
	}	

	@Bean(name = "apiPublicHost")
	public String apiPublicHost() {
		return env.getProperty("api.publicAccess.host");
	}
	
	@Bean(name = "jwtExpired")
	public String jwtExpired() {
		return env.getProperty("jwt.expired");
	}
	
	@Bean(name = "jwtRefreshExpired")
	public String jwtRefreshExpired() {
		return env.getProperty("jwt.refresh.expired");
	}
	
	@Bean(name = "apiParamHeaderAboxKey")
	public String apiParamHeaderAboxKey() {
		return env.getProperty("api.param.header.ABOX-KEY");
	}
	
	
	@Bean(name = "apiParamHeaderSaltKey")
	public String apiParamHeaderSaltKey() {
		return env.getProperty("api.param.header.SALT_KEY");
	}
}