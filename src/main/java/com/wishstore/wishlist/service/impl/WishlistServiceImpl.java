package com.wishstore.wishlist.service.impl;

import com.wishstore.wishlist.client.CatalogClient;
import com.wishstore.wishlist.client.HistoryClient;
import com.wishstore.wishlist.dto.request.HistoryRequest;
import com.wishstore.wishlist.dto.request.WishlistRequest;
import com.wishstore.wishlist.dto.response.CatalogProductResponse;
import com.wishstore.wishlist.dto.response.WishlistResponse;
import com.wishstore.wishlist.entity.Wishlist;
import com.wishstore.wishlist.exception.DuplicateWishlistException;
import com.wishstore.wishlist.exception.ResourceNotFoundException;
import com.wishstore.wishlist.mapper.WishlistMapper;
import com.wishstore.wishlist.repository.WishlistRepository;
import com.wishstore.wishlist.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementacion de la capa de servicio de Wishlist.
 *
 * Concentra unicamente aqui toda la logica de negocio del microservicio,
 * de acuerdo con la arquitectura definida: el controller solo delega,
 * el repositorio solo persiste, y los clientes Feign solo comunican.
 *
 * La inyeccion de dependencias se realiza por constructor mediante
 * @RequiredArgsConstructor de Lombok sobre campos final, sin usar
 * @Autowired en campos.
 */
@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private static final String HISTORY_ACTION_ADDED = "ADDED_TO_WISHLIST";

    private final WishlistRepository wishlistRepository;
    private final WishlistMapper wishlistMapper;
    private final CatalogClient catalogClient;
    private final HistoryClient historyClient;

    @Override
    public List<WishlistResponse> findAll() {
        return wishlistRepository.findAll()
                .stream()
                .map(wishlistMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public WishlistResponse findById(Long id) {
        Wishlist wishlist = wishlistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se encontro un registro de wishlist con id: " + id));
        return wishlistMapper.toResponse(wishlist);
    }

    @Override
    public List<WishlistResponse> findByUser(Long userId) {
        return wishlistRepository.findByUserId(userId)
                .stream()
                .map(wishlistMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public boolean exists(Long userId, Long productId) {
        return wishlistRepository.existsByUserIdAndProductId(userId, productId);
    }

    /**
     * Implementa exactamente el flujo obligatorio de creacion:
     *
     * 1. Recibe el WishlistRequest (ya validado por Bean Validation en el controller).
     * 2. Consulta el Catalog Service para verificar que el producto exista.
     * 3. Si no existe, lanza ResourceNotFoundException.
     * 4. Si existe, verifica que no este duplicado para el usuario.
     * 5. Si esta duplicado, lanza DuplicateWishlistException.
     * 6. Persiste el registro en MySQL.
     * 7. Envia el evento al History Service.
     * 8. Mapea la entidad guardada a WishlistResponse y la retorna.
     */
    @Override
    public WishlistResponse create(WishlistRequest request) {

        CatalogProductResponse product = catalogClient.getProductById(request.getProductId());
        if (product == null) {
            throw new ResourceNotFoundException(
                    "El producto con id " + request.getProductId() + " no existe en el catalogo");
        }

        if (exists(request.getUserId(), request.getProductId())) {
            throw new DuplicateWishlistException(
                    "El usuario " + request.getUserId()
                            + " ya tiene registrado el producto " + request.getProductId()
                            + " en su wishlist");
        }

        Wishlist wishlist = wishlistMapper.toEntity(request);
        Wishlist savedWishlist = wishlistRepository.save(wishlist);

        HistoryRequest historyRequest = HistoryRequest.builder()
                .userId(savedWishlist.getUserId())
                .productId(savedWishlist.getProductId())
                .action(HISTORY_ACTION_ADDED)
                .eventDate(LocalDateTime.now())
                .build();
        historyClient.registerEvent(historyRequest);

        return wishlistMapper.toResponse(savedWishlist);
    }

    @Override
    public void delete(Long id) {
        Wishlist wishlist = wishlistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se encontro un registro de wishlist con id: " + id));
        wishlistRepository.deleteById(wishlist.getId());
    }

}