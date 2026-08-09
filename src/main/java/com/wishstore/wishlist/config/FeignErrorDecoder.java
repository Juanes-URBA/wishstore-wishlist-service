package com.wishstore.wishlist.config;

import com.wishstore.wishlist.exception.ResourceNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;

/**
 * Decodificador de errores para los clientes Feign del microservicio.
 *
 * Traduce las respuestas HTTP de error devueltas por Catalog Service
 * y History Service en excepciones de dominio propias, permitiendo
 * que el flujo de negocio en WishlistServiceImpl y el futuro
 * GlobalExceptionHandler (Entrega 5) las manejen de forma consistente.
 */
public class FeignErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {

        // 404 proveniente del Catalog Service: el producto consultado no existe.
        if (response.status() == 404 && methodKey.contains("CatalogClient")) {
            return new ResourceNotFoundException(
                    "El producto consultado no existe en el Catalog Service");
        }

        // Errores 5xx de cualquiera de los dos servicios externos:
        // se reportan como fallas de comunicacion entre microservicios.
        if (response.status() >= 500) {
            return new IllegalStateException(
                    "Error de comunicacion con el servicio externo invocado en "
                            + methodKey + ". Codigo HTTP: " + response.status());
        }

        // Para cualquier otro caso no contemplado explicitamente,
        // se delega en el comportamiento por defecto de Feign.
        return defaultErrorDecoder.decode(methodKey, response);
    }

}