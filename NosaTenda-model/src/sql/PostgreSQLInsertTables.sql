
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
	VALUES ('adminTienda1@correo.es', 'pojo', 'ROLE_ADMIN_TIENDA', true, '12345678');
INSERT INTO empleado(empleado_correo, empleado_password, empleado_role, empleado_activado, empleado_nif)
	VALUES ('adminTienda2@correo.es', 'pojo', 'ROLE_ADMIN_TIENDA', true, '12345678');
INSERT INTO empleado(empleado_correo, empleado_password, empleado_activado, empleado_nif)
	VALUES ('empleado1@correo.es', 'pojo', true, '00000001');
INSERT INTO empleado(empleado_correo, empleado_password, empleado_activado, empleado_nif)
	VALUES ('empleado2@correo.es', 'pojo', false, '00000002');
INSERT INTO empleado(empleado_correo, empleado_password, empleado_activado, empleado_nif)
	VALUES ('empleado3@correo.es', 'pojo', true, '00000003');
INSERT INTO empleado(empleado_correo, empleado_password, empleado_role, empleado_activado, empleado_nif)
	VALUES ('empleado4@correo.es', 'pojo', 'ROLE_USUARIO', false, '00000004');

--empleadodata
	
INSERT INTO empleadodata(empleadodata_id, empleadodata_nombre)
	VALUES (1, 'Ernst Stavro Blofeld');
INSERT INTO empleadodata(empleadodata_id, empleadodata_nombre, empleadodata_apellidos, empleadodata_direccion, empleadodata_localidad)
	VALUES (2, 'Kim Jon Un', 'norcoreano', 'Corea la Buena', 'Pyonyang');
INSERT INTO empleadodata(empleadodata_id, empleadodata_nombre, empleadodata_apellidos, empleadodata_direccion, empleadodata_localidad)
	VALUES (3, 'Mao', 'Tusun', 'China la Mala', 'Beijing');
INSERT INTO empleadodata(empleadodata_id, empleadodata_nombre, empleadodata_apellidos, empleadodata_direccion, empleadodata_localidad, empleadodata_provincia, empleadodata_cp, empleadodata_phone1)
	VALUES (4, 'Pringao', 'Currante', 'plaza rojiza, nº1', 'Albacete', 'Albacete', '02001', '983485868');
INSERT INTO empleadodata(empleadodata_id, empleadodata_nombre, empleadodata_apellidos, empleadodata_direccion, empleadodata_localidad, empleadodata_provincia, empleadodata_cp, empleadodata_phone1)
	VALUES (5, 'Matao', 'Esclavizado', 'calle cualquiera, nº13', 'Huelva', 'Huelva', '21001', '984567686');
INSERT INTO empleadodata(empleadodata_id, empleadodata_nombre, empleadodata_apellidos, empleadodata_direccion, empleadodata_localidad, empleadodata_provincia, empleadodata_cp, empleadodata_phone1)
	VALUES (6, 'Explotao', 'Sin Seguro', 'calle dondesea, nº33', 'Málaga', 'Málaga', '29001', '95949584');
INSERT INTO empleadodata(empleadodata_id, empleadodata_nombre)
	VALUES (7, 'Reventao');

--cliente (NOT_NULL: correo, password, role, activado)

INSERT INTO cliente(cliente_correo, cliente_password, cliente_activado, cliente_nif)
	VALUES ('antonio@micorreo.es', 'pojo', true, '47503312');
INSERT INTO cliente(cliente_correo, cliente_password, cliente_activado, cliente_nif)
	VALUES ('quetzalenruta@correocaliente.com', 'pojo', true, '12345678');
INSERT INTO cliente(cliente_correo, cliente_password, cliente_activado, cliente_nif)
	VALUES ('correocorreocorreo@depruebadeprueba.org', 'pojo', true, '00000001');
INSERT INTO cliente(cliente_correo, cliente_password, cliente_activado)
	VALUES ('spectrasecreta@abajomi6.uk', 'pojo', true);
INSERT INTO cliente(cliente_correo, cliente_password, cliente_activado)
	VALUES ('norcoreano@twitter.es', 'pojo', true);

--clientedata

INSERT INTO clientedata(clientedata_id, clientedata_nombre, clientedata_apellidos, clientedata_direccion, clientedata_localidad, clientedata_provincia, clientedata_cp, clientedata_phone1)
	VALUES (1, 'Antonio', 'El del Bombo', 'calle de la piruleta, nº77', 'Gijón', 'Gijón', '16022', '984253545');
INSERT INTO clientedata(clientedata_id, clientedata_nombre, clientedata_apellidos, clientedata_direccion, clientedata_localidad, clientedata_provincia, clientedata_cp, clientedata_phone1)
	VALUES (2, 'José Miguel', 'de la Cuadra Salcedo', 'calle de la serna, nº7 7Z', 'León', 'León', '20143', '555123456');
INSERT INTO clientedata(clientedata_id, clientedata_nombre, clientedata_apellidos, clientedata_direccion, clientedata_localidad, clientedata_provincia, clientedata_cp, clientedata_phone1)
	VALUES (3, 'Grigory', 'Rasputín', 'plaza Roja, nº1', 'Moscú', 'Moscú', '56493', '881345678');
INSERT INTO clientedata(clientedata_id, clientedata_nombre, clientedata_localidad, clientedata_phone1)
	VALUES (4, 'Dimitir', 'Kursk', '881345678');
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

--categoria
	
INSERT INTO categoria(categoria_nombre)
	VALUES ('Libros');
INSERT INTO categoria(categoria_nombre)
	VALUES ('Electrodomésticos');
INSERT INTO categoria(categoria_nombre)
	VALUES ('Gourmet');
INSERT INTO categoria(categoria_nombre)
	VALUES ('Deportes');
INSERT INTO categoria(categoria_nombre)
	VALUES ('Tecnología');
INSERT INTO categoria(categoria_nombre)
	VALUES ('Hogar');
INSERT INTO categoria(categoria_nombre)
	VALUES ('Moda');
INSERT INTO categoria(categoria_nombre)
	VALUES ('Belleza');
INSERT INTO categoria(categoria_nombre)
	VALUES ('Mascotas');
INSERT INTO categoria(categoria_nombre)
	VALUES ('Manualidades');
INSERT INTO categoria(categoria_nombre)
	VALUES ('Otros');
	
--tienda
	
INSERT INTO tienda(tienda_nombre, tienda_direccion, localidad_id, tienda_nif, tienda_correo, tienda_web, tienda_phone1, tienda_imagen, tienda_lat, tienda_lon)
	VALUES ('La Campus', 'Calle del Ozelote, nº 7', 1, '01234567', 'rinconjose@mail.com', 'http://www.rinconjose.es', '660567098', 'http://i.imgur.com/c0Wnr7c.jpg', 43.369271, -8.419628);
INSERT INTO tienda(tienda_nombre, tienda_direccion, localidad_id, tienda_nif, tienda_correo, tienda_web, tienda_phone1, tienda_imagen, tienda_lat, tienda_lon)
	VALUES ('Pulpería Riazor', 'Calle de la Comadreja, nº 32 bajo', 1, '33596894', 'antoniosbar@mail.es', 'http://www.bardeantonio.es', '600432987', 'http://i.imgur.com/a4qcgnZ.jpg', 43.369673, -8.416556);
INSERT INTO tienda(tienda_nombre, tienda_direccion, localidad_id, tienda_nif, tienda_correo, tienda_web, tienda_phone1, tienda_imagen, tienda_lat, tienda_lon)
	VALUES ('Manger', 'Calle de la Fantasía, nº 12.5', 2, '24595833', 'pezcomics@hayoo.com', 'http://www.pezu-comics.com', '632000111', 'http://i.imgur.com/a5l0sWj.jpg', 43.008106, -7.558725);
INSERT INTO tienda(tienda_nombre, tienda_direccion, localidad_id, tienda_provincia, tienda_cp, tienda_nif, tienda_correo, tienda_web, tienda_phone1, tienda_imagen, tienda_lat, tienda_lon)
	VALUES ('Farggi', 'Calle del Carnaval, nº 132', 3, 'Río de Janeiro', '99987', '98764923', 'atenciongarrapina@gim.es', 'http://gimnasiogarrapina.es', '450300600', 'http://i.imgur.com/uBsoWBJ.jpg', 42.877461, -8.546027);
INSERT INTO tienda(tienda_nombre, tienda_direccion, localidad_id, tienda_nif, tienda_correo, tienda_phone1, tienda_imagen, tienda_lat, tienda_lon)
	VALUES ('La Internacional', 'Calle de Debajo de Mi Casa, nº 21', 2, '40765868', 'atencionalcliente@superesquina.ga', '981203040', 'http://i.imgur.com/SwsmOt4.jpg', 43.007479, -7.559272);
INSERT INTO tienda(tienda_nombre, tienda_direccion, localidad_id, tienda_nif, tienda_correo, tienda_web, tienda_phone1, tienda_imagen, tienda_lat, tienda_lon)
	VALUES ('Sieiro', 'Calle del Ozelote, nº 1', 1, '01234568', 'sieiro@mail.com', 'http://www.sierio.es', '600742353', 'http://i.imgur.com/nuBgvZ2.jpg', 43.369737, -8.416077);
INSERT INTO tienda(tienda_nombre, tienda_direccion, localidad_id, tienda_nif, tienda_correo, tienda_web, tienda_phone1, tienda_imagen, tienda_lat, tienda_lon)
	VALUES ('Gimnasio Garrapucho', 'Calle del Paseo Marítimo, nº 120', 1, '01245676', 'gimgarra@mail.com', 'http://www.garrapucho.es', '690233233', 'http://i.imgur.com/g2aAcDr.jpg', 43.370349, -8.416848);
INSERT INTO tienda(tienda_nombre, tienda_direccion, localidad_id, tienda_nif, tienda_correo, tienda_web, tienda_phone1, tienda_imagen, tienda_lat, tienda_lon)
	VALUES ('Fosco', 'Calle Real, nº 11', 1, '67895676', 'fosco@mail.com', 'http://www.fosco.es', '610112233', 'http://i.imgur.com/sljr3MW.jpg', 43.370150, -8.399504);
	
--metodoenvio

INSERT INTO metodoenvio(metodoenvio_coste, metodoenvio_plazo, metodoenvio_descripcion, tienda_id)
	VALUES (4.99, 48, 'Entrega en domicilio en dos días laborables, envíos a través de SEUR', 1);
INSERT INTO metodoenvio(metodoenvio_coste, metodoenvio_plazo, metodoenvio_descripcion, tienda_id)
	VALUES (2.99, 72, 'Entrega en domicilio en tres días laborables o menos, a través de SECURIS. Sólo península y baleares', 5);
INSERT INTO metodoenvio(metodoenvio_coste, metodoenvio_plazo, metodoenvio_descripcion, tienda_id)
	VALUES (5.99, 24, 'Entrega urgente, en 24 horas si se realiza el pedido antes de las 17:00 horas. Envío a domicilio mediante Correos, sólo península y baleares', 6);
	
--producto

INSERT INTO producto(producto_precio, producto_stock, producto_fecha_puesta_venta, producto_fecha_retirada, tienda_id)
	VALUES (7.99, 100, '2015-06-2 00:00:00', '2015-07-27 00:00:00', 1);
INSERT INTO producto(producto_precio, producto_stock, producto_tax_percentage, producto_fecha_puesta_venta, producto_fecha_retirada, tienda_id)
	VALUES (5.95, 1000, 0, '2015-06-2 00:00:00', '2015-07-29 00:00:00', 1);
INSERT INTO producto(producto_precio, producto_stock, producto_tax_percentage, producto_fecha_puesta_venta, producto_fecha_retirada, tienda_id)
	VALUES (12.95, 10, 0, '2015-06-2 00:00:00', '2015-07-28 00:00:00', 2);
INSERT INTO producto(producto_precio, producto_stock, producto_tax_percentage, producto_fecha_puesta_venta, producto_fecha_retirada, tienda_id)
	VALUES (7, 5, 0, '2015-06-2 00:00:00', '2015-07-31 00:00:00', 2);
INSERT INTO producto(producto_precio, producto_stock, producto_tax_percentage, producto_fecha_puesta_venta, producto_fecha_retirada, tienda_id)
	VALUES (21, 100, 0, '2015-06-2 00:00:00', '2015-07-30 00:00:00', 2);
INSERT INTO producto(producto_precio, producto_stock, producto_tax_percentage, producto_fecha_puesta_venta, producto_fecha_retirada, tienda_id)
	VALUES (2.99, 100, 0, '2015-07-1 00:00:00', '2015-07-30 00:00:00', 3);
INSERT INTO producto(producto_precio, producto_stock, producto_fecha_puesta_venta, producto_fecha_retirada, tienda_id)
	VALUES (13, 100, '2015-07-1 00:00:00', '2015-07-31 00:00:00', 4);
INSERT INTO producto(producto_precio, producto_stock, producto_fecha_puesta_venta, producto_fecha_retirada, tienda_id)
	VALUES (0.99, 100, '2015-06-2 00:00:00', '2015-06-23 00:00:00', 4);
INSERT INTO producto(producto_precio, producto_stock, producto_fecha_puesta_venta, producto_fecha_retirada, tienda_id)
	VALUES (120, 100, '2015-06-2 00:00:00', '2015-06-23 00:00:00', 4);
INSERT INTO producto(producto_precio, producto_stock, producto_fecha_puesta_venta, producto_fecha_retirada, tienda_id)
	VALUES (1599.99, 100, '2015-06-2 00:00:00', '2015-06-23 00:00:00', 4);
INSERT INTO producto(producto_precio, producto_stock, producto_fecha_puesta_venta, producto_fecha_retirada, tienda_id)
	VALUES (0.5, 100, '2015-06-2 00:00:00', '2015-06-23 00:00:00', 4);
INSERT INTO producto(producto_precio, producto_stock, producto_fecha_puesta_venta, producto_fecha_retirada, tienda_id)
	VALUES (17, 100, '2015-06-2 00:00:00', '2015-06-23 00:00:00', 4);
INSERT INTO producto(producto_precio, producto_stock, producto_fecha_puesta_venta, producto_fecha_retirada, tienda_id)
	VALUES (9.99, 100, '2015-06-2 00:00:00', '2015-06-23 00:00:00', 4);
INSERT INTO producto(producto_precio, producto_stock, producto_fecha_puesta_venta, producto_fecha_retirada, tienda_id)
	VALUES (25, 0, '2015-06-2 00:00:00', '2015-06-23 00:00:00', 4);
INSERT INTO producto(producto_precio, producto_stock, producto_fecha_puesta_venta, producto_fecha_retirada, tienda_id)
	VALUES (25, 1, '2015-06-2 00:00:00', '2015-06-23 00:00:00', 4);
INSERT INTO producto(producto_precio, producto_stock, producto_fecha_puesta_venta, producto_fecha_retirada, tienda_id)
	VALUES (25, 2, '2015-06-2 00:00:00', '2015-06-23 00:00:00', 4);
INSERT INTO producto(producto_precio, producto_stock, producto_tax_percentage, producto_fecha_puesta_venta, producto_fecha_retirada, tienda_id)
	VALUES (25, 3, 6.5, '2015-06-2 00:00:00', '2015-06-23 00:00:00', 4);
INSERT INTO producto(producto_precio, producto_stock, producto_tax_percentage, producto_fecha_puesta_venta, producto_fecha_retirada, tienda_id)
	VALUES (20199.9, 100, 21, '2015-06-2 00:00:00', '2015-06-23 00:00:00', 4);

--productodata

INSERT INTO productodata(productodata_id, productodata_nombre, productodata_descripcion, productodata_imagen)
	VALUES (1, 'El Viejo Pancho', 'Poesías de José Alonso y Trelles', 'http://i.imgur.com/NEmhnrG.jpg');
INSERT INTO productodata(productodata_id, productodata_nombre, productodata_descripcion, productodata_imagen)
	VALUES (2, 'Matrimonio de sabuesos', 'Novela de Agatha Christie, incluye el Caso de la Perla Rosa y Encerrona al Rey', 'http://i.imgur.com/fPunXJG.jpg');
INSERT INTO productodata(productodata_id, productodata_nombre, productodata_descripcion, productodata_imagen)
	VALUES (3, 'Kappa', 'A novel of exquisite precision - Spectator. By Ryunosuke Akutagawa', 'http://i.imgur.com/ymZ5sDj.jpg');
INSERT INTO productodata(productodata_id, productodata_nombre, productodata_descripcion, productodata_imagen)
	VALUES (4, 'La Ilíada', 'Adaptación del poema épico de Homero', 'http://i.imgur.com/UgAQWhd.jpg');
INSERT INTO productodata(productodata_id, productodata_nombre, productodata_descripcion, productodata_imagen)
	VALUES (5, 'LA DIVINA COMEDIA', 'Clásicos de bolsillo, Dante Alighieri', 'http://i.imgur.com/s55ry0D.jpg');
INSERT INTO productodata(productodata_id, productodata_nombre, productodata_descripcion, productodata_imagen)
	VALUES (6, 'Android', 'Con un buho en miniatura', 'http://i.imgur.com/nPRofzZ.jpg');
INSERT INTO productodata(productodata_id, productodata_nombre, productodata_descripcion, productodata_imagen)
	VALUES (7, 'Avecilla', 'Un clásico de la literatura española de Leopoldo Alas', 'http://i.imgur.com/VJhhW0M.jpg');
INSERT INTO productodata(productodata_id, productodata_nombre, productodata_descripcion, productodata_imagen)
	VALUES (8, 'Un perro', 'Porque no pueden faltar un perro o un gato', 'http://i.imgur.com/jDXtlrL.jpg');
INSERT INTO productodata(productodata_id, productodata_nombre, productodata_descripcion, productodata_imagen)
	VALUES (9, 'Dos perros', 'Lo siento, no tenía fotos de gatos a mano', 'http://i.imgur.com/OAvoM5Z.jpg');
INSERT INTO productodata(productodata_id, productodata_nombre, productodata_descripcion, productodata_imagen)
	VALUES (10, 'Y otro perro', 'Esto se empieza a desmadrar...', 'http://i.imgur.com/mAs6nqa.jpg');
INSERT INTO productodata(productodata_id, productodata_nombre, productodata_descripcion, productodata_imagen)
	VALUES (11, 'Ala, otro más', '¿Estamos en una perrera o qué?', 'http://i.imgur.com/AwwkgU3.jpg');
INSERT INTO productodata(productodata_id, productodata_nombre, productodata_descripcion, productodata_imagen)
	VALUES (12, 'Venga, el último', 'No más perros, lo prometo. Que aunque sean pequeños y un poco adorables, ya se están poniendo cansinos.', 'http://i.imgur.com/pwrbvES.jpg');
INSERT INTO productodata(productodata_id, productodata_nombre, productodata_descripcion, productodata_imagen)
	VALUES (13, 'Troll & Luck', 'Foto rara, a  ver cómo la procesa', 'http://i.imgur.com/J56eDsh.jpg');
INSERT INTO productodata(productodata_id, productodata_nombre, productodata_descripcion, productodata_imagen)
	VALUES (14, 'Perro caché 1', 'Sí, ya sé que dije que no más perros, pero es para probar la caché de imágenes', 'http://i.imgur.com/jDXtlrL.jpg');
INSERT INTO productodata(productodata_id, productodata_nombre, productodata_descripcion, productodata_imagen)
	VALUES (15, 'Perro caché 2', 'Sí, ya sé que dije que no más perros, pero es para probar la caché de imágenes', 'http://i.imgur.com/jDXtlrL.jpg');
INSERT INTO productodata(productodata_id, productodata_nombre, productodata_descripcion, productodata_imagen)
	VALUES (16, 'Perro caché 3', 'Sí, ya sé que dije que no más perros, pero es para probar la caché de imágenes', 'http://i.imgur.com/jDXtlrL.jpg');
INSERT INTO productodata(productodata_id, productodata_nombre, productodata_descripcion, productodata_imagen)
	VALUES (17, 'Perro caché 4', 'Sí, ya sé que dije que no más perros, pero es para probar la caché de imágenes', 'http://i.imgur.com/jDXtlrL.jpg');
INSERT INTO productodata(productodata_id, productodata_nombre, productodata_descripcion, productodata_imagen)
	VALUES (18, 'La canción de Rolando', 'Ronaldo es el último', 'http://i.imgur.com/XjR6Krc.jpg');
	
--productohistorico

INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (1, 1, 10.99, 12, '2015-07-02 00:00:00', '2015-07-05 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_tax_percentage, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (1, 2, 9.95, 10, 0, '2015-07-07 00:00:00', '2015-07-10 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_tax_percentage, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (1, 3, 12.95, 10, 0, '2015-07-11 00:00:00', '2015-07-12 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_tax_percentage, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (1, 4, 7, 5, 0, '2015-07-18 00:00:00', '2015-07-23 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_tax_percentage, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (1, 5, 8, 10, 0, '2015-07-28 00:00:00', '2015-08-01 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_tax_percentage, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (1, 6, 9.99, 10, 0, '2015-08-10 00:00:00', '2015-08-13 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (1, 7, 13, 17, '2015-08-17 00:00:00', '2015-08-20 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (1, 8, 8.99, 25, '2015-08-22 00:00:00', '2015-08-25 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (1, 9, 12, 10, '2015-08-29 00:00:00', '2015-08-31 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (1, 10, 10.99, 20, '2015-09-04 00:00:00', '2015-09-08 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (2, 1, 9.99, 10, '2015-07-02 00:00:00', '2015-07-05 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (2, 2, 8.5, 50, '2015-07-12 00:00:00', '2015-07-15 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (2, 3, 9.99, 10, '2015-07-20 00:00:00', '2015-07-23 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (2, 4, 7, 20, '2015-08-02 00:00:00', '2015-08-10 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (2, 5, 7, 10, '2015-08-20 00:00:00', '2015-08-23 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (2, 6, 6, 25, '2015-08-25 00:00:00', '2015-08-28 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_tax_percentage, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (2, 7, 6, 10, 6.5, '2015-08-31 00:00:00', '2015-09-03 00:00:00');
INSERT INTO productohistorico(productohistorico_id, productohistorico_version, productohistorico_precio, productohistorico_stock_inicial, productohistorico_tax_percentage, productohistorico_fecha_puesta_venta, productohistorico_fecha_retirada)
	VALUES (2, 8, 5.95, 10, 21, '2015-09-05 00:00:00', '2015-09-08 00:00:00');

	
--empleado_tienda

INSERT INTO empleado_tienda(empleado_id, tienda_id)
	VALUES (2, 1);
INSERT INTO empleado_tienda(empleado_id, tienda_id)
	VALUES (4, 1);
INSERT INTO empleado_tienda(empleado_id, tienda_id)
	VALUES (5, 1);
INSERT INTO empleado_tienda(empleado_id, tienda_id)
	VALUES (2, 2);
INSERT INTO empleado_tienda(empleado_id, tienda_id)
	VALUES (3, 2);
INSERT INTO empleado_tienda(empleado_id, tienda_id)
	VALUES (6, 2);
INSERT INTO empleado_tienda(empleado_id, tienda_id)
	VALUES (7, 2);
	
--cliente_tienda

INSERT INTO cliente_tienda(cliente_id, tienda_id)
	VALUES (1, 1);
INSERT INTO cliente_tienda(cliente_id, tienda_id)
	VALUES (1, 2);
INSERT INTO cliente_tienda(cliente_id, tienda_id)
	VALUES (1, 6);
INSERT INTO cliente_tienda(cliente_id, tienda_id)
	VALUES (1, 7);

--tienda_categoria

INSERT INTO tienda_categoria(categoria_id, tienda_id)
	VALUES (1, 1);
INSERT INTO tienda_categoria(categoria_id, tienda_id)
	VALUES (5, 1);
INSERT INTO tienda_categoria(categoria_id, tienda_id)
	VALUES (1, 2);
INSERT INTO tienda_categoria(categoria_id, tienda_id)
	VALUES (3, 2);
INSERT INTO tienda_categoria(categoria_id, tienda_id)
	VALUES (1, 3);
INSERT INTO tienda_categoria(categoria_id, tienda_id)
	VALUES (10, 3);
INSERT INTO tienda_categoria(categoria_id, tienda_id)
	VALUES (5, 3);
INSERT INTO tienda_categoria(categoria_id, tienda_id)
	VALUES (4, 4);
INSERT INTO tienda_categoria(categoria_id, tienda_id)
	VALUES (1, 4);
INSERT INTO tienda_categoria(categoria_id, tienda_id)
	VALUES (9, 4);
INSERT INTO tienda_categoria(categoria_id, tienda_id)
	VALUES (11, 4);
INSERT INTO tienda_categoria(categoria_id, tienda_id)
	VALUES (2, 5);
INSERT INTO tienda_categoria(categoria_id, tienda_id)
	VALUES (3, 5);
INSERT INTO tienda_categoria(categoria_id, tienda_id)
	VALUES (6, 5);
INSERT INTO tienda_categoria(categoria_id, tienda_id)
	VALUES (8, 5);
INSERT INTO tienda_categoria(categoria_id, tienda_id)
	VALUES (9, 5);
INSERT INTO tienda_categoria(categoria_id, tienda_id)
	VALUES (11, 5);
	
--producto_categoria

INSERT INTO producto_categoria(categoria_id, producto_id)
	VALUES (1, 1);
INSERT INTO producto_categoria(categoria_id, producto_id)
	VALUES (1, 2);
INSERT INTO producto_categoria(categoria_id, producto_id)
	VALUES (1, 3);
INSERT INTO producto_categoria(categoria_id, producto_id)
	VALUES (1, 4);
INSERT INTO producto_categoria(categoria_id, producto_id)
	VALUES (1, 5);
INSERT INTO producto_categoria(categoria_id, producto_id)
	VALUES (1, 6);
INSERT INTO producto_categoria(categoria_id, producto_id)
	VALUES (5, 6);
INSERT INTO producto_categoria(categoria_id, producto_id)
	VALUES (1, 7);
INSERT INTO producto_categoria(categoria_id, producto_id)
	VALUES (9, 8);
INSERT INTO producto_categoria(categoria_id, producto_id)
	VALUES (9, 9);
INSERT INTO producto_categoria(categoria_id, producto_id)
	VALUES (9, 10);
INSERT INTO producto_categoria(categoria_id, producto_id)
	VALUES (9, 11);
INSERT INTO producto_categoria(categoria_id, producto_id)
	VALUES (9, 12);
INSERT INTO producto_categoria(categoria_id, producto_id)
	VALUES (11, 13);
INSERT INTO producto_categoria(categoria_id, producto_id)
	VALUES (9, 14);
INSERT INTO producto_categoria(categoria_id, producto_id)
	VALUES (9, 15);
INSERT INTO producto_categoria(categoria_id, producto_id)
	VALUES (9, 16);
INSERT INTO producto_categoria(categoria_id, producto_id)
	VALUES (9, 17);
INSERT INTO producto_categoria(categoria_id, producto_id)
	VALUES (1, 18);

--reserva
	
INSERT INTO reserva(reserva_unidades, reserva_estado, reserva_fecha, reserva_precio, reserva_total, producto_id, cliente_id)
	VALUES( 1, 'PENDIENTE', '1999-01-08 04:05:06', 7.99, 7.99, 1, 1);
INSERT INTO reserva(reserva_unidades, reserva_estado, reserva_fecha, reserva_precio, reserva_total, producto_id, cliente_id)
	VALUES( 2, 'ENTREGADA', '2014-12-08 12:05:06', 7, 14, 4, 1);
INSERT INTO reserva(reserva_unidades, reserva_estado, reserva_fecha, reserva_precio, reserva_total, producto_id, cliente_id)
	VALUES( 1, 'CANCELADA', '2014-12-08 21:05:06', 5.95, 5.95, 2, 2);
	
INSERT INTO reserva(reserva_unidades, reserva_estado, reserva_fecha, reserva_precio, reserva_total, producto_id, cliente_id)
	VALUES( 2, 'ENTREGADA', '2015-08-02 21:05:06', 4.95, 9.9, 2, 2);
INSERT INTO reserva(reserva_unidades, reserva_estado, reserva_fecha, reserva_precio, reserva_total, producto_id, cliente_id)
	VALUES( 3, 'ENTREGADA', '2015-08-23 21:05:06', 6.95, 20.85, 1, 2);
INSERT INTO reserva(reserva_unidades, reserva_estado, reserva_fecha, reserva_precio, reserva_total, producto_id, cliente_id)
	VALUES( 2, 'ENTREGADA', '2015-08-24 21:05:06', 6.95, 13.9, 1, 2);
INSERT INTO reserva(reserva_unidades, reserva_estado, reserva_fecha, reserva_precio, reserva_total, producto_id, cliente_id)
	VALUES( 4, 'ENTREGADA', '2015-08-24 22:05:06', 6.95, 27.8, 1, 2);
INSERT INTO reserva(reserva_unidades, reserva_estado, reserva_fecha, reserva_precio, reserva_total, producto_id, cliente_id)
	VALUES( 1, 'ENTREGADA', '2015-08-30 21:05:06', 7.95, 7.95, 1, 2);
INSERT INTO reserva(reserva_unidades, reserva_estado, reserva_fecha, reserva_precio, reserva_total, producto_id, cliente_id)
	VALUES( 1, 'ENTREGADA', '2015-09-05 21:05:06', 5.95, 5.95, 1, 1);
INSERT INTO reserva(reserva_unidades, reserva_estado, reserva_fecha, reserva_precio, reserva_total, producto_id, cliente_id)
	VALUES( 1, 'ENTREGADA', '2015-09-07 21:05:06', 5.95, 5.95, 2, 1);
INSERT INTO reserva(reserva_unidades, reserva_estado, reserva_fecha, reserva_precio, reserva_total, producto_id, cliente_id)
	VALUES( 2, 'ENTREGADA', '2015-09-07 22:05:06', 6.95, 13.9, 1, 2);


	

--compra
	
INSERT INTO compra(compra_unidades, compra_estado_recogida, compra_fecha, compra_precio, compra_total, compra_id_paypal, compra_estado_paypal, compra_forma_pago, compra_currency, compra_tax_percentage, producto_id, cliente_id)
	VALUES( 2, 'RECOGIDA', '2015-02-26 11:34:50', 0.99, 1.98, 'PAY-8B123138ST4347536KTXQJWQ', 'approved', 'tarjeta', 'EUR', 0, 8, 5);
INSERT INTO compra(compra_unidades, compra_estado_recogida, compra_fecha, compra_precio, compra_total, compra_id_paypal, compra_estado_paypal, compra_forma_pago, compra_currency, compra_tax_percentage, producto_id, cliente_id)
	VALUES( 1, 'NO_RECOGIDA', '2015-02-25 20:44:29', 7, 7, 'PAY-0K423450Y36729250KTXDILI', 'approved', 'tarjeta', 'EUR', 0, 4, 5);
INSERT INTO compra(compra_unidades, compra_estado_recogida, compra_fecha, compra_precio, compra_total, compra_id_paypal, compra_estado_paypal, compra_forma_pago, compra_currency, compra_tax_percentage, producto_id, cliente_id)
	VALUES( 4, 'RECOGIDA', '2015-02-25 20:27:40', 0.99, 3.96, 'PAY-7TT482181V777425KKTXDAPA', 'approved', 'paypal', 'EUR', 21, 8, 1);
INSERT INTO compra(compra_unidades, compra_estado_recogida, compra_fecha, compra_precio, compra_total, compra_id_paypal, compra_estado_paypal, compra_forma_pago, compra_currency, compra_tax_percentage, producto_id, cliente_id)
	VALUES( 2, 'NO_RECOGIDA', '2015-02-25 20:05:04', 12.95, 25.90, 'PAY-0HU09832JN454922BKTXCV4A', 'approved', 'paypal', 'EUR', 6.5, 3, 1);
INSERT INTO compra(compra_unidades, compra_estado_recogida, compra_fecha, compra_precio, compra_total, compra_id_paypal, compra_estado_paypal, compra_forma_pago, compra_currency, compra_tax_percentage, producto_id, cliente_id)
	VALUES( 2, 'RECOGIDA', '2015-02-25 20:02:54', 7, 14, 'PAY-4T334392303928241KTXCU3Q', 'approved', 'paypal', 'EUR', 18.1, 4, 1);
	
INSERT INTO compra(compra_unidades, compra_estado_recogida, compra_fecha, compra_precio, compra_total, compra_id_paypal, compra_estado_paypal, compra_forma_pago, compra_currency, compra_tax_percentage, producto_id, cliente_id)
	VALUES( 1, 'RECOGIDA', '2015-08-02 20:02:54', 4.95, 4.95, 'PAY-11', 'approved', 'paypal', 'EUR', 18.1, 2, 1);
INSERT INTO compra(compra_unidades, compra_estado_recogida, compra_fecha, compra_precio, compra_total, compra_id_paypal, compra_estado_paypal, compra_forma_pago, compra_currency, compra_tax_percentage, producto_id, cliente_id)
	VALUES( 2, 'NO_RECOGIDA', '2015-08-30 20:02:54', 7.95, 15.9, 'PAY-12', 'approved', 'paypal', 'EUR', 18.1, 1, 2);
INSERT INTO compra(compra_unidades, compra_estado_recogida, compra_fecha, compra_precio, compra_total, compra_id_paypal, compra_estado_paypal, compra_forma_pago, compra_currency, compra_tax_percentage, producto_id, cliente_id)
	VALUES( 1, 'RECOGIDA', '2015-09-06 20:02:54', 5.95, 5.95, 'PAY-13', 'approved', 'paypal', 'EUR', 18.1, 1, 1);
INSERT INTO compra(compra_unidades, compra_estado_recogida, compra_fecha, compra_precio, compra_total, compra_id_paypal, compra_estado_paypal, compra_forma_pago, compra_currency, compra_tax_percentage, producto_id, cliente_id)
	VALUES( 3, 'NO_RECOGIDA', '2015-09-06 21:02:54', 5.95, 17.85, 'PAY-14', 'approved', 'paypal', 'EUR', 18.1, 2, 2);
INSERT INTO compra(compra_unidades, compra_estado_recogida, compra_fecha, compra_precio, compra_total, compra_id_paypal, compra_estado_paypal, compra_forma_pago, compra_currency, compra_tax_percentage, producto_id, cliente_id)
	VALUES( 1, 'RECOGIDA', '2015-09-06 23:02:54', 6.95, 6.95, 'PAY-15', 'approved', 'paypal', 'EUR', 18.1, 1, 2);
	

--compra_not_verified
	
INSERT INTO compra_not_verified(compra_not_verified_unidades, compra_not_verified_fecha, compra_not_verified_precio, compra_not_verified_total, compra_not_verified_id_paypal, compra_not_verified_forma_pago, compra_not_verified_currency, producto_id, cliente_id)
	VALUES( 1, '2015-02-25 17:08:01', 0.99, 0.99, 'PAY-90N36598CR826520HKTXAC4I', 'paypal', 'EUR', 8, 5);
--INSERT INTO reserva(reseva_unidades, reserva_estado, reserva_fecha, reserva_subtotal, reserva_total, producto_id, cliente_id)
--	VALUES( , '', , , , , );
--INSERT INTO reserva(reseva_unidades, reserva_estado, reserva_fecha, reserva_subtotal, reserva_total, producto_id, cliente_id)
--	VALUES( , '', , , , , );
--INSERT INTO reserva(reseva_unidades, reserva_estado, reserva_fecha, reserva_subtotal, reserva_total, producto_id, cliente_id)
--	VALUES( , '', , , , , );