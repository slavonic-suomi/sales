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

-- changeset vpispanen:initial-3 dbms:mysql
CREATE TABLE `sale` (
                      `id` INT NOT NULL AUTO_INCREMENT,
                      `product_id` INT NOT NULL ,
                      PRIMARY KEY (`id`),
                      FOREIGN KEY Sale_Product_FK (`product_id`)
                        REFERENCES `product`(`id`)
);

-- changeset vpispanen:initial-3-1 dbms:h2
CREATE TABLE `sale` (
              `id` INT NOT NULL AUTO_INCREMENT,
              `product_id` INT NOT NULL ,
              PRIMARY KEY (`id`),
              FOREIGN KEY  (`product_id`)
                REFERENCES `product`(`id`)
);

-- changeset vpispanen:initial-4
ALTER TABLE `sale`
ADD COLUMN `date` timestamp NOT NULL;

-- changeset vpispanen:initial-5
ALTER TABLE `sale`
  ADD COLUMN `user_id` INT NULL;

-- changeset vpispanen:initial-6
insert into user(email) values ('admin@admin.com');
update sale set sale.user_id = (
  select id from user where email = 'admin@admin.com'
);

-- changeset vpispanen:initial-7
alter table sale
  modify COLUMN `user_id` INT NOT NULL;

-- changeset vpispanen:initial-8 dbms:mysql
alter table sale
  add foreign key Sale_User_FK (`user_id`)
  references `user` (`id`);

-- changeset vpispanen:initial-8-1 dbms:h2
alter table sale
  add foreign key (`user_id`)
    references `user` (`id`);

-- changeset vpispanen:initial-9
CREATE TABLE `category` (
                         `id` INT NOT NULL AUTO_INCREMENT,
                         `name` VARCHAR(45) NOT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE INDEX `category_name_UNIQUE` (`name` ASC)
);

