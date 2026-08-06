package com.wishstore.wishlist.mapper;

import com.wishstore.wishlist.dto.request.WishlistRequest;
import com.wishstore.wishlist.dto.response.WishlistResponse;
import com.wishstore.wishlist.entity.Wishlist;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class WishlistMapper {

    public Wishlist toEntity(WishlistRequest request) {
        if (request == null) {
            return null;
        }
        return Wishlist.builder()
                .userId(request.getUserId())
                .productId(request.getProductId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    /**
     * Convierte una entidad Wishlist en su DTO de respuesta.
     *
     * @param wishlist entidad persistida
     * @return DTO de respuesta con los datos del registro
     */
    public WishlistResponse toResponse(Wishlist wishlist) {
        if (wishlist == null) {
            return null;
        }
        return WishlistResponse.builder()
                .id(wishlist.getId())
                .userId(wishlist.getUserId())
                .productId(wishlist.getProductId())
                .createdAt(wishlist.getCreatedAt())
                .build();
    }

}