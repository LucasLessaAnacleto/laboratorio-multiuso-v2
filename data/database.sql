CREATE TABLE `anexo` (
  `nome_anexo` varchar(255) NOT NULL,
  `content_blob` longblob,
  `content_type` varchar(255) DEFAULT NULL,
  `data_cricao` datetime(6) DEFAULT NULL,
  `data_upload` datetime(6) DEFAULT NULL,
  `size` bigint DEFAULT NULL,
  PRIMARY KEY (`nome_anexo`)
) ;

--
-- Table structure for table `equipamento`
--
CREATE TABLE `equipamento` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `categoria` varchar(255) DEFAULT NULL,
  `contato_responsavel` varchar(255) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `patrimonio` varchar(255) DEFAULT NULL,
  `anexo_imagem` varchar(255) DEFAULT NULL,
  `espaco_id` varchar(255) NOT NULL,
  `disponivel` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKt5xysla5y1kok8fn8j90ibw6i` (`anexo_imagem`),
  KEY `FKfb0n5cj8cmfc6lvh8jp9dkn4c` (`espaco_id`),
  CONSTRAINT `FKepa70nmfvpmq315jshx4r9jqq` FOREIGN KEY (`anexo_imagem`) REFERENCES `anexo` (`nome_anexo`),
  CONSTRAINT `FKfb0n5cj8cmfc6lvh8jp9dkn4c` FOREIGN KEY (`espaco_id`) REFERENCES `espaco` (`id`)
);

CREATE TABLE `espaco` (
  `id` varchar(255) NOT NULL,
  `andar` varchar(255) DEFAULT NULL,
  `ativo` bit(1) DEFAULT NULL,
  `departamento` varchar(255) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `disponibilidade` bit(1) DEFAULT NULL,
  `endereco` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `politica_uso` varchar(255) DEFAULT NULL,
  `sala` varchar(255) DEFAULT NULL,
  `imagem_capa` varchar(255) DEFAULT NULL,
  `contato` varchar(255) DEFAULT NULL,
  `disponivel` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKcab1gsh6e38ferfypc8o832rd` (`imagem_capa`),
  CONSTRAINT `FKe7medoaowrm766kiiujek9aut` FOREIGN KEY (`imagem_capa`) REFERENCES `anexo` (`nome_anexo`)
) ;
--
-- Table structure for table `reserva`
--

CREATE TABLE `reserva` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_reserva` datetime(6) DEFAULT NULL,
  `data_solicitacao` datetime(6) DEFAULT NULL,
  `descricao_reserva` varchar(255) DEFAULT NULL,
  `duracao` bigint DEFAULT NULL,
  `espaco_id` varchar(255) DEFAULT NULL,
  `data_cancelamento` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjxb4pp7dck95lib0y5qipxlxv` (`espaco_id`),
  CONSTRAINT `FKjxb4pp7dck95lib0y5qipxlxv` FOREIGN KEY (`espaco_id`) REFERENCES `espaco` (`id`)
) ;
--
-- Table structure for table `usuario`
--

CREATE TABLE `usuario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `foto_perfil` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK5171l57faosmj8myawaucatdw` (`email`),
  UNIQUE KEY `UKishqggt5yhm1f8pcg0rhg50wq` (`foto_perfil`),
  CONSTRAINT `FKpbiiphv0l9h9cxlwh2w014o8n` FOREIGN KEY (`foto_perfil`) REFERENCES `anexo` (`nome_anexo`)
) ;

-- view `agenda_mestre`
SET SESSION cte_max_recursion_depth = 5000;
create view agenda_mestre as
select v.*, 
	concat(date_format(v.dt,'%Y%m%d%H_'), e.id) as id,
    r.id as reserva_id,
    e.id as espaco_id
from (
	WITH RECURSIVE calendar (dt) AS (
		SELECT date_format('2025-11-30 17:00:00', '%Y-%m-%d %H:00:00')
		UNION ALL
		SELECT dt + INTERVAL 1 HOUR
		FROM calendar
	WHERE dt + INTERVAL 1 HOUR <= NOW() + INTERVAL 1 MONTH
	)
	SELECT dt, year(dt) as ano , month(dt) as mes, day(dt) as dia, dayofweek(dt) as dia_semana, hour(dt) as hora FROM calendar 
	where HOUR(dt) >= 08 and HOUR(dt) <= 22 and dayofweek(dt) <> 1
) v
cross join (select distinct id from espaco) e
left join reserva r
	on r.data_reserva = v.dt
	and r.espaco_id = e.id;