USE forum;

INSERT INTO
    thread (name)
VALUES
    ('Sports'),
    ('Music'),
    ('Video Games'),
    ('Movies'),
    ('TV Shows'),
    ('Programming');

INSERT INTO
    role (name)
VALUES
    ('User'),
    ('Admin');

select * from role;
select * from thread;
select * from post;

INSERT INTO
    user (creation_date, email, password, username, role_id)
VALUES
    ('2022-10-10', 'fdsa', '23123', 'fdsaf', 1);

INSERT INTO
    post (content, creation, title, thread_id, user_id)
VALUES
    ('sample content', '2022-10-10', 'my title', 4, 1);