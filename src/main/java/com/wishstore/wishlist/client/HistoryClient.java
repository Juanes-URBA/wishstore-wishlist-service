package com.wishstore.wishlist.client;

import com.wishstore.wishlist.dto.request.HistoryRequest;

public interface HistoryClient {

    /**
     * Registra un evento de wishlist en el History Service.
     *
     * @param historyRequest datos del evento a registrar
     */
    void registerEvent(HistoryRequest historyRequest);

}