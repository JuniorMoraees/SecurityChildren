package br.com.jmtech.configuration;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebMvc
public class SwaggerConfig {

    @Bean
    public io.swagger.v3.oas.models.OpenAPI customOpenAPI() {
        return new io.swagger.v3.oas.models.OpenAPI()
                .info(new Info()
                        .title("Security Children API")
                        .version("1.0.0")
                        .description("API para gerenciamento de alunos, responsáveis e QR Codes")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}


//abrir o Swagger : http://localhost:8080/swagger-ui/