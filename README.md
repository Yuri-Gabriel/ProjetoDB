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

