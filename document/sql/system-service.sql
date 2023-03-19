-- ----------------------------
-- Table structure for bs_unit
-- ----------------------------
DROP TABLE IF EXISTS `bs_unit`;
CREATE TABLE `bs_unit`  (
                                     `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                     `unit_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '单位编码',
                                     `unit_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '单位名称',
                                     `status` int NULL DEFAULT NULL COMMENT '启用状态：1，启用，0禁用',
                                     `create_time` datetime(0) DEFAULT NULL,
                                     `update_time` datetime(0) DEFAULT NULL,
                                     `updater_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                     `creator_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                     `del_flag` int DEFAULT NULL,
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '单表表';

-- ----------------------------
-- Records of bs_unit
-- ----------------------------
INSERT INTO `bs_unit` VALUES (1, 'KG', '公斤', 1, '2021-07-11 19:56:58', '2021-07-11 20:02:54', null, null, 0);
INSERT INTO `bs_unit` VALUES (10, 'BOM', '盒', 1, '2022-03-05 16:12:16', '2022-03-05 16:12:16', null, null, 0);
INSERT INTO `bs_unit` VALUES (11, 'PART', '件', 1, '2022-03-05 16:12:16', '2022-03-05 16:12:16', null, null, 0);

-- ----------------------------
-- Table structure for bs_currency
-- ----------------------------
DROP TABLE IF EXISTS `bs_currency`;
CREATE TABLE `bs_currency`  (
                            `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                            `currency_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '编码',
                            `currency_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
                            `currency_icon` varchar(64) DEFAULT NULL COMMENT '启用状态：1，启用，0禁用',
                            `create_time` datetime(0) DEFAULT NULL,
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '币种';

-- ----------------------------
-- Records of bs_currency
-- ----------------------------
INSERT INTO `bs_currency` VALUES (1, 'RMB', '人民币', '', '2021-07-11 19:56:58');



-- ----------------------------
-- Table structure for bs_pay_method
-- ----------------------------
DROP TABLE IF EXISTS `bs_pay_method`;
CREATE TABLE `bs_pay_method`  (
                                `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                `pay_method_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '编码',
                                `pay_method_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
                                `icon_url` varchar(64) DEFAULT NULL COMMENT '图标地址',
                                `enable_flag` int NULL DEFAULT NULL COMMENT '启用状态：1，启用，0禁用',
                                `create_time` datetime(0) DEFAULT NULL,
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '支付方式';

-- ----------------------------
-- Records of bs_pay_method
-- ----------------------------
INSERT INTO `bs_pay_method` VALUES (1, 'WeChat', '微信支付', '', 1, '2021-07-11 19:56:58');
INSERT INTO `bs_pay_method` VALUES (2, 'AliPay', '支付宝支付', '', 1, '2021-07-11 19:56:58');



-- ----------------------------
-- Table structure for bs_pay_method_params
-- ----------------------------
DROP TABLE IF EXISTS `bs_pay_method_params`;
CREATE TABLE `bs_pay_method_params`  (
                                  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                  `pay_method_id` bigint NOT NULL COMMENT '支付方式id',
                                  `param_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '编码',
                                  `param_val` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '值',
                                  `param_remark` varchar(64) DEFAULT NULL COMMENT '说明',
                                  `create_time` datetime(0) DEFAULT NULL,
                                  `update_time` datetime(0) DEFAULT NULL,
                                  `updater_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                  `creator_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                  `del_flag` int DEFAULT NULL,
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '支付参数';

-- ----------------------------
-- Records of bs_pay_method_params
-- ----------------------------


-- ----------------------------
-- Table structure for bs_sys_params
-- ----------------------------
DROP TABLE IF EXISTS `bs_sys_params`;
CREATE TABLE `bs_sys_params`  (
                                         `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                         `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '编码',
                                         `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
                                         `val` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '值',
                                         `remark` varchar(64) DEFAULT NULL COMMENT '说明',
                                         `status` int NULL DEFAULT NULL COMMENT '启用状态：1，启用，0禁用',
                                         `create_time` datetime(0) DEFAULT NULL,
                                         `update_time` datetime(0) DEFAULT NULL,
                                         `updater_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                         `creator_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                         `del_flag` int DEFAULT NULL,
                                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '平台参数';

-- ----------------------------
-- Records of bs_sys_params
-- ----------------------------