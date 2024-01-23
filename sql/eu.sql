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
-- Records of gen_table
-- ----------------------------
BEGIN;
COMMIT;

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
-- Records of gen_table_column
-- ----------------------------
BEGIN;
COMMIT;

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
-- Records of quartz_job
-- ----------------------------
BEGIN;
INSERT INTO `quartz_job` (`id`, `job_name`, `job_group`, `cron`, `status`, `misfire_policy`, `concurrent`, `invoke_class_name`, `spring_bean_name`, `method_name`, `method_params`, `pause_after_failure`, `alarm_email`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES ('1672495122451099649', '测试内容', 'DEFAULT', '0/5 * * * * ?', 1, 0, 0, NULL, 'testJob', 'run', 'abc', b'0', NULL, '1', '2023-06-24 06:41:25', '1', '2023-10-12 03:34:22', NULL, 0);
COMMIT;

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
-- Records of quartz_job_log
-- ----------------------------
BEGIN;
COMMIT;

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
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (1, '首页', 'workbench', 10, NULL, '/', NULL, NULL, 0, b'0', b'1', b'1', b'0', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, '2023-06-05 08:18:37', '1', '2023-10-20 09:19:52', NULL, 0, b'0');
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (10, '工作台', 'workbench', 10, NULL, '/workbench', 'Workbench', 'workbench/index', 0, b'1', b'1', b'1', b'0', NULL, b'0', NULL, 2, 1, b'1', b'1', NULL, '2023-06-05 08:18:37', '1', '2023-10-20 09:19:52', NULL, 0, b'0');
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (20, '系统设置', 'shezhi', 20, NULL, '/system', NULL, NULL, 0, b'0', b'1', NULL, b'0', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, '2023-06-05 08:18:37', '1', '2023-10-20 09:19:52', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (30, '系统工具', 'gongju', 40, NULL, '/tools', NULL, NULL, 0, b'0', b'1', NULL, b'0', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, '2023-06-05 08:18:38', '1', '2023-10-20 09:19:52', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (50, '关于', 'yingyongkaifazhe', 999, NULL, 'https://gitee.com/zhaoeryu/eu-backend-web', '', '', 0, b'0', b'1', b'0', b'0', NULL, b'0', NULL, 2, NULL, b'0', b'0', NULL, '2023-06-05 08:18:38', '1', '2023-10-20 09:19:52', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (1000, '个人中心', NULL, 10, NULL, '/personal-center', 'PersonalCenter', 'system/personal-center', 0, b'0', b'1', b'1', b'0', NULL, b'0', NULL, 2, 20, b'1', b'1', NULL, '2023-06-05 08:18:37', '1', '2023-10-20 09:19:52', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (1010, '用户管理', NULL, 20, 'system:user:list', '/users', 'Users', 'system/users', 0, b'0', b'1', b'1', b'0', NULL, b'1', NULL, 2, 20, b'1', b'1', NULL, '2023-06-05 08:18:37', '1', '2023-10-20 09:19:52', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (1015, '部门管理', NULL, 30, 'system:dept:list', '/depts', 'Depts', 'system/depts', 0, b'0', b'1', b'1', b'0', NULL, NULL, NULL, 2, 20, b'1', b'1', NULL, '2023-06-05 08:18:37', '1', '2023-10-20 09:19:52', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (1020, '岗位管理', NULL, 40, 'system:post:list', '/posts', 'Posts', 'system/posts', 0, b'0', b'1', b'1', b'0', NULL, NULL, NULL, 2, 20, b'1', b'1', NULL, '2023-06-05 08:18:37', '1', '2023-10-20 09:19:52', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (1030, '角色管理', NULL, 50, 'system:role:list', '/roles', 'Roles', 'system/roles', 0, b'0', b'1', b'1', b'0', NULL, NULL, NULL, 2, 20, b'1', b'1', NULL, '2023-06-05 08:18:37', '1', '2023-10-20 09:19:52', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (1031, '角色成员', NULL, 130, 'system:role:assign:list', '/roles/auth-user/:roleId', 'AuthUser', 'system/roles/auth-user', 0, b'0', b'0', b'0', b'0', NULL, b'0', NULL, 2, 20, b'1', b'1', NULL, '2023-06-05 08:18:37', '1', '2023-10-20 09:19:52', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (1040, '菜单管理', NULL, 60, 'system:menu:list', '/menus', 'Menus', 'system/menus', 0, b'0', b'1', b'1', b'0', NULL, NULL, NULL, 2, 20, b'1', b'1', NULL, '2023-06-05 08:18:37', '1', '2023-10-20 09:19:52', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (1050, '字典管理', NULL, 70, 'system:dict:list', '/dicts', 'Dicts', 'system/dicts', 0, b'0', b'1', b'1', b'0', NULL, NULL, NULL, 2, 20, b'1', b'1', NULL, '2023-06-05 08:18:37', '1', '2023-10-20 09:19:52', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (1060, '任务管理', NULL, 80, 'system:job:list', '/jobs', 'Jobs', 'system/jobs', 0, b'0', b'1', b'1', b'0', NULL, b'1', NULL, 2, 20, b'1', b'1', NULL, '2023-06-05 08:18:37', '1', '2023-10-20 09:19:52', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (1070, '操作日志', NULL, 90, 'system:oper-log:list', '/oper-logs', 'OperLogs', 'system/oper-logs', 0, b'0', b'1', b'1', b'0', NULL, b'0', NULL, 2, 2145, b'1', b'1', NULL, '2023-06-05 08:18:37', '1', '2023-10-20 09:19:52', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (1100, '快捷菜单', NULL, 120, 'system:usual-menus:list', '/usual-menus', 'UsualMenus', 'system/usual-menus', 0, b'0', b'1', b'1', b'0', NULL, b'0', 'New', 2, 20, b'1', b'0', NULL, '2023-06-05 08:18:37', '1', '2023-10-20 09:19:52', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2100, '代码生成', NULL, 10, 'tools:gen', '/gen', 'Gen', 'tools/generate', 0, b'0', b'1', b'1', b'0', NULL, b'0', NULL, 2, 30, b'1', b'1', NULL, '2023-06-05 08:18:38', '1', '2023-10-20 09:19:52', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2110, '系统接口', NULL, 20, '', '/swagger', NULL, 'tools/swagger', 0, b'0', b'1', b'1', b'1', 'http://localhost:8000/api/doc.html', b'1', NULL, 2, 30, b'0', b'0', NULL, '2023-06-05 08:18:38', '1', '2023-10-20 09:20:00', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2115, '新增', NULL, 0, 'system:role:add', NULL, NULL, NULL, 0, b'0', b'0', b'0', b'0', NULL, b'0', NULL, 3, 1030, NULL, NULL, NULL, '2023-06-08 08:56:00', NULL, '2023-06-08 08:56:00', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2116, '修改', NULL, 0, 'system:role:edit', NULL, NULL, NULL, 0, b'0', b'0', b'0', b'0', NULL, b'0', NULL, 3, 1030, NULL, NULL, NULL, '2023-06-08 08:56:00', NULL, '2023-06-08 08:56:00', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2117, '删除', NULL, 0, 'system:role:del', NULL, NULL, NULL, 0, b'0', b'0', b'0', b'0', NULL, b'0', NULL, 3, 1030, NULL, NULL, NULL, '2023-06-08 08:56:00', NULL, '2023-06-08 08:56:00', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2118, '查询单个值', NULL, 0, 'system:role:query', NULL, NULL, NULL, 0, b'0', b'0', b'0', b'0', NULL, b'0', NULL, 3, 1030, NULL, NULL, NULL, '2023-06-08 08:56:00', NULL, '2023-06-08 08:56:00', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2119, '新增', NULL, 0, 'system:post:add', NULL, NULL, NULL, 0, b'0', b'0', b'0', b'0', NULL, b'0', NULL, 3, 1020, NULL, NULL, NULL, '2023-06-08 09:02:31', NULL, '2023-06-08 09:02:31', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2120, '修改', NULL, 0, 'system:post:edit', NULL, NULL, NULL, 0, b'0', b'0', b'0', b'0', NULL, b'0', NULL, 3, 1020, NULL, NULL, NULL, '2023-06-08 09:02:31', NULL, '2023-06-08 09:02:31', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2121, '删除', NULL, 0, 'system:post:del', NULL, NULL, NULL, 0, b'0', b'0', b'0', b'0', NULL, b'0', NULL, 3, 1020, NULL, NULL, NULL, '2023-06-08 09:02:31', NULL, '2023-06-08 09:02:31', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2122, '查询单个值', NULL, 0, 'system:post:query', NULL, NULL, NULL, 0, b'0', b'0', b'0', b'0', NULL, b'0', NULL, 3, 1020, NULL, NULL, NULL, '2023-06-08 09:02:31', NULL, '2023-06-08 09:02:31', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2123, '新增', NULL, 0, 'system:dept:add', NULL, NULL, NULL, 0, b'0', b'0', b'0', b'0', NULL, b'0', NULL, 3, 1015, NULL, NULL, NULL, '2023-06-08 09:02:31', NULL, '2023-06-08 09:02:31', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2124, '修改', NULL, 0, 'system:dept:edit', NULL, NULL, NULL, 0, b'0', b'0', b'0', b'0', NULL, b'0', NULL, 3, 1015, NULL, NULL, NULL, '2023-06-08 09:02:31', NULL, '2023-06-08 09:02:31', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2125, '删除', NULL, 0, 'system:dept:del', NULL, NULL, NULL, 0, b'0', b'0', b'0', b'0', NULL, b'0', NULL, 3, 1015, NULL, NULL, NULL, '2023-06-08 09:02:31', NULL, '2023-06-08 09:02:31', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2126, '查询单个值', NULL, 0, 'system:dept:query', NULL, NULL, NULL, 0, b'0', b'0', b'0', b'0', NULL, b'0', NULL, 3, 1015, NULL, NULL, NULL, '2023-06-08 09:02:31', NULL, '2023-06-08 09:02:31', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2127, '新增', NULL, 0, 'system:menu:add', NULL, NULL, NULL, 0, b'0', b'0', b'0', b'0', NULL, b'0', NULL, 3, 1040, NULL, NULL, NULL, '2023-06-08 09:02:31', NULL, '2023-06-08 09:02:31', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2128, '修改', NULL, 0, 'system:menu:edit', NULL, NULL, NULL, 0, b'0', b'0', b'0', b'0', NULL, b'0', NULL, 3, 1040, NULL, NULL, NULL, '2023-06-08 09:02:31', NULL, '2023-06-08 09:02:31', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2129, '删除', NULL, 0, 'system:menu:del', NULL, NULL, NULL, 0, b'0', b'0', b'0', b'0', NULL, b'0', NULL, 3, 1040, NULL, NULL, NULL, '2023-06-08 09:02:31', NULL, '2023-06-08 09:02:31', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2130, '查询单个值', NULL, 0, 'system:menu:query', NULL, NULL, NULL, 0, b'0', b'0', b'0', b'0', NULL, b'0', NULL, 3, 1040, NULL, NULL, NULL, '2023-06-08 09:02:31', NULL, '2023-06-08 09:02:31', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2131, '新增', NULL, 0, 'system:dict:add', NULL, NULL, NULL, 0, b'0', b'0', b'0', b'0', NULL, b'0', NULL, 3, 1050, NULL, NULL, NULL, '2023-06-08 09:02:31', NULL, '2023-06-08 09:02:31', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2132, '修改', NULL, 0, 'system:dict:edit', NULL, NULL, NULL, 0, b'0', b'0', b'0', b'0', NULL, b'0', NULL, 3, 1050, NULL, NULL, NULL, '2023-06-08 09:02:31', NULL, '2023-06-08 09:02:31', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2133, '删除', NULL, 0, 'system:dict:del', NULL, NULL, NULL, 0, b'0', b'0', b'0', b'0', NULL, b'0', NULL, 3, 1050, NULL, NULL, NULL, '2023-06-08 09:02:31', NULL, '2023-06-08 09:02:31', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2134, '查询单个值', NULL, 0, 'system:dict:query', NULL, NULL, NULL, 0, b'0', b'0', b'0', b'0', NULL, b'0', NULL, 3, 1050, NULL, NULL, NULL, '2023-06-08 09:02:31', NULL, '2023-06-08 09:02:31', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2135, '新增', NULL, 0, 'system:dict-detail:add', NULL, NULL, NULL, 0, b'0', b'0', b'0', b'0', NULL, b'0', NULL, 3, 1050, NULL, NULL, NULL, '2023-06-08 09:02:31', NULL, '2023-06-08 09:02:31', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2136, '修改', NULL, 0, 'system:dict-detail:edit', NULL, NULL, NULL, 0, b'0', b'0', b'0', b'0', NULL, b'0', NULL, 3, 1050, NULL, NULL, NULL, '2023-06-08 09:02:31', NULL, '2023-06-08 09:02:31', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2137, '删除', NULL, 0, 'system:dict-detail:del', NULL, NULL, NULL, 0, b'0', b'0', b'0', b'0', NULL, b'0', NULL, 3, 1050, NULL, NULL, NULL, '2023-06-08 09:02:31', NULL, '2023-06-08 09:02:31', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2138, '查询单个值', NULL, 0, 'system:dict-detail:query', NULL, NULL, NULL, 0, b'0', b'0', b'0', b'0', NULL, b'0', NULL, 3, 1050, NULL, NULL, NULL, '2023-06-08 09:02:31', NULL, '2023-06-08 09:02:31', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2145, '系统监控', 'monitor', 30, NULL, '/monitor', NULL, NULL, 0, b'0', b'1', b'0', b'0', NULL, b'0', NULL, 1, NULL, NULL, NULL, '1', '2023-06-11 08:18:27', '1', '2023-06-11 08:18:27', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2146, '在线用户', NULL, 10, 'monitor:online:list', '/online', 'OnlineUser', 'monitor/online', 0, b'0', b'1', b'0', b'0', NULL, b'1', 'New', 2, 2145, b'1', b'1', '1', '2023-06-11 08:20:20', '1', '2023-10-20 09:19:52', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2147, '组件', 'table_layout', 48, NULL, '/components', NULL, NULL, 0, b'0', b'1', b'0', b'0', NULL, b'0', NULL, 1, NULL, NULL, NULL, '1', '2023-06-30 02:02:47', '1', '2023-06-30 02:02:47', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2148, '富文本编辑器', NULL, 0, NULL, '/editor', 'Editor', 'components/editor', 0, b'0', b'1', b'0', b'0', NULL, b'0', NULL, 2, 2147, b'1', b'1', '1', '2023-06-30 02:07:33', '1', '2023-10-20 09:19:52', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2149, '文件上传', NULL, 0, NULL, '/upload-file', NULL, 'components/demo-upload-file', 0, b'0', b'1', b'0', b'0', NULL, b'0', NULL, 2, 2147, b'1', b'1', '1', '2023-06-30 02:08:28', '1', '2023-10-20 09:19:52', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2159, '根据ID查询', NULL, 0, 'system:user:query', NULL, NULL, NULL, 0, b'0', b'0', b'0', b'0', NULL, b'0', NULL, 3, 1010, NULL, NULL, NULL, '2023-07-02 03:05:38', NULL, '2023-07-02 03:05:38', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2160, '新增', NULL, 0, 'system:user:add', NULL, NULL, NULL, 0, b'0', b'0', b'0', b'0', NULL, b'0', NULL, 3, 1010, NULL, NULL, NULL, '2023-07-02 03:05:38', NULL, '2023-07-02 03:13:14', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2162, '删除', NULL, 0, 'system:user:del', NULL, NULL, NULL, 0, b'0', b'0', b'0', b'0', NULL, b'0', NULL, 3, 1010, NULL, NULL, NULL, '2023-07-02 03:05:38', NULL, '2023-07-02 03:13:14', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2163, '导出', NULL, 0, 'system:user:export', NULL, NULL, NULL, 0, b'0', b'0', b'0', b'0', NULL, b'0', NULL, 3, 1010, NULL, NULL, NULL, '2023-07-02 03:05:38', NULL, '2023-07-02 03:13:14', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2164, '导入', NULL, 0, 'system:user:import', NULL, NULL, NULL, 0, b'0', b'0', b'0', b'0', NULL, b'0', NULL, 3, 1010, NULL, NULL, NULL, '2023-07-02 03:05:38', NULL, '2023-07-02 03:13:14', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2165, '重置密码', NULL, 0, 'system:user:resetPwd', NULL, NULL, NULL, 0, b'0', b'0', b'0', b'0', NULL, b'0', NULL, 3, 1010, NULL, NULL, NULL, '2023-07-02 03:05:38', NULL, '2023-07-02 03:13:14', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2166, '分配角色', NULL, 0, 'system:user:assignRole', NULL, NULL, NULL, 0, b'0', b'0', b'0', b'0', NULL, b'0', NULL, 3, 1010, NULL, NULL, NULL, '2023-07-02 03:05:38', NULL, '2023-07-02 03:13:14', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2167, 'Druid', NULL, 999, NULL, 'http://localhost:8000/api/druid/datasource.html', NULL, NULL, 0, b'0', b'1', b'0', b'0', NULL, b'0', NULL, 2, 2145, NULL, NULL, '1', '2023-07-12 03:46:41', NULL, '2023-07-12 03:46:41', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2174, '多级菜单', 'multi-dir', 90, NULL, '/first', NULL, NULL, 0, b'0', b'1', b'0', b'0', NULL, b'0', NULL, 1, NULL, b'1', b'1', '1', '2023-07-31 03:01:10', '1', '2023-07-31 03:01:10', NULL, 0, b'0');
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2175, '二级菜单1', NULL, 999, NULL, 'second1', NULL, NULL, 0, b'0', b'1', b'0', b'0', NULL, b'0', NULL, 1, 2174, b'1', b'1', '1', '2023-07-31 03:02:12', NULL, '2023-07-31 03:02:12', NULL, 0, b'0');
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2176, '二级菜单2', NULL, 999, NULL, 'second2', NULL, NULL, 0, b'0', b'1', b'0', b'0', NULL, b'0', NULL, 1, 2174, b'1', b'1', '1', '2023-07-31 03:02:37', NULL, '2023-07-31 03:02:37', NULL, 0, b'0');
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2177, '二级菜单3', NULL, 999, NULL, 'second3', 'Second3', 'first/second3', 0, b'0', b'1', b'0', b'0', NULL, b'0', 'New', 2, 2174, b'1', b'1', '1', '2023-07-31 03:03:39', '1', '2023-07-31 03:03:39', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2178, '三级菜单1', NULL, 999, NULL, 'third1', 'Second1Third1', 'first/second1/third1', 0, b'0', b'1', b'0', b'0', NULL, b'1', NULL, 2, 2175, b'1', b'1', '1', '2023-07-31 03:05:02', '1', '2023-07-31 03:05:02', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2179, '三级菜单2', NULL, 999, NULL, 'third2', 'Second1Third2', 'first/second1/third2', 0, b'0', b'1', b'0', b'0', NULL, b'0', NULL, 2, 2175, b'1', b'1', '1', '2023-07-31 03:05:35', NULL, '2023-07-31 03:05:35', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2180, '三级菜单1', NULL, 999, NULL, 'third1', 'Second2Third1', 'first/second2/third1', 0, b'0', b'1', b'0', b'0', NULL, b'0', NULL, 2, 2176, b'1', b'1', '1', '2023-07-31 03:05:59', NULL, '2023-07-31 03:05:59', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2181, '三级菜单2', NULL, 999, NULL, 'third2', 'Second2Third2', 'first/second2/third2', 0, b'0', b'1', b'0', b'0', NULL, b'0', '', 2, 2176, b'1', b'1', '1', '2023-07-31 03:06:23', '1', '2023-07-31 03:06:23', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2183, '二级菜单4', NULL, 999, NULL, 'second4', NULL, NULL, 0, b'0', b'1', b'0', b'0', NULL, b'0', NULL, 1, 2174, b'1', b'1', '1', '2023-08-03 12:39:57', '1', '2023-08-03 12:39:57', NULL, 0, b'0');
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2184, '三级菜单1', NULL, 999, NULL, 'third1', NULL, NULL, 0, b'0', b'1', b'0', b'0', NULL, b'0', NULL, 1, 2183, b'1', b'1', '1', '2023-08-03 12:40:33', NULL, '2023-08-03 12:40:33', NULL, 0, b'0');
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2185, '四级菜单1', NULL, 999, NULL, 'four1', 'Second4Third1Four1', 'first/second4/third1/four1', 0, b'0', b'1', b'0', b'0', NULL, b'0', NULL, 2, 2184, b'1', b'1', '1', '2023-08-03 12:41:14', '1', '2023-08-03 12:41:14', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2186, '修改', NULL, 999, 'system:user:edit', NULL, NULL, NULL, 0, b'0', b'1', b'0', b'0', NULL, b'0', NULL, 3, 1010, b'1', b'1', '1', '2023-08-04 13:53:56', NULL, '2023-08-04 13:53:56', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2187, '测试按钮', NULL, 999, NULL, NULL, NULL, NULL, 0, b'0', b'1', b'0', b'0', NULL, b'0', NULL, 3, 2185, b'1', b'1', '1', '2023-08-10 06:32:03', NULL, '2023-08-10 06:32:03', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2188, '通知公告', 'tongzhi', 999, 'system:sysNotice:list', '/sysNotice', 'SysNotice', 'system/sysNotice', 0, b'0', b'1', b'1', b'0', NULL, b'0', NULL, 2, 20, b'1', b'1', NULL, '2023-08-30 05:40:34', '1', '2023-08-30 07:13:44', NULL, 0, b'0');
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2189, '根据ID查询', NULL, 0, 'system:sysNotice:query', NULL, NULL, NULL, 0, b'0', b'0', b'0', b'0', NULL, b'0', NULL, 3, 2188, NULL, NULL, NULL, '2023-08-30 05:40:34', NULL, '2023-08-30 05:40:34', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2190, '列表', NULL, 0, 'system:sysNotice:list', NULL, NULL, NULL, 0, b'0', b'0', b'0', b'0', NULL, b'0', NULL, 3, 2188, NULL, NULL, NULL, '2023-08-30 05:40:34', NULL, '2023-08-30 05:40:34', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2191, '新增', NULL, 0, 'system:sysNotice:add', NULL, NULL, NULL, 0, b'0', b'0', b'0', b'0', NULL, b'0', NULL, 3, 2188, NULL, NULL, NULL, '2023-08-30 05:40:34', NULL, '2023-08-30 05:40:34', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2192, '修改', NULL, 0, 'system:sysNotice:edit', NULL, NULL, NULL, 0, b'0', b'0', b'0', b'0', NULL, b'0', NULL, 3, 2188, NULL, NULL, NULL, '2023-08-30 05:40:34', NULL, '2023-08-30 05:40:34', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2193, '删除', NULL, 0, 'system:sysNotice:del', NULL, NULL, NULL, 0, b'0', b'0', b'0', b'0', NULL, b'0', NULL, 3, 2188, NULL, NULL, NULL, '2023-08-30 05:40:34', NULL, '2023-08-30 05:40:34', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2194, '导出', NULL, 0, 'system:sysNotice:export', NULL, NULL, NULL, 0, b'0', b'0', b'0', b'0', NULL, b'0', NULL, 3, 2188, NULL, NULL, NULL, '2023-08-30 05:40:34', NULL, '2023-08-30 05:40:34', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2195, '导入', NULL, 0, 'system:sysNotice:import', NULL, NULL, NULL, 0, b'0', b'0', b'0', b'0', NULL, b'0', NULL, 3, 2188, NULL, NULL, NULL, '2023-08-30 05:40:34', NULL, '2023-08-30 05:40:34', NULL, 0, NULL);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_icon`, `sort_num`, `permission`, `path`, `component_name`, `component`, `status`, `affix`, `visible`, `cache`, `embed`, `embed_url`, `dot`, `badge`, `menu_type`, `parent_id`, `show_header`, `show_footer`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`, `always_show`) VALUES (2196, '一级菜单', 'shortcut', 100, NULL, '/one', 'First', 'first', 0, b'0', b'1', b'0', b'0', NULL, b'0', NULL, 2, NULL, b'1', b'1', '1', '2023-09-05 08:46:45', '1', '2023-09-05 08:46:45', NULL, 0, b'0');
COMMIT;

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
-- Records of sys_notice
-- ----------------------------
BEGIN;
INSERT INTO `sys_notice` (`id`, `title`, `type`, `description`, `content`, `status`, `publisher`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES ('1696788131262599170', '新版发布通知', 0, '🔥EuBackend v1.0.0版本采用Vue2+ElementUI全新发布！', '<h1>新版发布通知</h1><h3>🔥EuBackend v1.0.0版本采用Vue2+ElementUI全新发布！</h3><ul><li>测试内容测试内容测试内容测试内容</li><li>测试内容测试内容测试内容测试内容</li><li>测试内容测试内容测试内容测试内容</li></ul><pre><code >This is My Editor</code></pre><p><br></p>', 0, 'Eu.z', '1', '2023-08-30 07:33:09', '1', '2023-08-30 08:08:25', NULL, 0);
INSERT INTO `sys_notice` (`id`, `title`, `type`, `description`, `content`, `status`, `publisher`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES ('1696797959468982273', '「通知公告」功能上线啦', 1, '🔥EuBackend v1.1.0 新增「通知公告」功能，欢迎使用！', '<h1>「通知公告」功能上线啦</h1><h3>🔥EuBackend v1.1.0 新增「通知公告」功能，欢迎使用！</h3><ol><li>系统设置/通知公告</li><li>右上角通知图标</li></ol>', 0, '系统管理员', '1', '2023-08-30 08:12:13', NULL, '2023-08-30 08:12:13', NULL, 0);
COMMIT;

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
-- Records of sys_oper_log
-- ----------------------------
BEGIN;
COMMIT;

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
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` (`id`, `role_key`, `role_name`, `description`, `status`, `data_scope`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (1, 'admin', '系统管理员', '拥有网站的全部权限', 0, NULL, NULL, '2023-06-05 07:53:20', NULL, '2023-06-05 07:53:20', NULL, 0);
COMMIT;

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
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` (`id`, `username`, `nickname`, `avatar`, `mobile`, `email`, `password`, `sex`, `admin`, `dept_id`, `status`, `login_ip`, `login_time`, `last_active_time`, `password_reset_time`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES ('1', 'admin', '系统管理员', 'http://localhost:8000/api/upload/download?path=/1705115342541778944.png', '18500000000', 'admin@eu.com', '$2a$05$QMz7BBsK6eUl4VpUt9fFZeoonk.qUEFVR2vLwKOs6p1kP4aVQVPCm', 1, 1, NULL, 0, '127.0.0.1', '2023-10-20 15:38:30', '2023-10-20 17:28:33', '2023-09-20 22:32:04', NULL, '2023-06-05 05:59:02', '1', '2023-10-20 09:28:33', NULL, 0);
COMMIT;

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

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` (`role_id`, `user_id`) VALUES (1, '1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
