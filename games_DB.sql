
DROP DATABASE IF EXISTS games_db;

CREATE DATABASE IF NOT EXISTS games_db;

USE games_db;

CREATE TABLE Game (
id_Game INT PRIMARY KEY AUTO_INCREMENT,
title_Game VARCHAR(255) NOT NULL,
genre_Game VARCHAR(255) NOT NULL,
release_year_Game INT NOT NULL,
publisher_Game VARCHAR(255) NOT NULL,
price_Game DOUBLE(10,2),
rate_Game INT
);


CREATE TABLE Platform (
id_Platform INT PRIMARY KEY AUTO_INCREMENT,
name_Platform VARCHAR(255) NOT NULL,
manufacturer_Platform VARCHAR(255) NOT NULL
);

CREATE TABLE Store (
id_Store INT PRIMARY KEY AUTO_INCREMENT,
name_Store VARCHAR(255) NOT NULL,
location_Store VARCHAR(255) NOT NULL
);

CREATE TABLE Game_Platform (
id_Game_Platform INT PRIMARY KEY AUTO_INCREMENT,
id_Game INT NOT NULL,
id_Platform INT NOT NULL,
id_Store INT NOT NULL,
FOREIGN KEY (id_Game) REFERENCES Game(id_Game),
FOREIGN KEY (id_Platform) REFERENCES Platform(id_Platform),
FOREIGN KEY (id_Store) REFERENCES Store(id_Store)
);

INSERT INTO Game (title_Game, genre_Game, release_year_Game, publisher_Game, price_Game, rate_Game)
VALUES
('The Legend of Zelda', 'Action-Adventure', 2017, 'Nintendo', 59.99, 10),
('The Witcher 3', 'Action RPG', 2015, 'CD Projekt Red', 39.99, 9),
('Grand Theft Auto V', 'Action-Adventure', 2013, 'Rockstar Games', 29.99, 8),
('Portal 2', 'Puzzle-platform', 2011, 'Valve Corporation', 9.99, 9),
('Half-Life 2', 'First-person shooter', 2004, 'Valve Corporation', 9.99, 9),
('Super Mario Odyssey', 'Platformer', 2017, 'Nintendo', 59.99, 10),
('BioShock Infinite', 'First-person shooter', 2013, '2K Games', 29.99, 8),
('Dark Souls', 'Action RPG', 2011, 'Namco Bandai Games', 19.99, 9),
('Fallout: New Vegas', 'Action RPG', 2010, 'Bethesda Softworks', 19.99, 8),
('Mass Effect 2', 'Action RPG', 2010, 'Electronic Arts', 19.99, 9),
('Metal Gear Solid V', 'Action-Adventure', 2015, 'Konami', 19.99, 8),
('Overwatch', 'First-person shooter', 2016, 'Blizzard Entertainment', 39.99, 9),
('Stardew Valley', 'Simulation', 2016, 'ConcernedApe', 14.99, 9),
('Minecraft', 'Sandbox', 2011, 'Mojang Studios', 26.95, 9),
('Diablo III', 'Action RPG', 2012, 'Blizzard Entertainment', 19.99, 8);



INSERT INTO Platform (name_Platform, manufacturer_Platform) VALUES
('PlayStation 5', 'Sony'),
('Xbox Series X', 'Microsoft'),
('Nintendo Switch', 'Nintendo'),
('Windows 10', 'Microsoft'),
('macOS', 'Apple'),
('iOS', 'Apple'),
('Android', 'Google'),
('Facebook', 'Meta'),
('Instagram', 'Meta'),
('Twitter', 'Twitter Inc.'),
('YouTube', 'Google'),
('TikTok', 'ByteDance'),
('Amazon Web Services', 'Amazon'),
('Google Cloud Platform', 'Google'),
('Microsoft Azure', 'Microsoft');



INSERT INTO Store (name_Store, location_Store)
VALUES
('GameStop', 'New York, NY'),
('Best Buy', 'Los Angeles, CA'),
('Walmart', 'Bentonville, AR'),
('Target', 'Minneapolis, MN'),
('Amazon', 'Seattle, WA'),
('GameFly', 'Los Angeles, CA'),
('EB Games', 'Toronto, ON'),
('Steam', 'Bellevue, WA'),
('GOG.com', 'Warsaw, Poland'),
('Humble Bundle', 'San Francisco, CA'),
('Origin', 'Redwood City, CA'),
('Uplay', 'Montreal, QC'),
('Xbox Game Pass', 'Redmond, WA'),
('PlayStation Store', 'San Mateo, CA'),
('Nintendo eShop', 'Kyoto, Japan');



INSERT INTO Game_Platform (id_Game, id_Platform, id_Store) VALUES
(1, 1, 1),
(1, 2, 2),
(2, 2, 2),
(2, 3, 3),
(3, 3, 3),
(3, 1, 1),
(4, 4, 4),
(5, 5, 5),
(6, 6, 6),
(7, 7, 7),
(8, 8, 8),
(9, 9, 9),
(10, 10, 10),
(11, 11, 11),
(12, 12, 12);






