INSERT INTO users (uuid, birth_date, name)
VALUES (RANDOM_UUID(), '1992-04-16 12:11:19', 'Jack'),
       (RANDOM_UUID(), '1992-03-15 13:43:20', 'Jill'),
       (RANDOM_UUID(), '1999-08-16 18:16:33', 'Jam');
INSERT INTO posts (uuid, description, user_id)
VALUES (RANDOM_UUID(), 'first post', 1);
