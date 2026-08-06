package com.wishstore.wishlist.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * DTO que representa la respuesta del Catalog Service al consultar
 * un producto por su identificador. Utilizado por CatalogClient
 * para determinar si un producto existe antes de agregarlo a la wishlist.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CatalogProductResponse {

    private Long id;
    private String name;
    private BigDecimal price;
    private Boolean available;

}