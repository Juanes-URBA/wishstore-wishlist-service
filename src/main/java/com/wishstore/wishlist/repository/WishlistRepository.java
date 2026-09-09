package com.wishstore.wishlist.repository;

import com.wishstore.wishlist.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repositorio JPA para el acceso a datos de la entidad Wishlist.
 * Provee las operaciones CRUD basicas mas las consultas especificas
 * requeridas por las reglas de negocio del microservicio.
 */
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    /**
     * Obtiene todos los registros de wishlist asociados a un usuario.
     *
     * @param userId identificador del usuario
     * @return lista de registros de wishlist del usuario
     */
    List<Wishlist> findByUserId(Long userId);

    /**
     * Verifica si ya existe un registro de wishlist para un usuario
     * y un producto especificos, utilizado para evitar duplicados.
     *
     * @param userId    identificador del usuario
     * @param productId identificador del producto
     * @return true si ya existe el registro, false en caso contrario
     */
    boolean existsByUserIdAndProductId(Long userId, Long productId);

}