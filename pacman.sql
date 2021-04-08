-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : jeu. 08 avr. 2021 à 16:29
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `pacman`
--
CREATE DATABASE IF NOT EXISTS `pacman` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `pacman`;

-- --------------------------------------------------------

--
-- Structure de la table `cosmetic`
--

DROP TABLE IF EXISTS `cosmetic`;
CREATE TABLE IF NOT EXISTS `cosmetic` (
  `id_cosmetic` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `price` int(11) NOT NULL,
  `color` varchar(11) NOT NULL,
  PRIMARY KEY (`id_cosmetic`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `cosmetic`
--

INSERT INTO `cosmetic` (`id_cosmetic`, `name`, `price`, `color`) VALUES
(1, 'Pacman en noir', 100, 'black'),
(2, 'Pacman en bleu', 100, 'blue'),
(3, 'Pacman en cyan', 100, 'cyan'),
(4, 'Pacman en gris foncé', 100, 'darkGray'),
(5, 'Pacman en gris', 100, 'gray'),
(6, 'Pacman en vert', 100, 'green'),
(7, 'Pacman en gris clair', 100, 'lightGray'),
(8, 'Pacman en magenta', 100, 'magenta'),
(9, 'Pacman en orange', 100, 'orange'),
(10, 'Pacman en rose', 100, 'pink'),
(11, 'Pacman en rouge', 100, 'red'),
(12, 'Pacman en blanc', 100, 'white'),
(13, 'Pacman en jaune', 100, 'yellow');

-- --------------------------------------------------------

--
-- Structure de la table `game`
--

DROP TABLE IF EXISTS `game`;
CREATE TABLE IF NOT EXISTS `game` (
  `id_game` int(11) NOT NULL AUTO_INCREMENT,
  `id_player` int(11) NOT NULL,
  `score` int(11) NOT NULL DEFAULT '0',
  `time` int(11) NOT NULL DEFAULT '0',
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_game`),
  KEY `FK_player` (`id_player`)
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `game`
--

INSERT INTO `game` (`id_game`, `id_player`, `score`, `time`, `date`) VALUES
(31, 12, 2591, 444, '2021-01-11 00:53:44'),
(32, 10, 482, 76, '2021-01-11 18:55:03'),
(33, 1, 384, 23, '2021-03-22 07:47:16'),
(34, 7, 1651, 465, '2021-03-26 01:23:47'),
(35, 8, 3343, 448, '2021-01-31 02:35:12'),
(36, 1, 1070, 54, '2021-02-21 04:24:33'),
(37, 8, 1356, 377, '2021-01-02 04:04:04'),
(38, 10, 2731, 180, '2021-01-20 13:55:54'),
(39, 8, 2037, 450, '2021-04-06 08:25:30'),
(40, 5, 1144, 391, '2021-03-01 10:09:41'),
(41, 2, 2522, 125, '2021-04-02 17:53:35'),
(42, 9, 1261, 316, '2021-02-20 15:14:51'),
(43, 9, 3128, 441, '2021-01-31 13:00:25'),
(44, 11, 609, 135, '2021-03-03 10:31:31'),
(45, 1, 1107, 364, '2021-03-17 09:17:06'),
(46, 2, 2651, 325, '2021-02-09 20:13:19'),
(47, 11, 2019, 412, '2021-03-01 03:15:15'),
(48, 5, 812, 30, '2021-03-12 16:43:28'),
(49, 4, 237, 129, '2021-02-16 08:10:39'),
(50, 8, 3049, 251, '2021-01-04 10:14:12'),
(51, 8, 1285, 253, '2021-02-14 20:20:34'),
(52, 3, 2464, 136, '2021-01-14 11:05:48'),
(53, 6, 2822, 467, '2021-02-07 03:15:57'),
(54, 9, 3429, 497, '2021-02-26 04:40:44'),
(55, 1, 861, 402, '2021-02-13 14:11:49'),
(56, 7, 1622, 280, '2021-02-11 12:19:34'),
(57, 6, 916, 307, '2021-03-23 14:11:39'),
(58, 2, 1836, 159, '2021-04-02 12:05:24'),
(59, 3, 3381, 438, '2021-04-05 11:10:44'),
(60, 2, 1557, 31, '2021-03-01 20:52:24'),
(61, 1, 2975, 336, '2021-02-11 03:03:44'),
(62, 11, 1848, 469, '2021-02-15 17:21:54'),
(63, 7, 2866, 266, '2021-01-20 22:55:25'),
(64, 4, 1157, 189, '2021-02-11 18:28:47'),
(65, 9, 261, 471, '2021-01-24 12:58:43'),
(66, 9, 3050, 442, '2021-02-02 16:38:56'),
(67, 7, 1783, 169, '2021-01-28 02:52:59'),
(68, 6, 966, 180, '2021-03-05 07:45:30'),
(69, 3, 1390, 412, '2021-02-25 10:01:08'),
(70, 2, 1245, 479, '2021-03-01 07:40:32'),
(71, 5, 634, 455, '2021-03-15 01:47:34'),
(72, 5, 1199, 376, '2021-02-17 23:29:28'),
(73, 2, 852, 257, '2021-02-27 08:44:57'),
(74, 12, 1366, 167, '2021-02-22 05:02:45'),
(75, 1, 565, 270, '2021-04-05 15:15:27'),
(76, 3, 417, 497, '2021-03-28 09:44:44'),
(77, 3, 3404, 273, '2021-02-19 18:33:09'),
(78, 6, 3338, 352, '2021-02-27 05:35:34'),
(79, 4, 1199, 367, '2021-01-22 05:28:39'),
(80, 10, 1850, 409, '2021-01-08 05:12:28'),
(81, 11, 2905, 309, '2021-04-03 07:38:53'),
(82, 8, 2624, 294, '2021-01-31 09:46:23'),
(83, 2, 623, 277, '2021-03-30 05:53:19'),
(84, 3, 3030, 423, '2021-02-04 19:18:43'),
(85, 7, 2540, 239, '2021-02-02 15:30:52'),
(86, 9, 886, 177, '2021-01-30 02:23:18'),
(87, 7, 3180, 104, '2021-03-12 14:49:34'),
(88, 3, 2785, 365, '2021-02-05 14:28:18'),
(89, 5, 731, 126, '2021-04-03 12:52:03'),
(90, 8, 3370, 54, '2021-01-01 19:43:40'),
(91, 13, 3126, 366, '2021-02-08 21:58:24'),
(92, 2, 1321, 198, '2021-01-21 07:43:25'),
(93, 2, 886, 317, '2021-03-01 10:05:10'),
(94, 8, 281, 77, '2021-03-14 18:27:53'),
(95, 2, 2493, 146, '2021-01-14 09:04:17'),
(96, 11, 1025, 151, '2021-02-03 17:05:12'),
(97, 11, 2492, 144, '2021-03-22 21:19:32'),
(98, 2, 3146, 150, '2021-02-06 06:31:51'),
(99, 4, 2285, 118, '2021-01-15 12:44:10'),
(100, 8, 1353, 314, '2021-03-06 20:15:25'),
(101, 11, 1589, 433, '2021-01-05 11:30:22'),
(102, 2, 819, 270, '2021-04-05 18:04:08'),
(103, 4, 761, 446, '2021-01-28 06:38:46'),
(104, 13, 3384, 460, '2021-03-20 19:18:21'),
(105, 7, 273, 299, '2021-02-13 13:03:33'),
(106, 12, 953, 36, '2021-02-10 18:28:33'),
(107, 9, 2379, 401, '2021-01-27 08:09:21'),
(108, 2, 1904, 228, '2021-01-27 07:23:25'),
(109, 3, 465, 356, '2021-02-06 15:37:53'),
(110, 8, 1132, 235, '2021-03-09 04:14:02'),
(111, 4, 295, 479, '2021-01-14 04:02:15'),
(112, 6, 2800, 236, '2021-02-09 04:32:49'),
(113, 10, 1626, 150, '2021-02-07 10:23:22'),
(114, 8, 1278, 349, '2021-01-05 10:34:29'),
(115, 13, 470, 133, '2021-03-25 00:02:21'),
(116, 3, 616, 124, '2021-01-10 07:52:05'),
(117, 5, 2598, 223, '2021-03-15 18:47:03'),
(118, 2, 607, 36, '2021-02-26 08:08:55'),
(119, 11, 1264, 397, '2021-02-22 02:37:57'),
(120, 6, 3498, 50, '2021-02-18 14:45:04'),
(121, 2, 1375, 129, '2021-03-29 13:10:31'),
(122, 4, 1516, 456, '2021-03-12 00:53:59'),
(123, 6, 426, 221, '2021-01-02 10:13:39'),
(124, 13, 529, 452, '2021-04-03 01:35:00'),
(125, 7, 2500, 298, '2021-01-15 18:10:36'),
(126, 1, 231, 200, '2021-03-23 04:27:42'),
(127, 3, 3262, 343, '2021-01-19 18:42:52'),
(128, 6, 3430, 195, '2021-02-27 06:22:04'),
(129, 8, 2013, 83, '2021-01-24 13:14:09'),
(130, 3, 869, 78, '2021-02-05 15:38:34');

-- --------------------------------------------------------

--
-- Structure de la table `player`
--

DROP TABLE IF EXISTS `player`;
CREATE TABLE IF NOT EXISTS `player` (
  `id_player` int(11) NOT NULL AUTO_INCREMENT,
  `pseudo` varchar(50) NOT NULL,
  `login` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `nationality` varchar(2) NOT NULL DEFAULT 'FR',
  `date_inscription` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `solde` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_player`),
  UNIQUE KEY `pseudo` (`pseudo`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `player`
--

INSERT INTO `player` (`id_player`, `pseudo`, `login`, `password`, `nationality`, `date_inscription`, `solde`) VALUES
(1, 'Daltfresh', 'Daltfresh', '5f4dcc3b5aa765d61d8327deb882cf99', 'SY', '2020-05-06 23:32:38', 463),
(2, 'Pannier', 'Pannier', '5f4dcc3b5aa765d61d8327deb882cf99', 'NG', '2021-03-13 03:20:24', 546),
(3, 'Matsoft', 'Matsoft', '5f4dcc3b5aa765d61d8327deb882cf99', 'IE', '2021-01-08 06:41:00', 868),
(4, 'Ronstring', 'Ronstring', '5f4dcc3b5aa765d61d8327deb882cf99', 'UA', '2020-09-01 10:33:50', 620),
(5, 'Namfix', 'Namfix', '5f4dcc3b5aa765d61d8327deb882cf99', 'MU', '2020-09-24 09:28:21', 73),
(6, 'Viva', 'Viva', '5f4dcc3b5aa765d61d8327deb882cf99', 'CN', '2020-05-30 10:39:13', 709),
(7, 'Transcof', 'Transcof', '5f4dcc3b5aa765d61d8327deb882cf99', 'BD', '2020-08-07 07:35:34', 734),
(8, 'Latlux', 'Latlux', '5f4dcc3b5aa765d61d8327deb882cf99', 'TJ', '2020-04-11 06:58:01', 869),
(9, 'Sonair', 'Sonair', '5f4dcc3b5aa765d61d8327deb882cf99', 'RU', '2020-08-26 11:10:44', 216),
(10, 'Stim', 'Stim', '5f4dcc3b5aa765d61d8327deb882cf99', 'ID', '2020-11-13 05:09:43', 997),
(11, 'Veribet', 'Veribet', '5f4dcc3b5aa765d61d8327deb882cf99', 'BR', '2020-09-10 10:31:43', 902),
(12, 'Treeflex', 'Treeflex', '5f4dcc3b5aa765d61d8327deb882cf99', 'MX', '2021-03-14 02:11:30', 114),
(13, 'Andalax', 'Andalax', '5f4dcc3b5aa765d61d8327deb882cf99', 'FM', '2020-08-03 12:23:39', 468);

-- --------------------------------------------------------

--
-- Structure de la table `player_cosmetic`
--

DROP TABLE IF EXISTS `player_cosmetic`;
CREATE TABLE IF NOT EXISTS `player_cosmetic` (
  `id_possession_cosmetic` int(11) NOT NULL AUTO_INCREMENT,
  `id_cosmetic` int(11) NOT NULL,
  `id_player` int(11) NOT NULL,
  `is_used` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_possession_cosmetic`),
  KEY `FK_cosmetic` (`id_cosmetic`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `player_cosmetic`
--

INSERT INTO `player_cosmetic` (`id_possession_cosmetic`, `id_cosmetic`, `id_player`, `is_used`) VALUES
(1, 4, 5, 0),
(2, 5, 10, 0),
(3, 1, 2, 0),
(4, 11, 5, 0),
(5, 5, 11, 0),
(6, 4, 2, 0),
(7, 10, 5, 1),
(8, 9, 2, 1),
(9, 12, 2, 0),
(10, 5, 4, 0),
(11, 8, 10, 0),
(12, 11, 7, 0),
(13, 2, 8, 0),
(14, 3, 3, 0),
(15, 10, 5, 0),
(16, 4, 5, 0),
(17, 7, 10, 1),
(18, 7, 4, 0),
(19, 11, 4, 0),
(20, 2, 10, 0),
(21, 12, 6, 1),
(22, 12, 8, 0),
(23, 6, 4, 0),
(24, 11, 2, 0),
(25, 13, 2, 0),
(26, 10, 2, 0),
(27, 2, 5, 0),
(28, 8, 9, 0),
(29, 13, 2, 0),
(30, 2, 13, 1),
(31, 10, 12, 0),
(32, 4, 13, 0),
(33, 2, 6, 0),
(34, 4, 7, 0),
(35, 8, 11, 0),
(36, 9, 11, 0),
(37, 5, 4, 0),
(38, 3, 9, 0),
(39, 5, 10, 0),
(40, 2, 2, 0),
(41, 5, 9, 1),
(42, 4, 2, 0),
(43, 13, 11, 0),
(44, 7, 2, 0),
(45, 10, 1, 1),
(46, 4, 9, 0),
(47, 4, 8, 0),
(48, 1, 2, 0),
(49, 9, 6, 0),
(50, 7, 10, 0),
(51, 10, 2, 0),
(52, 12, 8, 0),
(53, 5, 12, 0),
(54, 1, 9, 0),
(55, 3, 1, 0),
(56, 10, 4, 1),
(57, 1, 10, 0),
(58, 2, 6, 0),
(59, 6, 12, 0),
(60, 6, 3, 0),
(61, 1, 4, 0),
(62, 10, 1, 0),
(63, 2, 5, 0),
(64, 2, 13, 0),
(65, 6, 7, 0),
(66, 9, 2, 0),
(67, 12, 9, 0),
(68, 2, 3, 0),
(69, 7, 13, 0),
(70, 2, 8, 0),
(71, 3, 7, 0),
(72, 6, 11, 0),
(73, 9, 1, 0),
(74, 1, 9, 0),
(75, 7, 12, 1),
(76, 12, 9, 0),
(77, 3, 5, 0),
(78, 11, 3, 1),
(79, 9, 7, 0),
(80, 13, 8, 0),
(81, 10, 11, 0),
(82, 2, 4, 0),
(83, 9, 3, 0),
(84, 11, 9, 0),
(85, 9, 12, 0),
(86, 4, 2, 0),
(87, 9, 13, 0),
(88, 3, 7, 1),
(89, 11, 8, 0),
(90, 1, 8, 1),
(91, 11, 7, 0),
(92, 13, 7, 0),
(93, 9, 10, 0),
(94, 4, 9, 0),
(95, 10, 13, 0),
(96, 6, 11, 1),
(97, 11, 1, 0),
(98, 11, 13, 0),
(99, 8, 13, 0),
(100, 12, 1, 0);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `game`
--
ALTER TABLE `game`
  ADD CONSTRAINT `FK_player` FOREIGN KEY (`id_player`) REFERENCES `player` (`id_player`);

--
-- Contraintes pour la table `player_cosmetic`
--
ALTER TABLE `player_cosmetic`
  ADD CONSTRAINT `FK_cosmetic` FOREIGN KEY (`id_cosmetic`) REFERENCES `cosmetic` (`id_cosmetic`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
