package com.ninja.dietician;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class DieticianHackathonApplication {

	public static void main(String[] args) {
		SpringApplication.run(DieticianHackathonApplication.class, args);
	}

	@Bean
    public Docket dieticianApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ninja.dietician"))
                .paths(PathSelectors.any())
                .build();


    }
}
