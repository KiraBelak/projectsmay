INSERT INTO roles (name) VALUES ('ADMIN');
INSERT INTO roles (name) VALUES ('MEMBER');

INSERT INTO users (username, password) VALUES ('Carlos', '$2a$12$svFqrF6eh4pP6n7u1k1IYOF2q0uDZPVVdlJ6yrFGcsn6hyTNfg1ta'); -- pass 123
INSERT INTO users (username, password) VALUES ('Josue', '$2a$12$xLhcs32Yh1GZeBdASWkdm.PuJxw2oOF7PFIehqUkJQPXOORiOxaPC'); -- pass 1234

INSERT INTO user_roles (user_id, role_id) values (1, 1);
INSERT INTO user_roles (user_id, role_id) values (1, 2);
INSERT INTO user_roles (user_id, role_id) values (2, 2);

INSERT INTO restaurants (name) values ('Burritos Villa Ahumada');

INSERT INTO categories (name) values ('Burrito'), ('Montado'), ('Quesadilla'), ('Refresco');

INSERT INTO menus (description, category_id, restaurant_id, price) values
('Deshebrada Rojo', 1, 1, 25), ('Deshebrada Rojo', 2, 1, 35),
('Deshebrada Verde', 1, 1, 25), ('Deshebrada Verde', 2, 1, 35),
('Bistec', 1, 1, 25), ('Bistec', 2, 1, 35),
('Asado', 1, 1, 25), ('Asado', 2, 1, 35),
('Chicharron', 1, 1, 25), ('Chicharron', 2, 1, 35),
('Frijoles con Chorizo', 1, 1, 25), ('Frijoles con Chorizo', 2, 1, 35),
('Frijoles con Queso', 1, 1, 25), ('Frijoles con Queso', 2, 1, 35),
('Picadillo', 1, 1, 25), ('Picadillo', 2, 1, 35),
('Queso', 3, 1, 25), ('Doble', 3, 1, 50), ('Carne', 3, 1, 50),
('Costilla Verde', 1, 1, 25), ('Costilla Verde', 2, 1, 35),
('Chile Relleno', 1, 1, 30), ('Chile Relleno', 2, 1, 40),
('Chicharron prensado', 1, 1, 30), ('Chicharron prensado', 2, 1, 40),
('Refresco', 4, 1, 25);

