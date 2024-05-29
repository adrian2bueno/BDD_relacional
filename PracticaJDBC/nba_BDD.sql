CREATE DATABASE  IF NOT EXISTS `nba` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `nba`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: nba
-- ------------------------------------------------------
-- Server version	8.4.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `historic_players`
--

DROP TABLE IF EXISTS `historic_players`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `historic_players` (
  `id` int DEFAULT NULL,
  `punts` decimal(5,2) DEFAULT NULL,
  `rebots` decimal(4,2) DEFAULT NULL,
  `assistencies` decimal(4,2) DEFAULT NULL,
  `ultim_equip` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historic_players`
--

LOCK TABLES `historic_players` WRITE;
/*!40000 ALTER TABLE `historic_players` DISABLE KEYS */;
INSERT INTO `historic_players` VALUES (21,138.90,33.40,44.50,'Cavaliers'),(22,116.70,27.80,19.50,'Thunder'),(23,105.50,22.30,27.80,'Warriors'),(24,77.80,36.10,16.70,'Bucks'),(25,38.80,11.10,13.90,'Mavericks'),(26,100.00,25.00,28.90,'Rockets'),(27,66.60,27.70,12.20,'Pelicans'),(28,83.30,23.80,15.50,'Raptors'),(29,94.40,16.70,24.40,'Trail Blazers'),(30,61.10,31.10,22.20,'Nuggets'),(31,55.60,16.10,18.30,'Celtics'),(32,77.80,20.00,13.90,'Bulls'),(33,69.40,33.30,12.20,'76ers'),(34,61.10,13.30,21.10,'Suns'),(35,49.40,8.90,24.40,'Hawks'),(36,46.70,24.40,12.20,'Timberwolves'),(37,65.00,13.90,18.30,'Wizards'),(38,41.10,16.70,8.90,'Pelicans'),(39,54.40,13.90,17.20,'Jazz'),(40,44.40,12.80,21.70,'Grizzlies');
/*!40000 ALTER TABLE `historic_players` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matches`
--

DROP TABLE IF EXISTS `matches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `matches` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_visitante` int DEFAULT NULL,
  `punts_visitant` int DEFAULT NULL,
  `id_local` int DEFAULT NULL,
  `punts_local` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matches`
--

LOCK TABLES `matches` WRITE;
/*!40000 ALTER TABLE `matches` DISABLE KEYS */;
INSERT INTO `matches` VALUES (1,1,102,2,98),(2,3,115,4,110),(3,5,105,1,100),(4,2,110,3,112),(5,4,120,5,118),(6,6,95,7,103),(7,8,109,9,97),(8,10,101,11,106),(9,12,99,13,104),(10,14,107,15,102),(11,16,108,17,101),(12,18,112,19,109),(13,20,110,6,107),(14,7,105,8,103),(15,9,98,10,101),(16,11,100,12,97),(17,13,104,14,106),(18,15,103,16,102),(19,17,110,18,107),(20,19,109,20,106);
/*!40000 ALTER TABLE `matches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player_stats`
--

DROP TABLE IF EXISTS `player_stats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `player_stats` (
  `id_jugador` int DEFAULT NULL,
  `avg_puntos` decimal(3,1) DEFAULT NULL,
  `avg_rebotes` decimal(3,1) DEFAULT NULL,
  `avg_asistencias` decimal(3,1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player_stats`
--

LOCK TABLES `player_stats` WRITE;
/*!40000 ALTER TABLE `player_stats` DISABLE KEYS */;
INSERT INTO `player_stats` VALUES (1,27.5,9.0,8.5),(2,37.5,6.0,5.0),(3,30.0,3.5,10.0),(4,20.0,13.5,6.0),(5,22.5,7.0,8.5),(6,29.0,5.5,9.0),(7,27.0,11.0,6.5),(8,23.5,7.5,4.5),(9,21.5,11.5,8.5),(10,23.0,8.0,9.5),(11,25.0,6.5,7.5),(12,24.5,11.5,6.5),(13,28.5,11.0,6.5),(14,25.5,8.0,4.5),(15,24.5,6.5,8.5),(16,28.0,5.5,9.5),(17,26.5,8.0,5.0),(18,21.5,8.0,7.0),(19,22.5,6.0,7.0);
/*!40000 ALTER TABLE `player_stats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `players`
--

DROP TABLE IF EXISTS `players`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `players` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) DEFAULT NULL,
  `alcada` int DEFAULT NULL,
  `pes` int DEFAULT NULL,
  `equipo_actual` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_players_teams` (`equipo_actual`),
  CONSTRAINT `fk_players_teams` FOREIGN KEY (`equipo_actual`) REFERENCES `teams` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `players`
--

LOCK TABLES `players` WRITE;
/*!40000 ALTER TABLE `players` DISABLE KEYS */;
INSERT INTO `players` VALUES (1,'LeBron James',206,113,1),(2,'Kevin Durant',208,109,2),(3,'Stephen Curry',191,84,3),(4,'Giannis Antetokounmpo',211,110,4),(5,'Luka Dončić',201,104,5),(6,'James Harden',196,100,6),(7,'Anthony Davis',208,115,1),(8,'Kawhi Leonard',201,102,7),(9,'Damian Lillard',188,88,8),(10,'Nikola Jokić',211,129,9),(11,'Jayson Tatum',203,95,10),(12,'Jimmy Butler',201,104,11),(13,'Joel Embiid',213,127,6),(14,'Devin Booker',196,93,12),(15,'Trae Young',185,82,13),(16,'Karl-Anthony Towns',211,112,14),(17,'Bradley Beal',191,94,15),(18,'Zion Williamson',201,129,16),(19,'Donovan Mitchell',185,98,17),(20,'Ja Morant',191,79,18);
/*!40000 ALTER TABLE `players` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `players_matches`
--

DROP TABLE IF EXISTS `players_matches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `players_matches` (
  `id_match` int DEFAULT NULL,
  `id_jugador` int DEFAULT NULL,
  `punts` int DEFAULT NULL,
  `rebots` int DEFAULT NULL,
  `assistencies` int DEFAULT NULL,
  KEY `fk_players_matches_matches` (`id_match`),
  CONSTRAINT `fk_players_matches_matches` FOREIGN KEY (`id_match`) REFERENCES `matches` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `players_matches`
--

LOCK TABLES `players_matches` WRITE;
/*!40000 ALTER TABLE `players_matches` DISABLE KEYS */;
INSERT INTO `players_matches` VALUES (1,3,28,10,9),(1,16,15,11,1),(1,12,21,6,6),(1,19,24,3,7),(1,10,26,2,9),(1,7,30,11,3),(1,4,14,13,5),(1,9,35,5,2),(1,2,12,7,4),(1,18,22,4,7),(2,11,8,8,10),(2,30,27,9,3),(2,28,10,2,8),(2,23,18,1,5),(2,15,20,6,7),(2,27,32,4,2),(2,25,17,10,9),(2,20,6,15,4),(2,13,5,11,1),(2,22,14,7,3),(3,6,35,10,8),(3,5,7,3,5),(3,17,22,12,4),(3,26,24,9,3),(3,14,18,14,9),(3,21,21,1,7),(3,1,9,5,2),(3,8,28,8,6),(3,29,10,15,5),(3,24,30,6,7),(4,15,16,11,5),(4,25,14,7,3),(4,19,27,9,8),(4,2,8,4,1),(4,26,10,8,6),(4,13,29,10,9),(4,28,6,12,4),(4,5,15,5,2),(4,3,20,3,7),(4,30,12,9,3),(5,8,18,4,1),(5,20,32,6,9),(5,14,11,7,5),(5,12,9,5,3),(5,7,26,14,8),(5,16,5,9,6),(5,23,10,12,4),(5,18,28,3,7),(5,11,22,8,2),(5,4,25,2,5),(6,21,30,9,3),(6,10,27,6,8),(6,22,19,4,1),(6,6,18,12,7),(6,29,15,7,5),(6,1,8,5,9),(6,9,12,11,6),(6,17,28,8,2),(6,19,20,14,4),(6,27,14,3,7),(7,3,16,9,5),(7,13,6,5,9),(7,24,12,8,4),(7,26,9,3,7),(7,11,14,11,2),(7,8,22,6,1),(7,18,27,4,8),(7,30,20,7,6),(7,5,25,12,3),(7,7,18,10,7),(8,28,19,5,4),(8,23,30,6,8),(8,2,14,9,5),(8,16,32,8,1),(8,15,10,2,7),(8,1,12,12,3),(8,10,6,11,9),(8,20,21,10,6),(8,21,17,7,4),(8,29,8,4,2),(9,19,18,6,9),(9,9,26,7,3),(9,12,5,11,1),(9,13,22,3,8),(9,3,10,8,5),(9,25,15,5,7),(9,17,20,4,2),(9,27,32,2,6),(9,26,9,12,4),(9,6,14,10,7),(10,30,21,1,5),(10,5,6,3,7),(10,8,19,4,9),(10,11,28,8,6),(10,24,12,9,3),(10,18,17,10,4),(10,22,27,11,2),(10,20,14,6,8),(10,14,8,5,7),(10,4,10,7,1);
/*!40000 ALTER TABLE `players_matches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teams`
--

DROP TABLE IF EXISTS `teams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teams` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) DEFAULT NULL,
  `franquicia` varchar(50) DEFAULT NULL,
  `nom_complet` varchar(100) GENERATED ALWAYS AS (concat(`franquicia`,_utf8mb4' ',`nom`)) VIRTUAL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teams`
--

LOCK TABLES `teams` WRITE;
/*!40000 ALTER TABLE `teams` DISABLE KEYS */;
INSERT INTO `teams` (`id`, `nom`, `franquicia`) VALUES (1,'Lakers','Los Angeles'),(2,'Nets','Brooklyn'),(3,'Warriors','San Francisco'),(4,'Bucks','Milwaukee'),(5,'Mavericks','Dallas'),(6,'76ers','Philadelphia'),(7,'Clippers','Los Angeles'),(8,'Blazers','Portland'),(9,'Nuggets','Denver'),(10,'Celtics','Boston'),(11,'Heat','Miami'),(12,'Suns','Phoenix'),(13,'Hawks','Atlanta Hawks'),(14,'Timberwolves','Minneapolis'),(15,'Wizards','Washington'),(16,'Pelicans','New Orleans'),(17,'Cavaliers','Cleveland'),(18,'Grizzlies','Memphis');
/*!40000 ALTER TABLE `teams` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-29 22:22:39
