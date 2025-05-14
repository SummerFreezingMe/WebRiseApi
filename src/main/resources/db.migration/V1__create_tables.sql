CREATE TABLE users (
                       id BIGINT PRIMARY KEY,
                       name VARCHAR(50) NOT NULL,
                       email VARCHAR(100) NOT NULL,
                       birth_date DATE NOT NULL,
                       address VARCHAR(255) NOT NULL,
                       phone_number VARCHAR(20) NOT NULL
);

CREATE TABLE subscriptions (
                               id BIGINT PRIMARY KEY,
                               service_name VARCHAR(100) NOT NULL,
                               description VARCHAR(255) NOT NULL,
                               price DECIMAL(10, 2) NOT NULL,
                               start_date DATE NOT NULL,
                               end_date DATE NOT NULL,
                               user_id BIGINT NOT NULL,
                               FOREIGN KEY (user_id) REFERENCES users(id)
);