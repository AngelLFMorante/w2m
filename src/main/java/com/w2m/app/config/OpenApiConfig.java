package com.w2m.app.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up OpenAPI (Swagger) documentation for the Spacecraft API.
 * This class customizes the OpenAPI configuration used for generating API documentation.
 */
@Configuration
public class OpenApiConfig {

    /**
     * Configures the custom OpenAPI settings.
     *
     * @return OpenAPI instance with custom API documentation metadata.
     */
    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("Spaceship Maintenance API")
                        .version("1.0")
                        .description("REST API to manage spacecraft from series and films")
                );
    }
}
