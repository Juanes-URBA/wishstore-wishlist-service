package com.wishstore.wishlist.client;

import com.wishstore.wishlist.dto.response.CatalogProductResponse;


public interface CatalogClient {

    /**
     * Consulta un producto en el Catalog Service por su identificador.
     *
     * @param productId identificador del producto a consultar
     * @return los datos del producto si existe, o null si el
     *         Catalog Service indica que el producto no existe
     */
    CatalogProductResponse getProductById(Long productId);

}