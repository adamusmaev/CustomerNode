CREATE TABLE IF NOT EXISTS `customernode`.`address` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `city` VARCHAR(255) NULL DEFAULT NULL,
    `country` VARCHAR(255) NULL DEFAULT NULL,
    `state` VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `customernode`.`customer` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `email` VARCHAR(255) NULL DEFAULT NULL,
    `first_name` VARCHAR(255) NULL DEFAULT NULL,
    `last_name` VARCHAR(255) NULL DEFAULT NULL,
    `password` VARCHAR(255) NULL DEFAULT NULL,
    `phone_number` VARCHAR(255) NULL DEFAULT NULL,
    `address_id` INT NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `UK_nonbx33y5nkpeeohhjs6r18c0` (`email` ASC, `phone_number` ASC),
    INDEX `FKglkhkmh2vyn790ijs6hiqqpi` (`address_id` ASC),
    CONSTRAINT `FKglkhkmh2vyn790ijs6hiqqpi`
    FOREIGN KEY (`address_id`)
    REFERENCES `customernode`.`address` (`id`));


CREATE TABLE IF NOT EXISTS `customernode`.`paid_type` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `customernode`.`customers_paid_types` (
    `customer_id` INT NOT NULL,
    `paidtype_id` INT NOT NULL,
    INDEX `FK8xgv6m8bg5oqj15lpaop1b819` (`paidtype_id` ASC),
    INDEX `FKcgw03fbneqm9gihjg364l8kpp` (`customer_id` ASC),
    CONSTRAINT `FK8xgv6m8bg5oqj15lpaop1b819`
    FOREIGN KEY (`paidtype_id`)
    REFERENCES `customernode`.`paid_type` (`id`),
    CONSTRAINT `FKcgw03fbneqm9gihjg364l8kpp`
    FOREIGN KEY (`customer_id`)
    REFERENCES `customernode`.`customer` (`id`));

