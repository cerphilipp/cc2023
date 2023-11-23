package cc2023.teamrandom.ccservice;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.logging.Logger;

@SpringBootApplication
public class Main {

    @Bean
    Logger getLogger()
    {
        return Logger.getLogger("Service");
    }

    @Bean
    public RestTemplate restTemplate(List<HttpMessageConverter<?>> messageConverters) {
        return new RestTemplate(messageConverters);
    }

    @Bean
    public ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
        return new ByteArrayHttpMessageConverter();
    }

    @Bean
    public OpenAPI customOpenApi(@Value("${springdoc.version}") String appVersion)
    {
        return new OpenAPI().components(new Components())
                .info(new Info()
                        .title("OrderApi")
                        .version(appVersion)
                        .license(new License()
                                .name("MIT")
                                .url("http://springdoc.org")));
    }

    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);
    }
}

