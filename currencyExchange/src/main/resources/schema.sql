CREATE TABLE IF NOT EXISTS Currency_Exchange (
    id BIGINT PRIMARY KEY,
    conversion_multiple DOUBLE NOT NULL,
    from_currency VARCHAR(255) NOT NULL,
    to_currency VARCHAR(255) NOT NULL
);