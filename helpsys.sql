/*
Navicat MySQL Data Transfer

Source Server         : try
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : helpsys

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2019-06-19 09:16:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `pwd` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`name`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', '111111');
INSERT INTO `admin` VALUES ('2', 'guanliyuan', '123456');

-- ----------------------------
-- Table structure for `count`
-- ----------------------------
DROP TABLE IF EXISTS `count`;
CREATE TABLE `count` (
  `msgid` int(11) NOT NULL,
  `accessCount` int(11) NOT NULL,
  `replyCount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of count
-- ----------------------------
INSERT INTO `count` VALUES ('22', '2', '0');
INSERT INTO `count` VALUES ('23', '6', '2');
INSERT INTO `count` VALUES ('24', '1', '0');
INSERT INTO `count` VALUES ('25', '2', '1');
INSERT INTO `count` VALUES ('26', '0', '0');
INSERT INTO `count` VALUES ('27', '0', '0');
INSERT INTO `count` VALUES ('28', '3', '1');
INSERT INTO `count` VALUES ('29', '5', '0');
INSERT INTO `count` VALUES ('30', '0', '0');
INSERT INTO `count` VALUES ('31', '3', '0');
INSERT INTO `count` VALUES ('32', '2', '0');
INSERT INTO `count` VALUES ('33', '3', '1');
INSERT INTO `count` VALUES ('34', '2', '0');

-- ----------------------------
-- Table structure for `message`
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `msgid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `msgtopic` varchar(200) NOT NULL,
  `msgcontents` varchar(5000) NOT NULL,
  `msgtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `theid` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT '0' COMMENT '0:正常 1:置顶 2:加精',
  PRIMARY KEY (`msgid`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('22', '1', '怎样学好数据结构？', '最近在学数据结构，被一些算法搞得云里雾里，求大神指路。', '2019-04-20 09:26:00', '1', '0');
INSERT INTO `message` VALUES ('23', '1', '关于考研数学', '现在有很多考研数学老师，想问一下选择哪个老师比较好？', '2019-04-20 09:28:21', '6', '0');
INSERT INTO `message` VALUES ('24', '1', '关于考研英语', '现在有很多考研英语老师，想问一下选择哪个老师比较好？', '2019-05-11 09:28:42', '9', '0');
INSERT INTO `message` VALUES ('25', '1', '二战考研租房', '请问大家有没有东校区房源，考研用。', '2019-05-12 09:30:45', '11', '0');
INSERT INTO `message` VALUES ('26', '1', '前端开发学习', '现在各种前端框架盛行，请问有没有好一点的前端知识学习路线？', '2019-05-14 09:32:55', '1', '0');
INSERT INTO `message` VALUES ('27', '1', '怎么选择股票', '最近想投资一点儿钱，玩一点儿股票，有没有大佬指点迷津。', '2019-05-14 09:35:02', '5', '0');
INSERT INTO `message` VALUES ('28', '8', '关于考研择校', '现在计算机这么火热，怎样才能选择一个比较稳的学校，有什么推荐么？', '2019-05-14 09:39:25', '8', '0');
INSERT INTO `message` VALUES ('29', '8', 'jdk环境变量配置', '怎么进行jdk环境变量配置？', '2019-05-14 09:40:49', '1', '0');
INSERT INTO `message` VALUES ('30', '9', '怎么学材料力学', '有没有大神可以分享一下学习心得和笔记，求！！！', '2019-05-15 09:43:17', '2', '0');
INSERT INTO `message` VALUES ('31', '84', '算法算法算法！', '最近在学算法，可是有些算法描述长篇大论，有什么比较好的学算法的方法？', '2019-05-25 13:08:33', '1', '0');
INSERT INTO `message` VALUES ('32', '84', '关于参加编程比赛', '想要报名参加蓝桥杯比赛，但又不知道怎么准备，有大神指点迷津么？', '2019-05-25 13:16:45', '23', '-1');
INSERT INTO `message` VALUES ('33', '1', '英语二真题。', '有没有大佬建议一下英语二真题应该什么时候做才合适？', '2019-06-01 19:47:06', '9', '0');
INSERT INTO `message` VALUES ('34', '86', '答辩答辩答辩', '是的刚好结构结构', '2019-06-10 08:33:30', '23', '-1');

-- ----------------------------
-- Table structure for `reply`
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `replyid` int(11) NOT NULL AUTO_INCREMENT,
  `msgid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `replycontents` varchar(5000) NOT NULL,
  `replytime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`replyid`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reply
-- ----------------------------
INSERT INTO `reply` VALUES ('25', '25', '9', '可以加学校考研租房群，里面房源挺多的<br/>', '2019-05-25 09:45:14');
INSERT INTO `reply` VALUES ('26', '23', '9', '要根据自己的基础，基础好选张宇，基础差选汤家凤', '2019-05-25 09:46:08');
INSERT INTO `reply` VALUES ('27', '28', '84', '首先要根据自己的能力确定几所，再根据自己的复习情况选择自己想去的学校。', '2019-05-25 13:28:40');
INSERT INTO `reply` VALUES ('28', '23', '1', '还想知道线性代数的李永乐老师怎么样？', '2019-06-01 13:08:12');
INSERT INTO `reply` VALUES ('29', '33', '2', '暑假就可以开始了，坚持背诵真题是很好的方法。', '2019-06-01 19:51:12');

-- ----------------------------
-- Table structure for `theme`
-- ----------------------------
DROP TABLE IF EXISTS `theme`;
CREATE TABLE `theme` (
  `theid` int(11) NOT NULL AUTO_INCREMENT,
  `thename` varchar(30) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  PRIMARY KEY (`theid`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of theme
-- ----------------------------
INSERT INTO `theme` VALUES ('1', '计算机', '0');
INSERT INTO `theme` VALUES ('2', '材料', '0');
INSERT INTO `theme` VALUES ('3', '化工', '0');
INSERT INTO `theme` VALUES ('4', '机械', '0');
INSERT INTO `theme` VALUES ('5', '金融', '0');
INSERT INTO `theme` VALUES ('6', '考研数学', '0');
INSERT INTO `theme` VALUES ('7', '考研政治', '0');
INSERT INTO `theme` VALUES ('8', '考研择校', '0');
INSERT INTO `theme` VALUES ('9', '考研英语', '0');
INSERT INTO `theme` VALUES ('10', '二手学习资料买卖', '0');
INSERT INTO `theme` VALUES ('11', '考研租房', '0');
INSERT INTO `theme` VALUES ('23', '其他', null);
INSERT INTO `theme` VALUES ('24', '心理', null);
INSERT INTO `theme` VALUES ('25', '运动', null);
INSERT INTO `theme` VALUES ('26', '答辩', null);

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `realname` varchar(30) NOT NULL,
  `sex` varchar(2) NOT NULL,
  `birthday` varchar(20) NOT NULL,
  `city` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `qq` varchar(20) NOT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `state` int(11) NOT NULL DEFAULT '0' COMMENT '0: 正常 -1:禁用',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '159074166', '111111', 'try', '男', '1996-04-16', '河北', 'aaa@163.com', '111111', '2019-05-20 15:36:00', '0');
INSERT INTO `user` VALUES ('2', '159074167', '111111', 'wxg', '男', '1996-11-12', '安徽', 'bbb@qq.com', '222222', '2019-05-20 08:32:37', '0');
INSERT INTO `user` VALUES ('7', '159074168', '111111', 'whh', '男', '1996-12-02', '安徽', 'ccc@qq.com', '333333', '2019-05-20 09:37:05', '0');
INSERT INTO `user` VALUES ('8', '159074169', '111111', 'wjz', '男', '1996-12-05', '安徽', 'ddd@qq.com', '444444', '2019-05-20 09:49:25', '0');
INSERT INTO `user` VALUES ('9', '111111', '111111', 'Jack', '男', '1998-09-05', '湖北', 'eee@163.com', '555555', '2019-05-20 09:55:02', '0');
INSERT INTO `user` VALUES ('10', '222222', '111111', 'Selina', '女', '1997-02-02', '河南', 'fff@163.com', '666666', '2019-05-20 09:56:14', '0');
INSERT INTO `user` VALUES ('11', '333333', '111111', 'ss', '女', '1996-01-01', '安徽', 'ggg@163.com', '777777', '2019-05-21 20:05:33', '0');
INSERT INTO `user` VALUES ('12', '444444', '111111', 'mm', '女', '1998-02-01', '上海', 'hhh@qq.com', '888888', '2019-05-22 10:52:26', '0');
INSERT INTO `user` VALUES ('13', '555555', '111111', 'ww', '男', '1999-03-01', '北京', 'iii@qq.com', '999999', '2019-05-22 10:53:42', '0');
INSERT INTO `user` VALUES ('14', '666666', '111111', 'hh', '女', '1996-05-01', '重庆', 'jjj@qq.com', '123456', '2019-05-22 10:59:24', '0');
INSERT INTO `user` VALUES ('15', '777777', '111111', 'bb', '女', '1995-01-17', '成都', 'kkk@qq.com', '789123', '2019-05-22 11:00:50', '0');
INSERT INTO `user` VALUES ('84', '888888', '111111', 'rr', '男', '1997-06-16', '安徽', 'lll@qq.com', '789109', '2019-05-25 12:23:34', '-1');
INSERT INTO `user` VALUES ('85', 'bbbbbb', '111111', 'ttt', '男', '1997-06-07', '安徽', '1029012080@qq.com', '1029012080', '2019-06-07 16:27:41', '0');
DROP TRIGGER IF EXISTS `ic`;
DELIMITER ;;
CREATE TRIGGER `ic` AFTER INSERT ON `message` FOR EACH ROW BEGIN
	INSERT INTO count VALUES(new.msgid, 0, 0);
END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `rc`;
DELIMITER ;;
CREATE TRIGGER `rc` AFTER INSERT ON `reply` FOR EACH ROW BEGIN
              update count set replyCount=replyCount+1 where msgid=new.msgid;
END
;;
DELIMITER ;
