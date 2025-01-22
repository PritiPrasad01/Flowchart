package com.flowchart.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI openAPI() {
		return new  OpenAPI().info(new Info()
				.title("Flowchart")
				.version("1.0")
				.description("This is a CRUD Flowchart application")
				.contact(new Contact()
						.name("Flow")
						.email("example@gmail.com"))
				.license(new License().name("flow"))
				);
	}

}
