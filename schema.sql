-- schema.sql for Books JDBC app
-- Create the books table
CREATE TABLE IF NOT EXISTS books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    published_year INT,
    price DECIMAL(10,2)
);

-- Insert some sample data
INSERT INTO books (title, author, published_year, price) VALUES
('The Hobbit', 'J.R.R. Tolkien', 1937, 15.99),
('1984', 'George Orwell', 1949, 12.50),
('To Kill a Mockingbird', 'Harper Lee', 1960, 10.99),
('The Catcher in the Rye', 'J.D. Salinger', 1951, 11.50),
('Pride and Prejudice', 'Jane Austen', 1813, 9.99);
