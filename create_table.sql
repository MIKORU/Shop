/*
Navicat MySQL Data Transfer

Source Server         : Test
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : shop

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2017-08-16 11:31:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `cart`
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `id` varchar(32) NOT NULL,
  `commodityid` varchar(32) NOT NULL DEFAULT '0',
  `commoditycount` int(10) DEFAULT '0',
  `userid` varchar(32) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `cart_commodityid` (`commodityid`),
  KEY `cart_userid` (`userid`),
  CONSTRAINT `cart_commodityid` FOREIGN KEY (`commodityid`) REFERENCES `commodity` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cart_userid` FOREIGN KEY (`userid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cart
-- ----------------------------

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` varchar(32) NOT NULL,
  `userid` varchar(32) NOT NULL DEFAULT '0',
  `username` varchar(50) DEFAULT '0',
  `commodityid` varchar(32) NOT NULL DEFAULT '0',
  `comment` varchar(400) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `com_userid` (`userid`),
  KEY `com_commodityid` (`commodityid`),
  CONSTRAINT `com_commodityid` FOREIGN KEY (`commodityid`) REFERENCES `commodity` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `com_userid` FOREIGN KEY (`userid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('649d5a7b7b6246209f0727c6cc373c1f', '4569b51234de41479c61131908acb5ee', 'admin', '9ac268c7c93e4583982d64d49e92e61c', '特别棒！');

-- ----------------------------
-- Table structure for `commodity`
-- ----------------------------
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity` (
  `id` varchar(32) NOT NULL,
  `name` varchar(20) DEFAULT ' ',
  `depict` longtext,
  `price` float DEFAULT '0',
  `amount` int(11) DEFAULT '0',
  `manufacturer` varchar(50) DEFAULT ' ',
  `img` varchar(100) DEFAULT ' ',
  `type` varchar(20) DEFAULT ' ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commodity
-- ----------------------------
INSERT INTO `commodity` VALUES ('9ac268c7c93e4583982d64d49e92e61c', '荣耀9x', '手机', '1225', '0', '华为', '', '日用品');

-- ----------------------------
-- Table structure for `order`
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` varchar(32) NOT NULL,
  `userid` varchar(32) NOT NULL,
  `address` varchar(100) DEFAULT '0',
  `phone` varchar(20) DEFAULT '0',
  `totalprice` float(11,0) DEFAULT '0',
  `pay` int(2) DEFAULT '0',
  `orderlist` longtext,
  PRIMARY KEY (`id`),
  KEY `order_userid` (`userid`),
  CONSTRAINT `order_userid` FOREIGN KEY (`userid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for `types`
-- ----------------------------
DROP TABLE IF EXISTS `types`;
CREATE TABLE `types` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15242 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of types
-- ----------------------------
INSERT INTO `types` VALUES ('10000', '食品');
INSERT INTO `types` VALUES ('15240', '学校');
INSERT INTO `types` VALUES ('15241', '手机');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `name` varchar(50) DEFAULT '0',
  `password` varchar(20) NOT NULL DEFAULT '0',
  `address` varchar(100) DEFAULT '0',
  `phone` varchar(20) DEFAULT '0',
  `mail` varchar(50) DEFAULT '0',
  `role` int(11) NOT NULL DEFAULT '0' COMMENT '用户角色',
  `money` int(16) DEFAULT '0',
  `regTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('4569b51234de41479c61131908acb5ee', 'admin', '123', '广东省深圳市龙华新区', '15220006159', 'xiajing@gmail.com', '1', '1202', '2017-08-09 00:00:00');
INSERT INTO `user` VALUES ('4569b51234de41479c61131908acb5eq', 'mikoru', '555666', '广东省广州市赤沙', '15223652659', '1244185684@qq.com', '0', '23', '2015-06-13 00:00:00');
