package com.wishstore.wishlist.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO de entrada utilizado para crear un nuevo registro en la wishlist.
 * Contiene las validaciones de Bean Validation exigidas antes de
 * ejecutar cualquier logica de negocio.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WishlistRequest {

    @NotNull(message = "El campo userId es obligatorio")
    @Positive(message = "El campo userId debe ser un valor positivo")
    private Long userId;

    @NotNull(message = "El campo productId es obligatorio")
    @Positive(message = "El campo productId debe ser un valor positivo")
    private Long productId;

}