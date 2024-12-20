-- Passengers Table
CREATE TABLE passengers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL
);

-- Drivers Table
CREATE TABLE drivers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    car_details VARCHAR(255) NOT NULL,
    availability BOOLEAN DEFAULT TRUE,
    earnings DOUBLE DEFAULT 0,
    ratings DOUBLE DEFAULT 0
);

-- Rides Table
CREATE TABLE rides (
    id INT AUTO_INCREMENT PRIMARY KEY,
    passenger_id INT NOT NULL,
    driver_id INT,
    pickup_location VARCHAR(255) NOT NULL,
    drop_location VARCHAR(255) NOT NULL,
    fare DOUBLE NOT NULL,
    status VARCHAR(50) NOT NULL,
    FOREIGN KEY (passenger_id) REFERENCES passengers(id),
    FOREIGN KEY (driver_id) REFERENCES drivers(id)
);

-- Admins Table
CREATE TABLE admins (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
