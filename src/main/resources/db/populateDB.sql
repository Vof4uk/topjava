DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;
ALTER SEQUENCE global_seq2 RESTART WITH 100000000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (description, user_id, calories, date_time)
VALUES ('Breakfast', 100000, 500, '2016-09-26 07:38:00');
INSERT INTO meals (description, user_id, calories, date_time)
VALUES ('Dinner', 100000, 600, '2016-09-26 11:38:00');
INSERT INTO meals (description, user_id, calories, date_time)
VALUES ('Supper', 100000, 300, '2016-09-26 20:38:00');
INSERT INTO meals (description, user_id, calories, date_time)
VALUES ('Breakfast', 100000, 600, '2016-09-27 07:38:00');
INSERT INTO meals (description, user_id, calories, date_time)
VALUES ('Dinner', 100000, 400, '2016-09-27 12:38:00');

INSERT INTO meals (description, user_id, calories, date_time)
VALUES ('Breakfast', 100001, 200, '2016-09-26 07:38:00');
INSERT INTO meals (description, user_id, calories, date_time)
VALUES ('Supper', 100001, 800, '2016-09-26 11:38:00');
