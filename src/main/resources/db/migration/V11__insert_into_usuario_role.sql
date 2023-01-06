INSERT INTO usuario (id, nome, email, password) VALUES (2, 'admin', 'admin@email.com', '$2a$12$ZI0DsKrqvld7rb4E9sdrzu6Lvxts14yv8ipt2KEDeYjjfufpATzHa');
INSERT INTO role (id, nome) VALUES (2, 'ADMIN');
INSERT INTO usuario_role (id, usuario_id, role_id) VALUES (2, 2, 2);