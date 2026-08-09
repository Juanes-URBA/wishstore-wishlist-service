package com.wishstore.wishlist.client;

import com.wishstore.wishlist.config.FeignClientConfig;
import com.wishstore.wishlist.dto.response.CatalogProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "catalog-service",
        url = "${services.catalog.url}",
        configuration = FeignClientConfig.class
)
public interface CatalogClient {
    /**
     * Consulta un producto en el Catalog Service por su identificador.
     *
     * @param productId identificador del producto a consultar
     * @return los datos del producto si existe, o null si el
     *         Catalog Service indica que el producto no existe
     */
    @GetMapping("/api/catalog/products/{productId}")
    CatalogProductResponse getProductById(Long productId);

}