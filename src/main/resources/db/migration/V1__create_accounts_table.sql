CREATE TABLE accounts (
    id UUID PRIMARY KEY,
    account_number VARCHAR(25) NOT NULL UNIQUE,
    status_name VARCHAR(50) NOT NULL,
    status_reason_name VARCHAR(255),
    account_open_date DATE NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);