﻿DROP TABLE IF EXISTS sb_goods, sb_authors, sb_produsers, sb_authorbooks;

CREATE TABLE  sb_authors (
	author_id SERIAL PRIMARY KEY,
	author_name varchar(300) NOT NULL
);
CREATE TABLE  sb_produsers (
	produser_id SERIAL PRIMARY KEY,
	produser_name varchar(300) NOT NULL

);
CREATE TABLE sb_goods (
	goods_id SERIAL PRIMARY KEY,
	goods_name varchar(300) NOT NULL,
	goods_price int NOT NULL,
	goods_status int NOT NULL DEFAULT 0,
	goods_gettingdate TIMESTAMP NOT NULL DEFAULT Now(),
	goods_dateofsale TIMESTAMP DEFAULT NULL,
	goods_produser_id int NOT NULL,
		FOREIGN KEY (goods_produser_id) REFERENCES sb_produsers(produser_id) ON UPDATE CASCADE
	
);
CREATE TABLE sb_authorbooks (
	authorsname_id int NOT NULL,
	booksname_id int NOT NULL ,
		FOREIGN KEY (authorsname_id) REFERENCES sb_authors(author_id)ON UPDATE CASCADE,
		FOREIGN KEY (booksname_id) REFERENCES sb_goods(goods_id) ON UPDATE CASCADE
);



INSERT INTO sb_authors (author_id, author_name) VALUES
(1, 'Питер Уоттс'),
(2, 'Кен Маклеод'),
(3, 'Чайна Мьевиль'),
(4, 'Питер Гамильтон'),
(5, 'Карл Шредер'),
(6, 'Чарльз Стросс'),
(7, 'Джон Скальци'),
(8, 'Аластер Рейнольдс'),
(9, 'Стивен Бакстер'),
(10, 'Адам Робертс'),
(11, 'Энн Леки'),
(12, 'Лорен Бьюкерс');

INSERT INTO sb_produsers (produser_id, produser_name) VALUES
(1, 'Азбука'),
(2, 'Алгоритм'),
(3, 'Альфа-книга'),
(4, 'АСТ'),
(5, 'Вентана-Граф'),
(6, 'Вече'),
(7, 'Вита Нова'),
(8, 'Время'),
(9, 'Дрофа'),
(10, 'Капитал'),
(11, 'Осма Медиа Групп'),
(12, 'Периодика'),
(13, 'Питер'),
(14, 'Просвещение'),
(15, 'РИПОЛ классик'),
(16, 'Розовый жираф'),
(17, 'Росмэн'),
(18, 'ТЕРРА'),
(19, 'Экзамен'),
(20, 'ЭКСМО');

INSERT INTO sb_goods (goods_name, goods_price, goods_produser_id) VALUES 
('Морские звёзды', 15000, 1),
('Ложная слепота', 17500, 2),
('Эхопраксия', 11000, 3),
('Поминки по Ньютону: Космическая опера', 25000, 4),
('Вторжение', 32000, 5),
('Канал казни', 15000, 6),
('Посольский город', 17500, 7),
('Город и город', 11000, 8),
('Вокзал потерянных снов', 25000, 9),
('Звезда Пандоры', 15000, 10),
('Великий северный путь', 17500, 11),
('Мечтающая бездна', 11000, 12),
('Порядок', 25000, 13),
('Дама лабиринтов', 15000, 14),
('Неизменность', 17500, 15),
('Аччелерандо', 11000, 16),
('Оранжерея', 25000, 17),
('Правило 34', 15000, 18),
('Люди в красном', 17500, 19),
('Сон андроида', 11000, 20),
('Взаперти', 25000, 1),
('Пространство откровения', 15000, 2),
('Дом солнц', 17500, 3),
('Толкая лёд', 11000, 4),
('Проксима', 25000, 5),
('Ковчег', 15000, 6),
('Многообразие космоса', 17500, 7),
('Соль', 11000, 8),
('Жёлто-голубая Тибия', 25000, 9),
('Стеклянный Джек', 15000, 10),
('Слуги правосудия', 17500, 11),
('Слуги меча', 11000, 12),
('Моксиленд', 25000, 13),
('Сияющие девочки', 15000, 14),
('Сломанные монстры', 17500, 15),
('Моксиленд', 26000, 1),
('Сияющие девочки', 16000, 2),
('Сломанные монстры', 18500, 3);

INSERT INTO sb_authorbooks (authorsname_id, booksname_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 4),
(2, 5),
(2, 6),
(3, 7),
(3, 8),
(3, 9),
(4, 10),
(4, 11),
(4, 12),
(5, 13),
(5, 14),
(5, 15),
(6, 16),
(6, 17),
(6, 18),
(7, 19),
(7, 20),
(7, 21),
(8, 22),
(8, 23),
(8, 24),
(9, 25),
(9, 26),
(9, 27),
(10, 28),
(10, 29),
(10, 30),
(11, 31),
(11, 32),
(12, 33),
(12, 34),
(12, 35),
(12, 32),
(11, 33);