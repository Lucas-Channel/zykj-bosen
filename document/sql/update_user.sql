DROP TABLE IF EXISTS `bs_user_account`;
CREATE TABLE `bs_user_account` (
    `id` varchar(32) NOT NULL COMMENT 'ID',
    `username` varchar(64) DEFAULT NULL COMMENT '用户名',
    `password` varchar(64) DEFAULT NULL COMMENT '密码',
    `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
    `nick_name` varchar(200) DEFAULT NULL COMMENT '昵称',
    `status` int DEFAULT NULL COMMENT '状态',
    `login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
    `logout_time` datetime DEFAULT NULL COMMENT '最后登出时间',
    `creator_user` varchar(50) DEFAULT NULL COMMENT '记录创建者',
    `create_time` datetime DEFAULT NULL COMMENT '记录创建时间',
    `updater_user` varchar(50) DEFAULT NULL COMMENT '记录更新者',
    `update_time` datetime DEFAULT NULL COMMENT '记录更新时间',
    `del_flag` int DEFAULT 0 COMMENT '删除标志',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户账户表';

DROP TABLE IF EXISTS `bs_user_info`;
CREATE TABLE `bs_user_info` (
    `id` varchar(32) NOT NULL COMMENT 'ID',
    `icon` varchar(500) DEFAULT NULL COMMENT '头像',
    `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
    `sex` int DEFAULT NULL COMMENT '性别',
    `birth_date` datetime DEFAULT NULL COMMENT '生日',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

DROP TABLE IF EXISTS `bs_role`;
CREATE TABLE `bs_role` (
    `id` varchar(32) NOT NULL COMMENT 'ID',
    `name` varchar(200) DEFAULT NULL COMMENT '角色名称',
    `description` varchar(400) DEFAULT NULL COMMENT '角色描述补充信息',
    `status` int DEFAULT 1 COMMENT '0-未启用，1-启用',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

DROP TABLE IF EXISTS `bs_resource`;
CREATE TABLE `bs_resource` (
   `id` varchar(32) NOT NULL COMMENT 'ID',
   `name` varchar(200) DEFAULT NULL COMMENT '资源名称',
   `type` int DEFAULT 1 COMMENT '资源类型，0-菜单，1-接口，2-数据',
   `option` json DEFAULT NULL COMMENT '资源数据补充信息',
   `description` varchar(400) DEFAULT NULL COMMENT '资源描述补充信息',
   `status` int DEFAULT 1 COMMENT '0-未启用，1-启用',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源表';

DROP TABLE IF EXISTS `bs_user_role`;
CREATE TABLE `bs_user_role` (
    `id` varchar(32) NOT NULL COMMENT 'ID',
    `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID,关联bs_user_account.id',
    `role_id` varchar(32) DEFAULT NULL COMMENT '角色ID,关联bs_role.id',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

DROP TABLE IF EXISTS `bs_role_resource`;
CREATE TABLE `bs_role_resource` (
    `id` varchar(32) NOT NULL COMMENT 'ID',
    `role_id` varchar(32) DEFAULT NULL COMMENT '角色ID,关联bs_role.id',
    `resource_id` varchar(32) DEFAULT NULL COMMENT '资源ID,bs_resource.id',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

