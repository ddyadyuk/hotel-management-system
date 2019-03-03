DROP TABLE IF EXISTS address CASCADE ;
DROP TABLE IF EXISTS contact CASCADE ;
DROP TABLE IF EXISTS hotel CASCADE ;
DROP TABLE IF EXISTS room_capacity CASCADE ;
DROP TABLE IF EXISTS room_type CASCADE ;
DROP TABLE IF EXISTS room CASCADE ;
DROP TABLE IF EXISTS user_type CASCADE ;
DROP TABLE IF EXISTS user CASCADE ;
DROP TABLE IF EXISTS guest CASCADE ;
DROP TABLE IF EXISTS reservation CASCADE ;
-- Table address

CREATE TABLE IF NOT EXISTS address (
                                     id_address INT8 AUTO_INCREMENT
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
                                     id_contact INT8 AUTO_INCREMENT ,
                                     phone varchar(50),
                                     email varchar(50),
                                     PRIMARY KEY (id_contact));
-- Table hotel

CREATE TABLE IF NOT EXISTS hotel (
                                   id_hotel int8  NOT NULL AUTO_INCREMENT,
                                   contact INT8 NOT NULL,
                                   hotel_type VARCHAR(45) NOT NULL,
                                   address INT8 NOT NULL,
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
                                           id_room_capacity INT8  NOT NULL AUTO_INCREMENT,
                                           capacity INT NOT NULL,
                                           PRIMARY KEY (id_room_capacity));
-- Table room_type
CREATE TABLE IF NOT EXISTS room_type (
                                       id_room_type INT8  NOT NULL AUTO_INCREMENT ,
                                       type_name VARCHAR(45) NOT NULL,
                                       PRIMARY KEY (id_room_type));
-- Table room
CREATE TABLE IF NOT EXISTS room (
                                  id_room INT8  NOT NULL AUTO_INCREMENT,
                                  status VARCHAR(45) NOT NULL,
                                  price_per_hour FLOAT NOT NULL,
                                  hotel INT8 NOT NULL,
                                  room_capacity INT8 NOT NULL,
                                  room_type INT8 NOT NULL,
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
                                       id_user_type INT8  NOT NULL AUTO_INCREMENT,
                                       type_name VARCHAR(45) NOT NULL,
                                       PRIMARY KEY (id_user_type));

-- Table user

CREATE TABLE IF NOT EXISTS user (
                                      id_user INT8 NOT NULL AUTO_INCREMENT,
                                      password VARCHAR(45) NOT NULL,
                                      contact INT8 NOT NULL ,
                                      address INT8 NOT NULL,
                                      user_type VARCHAR(45) NOT NULL,
                                      PRIMARY KEY (id_user),
                                      FOREIGN KEY (address)
                                        REFERENCES address (id_address)
                                        ON DELETE NO ACTION
                                        ON UPDATE NO ACTION,
                                      FOREIGN KEY (contact)
                                        REFERENCES contact (id_contact)
                                        ON DELETE NO ACTION
                                        ON UPDATE NO ACTION);
-- Table guest
CREATE TABLE IF NOT EXISTS guest (
                                   id_guest INT8  NOT NULL AUTO_INCREMENT,
                                   user_id INT8 NOT NULL,
                                   PRIMARY KEY (id_guest),
                                   FOREIGN KEY (user_id)
                                     REFERENCES user (id_user)
                                     ON DELETE NO ACTION
                                     ON UPDATE NO ACTION);
-- Table reservation
CREATE TABLE IF NOT EXISTS reservation (
                                         id_reservation INT8 not null AUTO_INCREMENT,
                                         country VARCHAR(45) NOT NULL,
                                         room_type VARCHAR(45) ,
                                         start_booking DATE NOT NULL,
                                         end_booking DATE NOT NULL,
                                         guest INT8 NOT NULL,
                                         room INT8 NOT NULL,
                                         hotel INT8 NOT NULL,
                                         PRIMARY KEY (id_reservation),
                                         FOREIGN KEY (guest)
                                           REFERENCES guest (id_guest)
                                           ON DELETE NO ACTION
                                           ON UPDATE NO ACTION,
                                         FOREIGN KEY (room , hotel)
                                           REFERENCES room (id_room , hotel)
                                           ON DELETE NO ACTION
                                           ON UPDATE NO ACTION);
