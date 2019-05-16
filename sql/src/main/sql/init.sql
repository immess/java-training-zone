insert INTO CATTERY(NAME, OWNER, CITY)
VALUES ('Bismark', 'Queen Elizabeth X', 'London'),
       ('Vostok', 'Tsiolkovsky', 'Moscow'),
       ('MLP', 'Geek on his thirties', 'Saint-Petersburg');

insert into CAT(CATTERY_ID, NAME, SEX, COLOR, STERILIZED)
values (select id from CATTERY where NAME = 'Bismark', 'Duke of York', 1, 'gray', 0),
       (select id from CATTERY where NAME = 'Bismark', 'Renown', 0, 'black', 0),
       (select id from CATTERY where NAME = 'Vostok', 'Soyuz', 1, 'red', 0),
       (select id from CATTERY where NAME = 'Vostok', 'Zarya', 0, 'white and black', 0),
       (select id from CATTERY where NAME = 'Vostok', 'Energia', 0, 'black', 1),
       (select id from CATTERY where NAME = 'MLP', 'Braeburn', 1, 'yellow', 0),
       (select id from CATTERY where NAME = 'MLP', 'Fluttershy', 0, 'yellow', 0);

insert into LITTER(CATTERY_ID, NAME, BIRTH_DATE, DAD_ID, MOM_ID)
values (select id from CATTERY where NAME = 'Bismark',
        'R',
        parsedatetime('01.01.2019', 'dd.MM.yyyy'),
        select id from CAT where NAME = 'Duke of York',
        select id from CAT where NAME = 'Renown'),
       (select id from CATTERY where NAME = 'Vostok',
        'V',
        parsedatetime('01.02.2019', 'dd.MM.yyyy'),
        select id from CAT where NAME = 'Soyuz',
        select id from CAT where NAME = 'Zarya');

insert into CAT(CATTERY_ID, LITTER_ID, NAME, SEX, COLOR, STERILIZED)
values (select id from CATTERY where NAME = 'Bismark',
        select id from LITTER where NAME = 'R',
        'Racehorse',
        1,
        'black',
        0),
       (select id from CATTERY where NAME = 'Bismark',
        select id from LITTER where NAME = 'R',
        'Rattler',
        0,
        'black',
        0),
       (select id from CATTERY where NAME = 'Vostok',
        select id from LITTER where NAME = 'V',
        'Vega',
        0,
        'red',
        0),
       (select id from CATTERY where NAME = 'Vostok',
        select id from LITTER where NAME = 'V',
        'Venera',
        0,
        'black',
        0),
       (select id from CATTERY where NAME = 'Vostok',
        select id from LITTER where NAME = 'V',
        'Voskhod',
        1,
        'black',
        0);
