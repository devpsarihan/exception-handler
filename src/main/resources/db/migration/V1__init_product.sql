CREATE SCHEMA IF NOT EXISTS PRODUCT;

CREATE TABLE PRODUCT.PRODUCT
(
    ID            SERIAL PRIMARY KEY,
    SKU           UUID           NOT NULL UNIQUE,
    NAME          VARCHAR(255)   NOT NULL,
    CATEGORY_ID   INTEGER        NOT NULL,
    DESCRIPTION   TEXT           NOT NULL,
    PRICE         NUMERIC(10, 2) NOT NULL,
    QUANTITY      INTEGER        NOT NULL,
    STATUS        INTEGER        NOT NULL,
    CREATED_DATE  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    MODIFIED_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);