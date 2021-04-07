-- phpMyAdmin SQL Dump
-- version 5.0.4deb2~bpo10+1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:3306
-- Généré le : ven. 02 avr. 2021 à 12:18
-- Version du serveur :  10.3.27-MariaDB-0+deb10u1
-- Version de PHP : 7.3.27-1~deb10u1

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

CREATE TABLE `cosmetic` (
  `id_cosmetic` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `price` int(11) NOT NULL,
  `color` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `cosmetic`
--

INSERT INTO `cosmetic` (`id_cosmetic`, `name`, `price`, `color`) VALUES
(1, 'itemA', 50, 'blue'),
(2, 'itemB', 100, 'pink'),
(3, 'itemC', 150, 'magenta');

-- --------------------------------------------------------

--
-- Structure de la table `game`
--

CREATE TABLE `game` (
  `id_game` int(11) NOT NULL,
  `id_player` int(11) NOT NULL,
  `score` int(11) NOT NULL DEFAULT 0,
  `time` int(11) NOT NULL DEFAULT 0,
  `date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `game`
--

INSERT INTO `game` (`id_game`, `id_player`, `score`, `time`, `date`) VALUES
(1, 2, 2569, 125, '2021-03-31 08:12:44'),
(2, 2, 2689, 119, '2021-03-31 08:12:44'),
(3, 2, 1998, 168, '2021-03-31 08:12:44'),
(4, 2, 2351, 110, '2021-03-31 08:12:44'),
(5, 3, 2960, 91, '2021-03-31 08:12:44'),
(6, 3, 2413, 101, '2021-03-31 08:12:44'),
(7, 4, 1722, 178, '2021-03-31 08:12:44'),
(8, 4, 1455, 198, '2021-03-31 08:12:44'),
(9, 4, 1910, 155, '2021-03-31 08:12:44'),
(10, 5, 1885, 165, '2021-03-31 08:12:44'),
(11, 5, 1733, 177, '2021-03-31 08:12:44'),
(12, 5, 2073, 148, '2021-03-31 08:12:44'),
(13, 6, 1910, 155, '2021-03-31 08:12:44'),
(14, 6, 1885, 165, '2021-03-31 08:12:44'),
(15, 6, 1733, 177, '2021-03-31 08:12:44'),
(16, 6, 2073, 148, '2021-03-31 08:12:44');

-- --------------------------------------------------------

--
-- Structure de la table `player`
--

CREATE TABLE `player` (
  `id_player` int(11) NOT NULL,
  `pseudo` varchar(50) NOT NULL,
  `login` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `nationality` varchar(2) NOT NULL DEFAULT 'FR',
  `date_inscription` timestamp NOT NULL DEFAULT current_timestamp(),
  `solde` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `player`
--

INSERT INTO `player` (`id_player`, `pseudo`, `login`, `password`, `nationality`, `date_inscription`, `solde`) VALUES
(1, 'Admin', 'admin', 'admin123', 'EN', '2021-03-31 08:12:44', 9999),
(2, 'Gotaga', 'gotaga', 'gotaga123', 'FR', '2021-03-31 08:12:44', 846),
(3, 'Wartek', 'wartek', 'wartek123', 'CH', '2021-03-31 08:12:44', 789),
(4, 'TheFantasio974', 'fanta', 'fanta123', 'FR', '2021-03-31 08:12:44', 985),
(5, 'Squeezie', 'squeezie', 'squeezie123', 'FR', '2021-03-31 08:12:44', 1256),
(6, 'pseudo', 'loginlogin', '25d55ad283aa400af464c76d713c07ad', 'FR', '2021-03-31 08:33:22', 3590),
(7, 'pseudo2', 'loginlogin2', '25d55ad283aa400af464c76d713c07ad', 'FR', '2021-03-31 17:51:09', 3250);

-- --------------------------------------------------------

--
-- Structure de la table `player_cosmetic`
--

CREATE TABLE `player_cosmetic` (
  `id_possession_cosmetic` int(11) NOT NULL,
  `id_cosmetic` int(11) NOT NULL,
  `id_player` int(11) NOT NULL,
  `is_used` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `player_cosmetic`
--

INSERT INTO `player_cosmetic` (`id_possession_cosmetic`, `id_cosmetic`, `id_player`, `is_used`) VALUES
(1, 1, 6, 0),
(2, 2, 6, 0),
(3, 3, 6, 1),
(4, 1, 7, 0),
(5, 2, 7, 0),
(6, 3, 7, 1);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `cosmetic`
--
ALTER TABLE `cosmetic`
  ADD PRIMARY KEY (`id_cosmetic`);

--
-- Index pour la table `game`
--
ALTER TABLE `game`
  ADD PRIMARY KEY (`id_game`),
  ADD KEY `FK_player` (`id_player`);

--
-- Index pour la table `player`
--
ALTER TABLE `player`
  ADD PRIMARY KEY (`id_player`),
  ADD UNIQUE KEY `pseudo` (`pseudo`),
  ADD UNIQUE KEY `login` (`login`);

--
-- Index pour la table `player_cosmetic`
--
ALTER TABLE `player_cosmetic`
  ADD PRIMARY KEY (`id_possession_cosmetic`),
  ADD KEY `FK_cosmetic` (`id_cosmetic`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `cosmetic`
--
ALTER TABLE `cosmetic`
  MODIFY `id_cosmetic` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `game`
--
ALTER TABLE `game`
  MODIFY `id_game` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT pour la table `player`
--
ALTER TABLE `player`
  MODIFY `id_player` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `player_cosmetic`
--
ALTER TABLE `player_cosmetic`
  MODIFY `id_possession_cosmetic` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

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
