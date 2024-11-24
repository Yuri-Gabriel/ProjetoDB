# Code to create tables and to populate the database

```
CREATE TABLE IF NOT EXISTS  user_entity (
	cod_user serial NOT NULL,
	PRIMARY KEY(cod_user),
	name_user varchar NOT NULL,
	email_user varchar NOT NULL,
	password_user varchar NOT NULL,
	biography_user text
);

CREATE TABLE IF NOT EXISTS  album_entity (
	cod_album serial NOT NULL,
	PRIMARY KEY(cod_album),
	name_album varchar NOT NULL,
	description_album text,
	creation_date_album date NOT NULL,
	cod_user integer NOT NULL,
	FOREIGN KEY(cod_user) REFERENCES user_entity(cod_user) ON DELETE CASCADE
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
		SET number_of_likes_photo = (SELECT getnumberoflikes(id_photo)) 
		WHERE cod_photo = id_photo;
	END
$$;

DROP VIEW IF EXISTS getallphotos;

CREATE OR REPLACE VIEW getAllPhotos AS
SELECT photo_entity.*, user_entity.name_user, user_entity.email_user FROM photo_entity 
INNER JOIN user_entity 
ON photo_entity.cod_user = user_entity.cod_user;

INSERT INTO user_entity (name_user, email_user, password_user, biography_user) VALUES
('Alice', 'alice@example.com', 'password123', 'Amante de fotografia e viagens.'),
('Bob', 'bob@example.com', 'securepass', 'Entusiasta de tecnologia.'),
('Carol', 'carol@example.com', 'carolpass', 'Artista e criadora de conteúdo.'),
('David', 'david@example.com', 'davidsafe', 'Amador em fotografia, amante da natureza.'),
('Eve', 'eve@example.com', 'evepassword', 'Especialista em retratos e editor de imagens.');

INSERT INTO album_entity (name_album, description_album, creation_date_album, cod_user) VALUES
('Viagens 2024', 'Fotos de viagens pelo mundo.', '2024-01-15', 1),
('Retratos', 'Coleção de retratos diversos.', '2024-02-10', 1),
('Natureza', 'Fotos tiradas em parques nacionais.', '2024-03-05', 2),
('Tecnologia Antiga', 'Equipamentos antigos e vintage.', '2024-04-12', 2),
('Arte Digital', 'Coleção de fotos manipuladas.', '2024-05-18', 3),
('Eventos', 'Fotos tiradas em eventos locais.', '2024-06-22', 3),
('Trilhas', 'Fotografias de trilhas e florestas.', '2024-07-13', 4),
('Paisagens', 'Paisagens de montanhas e praias.', '2024-08-09', 4),
('Retratos Exclusivos', 'Fotos detalhadas e realistas.', '2024-09-11', 5),
('Experimentações', 'Fotos experimentais.', '2024-10-02', 5);

INSERT INTO photo_entity (name_photo, description_photo, date_upload_photo, cod_user) VALUES
('Torre Eiffel', 'Vista incrível da Torre Eiffel.', '2024-01-16', 1),
('Ponte Golden Gate', 'Ponte em um dia ensolarado.', '2024-01-17', 1),
('Montanhas Rochosas', 'Foto ao amanhecer.', '2024-01-18', 1),
('Parque Nacional', 'Cachoeira majestosa.', '2024-03-06', 2),
('Tecnologia Vintage', 'Câmera antiga dos anos 80.', '2024-03-07', 2),
('Velocidade', 'Carro clássico em movimento.', '2024-03-08', 2),
('Arte Urbana', 'Grafite em uma parede.', '2024-05-19', 3),
('Retrato Criativo', 'Uma pose única com efeitos.', '2024-05-20', 3),
('Luzes Noturnas', 'Cidade iluminada à noite.', '2024-05-21', 3),
('Caminho de Pedra', 'Trilha em uma floresta.', '2024-07-14', 4),
('Pôr do Sol', 'Sol se pondo no horizonte.', '2024-07-15', 4),
('Cachoeira Alta', 'Cachoeira deslumbrante.', '2024-07-16', 4),
('Expressão Humana', 'Retrato emocional.', '2024-09-12', 5),
('Sombras', 'Foto com um jogo de sombras.', '2024-09-13', 5),
('Desfoque', 'Efeito bokeh no fundo.', '2024-09-14', 5);

INSERT INTO comment_entity (text_comment, date_comment, cod_photo, cod_user) VALUES
('Linda foto!', '2024-01-19', 1, 1),
('Incrível lugar!', '2024-01-20', 2, 1),
('Perspectiva interessante.', '2024-03-09', 4, 2),
('Adorei os detalhes.', '2024-03-10', 5, 2),
('Muito criativo!', '2024-05-22', 7, 3),
('Luzes perfeitas.', '2024-05-23', 8, 3),
('Adoro trilhas assim!', '2024-07-17', 10, 4),
('Foto sensacional!', '2024-07-18', 11, 4),
('Expressivo e forte.', '2024-09-15', 13, 5),
('Gostei da composição.', '2024-09-16', 14, 5);
```
