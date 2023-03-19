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

INSERT INTO ord_order(id, activity_id, archive_time, buyer_commented, buyer_inner_status, buyer_member_id,
                      buyer_member_name, buyer_member_phone, buyer_role_id, buyer_user_id, can_refund, change_process_status,
                      change_send_order_status, coupon_amount, create_time, currency_type, deduction_amount, deliver_period, digest, distribution,
                      finish_time, freight, group_id, has_delivered, has_paid, has_received, has_refund, has_return_goods, has_self_support, has_transition,
                      invoice_status, logistics_no, order_distribution, order_kind, order_mode, order_no, order_service_type, order_type, outer_status,
                      paid_amount, payment_type, phone, pick_up, product_amount, project_code, promotion_amount, promotion_status, quote_id, quote_no,
                      receivable_order_no, relation_id, relation_no, return_goods_time, separate_type, service_areavo, shop_environment, shop_id,
                      shop_name, shop_type, store_id, store_name, submit_time, supply_member_id, supply_member_name, supply_role_id, taxes, total_amount,
                      trade_no, transition_status, unique_order_no, vend_abbreviation, vendor_commented, vendor_inner_status, vendor_logo, vendor_member_id,
                      vendor_member_name, vendor_role_id, vendor_role_name, vendor_user_id, verification_code, verification_operator_id,
                      verification_operator_name, verification_status, verification_time, version, warehouse_id, warehouse_name,
                      change_task_id, consignee_id, contract_id, contract_text_id, invoice_id, pre_sale_id, project_id, requirement_id,
                      service_reservation_id, task_id, fixed_positiondto)
VALUES (orderId, NULL, NULL, 't', 1000, 18, '13585629104', '13585629104', 15, 4, 'f', NULL, NULL, '0.00', '2022-12-17 16:42:37.319',
        NULL, '0.00', '', '特殊商品', 't', '2022-12-17 16:45:11.112', '0', 0, 't', 't', 't', 'f', 'f', 'f', 'f', NULL, '', 1, 4, 1, orderNo, 0, 3, 100, amount, NULL, NULL, 'f', amount, NULL, '0', 0, 0, '', NULL, 0, '', NULL, 0, 'null', 3, 2, '芯选商城', 1, NULL, NULL, '2022-12-17 16:42:37.319', 45, '零售供应商（数商云测试账号2）', 11, '0.00', amount, NULL, 1, NULL, '零售供应商（数商云测试账号2）', 't', 1000, 'https://gy-shushangyun.obs.cn-east-3.myhuaweicloud.com/nullmorenlogo330d4922e58744bbbd3acb33231cb253.png', 40, '上海芯惠企业发展有限公司', 3, '永升自营商家', 0, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, 17, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 18, NULL);


INSERT INTO ord_product(id, address, amount, brand, cart_id, category, community_code, community_name, deliver_type, delivered, differ_count, discount, enhance_count, exchange_count, expected_delivery_date, freight_type, has_settled, last_count, left_count, line_number, logistics_template_id, logo, maintain_count, name, notice_count, original_price, out_of_stock, paid_amount, phone, platform_coupon_amount, price, price_type, product_id, product_no, product_type, promised_delivery_date, promotion_type, quantity, received, receiver, ref_price, ref_return_amount, relation_id, remark, requisitioned, return_amount, return_count, separate_type, service_product_type, sku_id, spec, stock, stock_id, store_id, store_name, tax, tax_rate, unique_service_no, unit, weight, material_id, order_id, activity_goods_id)
VALUES (nextval('ord_product_seq'), '', amount, '', 0, '服装', '', '', 1, '1.0000', '0.0000', '1.0000', '0.0000', '1.0000', NULL, 1, NULL, '1.0000', '0.0000', NULL, 0, 'https://gy-shushangyun.obs.cn-east-3.myhuaweicloud.com/null李宁阿甘鞋4bcfdc15946d4a9ba181e4727e31cbec.png', '0.0000', '李宁鞋001（数商云测试）_1', NULL, amount, 'f', amount, '', '0', amount, 1, 12, '', NULL, NULL, NULL, '1.0000', '1.0000', '', amount, NULL, 0, '', NULL, '0', '0.0000', 0, NULL, 15, '颜色:灰色', '997.0000', 0, 3, '上海芯惠企业发展有限公司', 't', '0.0900', null, '双', '10.000', NULL, 18, NULL);


INSERT INTO ord_payment(id, batch_no, buyer_inner_status, confirm_time, fund_mode, outer_status, pay_amount, pay_channel, pay_node, pay_rate, pay_time, pay_type, refund_no, serial_no, serial_number, settlement_status, tax_rate_paybo, trade_no, vendor_inner_status, vouchers, order_id)
VALUES (nextval('ord_payment_seq'), 1, 11, '2022-12-17 16:42:45.189', 1, 100, amount, 11, '首付', '1.0000', '2022-12-17 16:42:45.189', 6, '', 1, serialNumber, 0, '[{taxRate: 0.0900, deliveryFree: 0.0000, orderProductAmount: 0.02, orderProductPayAmount: 0.0200}]', 'DGJ8MLTD3-EIQEQS1', 110, '[]', orderId);

INSERT INTO ord_consignee(id, address, city_code, city_name, consignee, consignee_id, country_code, default_consignee, district_code, district_name, phone, postal_code, province_code, province_name, street_code, street_name, telephone, order_id)
 VALUES (nextval('ord_consignee_seq'), '沙面公园', '310100', '上海市', '李（数商云测试）', 3, '86', 't', '310106', '静安区', '13333333333', '', '310000', '上海', '310106006', '江宁路街道', '', orderId);
