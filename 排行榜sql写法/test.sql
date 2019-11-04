/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 31/10/2019 17:42:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for test1
-- ----------------------------
DROP TABLE IF EXISTS `test1`;
CREATE TABLE `test1`  (
  `openid` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  `start_time` datetime(0) DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test1
-- ----------------------------
INSERT INTO `test1` VALUES ('test1', 70, 25, '2019-10-31 14:23:37');
INSERT INTO `test1` VALUES ('test1', 70, 13, '2019-10-31 14:23:37');
INSERT INTO `test1` VALUES ('test2', 100, 36, '2019-10-31 14:23:37');
INSERT INTO `test1` VALUES ('test2', 90, 12, '2019-10-31 14:23:37');
INSERT INTO `test1` VALUES ('test3', 100, 18, '2019-10-31 14:23:37');
INSERT INTO `test1` VALUES ('test3', 100, 12, '2019-10-31 14:23:37');
INSERT INTO `test1` VALUES ('test4', 30, 45, '2019-10-31 14:23:37');
INSERT INTO `test1` VALUES ('test3', 100, 12, '2019-10-31 14:35:31');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `openid` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `nickname` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `headimgurl` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('test1', 'jide', '11111');
INSERT INTO `user` VALUES ('test2', 'xiangqian', '22222');
INSERT INTO `user` VALUES ('test3', 'crush', '33333');

SET FOREIGN_KEY_CHECKS = 1;
