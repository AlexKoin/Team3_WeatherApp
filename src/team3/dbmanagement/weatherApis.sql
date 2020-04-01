-- MySQL dump 10.13  Distrib 8.0.19, for macos10.15 (x86_64)
--
-- Host: localhost    Database: weatherApis
-- ------------------------------------------------------
-- Server version	8.0.19

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


drop database if exists weatherApis;
create database weatherApis;
use weatherApis;


--
-- Table structure for table `cities`
--

DROP TABLE IF EXISTS `cities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cities` (
  `CityName` varchar(30) NOT NULL,
  `ApiAeris` varchar(150) NOT NULL,
  `ApiClimaCell` varchar(150) NOT NULL,
  `ApiDarkSky` varchar(150) NOT NULL,
  `ApiOpenWeatherMap` varchar(150) NOT NULL,
  `ApiWeatherApi` varchar(150) NOT NULL,
  `ApiWeatherBit` varchar(150) NOT NULL,
  `ApiWeatherStack` varchar(150) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cities`
--

LOCK TABLES `cities` WRITE;
/*!40000 ALTER TABLE `cities` DISABLE KEYS */;
INSERT INTO `cities` VALUES ('Riga','riga,lv','lat=56.946&lon=24.105','56.946285,24.105078','Riga,lv','Riga','Riga,lv','Riga'),('London','London,uk','lat=51.509865&lon=-0.118092','51.509865,-0.118092','London,uk','London','London,uk','London'),('Paris','paris,fr','lat=2.35&lon=48.85','2.35,48.85','paris,fr','paris','paris,fr','paris'),('Vilnius','vilnius,lt','lat=54.687157&lon=25.279652','54.687157,25.279652','Vilnius,lt','Vilnius','Vilnius,lt','Vilnius'),('Tirana','tirana,al','lat=41.327953&lon=19.819025','41.327953,19.819025','tirana,al','tirana','tirana,al','tirana'),('Andorra la Vella','andorra,ad','lat=42.50779&lon=1.52109','42.50779,1.52109','andorra,ad','andorra','andorra,ad','andorra'),('Yerevan','yerevan,am','lat=40.177200&lon=44.503490','40.177200,44.503490','yerevan,am','yerevan','yerevan,am','yerevan'),('Vienna','vienna,at','lat=48.21003&lon=16.363449','48.210033,16.363449','vienna,at','vienna','vienna,at','vienna'),('Baku','baku,az','lat=40.409264&lon=49.867092','40.409264,49.867092','baku,az','baku','baku,az','baku'),('Minsk','minsk,by','lat=53.893009&lon=27.567444','53.893009,27.567444','minsk,by','minsk','minsk,by','minsk'),('Brussels','brussels,be','lat=50.85045&lon=4.34878','50.85045,4.34878','brussels,be','brussels','brussels,be','brussels'),('Sarajevo','sarajevo,ba','lat=43.856430&lon=18.413029','43.856430,18.413029','sarajevo,ba','sarajevo','sarajevo,ba','sarajevo'),('Sofia','sofia,bg','lat=42.698334&lon=23.319941','42.698334,23.319941','sofia,bg','sofia','sofia,bg','sofia'),('Zagreb','zagreb,hr','lat=45.815399&lon=15.966568','45.815399,15.966568','zagreb,hr','zagreb','zagreb,hr','zagreb'),('Nicosia','nicosia,cy','lat=35.185566&lon=33.382275','35.185566,33.382275','nicosia,cy','nicosia','nicosia,cy','nicosia'),('Prague','prague,cz','lat=50.073658&lon=14.418540','50.073658,14.418540','prague,cz','prague','prague,cz','prague'),('Copenhagen','copenhagen,dk','lat=55.676098&lon=12.568337','55.676098,12.568337','copenhagen,dk','copenhagen','copenhagen,dk','copenhagen'),('Tallinn','tallinn,ee','lat=59.436962&lon=24.753574','59.436962,24.753574','tallinn,ee','tallinn','tallinn,ee','tallinn'),('Helsinki','helsinki,fi','lat=60.192059&lon=24.945831','60.192059,24.945831','helsinki,fi','helsinki','helsinki,fi','helsinki'),('Tbilisi','tbilisi,ge','lat=41.716667&lon=44.783333','41.716667,44.783333','tbilisi,ge','tbilisi','tbilisi,ge','tbilisi'),('Berlin','berlin,de','lat=52.520008&lon=13.404954','52.520008,13.404954','berlin,de','berlin','berlin,de','berlin'),('Athens','athens,gr','lat=60.192059&lon=24.945831','37.983810,23.727539','athens,gr','athens','athens,gr','athens'),('Budapest','budapest,hu','lat=47.497913&lon=19.040236','47.497913,19.040236','budapest,hu','budapest','budapest,hu','budapest'),('Reykjavik','reykjavik,is','lat=64.128288&lon=-21.827774','64.128288,-21.827774','reykjavik,is','reykjavik','reykjavik,is','reykjavik'),('Dublin','dublin,ie','lat=53.350140&lon=-6.266155','53.350140,-6.266155','dublin,ie','dublin','dublin,ie','dublin'),('Rome','rome,it','lat=41.902782&lon=12.496366','41.902782,12.496366','rome,it','rome','rome,it','rome'),('Vaduz','vaduz,li','lat=47.14151&lon=9.5215397','47.14151,9.5215397','vaduz,li','vaduz','vaduz,li','vaduz'),('Pristina','pristina,xk','lat=42.667542&lon=21.166191','42.667542,21.166191','pristina,xk','pristina','pristina,xk','pristina'),('Luxembourg','luxembourg,lu','lat=49.611622&lon=6.131935','49.611622,6.131935','luxembourg,lu','luxembourg','luxembourg,lu','luxembourg'),('Valetta','valetta,mt','lat=35.89972&lon=14.51472','35.89972,14.51472','valetta,mt','valetta','valetta,mt','valetta'),('Chisinau','chisinau,md','lat=47.003670&lon=28.907089','47.003670,28.907089','chisinau,md','chisinau','chisinau,md','chisinau'),('Monaco','monaco,mc','lat=43.733334&lon=7.416667','43.733334,7.416667','monaco,mc','monaco','monaco,mc','monaco'),('Podgorica','podgorica,me','lat=42.442574&lon=19.268646','42.442574,19.268646','podgorica,me','podgorica','podgorica,me','podgorica'),('Amsterdam','amsterdam,nl','lat=52.377956&lon=4.897070','52.377956,4.897070','amsterdam,nl','amsterdam','amsterdam,nl','amsterdam'),('Skopje','skopje,mk','lat=41.99646&lon=21.43141','41.99646,21.43141','skopje,mk','skopje','skopje,mk','skopje'),('Oslo','oslo,no','lat=59.911491&lon=10.757933','59.911491,10.757933','oslo,no','oslo','oslo,no','oslo'),('Warsaw','warsaw,pl','lat=52.237049&lon=21.017532','52.237049,21.017532','warsaw,pl','warsaw','warsaw,pl','warsaw'),('Lisbon','lisbon,pt','lat=38.736946&lon=-9.142685','38.736946,-9.142685','lisbon,pt','lisbon','lisbon,pt','lisbon'),('Bucharest','bucharest,ro','lat=44.439663&lon=26.09630','44.439663,26.096306','bucharest,ro','bucharest','bucharest,ro','bucharest'),('Moscow','moscow,ru','lat=55.751244&lon=37.618423','55.751244,37.618423','moscow,ru','moscow','moscow,ru','moscow'),('San Marino','san+marino,sm','lat=43.93667,&lon=12.44639','43.93667,12.44639','san+marino,sm','san+marino','san+marino,sm','san+marino'),('Ankara','ankara,tr','lat=39.925533,&lon=32.866287','39.925533,32.866287','ankara,tr','ankara','ankara,tr','ankara'),('Belgrade','belgrade,rs','lat=44.787197,&lon=20.457273','44.787197,20.457273','belgrade,rs','belgrade','belgrade,rs','belgrade'),('Bratislava','bratislava,sk','lat=48.148598,&lon=17.107748','48.148598,17.107748','bratislava,sk','bratislava','bratislava,sk','bratislava'),('Ljubljana','ljubljana,sl','lat=46.056946,&lon=14.505751','46.056946,14.505751','ljubljana,sl','ljubljana','ljubljana,sl','ljubljana'),('Madrid','madrid,es','lat=40.416775,&lon=-3.703790','40.416775,-3.703790','madrid,es','madrid','madrid,es','madrid'),('Stockholm','stockholm,se','lat=59.334591,&lon=18.063240','59.334591,18.063240','stockholm,se','stockholm','stockholm,se','stockholm'),('Bern','bern,ch','lat=46.947456,&lon=7.4511238','46.947456,7.4511238','bern,ch','bern','bern,ch','bern'),('Kiev','kiev,ua','lat=50.431782,&lon=30.516382','50.431782,30.516382','kiev,ua','kiev','kiev,ua','kiev');
/*!40000 ALTER TABLE `cities` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-29 12:00:26
