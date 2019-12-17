/*
 Navicat MySQL Data Transfer

 Source Server         : MySQL80
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : pch_exam

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 16/12/2019 13:54:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pchcontainer
-- ----------------------------
DROP TABLE IF EXISTS `pchcontainer`;
CREATE TABLE `pchcontainer`  (
  `t_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '服务器的自增主键id',
  `t_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '服务器的名字',
  PRIMARY KEY (`t_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pchcontainer
-- ----------------------------
INSERT INTO `pchcontainer` VALUES (1, '服务器1');
INSERT INTO `pchcontainer` VALUES (2, '服务器2');

-- ----------------------------
-- Table structure for pchrelation
-- ----------------------------
DROP TABLE IF EXISTS `pchrelation`;
CREATE TABLE `pchrelation`  (
  `l_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '联系表的自增id',
  `s_id` int(4) NOT NULL COMMENT '服务的id',
  `t_id` int(4) NOT NULL COMMENT '服务器的id',
  PRIMARY KEY (`l_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pchrelation
-- ----------------------------
INSERT INTO `pchrelation` VALUES (1, 2, 1);
INSERT INTO `pchrelation` VALUES (2, 4, 2);
INSERT INTO `pchrelation` VALUES (3, 4, 1);

-- ----------------------------
-- Table structure for pchservlet
-- ----------------------------
DROP TABLE IF EXISTS `pchservlet`;
CREATE TABLE `pchservlet`  (
  `s_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '服务的自增主键id',
  `s_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '服务的名字',
  `s_choice` int(1) NOT NULL COMMENT '服务是否可选',
  PRIMARY KEY (`s_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pchservlet
-- ----------------------------
INSERT INTO `pchservlet` VALUES (1, '服务1', 1);
INSERT INTO `pchservlet` VALUES (2, '服务2', 0);
INSERT INTO `pchservlet` VALUES (3, '服务3', 0);
INSERT INTO `pchservlet` VALUES (4, '服务4', 0);
INSERT INTO `pchservlet` VALUES (5, '服务5', 0);

SET FOREIGN_KEY_CHECKS = 1;
