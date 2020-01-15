/*
 Navicat MySQL Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : 127.0.0.1
 Source Database       : sns

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : utf-8

 Date: 01/15/2020 23:24:03 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `s_chat`
-- ----------------------------
DROP TABLE IF EXISTS `s_chat`;
CREATE TABLE `s_chat` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `source_id` int(10) unsigned NOT NULL,
  `target_id` int(11) NOT NULL,
  `type` tinyint(1) NOT NULL COMMENT '1-私聊 2-群聊',
  `msg` varchar(255) NOT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `s_friends`
-- ----------------------------
DROP TABLE IF EXISTS `s_friends`;
CREATE TABLE `s_friends` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `source_id` int(11) NOT NULL,
  `target_id` int(11) NOT NULL,
  `is_deleted` tinyint(4) DEFAULT '0' COMMENT '0-未删除 1-已删除',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0-待处理 1-通过申请 2-黑名单',
  `create_time` timestamp NOT NULL,
  `update_time` timestamp NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `s_user`
-- ----------------------------
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(16) NOT NULL,
  `password` char(32) NOT NULL,
  `nickname` varchar(32) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
