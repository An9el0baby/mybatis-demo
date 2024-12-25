-- 创建数据库
CREATE DATABASE IF NOT EXISTS book CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- 使用数据库
USE book;

-- 创建表 book
CREATE TABLE IF NOT EXISTS book (
    id INT AUTO_INCREMENT PRIMARY KEY,        -- 自增主键
    name VARCHAR(255) NOT NULL,               -- 书名
    author VARCHAR(255) NOT NULL,             -- 作者
    book_desc TEXT,                           -- 书籍描述
    price DECIMAL(10, 2) NOT NULL             -- 价格，保留两位小数
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 插入测试数据
INSERT INTO book (name, author, book_desc, price) VALUES
('The Catcher in the Rye', 'J.D. Salinger', 'A classic novel about teenage angst and rebellion.', 10.99),
('To Kill a Mockingbird', 'Harper Lee', 'A novel about racial injustice in the Deep South.', 12.50),
('1984', 'George Orwell', 'A dystopian novel about totalitarianism.', 14.99),
('Pride and Prejudice', 'Jane Austen', 'A romantic novel that also critiques societal norms.', 9.99),
('The Great Gatsby', 'F. Scott Fitzgerald', 'A novel about the American Dream and disillusionment.', 13.99);