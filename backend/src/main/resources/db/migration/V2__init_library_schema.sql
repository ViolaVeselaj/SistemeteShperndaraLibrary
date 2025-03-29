DROP TABLE IF EXISTS book_reviews, books_categories, fines, reservations, loan, book, authors, categories, publishers, trending_books, book_recommendations, user, tenants;

CREATE TABLE tenants (
                         id BIGINT NOT NULL AUTO_INCREMENT,
                         name VARCHAR(255) NOT NULL,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         PRIMARY KEY (id),
                         UNIQUE KEY (name)
);

CREATE TABLE user (
                      id BIGINT NOT NULL AUTO_INCREMENT,
                      name VARCHAR(255) NOT NULL,
                      email VARCHAR(255) NOT NULL,
                      password VARCHAR(255) NOT NULL,
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      tenant_id BIGINT NOT NULL DEFAULT 1,
                      PRIMARY KEY (id),
                      UNIQUE KEY (email),
                      KEY fk_user_tenant (tenant_id),
                      FOREIGN KEY (tenant_id) REFERENCES tenants(id) ON DELETE CASCADE
);

CREATE TABLE authors (
                         id INT NOT NULL AUTO_INCREMENT,
                         tenant_id BIGINT NOT NULL,
                         first_name VARCHAR(100) NOT NULL,
                         last_name VARCHAR(100) NOT NULL,
                         biography TEXT,
                         birth_date DATE DEFAULT NULL,
                         nationality VARCHAR(100),
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         PRIMARY KEY (id)
);
CREATE TABLE categories (
                            id INT NOT NULL AUTO_INCREMENT,
                            tenant_id BIGINT NOT NULL,
                            name VARCHAR(100) NOT NULL,
                            description TEXT,
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            PRIMARY KEY (id),
                            UNIQUE KEY uq_category_name_tenant (name, tenant_id)
);

CREATE TABLE publishers (
                            id INT NOT NULL AUTO_INCREMENT,
                            name VARCHAR(150) NOT NULL,
                            address TEXT,
                            website VARCHAR(255),
                            contact_email VARCHAR(150),
                            phone_number VARCHAR(50),
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            tenant_id BIGINT NOT NULL,
                            PRIMARY KEY (id),
                            UNIQUE KEY (name)
);

CREATE TABLE book (
                      id BIGINT NOT NULL AUTO_INCREMENT,
                      title VARCHAR(255) NOT NULL,
                      published_year INT NOT NULL,
                      available_copies INT NOT NULL,
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      tenant_id BIGINT NOT NULL DEFAULT 1,
                      author_id INT NOT NULL,
                      category_id INT DEFAULT NULL,
                      publisher_id INT DEFAULT NULL,
                      PRIMARY KEY (id),
                      KEY fk_book_author (author_id),
                      KEY fk_book_category (category_id),
                      KEY fk_book_publisher (publisher_id),
                      FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE CASCADE,
                      FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE SET NULL,
                      FOREIGN KEY (publisher_id) REFERENCES publishers(id) ON DELETE SET NULL
);

CREATE TABLE loan (
                      id BIGINT NOT NULL AUTO_INCREMENT,
                      user_id BIGINT NOT NULL,
                      book_id BIGINT NOT NULL,
                      borrow_date DATETIME(6),
                      return_date DATETIME(6),
                      status ENUM('BORROWED','RETURNED') DEFAULT 'BORROWED',
                      tenant_id BIGINT NOT NULL DEFAULT 1,
                      PRIMARY KEY (id),
                      KEY (user_id),
                      KEY (book_id),
                      FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
                      FOREIGN KEY (book_id) REFERENCES book(id) ON DELETE CASCADE
);

CREATE TABLE fines (
                       id INT NOT NULL AUTO_INCREMENT,
                       tenant_id BIGINT NOT NULL,
                       user_id BIGINT NOT NULL,
                       loan_id BIGINT NOT NULL,
                       amount DECIMAL(10,2) NOT NULL,
                       issued_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       paid TINYINT(1) DEFAULT 0,
                       PRIMARY KEY (id),
                       KEY (user_id),
                       KEY (loan_id),
                       FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
                       FOREIGN KEY (loan_id) REFERENCES loan(id) ON DELETE CASCADE
);

CREATE TABLE reservations (
                              id INT NOT NULL AUTO_INCREMENT,
                              tenant_id BIGINT NOT NULL,
                              user_id BIGINT NOT NULL,
                              book_id BIGINT NOT NULL,
                              reservation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              status ENUM('active','cancelled','fulfilled') DEFAULT 'active',
                              PRIMARY KEY (id),
                              KEY (user_id),
                              KEY (book_id),
                              FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
                              FOREIGN KEY (book_id) REFERENCES book(id) ON DELETE CASCADE
);

CREATE TABLE trending_books (
                                id BIGINT NOT NULL AUTO_INCREMENT,
                                book_id BIGINT NOT NULL,
                                reason TEXT,
                                start_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                end_date DATE DEFAULT NULL,
                                tenant_id BIGINT NOT NULL DEFAULT 1,
                                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                PRIMARY KEY (id),
                                UNIQUE KEY uq_trending_book_tenant (book_id, tenant_id),
                                FOREIGN KEY (book_id) REFERENCES book(id) ON DELETE CASCADE
);

CREATE TABLE book_recommendations (
                                      id BIGINT NOT NULL AUTO_INCREMENT,
                                      user_id BIGINT DEFAULT NULL,
                                      book_id BIGINT NOT NULL,
                                      type ENUM('TRENDING', 'PERSONALIZED') DEFAULT 'TRENDING',
                                      note TEXT,
                                      recommended_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                      tenant_id BIGINT NOT NULL DEFAULT 1,
                                      PRIMARY KEY (id),
                                      FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
                                      FOREIGN KEY (book_id) REFERENCES book(id) ON DELETE CASCADE
);

CREATE TABLE book_reviews (
                              id BIGINT NOT NULL AUTO_INCREMENT,
                              user_id BIGINT NOT NULL,
                              book_id BIGINT NOT NULL,
                              rating INT CHECK (rating BETWEEN 1 AND 5),
                              comment TEXT,
                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              tenant_id BIGINT NOT NULL,
                              PRIMARY KEY (id),
                              UNIQUE KEY uq_user_book_review (user_id, book_id),
                              FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
                              FOREIGN KEY (book_id) REFERENCES book(id) ON DELETE CASCADE,
                              FOREIGN KEY (tenant_id) REFERENCES tenants(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `books_categories` (
                                                  `book_id` BIGINT NOT NULL,
                                                  `category_id` INT NOT NULL,
                                                  `tenant_id` BIGINT NOT NULL,
                                                  PRIMARY KEY (`book_id`, `category_id`),
                                                  FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE CASCADE,
                                                  FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE CASCADE,
                                                  FOREIGN KEY (`tenant_id`) REFERENCES `tenants` (`id`) ON DELETE CASCADE
);

CREATE TABLE activity_log (
                                              `id` BIGINT NOT NULL AUTO_INCREMENT,
                                              `user_id` BIGINT NOT NULL,
                                              `action` VARCHAR(100) NOT NULL,
                                              `description` TEXT,
                                              `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                              `tenant_id` BIGINT NOT NULL,
                                              PRIMARY KEY (`id`),
                                              FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
                                              FOREIGN KEY (`tenant_id`) REFERENCES `tenants` (`id`) ON DELETE CASCADE
);