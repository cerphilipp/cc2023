package cc2023.teamrandom.ccservice;

import io.micrometer.core.aop.CountedAspect;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.logging.Logger;

// Die folgende Anweisung zeigt, dass diese Klasse zur Spring Boot Anwendung gehört und somit automatische Konfigurationen und Komponentenscans durchführt.
@SpringBootApplication
public class Main {

    // Bean-Methode, um einen benutzerdefinierten Logger für das Anwendungsprotokoll zu erstellen.
    @Bean
    Logger getLogger() {
        return Logger.getLogger("cc-service");
    }

    // Bean-Methode, die eine Instanz von RestTemplate erstellt und injiziert.
    // RestTemplate wird für HTTP-Anfragen an externe Ressourcen verwendet.
    @Bean
    public RestTemplate restTemplate(List<HttpMessageConverter<?>> messageConverters) {
        return new RestTemplate(messageConverters);
    }

    // Bean-Methode, die eine Instanz von ByteArrayHttpMessageConverter erstellt und injiziert.
    // Diese Converter-Klasse wird verwendet, um zwischen Byte-Arrays und HTTP-Nachrichten zu konvertieren.
    @Bean
    public ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
        return new ByteArrayHttpMessageConverter();
    }

    // Bean-Methode, um eine benutzerdefinierte OpenAPI-Konfiguration für die API-Dokumentation zu erstellen.
    @Bean
    public OpenAPI customOpenApi(@Value("${springdoc.version}") String appVersion) {
        return new OpenAPI().components(new Components())
                .info(new Info()
                        .title("cc2023-service")
                        .version(appVersion)
                        .license(new License()
                                .name("MIT")
                                .url("http://springdoc.org")));
    }

    // Hauptmethode, die beim Start der Anwendung aufgerufen wird.
    public static void main(String[] args) {
        // SpringApplication.run startet die Spring Boot-Anwendung mit der angegebenen Konfigurationsklasse und Argumenten.
        SpringApplication.run(cc2023.teamrandom.ccservice.Main.class, args);
    }
}
