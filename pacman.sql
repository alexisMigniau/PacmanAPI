-- phpMyAdmin SQL Dump
-- version 5.0.4deb2~bpo10+1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:3306
-- Généré le : sam. 27 mars 2021 à 21:18
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
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `cosmetic`
--

INSERT INTO `cosmetic` (`id_cosmetic`, `name`, `price`) VALUES
(1, 'fortnite pacman', 50),
(2, 'PacmanBleu', 500);

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
  `solde` int(11) NOT NULL DEFAULT 0,
  `ranking_points` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `player` (`id_player`, `pseudo`, `login`, `password`, `nationality`, `solde`, `ranking_points`) VALUES
(1, 'Admin', 'admin', 'admin123', 'EN', 9999, 1563),
(2, 'Gotaga', 'gotaga', 'gotaga123', 'FR', 846, 8456),
(3, 'Wartek', 'wartek', 'wartek123', 'CH', 789, 5794),
(4, 'TheFantasio974', 'fanta', 'fanta123', 'FR', 985, 4552),
(5, 'Squeezie', 'squeezie', 'squeezie123', 'FR', 1256, 5821);


-- --------------------------------------------------------

--
-- Structure de la table `player_cosmetic`
--

CREATE TABLE `player_cosmetic` (
  `id_possession_cosmetic` int(11) NOT NULL,
  `id_cosmetic` int(11) NOT NULL,
  `id_player` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `cosmetic`
--
ALTER TABLE `cosmetic`
  ADD PRIMARY KEY (`id_cosmetic`);

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
  MODIFY `id_cosmetic` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

--
-- AUTO_INCREMENT pour la table `player`
--
ALTER TABLE `player`
  MODIFY `id_player` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

--
-- AUTO_INCREMENT pour la table `player_cosmetic`
--
ALTER TABLE `player_cosmetic`
  MODIFY `id_possession_cosmetic` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `player_cosmetic`
--
ALTER TABLE `player_cosmetic`
  ADD CONSTRAINT `FK_cosmetic` FOREIGN KEY (`id_cosmetic`) REFERENCES `cosmetic` (`id_cosmetic`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
