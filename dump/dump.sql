CREATE TABLE IF NOT EXISTS  user_entity (
	cod_user serial NOT NULL,
	PRIMARY KEY(cod_user),
	name_user varchar(15) NOT NULL,
	email_user varchar(30) NOT NULL,
	password_user varchar(20) NOT NULL,
	biography_user text
);

CREATE TABLE IF NOT EXISTS  album_entity (
	cod_album serial NOT NULL,
	PRIMARY KEY(cod_album),
	name_album varchar(20) NOT NULL,
	description_album text,
	creation_date_album date NOT NULL
);

CREATE TABLE IF NOT EXISTS  photo_entity (
	cod_photo serial NOT NULL,
	PRIMARY KEY(cod_photo),
	name_photo varchar NOT NULL,
	description_photo text,
	date_upload_photo date NOT NULL,
	number_of_likes_photo integer DEFAULT 0,
	cod_user integer NOT NULL,
	FOREIGN KEY(cod_user) REFERENCES user_entity(cod_user) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS  comment_entity (
	cod_comment serial NOT NULL,
	PRIMARY KEY(cod_comment),
	text_comment text NOT NULL,
	date_comment date NOT NULL,
	cod_photo integer NOT NULL,
	FOREIGN KEY(cod_photo) REFERENCES photo_entity(cod_photo) ON DELETE CASCADE,
	cod_user integer NOT NULL,
	FOREIGN KEY(cod_user) REFERENCES user_entity(cod_user) ON DELETE CASCADE
); 

CREATE TABLE IF NOT EXISTS  like_entity (
	cod_like serial NOT NULL,
	PRIMARY KEY(cod_like),
	cod_user integer NOT NULL,
	FOREIGN KEY(cod_user) REFERENCES user_entity(cod_user) ON DELETE CASCADE,
	cod_photo integer NOT NULL,
	FOREIGN KEY(cod_photo) REFERENCES photo_entity(cod_photo)ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS  album_photo (
	cod_album_photo serial NOT NULL,
	PRIMARY KEY(cod_album_photo),
	cod_album integer NOT NULL,
	FOREIGN KEY(cod_album) REFERENCES album_entity(cod_album) ON DELETE CASCADE,
	cod_photo integer NOT NULL,
	FOREIGN KEY(cod_photo) REFERENCES photo_entity(cod_photo) ON DELETE CASCADE
);

CREATE OR REPLACE FUNCTION getNumberOfLikes(id_photo integer) RETURNS integer
LANGUAGE plpgsql
AS $$
	DECLARE
		numberOfLikes integer;
	BEGIN
		SELECT COUNT(*) INTO numberOfLikes FROM like_entity WHERE like_entity.cod_photo = id_photo;
		RETURN numberOfLikes;
	END
$$;

CREATE OR REPLACE PROCEDURE updateNumberOfLikes(id_photo integer)
LANGUAGE plpgsql
AS $$
	BEGIN
		UPDATE photo_entity 
		SET number_of_likes_photo = (SELECT getnunberoflikes(id_photo)) 
		WHERE cod_photo = id_photo;
	END
$$;

DROP VIEW IF EXISTS getallphotos;

CREATE OR REPLACE VIEW getAllPhotos AS
SELECT photo_entity.*, user_entity.name_user, user_entity.email_user FROM photo_entity 
INNER JOIN user_entity 
ON photo_entity.cod_user = user_entity.cod_user;
	
