use `shop`;
create table `user` (
    `id` int(11)  NOT NULL AUTO_INCREMENT,
    `name` varchar(50) DEFAULT '0',
    `password` varchar(20) NOT NULL DEFAULT '0',
    `address` varchar(100) DEFAULT '0',
    `phone` varchar(20)  DEFAULT '0',
    `mail` varchar(50) DEFAULT '0',
    `role` int(11) NOT NULL DEFAULT '0' COMMENT '用户角色',
    `money` int(16) DEFAULT '0',
    `regTime` datetime DEFAULT NULL,
    PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

create table `order`(
    `id` int(13) NOT NULL AUTO_INCREMENT,
    `userid` int(11) DEFAULT '0',
    `address` varchar(100) DEFAULT '0',
    `phone` varchar(20) DEFAULT '0',
    `totalPrice` int(11) DEFAULT '0',
    `pay` int(2) DEFAULT '0',
    `orderlist` longtext,
    PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

create table `types` (
    `id` int(12) NOT NULL AUTO_INCREMENT,
    `name` varchar(20) DEFAULT  '0',
    PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

create table `commodity`(
    `id` int(13) NOT NULL AUTO_INCREMENT,
    `name` varchar(20) DEFAULT ' ',
    `depict` longtext DEFAULT NULL,
    `price` float(11) DEFAULT '0',
    `amount` int(11) DEFAULT '0',
    `manufacturer` varchar(50) DEFAULT ' ',
    `img` varchar(100) DEFAULT ' ',
    `type` varchar(20) DEFAULT ' ',
    PRIMARY KEY (id)
)ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

create table `cart`(
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `commodityId` int(13) DEFAULT '0',
    `commodityCount` int(10) DEFAULT '0',
    `userid` int(13) NOT NULL DEFAULT '0',
    PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

create table `comment`(
    `id` int(15) NOT NULL AUTO_INCREMENT,
    `userid` int(13) NOT NULL DEFAULT '0',
    `username` varchar(20) DEFAULT '0',
    `commodityid` int(13) NOT NULL DEFAULT '0',
    `comment` varchar(400) DEFAULT '0',
    PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;