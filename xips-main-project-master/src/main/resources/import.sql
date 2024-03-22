-- Insertar datos de ejemplo para la entidad User
INSERT INTO users (id, username, password, email, role)
VALUES
    (UUID(), 'user1', '$2a$10$M3mE/Z7Jdt0Y0mCJlv9bS.tswC5oyJ2P5vvcJqLt9BbVXTrSc3/u6', 'user1@example.com', 'USER'),
    (UUID(), 'user2', '$2a$10$DeMqhKmWlQGXrDxjWZbUXuEMCt5xtNtHXvk4QFL1n39fWQIiOMMaW', 'user2@example.com', 'USER'),
    (UUID(), 'user3', '$2a$10$CgNNUfCJFDZ/6WhuEqS.xeGpFO.VYFnUHnB5d/hZPkDfLY9S18kQ6', 'user3@example.com', 'USER'),
    (UUID(), 'user4', '$2a$10$fF93dZ4Yt6p8LzZksSftxOt9TlFPMcwm0Jy5TWfw8buHswpZC8bqi', 'user4@example.com', 'USER'),
    (UUID(), 'user5', '$2a$10$CJpvAr4AFUwzY14DwlgOce9Tqf1lPxU.Q3fDmJ0qfglZs6kJWgZZu', 'user5@example.com', 'USER'),
    (UUID(), 'user6', '$2a$10$ZiVw.SnhpiE4.SLHR7f0.Ox9r8aIalrRMlx/yDcyDPJEx0WwFOfai', 'user6@example.com', 'USER'),
    (UUID(), 'user7', '$2a$10$NvM5HesmIL2qJasXc2ExEObDY52h/5ObS6v1qgY.jxrCDBTknVKg2', 'user7@example.com', 'USER'),
    (UUID(), 'user8', '$2a$10$zkKuz4W1yq7v5v6meynzwuEZuvuXGOLM.S/kjvhD9wP4bErMUQOjW', 'user8@example.com', 'USER'),
    (UUID(), 'user9', '$2a$10$OT2uMhK3I/AUvXj2g2jPfugfH0OZIzUS3kPtRt20FkzMz5s4CmVqq', 'user9@example.com', 'USER'),
    (UUID(), 'user10', '$2a$10$Y8W2g4FOfSaYrl/cOx.NWeuJnJLV1n9U9O7aAQQy6z3ZJxw0bsbqS', 'user10@example.com', 'USER'),
    (UUID(), 'user11', '$2a$10$Y8W2g4FOfSaYrl/cOx.NWeuJnJLV1n9U9O7aAQQy6z3ZJxw0bsbqS', 'user100@example.com', 'USER'),
    (UUID(), 'user12', 'password123', 'user101@example.com', 'USER'),
    (UUID(), 'user13', 'p@ssw0rd', 'user102@example.com', 'USER'),
    (UUID(), 'user14', '123456', 'user103@example.com', 'USER'),
    (UUID(), 'user15', 'password123', 'user104@example.com', 'USER'),
    (UUID(), 'user16', 'p@ssw0rd', 'user105@example.com', 'USER'),
    (UUID(), 'user17', '123456', 'user106@example.com', 'USER'),
    (UUID(), 'user18', 'password123', 'user107@example.com', 'USER'),
    (UUID(), 'user19', 'p@ssw0rd', 'user108@example.com', 'USER'),
    (UUID(), 'user20', '123456', 'user109@example.com', 'USER'),
    (UUID(), 'user21', 'password123', 'user110@example.com', 'USER');

-- Insertar datos de ejemplo para la entidad UserProfile
INSERT INTO user_profile (id, firstname, lastname, birthdate, address, city_name, zip_code, country, user_id)
VALUES
    (UUID(), 'John', 'Doe', '1985-03-15', '123 Main St', 'New York', '10001', 'USA', (SELECT id FROM users WHERE username = 'user1')),
    (UUID(), 'Jane', 'Smith', '1990-08-22', '456 Elm St', 'Los Angeles', '90001', 'USA', (SELECT id FROM users WHERE username = 'user2')),
    (UUID(), 'Alice', 'Johnson', '1982-05-10', '789 Oak St', 'Chicago', '60601', 'USA', (SELECT id FROM users WHERE username = 'user3')),
    (UUID(), 'Bob', 'Brown', '1987-12-25', '10 Pine St', 'Miami', '33101', 'USA', (SELECT id FROM users WHERE username = 'user4')),
    (UUID(), 'Michael', 'Jones', '1980-06-18', '555 Cedar St', 'Houston', '77001', 'USA', (SELECT id FROM users WHERE username = 'user5')),
    (UUID(), 'Emily', 'Davis', '1995-04-30', '321 Oak St', 'Phoenix', '85001', 'USA', (SELECT id FROM users WHERE username = 'user6')),
    (UUID(), 'David', 'Wilson', '1988-09-07', '777 Maple St', 'San Francisco', '94101', 'USA', (SELECT id FROM users WHERE username = 'user7')),
    (UUID(), 'Sophia', 'Martinez', '1992-11-12', '999 Elm St', 'Boston', '02101', 'USA', (SELECT id FROM users WHERE username = 'user8')),
    (UUID(), 'James', 'Taylor', '1979-08-05', '456 Birch St', 'Seattle', '98101', 'USA', (SELECT id FROM users WHERE username = 'user9')),
    (UUID(), 'Olivia', 'Garcia', '1993-03-20', '888 Pine St', 'Denver', '80201', 'USA', (SELECT id FROM users WHERE username = 'user10')),
    (UUID(), 'Isabella', 'Rodriguez', '1986-07-18', '666 Cedar St', 'Atlanta', '30301', 'USA', (SELECT id FROM users WHERE username = 'user11')),
    (UUID(), 'Sophie', 'Brown', '1983-02-28', '123 Main St', 'New York', '10001', 'USA', (SELECT id FROM users WHERE username = 'user12')),
    (UUID(), 'Liam', 'Anderson', '1991-05-17', '456 Elm St', 'Los Angeles', '90001', 'USA', (SELECT id FROM users WHERE username = 'user13')),
    (UUID(), 'Ava', 'Garcia', '1978-09-10', '789 Oak St', 'Chicago', '60601', 'USA', (SELECT id FROM users WHERE username = 'user14')),
    (UUID(), 'Oliver', 'Martinez', '1986-12-24', '10 Pine St', 'Miami', '33101', 'USA', (SELECT id FROM users WHERE username = 'user15')),
    (UUID(), 'Emma', 'Smith', '1999-03-08', '555 Cedar St', 'Houston', '77001', 'USA', (SELECT id FROM users WHERE username = 'user16')),
    (UUID(), 'Mason', 'Wilson', '1982-06-15', '321 Oak St', 'Phoenix', '85001', 'USA', (SELECT id FROM users WHERE username = 'user17')),
    (UUID(), 'Charlotte', 'Taylor', '1995-08-20', '777 Maple St', 'San Francisco', '94101', 'USA', (SELECT id FROM users WHERE username = 'user18')),
    (UUID(), 'Ethan', 'Brown', '1989-11-07', '999 Elm St', 'Boston', '02101', 'USA', (SELECT id FROM users WHERE username = 'user19')),
    (UUID(), 'Amelia', 'Harris', '1984-04-03', '456 Birch St', 'Seattle', '98101', 'USA', (SELECT id FROM users WHERE username = 'user20')),
    (UUID(), 'Lucas', 'Jones', '1993-07-12', '888 Pine St', 'Denver', '80201', 'USA', (SELECT id FROM users WHERE username = 'user21'));

-- Insertar datos de ejemplo para la entidad Rating
INSERT INTO rating (id, stars, message, user_id)
VALUES
    (UUID(), 4, 'Great service!', (SELECT id FROM users WHERE username = 'user1')),
    (UUID(), 5, 'Excellent product!', (SELECT id FROM users WHERE username = 'user2')),
    (UUID(), 3, 'Could be better', (SELECT id FROM users WHERE username = 'user3')),
    (UUID(), 4, 'Good experience', (SELECT id FROM users WHERE username = 'user4')),
    (UUID(), 5, 'Amazing!', (SELECT id FROM users WHERE username = 'user5')),
    (UUID(), 2, 'Disappointed', (SELECT id FROM users WHERE username = 'user6')),
    (UUID(), 4, 'Would recommend', (SELECT id FROM users WHERE username = 'user7')),
    (UUID(), 3, 'Okay', (SELECT id FROM users WHERE username = 'user8')),
    (UUID(), 5, 'Fantastic!', (SELECT id FROM users WHERE username = 'user9')),
    (UUID(), 4, 'Great service!', (SELECT id FROM users WHERE username = 'user10')),
    (UUID(), 4, 'Nice!', (SELECT id FROM users WHERE username = 'user11')),
    (UUID(), 4, 'Great service!', (SELECT id FROM users WHERE username = 'user12')),
    (UUID(), 5, 'Excellent product!', (SELECT id FROM users WHERE username = 'user13')),
    (UUID(), 3, 'Could be better', (SELECT id FROM users WHERE username = 'user14')),
    (UUID(), 4, 'Good experience', (SELECT id FROM users WHERE username = 'user15')),
    (UUID(), 5, 'Amazing!', (SELECT id FROM users WHERE username = 'user16')),
    (UUID(), 2, 'Disappointed', (SELECT id FROM users WHERE username = 'user17')),
    (UUID(), 4, 'Would recommend', (SELECT id FROM users WHERE username = 'user18')),
    (UUID(), 3, 'Okay', (SELECT id FROM users WHERE username = 'user19')),
    (UUID(), 5, 'Fantastic!', (SELECT id FROM users WHERE username = 'user20')),
    (UUID(), 4, 'Great service!', (SELECT id FROM users WHERE username = 'user21'));