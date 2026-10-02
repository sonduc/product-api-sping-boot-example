-- Local development accounts only. Both passwords are BCrypt hashes of "password".
INSERT INTO users (username, password, role) VALUES
    ('admin', '$2a$10$V7bFZ63bY9SihCQkhGtrx.Nv1Sp0sqVzVtdqODLTk3P3yE6SdQNOe', 'ADMIN'),
    ('user', '$2a$10$1fb7CCwaNkzrAX/qr8cWfun99oICFs//SMYmvTQOWFatDC68Z72si', 'USER');
