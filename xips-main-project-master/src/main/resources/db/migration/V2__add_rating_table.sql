CREATE TABLE IF NOT EXISTS rating
(
    id      uuid    NOT NULL
        PRIMARY KEY,
    message varchar(255),
    stars   integer NOT NULL,
    user_id uuid
        CONSTRAINT fkf68lgbsbxl310n0jifwpfqgfh
            REFERENCES users
);

ALTER TABLE rating
    OWNER TO "xips-admin";

