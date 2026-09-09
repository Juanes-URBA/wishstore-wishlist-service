package com.wishstore.wishlist.client;

import com.wishstore.wishlist.config.FeignClientConfig;
import com.wishstore.wishlist.dto.request.HistoryRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "history-service",
        url = "${services.history.url}",
        configuration = FeignClientConfig.class
)
public interface HistoryClient {
    /**
     * Registra un evento de wishlist en el History Service.
     *
     * @param historyRequest datos del evento a registrar
     */
    @PostMapping("/api/history")
    void registerEvent(HistoryRequest historyRequest);

}