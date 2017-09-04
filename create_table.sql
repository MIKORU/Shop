/*
Navicat MySQL Data Transfer

Source Server         : Test
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : shop

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2017-09-04 15:55:56
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
INSERT INTO `comment` VALUES ('0bd12191db3540d4922ea61350506a9b', '4569b51234de41479c61131908acb5ee', 'admin', '6053ac4d75534f8db0d832a54cc79ad1', '好吃！');
INSERT INTO `comment` VALUES ('17982d24b6794969bf1eefcd11af8436', '4569b51234de41479c61131908acb5ee', 'admin', '6053ac4d75534f8db0d832a54cc79ad1', '便宜的无法想象！！！！');
INSERT INTO `comment` VALUES ('3402444284504168b61e2bc21817b0bf', '4569b51234de41479c61131908acb5ee', 'admin', 'e12e09dfeae94f1ba755fc9d1065cf43', '感觉别的手机都不够这个好用啊');
INSERT INTO `comment` VALUES ('3898633b2ac14c1aad78b3a64daa2516', '4569b51234de41479c61131908acb5ee', 'admin', 'e12e09dfeae94f1ba755fc9d1065cf43', '手机好用');
INSERT INTO `comment` VALUES ('7a7cc73634c84b47b77f51f59b26784f', '4569b51234de41479c61131908acb5ee', 'admin', '6053ac4d75534f8db0d832a54cc79ad1', '食尖美味');
INSERT INTO `comment` VALUES ('b97b8b423daa4ae783604bc45161b677', '4569b51234de41479c61131908acb5ee', 'admin', 'e12e09dfeae94f1ba755fc9d1065cf43', '特别好用！！');
INSERT INTO `comment` VALUES ('e90401e16efe4560a23f4b1719ebc54d', '4569b51234de41479c61131908acb5ee', 'admin', 'e12e09dfeae94f1ba755fc9d1065cf43', '别说太多！就是好！');

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
INSERT INTO `commodity` VALUES ('6053ac4d75534f8db0d832a54cc79ad1', '奶油雪糕冰淇淋', '甜味芬芳，一口顺滑', '2.2', '991', '广州冰淇淋', 'http://localhost:8080/Shop/image/1503476944128.png', '食品');
INSERT INTO `commodity` VALUES ('b2f5d7ac4840426789614bfabf082b11', '西瓜冰淇淋', '甜味芬芳，一口顺滑', '2.2', '1001', '广州冰淇淋', 'http://localhost:8080/Shop/image/1503477158520.png', '食品');
INSERT INTO `commodity` VALUES ('cab13afc783b489d9c9b5a449cb449f6', '蓝莓冰淇淋', '酸爽甘甜', '3.5', '997', '广州冰淇淋', 'http://localhost:8080/Shop/image/1503476631370.png', '食品');
INSERT INTO `commodity` VALUES ('e12e09dfeae94f1ba755fc9d1065cf43', '华为荣耀9x', '华为荣耀9是华为荣耀旗下推下的一款智能手机，5.2英寸屏幕，大弧度3D玻璃机身，正面指纹识别的设计。', '2699', '1998', '华为技术有限公司', 'http://localhost:8080/Shop/image/1502875345638.jpg', '手机');
INSERT INTO `commodity` VALUES ('fb8bd16a27774fd2b5087fa5176ac174', '巧克力冰淇淋', '甜味芬芳，一口顺滑', '2.2', '999', '广州冰淇淋', 'http://localhost:8080/Shop/image/1503477102503.png', '食品');

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
INSERT INTO `order` VALUES ('02d6ee95142944ab8b33f644f45a9ecf', '4569b51234de41479c61131908acb5ee', '广东省深圳市龙华新区', '15220006159', '2', '1', '[{\"commoditycount\":1,\"commodityid\":\"b2f5d7ac4840426789614bfabf082b11\",\"id\":\"e445e6a779b742b8b3207ff1a1090c46\",\"userid\":\"4569b51234de41479c61131908acb5ee\",\"price\":2.2}]');
INSERT INTO `order` VALUES ('87599054a7e7475d81b29d125bf68d77', '4569b51234de41479c61131908acb5ee', '广东省深圳市龙华新区', '15220006159', '6', '1', '[{\"commoditycount\":1,\"commodityid\":\"6053ac4d75534f8db0d832a54cc79ad1\",\"id\":\"1cb05eea9de34ef4b29897f1dd8389e8\",\"userid\":\"4569b51234de41479c61131908acb5ee\",\"price\":2.2},{\"commoditycount\":1,\"commodityid\":\"cab13afc783b489d9c9b5a449cb449f6\",\"id\":\"a944dd860e2d432ea094181d329e5a07\",\"userid\":\"4569b51234de41479c61131908acb5ee\",\"price\":3.5}]');

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
INSERT INTO `types` VALUES ('15241', '手机');

-- ----------------------------
-- Table structure for `urole`
-- ----------------------------
DROP TABLE IF EXISTS `urole`;
CREATE TABLE `urole` (
  `rid` int(2) NOT NULL,
  `rname` varchar(50) NOT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of urole
-- ----------------------------
INSERT INTO `urole` VALUES ('0', '用户');
INSERT INTO `urole` VALUES ('1', '管理员');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `name` varchar(50) DEFAULT '0',
  `password` varchar(50) NOT NULL DEFAULT '0',
  `address` varchar(100) DEFAULT '0',
  `phone` varchar(20) DEFAULT '0',
  `mail` varchar(50) DEFAULT '0',
  `role` int(11) NOT NULL DEFAULT '0' COMMENT '用户角色',
  `money` int(16) DEFAULT '0',
  `regTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_role` (`role`),
  CONSTRAINT `user_role` FOREIGN KEY (`role`) REFERENCES `urole` (`rid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('4569b51234de41479c61131908acb5ee', 'admin', '202cb962ac59075b964b07152d234b70', '广东省深圳市龙华新区', '15220006159', 'xiajing@gmail.com', '1', '6056', '2017-08-09 00:00:00');
INSERT INTO `user` VALUES ('4569b51234de41479c61131908acb5eq', 'mikoru', 'b7f6593421d9f21bdd5caef01b24f5c8', '广东省广州市赤沙', '15223652659', '1244185684@qq.com', '0', '5', '2015-06-13 00:00:00');
INSERT INTO `user` VALUES ('85f063c39d494377991629a6fb640491', 'alice', '250cf8b51c773f3f8dc8b4be867a9a02', '广州猎德天盈广场', '15296636953', '1144185684@126.com', '0', '0', '2017-08-25 16:15:02');
