DROP DATABASE IF EXISTS `eu`;
create database `eu` default character set utf8mb4 collate utf8mb4_general_ci;
USE eu;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table` (
  `id` varchar(20) NOT NULL,
  `package_name` varchar(255) NOT NULL COMMENT '包路径',
  `module_name` varchar(32) NOT NULL COMMENT '模块名',
  `func_group` varchar(32) DEFAULT NULL COMMENT '功能分组',
  `table_name` varchar(32) NOT NULL COMMENT '表名',
  `table_comment` varchar(255) DEFAULT NULL COMMENT '表注释',
  `author` varchar(32) DEFAULT NULL COMMENT '作者',
  `del_show_field` varchar(32) DEFAULT NULL COMMENT '删除时，提示使用的字段',
  `gen_mode` tinyint(1) NOT NULL DEFAULT '0' COMMENT '生成模式',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uni_table_name` (`table_name`) USING BTREE COMMENT '表唯一'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='代码生成表配置';

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column` (
  `id` varchar(20) NOT NULL,
  `table_name` varchar(32) NOT NULL COMMENT '表名',
  `column_name` varchar(32) NOT NULL COMMENT '字段名',
  `column_comment` varchar(255) DEFAULT NULL COMMENT '字段描述',
  `column_key` varchar(32) DEFAULT NULL COMMENT '字段键',
  `column_type` varchar(32) NOT NULL COMMENT '字段类型',
  `auto_pk` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否自增',
  `column_sort` int(3) NOT NULL COMMENT '字段排序',
  `not_null` bit(1) NOT NULL DEFAULT b'0' COMMENT '不为null',
  `java_type` varchar(32) NOT NULL COMMENT 'java字段类型',
  `java_field` varchar(32) NOT NULL COMMENT 'java字段名称',
  `column_length` int(6) DEFAULT NULL COMMENT '字段长度',
  `excel_export` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否导出',
  `table_show` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否在列表显示',
  `form_show` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否在表单显示',
  `form_type` varchar(32) DEFAULT NULL COMMENT '表单类型',
  `query_type` varchar(32) DEFAULT NULL COMMENT '查询方式',
  `dict_key` varchar(32) DEFAULT NULL COMMENT '关联字典',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_table_column` (`table_name`,`column_name`) COMMENT '字典唯一'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='代码生成列配置';

-- ----------------------------
-- Table structure for quartz_job
-- ----------------------------
DROP TABLE IF EXISTS `quartz_job`;
CREATE TABLE `quartz_job` (
  `id` varchar(20) NOT NULL,
  `job_name` varchar(32) NOT NULL COMMENT '任务名称',
  `job_group` varchar(32) NOT NULL COMMENT '任务分组',
  `cron` varchar(64) NOT NULL COMMENT 'cron表达式',
  `status` tinyint(1) NOT NULL COMMENT '任务状态',
  `misfire_policy` tinyint(1) NOT NULL COMMENT '执行策略',
  `concurrent` tinyint(1) NOT NULL COMMENT '是否允许并发',
  `invoke_class_name` varchar(255) DEFAULT NULL COMMENT '任务执行类',
  `spring_bean_name` varchar(64) DEFAULT NULL COMMENT '任务执行类的SpringBean名',
  `method_name` varchar(64) NOT NULL COMMENT '执行任务的方法名',
  `method_params` varchar(512) DEFAULT NULL COMMENT '方法参数',
  `pause_after_failure` bit(1) NOT NULL DEFAULT b'0' COMMENT '失败后是否暂停任务',
  `alarm_email` varchar(255) DEFAULT NULL COMMENT '任务失败后的告警邮箱',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='quartz任务';

-- ----------------------------
-- Table structure for quartz_job_log
-- ----------------------------
DROP TABLE IF EXISTS `quartz_job_log`;
CREATE TABLE `quartz_job_log` (
  `id` varchar(20) NOT NULL,
  `job_id` varchar(20) NOT NULL COMMENT '任务ID',
  `job_name` varchar(32) NOT NULL COMMENT '任务名称',
  `invoke_class_name` varchar(255) DEFAULT NULL COMMENT '任务执行类',
  `spring_bean_name` varchar(64) DEFAULT NULL COMMENT '任务执行类的SpringBean名',
  `method_name` varchar(64) NOT NULL COMMENT '执行任务的方法名',
  `method_params` varchar(512) DEFAULT NULL COMMENT '方法参数',
  `success` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否执行成功',
  `exception_message` varchar(512) DEFAULT NULL COMMENT '异常消息',
  `exception_detail` varchar(2048) DEFAULT NULL COMMENT '异常详情',
  `start_time` datetime NOT NULL COMMENT '开始执行时间',
  `end_time` datetime NOT NULL COMMENT '结束执行时间',
  `exec_time` bigint(20) NOT NULL COMMENT '执行时长，单位：毫秒',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='任务执行日志';

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(20) NOT NULL COMMENT '部门名称',
  `parent_id` int(11) NOT NULL COMMENT '父级ID',
  `parent_ids` varchar(50) NOT NULL COMMENT '祖级列表',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态',
  `sort_num` int(10) NOT NULL DEFAULT '0',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_dept_name` (`dept_name`,`parent_id`) USING BTREE COMMENT '同级部门唯一'
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COMMENT='部门';

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dict_key` varchar(20) NOT NULL COMMENT '字典key',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_dict_key` (`dict_key`) USING BTREE COMMENT '字典Key唯一'
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='字典';

-- ----------------------------
-- Table structure for sys_dict_detail
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_detail`;
CREATE TABLE `sys_dict_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL COMMENT '字典ID',
  `dict_key` varchar(30) NOT NULL COMMENT '字典Key',
  `dict_label` varchar(32) NOT NULL COMMENT '字典标签',
  `dict_value` varchar(30) NOT NULL COMMENT '字典Value',
  `sort_num` int(10) NOT NULL DEFAULT '0' COMMENT '排序',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_label` (`pid`,`dict_label`) COMMENT '字典Label唯一'
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COMMENT='字典详情';

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(20) NOT NULL COMMENT '菜单名称',
  `menu_icon` varchar(255) DEFAULT NULL COMMENT '菜单图标',
  `sort_num` int(10) NOT NULL DEFAULT '0' COMMENT '显示顺序',
  `permission` varchar(255) DEFAULT NULL COMMENT '权限标识',
  `path` varchar(255) DEFAULT NULL COMMENT '路由地址',
  `component_name` varchar(255) DEFAULT NULL COMMENT '组件名称',
  `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '菜单状态',
  `affix` bit(1) DEFAULT b'0',
  `visible` bit(1) DEFAULT b'0' COMMENT '是否显示',
  `cache` bit(1) DEFAULT b'0' COMMENT '是否缓存',
  `embed` bit(1) DEFAULT b'0' COMMENT '是否内嵌',
  `embed_url` varchar(255) DEFAULT NULL COMMENT '内嵌链接',
  `dot` bit(1) DEFAULT b'0' COMMENT '是否显示小红点',
  `badge` varchar(5) DEFAULT NULL COMMENT '徽标内容',
  `menu_type` tinyint(1) NOT NULL COMMENT '菜单类型',
  `parent_id` int(11) DEFAULT NULL COMMENT '父菜单ID',
  `show_header` bit(1) DEFAULT NULL COMMENT '是否显示Header',
  `show_footer` bit(1) DEFAULT NULL COMMENT '是否显示Footer',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  `always_show` bit(1) DEFAULT NULL COMMENT '是否保持显示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2197 DEFAULT CHARSET=utf8mb4 COMMENT='菜单';

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice` (
  `id` varchar(20) NOT NULL,
  `title` varchar(64) NOT NULL COMMENT '标题',
  `type` tinyint(1) NOT NULL COMMENT '公告类型',
  `description` varchar(1024) NOT NULL COMMENT '公告描述',
  `content` longtext NOT NULL COMMENT '公告内容',
  `status` tinyint(1) NOT NULL COMMENT '公告状态',
  `publisher` varchar(32) DEFAULT NULL COMMENT '发布人',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='通知公告';

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log` (
  `id` varchar(20) NOT NULL,
  `title` varchar(32) NOT NULL COMMENT '操作模块',
  `business_type` tinyint(2) NOT NULL COMMENT '业务类型',
  `method` varchar(255) NOT NULL COMMENT '执行方法',
  `req_method` varchar(10) NOT NULL COMMENT 'Http请求方式',
  `oper_name` varchar(32) DEFAULT NULL COMMENT '操作人名称',
  `dept_name` varchar(32) DEFAULT NULL COMMENT '操作人部门',
  `req_url` varchar(512) NOT NULL COMMENT '请求URL',
  `req_ip` varchar(32) NOT NULL COMMENT '请求IP',
  `req_region` varchar(32) DEFAULT NULL COMMENT '请求地域',
  `req_params` varchar(512) DEFAULT NULL COMMENT '请求参数',
  `resp_result` varchar(2048) DEFAULT NULL COMMENT '响应结果',
  `browser` varchar(32) NOT NULL COMMENT '浏览器',
  `os` varchar(32) NOT NULL COMMENT '操作系统',
  `status` tinyint(1) NOT NULL COMMENT '操作状态',
  `error_msg` varchar(255) DEFAULT NULL COMMENT '错误消息',
  `error_stack` text COMMENT '异常堆栈',
  `exec_time` int(11) NOT NULL COMMENT '执行时长',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='操作日志';

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `post_name` varchar(20) NOT NULL COMMENT '岗位名称',
  `code` varchar(20) NOT NULL COMMENT '岗位编码',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_post_name` (`post_name`) USING BTREE COMMENT '岗位名称唯一',
  UNIQUE KEY `uni_code` (`code`) USING BTREE COMMENT '岗位编码唯一'
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COMMENT='岗位';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_key` varchar(30) NOT NULL COMMENT '角色Key',
  `role_name` varchar(20) NOT NULL COMMENT '角色名称',
  `description` varchar(255) DEFAULT NULL COMMENT '角色描述',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态',
  `data_scope` tinyint(1) DEFAULT NULL COMMENT '数据权限',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_role_key` (`role_key`) USING BTREE COMMENT '角色Key唯一'
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='角色';

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `dept_id` int(11) NOT NULL COMMENT '部门ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色和部门的关系';

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  UNIQUE KEY `uni_role_menu` (`role_id`,`menu_id`) USING BTREE COMMENT '角色和菜单唯一'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色和菜单的关系';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(20) NOT NULL,
  `username` varchar(20) NOT NULL COMMENT '登录名',
  `nickname` varchar(10) NOT NULL COMMENT '昵称',
  `avatar` varchar(512) DEFAULT NULL COMMENT '头像',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `password` varchar(128) NOT NULL COMMENT '密码',
  `sex` tinyint(1) DEFAULT NULL COMMENT '性别',
  `admin` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否管理员',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '账号状态',
  `login_ip` varchar(128) DEFAULT NULL COMMENT '登录IP',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  `last_active_time` datetime DEFAULT NULL COMMENT '最后一次活跃时间',
  `password_reset_time` datetime DEFAULT NULL COMMENT '最后一次密码重置时间',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_username` (`username`) USING BTREE COMMENT '登录名唯一'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户';

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post` (
  `post_id` int(11) NOT NULL COMMENT '岗位ID',
  `user_id` varchar(20) NOT NULL COMMENT '用户ID',
  UNIQUE KEY `uni_user_post` (`post_id`,`user_id`) USING BTREE COMMENT '用户和岗位唯一'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户和岗位的关系';

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `user_id` varchar(20) NOT NULL COMMENT '用户ID',
  UNIQUE KEY `uni_role_user` (`role_id`,`user_id`) USING BTREE COMMENT '用户和角色唯一'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户和角色的关系';

SET FOREIGN_KEY_CHECKS = 1;
