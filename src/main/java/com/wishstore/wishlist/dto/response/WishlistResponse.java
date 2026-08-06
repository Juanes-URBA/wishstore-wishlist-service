package com.wishstore.wishlist.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO de salida que representa un registro de la wishlist
 * devuelto al cliente en las respuestas HTTP.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WishlistResponse {

    private Long id;
    private Long userId;
    private Long productId;
    private LocalDateTime createdAt;

}