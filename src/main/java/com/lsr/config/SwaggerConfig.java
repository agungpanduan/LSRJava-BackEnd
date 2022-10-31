package com.lsr.config;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {


    private ApiInfo apiInfo() {
        Contact contact = new Contact(
                "",
                "",
                "");
        return new ApiInfo(
                "",
                "",
                "",
                "",
                contact,
                "",
                "",
                Collections.emptyList());
    }
	
	
    @Bean
    public Docket api()
    {
    	
    	/*List<Parameter> params = new ArrayList<>();
    	ParameterBuilder paramBuilder = new ParameterBuilder();
    	paramBuilder.name("Authorization").modelRef(new ModelRef("string"))
    	.parameterType("header")
    	.required(false)
    	.build();
    	params.add(paramBuilder.build());
    	
    	ParameterBuilder paramBuilder2 = new ParameterBuilder();
    	paramBuilder2.name("client-source-type").modelRef(new ModelRef("string"))
    	.parameterType("header")
    	.required(false)
    	.build();
    	params.add(paramBuilder2.build());
    	
    	ParameterBuilder paramBuilder3 = new ParameterBuilder();
    	paramBuilder3.name("user-type").modelRef(new ModelRef("string"))
    	.parameterType("header")
    	.required(false)
    	.build();
    	params.add(paramBuilder3.build());*/
    	
    	
    	return new Docket(DocumentationType.SWAGGER_2)
    			//.globalOperationParameters(params)
    			.select()
    			.apis(RequestHandlerSelectors.any())
    			.paths(PathSelectors.any())
    			.paths(Predicates.not(PathSelectors.regex("/error/*")))
                .paths(Predicates.not(PathSelectors.regex("/actuator")))
    			.build()
    			.apiInfo(apiInfo())				
    			.consumes(new HashSet<String>(Arrays.asList("application/json")))
    			.produces(new HashSet<String>(Arrays.asList("application/json")));
    }
}
