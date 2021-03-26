-- phpMyAdmin SQL Dump
-- version 5.0.4deb2~bpo10+1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:3306
-- Généré le : ven. 26 mars 2021 à 15:51
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
  `name` int(20) NOT NULL,
  `price` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `solde` int(4) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `player`
--

INSERT INTO `player` (`id_player`, `pseudo`, `login`, `password`, `nationality`, `date_inscription`, `solde`) VALUES
(3, 'testpseudo', 'testlogin', '250cf8b51c773f3f8dc8b4be867a9a02', 'FR', '2021-03-26 14:34:08', 0);

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
  MODIFY `id_cosmetic` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `player`
--
ALTER TABLE `player`
  MODIFY `id_player` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

--
-- AUTO_INCREMENT pour la table `player_cosmetic`
--
ALTER TABLE `player_cosmetic`
  MODIFY `id_possession_cosmetic` int(11) NOT NULL AUTO_INCREMENT;

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
