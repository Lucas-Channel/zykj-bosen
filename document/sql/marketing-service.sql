-- ----------------------------
-- Table structure for bs_template
-- ----------------------------
DROP TABLE IF EXISTS `bs_template`;
CREATE TABLE `bs_template`  (
                                          `id` varchar(32) NOT NULL COMMENT '主键',
                                          `template_name` varchar(50) NOT NULL COMMENT '模板名称',
                                          `shop_id` varchar(32) NOT NULL COMMENT '商城id',
                                          `remark` varchar(300) NULL COMMENT '说明',
                                          `logo_url` varchar(200) NULL COMMENT 'logo地址',
                                          `front_template_code` varchar(50) NULL COMMENT '前端模板编码',
                                          `front_template_name` varchar(50) NULL COMMENT '前端模板名称',
                                          `status` int default 0 COMMENT '是否启用',
                                          `template_type` int default 0 COMMENT '模板类型:0.平台模板 1.企业商城模板 2.店铺模板 3.活动模板',
                                          `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                          `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                          `updater_user` varchar(50) DEFAULT NULL,
                                          `creator_user` varchar(50) DEFAULT NULL,
                                          `del_flag` int DEFAULT 0,
                                          PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '首页装修模板';
