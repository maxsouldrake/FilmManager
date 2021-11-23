DROP TABLE IF EXISTS films;

CREATE TABLE films
(
    id          SERIAL PRIMARY KEY,
    title       VARCHAR(128) NOT NULL,
    year        SMALLINT     NOT NULL CHECK (year > 999 AND year < 3000),
    genre       VARCHAR(16)  NOT NULL,
    country     VARCHAR(16)  NOT NULL,
    date        DATE         NOT NULL                                        DEFAULT now(),
    priority    SMALLINT     NOT NULL CHECK (priority > 0 AND priority < 11) DEFAULT 5,
    actors      VARCHAR(128) NOT NULL,
    description TEXT         NOT NULL,
    poster_url  VARCHAR(32)  NOT NULL
);
CREATE UNIQUE INDEX films_title_year_unique_index ON films (title, year);