INSERT INTO usuario (nome, email, password) VALUES ('admin', 'admin@email.com', "$2a$12$ZI0DsKrqvld7rb4E9sdrzu6Lvxts14yv8ipt2KEDeYjjfufpATzHa");
INSERT INTO role (nome) VALUES ('ADMIN');
INSERT INTO usuario_role (usuario_id, role_id) VALUES (2, 2);