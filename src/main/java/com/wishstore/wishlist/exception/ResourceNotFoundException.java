package com.wishstore.wishlist.exception;

/**
 * Excepcion lanzada cuando un recurso solicitado no existe,
 * por ejemplo: un producto que no fue encontrado en el Catalog Service,
 * o un registro de wishlist que no existe por id.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}