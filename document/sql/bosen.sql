DROP TABLE IF EXISTS `bs_admin_user_b`;
CREATE TABLE `bs_admin_user_b` (
        `id` varchar(32) NOT NULL,
        `username` varchar(64) DEFAULT NULL,
        `password` varchar(64) DEFAULT NULL,
        `icon` varchar(500) DEFAULT NULL COMMENT '头像',
        `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
        `nick_name` varchar(200) DEFAULT NULL COMMENT '昵称',
        `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
        `sex` int DEFAULT NULL COMMENT '性别',
        `status` int DEFAULT NULL COMMENT '状态',
        `login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
        `birth_date` datetime DEFAULT NULL COMMENT '生日',
        `creator_user` varchar(50) DEFAULT NULL,
        `create_time` datetime(0) DEFAULT NULL,
        `updater_user` varchar(50) DEFAULT NULL,
        `update_time` datetime(0) DEFAULT NULL,
        `del_flag` int DEFAULT 0,
        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户表';

-- ----------------------------
-- Records of admin_user_b
-- ----------------------------
INSERT INTO `bs_admin_user_b` VALUES (1, 'admin', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg.jpg', 'test@qq.com', '测试账号', null, null, null, '2018-09-29 13:55:30', '2018-09-29 13:55:39', '-1', '2023-02-20 13:55:30', '-1', '2023-02-20 13:55:30', 0);



-- ----------------------------
-- Table structure for portal_member_b
-- ----------------------------
DROP TABLE IF EXISTS `bs_portal_member_b`;
CREATE TABLE `bs_portal_member_b` (
       `id` varchar(32) NOT NULL,
       `member_level_id` varchar(32) DEFAULT NULL,
       `username` varchar(64) DEFAULT NULL COMMENT '用户名',
       `password` varchar(64) DEFAULT NULL COMMENT '密码',
       `nick_name` varchar(64) DEFAULT NULL COMMENT '昵称',
       `phone` varchar(64) DEFAULT NULL COMMENT '手机号码',
       `status` int(1) DEFAULT NULL COMMENT '帐号启用状态:0->禁用；1->启用',
       `create_time` datetime(0) DEFAULT NULL,
       `icon` varchar(500) DEFAULT NULL COMMENT '头像',
       `gender` int(1) DEFAULT NULL COMMENT '性别：0->未知；1->男；2->女',
       `birthday` date DEFAULT NULL COMMENT '生日',
       `city` varchar(64) DEFAULT NULL COMMENT '所做城市',
       `job` varchar(100) DEFAULT NULL COMMENT '职业',
       `personalized_signature` varchar(200) DEFAULT NULL COMMENT '个性签名',
       `source_type` int(1) DEFAULT NULL COMMENT '用户来源',
       `integration` decimal(11,2) DEFAULT NULL COMMENT '积分',
       `growth` decimal(11,2) DEFAULT NULL COMMENT '成长值',
       `luckey_count` int(11) DEFAULT NULL COMMENT '剩余抽奖次数',
       `history_integration` decimal(11,2) DEFAULT NULL COMMENT '历史积分数量',
       `creator_user` varchar(50) DEFAULT NULL,
       `updater_user` varchar(50) DEFAULT NULL,
       `update_time` datetime(0) DEFAULT NULL,
       `del_flag` int DEFAULT 0,
       `id_card` varchar(64) DEFAULT NULL COMMENT '身份证信息',
       `open_id` varchar(64) DEFAULT NULL COMMENT 'openId',
       PRIMARY KEY (`id`),
       UNIQUE KEY `idx_username` (`username`),
       UNIQUE KEY `idx_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员表';

-- ----------------------------
-- Records of portal_member_b
-- ----------------------------
INSERT INTO `bs_portal_member_b` VALUES (1, '4', 'test', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', 'windir', '18061581849', '1', '2018-08-02 10:35:44', null, '1', '2009-06-01', '上海', '学生', 'test', null, '5000', null, null, null, null, null, null, 0, null, null);
INSERT INTO `bs_portal_member_b` VALUES (3, '4', 'windy', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', 'windy', '18061581848', '1', '2018-08-03 16:46:38', null, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, null, null);
INSERT INTO `bs_portal_member_b` VALUES (4, '4', 'zhengsan', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', 'zhengsan', '18061581847', '1', '2018-11-12 14:12:04', null, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, null, null);
INSERT INTO `bs_portal_member_b` VALUES (5, '4', 'lisi', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', 'lisi', '18061581841', '1', '2018-11-12 14:12:38', null, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, null, null);
INSERT INTO `bs_portal_member_b` VALUES (6, '4', 'wangwu', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', 'wangwu', '18061581842', '1', '2018-11-12 14:13:09', null, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, null, null);
INSERT INTO `bs_portal_member_b` VALUES (7, '4', 'lion', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', 'lion', '18061581845', '1', '2018-11-12 14:21:39', null, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, null, null);

-- ----------------------------
-- Table structure for portal_member_level
-- ----------------------------
DROP TABLE IF EXISTS `bs_portal_member_level`;
CREATE TABLE `bs_portal_member_level` (
       `id` varchar(32) NOT NULL,
       `name` varchar(100) DEFAULT NULL,
       `growth_point` int(11) DEFAULT NULL,
       `default_status` int(1) DEFAULT NULL COMMENT '是否为默认等级：0->不是；1->是',
       `free_freight_point` decimal(10,2) DEFAULT NULL COMMENT '免运费标准',
       `comment_growth_point` int(11) DEFAULT NULL COMMENT '每次评价获取的成长值',
       `priviledge_free_freight` int(1) DEFAULT NULL COMMENT '是否有免邮特权',
       `priviledge_sign_in` int(1) DEFAULT NULL COMMENT '是否有签到特权',
       `priviledge_comment` int(1) DEFAULT NULL COMMENT '是否有评论获奖励特权',
       `priviledge_promotion` int(1) DEFAULT NULL COMMENT '是否有专享活动特权',
       `priviledge_member_price` int(1) DEFAULT NULL COMMENT '是否有会员价格特权',
       `priviledge_birthday` int(1) DEFAULT NULL COMMENT '是否有生日特权',
       `note` varchar(200) DEFAULT NULL,
       `creator_user` varchar(50) DEFAULT NULL,
       `create_time` datetime(0) DEFAULT NULL,
       `updater_user` varchar(50) DEFAULT NULL,
       `update_time` datetime(0) DEFAULT NULL,
       `del_flag` int DEFAULT 0,
       PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员等级表';

-- ----------------------------
-- Records of portal_member_level
-- ----------------------------
INSERT INTO `bs_portal_member_level` VALUES ('1', '黄金会员', '1000', '0', '199.00', '5', '1', '1', '1', '1', '1', '1', null, null, null, null, null, 0);
INSERT INTO `bs_portal_member_level` VALUES ('2', '白金会员', '5000', '0', '99.00', '10', '1', '1', '1', '1', '1', '1', null, null, null, null, null, 0);
INSERT INTO `bs_portal_member_level` VALUES ('3', '钻石会员', '15000', '0', '69.00', '15', '1', '1', '1', '1', '1', '1', null, null, null, null, null, 0);
INSERT INTO `bs_portal_member_level` VALUES ('4', '普通会员', '1', '1', '199.00', '20', '1', '1', '1', '1', '0', '0', null, null, null, null, null, 0);

-- ----------------------------
-- Table structure for portal_member_login_log
-- ----------------------------
DROP TABLE IF EXISTS `bs_portal_member_login_log`;
CREATE TABLE `bs_portal_member_login_log` (
        `id` varchar(32) NOT NULL,
        `member_id` varchar(32) DEFAULT NULL,
        `create_time` datetime DEFAULT NULL,
        `ip` varchar(64) DEFAULT NULL,
        `city` varchar(64) DEFAULT NULL,
        `login_type` int(1) DEFAULT NULL COMMENT '登录类型：0->PC；1->android;2->ios;3->小程序',
        `province` varchar(64) DEFAULT NULL,
        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员登录记录';


-- ----------------------------
-- Table structure for portal_member_receive_address
-- ----------------------------
DROP TABLE IF EXISTS `bs_portal_member_receive_address`;
CREATE TABLE `bs_portal_member_receive_address` (
     `id` varchar(32) NOT NULL,
     `member_id` varchar(32) DEFAULT NULL,
     `name` varchar(100) DEFAULT NULL COMMENT '收货人名称',
     `phone_number` varchar(64) DEFAULT NULL,
     `default_status` int(1) DEFAULT NULL COMMENT '是否为默认',
     `post_code` varchar(100) DEFAULT NULL COMMENT '邮政编码',
     `province` varchar(100) DEFAULT NULL COMMENT '省份/直辖市',
     `city` varchar(100) DEFAULT NULL COMMENT '城市',
     `region` varchar(100) DEFAULT NULL COMMENT '区',
     `detail_address` varchar(128) DEFAULT NULL COMMENT '详细地址(街道)',
     `creator_user` varchar(50) DEFAULT NULL,
     `create_time` datetime(0) DEFAULT NULL,
     `updater_user` varchar(50) DEFAULT NULL,
     `update_time` datetime(0) DEFAULT NULL,
     `del_flag` int DEFAULT 0,
     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员收货地址表';

-- ----------------------------
-- Records of ums_member_receive_address
-- ----------------------------
INSERT INTO `bs_portal_member_receive_address` VALUES ('1', '1', '大梨', '18033441849', '0', '518000', '广东省', '深圳市', '南山区', '科兴科学园', null, null, null, null, 0);
INSERT INTO `bs_portal_member_receive_address` VALUES ('3', '1', '大梨', '18033441849', '0', '518000', '广东省', '深圳市', '福田区', '清水河街道', null, null, null, null, 0);
INSERT INTO `bs_portal_member_receive_address` VALUES ('4', '1', '大梨', '18033441849', '1', '518000', '广东省', '深圳市', '福田区', '东晓街道', null, null, null, null, 0);


-- ----------------------------
-- Table structure for portal_member_statistics_info
-- ----------------------------
DROP TABLE IF EXISTS `bs_portal_member_statistics_info`;
CREATE TABLE `bs_portal_member_statistics_info` (
     `id` varchar(32) NOT NULL,
     `member_id` varchar(32) DEFAULT NULL,
     `consume_amount` decimal(10,2) DEFAULT NULL COMMENT '累计消费金额',
     `order_count` int(11) DEFAULT NULL COMMENT '订单数量',
     `coupon_count` int(11) DEFAULT NULL COMMENT '优惠券数量',
     `comment_count` int(11) DEFAULT NULL COMMENT '评价数',
     `return_order_count` int(11) DEFAULT NULL COMMENT '退货数量',
     `login_count` int(11) DEFAULT NULL COMMENT '登录次数',
     `attend_count` int(11) DEFAULT NULL COMMENT '关注数量',
     `fans_count` int(11) DEFAULT NULL COMMENT '粉丝数量',
     `collect_product_count` int(11) DEFAULT NULL,
     `collect_subject_count` int(11) DEFAULT NULL,
     `collect_topic_count` int(11) DEFAULT NULL,
     `collect_comment_count` int(11) DEFAULT NULL,
     `invite_friend_count` int(11) DEFAULT NULL,
     `recent_order_time` datetime DEFAULT NULL COMMENT '最后一次下订单时间',
     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员统计信息';


-- ----------------------------
-- Table structure for bs_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `bs_sys_menu`;
CREATE TABLE `bs_sys_menu` (
                               `id` varchar(32) NOT NULL,
                               `parent_id` varchar(32) DEFAULT NULL COMMENT '父级ID',
                               `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                               `title` varchar(100) DEFAULT NULL COMMENT '菜单名称',
                               `level` int(4) DEFAULT NULL COMMENT '菜单级数',
                               `sort` int(4) DEFAULT NULL COMMENT '菜单排序',
                               `name` varchar(100) DEFAULT NULL COMMENT '前端名称',
                               `icon` varchar(200) DEFAULT NULL COMMENT '前端图标',
                               `hidden` int(1) DEFAULT NULL COMMENT '前端隐藏',
                               `type` int(1) DEFAULT NULL COMMENT '菜单类型：0：目录， 1：菜单',
                               `route_url` varchar(200) DEFAULT NULL COMMENT '路由地址',
                               `belong_platform` int DEFAULT NULL COMMENT '所属平台：1、平台后台，2、商家后台',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台菜单表';

-- ----------------------------
-- Records of bs_sys_menu
-- ----------------------------
INSERT INTO `bs_sys_menu` VALUES ('1', '0', '2020-02-02 14:50:36', '商品', '0', '0', 'pms', 'product', '0', null, null, 1);
INSERT INTO `bs_sys_menu` VALUES ('2', '1', '2020-02-02 14:51:50', '商品列表', '1', '0', 'product', 'product-list', '0', null, null, 1);
INSERT INTO `bs_sys_menu` VALUES ('3', '1', '2020-02-02 14:52:44', '添加商品', '1', '0', 'addProduct', 'product-add', '0', null, null, 1);
INSERT INTO `bs_sys_menu` VALUES ('4', '1', '2020-02-02 14:53:51', '商品分类', '1', '0', 'productCate', 'product-cate', '0', null, null, 1);
INSERT INTO `bs_sys_menu` VALUES ('5', '1', '2020-02-02 14:54:51', '商品类型', '1', '0', 'productAttr', 'product-attr', '0', null, null, 1);
INSERT INTO `bs_sys_menu` VALUES ('6', '1', '2020-02-02 14:56:29', '品牌管理', '1', '0', 'brand', 'product-brand', '0', null, null, 1);
INSERT INTO `bs_sys_menu` VALUES ('7', '0', '2020-02-02 16:54:07', '订单', '0', '0', 'oms', 'order', '0', null, null, 1);
INSERT INTO `bs_sys_menu` VALUES ('8', '7', '2020-02-02 16:55:18', '订单列表', '1', '0', 'order', 'product-list', '0', null, null, 1);
INSERT INTO `bs_sys_menu` VALUES ('9', '7', '2020-02-02 16:56:46', '订单设置', '1', '0', 'orderSetting', 'order-setting', '0', null, null, 1);
INSERT INTO `bs_sys_menu` VALUES ('10', '7', '2020-02-02 16:57:39', '退货申请处理', '1', '0', 'returnApply', 'order-return', '0', null, null, 1);
INSERT INTO `bs_sys_menu` VALUES ('11', '7', '2020-02-02 16:59:40', '退货原因设置', '1', '0', 'returnReason', 'order-return-reason', '0', null, null, 1);
INSERT INTO `bs_sys_menu` VALUES ('12', '0', '2020-02-04 16:18:00', '营销', '0', '0', 'sms', 'sms', '0', null, null, 1);
INSERT INTO `bs_sys_menu` VALUES ('13', '12', '2020-02-04 16:19:22', '秒杀活动列表', '1', '0', 'flash', 'sms-flash', '0', null, null, 1);
INSERT INTO `bs_sys_menu` VALUES ('14', '12', '2020-02-04 16:20:16', '优惠券列表', '1', '0', 'coupon', 'sms-coupon', '0', null, null, 1);
INSERT INTO `bs_sys_menu` VALUES ('16', '12', '2020-02-07 16:22:38', '品牌推荐', '1', '0', 'homeBrand', 'product-brand', '0', null, null, 1);
INSERT INTO `bs_sys_menu` VALUES ('17', '12', '2020-02-07 16:23:14', '新品推荐', '1', '0', 'homeNew', 'sms-new', '0', null, null, 1);
INSERT INTO `bs_sys_menu` VALUES ('18', '12', '2020-02-07 16:26:38', '人气推荐', '1', '0', 'homeHot', 'sms-hot', '0', null, null, 1);
INSERT INTO `bs_sys_menu` VALUES ('19', '12', '2020-02-07 16:28:16', '专题推荐', '1', '0', 'homeSubject', 'sms-subject', '0', null, null, 1);
INSERT INTO `bs_sys_menu` VALUES ('20', '12', '2020-02-07 16:28:42', '广告列表', '1', '0', 'homeAdvertise', 'sms-ad', '0', null, null, 1);
INSERT INTO `bs_sys_menu` VALUES ('21', '0', '2020-02-07 16:29:13', '权限', '0', '0', 'ums', 'ums', '0', null, null, 1);
INSERT INTO `bs_sys_menu` VALUES ('22', '21', '2020-02-07 16:29:51', '用户列表', '1', '0', 'admin', 'ums-admin', '0', null, null, 1);
INSERT INTO `bs_sys_menu` VALUES ('23', '21', '2020-02-07 16:30:13', '角色列表', '1', '0', 'role', 'ums-role', '0', null, null, 1);
INSERT INTO `bs_sys_menu` VALUES ('24', '21', '2020-02-07 16:30:53', '菜单列表', '1', '0', 'menu', 'ums-menu', '0', null, null, 1);
INSERT INTO `bs_sys_menu` VALUES ('25', '21', '2020-02-07 16:31:13', '资源列表', '1', '0', 'resource', 'ums-resource', '0', null, null, 1);



-- ----------------------------
-- Table structure for bs_menu_permission
-- ----------------------------
DROP TABLE IF EXISTS `bs_menu_permission`;
CREATE TABLE `bs_menu_permission` (
                                      `id` varchar(32) NOT NULL,
                                      `menu_id` varchar(32) DEFAULT NULL COMMENT '菜单id',
                                      `name` varchar(100) DEFAULT NULL COMMENT '权限名称',
                                      `value` varchar(200) DEFAULT NULL COMMENT '权限值',
                                      `icon` varchar(500) DEFAULT NULL COMMENT '图标',
                                      `status` int(1) DEFAULT NULL COMMENT '启用状态；0->禁用；1->启用',
                                      `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                      `sort` int(11) DEFAULT NULL COMMENT '排序',
                                      `interface_url` varchar(100) DEFAULT NULL COMMENT '按钮权限接口访问地址',
                                      PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户菜单按钮权限表';

-- ----------------------------
-- Records of bs_menu_permission
-- ----------------------------
INSERT INTO `bs_menu_permission` VALUES (1, '1', '添加商品', 'pms:product:add', null, '1', '2018-09-29 16:15:14', '0');
INSERT INTO `bs_menu_permission` VALUES (2, '1', '商品列表', 'pms:product:read', null, '1', '2018-09-29 16:17:01', '0');



-- ----------------------------
-- Table structure for bs_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `bs_sys_role`;
CREATE TABLE `bs_sys_role` (
                               `id` varchar(32) NOT NULL,
                               `name` varchar(100) DEFAULT NULL COMMENT '名称',
                               `code` varchar(100) DEFAULT NULL COMMENT '编码',
                               `description` varchar(500) DEFAULT NULL COMMENT '描述',
                               `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                               `status` int(1) DEFAULT '1' COMMENT '启用状态：0->禁用；1->启用',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户角色表';

-- ----------------------------
-- Records of ums_role
-- ----------------------------
INSERT INTO `bs_sys_role` VALUES (3, '商品管理员', 'productAdmin', '只能查看及操作商品', '2020-02-03 16:50:37', '1');
INSERT INTO `bs_sys_role` VALUES (2, '订单管理员', 'orderAdmin', '只能查看及操作订单', '2018-09-30 15:53:45', '1');
INSERT INTO `bs_sys_role` VALUES (1, '超级管理员', 'admin', '拥有所有查看和操作功能', '2020-02-02 15:11:05', '1');

-- ----------------------------
-- Table structure for bs_role_menu_relation
-- ----------------------------
DROP TABLE IF EXISTS `bs_role_menu_relation`;
CREATE TABLE `bs_role_menu_relation` (
                                         `id` varchar(32) NOT NULL,
                                         `role_id` varchar(32) DEFAULT NULL COMMENT '角色ID',
                                         `menu_id` varchar(32) DEFAULT NULL COMMENT '菜单ID',
                                         PRIMARY KEY (`id`)
) ENGINE=InnoDB6 DEFAULT CHARSET=utf8 COMMENT='后台角色菜单关系表';

-- ----------------------------
-- Records of ums_role_menu_relation
-- ----------------------------
INSERT INTO `bs_role_menu_relation` VALUES (1, '3', '1');
INSERT INTO `bs_role_menu_relation` VALUES (2, '3', '2');

-- ----------------------------
-- Table structure for bs_role_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `bs_role_permission_relation`;
CREATE TABLE `bs_role_permission_relation` (
                                               `id` varchar(32) NOT NULL,
                                               `role_id` varchar(32) DEFAULT NULL,
                                               `permission_id` varchar(32) DEFAULT NULL,
                                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户角色和权限关系表';

-- ----------------------------
-- Records of ums_role_permission_relation
-- ----------------------------
INSERT INTO `bs_role_permission_relation` VALUES (1, '3', '1');
INSERT INTO `bs_role_permission_relation` VALUES (2, '3', '2');
INSERT INTO `bs_role_permission_relation` VALUES (3, '3', '3');


-- ----------------------------
-- Table structure for bs_user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `bs_user_role_relation`;
CREATE TABLE `bs_user_role_relation` (
                                         `id` varchar(32) NOT NULL,
                                         `admin_user_id` varchar(32) DEFAULT NULL,
                                         `role_id` varchar(32) DEFAULT NULL,
                                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户和角色关系表';

-- ----------------------------
-- Records of bs_user_role_relation
-- ----------------------------
INSERT INTO `bs_user_role_relation` VALUES (1, 1, 1);
INSERT INTO `bs_user_role_relation` VALUES (2, 1, 2);
INSERT INTO `bs_user_role_relation` VALUES (3, 1, 3);



-- ----------------------------
-- Table structure for bs_user_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `bs_user_permission_relation`;
CREATE TABLE `bs_user_permission_relation` (
     `id` varchar(32) NOT NULL,
     `admin_id` varchar(32) DEFAULT NULL,
     `permission_id` varchar(32) DEFAULT NULL,
     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户和权限关系表(除角色中定义的权限以外的加减权限)';


-- ----------------------------
-- Table structure for bs_merchant
-- ----------------------------
DROP TABLE IF EXISTS `bs_merchant`;
CREATE TABLE `bs_merchant` (
                                               `id` varchar(32) NOT NULL,
                                               `merchant_name` varchar(100) DEFAULT NULL,
                                               `register_mobile` varchar(100) DEFAULT NULL,
                                               `password` varchar(100) DEFAULT NULL,
                                               `admin_merchant_level_id` varchar(32) DEFAULT NULL,
                                               `merchant_email` varchar(20) DEFAULT NULL,
                                               `relation_mobile` varchar(20) DEFAULT NULL,
                                               `relation_name` varchar(100) DEFAULT NULL,
                                               `relation_email` varchar(20) DEFAULT NULL,
                                               `service_mobile` varchar(20) DEFAULT NULL,
                                               `order_notice_mobile` varchar(20) DEFAULT NULL,
                                               `icon` varchar(100) DEFAULT NULL,
                                               `status` int DEFAULT NULL,
                                               `login_flag` int DEFAULT NULL,
                                               `has_update_password` int DEFAULT NULL,
                                               `not_platform_merchant` int DEFAULT NULL,
                                               `platform_pumping_scale` decimal(5,2) DEFAULT NULL,
                                               `login_time` datetime(0) DEFAULT NULL,
                                               `creator_user` varchar(50) DEFAULT NULL,
                                               `create_time` datetime(0) DEFAULT NULL,
                                               `updater_user` varchar(50) DEFAULT NULL,
                                               `update_time` datetime(0) DEFAULT NULL,
                                               `del_flag` int DEFAULT 0,
                                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商户信息表';

-- ----------------------------
-- Table structure for bs_merchant_member_level
-- ----------------------------
DROP TABLE IF EXISTS `bs_merchant_member_level`;
CREATE TABLE `bs_merchant_member_level` (
                                               `id` varchar(32) NOT NULL,
                                               `merchant_id` varchar(32) DEFAULT NULL,
                                               `name` varchar(20) DEFAULT NULL,
                                               `creator_user` varchar(50) DEFAULT NULL,
                                               `create_time` datetime(0) DEFAULT NULL,
                                               `updater_user` varchar(50) DEFAULT NULL,
                                               `update_time` datetime(0) DEFAULT NULL,
                                               `del_flag` int DEFAULT 0,
                                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商家-消费会员等级';


-- ----------------------------
-- Table structure for bs_merchant_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `bs_merchant_role_relation`;
CREATE TABLE `bs_merchant_role_relation` (
                                         `id` varchar(32) NOT NULL,
                                         `merchant_id` varchar(32) DEFAULT NULL,
                                         `role_id` varchar(32) DEFAULT NULL,
                                         `creator_user` varchar(50) DEFAULT NULL,
                                         `create_time` datetime(0) DEFAULT NULL,
                                         `updater_user` varchar(50) DEFAULT NULL,
                                         `update_time` datetime(0) DEFAULT NULL,
                                         `del_flag` int DEFAULT 0,
                                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='入驻商家-角色信息';


-- ----------------------------
-- Table structure for bs_merchant_business_license
-- ----------------------------
DROP TABLE IF EXISTS `bs_merchant_business_license`;
CREATE TABLE `bs_merchant_business_license` (
                                             `id` varchar(32) NOT NULL,
                                             `merchant_id` varchar(32) DEFAULT NULL,
                                             `bank_account` varchar(50) DEFAULT NULL,
                                             `bank_deposit` varchar(50) DEFAULT NULL,
                                             `company_name` varchar(50) DEFAULT NULL,
                                             `unified_social_credit_code` varchar(50) DEFAULT NULL,
                                             `main_business` varchar(500) DEFAULT NULL,
                                             `business_license_url` varchar(100) DEFAULT NULL,
                                             `creator_user` varchar(50) DEFAULT NULL,
                                             `create_time` datetime(0) DEFAULT NULL,
                                             `updater_user` varchar(50) DEFAULT NULL,
                                             `update_time` datetime(0) DEFAULT NULL,
                                             `del_flag` int DEFAULT 0,
                                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台入驻商家-营业信息';

-- ----------------------------
-- Table structure for bs_admin_merchant_level
-- ----------------------------
DROP TABLE IF EXISTS `bs_admin_merchant_level`;
CREATE TABLE `bs_admin_merchant_level` (
                                             `id` varchar(32) NOT NULL,
                                             `name` varchar(50) DEFAULT NULL,
                                             `creator_user` varchar(50) DEFAULT NULL,
                                             `create_time` datetime(0) DEFAULT NULL,
                                             `updater_user` varchar(50) DEFAULT NULL,
                                             `update_time` datetime(0) DEFAULT NULL,
                                             `del_flag` int DEFAULT 0,
                                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台-商家等级';


-- bs_oauth_client_details definition

CREATE TABLE `bs_oauth_client_details` (
                                           `id` varchar(32) NOT NULL,
                                           `client_id` varchar(48) NOT NULL,
                                           `resource_ids` varchar(255) DEFAULT NULL,
                                           `client_secret` varchar(255) NOT NULL,
                                           `scope` varchar(255) DEFAULT NULL,
                                           `authorized_grant_types` varchar(255) DEFAULT NULL,
                                           `web_server_redirect_uri` varchar(255) DEFAULT NULL,
                                           `authorities` varchar(255) DEFAULT NULL,
                                           `access_token_validity` int DEFAULT NULL,
                                           `refresh_token_validity` int DEFAULT NULL,
                                           `additional_information` varchar(4096) DEFAULT NULL,
                                           `autoapprove` varchar(255) DEFAULT NULL,
                                           `creator_user` varchar(50) DEFAULT NULL,
                                           `create_time` datetime DEFAULT NULL,
                                           `updater_user` varchar(50) DEFAULT NULL,
                                           `update_time` datetime DEFAULT NULL,
                                           `del_flag` int DEFAULT '0',
                                           PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='客户端访问方式配置数据';


INSERT INTO bs_oauth_client_details
(id, client_id, resource_ids, client_secret, `scope`, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove, creator_user, create_time, updater_user, update_time, del_flag)
VALUES(1, 'portal-app', NULL, '$2a$10$2cFW0KaOkxRraxjB98uCOOAmk1ViiPmE6EOVjACGN69PAacx4kJmm', 'all', 'authorization_code,password,refresh_token,client_credentials,SMS', '', NULL, 86400, 604800, NULL, 'true', NULL, NULL, NULL, NULL, 0);
INSERT INTO bs_oauth_client_details
(id, client_id, resource_ids, client_secret, `scope`, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove, creator_user, create_time, updater_user, update_time, del_flag)
VALUES(2, 'admin-app', NULL, '$2a$10$2cFW0KaOkxRraxjB98uCOOAmk1ViiPmE6EOVjACGN69PAacx4kJmm', 'all', 'authorization_code,password,refresh_token,client_credentials,SMS', '', NULL, 86400, 604800, NULL, 'true', NULL, NULL, NULL, NULL, 0);
INSERT INTO bs_oauth_client_details
(id, client_id, resource_ids, client_secret, `scope`, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove, creator_user, create_time, updater_user, update_time, del_flag)
VALUES(3, 'merchant-app', NULL, '$2a$10$2cFW0KaOkxRraxjB98uCOOAmk1ViiPmE6EOVjACGN69PAacx4kJmm', 'all', 'authorization_code,password,refresh_token,client_credentials,SMS', '', NULL, 86400, 604800, NULL, 'true', NULL, NULL, NULL, NULL, 0);


-- ----------------------------
-- Table structure for bs_shop
-- ----------------------------
DROP TABLE IF EXISTS `bs_shop`;
CREATE TABLE `bs_shop` (
                                           `id` varchar(32) NOT NULL,
                                           `name` varchar(50) DEFAULT NULL COMMENT '商城名称',
                                           `shop_environment` int DEFAULT NULL COMMENT '商城所属',
                                           `logo_url` varchar(100) DEFAULT NULL COMMENT '商城图标',
                                           `remark` varchar(500) DEFAULT NULL COMMENT '商城描述',
                                           `status` int DEFAULT NULL COMMENT '状态',
                                           `adorn_content` json NULL COMMENT '装修内容，先有模板才能装修',
                                           `creator_user` varchar(50) DEFAULT NULL,
                                           `create_time` datetime(0) DEFAULT NULL,
                                           `updater_user` varchar(50) DEFAULT NULL,
                                           `update_time` datetime(0) DEFAULT NULL,
                                           `del_flag` int DEFAULT 0,
                                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商城';

-- ----------------------------
-- Table structure for bs_store
-- ----------------------------
DROP TABLE IF EXISTS `bs_store`;
CREATE TABLE `bs_store` (
                           `id` varchar(32) NOT NULL,
                           `merchant_id` varchar(64) not null COMMENT '商家id',
                           `merchant_role_id` varchar(64) not NULL COMMENT '商家角色id',
                           `name` varchar(50) DEFAULT NULL COMMENT '商城名称',
                           `logo_url` varchar(100) DEFAULT NULL COMMENT '商城图标',
                           `remark` varchar(500) DEFAULT NULL COMMENT '商城描述',
                           `status` int DEFAULT NULL COMMENT '状态',
                           `credit_score` decimal(11,2) NULL DEFAULT NULL COMMENT '信誉分',
                           `creator_user` varchar(50) DEFAULT NULL,
                           `create_time` datetime(0) DEFAULT NULL,
                           `updater_user` varchar(50) DEFAULT NULL,
                           `update_time` datetime(0) DEFAULT NULL,
                           `del_flag` int DEFAULT 0,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='店铺';

-- ----------------------------
-- Table structure for bs_store_shop
-- ----------------------------
DROP TABLE IF EXISTS `bs_store_shop`;
CREATE TABLE `bs_store_shop` (
                            `id` varchar(32) NOT NULL,
                            `store_id` varchar(64) not null COMMENT '店铺id',
                            `shop_id` varchar(64) not NULL COMMENT '商城id',
                            `status` int DEFAULT NULL COMMENT '状态',
                            `adorn_content` json NULL COMMENT '装修内容，开店申请通过之后才能进行店铺装修',
                            `credit_score` decimal(11,2) NULL DEFAULT NULL COMMENT '信誉分',
                            `creator_user` varchar(50) DEFAULT NULL,
                            `create_time` datetime(0) DEFAULT NULL,
                            `updater_user` varchar(50) DEFAULT NULL,
                            `update_time` datetime(0) DEFAULT NULL,
                            `del_flag` int DEFAULT 0,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='店铺-商城关系表';