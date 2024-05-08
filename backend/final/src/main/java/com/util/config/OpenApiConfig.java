package com.util.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                    .info(new Info()
                    .title("Aplicação para motoristas e passageiros")
                    .version("v1")
                    .description("Aplicação para fazer o gerenciamento de viagens entre passageiros e motoristas.")
                    .termsOfService("https://localhost:8080")
                    .license(
                            new License()
                                    .name("Apache 2.0")
                                    .url("https://paulinnleao.github.io/POOBD")
                    ));
    }
}
