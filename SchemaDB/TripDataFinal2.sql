-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema tripData3
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema tripData3
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tripData3` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `tripData3` ;

-- -----------------------------------------------------
-- Table `tripData3`.`Address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tripData3`.`Address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(45) NULL,
  `houseNumber` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `postcode` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tripData3`.`Company`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tripData3`.`Company` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `Address_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Company_Address1_idx` (`Address_id` ASC),
  CONSTRAINT `fk_Company_Address1`
    FOREIGN KEY (`Address_id`)
    REFERENCES `tripData3`.`Address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tripData3`.`Person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tripData3`.`Person` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  `role` VARCHAR(20) NOT NULL,
  `email` VARCHAR(25) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `Company_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Person_Company1_idx` (`Company_id` ASC),
  CONSTRAINT `fk_Person_Company1`
    FOREIGN KEY (`Company_id`)
    REFERENCES `tripData3`.`Company` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tripData3`.`Type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tripData3`.`Type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NULL,
  `description` TEXT NULL,
  `duration` VARCHAR(15) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tripData3`.`Place`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tripData3`.`Place` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `Address_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Place_Address1_idx` (`Address_id` ASC),
  CONSTRAINT `fk_Place_Address1`
    FOREIGN KEY (`Address_id`)
    REFERENCES `tripData3`.`Address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tripData3`.`Term`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tripData3`.`Term` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `date` DATE NOT NULL,
  `Type_id` INT NOT NULL,
  `Place_id` INT NOT NULL,
  `coordinator_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Term_Type1_idx` (`Type_id` ASC),
  INDEX `fk_Term_Place1_idx` (`Place_id` ASC),
  INDEX `fk_Term_Person1_idx` (`coordinator_id` ASC),
  CONSTRAINT `fk_Term_Type1`
    FOREIGN KEY (`Type_id`)
    REFERENCES `tripData3`.`Type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Term_Place1`
    FOREIGN KEY (`Place_id`)
    REFERENCES `tripData3`.`Place` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Term_Person1`
    FOREIGN KEY (`coordinator_id`)
    REFERENCES `tripData3`.`Person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tripData3`.`person_has_term`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tripData3`.`person_has_term` (
  `idPerson` INT NOT NULL,
  `idTerm` INT NOT NULL,
  INDEX `fk_person_has_term_Term_idx` (`idTerm` ASC),
  INDEX `fk_person_has_term_Person1_idx` (`idPerson` ASC),
  CONSTRAINT `fk_person_has_term_Term`
    FOREIGN KEY (`idTerm`)
    REFERENCES `tripData3`.`Term` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_person_has_term_Person1`
    FOREIGN KEY (`idPerson`)
    REFERENCES `tripData3`.`Person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tripData3`.`Note`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tripData3`.`Note` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` TEXT NULL,
  `Term_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Note_Term1_idx` (`Term_id` ASC),
  CONSTRAINT `fk_Note_Term1`
    FOREIGN KEY (`Term_id`)
    REFERENCES `tripData3`.`Term` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
