
-------------------------------------------------------------------------------------------
--
-- "NosaTenda"
--
-------------------------------------------------------------------------------------------
--
-- DELETE FROM role;
-- DELETE FROM empleado;
-- DELETE FROM empleadodata;
-- DELETE FROM cliente;
-- DELETE FROM clientedata;
-- DELETE FROM Localidad;
-- DELETE FROM Categoria;
-- DELETE FROM tienda;
-- DELETE FROM metodoenvio;
-- DELETE FROM producto;
-- DELETE FROM productodata;
-- DELETE FROM productohistorico;
-- DELETE FROM Empleado_Tienda;
-- DELETE FROM Cliente_Tienda;
-- DELETE FROM Tienda_Categoria;
-- DELETE FROM Producto_Categoria;
-- DELETE FROM reserva;
-- DELETE FROM compra_not_verified;
-- DELETE FROM compra;

--role

INSERT INTO role(role_nombre)
	VALUES ('ROLE_CLIENTE');
INSERT INTO role(role_nombre)
	VALUES ('ROLE_USUARIO');
INSERT INTO role(role_nombre)
	VALUES ('ROLE_ADMIN');
INSERT INTO role(role_nombre)
	VALUES ('ROLE_ADMIN_TIENDA');

--empleado (NOT_NULL: correo, password, role, activado)

INSERT INTO empleado(empleado_correo, empleado_password, empleado_role, empleado_activado, empleado_nif)
	VALUES ('admin@correo.es', 'pojo', 'ROLE_ADMIN', true, '47503312');
INSERT INTO empleado(empleado_correo, empleado_password, empleado_role, empleado_activado, empleado_nif)
	VALUES ('adminTienda1@correo.es', 'pojo', 'ROLE_ADMIN_TIENDA', true, '55512345');
INSERT INTO empleado(empleado_correo, empleado_password, empleado_role, empleado_activado, empleado_nif)
	VALUES ('adminTienda2@correo.es', 'pojo', 'ROLE_ADMIN_TIENDA', true, '55512346');
INSERT INTO empleado(empleado_correo, empleado_password, empleado_activado, empleado_nif)
	VALUES ('empleado1@correo.es', 'pojo', true, '00000001');
INSERT INTO empleado(empleado_correo, empleado_password, empleado_activado, empleado_nif)
	VALUES ('empleado2@correo.es', 'pojo', false, '00000002');
INSERT INTO empleado(empleado_correo, empleado_password, empleado_activado, empleado_nif)
	VALUES ('empleado3@correo.es', 'pojo', true, '00000003');
INSERT INTO empleado(empleado_correo, empleado_password, empleado_role, empleado_activado, empleado_nif)
	VALUES ('empleado4@correo.es', 'pojo', 'ROLE_USUARIO', false, '00000004');
INSERT INTO empleado(empleado_correo, empleado_password, empleado_role, empleado_activado, empleado_nif)
	VALUES ('adminTienda3@correo.es', 'pojo', 'ROLE_ADMIN_TIENDA', true, '55512347');
INSERT INTO empleado(empleado_correo, empleado_password, empleado_role, empleado_activado, empleado_nif)
	VALUES ('adminTienda4@correo.es', 'pojo', 'ROLE_ADMIN_TIENDA', true, '55512348');
INSERT INTO empleado(empleado_correo, empleado_password, empleado_role, empleado_activado, empleado_nif)
	VALUES ('adminTienda5@correo.es', 'pojo', 'ROLE_ADMIN_TIENDA', true, '55512349');
INSERT INTO empleado(empleado_correo, empleado_password, empleado_role, empleado_activado, empleado_nif)
	VALUES ('adminTienda6@correo.es', 'pojo', 'ROLE_ADMIN_TIENDA', true, '55512350');
INSERT INTO empleado(empleado_correo, empleado_password, empleado_role, empleado_activado, empleado_nif)
	VALUES ('adminTienda7@correo.es', 'pojo', 'ROLE_ADMIN_TIENDA', true, '55512351');
INSERT INTO empleado(empleado_correo, empleado_password, empleado_role, empleado_activado, empleado_nif)
	VALUES ('empleado5@correo.es', 'pojo', 'ROLE_USUARIO', true, '00000005');
INSERT INTO empleado(empleado_correo, empleado_password, empleado_role, empleado_activado, empleado_nif)
	VALUES ('empleado6@correo.es', 'pojo', 'ROLE_USUARIO', true, '00000006');
INSERT INTO empleado(empleado_correo, empleado_password, empleado_role, empleado_activado, empleado_nif)
	VALUES ('empleado7@correo.es', 'pojo', 'ROLE_USUARIO', true, '00000007');

--empleadodata
	
INSERT INTO empleadodata(empleadodata_id, empleadodata_nombre)
	VALUES (1, 'Administrador');
INSERT INTO empleadodata(empleadodata_id, empleadodata_nombre, empleadodata_apellidos, empleadodata_direccion, empleadodata_localidad)
	VALUES (2, 'Josefa', 'González Mango', 'Avenida Finisterre 17, 1C', 'A Coruña');
INSERT INTO empleadodata(empleadodata_id, empleadodata_nombre, empleadodata_apellidos, empleadodata_direccion, empleadodata_localidad)
	VALUES (3, 'Carmen', 'López Teixeiro', 'Calle Federico Tapia 5, 3A', 'A Coruña');
INSERT INTO empleadodata(empleadodata_id, empleadodata_nombre, empleadodata_apellidos, empleadodata_direccion, empleadodata_localidad, empleadodata_provincia, empleadodata_cp, empleadodata_phone1)
	VALUES (4, 'José Alberto', 'Rodríguez Albuín', 'Calle de la Barrera 3, 1A', 'A Coruña', 'A Coruña', '15003', '981485868');
INSERT INTO empleadodata(empleadodata_id, empleadodata_nombre, empleadodata_apellidos, empleadodata_direccion, empleadodata_localidad, empleadodata_provincia, empleadodata_cp, empleadodata_phone1)
	VALUES (5, 'Luis', 'Álvarez Rebollo', 'Calle Juan Flórez 101, 5E', 'A Coruña', 'A Coruña', '15010', '981567686');
INSERT INTO empleadodata(empleadodata_id, empleadodata_nombre, empleadodata_apellidos, empleadodata_direccion, empleadodata_localidad, empleadodata_provincia, empleadodata_cp, empleadodata_phone1)
	VALUES (6, 'Lisa', 'Vélez Montoya', 'Ronda de Outeiro 323, 8B', 'A Coruña', 'A Coruña', '15012', '981241312');
INSERT INTO empleadodata(empleadodata_id, empleadodata_nombre, empleadodata_apellidos, empleadodata_direccion, empleadodata_localidad, empleadodata_provincia, empleadodata_cp, empleadodata_phone1)
	VALUES (7, 'Laura', 'Gómez Iglesias', 'Avenida de Arteixo 10, 1B', 'A Coruña', 'A Coruña', '15002', '981292939');
INSERT INTO empleadodata(empleadodata_id, empleadodata_nombre, empleadodata_apellidos, empleadodata_direccion, empleadodata_localidad, empleadodata_provincia, empleadodata_cp, empleadodata_phone1)
	VALUES (8, 'Ramón', 'Méndez Cortés', 'Calle de la Torre 4, 4D', 'A Coruña', 'A Coruña', '15002', '981249584');
INSERT INTO empleadodata(empleadodata_id, empleadodata_nombre, empleadodata_apellidos, empleadodata_direccion, empleadodata_localidad, empleadodata_provincia, empleadodata_cp, empleadodata_phone1)
	VALUES (9, 'Alberto', 'Gómez Vázquez', 'Rúa da Senra 23, 2A', 'Santiago', 'A Coruña', '15701', '981235689');
INSERT INTO empleadodata(empleadodata_id, empleadodata_nombre, empleadodata_apellidos, empleadodata_direccion, empleadodata_localidad, empleadodata_provincia, empleadodata_cp, empleadodata_phone1)
	VALUES (10, 'Sara', 'De la Hoz Gotti', 'Rúa Alcalde Usero 70, 3A', 'Ferrol', 'A Coruña', '15403', '981374555');
INSERT INTO empleadodata(empleadodata_id, empleadodata_nombre, empleadodata_apellidos, empleadodata_direccion, empleadodata_localidad, empleadodata_provincia, empleadodata_cp, empleadodata_phone1)
	VALUES (11, 'Diana', 'Lamelas Val', 'Rúa de Castilla 80, 2B', 'Ferrol', 'A Coruña', '15404', '981293222');
INSERT INTO empleadodata(empleadodata_id, empleadodata_nombre, empleadodata_apellidos, empleadodata_direccion, empleadodata_localidad, empleadodata_provincia, empleadodata_cp, empleadodata_phone1)
	VALUES (12, 'Alejandro', 'Ruiz Conde', 'Rúa da Conga 2, 1º', 'Santiago', 'A Coruña', '15701', '981049500');
INSERT INTO empleadodata(empleadodata_id, empleadodata_nombre, empleadodata_apellidos, empleadodata_direccion, empleadodata_localidad, empleadodata_provincia, empleadodata_cp, empleadodata_phone1)
	VALUES (13, 'Marta', 'Val Meixeiro', 'Rúa Nova 24, 1A', 'Santiago', 'A Coruña', '15705', '981515565');
INSERT INTO empleadodata(empleadodata_id, empleadodata_nombre, empleadodata_apellidos, empleadodata_direccion, empleadodata_localidad, empleadodata_provincia, empleadodata_cp, empleadodata_phone1)
	VALUES (14, 'Carlos', 'Pérez Fonseca', 'Rúa Real 115, 2A', 'Ferrol', 'A Coruña', '15402', '981359762');
INSERT INTO empleadodata(empleadodata_id, empleadodata_nombre, empleadodata_apellidos, empleadodata_direccion, empleadodata_localidad, empleadodata_provincia, empleadodata_cp, empleadodata_phone1)
	VALUES (15, 'Cristina', 'Gastón Padín', 'Rúa do Preguntoiro 3, 1A', 'Santiago', 'A Coruña', '15782', '981552822');


--cliente (NOT_NULL: correo, password, role, activado)

INSERT INTO cliente(cliente_correo, cliente_password, cliente_activado)
	VALUES ('antonio@micorreo.es', 'pojo', true);
INSERT INTO cliente(cliente_correo, cliente_password, cliente_activado)
	VALUES ('carlos@micorreo.es', 'pojo', true);
INSERT INTO cliente(cliente_correo, cliente_password, cliente_activado, cliente_nif)
	VALUES ('gonzalez@email.com', 'pojo', true, '12345678');
INSERT INTO cliente(cliente_correo, cliente_password, cliente_activado, cliente_nif)
	VALUES ('jalvarez@yaaho.es', 'pojo', true, '87654321');
INSERT INTO cliente(cliente_correo, cliente_password, cliente_activado)
	VALUES ('mariapazos@hotamail.com', 'pojo', true);

--clientedata

INSERT INTO clientedata(clientedata_id)
	VALUES (1);
INSERT INTO clientedata(clientedata_id)
	VALUES (2);
INSERT INTO clientedata(clientedata_id, clientedata_nombre, clientedata_apellidos, clientedata_direccion, clientedata_localidad, clientedata_provincia, clientedata_cp, clientedata_phone1)
	VALUES (3, 'Jorge', 'Gonzalez Pons', 'Calle Manuel Murguía, 20', 'Gijón', 'Gijón', '16022', '984253545');
INSERT INTO clientedata(clientedata_id, clientedata_nombre, clientedata_apellidos, clientedata_direccion, clientedata_localidad, clientedata_provincia, clientedata_cp, clientedata_phone1)
	VALUES (4, 'Jessica', 'Álvarez Martínez', 'Calle de la serna, nº7 7d', 'León', 'León', '20143', '555123456');
INSERT INTO clientedata(clientedata_id)
	VALUES (5);
	
--localidad
	
INSERT INTO localidad(localidad_nombre)
	VALUES ('A Coruña');
INSERT INTO localidad(localidad_nombre)
	VALUES ('Lugo');
INSERT INTO localidad(localidad_nombre)
	VALUES ('Santiago');
INSERT INTO localidad(localidad_nombre)
	VALUES ('Vigo');
INSERT INTO localidad(localidad_nombre)
	VALUES ('Ourense');
INSERT INTO localidad(localidad_nombre)
	VALUES ('Ferrol');
INSERT INTO localidad(localidad_nombre)
	VALUES ('Pontevedra');

--categoria
	
INSERT INTO categoria(categoria_nombre)
	VALUES ('Belleza');
INSERT INTO categoria(categoria_nombre)
	VALUES ('Deportes');
INSERT INTO categoria(categoria_nombre)
	VALUES ('Electrodomésticos');
INSERT INTO categoria(categoria_nombre)
	VALUES ('Gourmet');
INSERT INTO categoria(categoria_nombre)
	VALUES ('Hogar');
INSERT INTO categoria(categoria_nombre)
	VALUES ('Libros');
INSERT INTO categoria(categoria_nombre)
	VALUES ('Manualidades');
INSERT INTO categoria(categoria_nombre)
	VALUES ('Mascotas');
INSERT INTO categoria(categoria_nombre)
	VALUES ('Moda');
INSERT INTO categoria(categoria_nombre)
	VALUES ('Tecnología');
INSERT INTO categoria(categoria_nombre)
	VALUES ('Zapatería');
INSERT INTO categoria(categoria_nombre)
	VALUES ('Otros');
	
--tienda

INSERT INTO tienda(tienda_nombre, tienda_direccion, localidad_id, tienda_nif, tienda_correo, tienda_web, tienda_phone1, tienda_imagen, tienda_lat, tienda_lon)
	VALUES ('Montana', 'Calle Corcubión, nº 23, bajo', 1, 'B70401419', 'info@montanastore.es', 'http://www.montanastore.es/', '881253271', 'http://i.imgur.com/HYZULIJ.jpg', 43.360988, -8.419587);
INSERT INTO tienda(tienda_nombre, tienda_direccion, localidad_id, tienda_nif, tienda_correo, tienda_web, tienda_phone1, tienda_imagen, tienda_lat, tienda_lon)
	VALUES ('Biomarket La Aldea', 'Av Finisterre 25', 1, '33596894', 'info@laaldeabiomarket.com', 'http://laaldea-supermercadoecologico.com/web/index.php', '981976165', 'http://i.imgur.com/ztySzSv.jpg', 43.365752, -8.410658);
INSERT INTO tienda(tienda_nombre, tienda_direccion, localidad_id, tienda_nif, tienda_correo, tienda_web, tienda_phone1, tienda_imagen, tienda_lat, tienda_lon)
	VALUES ('Handicap cero', 'Calle Payo Gómez 16, Bajo', 1, '24595833', 'handicapcero@handicapceromoda.com', 'http://www.handicapceromoda.com/', '981217177', 'http://i.imgur.com/12e3Jpy.jpg', 43.367341, -8.407206);
INSERT INTO tienda(tienda_nombre, tienda_direccion, localidad_id, tienda_nif, tienda_correo, tienda_web, tienda_phone1, tienda_imagen, tienda_lat, tienda_lon)
	VALUES ('OTTODISANPIETRO', 'Calle  Juan Flórez 41', 1, 'A15078728', 'info@ottodisanpietro.com', 'http://www.ottodisanpietro.com/', '981121123', 'http://i.imgur.com/487TbC5.jpg', 43.364131, -8.407966);
INSERT INTO tienda(tienda_nombre, tienda_direccion, localidad_id, tienda_nif, tienda_correo, tienda_web, tienda_phone1, tienda_imagen, tienda_lat, tienda_lon)
	VALUES ('Vintage & Coffee', 'Calle San Agustín, 32 (Pza. San Nicolás) ', 1, 'ES33296486F', 'contact@vintageandcoffee.com', 'http://www.vintageandcoffee.com/', '881872386', 'http://i.imgur.com/dDWWwfc.jpg', 43.371475, -8.398982);
INSERT INTO tienda(tienda_nombre, tienda_direccion, localidad_id, tienda_nif, tienda_correo, tienda_web, tienda_phone1, tienda_imagen, tienda_lat, tienda_lon)
	VALUES ('O Dolmen', 'Calle La Rosa, 30 Bajo', 3, '32411542B', 'info@odolmentallas.com', 'http://www.odolmentallas.com/', '981592949', 'http://i.imgur.com/Oj58nZp.png', 42.871528, -8.547694);
INSERT INTO tienda(tienda_nombre, tienda_direccion, localidad_id, tienda_nif, tienda_correo, tienda_web, tienda_phone1, tienda_imagen, tienda_lat, tienda_lon)
	VALUES ('Bichovintage', 'Plaza de Cervantes, 2 Bajo', 3, 'B70451182', 'info@bichovintage.com', 'https://www.bichovintage.com/es/', '981105719', 'http://i.imgur.com/1pmNof9.jpg', 42.881012, -8.542187);
INSERT INTO tienda(tienda_nombre, tienda_direccion, localidad_id, tienda_nif, tienda_correo, tienda_web, tienda_phone1, tienda_imagen, tienda_lat, tienda_lon)
	VALUES ('Mabe Moda', 'Calle Alfredo Brañas, 4', 3, 'J15564529', '', 'http://www.mabemoda.com/', '981590898', 'http://i.imgur.com/WccwznM.jpg', 42.875156, -8.547704);
INSERT INTO tienda(tienda_nombre, tienda_direccion, localidad_id, tienda_nif, tienda_correo, tienda_web, tienda_phone1, tienda_imagen, tienda_lat, tienda_lon)
	VALUES ('Central Librera', 'Calle Dolores nº2', 6, 'B70341458', 'centrallibrera@telefonica.net', 'http://www.centrallibrera.com/', '981352719', 'http://i.imgur.com/A1wUeOB.jpg', 43.484260, -8.233628);
INSERT INTO tienda(tienda_nombre, tienda_direccion, localidad_id, tienda_nif, tienda_correo, tienda_web, tienda_phone1, tienda_imagen, tienda_lat, tienda_lon)
	VALUES ('Metropolis Comics', 'Calle María 183', 6, '40765868', 'webmaster@metropoliscomics.net', 'http://www.metropoliscomics-tienda.net/', '981300416', 'http://i.imgur.com/pziFnNA.jpg', 43.482368, -8.238999);
INSERT INTO tienda(tienda_nombre, tienda_direccion, localidad_id, tienda_nif, tienda_correo, tienda_web, tienda_phone1, tienda_imagen, tienda_lat, tienda_lon)
	VALUES ('Cronopios Libros', 'Calle Fray Juan de Navarrete, 5, bajo', 7, 'B36188241', 'cronopioslibros@mundo-r.com', 'http://www.cronopioslibros.com/es/', '986103444', 'http://i.imgur.com/xVWe60u.jpg', 42.429340, -8.642657);
INSERT INTO tienda(tienda_nombre, tienda_direccion, localidad_id, tienda_nif, tienda_correo, tienda_web, tienda_phone1, tienda_imagen, tienda_lat, tienda_lon)
	VALUES ('Librería Madame Bovary', 'Calle Isidro Parga Pondal, 7 Bajo', 3, 'B70172986', 'info@madamebovarylibreria.com', 'http://www.madamebovarylibreria.com/', '881975643', 'http://i.imgur.com/XcA66QY.jpg', 42.863825, -8.544433);
INSERT INTO tienda(tienda_nombre, tienda_direccion, localidad_id, tienda_nif, tienda_correo, tienda_web, tienda_phone1, tienda_imagen, tienda_lat, tienda_lon)
	VALUES ('Librería Cartabón', 'Calle Urzaiz, 125', 4, '57895676', 'lcartabon@verial.es', 'http://www.librariacartabon.com/', '986372883', 'http://i.imgur.com/gyLbWxq.jpg', 42.231489, -8.708573);
	
--metodoenvio

INSERT INTO metodoenvio(metodoenvio_coste, metodoenvio_plazo, metodoenvio_descripcion, tienda_id)
	VALUES (4.99, 48, 'Entrega en domicilio en dos días laborables, envíos a través de SEUR', 1);
INSERT INTO metodoenvio(metodoenvio_coste, metodoenvio_plazo, metodoenvio_descripcion, tienda_id)
	VALUES (2.99, 72, 'Entrega en domicilio en tres días laborables o menos, a través de SECURIS. Sólo península y baleares', 5);
INSERT INTO metodoenvio(metodoenvio_coste, metodoenvio_plazo, metodoenvio_descripcion, tienda_id)
	VALUES (5.99, 24, 'Entrega urgente, en 24 horas si se realiza el pedido antes de las 17:00 horas. Envío a domicilio mediante Correos, sólo península y baleares', 6);
	
--producto

INSERT INTO producto(producto_precio, producto_stock, producto_tax_percentage, producto_fecha_puesta_venta, producto_fecha_retirada, tienda_id)
	VALUES (95, 100, 21, '2016-06-01 00:00:00', '2016-07-01 00:00:00', 1);
INSERT INTO producto(producto_precio, producto_stock, producto_tax_percentage, producto_fecha_puesta_venta, producto_fecha_retirada, tienda_id)
	VALUES (29.95, 50, 21, '2016-06-05 00:00:00', '2016-06-30 00:00:00', 1);
INSERT INTO producto(producto_precio, producto_stock, producto_tax_percentage, producto_fecha_puesta_venta, producto_fecha_retirada, tienda_id)
	VALUES (85, 5, 21, '2016-06-02 00:00:00', '2016-06-29 00:00:00', 1);
INSERT INTO producto(producto_precio, producto_stock, producto_tax_percentage, producto_fecha_puesta_venta, producto_fecha_retirada, tienda_id)
	VALUES (35, 0, 21, '2016-06-10 00:00:00', '2016-06-28 00:00:00', 1);
INSERT INTO producto(producto_precio, producto_stock, producto_tax_percentage, producto_fecha_puesta_venta, producto_fecha_retirada, tienda_id)
	VALUES (45, 10, 21, '2016-06-02 00:00:00', '2016-06-29 00:00:00', 1);
INSERT INTO producto(producto_precio, producto_stock, producto_tax_percentage, producto_fecha_puesta_venta, producto_fecha_retirada, tienda_id)
	VALUES (4.95, 73, 8, '2016-06-15 00:00:00', '2016-06-29 00:00:00', 2);
INSERT INTO producto(producto_precio, producto_stock, producto_tax_percentage, producto_fecha_puesta_venta, producto_fecha_retirada, tienda_id)
	VALUES (8.59, 24, 8, '2016-06-06 00:00:00', '2016-07-02 00:00:00', 2);
INSERT INTO producto(producto_precio, producto_stock, producto_tax_percentage, producto_fecha_puesta_venta, producto_fecha_retirada, tienda_id)
	VALUES (2.29, 115, 8, '2016-05-22 00:00:00', '2016-06-05 00:00:00', 2);
INSERT INTO producto(producto_precio, producto_stock, producto_tax_percentage, producto_fecha_puesta_venta, producto_fecha_retirada, tienda_id)
	VALUES (574, 3, 21, '2016-06-15 00:00:00', '2016-06-30 00:00:00', 3);
INSERT INTO producto(producto_precio, producto_stock, producto_tax_percentage, producto_fecha_puesta_venta, producto_fecha_retirada, tienda_id)
	VALUES (410, 5, 21, '2016-05-03 00:00:00', '2016-05-10 00:00:00', 3);
INSERT INTO producto(producto_precio, producto_stock, producto_tax_percentage, producto_fecha_puesta_venta, producto_fecha_retirada, tienda_id)
	VALUES (275, 3, 21, '2016-06-09 00:00:00', '2016-06-28 00:00:00', 4);
INSERT INTO producto(producto_precio, producto_stock, producto_tax_percentage, producto_fecha_puesta_venta, producto_fecha_retirada, tienda_id)
	VALUES (92, 7, 21, '2016-06-08 00:00:00', '2016-06-30 00:00:00', 4);
INSERT INTO producto(producto_precio, producto_stock, producto_tax_percentage, producto_fecha_puesta_venta, producto_fecha_retirada, tienda_id)
	VALUES (52, 22, 21, '2016-06-16 00:00:00', '2016-07-01 00:00:00', 6);
INSERT INTO producto(producto_precio, producto_stock, producto_tax_percentage, producto_fecha_puesta_venta, producto_fecha_retirada, tienda_id)
	VALUES (79, 12, 21, '2016-06-02 00:00:00', '2016-06-22 00:00:00', 6);
INSERT INTO producto(producto_precio, producto_stock, producto_tax_percentage, producto_fecha_puesta_venta, producto_fecha_retirada, tienda_id)
	VALUES (44.95, 17, 21, '2016-06-12 00:00:00', '2016-06-28 00:00:00', 7);
INSERT INTO producto(producto_precio, producto_stock, producto_tax_percentage, producto_fecha_puesta_venta, producto_fecha_retirada, tienda_id)
	VALUES (25.9, 2, 21, '2016-06-14 00:00:00', '2016-06-30 00:00:00', 7);
INSERT INTO producto(producto_precio, producto_stock, producto_tax_percentage, producto_fecha_puesta_venta, producto_fecha_retirada, tienda_id)
	VALUES (21.9, 30, 4, '2016-06-01 00:00:00', '2016-07-01 00:00:00', 9);
INSERT INTO producto(producto_precio, producto_stock, producto_tax_percentage, producto_fecha_puesta_venta, producto_fecha_retirada, tienda_id)
	VALUES (25, 14, 4, '2016-06-07 00:00:00', '2016-06-29 00:00:00', 9);
INSERT INTO producto(producto_precio, producto_stock, producto_tax_percentage, producto_fecha_puesta_venta, producto_fecha_retirada, tienda_id)
	VALUES (12, 6, 4, '2016-06-12 00:00:00', '2016-06-29 00:00:00', 9);
INSERT INTO producto(producto_precio, producto_stock, producto_tax_percentage, producto_fecha_puesta_venta, producto_fecha_retirada, tienda_id)
	VALUES (15.9, 0, 21, '2016-06-11 00:00:00', '2016-06-30 00:00:00', 10);
INSERT INTO producto(producto_precio, producto_stock, producto_tax_percentage, producto_fecha_puesta_venta, producto_fecha_retirada, tienda_id)
	VALUES (15, 12, 4, '2016-06-17 00:00:00', '2016-06-28 00:00:00', 10);
INSERT INTO producto(producto_precio, producto_stock, producto_tax_percentage, producto_fecha_puesta_venta, producto_fecha_retirada, tienda_id)
	VALUES (21.9, 5, 4, '2016-06-10 00:00:00', '2016-07-02 00:00:00', 11);
INSERT INTO producto(producto_precio, producto_stock, producto_tax_percentage, producto_fecha_puesta_venta, producto_fecha_retirada, tienda_id)
	VALUES (12.9, 0, 4, '2016-06-11 00:00:00', '2016-06-29 00:00:00', 11);


--productodata

INSERT INTO productodata(productodata_id, productodata_nombre, productodata_descripcion, productodata_imagen)
	VALUES (1, 'Zapatillas ASICS GEL-LYTE SPEED', 'Recogida en tienda. Disponible en Talla: 41, 41 1/2, 42 1/2, 43, 43 1/2 y 44.', 'http://i.imgur.com/wZAG4gr.jpg');
INSERT INTO productodata(productodata_id, productodata_nombre, productodata_descripcion, productodata_imagen)
	VALUES (2, 'Camiseta ONITSUKA TIGER burgundy', 'Envío desde 6,45€. Disponible en Talla: S, M y L.', 'http://i.imgur.com/wGVBxgb.jpg');
INSERT INTO productodata(productodata_id, productodata_nombre, productodata_descripcion, productodata_imagen)
	VALUES (3, 'Zapatillas LE COQ SPORTIF LCS R900', 'El LCS R900 se inspira del emblemático modelo LCS R1000 y se inscribe directamente en la tendencia de los diseños running que están causando furor. Apuesta por una comodidad a toda prueba, gracias a su ligereza y a su sistema de amortiguamiento Dynactif.', 'http://i.imgur.com/fk69ahf.jpg');
INSERT INTO productodata(productodata_id, productodata_nombre, productodata_descripcion, productodata_imagen)
	VALUES (4, 'Mochila LE COQ SPORTIF red', 'La mochila Inspired se caracteriza por sus líneas estilizadas y modernas. El contraste en tinta de la parte inferior realza el efecto de dinamismo que aporta el ancho asimétrico de la espalda. Se presenta en versiones monocroma, tricolor o estampada (con un motivo de inspiración deportiva) y su bolsillo principal se cierra con un botón a presión imantado e invisible.', 'http://i.imgur.com/n7EdVk3.jpg');
INSERT INTO productodata(productodata_id, productodata_nombre, productodata_descripcion, productodata_imagen)
	VALUES (5, 'Pantalon LE COQ SPORTIF gris', 'Con su corte estrecho, el pantalón Slim Unbrushed se impone como un modelo imparable. Está elaborado con un muletón no perchado que combina algodón y poliéster (perfecto para mitad de temporada) y se encuentra disponible en una amplia gama de colores para que puedas escoger tu preferido.', 'http://i.imgur.com/x41J4T4.jpg');
INSERT INTO productodata(productodata_id, productodata_nombre, productodata_descripcion, productodata_imagen)
	VALUES (6, 'Abeto de Chocolate con Leche 40gr.', 'Ingredientes: Pasta de cacao, Azúcar de caña moreno, manteca de cacao, leche entera en polvo, vainilla en polvo. Sin gluten, Vegetarianos, Sin trigo', 'http://i.imgur.com/Suy84ag.jpg');
INSERT INTO productodata(productodata_id, productodata_nombre, productodata_descripcion, productodata_imagen)
	VALUES (7, 'Turrón Duro 200g. Lluch', 'Ingredientes: Almendra*, azúcar de caña*, miel*, clara de huevo*. *De Agricultura ecológico Sin gluten, Vegetarianos, Sin trigo, Sin lactosa', 'http://i.imgur.com/1264GM8.jpg');
INSERT INTO productodata(productodata_id, productodata_nombre, productodata_descripcion, productodata_imagen)
	VALUES (8, 'Té Earl Grey 20u. Artemis', 'Ingredientes: Té Negro*, Esencia de Bergamota* *= Agricultura Ecológica. Sin gluten, Vegetarianos, Sin azúcar, Sin trigo, Sin lactosa', 'http://i.imgur.com/POVeD8z.jpg');
INSERT INTO productodata(productodata_id, productodata_nombre, productodata_descripcion, productodata_imagen)
	VALUES (9, 'Vestido Sirenas Nº21', 'COMPOSICIÓN: 96% Viscosa, 4% Elastán.', 'http://i.imgur.com/4LOdWmm.jpg');
INSERT INTO productodata(productodata_id, productodata_nombre, productodata_descripcion, productodata_imagen)
	VALUES (10, 'Zapato cordones piel Churchs', 'COMPOSICIÓN: 100% Piel.', 'http://i.imgur.com/xK5D9fq.jpg');
INSERT INTO productodata(productodata_id, productodata_nombre, productodata_descripcion, productodata_imagen)
	VALUES (11, 'Saint Laurent Billetero', 'Billetero de piel sin monedero de la serie Monogramme, 100% Piel', 'http://i.imgur.com/6ywwOdK.png');
INSERT INTO productodata(productodata_id, productodata_nombre, productodata_descripcion, productodata_imagen)
	VALUES (12, 'T by Alexander Wang vestido', 'Tank dress para transparencias. Ideal para arrugar con jerseys cortos encima de leggins.', 'http://i.imgur.com/fKAGfjC.png');
INSERT INTO productodata(productodata_id, productodata_nombre, productodata_descripcion, productodata_imagen)
	VALUES (13, 'Pichi Animal-Print mujer talla grande', '95% Poliester / 5% Elastán. Cuello chimenea, aplique de cremallera. Color único', 'http://i.imgur.com/lOStycG.jpg');
INSERT INTO productodata(productodata_id, productodata_nombre, productodata_descripcion, productodata_imagen)
	VALUES (14, 'Sueter fantasía mujer talla grande', '100% Acrílico, largo: 70cm', 'http://i.imgur.com/qPAid59.jpg');
INSERT INTO productodata(productodata_id, productodata_nombre, productodata_descripcion, productodata_imagen)
	VALUES (15, 'Vestido Vintage 80s Rosa&Negro Talla S', 'Vestido años 80 color rosa con estampado en negro brillo. Torso ajustado con elástico en la espalda que permite adaptarse mejor al cuerpo. Falda con vuelo. Incorpora una enagua de tul color negro para darle volumen. Totalmente forrado. El estilo del vestido permite cualquier ancho de cadera.', 'http://i.imgur.com/Unj12kv.jpg');
INSERT INTO productodata(productodata_id, productodata_nombre, productodata_descripcion, productodata_imagen)
	VALUES (16, 'Blazer Vintage 80s Rayas Talla M', 'Blazer de los años 80 estampado con rayas negras y grises. Escote con solapas en pico y dos falsos bolsillos laterales. Más largo por delante que por detrás, estilo chaleco. Totalmente forrado.', 'http://i.imgur.com/NTbzbu2.jpg');
INSERT INTO productodata(productodata_id, productodata_nombre, productodata_descripcion, productodata_imagen)
	VALUES (17, 'El Regreso del Catón', '¿Qué pueden tener en común la Ruta de la Seda, las alcantarillas de Estambul, Marco Polo, Mongolia y Tierra Santa? Eso es lo que los protagonistas de El último Catón, Ottavia Salina y Farag Boswell, tendrán que averiguar poniendo de nuevo sus vidas en peligro para resolver un misterio que arranca en el siglo I de nuestra era.', 'http://i.imgur.com/tdQ5RAW.gif');
INSERT INTO productodata(productodata_id, productodata_nombre, productodata_descripcion, productodata_imagen)
	VALUES (18, 'Historia de la Artillería de Costa española. Siglos XVIII XIX XX Y XXI', 'La Artillería de Costa ha dejado huella en la historia de España y el artillado de alguna de sus piezas aun nos muestra su prolongada presencia en baluartes de nuestro litoral. Hacer un largo recorrido de cinco siglos por su periplo geoestratégico resulta complejo, por ello se ha buscado la síntesis como referencia de esta obra llevada adelante por investigadores especializados.', 'http://i.imgur.com/y0ZF9Si.gif');
INSERT INTO productodata(productodata_id, productodata_nombre, productodata_descripcion, productodata_imagen)
	VALUES (19, 'Ferrol y su comarca. El patrimonio industrial', 'Juan J. Burgoa hace en esta obra un amplio recorrido sobre el Patrimonio Industrial de Ferrol y su comarca, producto de una industrialización nacida el siglo XVIII con motivo de las Reales Obras que dieron lugar al Arsenal y Astilleros de Ferrol, originando la creación de una serie de industrias derivadas de estas obras, extendidas por la amplia comarca de Ferrol, desde Narón a Mugardos y desde As Pontes a Neda.', 'http://i.imgur.com/3jkaH1g.gif');
INSERT INTO productodata(productodata_id, productodata_nombre, productodata_descripcion, productodata_imagen)
	VALUES (20, 'Star Wars Taza 3D C-3PO de 10cm', 'Taza de alta calidad. Licencia oficial. Tamaño: 12 x 12 x 10 cm. Material: plástico', 'http://i.imgur.com/ju7iKqM.jpg');
INSERT INTO productodata(productodata_id, productodata_nombre, productodata_descripcion, productodata_imagen)
	VALUES (21, 'El Caballero de los Siete Reinos', 'Con tono ligero y un desarrollo ejemplar, El caballero de los Siete Reinos retoma algunos de los temas centrales de Canción de hielo y fuego, como la lealtad, la justicia y la reflexión sobre el poder, pero desde la perspectiva cotidiana de la gente común y de su incidencia en escenarios desprovistos de fasto y oropel.', 'http://i.imgur.com/qwW80lC.jpg');
INSERT INTO productodata(productodata_id, productodata_nombre, productodata_descripcion, productodata_imagen)
	VALUES (22, 'El regreso del Catón', '¿Qué pueden tener en común la Ruta de la Seda, las alcantarillas de Estambul, Marco Polo, Mongolia y Tierra Santa? Eso es lo que los protagonistas de El último Catón, Ottavia Salina y Farag Boswell, tendrán que averiguar poniendo de nuevo sus vidas en peligro para resolver un misterio que arranca en el siglo I de nuestra era.', 'http://i.imgur.com/tdQ5RAW.gif');
INSERT INTO productodata(productodata_id, productodata_nombre, productodata_descripcion, productodata_imagen)
	VALUES (23, '¡Elecciones!', 'Nueva aventura de los personajes de Francisco Ibáñez que, sin duda, nos ofrecerán la cara más divertida de la política en época de elecciones.', 'http://i.imgur.com/Kmfg84U.jpg');
	
	

--productohistorico

INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_tax_percentage, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (1, 1, 95, 100, 21, '2016-06-01 00:00:00', '2016-07-01 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_tax_percentage, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (2, 1, 35, 10, 21, '2016-05-01 00:00:00', '2016-05-15 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_tax_percentage, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (2, 2, 29.95, 50, 21, '2016-06-05 00:00:00', '2016-06-30 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_tax_percentage, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (3, 1, 85, 8, 21, '2016-06-02 00:00:00', '2016-06-29 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_tax_percentage, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (4, 1, 50, 3, 21, '2016-05-02 00:00:00', '2016-05-12 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_tax_percentage, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (4, 2, 35, 3, 21, '2016-06-10 00:00:00', '2016-06-28 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_tax_percentage, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (5, 1, 45, 10, 21, '2016-06-02 00:00:00', '2016-06-29 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_tax_percentage, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (6, 1, 4.95, 75, 8, '2016-06-15 00:00:00', '2016-06-29 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_tax_percentage, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (7, 1, 8.59, 25, 8, '2016-06-06 00:00:00', '2016-07-02 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_tax_percentage, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (8, 1, 2.29, 115, 8, '2016-05-22 00:00:00', '2016-06-05 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_tax_percentage, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (9, 1, 700, 6, 21, '2016-04-28 00:00:00', '2016-05-05 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_tax_percentage, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (9, 2, 650, 5, 21, '2016-05-20 00:00:00', '2016-06-01 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_tax_percentage, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (9, 3, 574, 4, 21, '2016-06-15 00:00:00', '2016-06-30 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_tax_percentage, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (10, 1, 410, 5, 21, '2016-05-03 00:00:00', '2016-05-10 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_tax_percentage, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (11, 1, 275, 5, 21, '2016-06-09 00:00:00', '2016-06-28 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_tax_percentage, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (12, 1, 110, 5, 21, '2016-05-02 00:00:00', '2016-05-25 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_tax_percentage, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (12, 2, 92, 7, 21, '2016-06-08 00:00:00', '2016-06-30 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_tax_percentage, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (13, 1, 115, 30, 21, '2016-03-02 00:00:00', '2016-03-15 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_tax_percentage, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (13, 2, 85, 25, 21, '2016-04-26 00:00:00', '2016-05-02 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_tax_percentage, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (13, 3, 65, 15, 21, '2016-05-15 00:00:00', '2016-05-22 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_tax_percentage, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (13, 4, 52, 25, 21, '2016-06-16 00:00:00', '2016-07-01 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_tax_percentage, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (14, 1, 89, 15, 21, '2016-05-02 00:00:00', '2016-05-10 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_tax_percentage, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (14, 2, 79, 14, 21, '2016-06-02 00:00:00', '2016-06-22 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_tax_percentage, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (15, 1, 44.95, 17, 21, '2016-06-12 00:00:00', '2016-06-28 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_tax_percentage, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (16, 1, 25.9, 3, 21, '2016-06-14 00:00:00', '2016-06-30 00:00:00');	
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_tax_percentage, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (17, 1, 21.9, 30, 4, '2016-06-01 00:00:00', '2016-07-01 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_tax_percentage, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (18, 1, 25, 15, 4, '2016-06-07 00:00:00', '2016-06-29 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_tax_percentage, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (19, 1, 12, 6, 4, '2016-06-12 00:00:00', '2016-06-29 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_tax_percentage, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (20, 1, 15.9, 5, 21, '2016-06-11 00:00:00', '2016-06-30 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_tax_percentage, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (21, 1, 15, 12, 4, '2016-06-17 00:00:00', '2016-06-28 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_tax_percentage, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (22, 1, 21.9, 7, 4, '2016-06-10 00:00:00', '2016-07-02 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_tax_percentage, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (23, 1, 15.9, 10, 4, '2016-05-05 00:00:00', '2016-05-12 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_tax_percentage, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (23, 2, 12.9, 5, 4, '2016-06-11 00:00:00', '2016-06-29 00:00:00');

	
--empleado_tienda

INSERT INTO empleado_tienda(empleado_id, tienda_id)
	VALUES (2, 1);
INSERT INTO empleado_tienda(empleado_id, tienda_id)
	VALUES (2, 3);
INSERT INTO empleado_tienda(empleado_id, tienda_id)
	VALUES (2, 4);
INSERT INTO empleado_tienda(empleado_id, tienda_id)
	VALUES (3, 2);
INSERT INTO empleado_tienda(empleado_id, tienda_id)
	VALUES (4, 1);
INSERT INTO empleado_tienda(empleado_id, tienda_id)
	VALUES (5, 1);
INSERT INTO empleado_tienda(empleado_id, tienda_id)
	VALUES (6, 2);
INSERT INTO empleado_tienda(empleado_id, tienda_id)
	VALUES (7, 2);
INSERT INTO empleado_tienda(empleado_id, tienda_id)
	VALUES (8, 5);
INSERT INTO empleado_tienda(empleado_id, tienda_id)
	VALUES (8, 7);
INSERT INTO empleado_tienda(empleado_id, tienda_id)
	VALUES (9, 6);
INSERT INTO empleado_tienda(empleado_id, tienda_id)
	VALUES (9, 8);
INSERT INTO empleado_tienda(empleado_id, tienda_id)
	VALUES (10, 9);
INSERT INTO empleado_tienda(empleado_id, tienda_id)
	VALUES (11, 10);
INSERT INTO empleado_tienda(empleado_id, tienda_id)
	VALUES (12, 11);
INSERT INTO empleado_tienda(empleado_id, tienda_id)
	VALUES (12, 12);
INSERT INTO empleado_tienda(empleado_id, tienda_id)
	VALUES (12, 13);
INSERT INTO empleado_tienda(empleado_id, tienda_id)
	VALUES (13, 6);
INSERT INTO empleado_tienda(empleado_id, tienda_id)
	VALUES (13, 8);
INSERT INTO empleado_tienda(empleado_id, tienda_id)
	VALUES (14, 10);
INSERT INTO empleado_tienda(empleado_id, tienda_id)
	VALUES (15, 11);
INSERT INTO empleado_tienda(empleado_id, tienda_id)
	VALUES (15, 12);

	
--cliente_tienda

INSERT INTO cliente_tienda(cliente_id, tienda_id)
	VALUES (1, 1);
INSERT INTO cliente_tienda(cliente_id, tienda_id)
	VALUES (1, 2);
INSERT INTO cliente_tienda(cliente_id, tienda_id)
	VALUES (1, 6);
INSERT INTO cliente_tienda(cliente_id, tienda_id)
	VALUES (3, 3);
INSERT INTO cliente_tienda(cliente_id, tienda_id)
	VALUES (3, 4);
INSERT INTO cliente_tienda(cliente_id, tienda_id)
	VALUES (3, 8);
INSERT INTO cliente_tienda(cliente_id, tienda_id)
	VALUES (4, 9);
INSERT INTO cliente_tienda(cliente_id, tienda_id)
	VALUES (4, 10);
INSERT INTO cliente_tienda(cliente_id, tienda_id)
	VALUES (4, 11);
INSERT INTO cliente_tienda(cliente_id, tienda_id)
	VALUES (4, 13);

--tienda_categoria

INSERT INTO tienda_categoria(tienda_id, categoria_id)
	VALUES (1, 2);
INSERT INTO tienda_categoria(tienda_id, categoria_id)
	VALUES (1, 9);
INSERT INTO tienda_categoria(tienda_id, categoria_id)
	VALUES (1, 11);
INSERT INTO tienda_categoria(tienda_id, categoria_id)
	VALUES (2, 1);
INSERT INTO tienda_categoria(tienda_id, categoria_id)
	VALUES (2, 4);
INSERT INTO tienda_categoria(tienda_id, categoria_id)
	VALUES (2, 5);
INSERT INTO tienda_categoria(tienda_id, categoria_id)
	VALUES (2, 8);
INSERT INTO tienda_categoria(tienda_id, categoria_id)
	VALUES (2, 12);
INSERT INTO tienda_categoria(tienda_id, categoria_id)
	VALUES (3, 9);
INSERT INTO tienda_categoria(tienda_id, categoria_id)
	VALUES (3, 11);
INSERT INTO tienda_categoria(tienda_id, categoria_id)
	VALUES (4, 9);
INSERT INTO tienda_categoria(tienda_id, categoria_id)
	VALUES (4, 11);
INSERT INTO tienda_categoria(tienda_id, categoria_id)
	VALUES (4, 12);
INSERT INTO tienda_categoria(tienda_id, categoria_id)
	VALUES (5, 9);
INSERT INTO tienda_categoria(tienda_id, categoria_id)
	VALUES (6, 9);
INSERT INTO tienda_categoria(tienda_id, categoria_id)
	VALUES (7, 9);
INSERT INTO tienda_categoria(tienda_id, categoria_id)
	VALUES (7, 12);
INSERT INTO tienda_categoria(tienda_id, categoria_id)
	VALUES (8, 9);
INSERT INTO tienda_categoria(tienda_id, categoria_id)
	VALUES (8, 11);
INSERT INTO tienda_categoria(tienda_id, categoria_id)
	VALUES (8, 12);
INSERT INTO tienda_categoria(tienda_id, categoria_id)
	VALUES (9, 6);
INSERT INTO tienda_categoria(tienda_id, categoria_id)
	VALUES (10, 6);
INSERT INTO tienda_categoria(tienda_id, categoria_id)
	VALUES (10, 12);
INSERT INTO tienda_categoria(tienda_id, categoria_id)
	VALUES (11, 6);
INSERT INTO tienda_categoria(tienda_id, categoria_id)
	VALUES (12, 6);
INSERT INTO tienda_categoria(tienda_id, categoria_id)
	VALUES (13, 6);
	
--producto_categoria

INSERT INTO producto_categoria(producto_id, categoria_id)
	VALUES (1, 2);
INSERT INTO producto_categoria(producto_id, categoria_id)
	VALUES (1, 11);
INSERT INTO producto_categoria(producto_id, categoria_id)
	VALUES (2, 2);
INSERT INTO producto_categoria(producto_id, categoria_id)
	VALUES (2, 9);
INSERT INTO producto_categoria(producto_id, categoria_id)
	VALUES (3, 2);
INSERT INTO producto_categoria(producto_id, categoria_id)
	VALUES (3, 11);
INSERT INTO producto_categoria(producto_id, categoria_id)
	VALUES (4, 2);
INSERT INTO producto_categoria(producto_id, categoria_id)
	VALUES (5, 2);
INSERT INTO producto_categoria(producto_id, categoria_id)
	VALUES (5, 9);
INSERT INTO producto_categoria(producto_id, categoria_id)
	VALUES (6, 4);
INSERT INTO producto_categoria(producto_id, categoria_id)
	VALUES (7, 4);
INSERT INTO producto_categoria(producto_id, categoria_id)
	VALUES (8, 4);
INSERT INTO producto_categoria(producto_id, categoria_id)
	VALUES (9, 9);
INSERT INTO producto_categoria(producto_id, categoria_id)
	VALUES (10, 11);
INSERT INTO producto_categoria(producto_id, categoria_id)
	VALUES (11, 12);
INSERT INTO producto_categoria(producto_id, categoria_id)
	VALUES (12, 9);
INSERT INTO producto_categoria(producto_id, categoria_id)
	VALUES (13, 9);
INSERT INTO producto_categoria(producto_id, categoria_id)
	VALUES (14, 9);
INSERT INTO producto_categoria(producto_id, categoria_id)
	VALUES (15, 9);
INSERT INTO producto_categoria(producto_id, categoria_id)
	VALUES (16, 9);
INSERT INTO producto_categoria(producto_id, categoria_id)
	VALUES (17, 6);
INSERT INTO producto_categoria(producto_id, categoria_id)
	VALUES (18, 6);
INSERT INTO producto_categoria(producto_id, categoria_id)
	VALUES (19, 6);
INSERT INTO producto_categoria(producto_id, categoria_id)
	VALUES (20, 12);
INSERT INTO producto_categoria(producto_id, categoria_id)
	VALUES (21, 6);
INSERT INTO producto_categoria(producto_id, categoria_id)
	VALUES (22, 6);
INSERT INTO producto_categoria(producto_id, categoria_id)
	VALUES (23, 6);


--reserva

INSERT INTO reserva(reserva_unidades, reserva_estado, reserva_fecha, reserva_precio, reserva_total, reserva_tax_percentage, producto_id, cliente_id)
	VALUES( 3, 'ENTREGADA', '2016-06-09 04:05:06', 85, 255, 21, 3, 1);
INSERT INTO reserva(reserva_unidades, reserva_estado, reserva_fecha, reserva_precio, reserva_total, reserva_tax_percentage, producto_id, cliente_id)
	VALUES( 2, 'ENTREGADA', '2016-06-11 04:05:06', 35, 70, 21, 4, 2);
INSERT INTO reserva(reserva_unidades, reserva_estado, reserva_fecha, reserva_precio, reserva_total, reserva_tax_percentage, producto_id, cliente_id)
	VALUES( 2, 'ENTREGADA', '2016-06-17 04:05:06', 4.95, 9.9, 8, 6, 4);
INSERT INTO reserva(reserva_unidades, reserva_estado, reserva_fecha, reserva_precio, reserva_total, reserva_tax_percentage, producto_id, cliente_id)
	VALUES( 1, 'ENTREGADA', '2016-05-02 04:05:06', 700, 700, 21, 9, 1);
INSERT INTO reserva(reserva_unidades, reserva_estado, reserva_fecha, reserva_precio, reserva_total, reserva_tax_percentage, producto_id, cliente_id)
	VALUES( 1, 'ENTREGADA', '2016-06-17 04:05:06', 275, 275, 21, 11, 4);
INSERT INTO reserva(reserva_unidades, reserva_estado, reserva_fecha, reserva_precio, reserva_total, reserva_tax_percentage, producto_id, cliente_id)
	VALUES( 1, 'ENTREGADA', '2016-05-20 04:05:06', 110, 110, 21, 12, 1);
INSERT INTO reserva(reserva_unidades, reserva_estado, reserva_fecha, reserva_precio, reserva_total, reserva_tax_percentage, producto_id, cliente_id)
	VALUES( 2, 'ENTREGADA', '2016-03-08 04:05:06', 115, 230, 21, 13, 2);
INSERT INTO reserva(reserva_unidades, reserva_estado, reserva_fecha, reserva_precio, reserva_total, reserva_tax_percentage, producto_id, cliente_id)
	VALUES( 3, 'ENTREGADA', '2016-04-28 04:05:06', 85, 255, 21, 13, 3);
INSERT INTO reserva(reserva_unidades, reserva_estado, reserva_fecha, reserva_precio, reserva_total, reserva_tax_percentage, producto_id, cliente_id)
	VALUES( 1, 'ENTREGADA', '2016-05-05 04:05:06', 89, 89, 21, 14, 1);
INSERT INTO reserva(reserva_unidades, reserva_estado, reserva_fecha, reserva_precio, reserva_total, reserva_tax_percentage, producto_id, cliente_id)
	VALUES( 2, 'ENTREGADA', '2016-06-13 04:05:06', 79, 158, 21, 14, 2);
INSERT INTO reserva(reserva_unidades, reserva_estado, reserva_fecha, reserva_precio, reserva_total, reserva_tax_percentage, producto_id, cliente_id)
	VALUES( 2, 'ENTREGADA', '2016-06-12 04:05:06', 21.9, 43.8, 4, 22, 5);
INSERT INTO reserva(reserva_unidades, reserva_estado, reserva_fecha, reserva_precio, reserva_total, reserva_tax_percentage, producto_id, cliente_id)
	VALUES( 2, 'ENTREGADA', '2016-05-08 04:05:06', 15.9, 31.8, 4, 23, 3);
INSERT INTO reserva(reserva_unidades, reserva_estado, reserva_fecha, reserva_precio, reserva_total, reserva_tax_percentage, producto_id, cliente_id)
	VALUES( 5, 'ENTREGADA', '2016-06-14 04:05:06', 12.9, 64.5, 4, 23, 5);
	

--compra

INSERT INTO compra(compra_unidades, compra_estado_recogida, compra_fecha, compra_precio, compra_total, compra_id_paypal, compra_estado_paypal, compra_forma_pago, compra_currency, compra_tax_percentage, producto_id, cliente_id)
	VALUES( 1, 'RECOGIDA', '2016-06-13 20:02:54', 35, 35, 'PAY-101', 'approved', 'paypal', 'EUR', 21, 4, 3);
INSERT INTO compra(compra_unidades, compra_estado_recogida, compra_fecha, compra_precio, compra_total, compra_id_paypal, compra_estado_paypal, compra_forma_pago, compra_currency, compra_tax_percentage, producto_id, cliente_id)
	VALUES( 1, 'RECOGIDA', '2016-06-11 20:02:54', 8.59, 8.59, 'PAY-102', 'approved', 'paypal', 'EUR', 8, 7, 5);	
INSERT INTO compra(compra_unidades, compra_estado_recogida, compra_fecha, compra_precio, compra_total, compra_id_paypal, compra_estado_paypal, compra_forma_pago, compra_currency, compra_tax_percentage, producto_id, cliente_id)
	VALUES( 1, 'RECOGIDA', '2016-05-28 20:02:54', 650, 650, 'PAY-103', 'approved', 'paypal', 'EUR', 21, 9, 2);	
INSERT INTO compra(compra_unidades, compra_estado_recogida, compra_fecha, compra_precio, compra_total, compra_id_paypal, compra_estado_paypal, compra_forma_pago, compra_currency, compra_tax_percentage, producto_id, cliente_id)
	VALUES( 1, 'RECOGIDA', '2016-06-16 20:02:54', 574, 574, 'PAY-104', 'approved', 'paypal', 'EUR', 21, 9, 3);
INSERT INTO compra(compra_unidades, compra_estado_recogida, compra_fecha, compra_precio, compra_total, compra_id_paypal, compra_estado_paypal, compra_forma_pago, compra_currency, compra_tax_percentage, producto_id, cliente_id)
	VALUES( 1, 'RECOGIDA', '2016-06-12 20:02:54', 275, 275, 'PAY-105', 'approved', 'paypal', 'EUR', 21, 11, 5);
INSERT INTO compra(compra_unidades, compra_estado_recogida, compra_fecha, compra_precio, compra_total, compra_id_paypal, compra_estado_paypal, compra_forma_pago, compra_currency, compra_tax_percentage, producto_id, cliente_id)
	VALUES( 4, 'RECOGIDA', '2016-05-17 20:02:54', 65, 260, 'PAY-106', 'approved', 'paypal', 'EUR', 21, 13, 4);
INSERT INTO compra(compra_unidades, compra_estado_recogida, compra_fecha, compra_precio, compra_total, compra_id_paypal, compra_estado_paypal, compra_forma_pago, compra_currency, compra_tax_percentage, producto_id, cliente_id)
	VALUES( 3, 'RECOGIDA', '2016-06-17 20:02:54', 52, 156, 'PAY-107', 'approved', 'paypal', 'EUR', 21, 13, 5);
INSERT INTO compra(compra_unidades, compra_estado_recogida, compra_fecha, compra_precio, compra_total, compra_id_paypal, compra_estado_paypal, compra_forma_pago, compra_currency, compra_tax_percentage, producto_id, cliente_id)
	VALUES( 1, 'RECOGIDA', '2016-06-16 20:02:54', 25.9, 25.9, 'PAY-108', 'approved', 'paypal', 'EUR', 21, 16, 3);
INSERT INTO compra(compra_unidades, compra_estado_recogida, compra_fecha, compra_precio, compra_total, compra_id_paypal, compra_estado_paypal, compra_forma_pago, compra_currency, compra_tax_percentage, producto_id, cliente_id)
	VALUES( 1, 'RECOGIDA', '2016-06-13 20:02:54', 25, 25, 'PAY-109', 'approved', 'paypal', 'EUR', 4, 18, 4);
INSERT INTO compra(compra_unidades, compra_estado_recogida, compra_fecha, compra_precio, compra_total, compra_id_paypal, compra_estado_paypal, compra_forma_pago, compra_currency, compra_tax_percentage, producto_id, cliente_id)
	VALUES( 5, 'RECOGIDA', '2016-06-14 20:02:54', 15.9, 79.5, 'PAY-110', 'approved', 'paypal', 'EUR', 21, 20, 5);
	

--compra_not_verified
	
