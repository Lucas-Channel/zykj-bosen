-- ----------------------------
-- Table structure for bs_coupon
-- ----------------------------
DROP TABLE IF EXISTS `bs_coupon`;
CREATE TABLE `bs_coupon`  (
                                `id` varchar(32) NOT NULL COMMENT '主键',
                                `coupon_code` varchar(80) NOT NULL COMMENT '优惠券编码',
                                `coupon_name` varchar(80) NOT NULL COMMENT '优惠券名称',
                                `coupon_type` int NOT NULL COMMENT '优惠券类型：平台优惠券，店铺优惠券',
                                `coupon_level` int NOT NULL COMMENT '优惠层面：订单优惠，商品优惠，品牌优惠，品类优惠，通用优惠',
                                `effective_start_date` date default NULL COMMENT '有效期起',
                                `effective_end_date` date default NULL COMMENT '有效期止',
                                `qty` int default NULL COMMENT '优惠券发放数量',
                                `max_pull_qty` int default 1 COMMENT '最大领取数量',
                                `qty_limit_type` int default NULL COMMENT '数量限制：不限领取，限制领取',
                                `release_model` int default NULL COMMENT '发放方式：主动领取，支付购买后发放，玩法领取，固定发放',
                                `store_id` varchar(32) NULL COMMENT '店铺id',
                                `status` int default 0 COMMENT '是否发放',
                                `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                `updater_user` varchar(50) DEFAULT NULL,
                                `creator_user` varchar(50) DEFAULT NULL,
                                `del_flag` int DEFAULT 0,
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '优惠券';


-- Table structure for bs_coupon_group
-- ----------------------------
DROP TABLE IF EXISTS `bs_coupon_group`;
CREATE TABLE `bs_coupon_group`  (
                              `id` varchar(32) NOT NULL COMMENT '主键',
                              `coupon_group_code` varchar(80) NOT NULL COMMENT '优惠券组编码',
                              `coupon_group_name` varchar(80) NOT NULL COMMENT '优惠券组名称',
                              `coupon_ids` varchar(320) NOT NULL COMMENT '优惠券id,逗号拼接',
                              `effective_start_date` date default NULL COMMENT '有效期起',
                              `effective_end_date` date default NULL COMMENT '有效期止',
                              `qty` int default NULL COMMENT '优惠券发放数量',
                              `status` int default 0 COMMENT '是否发放',
                              `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                              `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                              `updater_user` varchar(50) DEFAULT NULL,
                              `creator_user` varchar(50) DEFAULT NULL,
                              `del_flag` int DEFAULT 0,
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '优惠券组';

-- Table structure for bs_coupon_assignment
-- ----------------------------
DROP TABLE IF EXISTS `bs_coupon_assignment`;
CREATE TABLE `bs_coupon_assignment`  (
                                    `id` varchar(32) NOT NULL COMMENT '主键',
                                    `assignment_code` varchar(80) NOT NULL COMMENT '领取码',
                                    `coupon_id` varchar(32) NOT NULL COMMENT '优惠券id',
                                    `coupon_group_id` varchar(32) default NULL COMMENT '优惠券组id',
                                    `qty` int default NULL COMMENT '领取',
                                    `status` int default 0 COMMENT '是否使用',
                                    `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                    `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                    `updater_user` varchar(50) DEFAULT NULL,
                                    `creator_user` varchar(50) DEFAULT NULL,
                                    `del_flag` int DEFAULT 0,
                                    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '优惠券领取记录';



-- Table structure for bs_platform_activity
-- ----------------------------
DROP TABLE IF EXISTS `bs_platform_activity`;
CREATE TABLE `bs_platform_activity`  (
                                         `id` varchar(32) NOT NULL COMMENT '主键',
                                         `activity_code` varchar(80) NOT NULL COMMENT '活动编码',
                                         `activity_name` varchar(200) NOT NULL COMMENT '活动名称',
                                         `activity_start_time` datetime not NULL COMMENT '活动开始时间',
                                         `activity_end_time` datetime not NULL COMMENT '活动结束时间',
                                         `allow_use_coupon` boolean default false COMMENT '是否允许使用优惠券',
                                         `type` int default NULL COMMENT '活动方式：1、商家报名参与。2、平台抽奖活动',
                                         `activity_type` int default NULL COMMENT '活动类型， 1-特价促销 2-直降促销 3-折扣促销 4-满量促销 5-满额促销 6-赠送促销 7-多件促销 8-组合促销 9-拼团 10-抽奖 11-砍价 12-秒杀 13-换购 14-预售 15-套餐 16-试用',
                                         `activity_min_type` int default NULL COMMENT '活动细分类型（满额、满量、赠送促销）：1.满量减/满额减/赠商品；2.满量折/满额折/赠优惠卷',
                                         `activity_rule` json not null COMMENT '活动规则',
                                         `status` int default 0 COMMENT '是否发布',
                                         `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                         `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                         `updater_user` varchar(50) DEFAULT NULL,
                                         `creator_user` varchar(50) DEFAULT NULL,
                                         `del_flag` int DEFAULT 0,
                                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '平台活动主表';

-- Table structure for bs_platform_activity_merchant_apply
-- ----------------------------
DROP TABLE IF EXISTS `bs_platform_activity_merchant_apply`;
CREATE TABLE `bs_platform_activity_merchant_apply`  (
                                         `id` varchar(32) NOT NULL COMMENT '主键',
                                         `activity_id` varchar(32) NOT NULL COMMENT '活动id',
                                         `apply_date_time` datetime NOT NULL COMMENT '报名时间',
                                         `merchant_id` varchar(32) not null COMMENT '商家id',
                                         `merchant_role_id` varchar(32) not NULL COMMENT '商家角色id',
                                         `merchant_name` varchar(70) not NULL COMMENT '商家名称',
                                         `activity_rule` json not null COMMENT '活动规则',
                                         `status` int default 0 COMMENT '审核状态：0，待审核，1，审核通过，2、审核不通过',
                                         `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                         `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                         `updater_user` varchar(50) DEFAULT NULL,
                                         `creator_user` varchar(50) DEFAULT NULL,
                                         `del_flag` int DEFAULT 0,
                                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '平台活动商家报名表';

-- Table structure for bs_activity_goods
-- ----------------------------
DROP TABLE IF EXISTS `bs_activity_goods`;
CREATE TABLE `bs_activity_goods`  (
                                                        `id` varchar(32) NOT NULL COMMENT '主键',
                                                        `activity_id` varchar(32) NOT NULL COMMENT '活动id',
                                                        `activity_belong_type` int NOT NULL COMMENT '活动归属类型：1、平台，2、商家',
                                                        `activity_merchant_apply_id` varchar(32) default null COMMENT '商家报名id',
                                                        `merchant_id` varchar(32) not null COMMENT '商家id',
                                                        `merchant_role_id` varchar(32) not NULL COMMENT '商家角色id',
                                                        `merchant_name` varchar(70) not NULL COMMENT '商家名称',
                                                        `spu_id` varchar(32) not null COMMENT '商品spuId',
                                                        `sku_id` varchar(32) default null COMMENT '商品skuId',
                                                        `spu_name` varchar(200) not null COMMENT '商品spuName',
                                                        `sku_name` varchar(200) default null COMMENT '商品skuName',
                                                        `category_id` varchar(32) not null COMMENT '品类id：当活动归属为平台，该值为平台品类，否则为商家品类',
                                                        `category_name` varchar(100) not null COMMENT '品类名称',
                                                        `brand_id` varchar(32) not null COMMENT '品牌id',
                                                        `brand_name` varchar(100) not null COMMENT '品牌名称',
                                                        `unit` varchar(20) not null COMMENT '单位',
                                                        `original_price` decimal(10, 2) default 0.01 COMMENT '原价',
                                                        `sales_price` decimal(10, 2) default 0.01 COMMENT '销售价',
                                                        `restrict_num` decimal(10, 2) default 1 COMMENT '个人限购数量',
                                                        `activity_stock` decimal(10, 2) not null COMMENT '活动库存',
                                                        `sales_num` decimal(10, 2) default 0 COMMENT '销量',
                                                        `show_img_url` varchar(100) default null COMMENT '展示图',
                                                        `status` int default 0 COMMENT '审核状态：0，待审核，1，审核通过，2、审核不通过',
                                                        `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                                        `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                                        `updater_user` varchar(50) DEFAULT NULL,
                                                        `creator_user` varchar(50) DEFAULT NULL,
                                                        `del_flag` int DEFAULT 0,
                                                        PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '活动商品表';