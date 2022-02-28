CREATE DATABASE tuits;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nickName` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nickName` (`nickName`)
) ENGINE = InnoDB AUTO_INCREMENT = 30 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci


CREATE TABLE `post` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `id_user` int NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `isEdited` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `post_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 172 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci



CREATE TABLE `password_change_requests` (
  `id` varchar(36) NOT NULL,
  `time` datetime NOT NULL,
  `id_user` int NOT NULL,
  `token_recover` varchar(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `token_recover` (`token_recover`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `password_change_requests_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci