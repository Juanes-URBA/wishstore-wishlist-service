package com.wishstore.wishlist.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO enviado al History Service para registrar el evento generado
 * cuando un producto es agregado a la wishlist de un usuario.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryRequest {

    private Long wishlistId;
    private Long productId;
    private String action;
    private String description;

}
