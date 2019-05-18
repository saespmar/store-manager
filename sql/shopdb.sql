DROP SCHEMA IF EXISTS `shopdb` ;
CREATE SCHEMA IF NOT EXISTS `shopdb` DEFAULT CHARACTER SET utf8 ;
USE `shopdb` ;

-- -----------------------------------------------------
-- Customers
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shopdb`.`customers` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `first_name` VARCHAR(255) NULL,
  `last_name` VARCHAR(255) NULL,
  `phone` VARCHAR(20) NULL,
  `street` VARCHAR(255) NULL,
  `zip_code` VARCHAR(10) NULL,
  `city` VARCHAR(100) NULL,
  `state` VARCHAR(100) NULL,
  `country` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC)
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Categories
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shopdb`.`categories` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Sub products
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shopdb`.`sub_products` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Products
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shopdb`.`products` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `description` TEXT NULL,
  `price` DECIMAL(12,2) NULL,
  `stock` SMALLINT UNSIGNED NULL,
  `category` INT UNSIGNED NOT NULL,
  `sub_products_id` INT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_products_categories1_idx` (`category` ASC),
  INDEX `fk_products_sub_products1_idx` (`sub_products_id` ASC),
  CONSTRAINT `fk_products_categories1`
    FOREIGN KEY (`category`)
    REFERENCES `shopdb`.`categories` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_products_sub_products1`
    FOREIGN KEY (`sub_products_id`)
    REFERENCES `shopdb`.`sub_products` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Orders
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shopdb`.`orders` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `order_date` DATE NOT NULL,
  `shipping_date` DATE NULL,
  `delivery_date` DATE NULL,
  `payment` VARCHAR(45) NULL,
  `customer` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_orders_customers1_idx` (`customer` ASC),
  CONSTRAINT `fk_orders_customers1`
    FOREIGN KEY (`customer`)
    REFERENCES `shopdb`.`customers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Orders have products
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shopdb`.`orders_have_products` (
  `order` INT UNSIGNED NOT NULL,
  `product` INT UNSIGNED NOT NULL,
  `quantity` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`order`, `product`),
  INDEX `fk_orders_has_products_products1_idx` (`product` ASC),
  INDEX `fk_orders_has_products_orders_idx` (`order` ASC),
  CONSTRAINT `fk_orders_has_products_orders`
    FOREIGN KEY (`order`)
    REFERENCES `shopdb`.`orders` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_orders_has_products_products1`
    FOREIGN KEY (`product`)
    REFERENCES `shopdb`.`products` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Shopping cart
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shopdb`.`shopping_cart` (
  `customer` INT UNSIGNED NOT NULL,
  `product` INT UNSIGNED NOT NULL,
  `quantity` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`customer`, `product`),
  INDEX `fk_products_has_customers_customers1_idx` (`customer` ASC),
  INDEX `fk_products_has_customers_products1_idx` (`product` ASC),
  CONSTRAINT `fk_products_has_customers_products1`
    FOREIGN KEY (`product`)
    REFERENCES `shopdb`.`products` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_products_has_customers_customers1`
    FOREIGN KEY (`customer`)
    REFERENCES `shopdb`.`customers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Opinions
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shopdb`.`opinions` (
  `product` INT UNSIGNED NOT NULL,
  `customer` INT UNSIGNED NOT NULL,
  `rating` TINYINT UNSIGNED NOT NULL,
  `review` TEXT NULL,
  PRIMARY KEY (`product`, `customer`),
  INDEX `fk_products_has_customers_customers2_idx` (`customer` ASC),
  INDEX `fk_products_has_customers_products2_idx` (`product` ASC),
  CONSTRAINT `fk_products_has_customers_products2`
    FOREIGN KEY (`product`)
    REFERENCES `shopdb`.`products` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_products_has_customers_customers2`
    FOREIGN KEY (`customer`)
    REFERENCES `shopdb`.`customers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- DEMO DATA
-- -----------------------------------------------------
INSERT INTO `shopdb`.`customers`
(`email`,                  `password`) VALUES
('hwdarin22@yopmail.com',  'f298d9de66efa43bc98f8032ca8b29c110647f45'),
('cieric8@yopmail.com',    '9c97b87b01005e510cffe79836cc372be39f61b5'),
('fobridie14@yopmail.com', '394fdf3d227d230a0bc6df4312ddac4211382aca');

INSERT INTO `shopdb`.`categories`
(`name`) VALUES
('Electronics'),
('Accessories'),
('Grocery');

INSERT INTO `shopdb`.`sub_products`
(`name`) VALUES
('Colour blue'),
('Colour black');

INSERT INTO `shopdb`.`products`
(`name`,                 `price`, `stock`, `category`, `sub_products_id`) VALUES
('Headphone holder',     9.99,    4,       1,          1),
('Headphone holder',     9.99,    25,      1,          2),
('Polarized sunglasses', 22.00,   5,       2,          NULL),
('Dishwasher tablets',   19.99,   7,       3,          NULL);

INSERT INTO `shopdb`.`orders`
(`order_date`, `shipping_date`, `delivery_date`, `payment`,     `customer`) VALUES
('2019-04-22', '2019-04-22',     '2019-04-24',   'Credit card', 1),
('2019-04-26', NULL,             NULL,            NULL,         2);

INSERT INTO `shopdb`.`orders_have_products`
(`order`, `product`, `quantity`) VALUES
(1,       1,         1),
(1,       3,         1),
(2,       4,         5);

INSERT INTO `shopdb`.`shopping_cart`
(`product`, `customer`, `quantity`) VALUES
(1,         3,          1);

INSERT INTO `shopdb`.`opinions`
(`product`, `customer`, `rating`, `review`) VALUES
(1,         1,          4,        'Good product');
