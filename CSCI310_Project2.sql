DROP DATABASE IF EXISTS csci_310_project2;
CREATE DATABASE csci_310_project2;
USE csci_310_project2;

DROP TABLE IF EXISTS users;
CREATE TABLE users (
	userID int(11) primary key not null auto_increment,
    username varchar(50) NOT NULL DEFAULT 'null',
    password varchar(50) NOT NULL DEFAULT 'null'
);

DROP TABLE IF EXISTS collages;
CREATE TABLE collages (
	collageID int(11) primary key not null auto_increment,
    topic varchar(50) NOT NULL DEFAULT 'null',
    filepath varchar(500) NOT NULL DEFAULT 'null',
    userID int(11) NOT NULL,
    
    FOREIGN KEY fk1(userID) REFERENCES users(userID)
);