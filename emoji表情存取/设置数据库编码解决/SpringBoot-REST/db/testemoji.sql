/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : springbootdemo

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 02/08/2019 11:43:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for testemoji
-- ----------------------------
DROP TABLE IF EXISTS `testemoji`;
CREATE TABLE `testemoji`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '',
  `emoji` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of testemoji
-- ----------------------------
INSERT INTO `testemoji` VALUES (5, '3热情而为人', 'sdf✝[[%F0%9F%98%AD]][[%F0%9F%92%8B]][[%F0%9F%98%80]]ad3双方的');
INSERT INTO `testemoji` VALUES (6, 'tsetse', 'set[[%F0%9F%98%98]]');

SET FOREIGN_KEY_CHECKS = 1;
