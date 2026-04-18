INSERT INTO users (email, name)
VALUES ('luis@example.com', 'Luis Alonso');

INSERT INTO users (email, name)
VALUES ('maria@example.com', 'Maria Lopez');

INSERT INTO users (email, name)
VALUES ('juan@example.com', 'Juan Perez');


DELETE FROM users
WHERE email = 'luis@example.com';

DELETE FROM users
WHERE email = 'maria@example.com';

DELETE FROM users
WHERE email = 'juan@example.com';
