
-- 1) Tabela e role-ve
CREATE TABLE role (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(50) NOT NULL UNIQUE
);

-- 2) Tabela e lejeve (permissions)
CREATE TABLE permission (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            http_method VARCHAR(10) NOT NULL,
                            url_pattern VARCHAR(255) NOT NULL
);

-- 3) Many-to-many user ↔ role
CREATE TABLE user_role (
                           user_id BIGINT NOT NULL,
                           role_id BIGINT NOT NULL,
                           PRIMARY KEY (user_id, role_id),
                           FOREIGN KEY (user_id)   REFERENCES `user`(id),
                           FOREIGN KEY (role_id)   REFERENCES role(id)
);

-- 4) Many-to-many role ↔ permission
CREATE TABLE role_permission (
                                 role_id       BIGINT NOT NULL,
                                 permission_id BIGINT NOT NULL,
                                 PRIMARY KEY (role_id, permission_id),
                                 FOREIGN KEY (role_id)       REFERENCES role(id),
                                 FOREIGN KEY (permission_id) REFERENCES permission(id)
);

ALTER TABLE `user`
    DROP COLUMN role;
