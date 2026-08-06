package com.wishstore.wishlist.service;

import com.wishstore.wishlist.dto.request.WishlistRequest;
import com.wishstore.wishlist.dto.response.WishlistResponse;

import java.util.List;

public interface WishlistService {

    /**
     * Obtiene todos los registros de wishlist existentes.
     *
     * @return lista completa de registros mapeados a DTO de respuesta
     */
    List<WishlistResponse> findAll();

    /**
     * Busca un registro de wishlist por su identificador.
     *
     * @param id identificador del registro
     * @return DTO de respuesta correspondiente
     */
    WishlistResponse findById(Long id);

    /**
     * Obtiene todos los registros de wishlist asociados a un usuario.
     *
     * @param userId identificador del usuario
     * @return lista de registros del usuario
     */
    List<WishlistResponse> findByUser(Long userId);

    /**
     * Verifica si un usuario ya tiene registrado un producto en su wishlist.
     *
     * @param userId    identificador del usuario
     * @param productId identificador del producto
     * @return true si ya existe el registro, false en caso contrario
     */
    boolean exists(Long userId, Long productId);

    /**
     * Crea un nuevo registro de wishlist siguiendo el flujo obligatorio:
     * validar producto en Catalog Service, verificar duplicado, persistir,
     * notificar al History Service y retornar el DTO de respuesta.
     *
     * @param request DTO de entrada con userId y productId
     * @return DTO de respuesta del registro creado
     */
    WishlistResponse create(WishlistRequest request);

    /**
     * Elimina un registro de wishlist por su identificador.
     *
     * @param id identificador del registro a eliminar
     */
    void delete(Long id);

}