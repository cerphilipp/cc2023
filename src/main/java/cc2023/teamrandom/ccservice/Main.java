package cc2023.teamrandom;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import philippcerweny.restapi.orderservice.interfaces.ICustomerRepository;
import philippcerweny.restapi.orderservice.interfaces.IOrderRepository;

import java.util.logging.Logger;

@SpringBootApplication
public class Main {

    @Bean
    Logger getLogger()
    {
        return Logger.getLogger("Service");
    }

    @Bean
    CommandLineRunner initDatabase(ICustomerRepository customerRepository, IOrderRepository orderRepository)
    {
        return args ->
        {

        };
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

        SpringApplication.run(philippcerweny.restapi.orderservice.Main.class, args);
    }
}

