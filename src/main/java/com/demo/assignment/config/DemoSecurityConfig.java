package com.demo.assignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.csrf().
    	disable()
        	.authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS, "/**")
        	.permitAll()
        	.anyRequest()
        	.authenticated()
        	.and()
        	.httpBasic();
	}

	//http://localhost:8080/swagger-ui.html
	@Bean
	public Docket LMSProgramBatchApi() {
		return  new Docket(DocumentationType.SWAGGER_2)
				.select()
				//.apis(RequestHandlerSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("com.demo.assignment"))
                .paths(PathSelectors.any())
                //give request mapping path here -check in controller , now for regex input i have given eg: /program.*
				//.paths(regex("/program.*"))
	            .build()
	            //in our case we dont have path mapping so commented
	            //.pathMapping("/api")
	           
	            ;
			}

	//@Override
	/*public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}*/
}
