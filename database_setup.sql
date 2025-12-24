-- ============================================
-- Restaurant Management System Database Setup
-- ============================================

-- Create Database
CREATE DATABASE IF NOT EXISTS restaurant_management;
USE restaurant_management;

-- ============================================
-- 1. Users Table (for customer/user authentication)
-- ============================================
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    fullName VARCHAR(150),
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    mobileNumber VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_email (email),
    INDEX idx_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ============================================
-- 2. Authority Table (for admin/owner login)
-- ============================================
CREATE TABLE IF NOT EXISTS Authority (
    Id VARCHAR(50) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(100),
    email VARCHAR(100),
    role VARCHAR(50) DEFAULT 'admin',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Insert default admin account (change password in production!)
INSERT INTO Authority (Id, password, name, email, role) VALUES 
('admin', 'admin123', 'System Administrator', 'admin@restaurant.com', 'owner');

-- ============================================
-- 3. Staff Table (for employee management)
-- ============================================
CREATE TABLE IF NOT EXISTS staff (
    id INT AUTO_INCREMENT PRIMARY KEY,
    staff_code VARCHAR(50) UNIQUE NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    mobile VARCHAR(20),
    address TEXT,
    position VARCHAR(100),
    salary DECIMAL(10, 2),
    hire_date DATE,
    birth_date DATE,
    gender ENUM('Male', 'Female', 'Other'),
    status ENUM('Active', 'Inactive', 'On Leave') DEFAULT 'Active',
    user_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL,
    INDEX idx_staff_code (staff_code),
    INDEX idx_email (email),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ============================================
-- 4. Password Reset Tokens Table (for OTP/password recovery)
-- ============================================
CREATE TABLE IF NOT EXISTS password_reset_tokens (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL,
    otp VARCHAR(10) NOT NULL,
    expires_at DATETIME NOT NULL,
    used TINYINT(1) DEFAULT 0,
    attempts INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_email_created (email, created_at),
    INDEX idx_expires (expires_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ============================================
-- 5. Orders Table (for customer orders)
-- ============================================
CREATE TABLE IF NOT EXISTS orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    order_number VARCHAR(50) UNIQUE,
    email VARCHAR(100),
    phone VARCHAR(20),
    address TEXT,
    total DECIMAL(10, 2) NOT NULL,
    order_type ENUM('Dine-In', 'Takeaway', 'Home Delivery') DEFAULT 'Home Delivery',
    status ENUM('Pending', 'Confirmed', 'Preparing', 'Ready', 'Delivered', 'Cancelled') DEFAULT 'Pending',
    payment_status ENUM('Pending', 'Paid', 'Refunded') DEFAULT 'Pending',
    payment_method VARCHAR(50),
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    delivery_date DATETIME,
    notes TEXT,
    INDEX idx_order_number (order_number),
    INDEX idx_email (email),
    INDEX idx_status (status),
    INDEX idx_order_date (order_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ============================================
-- 6. Menu Items Table (for restaurant menu)
-- ============================================
CREATE TABLE IF NOT EXISTS menu_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    item_name VARCHAR(150) NOT NULL,
    category ENUM('Breakfast', 'Lunch', 'Dinner', 'Dessert', 'Beverage', 'Soup', 'Appetizer') NOT NULL,
    price DECIMAL(8, 2) NOT NULL,
    description TEXT,
    image_path VARCHAR(255),
    is_available BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_category (category),
    INDEX idx_available (is_available)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ============================================
-- 7. Order Items Table (for individual order items)
-- ============================================
CREATE TABLE IF NOT EXISTS order_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    item_name VARCHAR(150) NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(8, 2) NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
    INDEX idx_order_id (order_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ============================================
-- 8. Tables Management (for dine-in reservations)
-- ============================================
CREATE TABLE IF NOT EXISTS restaurant_tables (
    id INT AUTO_INCREMENT PRIMARY KEY,
    table_number VARCHAR(20) UNIQUE NOT NULL,
    capacity INT NOT NULL,
    status ENUM('Available', 'Occupied', 'Reserved') DEFAULT 'Available',
    location VARCHAR(100),
    INDEX idx_table_number (table_number),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Insert sample tables
INSERT INTO restaurant_tables (table_number, capacity, status, location) VALUES
('Table 1', 4, 'Available', 'Main Hall'),
('Table 2', 2, 'Available', 'Main Hall'),
('Table 3', 6, 'Available', 'Main Hall'),
('Table 4', 4, 'Available', 'Private Room'),
('Table 5', 8, 'Available', 'Outdoor');

-- ============================================
-- 9. Reservations Table
-- ============================================
CREATE TABLE IF NOT EXISTS reservations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    table_id INT,
    customer_name VARCHAR(100) NOT NULL,
    customer_phone VARCHAR(20) NOT NULL,
    customer_email VARCHAR(100),
    party_size INT NOT NULL,
    reservation_date DATE NOT NULL,
    reservation_time TIME NOT NULL,
    status ENUM('Pending', 'Confirmed', 'Cancelled', 'Completed') DEFAULT 'Pending',
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (table_id) REFERENCES restaurant_tables(id) ON DELETE SET NULL,
    INDEX idx_reservation_date (reservation_date),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ============================================
-- Sample Menu Items
-- ============================================
INSERT INTO menu_items (item_name, category, price, description) VALUES
-- Breakfast
('Pancakes', 'Breakfast', 8.99, 'Fluffy pancakes with maple syrup'),
('Eggs Benedict', 'Breakfast', 12.99, 'Poached eggs with hollandaise sauce'),
('French Toast', 'Breakfast', 9.99, 'Classic French toast with berries'),
('Omelette', 'Breakfast', 10.99, 'Three-egg omelette with cheese'),

-- Lunch/Dinner
('Grilled Chicken', 'Dinner', 15.99, 'Grilled chicken breast with vegetables'),
('Beef Steak', 'Dinner', 24.99, 'Premium beef steak'),
('Pasta Carbonara', 'Dinner', 13.99, 'Creamy pasta with bacon'),
('Fish and Chips', 'Dinner', 14.99, 'Crispy fish with french fries'),
('Caesar Salad', 'Lunch', 9.99, 'Fresh salad with Caesar dressing'),
('Club Sandwich', 'Lunch', 11.99, 'Triple-decker sandwich'),

-- Desserts
('Chocolate Cake', 'Dessert', 6.99, 'Rich chocolate layer cake'),
('Ice Cream Sundae', 'Dessert', 5.99, 'Vanilla ice cream with toppings'),
('Tiramisu', 'Dessert', 7.99, 'Italian coffee-flavored dessert'),
('Cheesecake', 'Dessert', 6.99, 'New York style cheesecake'),

-- Beverages
('Coffee', 'Beverage', 2.99, 'Freshly brewed coffee'),
('Cappuccino', 'Beverage', 4.99, 'Espresso with steamed milk'),
('Fresh Juice', 'Beverage', 4.50, 'Orange or apple juice'),
('Soft Drink', 'Beverage', 2.50, 'Coke, Sprite, Fanta'),
('Iced Tea', 'Beverage', 3.50, 'Refreshing iced tea'),

-- Soups
('Tomato Soup', 'Soup', 5.99, 'Creamy tomato soup'),
('Chicken Soup', 'Soup', 6.99, 'Homemade chicken soup'),
('Mushroom Soup', 'Soup', 6.50, 'Cream of mushroom soup');

-- ============================================
-- Useful Queries for Your Application
-- ============================================

-- View all active orders
-- SELECT * FROM orders WHERE status != 'Delivered' AND status != 'Cancelled' ORDER BY order_date DESC;

-- View today's orders
-- SELECT * FROM orders WHERE DATE(order_date) = CURDATE();

-- View available menu items by category
-- SELECT * FROM menu_items WHERE is_available = TRUE AND category = 'Breakfast';

-- View active staff
-- SELECT * FROM staff WHERE status = 'Active' ORDER BY hire_date;

-- Check table availability
-- SELECT * FROM restaurant_tables WHERE status = 'Available';

-- View order details with items
-- SELECT o.*, oi.item_name, oi.quantity, oi.price 
-- FROM orders o 
-- JOIN order_items oi ON o.id = oi.order_id 
-- WHERE o.id = ?;

-- ============================================
-- Clean up expired password reset tokens (run periodically)
-- ============================================
-- DELETE FROM password_reset_tokens WHERE expires_at < NOW() OR used = 1;

-- ============================================
-- Database Setup Complete
-- ============================================
