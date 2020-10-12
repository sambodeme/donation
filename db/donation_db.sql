-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema donation_db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `donation_db` ;

-- -----------------------------------------------------
-- Schema donation_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `donation_db` DEFAULT CHARACTER SET utf8 ;
USE `donation_db` ;

-- -----------------------------------------------------
-- Table `donation_db`.`donor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `donation_db`.`donor` ;

CREATE TABLE IF NOT EXISTS `donation_db`.`donor` (
  `idDonor` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) NULL,
  `address` VARCHAR(128) NULL,
  `email` VARCHAR(64) NULL,
  `phone` VARCHAR(32) NULL,
  `type` VARCHAR(1) NULL COMMENT 'H for human and O for organisation',
  PRIMARY KEY (`idDonor`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `donation_db`.`payment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `donation_db`.`payment` ;

CREATE TABLE IF NOT EXISTS `donation_db`.`payment` (
  `idPayment` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(32) NULL COMMENT 'Payment method',
  PRIMARY KEY (`idPayment`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `donation_db`.`donation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `donation_db`.`donation` ;

CREATE TABLE IF NOT EXISTS `donation_db`.`donation` (
  `idDonation` INT NOT NULL AUTO_INCREMENT,
  `amount` DECIMAL(10,2) NULL,
  `date` DATE NULL,
  `idDonor` INT NOT NULL,
  `idPayment` INT NOT NULL,
  PRIMARY KEY (`idDonation`),
  INDEX `fk_donation_donor1_idx` (`idDonor` ASC) VISIBLE,
  INDEX `fk_donation_payment1_idx` (`idPayment` ASC) VISIBLE,
  CONSTRAINT `fk_donation_donor1`
    FOREIGN KEY (`idDonor`)
    REFERENCES `donation_db`.`donor` (`idDonor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_donation_payment1`
    FOREIGN KEY (`idPayment`)
    REFERENCES `donation_db`.`payment` (`idPayment`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `donation_db`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `donation_db`.`category` ;

CREATE TABLE IF NOT EXISTS `donation_db`.`category` (
  `idCategory` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`idCategory`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `donation_db`.`expense`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `donation_db`.`expense` ;

CREATE TABLE IF NOT EXISTS `donation_db`.`expense` (
  `idExpense` INT NOT NULL AUTO_INCREMENT,
  `amount` DECIMAL(10,2) NOT NULL,
  `date` DATE NOT NULL,
  `idcategory` INT NOT NULL,
  `comment` VARCHAR(128) NULL,
  PRIMARY KEY (`idExpense`),
  INDEX `fk_espense_category1_idx` (`idcategory` ASC) VISIBLE,
  CONSTRAINT `fk_espense_category1`
    FOREIGN KEY (`idcategory`)
    REFERENCES `donation_db`.`category` (`idCategory`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `donation_db`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `donation_db`.`role` ;

CREATE TABLE IF NOT EXISTS `donation_db`.`role` (
  `idrole` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(32) NULL,
  PRIMARY KEY (`idrole`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `donation_db`.`member`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `donation_db`.`member` ;

CREATE TABLE IF NOT EXISTS `donation_db`.`member` (
  `idmember` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) NULL,
  `address` VARCHAR(128) NULL,
  `email` VARCHAR(64) NULL,
  `phone` VARCHAR(32) NULL,
  `idrole` INT NOT NULL,
  PRIMARY KEY (`idmember`),
  INDEX `fk_member_role1_idx` (`idrole` ASC) VISIBLE,
  CONSTRAINT `fk_member_role1`
    FOREIGN KEY (`idrole`)
    REFERENCES `donation_db`.`role` (`idrole`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `donation_db`.`question`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `donation_db`.`question` ;

CREATE TABLE IF NOT EXISTS `donation_db`.`question` (
  `idquestion` INT NOT NULL AUTO_INCREMENT,
  `question` VARCHAR(45) NULL,
  PRIMARY KEY (`idquestion`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `donation_db`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `donation_db`.`user` ;

CREATE TABLE IF NOT EXISTS `donation_db`.`user` (
  `iduser` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(32) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `response` VARCHAR(45) NOT NULL COMMENT 'for password recovery',
  `type` VARCHAR(1) NULL COMMENT 'A means admin - Have full access\nV means can only query reports\nE means can entry data',
  `idmember` INT NOT NULL,
  `idquestion` INT NOT NULL,
  PRIMARY KEY (`iduser`),
  INDEX `fk_user_member1_idx` (`idmember` ASC) VISIBLE,
  INDEX `fk_user_question2_idx` (`idquestion` ASC) VISIBLE,
  CONSTRAINT `fk_user_member1`
    FOREIGN KEY (`idmember`)
    REFERENCES `donation_db`.`member` (`idmember`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_question2`
    FOREIGN KEY (`idquestion`)
    REFERENCES `donation_db`.`question` (`idquestion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `donation_db`.`audit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `donation_db`.`audit` ;

CREATE TABLE IF NOT EXISTS `donation_db`.`audit` (
  `idAudit` INT NOT NULL,
  `amount` DECIMAL(10,2) NULL,
  `date` DATE NULL,
  `iddonor` INT NULL,
  `idpayment` INT NULL,
  `userDetails` VARCHAR(45) NULL,
  PRIMARY KEY (`idAudit`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
