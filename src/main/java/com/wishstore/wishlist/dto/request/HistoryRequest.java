package com.wishstore.wishlist.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO enviado al History Service para registrar el evento generado
 * cuando un producto es agregado a la wishlist de un usuario.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryRequest {

    private Long userId;
    private Long productId;
    private String action;
    private LocalDateTime eventDate;

}