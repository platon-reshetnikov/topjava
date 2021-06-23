DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (date_time, description, calories, user_id) VALUES
('2015-05-30 10:00:00', 'Завтрак', 500, 100001),
('2015-05-30 13:00:00', 'Обед', 1000, 100001),
('2015-05-30 20:00:00', 'Ужин', 500, 100001),
('2015-05-31 10:00:00', 'Завтрак', 1000, 100001),
('2015-05-31 14:00:00', 'Обед', 500, 100001),
('2015-05-31 20:00:00', 'Ужин', 510, 100001),
('2018-03-13 08:00:00', 'Завтрак', 1000, 100000),
('2018-03-13 21:00:00', 'Ужин', 1000, 100000);
