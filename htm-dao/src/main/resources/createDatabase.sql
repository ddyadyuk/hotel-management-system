DROP TABLE IF EXISTS address CASCADE ;
DROP TABLE IF EXISTS contact CASCADE ;
DROP TABLE IF EXISTS hotel CASCADE ;
DROP TABLE IF EXISTS room_capacity CASCADE ;
DROP TABLE IF EXISTS room_type CASCADE ;
DROP TABLE IF EXISTS room CASCADE ;
DROP TABLE IF EXISTS user_type CASCADE ;
DROP TABLE IF EXISTS "user" CASCADE ;
DROP TABLE IF EXISTS guest CASCADE ;
DROP TABLE IF EXISTS reservation CASCADE ;
-- Table address

CREATE TABLE IF NOT EXISTS address (
                                       id_address SERIAL
                                           constraint address_pkey
                                               primary key,
                                       address1 varchar(1024),
                                       address2 varchar(1024),
                                       address3 varchar(1024),
                                       city varchar(50),
                                       street varchar(100)  ,
                                       postal_code varchar(50));
-- Table Contact
CREATE TABLE IF NOT EXISTS contact (
                                       id_contact SERIAL ,
                                       phone varchar(50),
                                       email varchar(50),
                                       PRIMARY KEY (id_contact));
-- Table hotel

CREATE TABLE IF NOT EXISTS hotel (
                                     id_hotel SERIAL  NOT NULL ,
                                     contact SERIAL NOT NULL,
                                     hotel_type VARCHAR(45) NOT NULL,
                                     address SERIAL NOT NULL,
                                     PRIMARY KEY (id_hotel),
                                     FOREIGN KEY (contact)
                                         REFERENCES contact (id_contact)
                                         ON DELETE NO ACTION
                                         ON UPDATE NO ACTION,
                                     FOREIGN KEY (address)
                                         REFERENCES address (id_address)
                                         ON DELETE NO ACTION
                                         ON UPDATE NO ACTION);

-- Table room_capacity
CREATE TABLE IF NOT EXISTS room_capacity (
                                             id_room_capacity SERIAL  NOT NULL,
                                             capacity INT NOT NULL,
                                             PRIMARY KEY (id_room_capacity));
-- Table room_type
CREATE TABLE IF NOT EXISTS room_type (
                                         id_room_type SERIAL NOT NULL  ,
                                         type_name VARCHAR(45) NOT NULL,
                                         PRIMARY KEY (id_room_type));
-- Table room
CREATE TABLE IF NOT EXISTS room (
                                    id_room SERIAL NOT NULL,
                                    status VARCHAR(45) NOT NULL,
                                    price_per_hour FLOAT NOT NULL,
                                    hotel SERIAL NOT NULL,
                                    room_capacity SERIAL NOT NULL,
                                    room_type SERIAL NOT NULL,
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
                                        ON UPDATE NO ACTION);
-- Table user_type
CREATE TABLE IF NOT EXISTS user_type (
                                         id_user_type SERIAL NOT NULL,
                                         type_name VARCHAR(45) NOT NULL,
                                         PRIMARY KEY (id_user_type));

-- Table user

CREATE TABLE IF NOT EXISTS "user" (
                                      id_user SERIAL NOT NULL,
                                      password VARCHAR(45) NOT NULL,
                                      contact INT8,
                                      address INT8,
                                      user_type VARCHAR(45) NOT NULL,
                                      PRIMARY KEY (id_user));
--                                       FOREIGN KEY (address)
--                                           REFERENCES address (id_address)
--                                           ON DELETE NO ACTION
--                                           ON UPDATE NO ACTION,
--                                       FOREIGN KEY (contact)
--                                           REFERENCES contact (id_contact)
--                                           ON DELETE NO ACTION
--                                           ON UPDATE NO ACTION);
-- Table guest
CREATE TABLE IF NOT EXISTS guest (
                                     id_guest SERIAL NOT NULL ,
                                     user_id SERIAL NOT NULL,
                                     PRIMARY KEY (id_guest),
                                     FOREIGN KEY (user_id)
                                         REFERENCES "user" (id_user)
                                         ON DELETE NO ACTION
                                         ON UPDATE NO ACTION);
-- Table reservation
CREATE TABLE IF NOT EXISTS reservation (
                                           id_reservation SERIAL not null ,
                                           country VARCHAR(45) NOT NULL,
                                           room_type VARCHAR(45) ,
                                           start_booking DATE NOT NULL,
                                           end_booking DATE NOT NULL,
                                           guest SERIAL NOT NULL,
                                           room SERIAL NOT NULL,
                                           hotel SERIAL NOT NULL,
                                           PRIMARY KEY (id_reservation),
                                           FOREIGN KEY (guest)
                                               REFERENCES guest (id_guest)
                                               ON DELETE NO ACTION
                                               ON UPDATE NO ACTION,
                                           FOREIGN KEY (room , hotel)
                                               REFERENCES room (id_room , hotel)
                                               ON DELETE NO ACTION
                                               ON UPDATE NO ACTION);
