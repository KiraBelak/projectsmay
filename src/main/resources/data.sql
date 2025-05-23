-- USERS
INSERT INTO SURVEY_USERS ("EMAIL", "NAME", "PASSWORD")
    VALUES
    (null, 'admin', '$2a$10$5ER3ROQZl/SDl2JpG/icGOszQ/AvxbD.tXya6CIh/1.NwIVw0brr.'),
    (null, 'emed', '$2a$10$5ER3ROQZl/SDl2JpG/icGOszQ/AvxbD.tXya6CIh/1.NwIVw0brr.');
INSERT INTO USER_ROLES ("USER_ID", "ROLES")
    VALUES
     (1, 'ADMIN'),
     (1, 'USER'),
     (2, 'USER');

-- SURVEY
INSERT INTO SURVEY ("CODE", "TITLE", "USER_ID", "STATUS")
    VALUES ('iAVtBlmC46', 'CET Survey', 2, 'DRAFT');
INSERT INTO QUESTION ("TEXT", "FK_SURVEY")
    VALUES
     ('What is your preferred IDE?', 1),
     ('What is your favorite programming language?', 1),
     ('How often do you participate in code reviews?', 1);
INSERT INTO OPTION ("TEXT", "FK_QUESTION")
    VALUES
    ('IntelliJ IDEA', 1),
    ('Eclipse', 1),
    ('Visual Studio', 1),
    ('Visual Studio Code', 1),
    ('Other', 1),
    ('Java', 2),
    ('C#', 2),
    ('Python', 2),
    ('JavaScript', 2),
    ('Other', 2),
    ('Daily', 3),
    ('Weekly', 3),
    ('Monthly', 3),
    ('Never', 3);
