package com.ninja.dietician;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
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
        		.apiInfo(getApiInformation())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ninja.dietician"))
                .paths(PathSelectors.any())
                .build();


    }
	
	private ApiInfo getApiInformation(){
		
		Contact contact = new Contact("Team - Ninja Turtles ","https://www.numpyninja.com/","");
		
		return new ApiInfoBuilder()
	              .title("API Documentation For Dietician Hackathon 2022")
	              .description("This is the Dietician API created using Spring Boot and DynamoDB")
	              .contact(contact)
	              .version("1.0.0")
	              .build();
	}
}
