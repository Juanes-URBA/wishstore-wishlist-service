package com.wishstore.wishlist.config;

import feign.Logger;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

/**
 * Configuracion comun aplicada a los clientes Feign del microservicio
 * (CatalogClient y HistoryClient).
 *
 * No se anota con @Configuration para evitar que Spring la registre
 * globalmente: al ser referenciada desde el atributo "configuration"
 * de @FeignClient, Spring la trata como una configuracion especifica
 * de esos clientes, siguiendo la practica recomendada por Spring Cloud
 * OpenFeign para no contaminar el contexto general de la aplicacion.
 */
public class FeignClientConfig {
    /**
     * Define el nivel de detalle de los logs de las llamadas Feign.
     * FULL registra headers, body y metadata de cada request/response,
     * util durante el desarrollo y la depuracion de la integracion.
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
    /**
     * Registra el decodificador de errores personalizado, encargado
     * de traducir las respuestas HTTP de error de Catalog Service y
     * History Service en excepciones de dominio del microservicio.
     */
    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignErrorDecoder();
    }

}