package org.lpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ManyToManyApp {

	public static void main(String[] args) {
		SpringApplication.run(ManyToManyApp.class, args);
	}

	@Bean
	public Docket swaggerAPI() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("org.lpro"))
				.paths(PathSelectors.any()).build().apiInfo(new ApiInfoBuilder().version("1.0")
						.title("API pour les LPRO").description("Documentation de l'API").build());
	}
}
