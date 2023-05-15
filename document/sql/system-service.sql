-- ----------------------------
-- Table structure for bs_unit
-- ----------------------------
DROP TABLE IF EXISTS `bs_unit`;
CREATE TABLE `bs_unit`  (
                                     `id` varchar(32) NOT NULL  COMMENT '主键',
                                     `unit_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '单位编码',
                                     `unit_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '单位名称',
                                     `status` int NULL DEFAULT NULL COMMENT '启用状态：1，启用，0禁用',
                                     `create_time` datetime(0) DEFAULT NULL,
                                     `update_time` datetime(0) DEFAULT NULL,
                                     `updater_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                     `creator_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                     `del_flag` int DEFAULT 0,
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '单位表';

-- ----------------------------
-- Records of bs_unit
-- ----------------------------
-- ----------------------------
-- Table structure for bs_currency
-- ----------------------------
DROP TABLE IF EXISTS `bs_currency`;
CREATE TABLE `bs_currency`  (
                            `id` varchar(32) NOT NULL  COMMENT '主键',
                            `currency_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '编码',
                            `currency_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
                            `currency_icon` varchar(64) DEFAULT NULL COMMENT '启用状态：1，启用，0禁用',
                            `create_time` datetime(0) DEFAULT NULL,
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '币种';

-- ----------------------------
-- Records of bs_currency
-- ----------------------------
INSERT INTO `bs_currency` VALUES (1, 'RMB', '人民币', '', '2021-07-11 19:56:58');



-- ----------------------------
-- Table structure for bs_pay_method
-- ----------------------------
DROP TABLE IF EXISTS `bs_pay_method`;
CREATE TABLE `bs_pay_method`  (
                                `id` varchar(32) NOT NULL  COMMENT '主键',
                                `pay_method_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '编码',
                                `pay_method_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
                                `icon_url` varchar(64) DEFAULT NULL COMMENT '图标地址',
                                `enable_flag` int NULL DEFAULT NULL COMMENT '启用状态：1，启用，0禁用',
                                `fund_model` int NULL DEFAULT NULL COMMENT '资金归集方式',
                                `create_time` datetime(0) DEFAULT NULL,
                                `update_time` datetime(0) DEFAULT NULL,
                                `updater_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                `creator_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                `del_flag` int DEFAULT 0,
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '支付方式';

-- ----------------------------
-- Records of bs_pay_method
-- ----------------------------



-- ----------------------------
-- Table structure for bs_pay_method_params
-- ----------------------------
DROP TABLE IF EXISTS `bs_pay_method_params`;
CREATE TABLE `bs_pay_method_params`  (
                                  `id` varchar(32) NOT NULL  COMMENT '主键',
                                  `pay_method_id` varchar(32) NOT NULL COMMENT '支付方式id',
                                  `param_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '编码',
                                  `param_val` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '值',
                                  `param_remark` varchar(64) DEFAULT NULL COMMENT '说明',
                                  `fund_model` int NULL DEFAULT NULL COMMENT '资金归集方式',
                                  `create_time` datetime(0) DEFAULT NULL,
                                  `update_time` datetime(0) DEFAULT NULL,
                                  `updater_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                  `creator_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                  `del_flag` int DEFAULT 0,
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '支付参数';

-- ----------------------------
-- Records of bs_pay_method_params
-- ----------------------------


-- ----------------------------
-- Table structure for bs_sys_params
-- ----------------------------
DROP TABLE IF EXISTS `bs_sys_params`;
CREATE TABLE `bs_sys_params`  (
                                         `id` varchar(32) NOT NULL  COMMENT '主键',
                                         `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '编码',
                                         `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
                                         `val` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '值',
                                         `remark` varchar(64) DEFAULT NULL COMMENT '说明',
                                         `status` int NULL DEFAULT NULL COMMENT '启用状态：1，启用，0禁用',
                                         `create_time` datetime(0) DEFAULT NULL,
                                         `update_time` datetime(0) DEFAULT NULL,
                                         `updater_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                         `creator_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                         `del_flag` int DEFAULT 0,
                                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '平台参数';

-- ----------------------------
-- Records of bs_sys_params
-- ----------------------------


-- ----------------------------
-- Table structure for bs_area
-- ----------------------------
DROP TABLE IF EXISTS `bs_area`;
CREATE TABLE `bs_area`  (
                                  `id` varchar(32) NOT NULL  COMMENT '主键',
                                  `code` varchar(64) NOT NULL COMMENT '编码',
                                  `name` varchar(64) NOT NULL COMMENT '名称',
                                  `level` int NOT NULL COMMENT '层级',
                                  `sort` int DEFAULT NULL COMMENT '排序',
                                  `parent_id` varchar(32) NULL DEFAULT NULL COMMENT '父类id',
                                  `create_time` datetime(0) DEFAULT NULL,
                                  `update_time` datetime(0) DEFAULT NULL,
                                  `updater_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                  `creator_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                  `del_flag` int DEFAULT 0,
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '省市级区域信息表';
