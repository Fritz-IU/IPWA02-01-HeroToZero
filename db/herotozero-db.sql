-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 16. Jun 2026 um 08:44
-- Server-Version: 10.4.32-MariaDB
-- PHP-Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `herotozero-db`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `benutzer`
--

DROP TABLE IF EXISTS `benutzer`;
CREATE TABLE `benutzer` (
  `ID` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `passwort` varchar(255) NOT NULL,
  `rolle` enum('ADMIN','WISSENSCHAFTLER','BENUTZER') NOT NULL DEFAULT 'BENUTZER'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `benutzer`
--

INSERT INTO `benutzer` (`ID`, `name`, `passwort`, `rolle`) VALUES
(1, 'admin', 'admin', 'ADMIN'),
(2, 'wissen', 'wissen', 'WISSENSCHAFTLER'),
(3, 'user', 'user', 'BENUTZER');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `land`
--

DROP TABLE IF EXISTS `land`;
CREATE TABLE `land` (
  `ID` int(11) NOT NULL,
  `co2Emission` double NOT NULL,
  `laendercode` char(2) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `jahr` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `land`
--

INSERT INTO `land` (`ID`, `co2Emission`, `laendercode`, `name`, `jahr`) VALUES
(1, 264155620, 'FR', 'Frankreich', 2024),
(2, 572319170, 'DE', 'Deutschland', 2024),
(3, 56367656, 'AT', 'Österreich', 2024),
(4, 31977486, 'CH', 'Schweiz', 2024),
(6, 32071708, 'DK', 'Dänemark', 2024),
(8, 171059352, 'AR', 'Argentinien', 2024),
(9, 12289037000, 'CN', 'China', 2024),
(10, 272861820, 'PL', 'Polen', 2024),
(11, 114784730, 'NL', 'Netherlands', 2024),
(12, 85456200, 'BE', 'Belgien', 2024),
(13, 7039783, 'LU', 'Luxemburg', 2024),
(14, 75623000, 'CZ', 'Tschechien', 2024),
(15, 33310624, 'IE', 'Irland', 2024),
(18, 220341280, 'ES', 'Spanien', 2024),
(26, 131624, 'LI', 'Liechtenstein', 2024),
(27, 301929760, 'IT', 'Italien', 2024),
(28, 424663, 'AD', 'Andorra', 2024),
(31, 258367860.11, 'EG', 'Ägypten', 2024),
(35, 593766000, 'DE', 'Deutschland', 2023),
(36, 898975740, 'DE', 'Deutschland', 2000),
(37, 677997700, 'DE', 'Deutschland', 2021),
(38, 667843000, 'DE', 'Deutschland', 2022),
(39, 708648960, 'DE', 'Deutschland', 2019),
(40, 647176800, 'DE', 'Deutschland', 2020),
(41, 56908956, 'AT', 'Österreich', 2023),
(42, 61454160, 'AT', 'Österreich', 2022),
(43, 65751344, 'AT', 'Österreich', 2021),
(44, 62179828, 'AT', 'Österreich', 2020),
(45, 31977486, 'CH', 'Schweiz', 2023),
(46, 32950562, 'CH', 'Schweiz', 2022);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `landupdate`
--

DROP TABLE IF EXISTS `landupdate`;
CREATE TABLE `landupdate` (
  `ID` int(11) NOT NULL,
  `co2Emission` double NOT NULL,
  `idLand` int(11) DEFAULT NULL,
  `laendercode` char(2) DEFAULT NULL,
  `logDate` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `updateStatus` enum('OFFEN','AKZEPTIERT','ABGELEHNT') DEFAULT NULL,
  `vorhandenLand` bit(1) NOT NULL,
  `jahr` int(11) NOT NULL,
  `idUser` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `landupdate`
--

INSERT INTO `landupdate` (`ID`, `co2Emission`, `idLand`, `laendercode`, `logDate`, `name`, `updateStatus`, `vorhandenLand`, `jahr`, `idUser`) VALUES
(1, 131624, NULL, 'LI', '2026-05-07 15:14:06.000000', 'Liechtenstein', 'AKZEPTIERT', b'0', 0, NULL),
(2, 220341280.55, 18, 'ES', '2026-05-07 16:34:42.000000', 'Spanien', 'AKZEPTIERT', b'1', 0, NULL),
(3, 220341280.55, 18, 'ES', '2026-05-07 16:38:08.000000', 'Spanien', 'ABGELEHNT', b'1', 0, NULL),
(4, 33310624.44, 15, 'IE', '2026-05-07 16:41:43.000000', 'Irland', 'AKZEPTIERT', b'1', 0, NULL),
(5, 220341280.33, 18, 'ES', '2026-05-07 16:48:28.000000', 'Spanien', 'ABGELEHNT', b'1', 0, NULL),
(6, 301929760, NULL, 'IT', '2026-05-09 11:31:03.000000', 'Italien', 'AKZEPTIERT', b'0', 0, NULL),
(7, 424663, NULL, 'AD', '2026-05-09 11:36:35.000000', 'Andorra', 'AKZEPTIERT', b'0', 0, NULL),
(8, 264155620, 1, 'FR', '2026-05-16 10:57:13.000000', 'Frankreich', 'AKZEPTIERT', b'1', 2024, NULL),
(9, 572319170.55, 2, 'DE', '2026-05-16 10:57:14.000000', 'Deutschland', 'AKZEPTIERT', b'1', 2024, NULL),
(10, 56367656, 3, 'AT', '2026-05-16 10:57:15.000000', 'Österreich', 'AKZEPTIERT', b'1', 2024, NULL),
(11, 31977486, 4, 'CH', '2026-05-16 10:57:15.000000', 'Schweiz', 'AKZEPTIERT', b'1', 2024, NULL),
(12, 32071708, 6, 'DK', '2026-05-16 10:57:16.000000', 'Dänemark', 'AKZEPTIERT', b'1', 2024, NULL),
(13, 171059352, 8, 'AR', '2026-05-16 10:57:16.000000', 'Argentinien', 'AKZEPTIERT', b'1', 2024, NULL),
(14, 12289037000, 9, 'CN', '2026-05-16 10:57:17.000000', 'China', 'AKZEPTIERT', b'1', 2024, NULL),
(15, 272861820, 10, 'PL', '2026-05-16 10:57:18.000000', 'Polen', 'AKZEPTIERT', b'1', 2024, NULL),
(16, 114784730, 11, 'NL', '2026-05-16 10:57:19.000000', 'Netherlands', 'AKZEPTIERT', b'1', 2024, NULL),
(17, 85456200, 12, 'BE', '2026-05-16 10:57:19.000000', 'Belgien', 'AKZEPTIERT', b'1', 2024, NULL),
(18, 7039783, 13, 'LU', '2026-05-16 10:57:20.000000', 'Luxemburg', 'AKZEPTIERT', b'1', 2024, NULL),
(19, 75623000, 14, 'CZ', '2026-05-16 10:57:20.000000', 'Tschechien', 'AKZEPTIERT', b'1', 2024, NULL),
(20, 33310624.44, 15, 'IE', '2026-05-16 10:57:21.000000', 'Irland', 'AKZEPTIERT', b'1', 2024, NULL),
(21, 220341280.55, 18, 'ES', '2026-05-16 10:57:21.000000', 'Spanien', 'AKZEPTIERT', b'1', 2024, NULL),
(22, 131624, 26, 'LI', '2026-05-16 10:57:22.000000', 'Liechtenstein', 'AKZEPTIERT', b'1', 2024, NULL),
(23, 301929760, 27, 'IT', '2026-05-16 10:57:23.000000', 'Italien', 'AKZEPTIERT', b'1', 2024, NULL),
(24, 424663, 28, 'AD', '2026-05-16 10:57:24.000000', 'Andorra', 'AKZEPTIERT', b'1', 2024, NULL),
(25, 264155620.55, 1, 'FR', '2026-05-19 08:42:03.000000', 'Frankreich', 'ABGELEHNT', b'1', 2024, NULL),
(26, 258367860, NULL, 'EG', '2026-05-19 08:51:55.000000', 'Ägypten', 'AKZEPTIERT', b'0', 2024, NULL),
(27, 593766000, NULL, 'DE', '2026-05-20 10:15:03.000000', 'Deutschland', 'AKZEPTIERT', b'0', 2023, NULL),
(28, 898975740, NULL, 'DE', '2026-05-20 16:58:14.000000', 'Deutschland', 'AKZEPTIERT', b'0', 2000, NULL),
(29, 258367860.55, 31, 'EG', '2026-05-25 10:14:50.000000', 'Ägypten', 'AKZEPTIERT', b'1', 2024, NULL),
(30, 258367860.66, NULL, 'EG', '2026-05-27 11:40:06.000000', 'Ägypten', 'ABGELEHNT', b'1', 2024, NULL),
(31, 258367860.77, NULL, 'EG', '2026-05-27 12:10:56.000000', 'Ägypten', 'ABGELEHNT', b'1', 2024, NULL),
(32, 424663.11, NULL, 'AD', '2026-05-27 12:20:37.000000', 'Andorra', 'ABGELEHNT', b'1', 2024, NULL),
(33, 258367860.99, 31, 'EG', '2026-05-27 12:39:39.000000', 'Ägypten', 'AKZEPTIERT', b'1', 2024, NULL),
(34, 677997700, NULL, 'DE', '2026-05-27 12:42:24.000000', 'Deutschland', 'AKZEPTIERT', b'0', 2021, NULL),
(35, 258367860.22, 31, 'EG', '2026-05-27 14:41:19.000000', 'Ägypten', 'AKZEPTIERT', b'1', 2024, 1),
(36, 667843000, NULL, 'DE', '2026-05-27 14:42:34.000000', 'Deutschland', 'AKZEPTIERT', b'0', 2022, 1),
(37, 708648960, NULL, 'DE', '2026-05-28 12:08:20.000000', 'Deutschland', 'AKZEPTIERT', b'0', 2019, 1),
(38, 258367860.22, 31, 'EG', '2026-06-05 09:54:43.000000', 'Ägypten', 'AKZEPTIERT', b'1', 2024, 1),
(39, 258367860.33, 31, 'EG', '2026-06-05 10:18:22.000000', 'Ägypten', 'AKZEPTIERT', b'1', 2024, 1),
(40, 258367860.44, 31, 'EG', '2026-06-08 08:43:05.000000', 'Ägypten', 'AKZEPTIERT', b'1', 2024, 1),
(41, 258367860, 31, 'EG', '2026-06-16 07:59:40.000000', 'Ägypten', 'AKZEPTIERT', b'1', 2024, 2),
(42, 647176800, NULL, 'DE', '2026-06-16 08:00:43.000000', 'Deutschland', 'AKZEPTIERT', b'0', 2020, 2),
(43, 56908956, NULL, 'AT', '2026-06-16 08:02:50.000000', 'Österreich', 'AKZEPTIERT', b'0', 2023, 2),
(44, 133713371337, NULL, 'AT', '2026-06-16 08:03:06.000000', 'Österreicht', 'ABGELEHNT', b'0', 2023, 2),
(45, 255555, NULL, 'AT', '2026-06-16 08:03:51.000000', 'Österreich', 'ABGELEHNT', b'0', 2023, 2),
(46, 61454160, NULL, 'AT', '2026-06-16 08:05:44.000000', 'Österreicht', 'AKZEPTIERT', b'0', 2022, 1),
(47, 65751344, NULL, 'AT', '2026-06-16 08:06:51.000000', 'Österreicht', 'AKZEPTIERT', b'0', 2021, 1),
(48, 62179828, NULL, 'AT', '2026-06-16 08:07:34.000000', 'Österreich', 'AKZEPTIERT', b'0', 2020, 1),
(49, 572319170, 2, 'DE', '2026-06-16 08:17:33.000000', 'Deutschland', 'AKZEPTIERT', b'1', 2024, 1),
(50, 220341280, 18, 'ES', '2026-06-16 08:17:43.000000', 'Spanien', 'AKZEPTIERT', b'1', 2024, 1),
(51, 33310624, 15, 'IE', '2026-06-16 08:17:54.000000', 'Irland', 'AKZEPTIERT', b'1', 2024, 1),
(52, 31977486, NULL, 'CH', '2026-06-16 08:18:30.000000', 'Schweiz', 'AKZEPTIERT', b'0', 2023, 1),
(53, 258367860.11, 31, 'EG', '2026-06-16 08:28:16.000000', 'Ägypten', 'AKZEPTIERT', b'1', 2024, 2),
(54, 32950562, NULL, 'CH', '2026-06-16 08:28:52.000000', 'Schweiz', 'AKZEPTIERT', b'0', 2022, 2);

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `benutzer`
--
ALTER TABLE `benutzer`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indizes für die Tabelle `land`
--
ALTER TABLE `land`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `UK79bh453b46iqso35mtpp44bg3` (`laendercode`,`jahr`);

--
-- Indizes für die Tabelle `landupdate`
--
ALTER TABLE `landupdate`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FKsnd2il7wbbls7hmbtda268gfs` (`idLand`),
  ADD KEY `FKe8qb3hbcsyq88fbfdxqx4h2l2` (`idUser`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `benutzer`
--
ALTER TABLE `benutzer`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT für Tabelle `land`
--
ALTER TABLE `land`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT für Tabelle `landupdate`
--
ALTER TABLE `landupdate`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `landupdate`
--
ALTER TABLE `landupdate`
  ADD CONSTRAINT `FKe8qb3hbcsyq88fbfdxqx4h2l2` FOREIGN KEY (`idUser`) REFERENCES `benutzer` (`ID`),
  ADD CONSTRAINT `FKsnd2il7wbbls7hmbtda268gfs` FOREIGN KEY (`idLand`) REFERENCES `land` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
