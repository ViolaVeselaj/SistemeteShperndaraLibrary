-- V1: Initial Schema for Library Management System

-- Table for Users
CREATE TABLE IF NOT EXISTS users (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     name VARCHAR(255) NOT NULL,
                                     email VARCHAR(255) UNIQUE NOT NULL,
                                     password VARCHAR(255) NOT NULL,
                                     role ENUM('ADMIN', 'LIBRARIAN', 'USER') NOT NULL,
                                     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table for Books
CREATE TABLE IF NOT EXISTS books (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     title VARCHAR(255) NOT NULL,
                                     author VARCHAR(255) NOT NULL,
                                     published_year INT NOT NULL,
                                     available_copies INT NOT NULL,
                                     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table for Loans (Tracking Borrowed Books)
CREATE TABLE IF NOT EXISTS loans (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     user_id BIGINT NOT NULL,
                                     book_id BIGINT NOT NULL,
                                     borrow_date DATE NOT NULL,
                                     return_date DATE,
                                     status ENUM('BORROWED', 'RETURNED') DEFAULT 'BORROWED',
                                     FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                                     FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE
);
