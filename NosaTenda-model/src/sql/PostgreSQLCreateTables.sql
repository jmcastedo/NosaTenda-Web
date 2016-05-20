
-------------------------------------------------------------------------------------------
--
-- "NosaTenda"
--
-------------------------------------------------------------------------------------------
--
-- -----------------------------------------------------------------------------
-- Drop tables. NOTE: before dropping a table (when re-executing the script),
-- the tables having columns acting as foreign keys of the table to be dropped,
-- must be dropped first (otherwise, the corresponding checks on those tables
-- could not be done).

DROP TABLE IF EXISTS Compra;
DROP TABLE IF EXISTS Compra_not_verified;
DROP TABLE IF EXISTS Reserva;
DROP TABLE IF EXISTS Producto_Categoria;
DROP TABLE IF EXISTS Tienda_Categoria;
DROP TABLE IF EXISTS Cliente_Tienda;
DROP TABLE IF EXISTS Empleado_Tienda;
DROP TABLE IF EXISTS ProductoHistorico;
DROP TABLE IF EXISTS ProductoData;
DROP TABLE IF EXISTS Producto;
DROP TABLE IF EXISTS MetodoEnvio;
DROP TABLE IF EXISTS Tienda;
DROP TABLE IF EXISTS Categoria;
DROP TABLE IF EXISTS Localidad;
DROP TABLE IF EXISTS ClienteData;
DROP TABLE IF EXISTS Cliente;
DROP TABLE IF EXISTS EmpleadoData;
DROP TABLE IF EXISTS Empleado;
DROP TABLE IF EXISTS Role;

DROP SEQUENCE IF EXISTS compra_compra_id_seq;
DROP SEQUENCE IF EXISTS compra_not_verified_compra_not_verified_id_seq;
DROP SEQUENCE IF EXISTS reserva_reserva_id_seq;
DROP SEQUENCE IF EXISTS producto_producto_id_seq;
DROP SEQUENCE IF EXISTS metodoenvio_metodoenvio_id_seq;
DROP SEQUENCE IF EXISTS tienda_tienda_id_seq;
DROP SEQUENCE IF EXISTS categoria_categoria_id_seq;
DROP SEQUENCE IF EXISTS localidad_localidad_id_seq;
DROP SEQUENCE IF EXISTS cliente_cliente_id_seq;
DROP SEQUENCE IF EXISTS clientedata_cliente_id_seq;
DROP SEQUENCE IF EXISTS empleado_empleado_id_seq;
DROP SEQUENCE IF EXISTS empleadodata_empleado_id_seq;

---------------------------- Role ----------------------------

CREATE TABLE Role (
	role_nombre varchar(100) PRIMARY KEY,
	UNIQUE(role_nombre)
);

---------------------------- Empleado ----------------------------

CREATE TABLE Empleado (
	empleado_id serial PRIMARY KEY,
	empleado_correo varchar(100) NOT NULL,
	empleado_password varchar(12) NOT NULL,
	empleado_role varchar(100) NOT NULL DEFAULT 'ROLE_USUARIO',
	empleado_activado boolean NOT NULL,
	empleado_nif varchar(16),
	UNIQUE(empleado_id),
	UNIQUE(empleado_correo)
);

---------------------------- EmpleadoData ----------------------------

CREATE TABLE EmpleadoData (
	empleadodata_id integer PRIMARY KEY,
	empleadodata_nombre varchar(100),
	empleadodata_apellidos varchar(200),
	empleadodata_direccion varchar(200),
	empleadodata_localidad varchar(45),
	empleadodata_provincia varchar(45),
	empleadodata_cp varchar(16),
	empleadodata_phone1 varchar(32),
	empleadodata_phone2 varchar(32),
	FOREIGN KEY (empleadodata_id) REFERENCES empleado(empleado_id),
	UNIQUE(empleadodata_id)
);

---------------------------- Cliente ----------------------------

CREATE TABLE Cliente (
	cliente_id serial PRIMARY KEY,
	cliente_correo varchar(100) NOT NULL,
	cliente_password varchar(12) NOT NULL,
	cliente_role varchar(100) NOT NULL DEFAULT 'ROLE_CLIENTE',
	cliente_activado boolean NOT NULL,
	cliente_nif varchar(16),
	UNIQUE(cliente_id),
	UNIQUE(cliente_correo)
);

---------------------------- ClienteData ----------------------------

CREATE TABLE ClienteData (
	clientedata_id integer PRIMARY KEY,
	clientedata_nombre varchar(100),
	clientedata_apellidos varchar(200),
	clientedata_direccion varchar(200),
	clientedata_localidad varchar(45),
	clientedata_provincia varchar(45),
	clientedata_cp varchar(16),
	clientedata_phone1 varchar(32),
	clientedata_phone2 varchar(32),
	FOREIGN KEY (clientedata_id) REFERENCES cliente(cliente_id),
	UNIQUE(clientedata_id)
);

---------------------------- Localidad -------------------------

CREATE TABLE Localidad (
	localidad_id serial PRIMARY KEY,
	localidad_nombre varchar(100) NOT NULL,
	UNIQUE(localidad_id),
	UNIQUE(localidad_nombre)
);

---------------------------- Categoria -------------------------

CREATE TABLE Categoria (
	categoria_id serial PRIMARY KEY,
	categoria_nombre varchar(100) NOT NULL,
	UNIQUE(categoria_id),
	UNIQUE(categoria_nombre)
);

---------------------------- Tienda ----------------------------

CREATE TABLE Tienda (
	tienda_id serial PRIMARY KEY,
	tienda_nombre varchar(100) NOT NULL,
	tienda_direccion varchar(200) NOT NULL,
	tienda_provincia varchar(45),
	tienda_cp varchar(16),
	tienda_nif varchar(16) NOT NULL,
	tienda_correo varchar(100) NOT NULL,
	tienda_web varchar(200),
	tienda_phone1 varchar(32) NOT NULL,
	tienda_phone2 varchar(32),
	tienda_fax varchar(32),
	tienda_imagen varchar(450),
	tienda_lat NUMERIC,
	tienda_lon NUMERIC,
	localidad_id integer NOT NULL,
	FOREIGN KEY (localidad_id) REFERENCES localidad(localidad_id),
	UNIQUE(tienda_id)
);

---------------------------- MetodoEnvio -------------------------

CREATE TABLE MetodoEnvio(
	metodoenvio_id serial PRIMARY KEY,
	metodoenvio_coste NUMERIC,
	metodoenvio_plazo INTEGER,
	metodoenvio_descripcion varchar(450),
	tienda_id integer NOT NULL,
	FOREIGN KEY (tienda_id) REFERENCES tienda(tienda_id),
	UNIQUE(metodoEnvio_id)
);

---------------------------- Producto ----------------------------

CREATE TABLE Producto (
	producto_id serial PRIMARY KEY,
	producto_precio NUMERIC NOT NULL,
	producto_precio_noiva NUMERIC,
	producto_stock INTEGER NOT NULL,
	producto_tax_amount NUMERIC,
	producto_tax_percentage NUMERIC,
	producto_fecha_puesta_venta TIMESTAMP,
	producto_fecha_retirada TIMESTAMP,
	tienda_id integer NOT NULL,
	FOREIGN KEY (tienda_id) REFERENCES tienda(tienda_id),
	UNIQUE(producto_id)
);

---------------------------- ProductoData ----------------------------

CREATE TABLE ProductoData (
	productodata_id integer PRIMARY KEY,
	productodata_nombre varchar(100),
	productodata_descripcion varchar(450),
	productodata_imagen varchar(450),
	FOREIGN KEY (productodata_id) REFERENCES producto(producto_id),
	UNIQUE(productodata_id)
);

---------------------------- ProductoHistorico ----------------------------

CREATE TABLE ProductoHistorico (
	productohistorico_id integer NOT NULL,
	productohistorico_version integer NOT NULL,
	productohistorico_fecha_puesta_venta TIMESTAMP NOT NULL,
	productohistorico_fecha_retirada TIMESTAMP NOT NULL,
	productohistorico_precio NUMERIC NOT NULL,
	productohistorico_precio_noiva NUMERIC,
	productohistorico_stock_inicial INTEGER NOT NULL,
	productohistorico_tax_amount NUMERIC,
	productohistorico_tax_percentage NUMERIC,
	FOREIGN KEY (productohistorico_id) REFERENCES producto(producto_id),
	PRIMARY KEY (productohistorico_id, productohistorico_version)
);

---------------------------- Cliente_Tienda -------------------------

CREATE TABLE Cliente_Tienda (
	cliente_id integer,
	tienda_id integer,
	FOREIGN KEY (cliente_id) REFERENCES cliente(cliente_id),
	FOREIGN KEY (tienda_id) REFERENCES tienda(tienda_id),
	PRIMARY KEY (cliente_id, tienda_id)
);

---------------------------- Empleado_Tienda -------------------------

CREATE TABLE Empleado_Tienda (
	empleado_id integer,
	tienda_id integer,
	FOREIGN KEY (empleado_id) REFERENCES empleado(empleado_id),
	FOREIGN KEY (tienda_id) REFERENCES tienda(tienda_id),
	PRIMARY KEY (empleado_id, tienda_id)
);

---------------------------- Tienda_Categoria -------------------------

CREATE TABLE Tienda_Categoria (
	categoria_id integer,
	tienda_id integer,
	FOREIGN KEY (categoria_id) REFERENCES categoria(categoria_id),
	FOREIGN KEY (tienda_id) REFERENCES tienda(tienda_id),
	PRIMARY KEY (categoria_id, tienda_id)
);

---------------------------- Producto_Categoria -------------------------

CREATE TABLE Producto_Categoria (
	categoria_id integer,
	producto_id integer,
	FOREIGN KEY (categoria_id) REFERENCES categoria(categoria_id),
	FOREIGN KEY (producto_id) REFERENCES producto(producto_id),
	PRIMARY KEY (categoria_id, producto_id)
);

---------------------------- Reserva ----------------------------

CREATE TABLE Reserva (
	reserva_id serial PRIMARY KEY,
	reserva_unidades integer NOT NULL,
	reserva_estado VARCHAR(10) NOT NULL,
	reserva_fecha TIMESTAMP NOT NULL,
	reserva_fecha_limite TIMESTAMP,
	reserva_precio_noiva NUMERIC,
	reserva_precio NUMERIC NOT NULL,
	reserva_total NUMERIC NOT NULL,
	reserva_tax_amount NUMERIC,
	reserva_tax_percentage NUMERIC,
	producto_id integer NOT NULL,
	cliente_id integer NOT NULL,
	FOREIGN KEY (producto_id) REFERENCES producto(producto_id),
	FOREIGN KEY (cliente_id) REFERENCES cliente(cliente_id),
	UNIQUE(reserva_id)
);

---------------------------- Compra_not_verified -----------------

CREATE TABLE Compra_not_verified (
	compra_not_verified_id serial PRIMARY KEY,
	compra_not_verified_unidades integer NOT NULL,
	compra_not_verified_fecha TIMESTAMP NOT NULL,
	compra_not_verified_precio_noiva NUMERIC,
	compra_not_verified_precio NUMERIC NOT NULL,
	compra_not_verified_total NUMERIC NOT NULL,
	compra_not_verified_id_paypal varchar(45) NOT NULL,
	compra_not_verified_forma_pago varchar(45) NOT NULL,
	compra_not_verified_currency varchar(3) NOT NULL,
	compra_not_verified_tax_amount NUMERIC,
	compra_not_verified_tax_percentage NUMERIC,
	producto_id integer NOT NULL,
	cliente_id integer NOT NULL,
	FOREIGN KEY (producto_id) REFERENCES producto(producto_id),
	FOREIGN KEY (cliente_id) REFERENCES cliente(cliente_id),
	UNIQUE(compra_not_verified_id),
	UNIQUE(compra_not_verified_id_paypal)
);

---------------------------- Compra ------------------------------
	
CREATE TABLE Compra (
	compra_id serial PRIMARY KEY,
	compra_unidades integer NOT NULL,
	compra_estado_recogida VARCHAR(11) NOT NULL,
	compra_fecha TIMESTAMP NOT NULL,
	compra_precio_noiva NUMERIC,
	compra_precio NUMERIC NOT NULL,
	compra_total NUMERIC NOT NULL,
	compra_id_paypal varchar(45) NOT NULL,
	compra_estado_paypal varchar(8) NOT NULL,
	compra_forma_pago varchar(45) NOT NULL,
	compra_currency varchar(3) NOT NULL,
	compra_tax_amount NUMERIC,
	compra_tax_percentage NUMERIC,
	compra_num_factura serial NOT NULL,
	producto_id integer NOT NULL,
	cliente_id integer NOT NULL,
	FOREIGN KEY (producto_id) REFERENCES producto(producto_id),
	FOREIGN KEY (cliente_id) REFERENCES cliente(cliente_id),
	UNIQUE(compra_id),
	UNIQUE(compra_id_paypal),
	UNIQUE(compra_num_factura)
);

---------------------------- Permissions ----------------------------

GRANT USAGE, SELECT ON SEQUENCE compra_compra_id_seq TO pojo;
GRANT USAGE, SELECT ON SEQUENCE compra_compra_num_factura_seq TO pojo;
GRANT USAGE, SELECT ON SEQUENCE compra_not_verified_compra_not_verified_id_seq TO pojo;
GRANT USAGE, SELECT ON SEQUENCE reserva_reserva_id_seq TO pojo;
GRANT USAGE, SELECT ON SEQUENCE producto_producto_id_seq TO pojo;
GRANT USAGE, SELECT ON SEQUENCE metodoenvio_metodoenvio_id_seq TO pojo;
GRANT USAGE, SELECT ON SEQUENCE tienda_tienda_id_seq TO pojo;
GRANT USAGE, SELECT ON SEQUENCE categoria_categoria_id_seq TO pojo;
GRANT USAGE, SELECT ON SEQUENCE localidad_localidad_id_seq TO pojo;
GRANT USAGE, SELECT ON SEQUENCE cliente_cliente_id_seq TO pojo;
GRANT USAGE, SELECT ON SEQUENCE empleado_empleado_id_seq TO pojo;

GRANT ALL ON TABLE role TO pojo;
GRANT ALL ON TABLE empleado TO pojo;
GRANT ALL ON TABLE cliente TO pojo;
GRANT ALL ON TABLE empleadodata TO pojo;
GRANT ALL ON TABLE clientedata TO pojo;
GRANT ALL ON TABLE localidad TO pojo;
GRANT ALL ON TABLE categoria TO pojo;
GRANT ALL ON TABLE tienda TO pojo;
GRANT ALL ON TABLE metodoenvio TO pojo;
GRANT ALL ON TABLE producto TO pojo;
GRANT ALL ON TABLE productodata TO pojo;
GRANT ALL ON TABLE productohistorico TO pojo;
GRANT ALL ON TABLE empleado_tienda TO pojo;
GRANT ALL ON TABLE cliente_tienda TO pojo;
GRANT ALL ON TABLE tienda_categoria TO pojo;
GRANT ALL ON TABLE producto_categoria TO pojo;
GRANT ALL ON TABLE reserva TO pojo;
GRANT ALL ON TABLE compra_not_verified TO pojo;
GRANT ALL ON TABLE compra TO pojo;