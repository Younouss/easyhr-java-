-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mer. 25 sep. 2019 à 11:09
-- Version du serveur :  5.7.26
-- Version de PHP :  7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `contrat`
--
CREATE DATABASE IF NOT EXISTS `contrat` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `contrat`;

-- --------------------------------------------------------

--
-- Structure de la table `archive`
--

DROP TABLE IF EXISTS `archive`;
CREATE TABLE IF NOT EXISTS `archive` (
  `ID_employee` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `registration_number` varchar(20) DEFAULT NULL,
  `surname` varchar(40) NOT NULL,
  `name` varchar(40) NOT NULL,
  `end_date` date NOT NULL,
  `ID_contract` bigint(20) UNSIGNED NOT NULL,
  `work_certificate` longblob,
  `account_balance` longblob,
  `nominative_statement` longblob,
  `year` int(10) NOT NULL,
  `status` int(2) NOT NULL,
  `date_created` datetime NOT NULL,
  `user_date_created` varchar(20) NOT NULL,
  `date_modified` datetime NOT NULL,
  `user_date_modified` varchar(20) NOT NULL,
  PRIMARY KEY (`ID_employee`),
  KEY `fk_contract2` (`ID_contract`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `contract`
--

DROP TABLE IF EXISTS `contract`;
CREATE TABLE IF NOT EXISTS `contract` (
  `ID_contract` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `contract` varchar(50) NOT NULL,
  `status` int(2) NOT NULL,
  `date_created` datetime NOT NULL,
  `user_date_created` varchar(20) NOT NULL,
  `date_modified` datetime NOT NULL,
  `user_date_modified` varchar(20) NOT NULL,
  PRIMARY KEY (`ID_contract`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `employee`
--

DROP TABLE IF EXISTS `employee`;
CREATE TABLE IF NOT EXISTS `employee` (
  `ID_employee` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `registration_number` varchar(20) DEFAULT NULL,
  `cnps` varchar(20) DEFAULT NULL,
  `surname` varchar(40) NOT NULL,
  `name` varchar(40) NOT NULL,
  `entry_date` date NOT NULL,
  `length` int(10) NOT NULL,
  `status` int(2) NOT NULL,
  `date_created` datetime NOT NULL,
  `user_date_created` varchar(20) NOT NULL,
  `date_modified` datetime NOT NULL,
  `user_date_modified` varchar(20) NOT NULL,
  `ID_function` bigint(20) UNSIGNED NOT NULL,
  `ID_observation` bigint(20) UNSIGNED DEFAULT NULL,
  `ID_contract` bigint(20) UNSIGNED NOT NULL,
  `work_certificate` longblob,
  `account_balance` longblob,
  `nominative_statement` longblob,
  PRIMARY KEY (`ID_employee`),
  KEY `fk_function` (`ID_function`),
  KEY `fk_observation` (`ID_observation`),
  KEY `fk_contract` (`ID_contract`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `function`
--

DROP TABLE IF EXISTS `function`;
CREATE TABLE IF NOT EXISTS `function` (
  `ID_function` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `description` varchar(50) NOT NULL,
  `status` int(2) NOT NULL,
  `date_created` datetime NOT NULL,
  `user_date_created` varchar(20) NOT NULL,
  `date_modified` datetime NOT NULL,
  `user_date_modified` varchar(20) NOT NULL,
  PRIMARY KEY (`ID_function`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `observation`
--

DROP TABLE IF EXISTS `observation`;
CREATE TABLE IF NOT EXISTS `observation` (
  `ID_observation` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `observation` varchar(20) DEFAULT NULL,
  `status` int(2) NOT NULL,
  `date_created` datetime NOT NULL,
  `user_date_created` varchar(20) NOT NULL,
  `date_modified` datetime NOT NULL,
  `user_date_modified` varchar(20) NOT NULL,
  PRIMARY KEY (`ID_observation`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `ID_user` int(200) UNSIGNED NOT NULL AUTO_INCREMENT,
  `account` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `status` int(2) NOT NULL,
  PRIMARY KEY (`ID_user`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`ID_user`, `account`, `password`, `status`) VALUES
(1, 'younouss', 'optimum2019', 1),
(2, 'utilisateur1', 'optimum2019', 1);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `archive`
--
ALTER TABLE `archive`
  ADD CONSTRAINT `fk_contract2` FOREIGN KEY (`ID_contract`) REFERENCES `contract` (`ID_contract`) ON UPDATE CASCADE;

--
-- Contraintes pour la table `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `fk_contract` FOREIGN KEY (`ID_contract`) REFERENCES `contract` (`ID_contract`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_function` FOREIGN KEY (`ID_function`) REFERENCES `function` (`ID_function`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_observation` FOREIGN KEY (`ID_observation`) REFERENCES `observation` (`ID_observation`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
