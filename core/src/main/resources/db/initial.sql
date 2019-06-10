-- liquibase formatted sql

-- changeset vpispanen:initial-1
CREATE TABLE `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `user_email_UNIQUE` (`email` ASC)
);

-- changeset vpispanen:initial-2
CREATE TABLE `product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `product_name_UNIQUE` (`name` ASC)
);

-- changeset vpispanen:initial-3
CREATE TABLE `sale` (
   `id` INT NOT NULL AUTO_INCREMENT,
   `product_id` INT NOT NULL ,
   PRIMARY KEY (`id`),
   FOREIGN KEY Sale_Product_FK (`product_id`)
     REFERENCES `product`(`id`)
);

-- changeset vpispanen:initial-4
ALTER TABLE `sale`
ADD COLUMN `date` timestamp NOT NULL;

