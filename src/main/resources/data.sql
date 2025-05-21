-- USER
INSERT INTO SURVEY_USERS ("ID", "EMAIL", "NAME", "PASSWORD")
    VALUES
    (1, null, 'admin', '123');
    (2, null, 'emed', '123');

-- SURVEY
INSERT INTO SURVEY ("ID", "CODE", "TITLE", "USER_ID", "STATUS")
    VALUES (1, 'iAVtBlmC46', 'CET Survey', 2, 'DRAFT');
INSERT INTO QUESTION ("ID", "TEXT", "FK_SURVEY")
    VALUES
     (1, 'What is your preferred IDE?', 1),
     (2, 'What is your favorite programming language?', 1),
     (3, 'How often do you participate in code reviews?', 1);
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
