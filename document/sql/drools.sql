-- ----------------------------
-- Table structure for bs_drools_col
-- ----------------------------
DROP TABLE IF EXISTS `bs_drools_col`;
CREATE TABLE `bs_drools_col`  (
                                   `id` varchar(32) NOT NULL COMMENT '主键',
                                   `drools_col_code` varchar(64) NOT NULL COMMENT '规则列编码',
                                   `drools_col_name` varchar(64) NOT NULL COMMENT '规则列名称',
                                   `drools_col_script` text NOT NULL COMMENT '规则列代码',
                                   `drools_col_desc` varchar(200) NOT NULL COMMENT '描述',
                                   `status` int NULL DEFAULT NULL COMMENT '启用状态：1，启用，0禁用',
                                   `create_time` datetime DEFAULT NULL,
                                   `update_time` datetime DEFAULT NULL,
                                   `updater_user` varchar(50) DEFAULT NULL,
                                   `creator_user` varchar(50) DEFAULT NULL,
                                   `del_flag` int DEFAULT 0,
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '规则列表';

-- ----------------------------
-- Table structure for bs_drools_col
-- ----------------------------
DROP TABLE IF EXISTS `bs_drools_result_col`;
CREATE TABLE `bs_drools_result_col`  (
                                   `id` varchar(32) NOT NULL COMMENT '主键',
                                   `drools_result_col_code` varchar(64) NOT NULL COMMENT '规则结果列编码',
                                   `drools_result_col_name` varchar(64) NOT NULL COMMENT '规则结果列名称',
                                   `drools_result_col_script` text NOT NULL COMMENT '规则结果列代码',
                                   `drools_result_col_desc` varchar(200) NOT NULL COMMENT '描述',
                                   `status` int NULL DEFAULT NULL COMMENT '启用状态：1，启用，0禁用',
                                   `create_time` datetime DEFAULT NULL,
                                   `update_time` datetime DEFAULT NULL,
                                   `updater_user` varchar(50) DEFAULT NULL,
                                   `creator_user` varchar(50) DEFAULT NULL,
                                   `del_flag` int DEFAULT 0,
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '规则结果列表';


-- ----------------------------
-- Table structure for bs_drools_info 用于存储drools代码
-- ----------------------------
DROP TABLE IF EXISTS `bs_drools_info`;
CREATE TABLE `bs_drools_info`  (
                                     `id` varchar(32) NOT NULL COMMENT '主键',
                                     `drools_code` varchar(64) NOT NULL COMMENT '规则编码',
                                     `drools_name` varchar(64) NOT NULL COMMENT '规则名称',
                                     `drools_script` text NOT NULL COMMENT '规则代码',
                                     `drools_desc` varchar(200) NOT NULL COMMENT '描述',
                                     `status` int NULL DEFAULT NULL COMMENT '启用状态：1，启用，0禁用',
                                     `drools_type` int NULL DEFAULT NULL COMMENT '规则类型',
                                     `create_time` datetime DEFAULT NULL,
                                     `update_time` datetime DEFAULT NULL,
                                     `updater_user` varchar(50) DEFAULT NULL,
                                     `creator_user` varchar(50) DEFAULT NULL,
                                     `del_flag` int DEFAULT 0,
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '规则详情表';
