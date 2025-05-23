-- 1. Insert products
INSERT INTO product (id, name, description, price, stock, rating) VALUES 
(1, 'The Legend of Zelda: Breath of the Wild', 'Explore a vast open world as Link, uncovering secrets and solving physics-based puzzles in this critically acclaimed Nintendo adventure.', 59.99, 100, 4.9),
(2, 'Super Mario Odyssey', 'Join Mario on a massive, globe-trotting 3D adventure and use his new abilities to collect Moons and save Princess Peach from Bowser’s wedding plans.', 49.99, 75, 4.8),
(3, 'God of War', 'A cinematic action-adventure game blending Norse mythology and intense combat as Kratos and his son journey across dangerous realms.', 39.99, 50, 4.7),
(4, 'Elden Ring', 'An expansive open-world action RPG developed by FromSoftware, featuring challenging combat, deep lore, and a vast interconnected world.', 59.99, 80, 4.8),
(5, 'Horizon Forbidden West', 'A post-apocalyptic action RPG where you explore distant lands, fight mechanized beasts, and uncover Earth’s mysterious past.', 49.99, 60, 4.6),
(6, 'Nintendo Switch', 'A versatile hybrid console that can be played at home or on the go, supporting both handheld and docked gameplay modes.', 299.99, 50, 4.8),
(7, 'PlayStation 5', 'Sony’s next-gen console featuring lightning-fast load times, ray tracing, and a revolutionary DualSense controller.', 499.99, 25, 4.7),
(8, 'Xbox Series X', 'Microsoft’s most powerful console with 4K gaming, fast load times, and support for Xbox Game Pass.', 499.99, 30, 4.6),
(9, 'PlayStation 4 Pro', 'An upgraded version of the PS4 with enhanced graphics and performance for select titles.', 399.99, 40, 4.5),
(10, 'Xbox Series S', 'A compact digital-only console offering next-gen performance at a lower price.', 299.99, 35, 4.4),
(11, 'Pro Controller', 'Premium wireless controller for the Nintendo Switch, offering ergonomic design and motion controls.', 69.99, 50, 4.7),
(12, 'Joy-Con Pair', 'Two detachable controllers for Nintendo Switch with HD Rumble and motion sensors.', 79.99, 30, 4.5),
(13, 'DualSense Controller', 'PlayStation 5’s revolutionary controller with haptic feedback and adaptive triggers.', 69.99, 40, 4.8),
(14, 'Xbox Wireless Controller', 'Ergonomic and responsive controller compatible with Xbox consoles and Windows PCs.', 59.99, 45, 4.6),
(15, 'Pulse 3D Wireless Headset', 'Wireless headset for PS5 optimized for 3D Audio and voice chat with dual hidden microphones.', 99.99, 20, 4.4),
(16, 'Mario Kart 8 Deluxe', 'High-speed kart racing game with beloved Nintendo characters, inventive tracks, and fun multiplayer modes.', 59.99, 90, 4.9),
(17, 'Spider-Man: Miles Morales', 'A standalone Marvel superhero adventure featuring Miles Morales as he embraces his powers in a snowy New York City.', 49.99, 55, 4.7),
(18, 'Halo Infinite', 'Sci-fi first-person shooter featuring Master Chief in an expansive campaign and free-to-play multiplayer.', 59.99, 70, 4.5),
(19, 'Xbox Elite Controller', 'High-performance customizable controller for competitive Xbox and PC gaming.', 179.99, 15, 4.9),
(20, 'Nintendo Switch Lite', 'A handheld-only version of the Switch designed for portable gaming.', 199.99, 60, 3.0),
(21, 'Third-Party Switch Charger', 'Low-cost third-party charger for the Nintendo Switch, known for poor build quality and overheating.', 14.99, 100, 2.1),
(22, 'VR Headset X10', 'Budget VR headset with subpar image quality and unreliable tracking performance.', 89.99, 80, 1.8);

-- 2. Insert videogame details
INSERT INTO videogame (id, genre, developer, publisher, release_year, platform) VALUES 
(1, 'Action-Adventure', 'Nintendo', 'Nintendo', 2017, 'Nintendo Switch'),
(2, 'Platform', 'Nintendo', 'Nintendo', 2017, 'Nintendo Switch'),
(3, 'Action-Adventure', 'Santa Monica Studio', 'Sony', 2018, 'PlayStation 4'),
(4, 'Action RPG', 'FromSoftware', 'Bandai Namco', 2022, 'Multiple'),
(5, 'Action RPG', 'Guerrilla Games', 'Sony', 2022, 'PlayStation 5'),
(16, 'Racing', 'Nintendo', 'Nintendo', 2017, 'Nintendo Switch'),
(17, 'Action-Adventure', 'Insomniac Games', 'Sony', 2020, 'PlayStation 5'),
(18, 'Shooter', '343 Industries', 'Xbox Game Studios', 2021, 'Xbox Series X/S');

-- 3. Insert console details
INSERT INTO console (id, manufacturer, model, release_year, generation) VALUES 
(6, 'Nintendo', 'Switch', 2017, '8th Generation'),
(7, 'Sony', 'PlayStation 5', 2020, '9th Generation'),
(8, 'Microsoft', 'Xbox Series X', 2020, '9th Generation'),
(9, 'Sony', 'PlayStation 4 Pro', 2016, '8th Generation'),
(10, 'Microsoft', 'Xbox Series S', 2020, '9th Generation'),
(20, 'Nintendo', 'Switch Lite', 2019, '8th Generation');

-- 4. Insert accessories
INSERT INTO accessory (id, type, compatibility, brand) VALUES 
(11, 'Controller', 'Nintendo Switch', 'Nintendo'),
(12, 'Controller', 'Nintendo Switch', 'Nintendo'),
(13, 'Controller', 'PlayStation 5', 'Sony'),
(14, 'Controller', 'Xbox Series X/S', 'Microsoft'),
(15, 'Headset', 'PlayStation 5', 'Sony'),
(19, 'Controller', 'Xbox Series X/S', 'Microsoft');

-- 5. Insert reviews
INSERT INTO review (user_name, rating, comment, review_date, product_id) VALUES 
('marian.rojas', 5, 'This game had me hooked for hours. Beautiful world, tight gameplay.', '2023-01-15 10:30:00', 1),
('gbrl_mtz', 4, 'Very solid overall, though the controls felt weird at first.', '2023-02-20 14:45:00', 1),
('alejandro_vv', 5, 'Bought this for my nephew and ended up keeping it for myself.', '2023-03-10 09:15:00', 6),
('cecigomez_', 3, 'Works well, but the build quality does not feel premium.', '2023-04-05 16:20:00', 11),
('lhernandez.93', 5, 'Incredible storytelling. I actually cried at the ending.', '2023-05-12 11:30:00', 3),
('laura.mqz', 5, 'Feels like magic in your hands. Not exaggerating.', '2023-06-18 13:45:00', 13),
('ramonescobar', 2, 'Why is this still out of stock after all these months?', '2023-07-22 15:10:00', 7),
('vanealvarez', 4, 'Charming and fun. Took me back to simpler times.', '2023-08-30 12:25:00', 2),
('ismael.torres', 3, 'Performance is decent, but it heats up fast.', '2023-09-14 10:50:00', 10),
('andreavzla', 5, 'The audio quality is surprisingly good for a wireless headset.', '2023-10-05 14:15:00', 15),
('r.mendez', 5, 'Great pick for casual nights with friends or kids.', '2023-11-10 11:00:00', 16),
('pau.ventura', 4, 'Good action and pacing, though not as memorable as expected.', '2023-12-01 13:30:00', 17),
('jgomez.a', 5, 'Tense and fun. If you like fast-paced combat, get this.', '2024-01-20 15:45:00', 18),
('valemz23', 5, 'Yes, it is expensive. But it is also easily the best controller I have used.', '2024-02-14 17:20:00', 19),
('e.castillo', 3, 'Looks good and feels nice, but the battery life is disappointing.', '2024-03-05 09:10:00', 20);

-- 6. Insert orders
INSERT INTO orders (customer_name, customer_email, shipping_address, order_date, total_amount, status) VALUES
('Marian Rojas', 'marian.rojas@gmail.com', '58 Copperwood Lane, Redwood City', '2024-05-10 14:30:00', 159.97, 'DELIVERED'),
('Gabriel Martinez', 'gabriel.mtz@gmail.com', '103 Pine Hollow Dr, Echo Ridge', '2024-05-11 10:15:00', 499.99, 'SHIPPED'),
('Alejandro Vazquez', 'alejandro.vv@hotmail.com', '23 Larkspur Trail, Northbridge', '2024-05-12 16:45:00', 129.98, 'PROCESSING'),
('Cecilia Gomez', 'ceci.gomez@outlook.com', '742 Westlake Blvd, Elmridge', '2024-05-13 09:30:00', 599.97, 'PENDING'),
('Luis Hernandez', 'luis.hernandez@correo.com', '12 Oakshade Lane, Greenbrook', '2024-05-14 13:20:00', 169.98, 'CANCELED'),
('Laura Marquez', 'laura.mqz@gmail.com', '9 Fox Hollow Ct, Silver Hill', '2024-05-15 11:00:00', 299.99, 'DELIVERED'),
('Ramon Escobar', 'ramon.esc@gmail.com', '14 Sycamore Ridge, Windstone', '2024-05-16 12:45:00', 499.99, 'SHIPPED'),
('Vanessa Alvarez', 'vane.alvarez@live.com', '31 Brookside Terrace, Meadowfield', '2024-05-17 15:30:00', 79.99, 'PROCESSING');

-- 7. Insert order items
INSERT INTO order_item (order_id, product_id, quantity, unit_price, subtotal) VALUES
(1, 1, 1, 59.99, 59.99),
(1, 11, 1, 69.99, 69.99),
(1, 12, 1, 29.99, 29.99),
(2, 7, 1, 499.99, 499.99),
(3, 2, 1, 49.99, 49.99),
(3, 13, 1, 79.99, 79.99),
(4, 8, 1, 499.99, 499.99),
(4, 4, 1, 59.99, 59.99),
(4, 14, 1, 39.99, 39.99),
(5, 3, 1, 39.99, 39.99),
(5, 15, 1, 129.99, 129.99),
(6, 20, 1, 199.99, 199.99),
(7, 18, 1, 59.99, 59.99),
(7, 19, 1, 179.99, 179.99),
(8, 12, 1, 79.99, 79.99);

-- 8. Insert users
INSERT INTO users (username, password, role) VALUES
('admin', '$2a$12$15tQgbio0Z3WkRx3mEu8TO4QAzZjB71FEfRB8Ex02ATR855ua3jyy', 'ADMIN'),
('user', '$2a$12$22nKy93V.Q7Nem.IhKya.OAlytTjoYhk6lVnnBTFofv6hcPDXJ8b6', 'USER'),
('josueMtz', '$2a$12$22nKy93V.Q7Nem.IhKya.OAlytTjoYhk6lVnnBTFofv6hcPDXJ8b6', 'USER');
