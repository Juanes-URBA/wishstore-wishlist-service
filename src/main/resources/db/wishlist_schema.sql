-- ============================================================
-- Script de creacion de base de datos y tabla
-- Microservicio: wishstore-wishlist-service
-- ============================================================

CREATE DATABASE IF NOT EXISTS wishstore_wishlist_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE wishstore_wishlist_db;

CREATE TABLE IF NOT EXISTS wishlist (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id     BIGINT NOT NULL,
    product_id  BIGINT NOT NULL,
    created_at  DATETIME NOT NULL,

    CONSTRAINT uq_wishlist_user_product UNIQUE (user_id, product_id)
);

CREATE INDEX idx_wishlist_user_id ON wishlist (user_id);