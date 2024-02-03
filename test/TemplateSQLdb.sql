/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Stiven Quiroga && Daniel Lozano
 * Created: 3/02/2024
 */



CREATE database software_sena;
USE software_sena;

CREATE TABLE IF NOT EXISTS proovedor (
`id_proovedor` INT NOT NULL PRIMARY KEY auto_increment,
  `nombre` VARCHAR(45) NOT NULL,
  `telefono` VARCHAR(15) NOT NULL,
  `identificacion` VARCHAR(15) NOT NULL UNIQUE,
  `direccion` VARCHAR(100) NOT NULL,
  `catalogo` TEXT(500) NULL,
  `ciudad` VARCHAR(50) NOT NULL
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS productos (
	`id_producto` INT NOT NULL PRIMARY KEY auto_increment,
	`id_proovedor` INT NOT NULL,
	`nombre` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(45) NULL,
  `categoria` VARCHAR(45) NOT NULL,
  `cantidad` INT NOT NULL DEFAULT 0,
  `precio` DECIMAL(10,2) NOT NULL,
  CONSTRAINT fk_productos_proovedor FOREIGN KEY (id_proovedor) REFERENCES proovedor (id_proovedor)
) ENGINE = InnoDB;

-- Se necesita de un proovedor, en este caso es el id = "1" como referencia a la hora de enviar datos a la base de datos;
INSERT into proovedor(nombre, telefono, identificacion, direccion, catalogo, ciudad) values ("Gonzalo Jus", 8371829191, 1074827394, "Carrera 9 este c#47", "[]", "Bogota D.C");

-- Insertar productos
INSERT INTO productos (id_proovedor, nombre, descripcion, categoria, cantidad, precio)
VALUES (1, 'Queso Gouda', 'Queso de leche de vaca', 'Ganadería', 50, 8.99);

INSERT INTO productos (id_proovedor, nombre, descripcion, categoria, cantidad, precio)
VALUES (1, 'Bocadillo de Guayaba', 'Dulce de guayaba envuelto en hojaldre', 'Ganadería', 30, 2.50);

INSERT INTO productos (id_proovedor, nombre, descripcion, categoria, cantidad, precio)
VALUES (1, 'Leche Fresca', 'Leche pasteurizada de alta calidad', 'Ganadería', 100, 1.99);

INSERT INTO productos (id_proovedor, nombre, descripcion, categoria, cantidad, precio)
VALUES (1, 'Buñuelos Rellenos', 'Buñuelos rellenos de queso', 'Ganadería', 40, 4.50);

SELECT * from productos; 