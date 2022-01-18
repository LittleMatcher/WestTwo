/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50536
Source Host           : localhost:3306
Source Database       : westtwoweather

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2022-01-19 00:05:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `city`
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `city_name` varchar(256) DEFAULT NULL,
  `city_id` int(32) NOT NULL,
  `city_longitude` varchar(256) DEFAULT NULL,
  `city_latitude` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`city_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of city
-- ----------------------------
INSERT INTO `city` VALUES ('北京', '101010100', '116.40528', '39.90498');
INSERT INTO `city` VALUES ('上海', '101020100', '121.47264', '31.23170');
INSERT INTO `city` VALUES ('福州', '101230101', '119.30623', '26.07530');

-- ----------------------------
-- Table structure for `weather`
-- ----------------------------
DROP TABLE IF EXISTS `weather`;
CREATE TABLE `weather` (
  `fxDate` varchar(128) NOT NULL DEFAULT '',
  `id` int(9) NOT NULL DEFAULT '0',
  `tempMax` varchar(256) DEFAULT NULL,
  `tempMin` varchar(256) DEFAULT NULL,
  `textDay` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`,`fxDate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weather
-- ----------------------------
INSERT INTO `weather` VALUES ('2022-01-18', '101010100', '3', '-7', '晴');
INSERT INTO `weather` VALUES ('2022-01-19', '101010100', '-1', '-8', '多云');
INSERT INTO `weather` VALUES ('2022-01-20', '101010100', '-2', '-7', '多云');
INSERT INTO `weather` VALUES ('2022-01-18', '101020100', '11', '6', '晴');
INSERT INTO `weather` VALUES ('2022-01-19', '101020100', '14', '5', '多云');
INSERT INTO `weather` VALUES ('2022-01-20', '101020100', '8', '4', '多云');
INSERT INTO `weather` VALUES ('2022-01-18', '101230101', '13', '11', '阴');
INSERT INTO `weather` VALUES ('2022-01-19', '101230101', '19', '10', '阴');
INSERT INTO `weather` VALUES ('2022-01-20', '101230101', '16', '10', '阴');
