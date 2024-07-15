-- Create the database
CREATE DATABASE IF NOT EXISTS commentdb;

-- Use the database
USE commentdb;

-- Create the comment table
CREATE TABLE comment (
    id INT AUTO_INCREMENT PRIMARY KEY,
    post_id INT NOT NULL,
    content TEXT NOT NULL,
    username VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);