package com.wishstore.wishlist.controller;

import com.wishstore.wishlist.dto.request.WishlistRequest;
import com.wishstore.wishlist.dto.response.WishlistResponse;
import com.wishstore.wishlist.service.WishlistService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador REST del microservicio wishstore-wishlist-service.
 *
 * Unica responsabilidad: recibir las solicitudes HTTP y delegar
 * inmediatamente en WishlistService. No contiene logica de negocio,
 * de acuerdo con la arquitectura en capas definida.
 */
@RestController
@RequestMapping("/wishlist")
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;

    /**
     * GET /wishlist
     * Retorna todos los registros de wishlist existentes.
     */
    @GetMapping
    public ResponseEntity<List<WishlistResponse>> findAll() {
        List<WishlistResponse> response = wishlistService.findAll();
        return ResponseEntity.ok(response);
    }

    /**
     * GET /wishlist/{id}
     * Retorna un registro de wishlist por su identificador.
     */
    @GetMapping("/{id}")
    public ResponseEntity<WishlistResponse> findById(@PathVariable Long id) {
        WishlistResponse response = wishlistService.findById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * GET /wishlist/user/{userId}
     * Retorna todos los registros de wishlist asociados a un usuario.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WishlistResponse>> findByUser(@PathVariable Long userId) {
        List<WishlistResponse> response = wishlistService.findByUser(userId);
        return ResponseEntity.ok(response);
    }

    /**
     * POST /wishlist
     * Crea un nuevo registro de wishlist siguiendo el flujo de negocio
     * completo definido en WishlistServiceImpl.
     */
    @PostMapping
    public ResponseEntity<WishlistResponse> create(@Valid @RequestBody WishlistRequest request) {
        WishlistResponse response = wishlistService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * DELETE /wishlist/{id}
     * Elimina un registro de wishlist por su identificador.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        wishlistService.delete(id);
        return ResponseEntity.noContent().build();
    }

}