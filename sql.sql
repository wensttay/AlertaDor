CREATE EXTENSION postgis;
-- Enable Topology
CREATE EXTENSION postgis_topology;
-- Enable PostGIS Advanced 3D 
-- and other geoprocessing algorithms
-- sfcgal not available with all distributions
CREATE EXTENSION postgis_sfcgal;
-- fuzzy matching needed for Tiger
CREATE EXTENSION fuzzystrmatch;
  

CREATE TABLE USERS(
	id SERIAL,
	email TEXT UNIQUE NOT NULL,
	name TEXT NOT NULL,
	password TEXT NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE DENUNCIATION (
	id SERIAL,
	squealer_id INT NOT NULL,
	squaler_type INT NOT NULL,
	description TEXT NOT NULL,
	location TEXT NOT NULL,
	denunciation_type INT NOT NULL,
	anonymous BOOLEAN NOT NULL,
	
	PRIMARY KEY (id),
	FOREIGN KEY (squealer_id) REFERENCES USERS(id)
);
