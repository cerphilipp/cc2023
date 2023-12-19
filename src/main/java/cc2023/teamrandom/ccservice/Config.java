package cc2023.teamrandom.ccservice;

import io.micrometer.core.aop.CountedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

// Diese Klasse wird als Konfigurationsklasse für Spring Framework markiert.
@Configuration
// Aktiviert die automatische Erzeugung von Aspekten für die Anwendung von Metriken (CountedAspect) durch das AspectJ-Framework.
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class Config {

	// Bean-Methode, die eine Instanz von CountedAspect erstellt und injiziert.
	// CountedAspect ist ein Aspekt, der Metriken für annotierte Methoden generiert und in einem MeterRegistry registriert.
	@Bean
	CountedAspect countedAspect(MeterRegistry registry) {
		return new CountedAspect(registry);
	}
}