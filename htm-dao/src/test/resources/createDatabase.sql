CREATE SCHEMA IF NOT EXISTS HTM AUTHORIZATION SA;

SET SEARCH_PATH TO HTM;

DROP TABLE IF EXISTS HTM.address CASCADE;
DROP TABLE IF EXISTS HTM.contact CASCADE;
DROP TABLE IF EXISTS HTM.hotel CASCADE;
DROP TABLE IF EXISTS HTM.room_capacity CASCADE;
DROP TABLE IF EXISTS HTM.room_type CASCADE;
DROP TABLE IF EXISTS HTM.room CASCADE;
DROP TABLE IF EXISTS HTM.user_type CASCADE;
DROP TABLE IF EXISTS HTM."user" CASCADE;
DROP TABLE IF EXISTS HTM.guest CASCADE;
DROP TABLE IF EXISTS HTM.reservation CASCADE;

-- Table address

CREATE TABLE IF NOT EXISTS HTM.address
(
    id_address  BIGSERIAL
        constraint address_pkey
            primary key,
    address1    varchar(1024),
    address2    varchar(1024),
    address3    varchar(1024),
    city        varchar(50),
    street      varchar(100),
    postal_code varchar(50),
    CREATED_BY  VARCHAR(50),
    CREATED_TS  TIMESTAMP,
    UPDATED_BY  VARCHAR(50),
    UPDATED_TS  TIMESTAMP
);
-- Table Contact
CREATE TABLE IF NOT EXISTS HTM.contact
(
    CONTACT_ID BIGSERIAL
        constraint contact_id
            primary key,
    phone      varchar(50),
    email      varchar(50),
    CREATED_BY VARCHAR(50),
    CREATED_TS TIMESTAMP,
    UPDATED_BY VARCHAR(50),
    UPDATED_TS TIMESTAMP

);

-- Table hotel

CREATE TABLE IF NOT EXISTS HTM.hotel
(
    id_hotel   BIGSERIAL   NOT NULL,
    contact    BIGINT      NOT NULL,
    hotel_type VARCHAR(45) NOT NULL,
    address    BIGINT      NOT NULL,
    CREATED_BY VARCHAR(50),
    CREATED_TS TIMESTAMP,
    UPDATED_BY VARCHAR(50),
    UPDATED_TS TIMESTAMP,
    PRIMARY KEY (id_hotel),
    FOREIGN KEY (contact)
        REFERENCES contact (CONTACT_ID)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    FOREIGN KEY (address)
        REFERENCES address (id_address)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);

-- Table room_capacity
CREATE TABLE IF NOT EXISTS HTM.room_capacity
(
    id_room_capacity BIGSERIAL NOT NULL,
    capacity         INT       NOT NULL,
    CREATED_BY       VARCHAR(50),
    CREATED_TS       TIMESTAMP,
    UPDATED_BY       VARCHAR(50),
    UPDATED_TS       TIMESTAMP,
    PRIMARY KEY (id_room_capacity)
);
-- Table room_type
CREATE TABLE IF NOT EXISTS HTM.room_type
(
    id_room_type BIGSERIAL   NOT NULL,
    type_name    VARCHAR(45) NOT NULL,
    CREATED_BY   VARCHAR(50),
    CREATED_TS   TIMESTAMP,
    UPDATED_BY   VARCHAR(50),
    UPDATED_TS   TIMESTAMP,
    PRIMARY KEY (id_room_type)
);
-- Table room
CREATE TABLE IF NOT EXISTS HTM.room
(
    id_room        BIGSERIAL   NOT NULL,
    status         VARCHAR(45) NOT NULL,
    price_per_hour FLOAT       NOT NULL,
    hotel          BIGINT      NOT NULL,
    room_capacity  SMALLINT    NOT NULL,
    room_type      BIGINT      NOT NULL,
    CREATED_BY     VARCHAR(50),
    CREATED_TS     TIMESTAMP,
    UPDATED_BY     VARCHAR(50),
    UPDATED_TS     TIMESTAMP,
    PRIMARY KEY (id_room, hotel),
    FOREIGN KEY (hotel)
        REFERENCES hotel (id_hotel)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,

    FOREIGN KEY (room_capacity)
        REFERENCES room_capacity (id_room_capacity)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,

    FOREIGN KEY (room_type)
        REFERENCES room_type (id_room_type)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);
-- Table user_type
CREATE TABLE IF NOT EXISTS HTM.user_type
(
    id_user_type BIGSERIAL   NOT NULL,
    type_name    VARCHAR(45) NOT NULL,
    CREATED_BY   VARCHAR(50),
    CREATED_TS   TIMESTAMP,
    UPDATED_BY   VARCHAR(50),
    UPDATED_TS   TIMESTAMP,
    PRIMARY KEY (id_user_type)
);
-- Table user

CREATE TABLE IF NOT EXISTS HTM."user"
(
    id_user    BIGSERIAL   NOT NULL,
    password   VARCHAR(45) NOT NULL,
    user_name  VARCHAR(50) NOT NULL,
    contact    INT8,
    address    INT8,
    user_type  VARCHAR(45) NOT NULL,
    CREATED_BY VARCHAR(50),
    CREATED_TS TIMESTAMP,
    UPDATED_BY VARCHAR(50),
    UPDATED_TS TIMESTAMP,
    PRIMARY KEY (id_user)
);
--                                       FOREIGN KEY (address)
--                                           REFERENCES address (id_address)
--                                           ON DELETE NO ACTION
--                                           ON UPDATE NO ACTION,
--                                       FOREIGN KEY (contact)
--                                           REFERENCES contact (id_contact)
--                                           ON DELETE NO ACTION
--                                           ON UPDATE NO ACTION);


-- Table guest
CREATE TABLE IF NOT EXISTS HTM.guest
(
    GUEST_ID BIGSERIAL NOT NULL,
    USER_ID  BIGINT    NOT NULL,
    CREATED_BY VARCHAR(50),
    CREATED_TS TIMESTAMP,
    UPDATED_BY VARCHAR(50),
    UPDATED_TS TIMESTAMP,
    PRIMARY KEY (GUEST_ID),
    FOREIGN KEY (USER_ID)
        REFERENCES "user" (id_user)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);
-- Table reservation
CREATE TABLE IF NOT EXISTS HTM.reservation
(
    id_reservation BIGSERIAL   not null,
    country        VARCHAR(45) NOT NULL,
    room_type      VARCHAR(45),
    start_booking  DATE        NOT NULL,
    end_booking    DATE        NOT NULL,
    GUEST_ID       BIGINT      NOT NULL,
    room           BIGINT      NOT NULL,
    hotel          BIGINT      NOT NULL,
    CREATED_BY VARCHAR(50),
    CREATED_TS TIMESTAMP,
    UPDATED_BY VARCHAR(50),
    UPDATED_TS TIMESTAMP,
    PRIMARY KEY (id_reservation),
    FOREIGN KEY (GUEST_ID)
        REFERENCES guest (GUEST_ID)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    FOREIGN KEY (room, hotel)
        REFERENCES room (id_room, hotel)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);
