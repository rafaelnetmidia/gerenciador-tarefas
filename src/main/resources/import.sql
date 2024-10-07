INSERT INTO roles (id, name) VALUES (1, 'ADMIN');
INSERT INTO roles (id, name) VALUES (2, 'USER');

SELECT nextval('users_id_seq');
INSERT INTO users (id, username, password) VALUES (1, 'admin', '$2a$10$XC3JmSsW4XWI3SrF3A79i.jDCeHr.TmVFGDhyP092R.pO2jKB/odi');

INSERT INTO users_roles (role_id, user_id) values (1, 1);
INSERT INTO users_roles (role_id, user_id) values (2, 1);