/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : watercup

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 24/06/2019 21:39:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sequence
-- ----------------------------
DROP TABLE IF EXISTS `sequence`;
CREATE TABLE `sequence`  (
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '序列的名字',
  `current_value` int(11) NOT NULL COMMENT '序列的当前值',
  `increment` int(11) NOT NULL DEFAULT 1 COMMENT '序列的自增值',
  PRIMARY KEY (`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sequence
-- ----------------------------
INSERT INTO `sequence` VALUES ('aclSeq', 10021, 1);

-- ----------------------------
-- Table structure for sys_acl
-- ----------------------------
DROP TABLE IF EXISTS `sys_acl`;
CREATE TABLE `sys_acl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '权限编码',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '部门名称',
  `acl_module_id` int(11) NOT NULL DEFAULT 0 COMMENT '权限所在模块id',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '请求的url,可以填正则表达式',
  `type` int(11) NOT NULL DEFAULT 3 COMMENT '类型：1：菜单，2：按钮，3：其它',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '状态，1：正常，0：冻结',
  `seq` int(11) NOT NULL DEFAULT 0 COMMENT '权限在当前模块下的顺序，由小到大',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  `operator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '操作者',
  `operator_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次操作时间',
  `operator_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '最后一次更新操作者ip',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_acl
-- ----------------------------
INSERT INTO `sys_acl` VALUES (1, '10007', '进入产品管理页面', 2, '/product/productPage', 1, 1, 1, '产品管理首页', 'admin', '2019-06-10 23:01:01', '127.0.0.1');
INSERT INTO `sys_acl` VALUES (2, '10008', '产品列表', 2, '/product/productPageQuery', 3, 1, 2, '产品列表查询', 'admin', '2019-06-12 10:18:32', '127.0.0.1');
INSERT INTO `sys_acl` VALUES (3, '10009', '产品上架', 2, '/product/productOnline', 2, 1, 3, '上架产品', 'admin', '2019-06-10 23:03:55', '127.0.0.1');
INSERT INTO `sys_acl` VALUES (4, '10010', '产品下架', 2, '/product/productDownline', 2, 1, 4, '下架产品', 'admin', '2019-06-10 23:04:44', '127.0.0.1');
INSERT INTO `sys_acl` VALUES (5, '10011', '进入公告管理页面', 4, '/announcement/announcementPage', 1, 1, 1, '公告管理页面', 'admin', '2019-06-12 10:07:52', '127.0.0.1');
INSERT INTO `sys_acl` VALUES (6, '10012', '公告列表', 4, '/announcement/announcementPageQuery', 3, 1, 2, '查询公告列表', 'admin', '2019-06-12 10:18:57', '127.0.0.1');
INSERT INTO `sys_acl` VALUES (7, '10013', '公告新增', 4, '/announcement/announcementSave', 2, 1, 3, '保存公告', 'admin', '2019-06-12 10:20:33', '127.0.0.1');
INSERT INTO `sys_acl` VALUES (8, '10014', '公告修改', 4, '/announcement/announcementUpdate', 2, 1, 4, '修改公告', 'admin', '2019-06-12 10:26:52', '127.0.0.1');
INSERT INTO `sys_acl` VALUES (9, '10015', '进入订单管理首页', 3, '/order/orderPage', 1, 1, 1, '管理订单首页', 'admin', '2019-06-12 10:23:15', '127.0.0.1');
INSERT INTO `sys_acl` VALUES (10, '10016', '订单列表', 3, '/order/orderPageQuery', 3, 1, 2, '查询订单', 'admin', '2019-06-12 10:24:36', '127.0.0.1');
INSERT INTO `sys_acl` VALUES (11, '10017', '订单新增', 3, '/order/orderSave', 2, 1, 3, '新增订单', 'admin', '2019-06-12 10:25:25', '127.0.0.1');
INSERT INTO `sys_acl` VALUES (12, '10018', '订单修改', 3, '/order/orderUpdate', 2, 1, 4, '修改订单', 'admin', '2019-06-12 10:26:06', '127.0.0.1');
INSERT INTO `sys_acl` VALUES (13, '10019', '进入限时专享首页', 6, '/timeLimit/timeLimitPage', 1, 1, 1, '进入限时专享首页', 'admin', '2019-06-12 14:13:53', '127.0.0.1');
INSERT INTO `sys_acl` VALUES (14, '10020', '进入专属产品首页', 7, '/exclusive/exclusivePage', 1, 1, 1, '进入专属产品首页', 'admin', '2019-06-12 14:39:03', '127.0.0.1');
INSERT INTO `sys_acl` VALUES (15, '10021', '进入急速升级首页', 8, '/rapidy/rapidyPage', 1, 1, 1, '进入急速升级首页', 'admin', '2019-06-12 14:39:50', '127.0.0.1');

-- ----------------------------
-- Table structure for sys_acl_module
-- ----------------------------
DROP TABLE IF EXISTS `sys_acl_module`;
CREATE TABLE `sys_acl_module`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限模块id',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '权限模块名',
  `parent_id` int(11) NOT NULL DEFAULT 0 COMMENT '父模块id',
  `level` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '部门层级',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '状态，1：正常，0：冻结',
  `seq` int(11) NOT NULL DEFAULT 0 COMMENT '权限模块在当前层级下的顺序，由小到大',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  `operator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '操作者',
  `operator_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次操作时间',
  `operator_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '最后一次操作者ip',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限模块表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_acl_module
-- ----------------------------
INSERT INTO `sys_acl_module` VALUES (2, '产品管理', 0, '0', 1, 1, '管理产品', 'admin', '2019-06-12 10:04:13', '127.0.0.1');
INSERT INTO `sys_acl_module` VALUES (3, '订单管理', 0, '0', 1, 2, '管理订单', 'admin', '2019-06-10 22:22:42', '127.0.0.1');
INSERT INTO `sys_acl_module` VALUES (4, '公告管理', 0, '0', 1, 3, '管理公告信息', 'admin', '2019-06-12 10:04:43', '127.0.0.1');
INSERT INTO `sys_acl_module` VALUES (5, '活动管理', 0, '0', 1, 4, '管理活动', 'admin', '2019-06-12 10:32:17', '127.0.0.1');
INSERT INTO `sys_acl_module` VALUES (6, '限时专享活动', 5, '0.5', 1, 1, '具有时效性', 'admin', '2019-06-12 14:08:49', '127.0.0.1');
INSERT INTO `sys_acl_module` VALUES (7, '专属产品活动', 5, '0.5', 1, 2, '针对不同人群', 'admin', '2019-06-12 14:09:00', '127.0.0.1');
INSERT INTO `sys_acl_module` VALUES (8, '急速升级活动', 5, '0.5', 1, 3, '具有目的性', 'admin', '2019-06-12 14:09:10', '127.0.0.1');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '部门名称',
  `parent_id` int(11) NOT NULL DEFAULT 0 COMMENT '上级部门id',
  `level` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '部门层级',
  `seq` int(11) NOT NULL DEFAULT 0 COMMENT '部门在当前层级下的顺序，由小到大',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  `operator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '操作者',
  `operator_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次操作时间',
  `operator_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '最后一次更新操作者ip',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1, '技术部', 0, '0', 1, '提供技术服务', 'admin', '2019-06-09 20:57:07', '127.0.0.1');
INSERT INTO `sys_dept` VALUES (2, '后端开发', 1, '0.1', 1, 'java后端开发', 'admin', '2019-06-09 21:35:18', '127.0.0.1');
INSERT INTO `sys_dept` VALUES (3, '前端开发', 1, '0.1', 2, 'vue前端开发', 'admin', '2019-06-09 21:35:45', '127.0.0.1');
INSERT INTO `sys_dept` VALUES (4, 'UI界面开发', 1, '0.1', 3, '原型图开发设计', 'admin', '2019-06-09 21:38:50', '127.0.0.1');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL DEFAULT 0 COMMENT '权限更新的类型，1：部门，2：用户，3：权限模块，4：权限，5：角色，6：角色用户关系，7：角色权限关系',
  `target_id` int(11) NOT NULL DEFAULT 0 COMMENT '基于type后指定的对象id,比如用户、权限、角色等表的主键',
  `old_value` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '旧值',
  `new_value` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '新值',
  `operator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作者',
  `operator_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次操作时间',
  `operator_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '最后一次操作者ip',
  `status` int(255) NOT NULL COMMENT '当前是否复原过，0：没有，1：复原过',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '日志表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '角色名',
  `type` int(11) NOT NULL DEFAULT 1 COMMENT '角色类型，1:管理员角色，2：其它',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '状态，1：正常，0：冻结',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  `operator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '操作者',
  `operator_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次操作时间',
  `operator_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '最后一次操作者ip',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '系统管理员', 1, 1, '拥有所用权限', 'admin', '2019-06-11 15:46:38', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role` VALUES (2, '普通用户', 1, 1, '无权限', 'admin', '2019-06-11 15:48:25', '0:0:0:0:0:0:0:1');

-- ----------------------------
-- Table structure for sys_role_acl_rel
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_acl_rel`;
CREATE TABLE `sys_role_acl_rel`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色权限id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `acl_id` int(11) NOT NULL COMMENT '权限id',
  `operator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '操作者',
  `operator_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次操作时间',
  `operator_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '最后一次操作者ip',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限关系表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role_acl_rel
-- ----------------------------
INSERT INTO `sys_role_acl_rel` VALUES (1, 1, 1, 'admin', '2019-06-12 16:40:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl_rel` VALUES (2, 1, 2, 'admin', '2019-06-12 16:40:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl_rel` VALUES (3, 1, 9, 'admin', '2019-06-12 16:40:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl_rel` VALUES (4, 1, 10, 'admin', '2019-06-12 16:40:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl_rel` VALUES (5, 1, 5, 'admin', '2019-06-12 16:40:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl_rel` VALUES (6, 1, 6, 'admin', '2019-06-12 16:40:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl_rel` VALUES (7, 1, 7, 'admin', '2019-06-12 16:40:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl_rel` VALUES (8, 1, 8, 'admin', '2019-06-12 16:40:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl_rel` VALUES (9, 1, 13, 'admin', '2019-06-12 16:40:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl_rel` VALUES (10, 1, 14, 'admin', '2019-06-12 16:40:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl_rel` VALUES (11, 1, 15, 'admin', '2019-06-12 16:40:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl_rel` VALUES (12, 2, 1, 'admin', '2019-06-12 21:51:53', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl_rel` VALUES (13, 2, 2, 'admin', '2019-06-12 21:51:53', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl_rel` VALUES (14, 2, 3, 'admin', '2019-06-12 21:51:53', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl_rel` VALUES (15, 2, 4, 'admin', '2019-06-12 21:51:53', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl_rel` VALUES (16, 2, 9, 'admin', '2019-06-12 21:51:53', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl_rel` VALUES (17, 2, 10, 'admin', '2019-06-12 21:51:53', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl_rel` VALUES (18, 2, 11, 'admin', '2019-06-12 21:51:53', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl_rel` VALUES (19, 2, 12, 'admin', '2019-06-12 21:51:53', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl_rel` VALUES (20, 2, 13, 'admin', '2019-06-12 21:51:53', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl_rel` VALUES (21, 2, 14, 'admin', '2019-06-12 21:51:53', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl_rel` VALUES (22, 2, 15, 'admin', '2019-06-12 21:51:53', '0:0:0:0:0:0:0:1');

-- ----------------------------
-- Table structure for sys_role_user_rel
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user_rel`;
CREATE TABLE `sys_role_user_rel`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色用户id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `operator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '操作者',
  `operator_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次操作时间',
  `operator_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '最后一次操作者ip',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色用户关系表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role_user_rel
-- ----------------------------
INSERT INTO `sys_role_user_rel` VALUES (2, 2, 2, 'admin', '2019-06-12 21:52:19', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user_rel` VALUES (3, 2, 3, 'admin', '2019-06-12 21:52:19', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user_rel` VALUES (5, 1, 1, 'admin', '2019-06-12 22:05:59', '0:0:0:0:0:0:0:1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户密码',
  `salt` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '盐值',
  `phoneNo` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '手机号',
  `mail` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '邮箱',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  `deptId` int(11) NOT NULL DEFAULT 1 COMMENT '用户所在部门id',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '状态,1:正常，0：冻结状态，2：删除',
  `operator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '操作者',
  `operator_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后操作时间',
  `operator_ip` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '最后一次操作者ip',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', 'e2983a556ddf7140fec66bdab903fc9e', '0a1b5297dff3d309df2d5459f9c53fb0', '18270718435', 'admin@qq.com', '大佬级人物', 1, 1, 'admin', '2019-06-09 22:03:44', '127.0.0.1');
INSERT INTO `sys_user` VALUES (2, 'kubi', '42dc48308d5f47949b9d0aac310b2fbd', 'b2ed1e219fc7c893c752327ffd243be1', '13334444555', 'kubi@qq.com', '苦逼技术宅男', 2, 1, 'admin', '2019-06-09 22:38:31', '127.0.0.1');
INSERT INTO `sys_user` VALUES (3, 'xiaokubi', '31ea4ebeb2c0823f0a53551622b08dd2', '4ede29b050779953ce7019e9d3d4a0e4', '14445555666', 'xiaokubi@qq.com', '小苦逼技术宅男', 3, 1, 'admin', '2019-06-09 22:40:46', '127.0.0.1');

-- ----------------------------
-- Function structure for currval
-- ----------------------------
DROP FUNCTION IF EXISTS `currval`;
delimiter ;;
CREATE FUNCTION `currval`(seq_name VARCHAR(50))
 RETURNS int(11)
  DETERMINISTIC
BEGIN 
     DECLARE value INTEGER; 
     SET value = 0; 
     SELECT current_value INTO value 
          FROM sequence 
          WHERE name = seq_name; 
     RETURN value; 
END
;;
delimiter ;

-- ----------------------------
-- Function structure for nextval
-- ----------------------------
DROP FUNCTION IF EXISTS `nextval`;
delimiter ;;
CREATE FUNCTION `nextval`(seq_name VARCHAR(50))
 RETURNS int(11)
  DETERMINISTIC
BEGIN 
     UPDATE sequence 
          SET current_value = current_value + increment 
          WHERE name = seq_name; 
     RETURN currval(seq_name); 
END
;;
delimiter ;

-- ----------------------------
-- Function structure for setval
-- ----------------------------
DROP FUNCTION IF EXISTS `setval`;
delimiter ;;
CREATE FUNCTION `setval`(seq_name VARCHAR(50), value INTEGER)
 RETURNS int(11)
  DETERMINISTIC
BEGIN 
     UPDATE sequence 
          SET current_value = value 
          WHERE name = seq_name; 
     RETURN currval(seq_name); 
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
