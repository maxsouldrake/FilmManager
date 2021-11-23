DELETE
FROM films;

ALTER SEQUENCE films_id_seq RESTART WITH 1;

INSERT INTO films (title, year, genre, country, date, priority, actors, description, poster_url)
VALUES ('title1', 2001, 'genre1', 'country1', '2011-01-01', 1, 'actors1', 'description1', 'posterUrl1'),
       ('title2', 2002, 'genre2', 'country2', '2012-02-02', 2, 'actors2', 'description2', 'posterUrl2'),
       ('title3', 2003, 'genre3', 'country3', '2013-03-03', 3, 'actors3', 'description3', 'posterUrl3'),
       ('title4', 2004, 'genre4', 'country4', '2014-04-04', 4, 'actors4', 'description4', 'posterUrl4'),
       ('title5', 2005, 'genre5', 'country5', '2015-05-05', 5, 'actors5', 'description5', 'posterUrl5');

