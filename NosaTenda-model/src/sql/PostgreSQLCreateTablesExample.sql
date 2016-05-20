
DROP TABLE IF EXISTS TestPoint;
DROP TABLE IF EXISTS TestLatLon;

DROP SEQUENCE IF EXISTS testpoint_testpoint_id_seq;
DROP SEQUENCE IF EXISTS testlatlon_testlatlon_id_seq;


---------------------------- TestPoint -------------------------

CREATE TABLE TestPoint (
	testpoint_id serial PRIMARY KEY,
	testpoint_point point NOT NULL,
	UNIQUE (testpoint_id)
);

CREATE TABLE TestLatLon (
	testlatlon_id serial PRIMARY KEY,
	testlatlon_lat NUMERIC NOT NULL,
	testlatlon_lon NUMERIC NOT NULL,
	UNIQUE (testlatlon_id)
);