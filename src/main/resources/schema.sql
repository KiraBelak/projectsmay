CREATE TABLE IF NOT EXISTS roles (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS users (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255),
  password VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS user_roles (
  user_id INTEGER,
  role_id INTEGER,
  CONSTRAINT fk_user_roles_user_id FOREIGN KEY (user_id) REFERENCES users(id),
  CONSTRAINT fk_user_roles_role_id FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE IF NOT EXISTS restaurants (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS categories (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS menus (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  description VARCHAR(255),
  category_id INTEGER,
  restaurant_id INTEGER,
  price DOUBLE,
  CONSTRAINT fk_menus_category_id FOREIGN KEY (category_id) REFERENCES categories(id),
  CONSTRAINT fk_menus_restaurant_id FOREIGN KEY (restaurant_id) REFERENCES restaurants(id)
);

CREATE TABLE IF NOT EXISTS orders (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  user_id INTEGER,
  menu_id INTEGER,
  notes VARCHAR(255),
  quantity INTEGER,
  total DOUBLE,
  order_date DATE,
  paid BIT,
  CONSTRAINT fk_orders_user_id FOREIGN KEY (user_id) REFERENCES users(id),
  CONSTRAINT fk_orders_menu_id FOREIGN KEY (menu_id) REFERENCES menus(id)
);