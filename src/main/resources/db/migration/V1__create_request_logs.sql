CREATE TABLE request_logs (
    id VARCHAR(26) PRIMARY KEY,
    origin_ip VARCHAR(45) NOT NULL,
    request_date TIMESTAMP NOT NULL,
    method VARCHAR(100) NOT NULL,
    duration_ms BIGINT,
    request TEXT,
    response TEXT
);