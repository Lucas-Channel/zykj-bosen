

-- ----------------------------
-- Table structure for bs_product_brand
-- ----------------------------
DROP TABLE IF EXISTS `bs_product_brand`;
CREATE TABLE `bs_product_brand`  (
                                     `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                     `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '品牌名称',
                                     `logo_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'LOGO图片',
                                     `sort` int NULL DEFAULT NULL COMMENT '排序',
                                     `create_time` datetime(0) DEFAULT NULL,
                                     `update_time` datetime(0) DEFAULT NULL,
                                     `updater_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                     `creator_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                     `del_flag` int DEFAULT 0,
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品品牌表';

-- ----------------------------
-- Records of bs_product_brand
-- ----------------------------
INSERT INTO `bs_product_brand` VALUES (1, '有来', 'http://a.youlai.tech:9000/default/5409e3deb5a14b8fa8cb4275dee0e25d.png', 1, '2021-07-11 19:56:58', '2021-07-11 20:02:54', null, null, 0);
INSERT INTO `bs_product_brand` VALUES (10, '小米', 'http://a.youlai.tech:9000/default/6a5a606fc60742919149a7861bf26cd5.jpg', 2, '2022-03-05 16:12:16', '2022-03-05 16:12:16', null, null, 0);
INSERT INTO `bs_product_brand` VALUES (11, '华硕', 'http://a.youlai.tech:9000/default/f18083f95e104a0bae3c587dee3bb2ed.png', 3, '2022-03-05 16:12:16', '2022-03-05 16:12:16', null, null, 0);
INSERT INTO `bs_product_brand` VALUES (20, '华为', 'https://oss.youlai.tech/default/ff61bd639b23491d8f2aa85d09fcf788.jpg', 1, '2022-05-06 23:08:33', '2022-05-06 23:08:33', null, null, 0);
INSERT INTO `bs_product_brand` VALUES (33, '惠普', 'https://oss.youlai.tech/default/4cf579add9544c6eaafb41ce1131559e.gif', 1, '2022-07-07 00:12:16', '2022-07-07 00:12:16', null, null, 0);

-- ----------------------------
-- Table structure for bs_product_category
-- ----------------------------
DROP TABLE IF EXISTS `bs_product_category`;
CREATE TABLE `bs_product_category`  (
                                        `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                        `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品分类名称',
                                        `parent_id` bigint NOT NULL COMMENT '父级ID',
                                        `level` int NULL DEFAULT NULL COMMENT '层级',
                                        `icon_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图标地址',
                                        `sort` int NULL DEFAULT NULL COMMENT '排序',
                                        `visible` tinyint(1) NULL DEFAULT 1 COMMENT '显示状态:( 0:隐藏 1:显示)',
                                        `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                        `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                        `updater_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                        `creator_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                        `del_flag` int DEFAULT 0,
                                        PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品分类表';

-- ----------------------------
-- Records of bs_product_category
-- ----------------------------
INSERT INTO `bs_product_category` VALUES (3, '手机配件', 0, 1, NULL, 2, 1, NULL, '2022-07-07 22:56:53', null, null, 0);
INSERT INTO `bs_product_category` VALUES (4, '智能手机', 3, 2, NULL, 1, 1, NULL, NULL, null, null, 0);
INSERT INTO `bs_product_category` VALUES (5, '5g手机', 4, 3, 'https://oss.youlai.tech/default/6ffb37110ac2434a9882b9e8968b2887.jpg', 1, 1, NULL, '2022-07-08 00:28:38', null, null, 0);
INSERT INTO `bs_product_category` VALUES (6, '电脑办公', 0, 1, 'https://www.youlai.tech/files/default/776c21c1a71848069093033f461c5f4a.jpg', 1, 1, '2022-02-25 11:22:44', '2022-07-07 22:56:38', null, null, 0);
INSERT INTO `bs_product_category` VALUES (97, '笔记本电脑', 6, 2, NULL, 100, 1, '2022-07-08 00:10:27', '2022-07-08 00:10:27', null, null, 0);
INSERT INTO `bs_product_category` VALUES (99, '轻薄本', 97, 3, 'https://oss.youlai.tech/default/2f849b96ebb54ab3a94b1b90137f1b4d.png', 100, 1, '2022-07-08 00:14:03', '2022-07-08 00:26:52', null, null, 0);
INSERT INTO `bs_product_category` VALUES (100, '全能本', 97, 3, 'https://oss.youlai.tech/default/37cc080ec61b4ce7b0583b002568ebaa.png', 100, 1, '2022-07-08 00:14:10', '2022-07-08 00:27:01', null, null, 0);
INSERT INTO `bs_product_category` VALUES (101, '游戏本', 97, 3, 'https://oss.youlai.tech/default/5c1a2d5427534b48bc382caa55197f11.png', 100, 1, '2022-07-08 00:14:18', '2022-07-08 00:27:11', null, null, 0);

-- ----------------------------
-- Table structure for bs_product_category_attribute
-- ----------------------------
DROP TABLE IF EXISTS `bs_product_category_attribute`;
CREATE TABLE `bs_product_category_attribute`  (
                                                  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                                  `category_id` bigint NOT NULL COMMENT '分类ID',
                                                  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '属性名称',
                                                  `type` tinyint NOT NULL COMMENT '类型(1:规格;2:属性;)',
                                                  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                                  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                                  `updater_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                                  `creator_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                                  `del_flag` int DEFAULT 0,
                                                  PRIMARY KEY (`id`) USING BTREE,
                                                  INDEX `fk_pms_attr_pms_category`(`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品属性表';

-- ----------------------------
-- Records of bs_product_category_attribute
-- ----------------------------
INSERT INTO `bs_product_category_attribute` VALUES (34, 5, '颜色', 1, '2021-07-11 17:57:06', '2022-07-01 00:08:19', null, null, 0);
INSERT INTO `bs_product_category_attribute` VALUES (35, 5, '规格', 1, '2021-07-11 18:00:06', '2022-07-01 00:08:19', null, null, 0);
INSERT INTO `bs_product_category_attribute` VALUES (36, 5, '上市时间', 2, '2021-07-11 18:00:08', '2022-06-01 17:41:05', null, null, 0);

-- ----------------------------
-- Table structure for bs_product_category_brand
-- ----------------------------
DROP TABLE IF EXISTS `bs_product_category_brand`;
CREATE TABLE `bs_product_category_brand`  (
                                              `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                              `category_id` bigint NOT NULL,
                                              `brand_id` bigint NOT NULL,
                                              `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                              `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                              `updater_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                              `creator_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                              `del_flag` int DEFAULT 0,
                                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of bs_product_category_brand
-- ----------------------------

INSERT INTO `bs_product_category_brand` VALUES (1, 3, 20, '2021-07-11 17:57:06', '2022-07-01 00:08:19', null, null, 0);
INSERT INTO `bs_product_category_brand` VALUES (2, 6, 11, '2021-07-11 18:00:06', '2022-07-01 00:08:19', null, null, 0);
INSERT INTO `bs_product_category_brand` VALUES (3, 6, 33, '2021-07-11 18:00:08', '2022-06-01 17:41:05', null, null, 0);


-- ----------------------------
-- Table structure for bs_product_sku
-- ----------------------------
DROP TABLE IF EXISTS `bs_product_sku`;
CREATE TABLE `bs_product_sku`  (
                                   `id` bigint NOT NULL AUTO_INCREMENT,
                                   `sku_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品编码',
                                   `product_id` bigint NOT NULL COMMENT '商品 ID',
                                   `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品名称',
                                   `spec_name_val` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品规格值拼接',
                                   `spec_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品规格值，以英文逗号(,)分割',
                                   `spec_name` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规格属性值，以;分割',
                                   `price` decimal(11,2) NULL DEFAULT NULL COMMENT '商品价格',
                                   `stock_num` int NULL DEFAULT 0 COMMENT '库存数量',
                                   `locked_stock_num` int NULL DEFAULT 0 COMMENT '锁定库存数量',
                                   `pic_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品图片地址',
                                   `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                   `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                   `vip_price` decimal(11,2) NULL DEFAULT NULL COMMENT 'vip商品价格',
                                   `updater_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                   `creator_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                   `del_flag` int DEFAULT 0,
                                   `wholesale_price` json NULL COMMENT '批发价格',
                                   `member_price` json NULL COMMENT '指定会员销售价格',
                                   `lot_number` varchar(50) NULL COMMENT '入库批次',
                                   `bar_code` varchar(100) NULL COMMENT '条形码/二维码',
                                   `sales_count` int NULL COMMENT '销量',
                                   `auto_splice_spec_for_name` int NULL COMMENT '是否自动拼接规格值作为skuName',
                                   `stock_inventory_warning` int NULL COMMENT '库存预警数量',
                                   `calculate_inventory` int NULL COMMENT '是否计算库存',
                                   `validity_start_time` datetime NULL DEFAULT NULL COMMENT '有效期开始时间',
                                   `validity_end_time` datetime NULL DEFAULT NULL COMMENT '有效期结束时间',
                                   `unit_score` decimal(11,2) DEFAULT NULL COMMENT '单位积分',
                                   `min_order` decimal(11,2) DEFAULT NULL COMMENT '最小起订',
                                   PRIMARY KEY (`id`) USING BTREE,
                                   INDEX `fk_product_id`(`product_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品库存表';

-- ----------------------------
-- Table structure for bs_product
-- ----------------------------
DROP TABLE IF EXISTS `bs_product`;
CREATE TABLE `bs_product`  (
                               `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                               `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品名称',
                               `category_id` bigint NOT NULL COMMENT '商品类型ID',
                               `brand_id` bigint NULL DEFAULT NULL COMMENT '商品品牌ID',
                               `origin_price` decimal(11,2) NOT NULL COMMENT '原价【起】，默认规格价格',
                               `sales_price` decimal(11,2) NOT NULL COMMENT '现价【起】，默认规格价格',
                               `selling_point` varchar(500) NULL COMMENT '卖点',
                               `sales_counts` int NULL DEFAULT 0 COMMENT '销量',
                               `pic_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品主图',
                               `album` json NULL COMMENT '商品图册',
                               `unit` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单位',
                               `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品简介',
                               `detail` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '商品详情',
                               `status` tinyint NULL DEFAULT 1 COMMENT '商品状态(0:下架 1:上架)',
                               `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                               `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                               `updater_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                               `creator_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                               `del_flag` int DEFAULT 0,
                               `push_date_time` datetime NULL DEFAULT NULL COMMENT '上架时间',
                               `pull_date_time` datetime NULL DEFAULT NULL COMMENT '下架时间',
                               `apply_date_time` datetime NULL DEFAULT NULL COMMENT '审核时间',
                               `merchant_id` bigint not null COMMENT '商家id',
                               `merchant_role_id` bigint not NULL COMMENT '商家角色id',
                               `product_type` int not NULL COMMENT '商品类型',
                               PRIMARY KEY (`id`) USING BTREE,
                               INDEX `fk_brand`(`brand_id`) USING BTREE,
                               INDEX `fk_category`(`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品表';


-- ----------------------------
-- Table structure for bs_product_label
-- ----------------------------
DROP TABLE IF EXISTS `bs_product_label`;
CREATE TABLE `bs_product_label`  (
                               `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                               `label_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标签名称',
                               `label_id` varchar(64) NOT NULL COMMENT '标签id',
                               `product_id` varchar(64) NULL DEFAULT NULL COMMENT '商品ID',
                               `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                               `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                               `updater_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                               `creator_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                               `del_flag` int DEFAULT 0,
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品标签表';

-- ----------------------------
-- Table structure for bs_product_attribute
-- ----------------------------
DROP TABLE IF EXISTS `bs_product_attribute`;
CREATE TABLE `bs_product_attribute`  (
                                         `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                         `product_id` bigint NOT NULL COMMENT '产品ID',
                                         `attribute_id` bigint NULL DEFAULT NULL COMMENT '属性ID',
                                         `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '属性名称',
                                         `type` tinyint NOT NULL COMMENT '类型(1:规格;2:属性;)',
                                         `default_attribute` tinyint CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否默认规格',
                                         `sort` int DEFAULT NULL COMMENT '排序',
                                         `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                         `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                         `updater_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                         `creator_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                         `del_flag` int DEFAULT 0,
                                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品属性/规格表';

-- ----------------------------
-- Records of bs_product_attribute
-- ----------------------------

-- ----------------------------
-- Table structure for bs_product_attribute_value
-- ----------------------------
DROP TABLE IF EXISTS `bs_product_attribute_value`;
CREATE TABLE `bs_product_attribute_value`  (
                                         `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                         `product_id` bigint NOT NULL COMMENT '产品ID',
                                         `product_attribute_id` bigint NULL DEFAULT NULL COMMENT '商品规格/属性ID',
                                         `value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '规格/属性值',
                                         `type` tinyint NOT NULL COMMENT '类型(1:规格;2:属性;)',
                                         `pic_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规格图片',
                                         `default_attribute` tinyint CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否默认规格',
                                         `sort` int DEFAULT NULL COMMENT '排序',
                                         `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                         `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                         `updater_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                         `creator_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                         `del_flag` int DEFAULT 0,
                                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品属性/规格-值表';


-- ----------------------------
-- Table structure for bs_product_area
-- ----------------------------
DROP TABLE IF EXISTS `bs_product_area`;
CREATE TABLE `bs_product_area`  (
                                         `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                         `product_id` bigint NOT NULL COMMENT '产品ID',
                                         `province_code` varchar(100) NULL DEFAULT NULL COMMENT '省份编码',
                                         `province_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '省份名称',
                                         `allow_all_city` tinyint NOT NULL COMMENT '是否不限制城市',
                                         `city_code` varchar(100) NULL DEFAULT NULL COMMENT '市编码',
                                         `city_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '市名称',
                                         `allow_all_region` tinyint NOT NULL COMMENT '是否不限制区域',
                                         `region_code` varchar(100) NULL DEFAULT NULL COMMENT '省份编码',
                                         `region_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '省份名称',
                                         `allow_all_community` tinyint NOT NULL COMMENT '是否不限制小区',
                                         `community_code` varchar(100) NULL DEFAULT NULL COMMENT '省份编码',
                                         `community_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '省份名称',
                                         `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                         `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                         `updater_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                         `creator_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                         `del_flag` int DEFAULT 0,
                                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品销售区域表';


-- ----------------------------
-- Table structure for bs_product_package_detail
-- ----------------------------
DROP TABLE IF EXISTS `bs_product_package_detail`;
CREATE TABLE `bs_product_package_detail`  (
                               `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                               `product_id` varchar(64) NOT NULL COMMENT '商品spuID',
                               `sku_id` varchar(64) NOT NULL COMMENT '商品skuID',
                               `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                               `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                               `updater_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                               `creator_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                               `del_flag` int DEFAULT 0,
                               PRIMARY KEY (`id`) USING BTREE,
                               INDEX `fk_product_package_detail`(`product_id`) USING BTREE,
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品包明细表';
