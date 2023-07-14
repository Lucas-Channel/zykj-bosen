

-- ----------------------------
-- Table structure for bs_product_brand
-- ----------------------------
DROP TABLE IF EXISTS `bs_product_brand`;
CREATE TABLE `bs_product_brand`  (
                                     `id` varchar(32) NOT NULL COMMENT '主键',
                                     `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '品牌名称',
                                     `logo_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'LOGO图片',
                                     `sort` int NULL DEFAULT NULL COMMENT '排序',
                                     `create_time` datetime(0) DEFAULT NULL,
                                     `update_time` datetime(0) DEFAULT NULL,
                                     `updater_user` varchar(50) DEFAULT NULL,
                                     `creator_user` varchar(50) DEFAULT NULL,
                                     `del_flag` int DEFAULT 0,
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品品牌表';

-- ----------------------------
-- Table structure for bs_product_category
-- ----------------------------
DROP TABLE IF EXISTS `bs_product_category`;
CREATE TABLE `bs_product_category`  (
                                        `id` varchar(32) NOT NULL COMMENT '主键',
                                        `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品分类名称',
                                        `parent_id` varchar(32) NOT NULL COMMENT '父级ID',
                                        `level` int NULL DEFAULT NULL COMMENT '层级',
                                        `icon_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图标地址',
                                        `sort` int NULL DEFAULT NULL COMMENT '排序',
                                        `visible` tinyint(1) NULL DEFAULT 1 COMMENT '显示状态:( 0:隐藏 1:显示)',
                                        `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                        `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                        `updater_user` varchar(50) DEFAULT NULL,
                                        `creator_user` varchar(50) DEFAULT NULL,
                                        `del_flag` int DEFAULT 0,
                                        PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品分类表';

-- ----------------------------
-- Table structure for bs_product_category_attribute
-- ----------------------------
DROP TABLE IF EXISTS `bs_product_category_attribute`;
CREATE TABLE `bs_product_category_attribute`  (
                                                  `id` varchar(32) NOT NULL COMMENT '主键',
                                                  `category_id` varchar(32) NOT NULL COMMENT '分类ID',
                                                  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '属性名称',
                                                  `type` tinyint NOT NULL COMMENT '类型(1:规格;2:属性;)',
                                                  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                                  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                                  `updater_user` varchar(50) DEFAULT NULL,
                                                  `creator_user` varchar(50) DEFAULT NULL,
                                                  `del_flag` int DEFAULT 0,
                                                  PRIMARY KEY (`id`) USING BTREE,
                                                  INDEX `fk_pms_attr_pms_category`(`category_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品属性表';

-- ----------------------------
-- Table structure for bs_product_category_brand
-- ----------------------------
DROP TABLE IF EXISTS `bs_product_category_brand`;
CREATE TABLE `bs_product_category_brand`  (
                                              `id` varchar(32) NOT NULL COMMENT '主键',
                                              `category_id` varchar(32) NOT NULL,
                                              `brand_id` varchar(32) NOT NULL,
                                              `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                              `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                              `updater_user` varchar(50) DEFAULT NULL,
                                              `creator_user` varchar(50) DEFAULT NULL,
                                              `del_flag` int DEFAULT 0,
                                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;


-- ----------------------------
-- Table structure for bs_product_sku
-- ----------------------------
DROP TABLE IF EXISTS `bs_product_sku`;
CREATE TABLE `bs_product_sku`  (
                                   `id` varchar(32) NOT NULL,
                                   `sku_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品编码',
                                   `product_id` varchar(32) NOT NULL COMMENT '商品 ID',
                                   `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品名称',
                                   `spec_name_val` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品规格值拼接',
                                   `spec_ids` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品规格值，以英文逗号(,)分割',
                                   `spec_names` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规格属性值，以;分割',
                                   `origin_price` decimal(11,2) NOT NULL COMMENT '原价',
                                   `sales_price` decimal(11,2) NOT NULL COMMENT '销售价',
                                   `vip_price` decimal(11,2) NULL DEFAULT NULL COMMENT 'vip商品价格',
                                   `sku_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品sku主图图片地址',
                                   `album` json NULL COMMENT '商品图册',
                                   `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                   `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                   `updater_user` varchar(50) DEFAULT NULL,
                                   `creator_user` varchar(50) DEFAULT NULL,
                                   `del_flag` int DEFAULT 0,
                                   `wholesale_price` json NULL COMMENT '批发价格',
                                   `member_price` json NULL COMMENT '指定会员销售价格',
                                   `bar_code` varchar(100) NULL COMMENT '条形码/二维码',
                                   `sales_count` decimal(11,2) NULL COMMENT '销量',
                                   `auto_splice_spec_for_name` int NULL COMMENT '是否自动拼接规格值作为skuName',
                                   `stock_inventory_warning` decimal(11,2) NULL COMMENT '库存预警数量',
                                   `calculate_inventory` int NULL COMMENT '是否计算库存',
                                   `unit_score` decimal(11,2) DEFAULT NULL COMMENT '单位积分',
                                   `min_order` decimal(11,2) DEFAULT NULL COMMENT '最小起订',
                                   `weight` decimal(11,2) DEFAULT NULL COMMENT '重量',
                                   `sell_out` int NULL COMMENT '是否售罄',
                                   `merchant_id` varchar(32) not null COMMENT '商家id',
                                   `merchant_role_id` varchar(32) not NULL COMMENT '商家角色id',
                                   `merchant_name` varchar(70) not NULL COMMENT '商家名称',
                                   PRIMARY KEY (`id`) USING BTREE,
                                   INDEX `fk_product_id`(`product_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品库存表';

-- ----------------------------
-- Table structure for bs_product
-- ----------------------------
DROP TABLE IF EXISTS `bs_product`;
CREATE TABLE `bs_product`  (
                               `id` varchar(32) NOT NULL COMMENT '主键',
                               `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品名称',
                               `category_id` varchar(320) NOT NULL COMMENT '商品品类ID',
                               `brand_id` varchar(32) NULL DEFAULT NULL COMMENT '商品品牌ID',
                               `origin_price` decimal(11,2) NOT NULL COMMENT '原价【起】，默认规格价格',
                               `sales_price` decimal(11,2) NOT NULL COMMENT '现价【起】，默认规格价格',
                               `selling_point` varchar(500) NULL COMMENT '卖点',
                               `sales_counts` decimal(11,2) NULL DEFAULT 0 COMMENT '销量',
                               `pic_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品主图',
                               `album` json NULL COMMENT '商品图册',
                               `unit` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单位',
                               `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品简介',
                               `detail` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '商品详情',
                               `status` int NULL DEFAULT 0 COMMENT '商品状态',
                               `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                               `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                               `updater_user` varchar(50) DEFAULT NULL,
                               `creator_user` varchar(50) DEFAULT NULL,
                               `del_flag` int DEFAULT 0,
                               `push_date_time` datetime NULL DEFAULT NULL COMMENT '上架时间',
                               `pull_date_time` datetime NULL DEFAULT NULL COMMENT '下架时间',
                               `apply_date_time` datetime NULL DEFAULT NULL COMMENT '审核时间',
                               `merchant_id` varchar(32) not null COMMENT '商家id',
                               `merchant_role_id` varchar(32) not NULL COMMENT '商家角色id',
                               `merchant_name` varchar(70) not NULL COMMENT '商家名称',
                               `product_type` int not NULL COMMENT '商品类型',
                               `delivery_type` int not NULL COMMENT '配送方式',
                               `sales_all_area` int not NULL COMMENT '是否全国可售',
                               `merchant_category_id` varchar(320) DEFAULT NULL COMMENT '商家品类',
                               `freight_calculate_model` int not NULL COMMENT '运费承担方',
                               `freight_template_id` varchar(70) DEFAULT NULL COMMENT '运费模板id',
                               `delivery_company_id` varchar(70) DEFAULT NULL COMMENT '物流公司id',
#                                `store_id` varchar(32) not NULL COMMENT '店铺id',
#                                `store_name` varchar(50) not NULL COMMENT '店铺名称',
#                                `store_logo` varchar(70) not NULL COMMENT '店铺logo',
                               PRIMARY KEY (`id`) USING BTREE,
                               INDEX `fk_brand`(`brand_id`) USING BTREE,
                               INDEX `fk_category`(`category_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品表';


-- ----------------------------
-- Table structure for bs_product_label
-- ----------------------------
DROP TABLE IF EXISTS `bs_product_label`;
CREATE TABLE `bs_product_label`  (
                               `id` varchar(32) NOT NULL COMMENT '主键',
                               `label_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标签名称',
                               `label_id` varchar(64) NOT NULL COMMENT '标签id',
                               `product_id` varchar(64) NULL DEFAULT NULL COMMENT '商品ID',
                               `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                               `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                               `updater_user` varchar(50) DEFAULT NULL,
                               `creator_user` varchar(50) DEFAULT NULL,
                               `del_flag` int DEFAULT 0,
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品标签表';

-- ----------------------------
-- Table structure for bs_product_attribute
-- ----------------------------
DROP TABLE IF EXISTS `bs_product_attribute`;
CREATE TABLE `bs_product_attribute`  (
                                         `id` varchar(32) NOT NULL COMMENT '主键',
                                         `product_id` varchar(32) NOT NULL COMMENT '产品ID',
                                         `attribute_id` varchar(32) NULL DEFAULT NULL COMMENT '属性ID',
                                         `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '属性名称',
                                         `type` tinyint NOT NULL COMMENT '类型(1:规格;2:属性;)',
                                         `sort` int DEFAULT NULL COMMENT '排序',
                                         `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                         `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                         `updater_user` varchar(50) DEFAULT NULL,
                                         `creator_user` varchar(50) DEFAULT NULL,
                                         `del_flag` int DEFAULT 0,
                                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品属性/规格表';

-- ----------------------------
-- Records of bs_product_attribute
-- ----------------------------

-- ----------------------------
-- Table structure for bs_product_attribute_value
-- ----------------------------
DROP TABLE IF EXISTS `bs_product_attribute_value`;
CREATE TABLE `bs_product_attribute_value`  (
                                         `id` varchar(32) NOT NULL COMMENT '主键',
                                         `product_id` varchar(32) NOT NULL COMMENT '产品ID',
                                         `product_attribute_id` varchar(32) NULL DEFAULT NULL COMMENT '商品规格/属性ID',
                                         `value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '规格/属性值',
                                         `type` tinyint NOT NULL COMMENT '类型(1:规格;2:属性;)',
                                         `pic_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规格图片',
                                         `default_attribute_value` int DEFAULT NULL COMMENT '是否默认规格',
                                         `sort` int DEFAULT NULL COMMENT '排序',
                                         `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                         `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                         `updater_user` varchar(50) DEFAULT NULL,
                                         `creator_user` varchar(50) DEFAULT NULL,
                                         `del_flag` int DEFAULT 0,
                                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品属性/规格-值表';


-- ----------------------------
-- Table structure for bs_product_area
-- ----------------------------
DROP TABLE IF EXISTS `bs_product_area`;
CREATE TABLE `bs_product_area`  (
                                         `id` varchar(32) NOT NULL COMMENT '主键',
                                         `product_id` varchar(32) NOT NULL COMMENT '产品ID',
                                         `province_code` varchar(100) NULL DEFAULT NULL COMMENT '省份编码',
                                         `province_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '省份名称',
                                         `allow_all_city` tinyint NOT NULL COMMENT '是否不限制城市',
                                         `city_code` varchar(100) NULL DEFAULT NULL COMMENT '市编码',
                                         `city_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '市名称',
                                         `allow_all_region` tinyint NOT NULL COMMENT '是否不限制区域',
                                         `region_code` varchar(100) NULL DEFAULT NULL COMMENT '区域编码',
                                         `region_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '区域名称',
                                         `allow_all_community` tinyint NOT NULL COMMENT '是否不限制小区',
                                         `community_code` varchar(100) NULL DEFAULT NULL COMMENT '小区编码',
                                         `community_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '小区名称',
                                         `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                         `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                         `updater_user` varchar(50) DEFAULT NULL,
                                         `creator_user` varchar(50) DEFAULT NULL,
                                         `del_flag` int DEFAULT 0,
                                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品销售区域表';


-- ----------------------------
-- Table structure for bs_product_package_detail
-- ----------------------------
DROP TABLE IF EXISTS `bs_product_package_detail`;
CREATE TABLE `bs_product_package_detail`  (
                               `id` varchar(32) NOT NULL COMMENT '主键',
                               `product_id` varchar(64) NOT NULL COMMENT '商品spuID',
                               `sku_id` varchar(64) NOT NULL COMMENT '商品skuID',
                               `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                               `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                               `updater_user` varchar(50) DEFAULT NULL,
                               `creator_user` varchar(50) DEFAULT NULL,
                               `del_flag` int DEFAULT 0,
                               PRIMARY KEY (`id`) USING BTREE,
                               INDEX `fk_product_package_detail`(`product_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品包明细表';



-- ----------------------------
-- Table structure for bs_freight_template
-- ----------------------------
DROP TABLE IF EXISTS `bs_freight_template`;
CREATE TABLE `bs_freight_template`  (
                               `id` varchar(32) NOT NULL COMMENT '主键',
                               `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
                               `pricing_mode` int NOT NULL COMMENT '计价方式 1-按重量',
                               `transport_mode` int NULL DEFAULT NULL COMMENT '运送方式',
                               `first_weight` decimal(11,2) NOT NULL COMMENT '首重',
                               `first_weight_price` decimal(11,2) NOT NULL COMMENT '首重价格',
                               `increment_weight` decimal(11,2) NOT NULL COMMENT '每增加重量',
                               `increment_price` decimal(11,2) NOT NULL COMMENT '每增加价格',
                               `remark` varchar(500) NULL COMMENT '运费说明',
                               `status` int NULL DEFAULT 0 COMMENT '状态 0-无效 1-有效',
                               `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                               `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                               `updater_user` varchar(50) DEFAULT NULL,
                               `creator_user` varchar(50) DEFAULT NULL,
                               `del_flag` int DEFAULT 0,
                               `merchant_id` varchar(64) not null COMMENT '商家id',
                               `merchant_role_id` varchar(64) not NULL COMMENT '商家角色id',
                               `merchant_name` varchar(70) not NULL COMMENT '商家名称',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '运费模板';


-- ----------------------------
-- Table structure for bs_product_approve_record
-- ----------------------------
DROP TABLE IF EXISTS `bs_product_approve_record`;
CREATE TABLE `bs_product_approve_record`  (
                                    `id` varchar(32) NOT NULL COMMENT '主键',
                                    `product_id` varchar(32) NOT NULL COMMENT '产品ID',
                                    `status` int NULL DEFAULT NULL COMMENT '状态',
                                    `operation_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '操作用户id',
                                    `operation_user_role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '操作用户角色id',
                                    `operation_user_name` varchar(32) NULL COMMENT '操作用户名称',
                                    `agree_advice` varchar(200) NULL DEFAULT NULL COMMENT '审核意见',
                                    `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                    `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                    `updater_user` varchar(50) DEFAULT NULL,
                                    `creator_user` varchar(50) DEFAULT NULL,
                                    `del_flag` int DEFAULT 0,
                                    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品审核记录';

-- ----------------------------
-- Table structure for bs_freight_space
-- ----------------------------
DROP TABLE IF EXISTS `bs_freight_space`;
CREATE TABLE `bs_freight_space`  (
                                        `id` varchar(32) NOT NULL COMMENT '主键',
                                        `name` varchar(64) NOT NULL COMMENT '名称',
                                        `code` varchar(64) NOT NULL COMMENT '编码',
                                        `remark` varchar(500) NULL COMMENT '运费说明',
                                        `status` int NULL DEFAULT 0 COMMENT '状态 0-无效 1-有效',
                                        `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                        `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                        `updater_user` varchar(50) DEFAULT NULL,
                                        `creator_user` varchar(50) DEFAULT NULL,
                                        `del_flag` int DEFAULT 0,
                                        `merchant_id` varchar(64) not null COMMENT '商家id',
                                        `merchant_role_id` varchar(64) not NULL COMMENT '商家角色id',
                                        `merchant_name` varchar(70) not NULL COMMENT '商家名称',
                                        PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '仓位表';


-- ----------------------------
-- Table structure for bs_product_store_shop
-- ----------------------------
DROP TABLE IF EXISTS `bs_product_store_shop`;
CREATE TABLE `bs_product_store_shop`  (
                                     `id` varchar(32) NOT NULL COMMENT '主键',
                                     `shop_id` varchar(32) NOT NULL COMMENT '商城id',
                                     `store_id` varchar(32) NOT NULL COMMENT '店铺id',
                                     `product_id` varchar(32) NULL COMMENT '商品id',
                                     `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                     `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                     `updater_user` varchar(50) DEFAULT NULL,
                                     `creator_user` varchar(50) DEFAULT NULL,
                                     `del_flag` int DEFAULT 0,
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品上架关联商城和店铺中间表';

DROP TABLE IF EXISTS `bs_sku_inventory`;
CREATE TABLE `bs_sku_inventory` (
                                    `id` varchar(32) NOT NULL,
                                    `sku_id` varchar(32) not NULL COMMENT 'skuId',
                                    `stock_num` decimal(11,2) NULL DEFAULT 0 COMMENT '库存数量',
                                    `locked_stock_num` decimal(11,2) NULL DEFAULT 0 COMMENT '锁定库存数量',
                                    `lot_number` varchar(50) NULL COMMENT '入库批次',
                                    `freight_space_id` varchar(32) default NULL COMMENT '仓位id',
                                    `allot_effective_start` datetime(0) default null comment '有效日期起',
                                    `allot_effective_end` datetime(0) default null comment '有效日期止',
                                    `remark` varchar(600) default NULL COMMENT '备注',
                                    `creator_user` varchar(50) DEFAULT NULL,
                                    `create_time` datetime(0) DEFAULT NULL,
                                    `updater_user` varchar(50) DEFAULT NULL,
                                    `update_time` datetime(0) DEFAULT NULL,
                                    `del_flag` int DEFAULT 0,
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品SKU库存';

DROP TABLE IF EXISTS `bs_sku_inventory_allot`;
CREATE TABLE `bs_sku_inventory_allot` (
                                          `id` varchar(32) NOT NULL,
                                          `allot_no` varchar(60) not NULL COMMENT '调拨单号',
                                          `allot_time` datetime(0) not null comment '调拨时间',
                                          `allot_type` int DEFAULT NULL comment '调拨类型',
                                          `status` int DEFAULT NULL comment '调拨单状态',
                                          `allot_quantity` decimal(15, 2) not NULL COMMENT '调拨总量',
                                          `allot_amount` decimal(15, 2) not NULL COMMENT '调拨费用',
                                          `allot_remark` varchar(600) default NULL COMMENT '调拨说明',
                                          `allot_file` varchar(300) not NULL COMMENT '附件地址，逗号拼接',
                                          `merchant_id` varchar(64) not null COMMENT '商家id',
                                          `merchant_role_id` varchar(64) not NULL COMMENT '商家角色id',
                                          `merchant_name` varchar(70) not NULL COMMENT '商家名称',
                                          `creator_user` varchar(50) DEFAULT NULL,
                                          `create_time` datetime(0) DEFAULT NULL,
                                          `updater_user` varchar(50) DEFAULT NULL,
                                          `update_time` datetime(0) DEFAULT NULL,
                                          `del_flag` int DEFAULT 0,
                                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='仓位库存调拨';

DROP TABLE IF EXISTS `bs_sku_inventory_allot_detail`;
CREATE TABLE `bs_sku_inventory_allot_detail` (
                                                 `id` varchar(32) NOT NULL,
                                                 `allot_id` varchar(32) not NULL COMMENT '调拨单id',
                                                 `out_freight_space_id` varchar(32) not null COMMENT '调出仓位id',
                                                 `in_freight_space_id` varchar(32) not NULL COMMENT '调入仓位id',
                                                 `sku_id` varchar(32) not null comment '商品skuId',
                                                 `amount` decimal(15, 2) not null comment '费用价',
                                                 `quantity` decimal(15, 2) not null comment '数量',
                                                 `lot_number` varchar(50) NULL COMMENT '入库批次',
                                                 `remark` varchar(600) default NULL COMMENT '说明',
                                                 `creator_user` varchar(50) DEFAULT NULL,
                                                 `create_time` datetime(0) DEFAULT NULL,
                                                 `updater_user` varchar(50) DEFAULT NULL,
                                                 `update_time` datetime(0) DEFAULT NULL,
                                                 `del_flag` int DEFAULT 0,
                                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='仓位库存调拨明细';


-- ----------------------------
-- Table structure for bs_sku_inventory_allot_approve_record
-- ----------------------------
DROP TABLE IF EXISTS `bs_sku_inventory_allot_approve_record`;
CREATE TABLE `bs_sku_inventory_allot_approve_record`  (
                                                          `id` varchar(32) NOT NULL COMMENT '主键',
                                                          `allot_id` varchar(32) NOT NULL COMMENT '调拨单id',
                                                          `status` int NULL DEFAULT NULL COMMENT '状态',
                                                          `operation_user_id` varchar(32) NOT NULL COMMENT '操作用户id',
                                                          `operation_user_role_id` varchar(32) NOT NULL COMMENT '操作用户角色id',
                                                          `operation_user_name` varchar(32) NULL COMMENT '操作用户名称',
                                                          `agree_advice` varchar(200) NULL DEFAULT NULL COMMENT '审核意见',
                                                          `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                                          `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                                          `updater_user` varchar(50) DEFAULT NULL,
                                                          `creator_user` varchar(50) DEFAULT NULL,
                                                          `del_flag` int DEFAULT 0,
                                                          PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COMMENT = '调拨单审核记录';
