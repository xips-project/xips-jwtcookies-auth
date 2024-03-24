CREATE TABLE IF NOT EXISTS users
(
    id         uuid         NOT NULL
        PRIMARY KEY,
    created_at timestamp(6),
    deleted_at timestamp(6),
    updated_at timestamp(6),
    email      varchar(255),
    password   varchar(255),
    role       varchar(255)
        CONSTRAINT users_role_check
            CHECK ((role)::text = ANY ((ARRAY ['USER'::character varying, 'ADMIN'::character varying])::text[])),
    username   varchar(255) NOT NULL
        CONSTRAINT ukr43af9ap4edm43mmtq01oddj6
            UNIQUE
);

ALTER TABLE users
    OWNER TO "xips-admin";

CREATE TABLE IF NOT EXISTS user_profile
(
    id        uuid NOT NULL
        PRIMARY KEY,
    address   varchar(255),
    birthdate date,
    city_name varchar(255),
    country   varchar(255),
    firstname varchar(255),
    lastname  varchar(255),
    zip_code  varchar(255),
    user_id   uuid
        CONSTRAINT uk_ebc21hy5j7scdvcjt0jy6xxrv
            UNIQUE
        CONSTRAINT fkuganfwvnbll4kn2a3jeyxtyi
            REFERENCES users
);

ALTER TABLE user_profile
    OWNER TO "xips-admin";

CREATE TABLE IF NOT EXISTS ratings
(
    id      uuid    NOT NULL
        PRIMARY KEY,
    message varchar(255),
    stars   integer NOT NULL,
    user_id uuid
        CONSTRAINT fkb3354ee2xxvdrbyq9f42jdayd
            REFERENCES users
);

ALTER TABLE ratings
    OWNER TO "xips-admin";

