CREATE TABLE machines (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    manufacturer VARCHAR(255),
    model VARCHAR(255),
    status_id INT,
    location_id INT,
    FOREIGN KEY (status_id) REFERENCES machine_statuses(id),
    FOREIGN KEY (location_id) REFERENCES locations(id)
);

CREATE TABLE locations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255)
);

CREATE TABLE machine_statuses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    status ENUM('貸出可能', 'レンタル中', 'メンテナンス中') NOT NULL
);
