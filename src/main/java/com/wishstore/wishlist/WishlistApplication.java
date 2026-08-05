package com.wishstore.wishlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Clase principal de arranque del microservicio wishstore-wishlist-service.
 *
 * Habilita el escaneo de componentes de Spring Boot y activa los clientes
 * declarativos de OpenFeign (CatalogClient, HistoryClient) que seran
 * implementados en la Entrega 4.
 */
@SpringBootApplication
@EnableFeignClients
public class WishlistApplication {

    public static void main(String[] args) {
        SpringApplication.run(WishlistApplication.class, args);
    }

}