-- ----------------------------
-- Table structure for bs_system_message
-- ----------------------------
DROP TABLE IF EXISTS `bs_system_message`;
CREATE TABLE `bs_system_message`  (
                                      `id` varchar(32) NOT NULL COMMENT '主键',
                                      `message_target_group_type` int NOT NULL COMMENT '消息群体类型',
                                      `message_target_group_val` varchar(500) NOT NULL COMMENT '消息群体值，逗号拼接',
                                      `title` varchar(200) NOT NULL COMMENT '标题',
                                      `content` text NOT NULL COMMENT '内容',
                                      `visit_url` varchar(150) default NULL COMMENT '访问地址',
                                      `effective_start_date` date default NULL COMMENT '内容有效期',
                                      `effective_end_date` date default NULL COMMENT '内容有效期',
                                      `status` int default NULL COMMENT '状态：1、草稿，2、已发布，3、作废，4、已过期',
                                      `create_time` datetime DEFAULT NULL,
                                      `update_time` datetime DEFAULT NULL,
                                      `updater_user` varchar(50) DEFAULT NULL,
                                      `creator_user` varchar(50) DEFAULT NULL,
                                      `del_flag` int DEFAULT 0,
                                      PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统消息';

-- ----------------------------
-- Table structure for bs_user_message
-- ----------------------------
DROP TABLE IF EXISTS `bs_user_message`;
CREATE TABLE `bs_user_message`  (
                                    `id` varchar(32) NOT NULL COMMENT '主键',
                                    `receive_user_id` varchar(32) NOT NULL COMMENT '接收人id',
                                    `receive_user_role_id` varchar(32) NOT NULL COMMENT '接收人角色id',
                                    `send_user_id` varchar(32) NOT NULL COMMENT '发送人id',
                                    `send_user_role_id` varchar(32) NOT NULL COMMENT '发送人角色id',
                                    `title` varchar(200) NOT NULL COMMENT '标题',
                                    `content` text NOT NULL COMMENT '内容',
                                    `visit_url` varchar(150) default NULL COMMENT '访问地址',
                                    `effective_start_date` date default NULL COMMENT '内容有效期',
                                    `effective_end_date` date default NULL COMMENT '内容有效期',
                                    `status` int default NULL COMMENT '状态',
                                    `type` int NULL DEFAULT NULL COMMENT '消息类型：1、系统消息，2、其他消息',
                                    `business_type` int NULL DEFAULT NULL COMMENT '业务消息类型：1、交易消息，2、订单消息， 3、售后，4、物流，5、库存，6、会员，7、其他',
                                    `create_time` datetime DEFAULT NULL,
                                    `update_time` datetime DEFAULT NULL,
                                    `updater_user` varchar(50) DEFAULT NULL,
                                    `creator_user` varchar(50) DEFAULT NULL,
                                    `del_flag` int DEFAULT 0,
                                    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户消息';