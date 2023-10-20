USE eu;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Records of quartz_job
-- ----------------------------
BEGIN;
INSERT INTO `quartz_job` (`id`, `job_name`, `job_group`, `cron`, `status`, `misfire_policy`, `concurrent`, `invoke_class_name`, `spring_bean_name`, `method_name`, `method_params`, `pause_after_failure`, `alarm_email`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES ('1672495122451099649', '测试内容', 'DEFAULT', '0/5 * * * * ?', 1, 0, 0, NULL, 'testJob', 'run', 'abc', b'0', NULL, '1', '2023-06-24 06:41:25', '1', '2023-10-12 03:34:22', NULL, 0);
COMMIT;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept` (`id`, `dept_name`, `parent_id`, `parent_ids`, `status`, `sort_num`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (1, '上海分', 0, '0', 0, 0, '1', '2023-06-06 06:00:59', '1', '2023-06-07 08:47:22', NULL, 0);
INSERT INTO `sys_dept` (`id`, `dept_name`, `parent_id`, `parent_ids`, `status`, `sort_num`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (2, '北京分', 0, '0', 0, 0, '1', '2023-06-06 06:01:47', NULL, '2023-06-07 08:47:24', NULL, 0);
INSERT INTO `sys_dept` (`id`, `dept_name`, `parent_id`, `parent_ids`, `status`, `sort_num`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (3, '深圳分', 0, '0', 0, 0, '1', '2023-06-06 06:01:56', NULL, '2023-06-07 08:47:26', NULL, 0);
INSERT INTO `sys_dept` (`id`, `dept_name`, `parent_id`, `parent_ids`, `status`, `sort_num`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (4, '研发部', 1, '0,1', 0, 9, '1', '2023-06-06 06:02:11', '1', '2023-06-07 08:46:29', NULL, 0);
INSERT INTO `sys_dept` (`id`, `dept_name`, `parent_id`, `parent_ids`, `status`, `sort_num`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (5, '运营部', 1, '0,1', 0, 0, '1', '2023-06-06 06:02:20', NULL, '2023-06-07 08:46:33', NULL, 0);
INSERT INTO `sys_dept` (`id`, `dept_name`, `parent_id`, `parent_ids`, `status`, `sort_num`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (6, '测试部', 1, '0,1', 0, 0, '1', '2023-06-06 06:02:25', NULL, '2023-06-07 08:46:35', NULL, 0);
INSERT INTO `sys_dept` (`id`, `dept_name`, `parent_id`, `parent_ids`, `status`, `sort_num`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (7, '产品部', 1, '0,1', 0, 0, '1', '2023-06-06 06:02:31', NULL, '2023-06-07 08:46:37', NULL, 0);
INSERT INTO `sys_dept` (`id`, `dept_name`, `parent_id`, `parent_ids`, `status`, `sort_num`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (8, '财务部', 1, '0,1', 0, 0, '1', '2023-06-06 06:02:35', NULL, '2023-06-07 08:46:39', NULL, 0);
INSERT INTO `sys_dept` (`id`, `dept_name`, `parent_id`, `parent_ids`, `status`, `sort_num`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (9, '研发部', 2, '0,2', 0, 0, '1', '2023-06-06 06:02:42', NULL, '2023-06-07 08:46:44', NULL, 0);
INSERT INTO `sys_dept` (`id`, `dept_name`, `parent_id`, `parent_ids`, `status`, `sort_num`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (10, '运营部', 2, '0,2', 0, 0, '1', '2023-06-06 06:02:49', NULL, '2023-06-07 08:46:46', NULL, 0);
INSERT INTO `sys_dept` (`id`, `dept_name`, `parent_id`, `parent_ids`, `status`, `sort_num`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (11, '财务部', 2, '0,2', 0, 0, '1', '2023-06-06 06:02:59', NULL, '2023-06-07 08:46:47', NULL, 0);
INSERT INTO `sys_dept` (`id`, `dept_name`, `parent_id`, `parent_ids`, `status`, `sort_num`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (13, '行政部', 3, '0,3', 0, 0, '1', '2023-06-06 06:04:27', NULL, '2023-06-07 08:46:51', NULL, 0);
INSERT INTO `sys_dept` (`id`, `dept_name`, `parent_id`, `parent_ids`, `status`, `sort_num`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (14, '产品部', 3, '0,3', 0, 0, '1', '2023-06-06 06:05:22', NULL, '2023-06-07 08:46:52', NULL, 0);
INSERT INTO `sys_dept` (`id`, `dept_name`, `parent_id`, `parent_ids`, `status`, `sort_num`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (15, '研发部', 3, '0,3', 0, 0, '1', '2023-06-06 06:05:32', NULL, '2023-06-07 08:46:54', NULL, 0);
INSERT INTO `sys_dept` (`id`, `dept_name`, `parent_id`, `parent_ids`, `status`, `sort_num`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (22, '三级部门', 4, '0,1,4', 0, 0, '1', '2023-07-31 11:57:36', NULL, '2023-10-20 06:25:22', NULL, 0);
INSERT INTO `sys_dept` (`id`, `dept_name`, `parent_id`, `parent_ids`, `status`, `sort_num`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (32, '四级部门', 22, '0,1,4,22', 0, 999, '1', '2023-08-25 03:19:33', NULL, '2023-08-25 03:19:33', NULL, 0);
INSERT INTO `sys_dept` (`id`, `dept_name`, `parent_id`, `parent_ids`, `status`, `sort_num`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (33, '五级部门', 32, '0,1,4,22,32', 0, 999, '1', '2023-08-25 03:19:40', NULL, '2023-08-25 03:19:40', NULL, 0);
COMMIT;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict` (`id`, `dict_key`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (1, 'sex', 0, '1', '2023-06-06 06:48:06', '1', '2023-08-25 09:18:30', '性别', 0);
INSERT INTO `sys_dict` (`id`, `dict_key`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (2, 'test', 0, '1', '2023-06-07 14:11:42', '1', '2023-08-25 09:18:30', '测试', 0);
INSERT INTO `sys_dict` (`id`, `dict_key`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (3, 'job_group', 0, '1', '2023-06-29 12:29:33', NULL, '2023-08-25 09:18:30', '任务分组', 0);
INSERT INTO `sys_dict` (`id`, `dict_key`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (4, 'user_status', 0, '1', '2023-06-29 15:01:40', '1', '2023-08-25 09:18:30', '用户状态', 0);
INSERT INTO `sys_dict` (`id`, `dict_key`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (5, 'message_type', 0, '1', '2023-07-16 01:03:43', NULL, '2023-08-25 09:18:30', '消息类型', 0);
INSERT INTO `sys_dict` (`id`, `dict_key`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (6, 'message_status', 0, '1', '2023-07-16 01:04:08', NULL, '2023-08-25 09:18:30', '消息状态', 0);
INSERT INTO `sys_dict` (`id`, `dict_key`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (7, 'message_group', 0, '1', '2023-07-16 01:06:29', NULL, '2023-08-25 09:18:30', '消息分组', 0);
INSERT INTO `sys_dict` (`id`, `dict_key`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (8, 'asdff', 0, '1', '2023-08-02 14:55:19', NULL, '2023-08-25 09:18:30', 'saf', 0);
INSERT INTO `sys_dict` (`id`, `dict_key`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (9, 'a', 0, '1', '2023-08-25 09:19:41', '1', '2023-08-25 09:19:46', '测试', 0);
INSERT INTO `sys_dict` (`id`, `dict_key`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (10, 'notice_type', 0, '1', '2023-08-30 03:49:41', NULL, '2023-08-30 03:49:41', '通知公告类型', 0);
COMMIT;

-- ----------------------------
-- Records of sys_dict_detail
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_detail` (`id`, `pid`, `dict_key`, `dict_label`, `dict_value`, `sort_num`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (4, 1, 'sex', '男', '12', 0, 0, '1', '2023-06-06 06:59:02', '1', '2023-08-25 09:18:40', '', 0);
INSERT INTO `sys_dict_detail` (`id`, `pid`, `dict_key`, `dict_label`, `dict_value`, `sort_num`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (5, 1, 'sex', '女', '0', 0, 0, '1', '2023-06-06 07:01:28', NULL, '2023-08-25 09:18:40', NULL, 0);
INSERT INTO `sys_dict_detail` (`id`, `pid`, `dict_key`, `dict_label`, `dict_value`, `sort_num`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (7, 2, 'test', '12', '34', 123, 0, '1', '2023-06-08 00:58:04', '1', '2023-08-25 09:18:40', 'sdf', 0);
INSERT INTO `sys_dict_detail` (`id`, `pid`, `dict_key`, `dict_label`, `dict_value`, `sort_num`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (8, 3, 'job_group', '默认', 'DEFAULT', 0, 0, '1', '2023-06-29 12:29:54', '1', '2023-08-25 09:18:40', NULL, 0);
INSERT INTO `sys_dict_detail` (`id`, `pid`, `dict_key`, `dict_label`, `dict_value`, `sort_num`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (9, 3, 'job_group', '系统', 'SYSTEM', 0, 0, '1', '2023-06-29 12:30:14', NULL, '2023-08-25 09:18:40', NULL, 0);
INSERT INTO `sys_dict_detail` (`id`, `pid`, `dict_key`, `dict_label`, `dict_value`, `sort_num`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (10, 4, 'user_status', '正常', 'normal', 0, 0, '1', '2023-06-29 15:03:56', NULL, '2023-08-25 09:18:40', NULL, 0);
INSERT INTO `sys_dict_detail` (`id`, `pid`, `dict_key`, `dict_label`, `dict_value`, `sort_num`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (11, 4, 'user_status', '禁用', 'disabled', 0, 0, '1', '2023-06-29 15:04:06', NULL, '2023-08-25 09:18:40', NULL, 0);
INSERT INTO `sys_dict_detail` (`id`, `pid`, `dict_key`, `dict_label`, `dict_value`, `sort_num`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (12, 5, 'message_type', '系统消息', '0', 0, 0, '1', '2023-07-16 01:04:47', NULL, '2023-08-25 09:18:40', NULL, 0);
INSERT INTO `sys_dict_detail` (`id`, `pid`, `dict_key`, `dict_label`, `dict_value`, `sort_num`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (13, 7, 'message_group', '通知', '0', 0, 0, '1', '2023-07-16 01:06:47', NULL, '2023-08-25 09:18:40', NULL, 0);
INSERT INTO `sys_dict_detail` (`id`, `pid`, `dict_key`, `dict_label`, `dict_value`, `sort_num`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (14, 7, 'message_group', '消息', '1', 0, 0, '1', '2023-07-16 01:06:55', NULL, '2023-08-25 09:18:40', NULL, 0);
INSERT INTO `sys_dict_detail` (`id`, `pid`, `dict_key`, `dict_label`, `dict_value`, `sort_num`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (15, 6, 'message_status', '待发布', '0', 0, 0, '1', '2023-07-16 01:07:22', NULL, '2023-08-25 09:18:40', NULL, 0);
INSERT INTO `sys_dict_detail` (`id`, `pid`, `dict_key`, `dict_label`, `dict_value`, `sort_num`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (16, 6, 'message_status', '已发布', '1', 0, 0, '1', '2023-07-16 01:07:29', NULL, '2023-08-25 09:18:40', NULL, 0);
INSERT INTO `sys_dict_detail` (`id`, `pid`, `dict_key`, `dict_label`, `dict_value`, `sort_num`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (17, 5, 'message_type', '用户消息', '1', 0, 0, '1', '2023-07-16 01:07:46', NULL, '2023-08-25 09:18:40', NULL, 0);
INSERT INTO `sys_dict_detail` (`id`, `pid`, `dict_key`, `dict_label`, `dict_value`, `sort_num`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (18, 8, 'asdff', 'asd', 'a', 999, 0, '1', '2023-08-02 14:56:49', '1', '2023-08-25 09:18:40', '1ad', 0);
INSERT INTO `sys_dict_detail` (`id`, `pid`, `dict_key`, `dict_label`, `dict_value`, `sort_num`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (19, 9, 'a', 'x', '1', 99, 0, '1', '2023-08-26 14:42:47', NULL, '2023-08-26 14:42:47', '11111', 0);
INSERT INTO `sys_dict_detail` (`id`, `pid`, `dict_key`, `dict_label`, `dict_value`, `sort_num`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (20, 9, 'a', 'y', '2', 100, 0, '1', '2023-08-26 14:42:47', NULL, '2023-08-26 14:42:47', '测试', 0);
INSERT INTO `sys_dict_detail` (`id`, `pid`, `dict_key`, `dict_label`, `dict_value`, `sort_num`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (21, 9, 'a', 'z', '3', 99, 1, '1', '2023-08-26 14:42:47', NULL, '2023-08-26 14:42:47', '阿斯顿发', 0);
INSERT INTO `sys_dict_detail` (`id`, `pid`, `dict_key`, `dict_label`, `dict_value`, `sort_num`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (22, 10, 'notice_type', '通知', '0', 998, 0, '1', '2023-08-30 03:50:08', '1', '2023-08-30 03:50:08', '通知', 0);
INSERT INTO `sys_dict_detail` (`id`, `pid`, `dict_key`, `dict_label`, `dict_value`, `sort_num`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (23, 10, 'notice_type', '公告', '1', 999, 0, '1', '2023-08-30 03:50:33', '1', '2023-08-30 03:50:33', '公告', 0);
COMMIT;

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
-- Records of sys_notice
-- ----------------------------
BEGIN;
INSERT INTO `sys_notice` (`id`, `title`, `type`, `description`, `content`, `status`, `publisher`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES ('1696788131262599170', '新版发布通知', 0, '🔥EuBackend v1.0.0版本采用Vue2+ElementUI全新发布！', '<h1>新版发布通知</h1><h3>🔥EuBackend v1.0.0版本采用Vue2+ElementUI全新发布！</h3><ul><li>测试内容测试内容测试内容测试内容</li><li>测试内容测试内容测试内容测试内容</li><li>测试内容测试内容测试内容测试内容</li></ul><pre><code >This is My Editor</code></pre><p><br></p>', 0, 'Eu.z', '1', '2023-08-30 07:33:09', '1', '2023-08-30 08:08:25', NULL, 0);
INSERT INTO `sys_notice` (`id`, `title`, `type`, `description`, `content`, `status`, `publisher`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES ('1696797959468982273', '「通知公告」功能上线啦', 1, '🔥EuBackend v1.1.0 新增「通知公告」功能，欢迎使用！', '<h1>「通知公告」功能上线啦</h1><h3>🔥EuBackend v1.1.0 新增「通知公告」功能，欢迎使用！</h3><ol><li>系统设置/通知公告</li><li>右上角通知图标</li></ol>', 0, '系统管理员', '1', '2023-08-30 08:12:13', NULL, '2023-08-30 08:12:13', NULL, 0);
INSERT INTO `sys_notice` (`id`, `title`, `type`, `description`, `content`, `status`, `publisher`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES ('1696807814057533441', '测试内容', 0, '我是测试通知', '<p>阿斯顿阿道夫</p><p>阿道夫</p><p>阿斯顿发</p><p>阿斯顿发</p><p><br></p><p>阿斯顿发</p><p><br></p><p>阿斯顿发</p>', 0, '系统管理员', '1', '2023-08-30 08:51:22', NULL, '2023-08-30 08:51:22', NULL, 0);
INSERT INTO `sys_notice` (`id`, `title`, `type`, `description`, `content`, `status`, `publisher`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES ('1696807914758578177', '阿斯顿发', 0, '阿斯顿生生世世生生世世', '<p>啊啊啊啊啊啊啊啊</p><p>阿斯顿</p><p><br></p><p><br></p><pre><code >docker-compose up -d .</code></pre><p>啊啊啊啊啊啊啊啊</p><p>阿斯顿</p><p><br></p><p>啊啊啊啊啊啊啊啊</p><p>阿斯顿</p><p><br></p><p>啊啊啊啊啊啊啊啊</p><p>阿斯顿</p>', 0, '系统管理员', '1', '2023-08-30 08:51:46', '1', '2023-08-30 08:51:46', NULL, 0);
COMMIT;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
BEGIN;
INSERT INTO `sys_post` (`id`, `post_name`, `code`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (1, '总裁', 'ceo', 0, '1', '2023-06-06 06:17:00', '1', '2023-08-25 08:25:43', NULL, 0);
INSERT INTO `sys_post` (`id`, `post_name`, `code`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (4, '财务总监', 'cfo', 0, '1', '2023-06-06 06:19:33', NULL, '2023-06-06 06:19:33', NULL, 0);
INSERT INTO `sys_post` (`id`, `post_name`, `code`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (5, '运营总监', 'coo', 0, '1', '2023-06-06 06:19:54', NULL, '2023-06-06 06:19:54', NULL, 0);
INSERT INTO `sys_post` (`id`, `post_name`, `code`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (6, '技术总监', 'cto', 0, '1', '2023-06-06 06:20:12', NULL, '2023-06-06 06:20:12', NULL, 0);
INSERT INTO `sys_post` (`id`, `post_name`, `code`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (7, '高级软件开发工程师', 'developer', 0, '1', '2023-06-07 09:15:45', '1', '2023-06-07 09:15:45', NULL, 0);
INSERT INTO `sys_post` (`id`, `post_name`, `code`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (24, '上海分总经理', 'shanghai_ceo', 0, '1', '2023-08-25 08:27:42', '1', '2023-08-25 08:27:57', NULL, 0);
COMMIT;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` (`id`, `role_key`, `role_name`, `description`, `status`, `data_scope`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (1, 'admin', '系统管理员', '拥有网站的全部权限', 0, NULL, NULL, '2023-06-05 07:53:20', NULL, '2023-06-05 07:53:20', NULL, 0);
INSERT INTO `sys_role` (`id`, `role_key`, `role_name`, `description`, `status`, `data_scope`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (2, 'common', '普通用户', '拥有网站的最基础功能', 0, 1, '1', '2023-06-06 05:44:15', '1', '2023-08-03 09:03:55', NULL, 0);
INSERT INTO `sys_role` (`id`, `role_key`, `role_name`, `description`, `status`, `data_scope`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (4, 'self', '本人', NULL, 0, 5, '1', '2023-07-02 01:59:57', '1', '2023-07-02 01:59:57', NULL, 0);
INSERT INTO `sys_role` (`id`, `role_key`, `role_name`, `description`, `status`, `data_scope`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (8, 'benbumen', '本部门', NULL, 0, 3, '1', '2023-08-11 03:33:03', '1', '2023-08-11 03:33:03', NULL, 0);
INSERT INTO `sys_role` (`id`, `role_key`, `role_name`, `description`, `status`, `data_scope`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (9, 'benbumenjiyixia', '本部门及以下', NULL, 0, 4, '1', '2023-08-11 03:33:53', '1', '2023-08-11 03:33:53', NULL, 0);
INSERT INTO `sys_role` (`id`, `role_key`, `role_name`, `description`, `status`, `data_scope`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES (10, 'zidingyi', '自定义', NULL, 0, 2, '1', '2023-08-11 03:34:15', '1', '2023-08-11 03:34:15', NULL, 0);
COMMIT;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_dept` (`role_id`, `dept_id`) VALUES (10, 2);
INSERT INTO `sys_role_dept` (`role_id`, `dept_id`) VALUES (10, 9);
INSERT INTO `sys_role_dept` (`role_id`, `dept_id`) VALUES (10, 10);
INSERT INTO `sys_role_dept` (`role_id`, `dept_id`) VALUES (10, 11);
COMMIT;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 1);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 10);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 20);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 50);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 1000);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 2147);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 2148);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 2149);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 2174);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 2175);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 2176);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 2177);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 2178);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 2179);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 2180);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 2181);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 2183);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 2184);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 2185);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (3, 1);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (3, 10);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (3, 20);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (3, 1000);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (3, 1010);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (3, 1011);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (3, 1012);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (3, 1013);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (3, 1015);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (3, 1020);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (3, 1030);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (3, 1031);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (3, 1040);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (3, 1050);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (3, 1060);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (3, 1070);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (3, 1080);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (3, 1090);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (3, 1100);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 1);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 10);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 20);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 1000);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 1010);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 1015);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 1020);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 1030);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 1031);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 1040);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 1050);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 1060);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 1100);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 2115);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 2116);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 2117);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 2118);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 2119);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 2120);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 2121);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 2122);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 2123);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 2124);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 2125);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 2126);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 2127);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 2128);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 2129);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 2130);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 2131);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 2132);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 2133);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 2134);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 2135);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 2136);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 2137);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 2138);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 2159);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 2160);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 2162);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 2163);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 2164);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 2165);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 2166);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (4, 2186);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 1);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 10);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 20);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 30);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 1000);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 1010);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 1015);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 1020);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 1030);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 1031);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 1040);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 1050);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 1060);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 1070);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 1100);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 2100);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 2110);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 2115);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 2116);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 2117);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 2118);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 2119);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 2120);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 2122);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 2123);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 2124);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 2125);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 2126);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 2127);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 2128);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 2129);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 2130);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 2131);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 2132);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 2133);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 2134);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 2135);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 2136);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 2137);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 2138);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 2145);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 2146);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 2159);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 2160);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 2162);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 2163);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 2164);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 2165);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 2166);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 2186);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 1);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 10);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 20);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 1000);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 1010);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 1015);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 1020);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 1030);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 1031);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 1040);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 1050);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 1060);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 1100);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 2115);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 2116);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 2117);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 2118);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 2119);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 2120);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 2121);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 2122);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 2123);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 2124);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 2125);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 2126);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 2127);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 2128);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 2129);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 2130);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 2131);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 2132);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 2133);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 2134);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 2135);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 2136);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 2137);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 2138);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 2159);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 2160);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 2162);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 2163);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 2164);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 2165);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 2166);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (8, 2186);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 1);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 10);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 20);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 1000);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 1010);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 1015);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 1020);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 1030);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 1031);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 1040);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 1050);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 1060);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 1100);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 2115);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 2116);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 2117);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 2118);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 2119);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 2120);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 2121);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 2122);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 2123);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 2124);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 2125);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 2126);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 2127);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 2128);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 2129);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 2130);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 2131);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 2132);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 2133);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 2134);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 2135);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 2136);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 2137);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 2138);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 2159);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 2160);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 2162);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 2163);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 2164);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 2165);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 2166);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (9, 2186);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 1);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 10);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 20);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 1000);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 1010);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 1015);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 1020);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 1030);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 1031);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 1040);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 1050);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 1060);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 1100);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 2115);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 2116);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 2117);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 2118);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 2119);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 2120);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 2121);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 2122);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 2123);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 2124);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 2125);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 2126);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 2127);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 2128);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 2129);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 2130);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 2131);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 2132);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 2133);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 2134);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 2135);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 2136);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 2137);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 2138);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 2159);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 2160);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 2162);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 2163);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 2164);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 2165);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 2166);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (10, 2186);
COMMIT;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` (`id`, `username`, `nickname`, `avatar`, `mobile`, `email`, `password`, `sex`, `admin`, `dept_id`, `status`, `login_ip`, `login_time`, `last_active_time`, `password_reset_time`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES ('1', 'admin', '系统管理员', 'http://localhost:8000/api/upload/download?path=/1705115342541778944.png', '18500000000', 'admin@eu.com', '$2a$05$QMz7BBsK6eUl4VpUt9fFZeoonk.qUEFVR2vLwKOs6p1kP4aVQVPCm', 1, 1, NULL, 0, '127.0.0.1', '2023-10-20 15:38:30', '2023-10-20 17:28:33', '2023-09-20 22:32:04', NULL, '2023-06-05 05:59:02', '1', '2023-10-20 09:28:33', NULL, 0);
INSERT INTO `sys_user` (`id`, `username`, `nickname`, `avatar`, `mobile`, `email`, `password`, `sex`, `admin`, `dept_id`, `status`, `login_ip`, `login_time`, `last_active_time`, `password_reset_time`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES ('1665897272275132418', 'zhaoey', 'Eu.z', NULL, '18500066238', 'cn.zhaoey@qq.com', '$2a$05$4vAOO4usOFObVgdvt/qLoegG/Fwkr/qwe1n1dmGuqdcnw39qbkOUq', 1, 0, 4, 0, '127.0.0.1', '2023-10-13 14:06:18', '2023-10-13 14:06:18', '2023-10-13 14:05:30', '1', '2023-06-06 01:43:55', '1', '2023-10-13 06:06:18', '测试备注asdfaa', 0);
INSERT INTO `sys_user` (`id`, `username`, `nickname`, `avatar`, `mobile`, `email`, `password`, `sex`, `admin`, `dept_id`, `status`, `login_ip`, `login_time`, `last_active_time`, `password_reset_time`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES ('1689843130490179585', 'benbumen', 'benbumen', NULL, '13500000000', NULL, '$2a$05$BebjR61T5zdGwmosth1jpuk6vS.O/Tr75mBzLVTI36YaUxSctc9Z.', 1, 0, 22, 0, '127.0.0.1', '2023-08-11 12:39:19', '2023-08-11 12:39:20', NULL, '1', '2023-08-11 03:36:12', NULL, '2023-08-11 04:39:19', NULL, 0);
INSERT INTO `sys_user` (`id`, `username`, `nickname`, `avatar`, `mobile`, `email`, `password`, `sex`, `admin`, `dept_id`, `status`, `login_ip`, `login_time`, `last_active_time`, `password_reset_time`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES ('1689843309113004034', 'benbumenchild', '本部门及以下', NULL, '13500000001', NULL, '$2a$05$1RnuY4HMMb2FJncxrRTL8uj9rzhTdjR06UGgmDr1y/br2Bs3n6DAe', 1, 0, 4, 0, '127.0.0.1', '2023-08-11 12:40:10', '2023-08-11 12:40:10', NULL, '1', '2023-08-11 03:36:55', NULL, '2023-08-11 04:40:09', NULL, 0);
INSERT INTO `sys_user` (`id`, `username`, `nickname`, `avatar`, `mobile`, `email`, `password`, `sex`, `admin`, `dept_id`, `status`, `login_ip`, `login_time`, `last_active_time`, `password_reset_time`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `del_flag`) VALUES ('1689843391623352321', 'zidingyi', '自定义', NULL, '13500000002', NULL, '$2a$05$dLUBBF0cco8XiJXhXzc17OYEtc4.jlvwmesACpUoCbigPVGh1ohjK', 1, 0, 5, 0, '127.0.0.1', '2023-08-11 12:40:28', '2023-08-11 14:30:00', NULL, '1', '2023-08-11 03:37:15', NULL, '2023-08-11 06:29:59', NULL, 0);
COMMIT;

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_post` (`post_id`, `user_id`) VALUES (1, '1666004924686667777');
INSERT INTO `sys_user_post` (`post_id`, `user_id`) VALUES (1, '1699318358639767553');
INSERT INTO `sys_user_post` (`post_id`, `user_id`) VALUES (1, '1704740314666848257');
INSERT INTO `sys_user_post` (`post_id`, `user_id`) VALUES (4, '1666004924686667777');
INSERT INTO `sys_user_post` (`post_id`, `user_id`) VALUES (6, '1665897272275132418');
COMMIT;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` (`role_id`, `user_id`) VALUES (1, '1');
INSERT INTO `sys_user_role` (`role_id`, `user_id`) VALUES (1, '1689083379904954369');
INSERT INTO `sys_user_role` (`role_id`, `user_id`) VALUES (1, '1699318358639767553');
INSERT INTO `sys_user_role` (`role_id`, `user_id`) VALUES (1, '1704740314666848257');
INSERT INTO `sys_user_role` (`role_id`, `user_id`) VALUES (2, '1666004924686667777');
INSERT INTO `sys_user_role` (`role_id`, `user_id`) VALUES (2, '1704740314666848257');
INSERT INTO `sys_user_role` (`role_id`, `user_id`) VALUES (4, '1665897272275132418');
INSERT INTO `sys_user_role` (`role_id`, `user_id`) VALUES (4, '1704740314666848257');
INSERT INTO `sys_user_role` (`role_id`, `user_id`) VALUES (8, '1689843130490179585');
INSERT INTO `sys_user_role` (`role_id`, `user_id`) VALUES (8, '1704740314666848257');
INSERT INTO `sys_user_role` (`role_id`, `user_id`) VALUES (9, '1689843309113004034');
INSERT INTO `sys_user_role` (`role_id`, `user_id`) VALUES (9, '1704740314666848257');
INSERT INTO `sys_user_role` (`role_id`, `user_id`) VALUES (10, '1689843391623352321');
INSERT INTO `sys_user_role` (`role_id`, `user_id`) VALUES (10, '1704740314666848257');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
