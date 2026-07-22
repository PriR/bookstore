INSERT INTO AUTHOR (FIRST_NAME, LAST_NAME) VALUES ( 'George', 'Orwell');
INSERT INTO AUTHOR (FIRST_NAME, LAST_NAME) VALUES ( 'Another', 'Author');
INSERT INTO AUTHOR (FIRST_NAME, LAST_NAME) VALUES ( 'Jane', 'Austin');
INSERT INTO AUTHOR (FIRST_NAME, LAST_NAME) VALUES ( 'Fiodor', 'Dostoïevski');

INSERT INTO BOOK (title, price, AUTHOR_ID)
VALUES ('1984',
        10.50,
        1);

INSERT INTO BOOK (title, price, AUTHOR_ID)
VALUES ('Another book',
        20.50,
        2);

INSERT INTO BOOK (title, price, AUTHOR_ID)
VALUES ('Pride and prejudice',
        20.20,
        3);

INSERT INTO BOOK (title, price, AUTHOR_ID)
VALUES ('The Brothers Karamazov ',
        7.50,
        4);