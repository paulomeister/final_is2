package com.serviciudad.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * Configuración global de CORS para permitir el acceso desde el frontend Angular
 * Solo permite el origen específico del frontend (http://localhost:4200)
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configuración de CORS para permitir solicitudes desde el frontend Angular
     * @param registry - Registry para configurar CORS
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("Content-Type", "Authorization", "X-Requested-With", "Accept", "Origin")
                .allowCredentials(true)
                .maxAge(3600);
    }

    /**
     * Configuración adicional de CORS como fuente de configuración
     * @return CorsConfigurationSource configurado
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // Solo permitir el origen específico del frontend
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        
        // Métodos HTTP permitidos
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        
        // Headers permitidos
        configuration.setAllowedHeaders(Arrays.asList(
            "Content-Type", 
            "Authorization", 
            "X-Requested-With", 
            "Accept", 
            "Origin"
        ));
        
        // Permitir credenciales
        configuration.setAllowCredentials(true);
        
        // Tiempo de vida del preflight request
        configuration.setMaxAge(3600L);
        
        // Aplicar configuración a todas las rutas de la API
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", configuration);
        
        return source;
    }
}
