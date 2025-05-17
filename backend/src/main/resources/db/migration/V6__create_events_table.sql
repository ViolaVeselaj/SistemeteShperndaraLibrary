CREATE TABLE events (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    description TEXT,
    location VARCHAR(200),
    event_date TIMESTAMP NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    tenant_id BIGINT NOT NULL DEFAULT 1,
    organizer_id BIGINT NOT NULL,
    CONSTRAINT fk_event_organizer FOREIGN KEY (organizer_id) REFERENCES user(id) ON DELETE CASCADE
);
