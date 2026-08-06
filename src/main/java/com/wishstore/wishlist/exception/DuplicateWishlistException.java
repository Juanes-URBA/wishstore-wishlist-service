package com.wishstore.wishlist.exception;

/**
 * Excepcion lanzada cuando se intenta agregar a la wishlist un producto
 * que el usuario ya tiene registrado previamente.
 */
public class DuplicateWishlistException extends RuntimeException {

    public DuplicateWishlistException(String message) {
        super(message);
    }

    public DuplicateWishlistException(String message, Throwable cause) {
        super(message, cause);
    }

}