-- ----------------------------
-- Table structure for bs_drools_condition_col
-- ----------------------------
DROP TABLE IF EXISTS `bs_drools_condition_col`;
CREATE TABLE `bs_drools_condition_col`  (
                                   `id` varchar(32) NOT NULL COMMENT '主键',
                                   `drools_condition_col_code` varchar(64) NOT NULL COMMENT '规则列编码',
                                   `drools_condition_col_name` varchar(64) NOT NULL COMMENT '规则列名称',
                                   `drools_condition_col_desc` varchar(200) NOT NULL COMMENT '描述',
                                   `status` int NULL DEFAULT NULL COMMENT '启用状态：1，启用，0禁用',
                                   `create_time` datetime DEFAULT NULL,
                                   `update_time` datetime DEFAULT NULL,
                                   `updater_user` varchar(50) DEFAULT NULL,
                                   `creator_user` varchar(50) DEFAULT NULL,
                                   `del_flag` int DEFAULT 0,
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '规则列表';

-- ----------------------------
-- Table structure for bs_drools_condition_col_item
-- ----------------------------
DROP TABLE IF EXISTS `bs_drools_condition_col_item`;
CREATE TABLE `bs_drools_condition_col_item`  (
                                   `id` varchar(32) NOT NULL COMMENT '主键',
                                   `bs_drools_condition_col_id` varchar(32) NOT NULL COMMENT '规则条件列id',
                                   `bs_drools_condition_col_item_code` varchar(64) NOT NULL COMMENT '规则条件列明细编码',
                                   `bs_drools_condition_col_item_name` varchar(64) NOT NULL COMMENT '规则条件列明细名称',
                                   `bs_drools_condition_col_item_desc` varchar(64) NOT NULL COMMENT '规则条件列明细描述',
                                   `bs_drools_condition_col_item_object_name` varchar(64) NOT NULL COMMENT '条件对象名称',
                                   `bs_drools_condition_col_item_key` varchar(64) NOT NULL COMMENT '条件具体字段',
                                   `bs_drools_condition_col_item_value` varchar(200) NOT NULL COMMENT '条件值',
                                   `bs_drools_condition_col_item_operator` varchar(50) NOT NULL COMMENT '条件操作符',
                                   `field_type` varchar(50) NOT NULL COMMENT '条件明细列类型',
                                   `field_select_url` varchar(200) NOT NULL COMMENT '接口地址',
                                   `field_select_vf` varchar(50) NOT NULL COMMENT '传递的值的key',
                                   `field_select_tf` varchar(50) NOT NULL COMMENT '显示值的key',
                                   `status` int NULL DEFAULT NULL COMMENT '启用状态：1，启用，0禁用',
                                   `create_time` datetime DEFAULT NULL,
                                   `update_time` datetime DEFAULT NULL,
                                   `updater_user` varchar(50) DEFAULT NULL,
                                   `creator_user` varchar(50) DEFAULT NULL,
                                   `del_flag` int DEFAULT 0,
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '规则列表明细实体类';


-- ----------------------------
-- Table structure for bs_drools_action_col
-- ----------------------------
DROP TABLE IF EXISTS `bs_drools_action_col`;
CREATE TABLE `bs_drools_action_col`  (
                                            `id` varchar(32) NOT NULL COMMENT '主键',
                                            `drools_action_col_code` varchar(64) NOT NULL COMMENT '规则结果列编码',
                                            `drools_action_col_name` varchar(64) NOT NULL COMMENT '规则结果列名称',
                                            `drools_action_col_desc` varchar(200) NOT NULL COMMENT '描述',
                                            `status` int NULL DEFAULT NULL COMMENT '启用状态：1，启用，0禁用',
                                            `create_time` datetime DEFAULT NULL,
                                            `update_time` datetime DEFAULT NULL,
                                            `updater_user` varchar(50) DEFAULT NULL,
                                            `creator_user` varchar(50) DEFAULT NULL,
                                            `del_flag` int DEFAULT 0,
                                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '规则结果列表';


-- ----------------------------
-- Table structure for bs_drools_action_col_item
-- ----------------------------
DROP TABLE IF EXISTS `bs_drools_action_col_item`;
CREATE TABLE `bs_drools_action_col_item`  (
                                         `id` varchar(32) NOT NULL COMMENT '主键',
                                         `drools_action_col_code` varchar(64) NOT NULL COMMENT '规则结果列编码',
                                         `drools_action_col_name` varchar(64) NOT NULL COMMENT '规则结果列名称',
                                         `drools_action_col_desc` varchar(200) NOT NULL COMMENT '描述',
                                         `status` int NULL DEFAULT NULL COMMENT '启用状态：1，启用，0禁用',
                                         `create_time` datetime DEFAULT NULL,
                                         `update_time` datetime DEFAULT NULL,
                                         `updater_user` varchar(50) DEFAULT NULL,
                                         `creator_user` varchar(50) DEFAULT NULL,
                                         `del_flag` int DEFAULT 0,
                                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '规则结果列明细表';

-- ----------------------------
-- Table structure for bs_drools_script 用于存储drools代码
-- ----------------------------
DROP TABLE IF EXISTS `bs_drools_script`;
CREATE TABLE `bs_drools_script`  (
                                     `id` varchar(32) NOT NULL COMMENT '主键',
                                     `data_source_id` varchar(32) NOT NULL COMMENT '数据源id',
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
