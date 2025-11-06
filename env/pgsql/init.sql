-- PostgreSQL 数据库脚本
DROP DATABASE IF EXISTS eu;
CREATE DATABASE eu;
-- 切换到新数据库
\c eu;

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS gen_table;
CREATE TABLE gen_table (
  id VARCHAR(20) NOT NULL,
  package_name VARCHAR(255) NOT NULL, -- 包路径
  module_name VARCHAR(32) NOT NULL, -- 模块名
  func_group VARCHAR(32) DEFAULT NULL, -- 功能分组
  table_name VARCHAR(32) NOT NULL, -- 表名
  table_comment VARCHAR(255) DEFAULT NULL, -- 表注释
  author VARCHAR(32) DEFAULT NULL, -- 作者
  del_show_field VARCHAR(32) DEFAULT NULL, -- 删除时，提示使用的字段
  gen_mode SMALLINT NOT NULL DEFAULT 0, -- 生成模式
  detail_header_field_key VARCHAR(32) DEFAULT NULL, -- 详情页头部字段
  crud_edit_mode VARCHAR(10) NOT NULL, -- crud编辑模式
  i18n_enable BOOLEAN NOT NULL DEFAULT FALSE, -- 开启i18n
  create_by VARCHAR(20) DEFAULT NULL, -- 创建人
  create_by_name VARCHAR(20) DEFAULT NULL, -- 创建人
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 创建时间
  update_by VARCHAR(20) DEFAULT NULL, -- 修改人
  update_by_name VARCHAR(20) DEFAULT NULL, -- 修改人
  update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 修改时间
  PRIMARY KEY (id),
  CONSTRAINT uni_table_name UNIQUE (table_name) -- 表唯一
);

-- 添加表注释
COMMENT ON TABLE gen_table IS '代码生成表配置';
COMMENT ON COLUMN gen_table.package_name IS '包路径';
COMMENT ON COLUMN gen_table.module_name IS '模块名';
COMMENT ON COLUMN gen_table.func_group IS '功能分组';
COMMENT ON COLUMN gen_table.table_name IS '表名';
COMMENT ON COLUMN gen_table.table_comment IS '表注释';
COMMENT ON COLUMN gen_table.author IS '作者';
COMMENT ON COLUMN gen_table.del_show_field IS '删除时，提示使用的字段';
COMMENT ON COLUMN gen_table.gen_mode IS '生成模式';
COMMENT ON COLUMN gen_table.detail_header_field_key IS '详情页头部字段';
COMMENT ON COLUMN gen_table.crud_edit_mode IS 'crud编辑模式';
COMMENT ON COLUMN gen_table.i18n_enable IS '开启i18n';
COMMENT ON COLUMN gen_table.create_by IS '创建人';
COMMENT ON COLUMN gen_table.create_by_name IS '创建人';
COMMENT ON COLUMN gen_table.create_time IS '创建时间';
COMMENT ON COLUMN gen_table.update_by IS '修改人';
COMMENT ON COLUMN gen_table.update_by_name IS '修改人';
COMMENT ON COLUMN gen_table.update_time IS '修改时间';

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS gen_table_column;
CREATE TABLE gen_table_column (
  id VARCHAR(20) NOT NULL,
  table_name VARCHAR(32) NOT NULL, -- 表名
  column_name VARCHAR(32) NOT NULL, -- 字段名
  column_comment VARCHAR(255) DEFAULT NULL, -- 字段描述
  column_key VARCHAR(32) DEFAULT NULL, -- 字段键
  column_type VARCHAR(32) NOT NULL, -- 字段类型
  auto_pk BOOLEAN NOT NULL DEFAULT FALSE, -- 是否自增
  column_sort INTEGER NOT NULL, -- 字段排序
  not_null BOOLEAN NOT NULL DEFAULT FALSE, -- 不为null
  java_type VARCHAR(32) NOT NULL, -- java字段类型
  java_field VARCHAR(32) NOT NULL, -- java字段名称
  final_column_comment VARCHAR(255) DEFAULT NULL, -- 调整后的字段描述
  column_length INTEGER DEFAULT NULL, -- 字段长度
  excel_export BOOLEAN NOT NULL DEFAULT FALSE, -- 是否导出
  table_show BOOLEAN NOT NULL DEFAULT FALSE, -- 是否在列表显示
  form_show BOOLEAN NOT NULL DEFAULT FALSE, -- 是否在表单显示
  js_type VARCHAR(32) NOT NULL, -- js字段类型
  form_type VARCHAR(32) DEFAULT NULL, -- 表单类型
  query_type VARCHAR(32) DEFAULT NULL, -- 查询方式
  dict_key VARCHAR(32) DEFAULT NULL, -- 关联字典
  enum_key VARCHAR(32) DEFAULT NULL, -- 关联枚举
  area_query BOOLEAN DEFAULT FALSE, -- 查询区域查询
  table_header_query BOOLEAN DEFAULT FALSE, -- 是否表头里查询
  default_visible BOOLEAN NOT NULL DEFAULT FALSE, -- 默认是否可见
  create_by VARCHAR(20) DEFAULT NULL, -- 创建人
  create_by_name VARCHAR(20) DEFAULT NULL, -- 创建人
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 创建时间
  update_by VARCHAR(20) DEFAULT NULL, -- 修改人
  update_by_name VARCHAR(20) DEFAULT NULL, -- 修改人
  update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 修改时间
  PRIMARY KEY (id),
  CONSTRAINT uni_table_column UNIQUE (table_name, column_name) -- 字典唯一
);

-- 添加表注释
COMMENT ON TABLE gen_table_column IS '代码生成列配置';
COMMENT ON COLUMN gen_table_column.table_name IS '表名';
COMMENT ON COLUMN gen_table_column.column_name IS '字段名';
COMMENT ON COLUMN gen_table_column.column_comment IS '字段描述';
COMMENT ON COLUMN gen_table_column.column_key IS '字段键';
COMMENT ON COLUMN gen_table_column.column_type IS '字段类型';
COMMENT ON COLUMN gen_table_column.auto_pk IS '是否自增';
COMMENT ON COLUMN gen_table_column.column_sort IS '字段排序';
COMMENT ON COLUMN gen_table_column.not_null IS '不为null';
COMMENT ON COLUMN gen_table_column.java_type IS 'java字段类型';
COMMENT ON COLUMN gen_table_column.java_field IS 'java字段名称';
COMMENT ON COLUMN gen_table_column.final_column_comment IS '调整后的字段描述';
COMMENT ON COLUMN gen_table_column.column_length IS '字段长度';
COMMENT ON COLUMN gen_table_column.excel_export IS '是否导出';
COMMENT ON COLUMN gen_table_column.table_show IS '是否在列表显示';
COMMENT ON COLUMN gen_table_column.form_show IS '是否在表单显示';
COMMENT ON COLUMN gen_table_column.js_type IS 'js字段类型';
COMMENT ON COLUMN gen_table_column.form_type IS '表单类型';
COMMENT ON COLUMN gen_table_column.query_type IS '查询方式';
COMMENT ON COLUMN gen_table_column.dict_key IS '关联字典';
COMMENT ON COLUMN gen_table_column.enum_key IS '关联枚举';
COMMENT ON COLUMN gen_table_column.area_query IS '查询区域查询';
COMMENT ON COLUMN gen_table_column.table_header_query IS '是否表头里查询';
COMMENT ON COLUMN gen_table_column.default_visible IS '默认是否可见';
COMMENT ON COLUMN gen_table_column.create_by IS '创建人';
COMMENT ON COLUMN gen_table_column.create_by_name IS '创建人';
COMMENT ON COLUMN gen_table_column.create_time IS '创建时间';
COMMENT ON COLUMN gen_table_column.update_by IS '修改人';
COMMENT ON COLUMN gen_table_column.update_by_name IS '修改人';
COMMENT ON COLUMN gen_table_column.update_time IS '修改时间';

-- ----------------------------
-- Table structure for quartz_job
-- ----------------------------
DROP TABLE IF EXISTS quartz_job;
CREATE TABLE quartz_job (
  id VARCHAR(20) NOT NULL,
  job_name VARCHAR(32) NOT NULL, -- 任务名称
  job_group VARCHAR(32) NOT NULL, -- 任务分组
  cron VARCHAR(64) NOT NULL, -- cron表达式
  status SMALLINT NOT NULL, -- 任务状态
  misfire_policy SMALLINT NOT NULL, -- 执行策略
  concurrent SMALLINT NOT NULL, -- 是否允许并发
  invoke_class_name VARCHAR(255) DEFAULT NULL, -- 任务执行类
  spring_bean_name VARCHAR(64) DEFAULT NULL, -- 任务执行类的SpringBean名
  method_name VARCHAR(64) NOT NULL, -- 执行任务的方法名
  method_params VARCHAR(512) DEFAULT NULL, -- 方法参数
  pause_after_failure BOOLEAN NOT NULL DEFAULT FALSE, -- 失败后是否暂停任务
  alarm_email VARCHAR(255) DEFAULT NULL, -- 任务失败后的告警邮箱
  create_by VARCHAR(20) DEFAULT NULL, -- 创建人
  create_by_name VARCHAR(20) DEFAULT NULL, -- 创建人
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 创建时间
  update_by VARCHAR(20) DEFAULT NULL, -- 修改人
  update_by_name VARCHAR(20) DEFAULT NULL, -- 修改人
  update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 修改时间
  remark VARCHAR(255) DEFAULT NULL, -- 备注
  del_flag SMALLINT DEFAULT 0, -- 删除标志
  PRIMARY KEY (id)
);

-- 添加表注释
COMMENT ON TABLE quartz_job IS 'quartz任务';
COMMENT ON COLUMN quartz_job.job_name IS '任务名称';
COMMENT ON COLUMN quartz_job.job_group IS '任务分组';
COMMENT ON COLUMN quartz_job.cron IS 'cron表达式';
COMMENT ON COLUMN quartz_job.status IS '任务状态';
COMMENT ON COLUMN quartz_job.misfire_policy IS '执行策略';
COMMENT ON COLUMN quartz_job.concurrent IS '是否允许并发';
COMMENT ON COLUMN quartz_job.invoke_class_name IS '任务执行类';
COMMENT ON COLUMN quartz_job.spring_bean_name IS '任务执行类的SpringBean名';
COMMENT ON COLUMN quartz_job.method_name IS '执行任务的方法名';
COMMENT ON COLUMN quartz_job.method_params IS '方法参数';
COMMENT ON COLUMN quartz_job.pause_after_failure IS '失败后是否暂停任务';
COMMENT ON COLUMN quartz_job.alarm_email IS '任务失败后的告警邮箱';
COMMENT ON COLUMN quartz_job.create_by IS '创建人';
COMMENT ON COLUMN quartz_job.create_by_name IS '创建人';
COMMENT ON COLUMN quartz_job.create_time IS '创建时间';
COMMENT ON COLUMN quartz_job.update_by IS '修改人';
COMMENT ON COLUMN quartz_job.update_by_name IS '修改人';
COMMENT ON COLUMN quartz_job.update_time IS '修改时间';
COMMENT ON COLUMN quartz_job.remark IS '备注';
COMMENT ON COLUMN quartz_job.del_flag IS '删除标志';

-- 插入数据
INSERT INTO quartz_job (id, job_name, job_group, cron, status, misfire_policy, concurrent, invoke_class_name, spring_bean_name, method_name, method_params, pause_after_failure, alarm_email, create_by, create_time, update_by, update_time, remark, del_flag)
VALUES ('1672495122451099649', '测试内容', 'DEFAULT', '0/5 * * * * ?', 1, 0, 0, NULL, 'testJob', 'run', 'abc', FALSE, NULL, '1', '2023-06-24 06:41:25', '1', '2023-10-12 03:34:22', NULL, 0);

-- ----------------------------
-- Table structure for quartz_job_log
-- ----------------------------
DROP TABLE IF EXISTS quartz_job_log;
CREATE TABLE quartz_job_log (
  id VARCHAR(20) NOT NULL,
  job_id VARCHAR(20) NOT NULL, -- 任务ID
  job_name VARCHAR(32) NOT NULL, -- 任务名称
  invoke_class_name VARCHAR(255) DEFAULT NULL, -- 任务执行类
  spring_bean_name VARCHAR(64) DEFAULT NULL, -- 任务执行类的SpringBean名
  method_name VARCHAR(64) NOT NULL, -- 执行任务的方法名
  method_params VARCHAR(512) DEFAULT NULL, -- 方法参数
  success BOOLEAN NOT NULL DEFAULT FALSE, -- 是否执行成功
  exception_message VARCHAR(512) DEFAULT NULL, -- 异常消息
  exception_detail VARCHAR(2048) DEFAULT NULL, -- 异常详情
  start_time TIMESTAMP NOT NULL, -- 开始执行时间
  end_time TIMESTAMP NOT NULL, -- 结束执行时间
  exec_time BIGINT NOT NULL, -- 执行时长，单位：毫秒
  create_by VARCHAR(20) DEFAULT NULL, -- 创建人
  create_by_name VARCHAR(20) DEFAULT NULL, -- 创建人
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 创建时间
  update_by VARCHAR(20) DEFAULT NULL, -- 修改人
  update_by_name VARCHAR(20) DEFAULT NULL, -- 修改人
  update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 修改时间
  remark VARCHAR(255) DEFAULT NULL, -- 备注
  del_flag SMALLINT DEFAULT 0, -- 删除标志
  PRIMARY KEY (id)
);

-- 添加表注释
COMMENT ON TABLE quartz_job_log IS '任务执行日志';
COMMENT ON COLUMN quartz_job_log.job_id IS '任务ID';
COMMENT ON COLUMN quartz_job_log.job_name IS '任务名称';
COMMENT ON COLUMN quartz_job_log.invoke_class_name IS '任务执行类';
COMMENT ON COLUMN quartz_job_log.spring_bean_name IS '任务执行类的SpringBean名';
COMMENT ON COLUMN quartz_job_log.method_name IS '执行任务的方法名';
COMMENT ON COLUMN quartz_job_log.method_params IS '方法参数';
COMMENT ON COLUMN quartz_job_log.success IS '是否执行成功';
COMMENT ON COLUMN quartz_job_log.exception_message IS '异常消息';
COMMENT ON COLUMN quartz_job_log.exception_detail IS '异常详情';
COMMENT ON COLUMN quartz_job_log.start_time IS '开始执行时间';
COMMENT ON COLUMN quartz_job_log.end_time IS '结束执行时间';
COMMENT ON COLUMN quartz_job_log.exec_time IS '执行时长，单位：毫秒';
COMMENT ON COLUMN quartz_job_log.create_by IS '创建人';
COMMENT ON COLUMN quartz_job_log.create_by_name IS '创建人';
COMMENT ON COLUMN quartz_job_log.create_time IS '创建时间';
COMMENT ON COLUMN quartz_job_log.update_by IS '修改人';
COMMENT ON COLUMN quartz_job_log.update_by_name IS '修改人';
COMMENT ON COLUMN quartz_job_log.update_time IS '修改时间';
COMMENT ON COLUMN quartz_job_log.remark IS '备注';
COMMENT ON COLUMN quartz_job_log.del_flag IS '删除标志';

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS sys_dept;
CREATE TABLE sys_dept (
  id INTEGER GENERATED ALWAYS AS IDENTITY, -- 主键自增
  dept_name VARCHAR(20) NOT NULL, -- 部门名称
  parent_id INTEGER NOT NULL, -- 父级ID
  parent_ids VARCHAR(50) NOT NULL, -- 祖级列表
  status SMALLINT NOT NULL DEFAULT 0, -- 状态
  sort_num INTEGER NOT NULL DEFAULT 0,
  create_by VARCHAR(20) DEFAULT NULL, -- 创建人
  create_by_name VARCHAR(20) DEFAULT NULL, -- 创建人
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 创建时间
  update_by VARCHAR(20) DEFAULT NULL, -- 修改人
  update_by_name VARCHAR(20) DEFAULT NULL, -- 修改人
  update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 修改时间
  remark VARCHAR(255) DEFAULT NULL, -- 备注
  del_flag SMALLINT DEFAULT 0, -- 删除标志
  PRIMARY KEY (id),
  CONSTRAINT uni_dept_name UNIQUE (dept_name, parent_id, del_flag) -- 同级部门唯一
);

-- 添加表注释
COMMENT ON TABLE sys_dept IS '部门';
COMMENT ON COLUMN sys_dept.dept_name IS '部门名称';
COMMENT ON COLUMN sys_dept.parent_id IS '父级ID';
COMMENT ON COLUMN sys_dept.parent_ids IS '祖级列表';
COMMENT ON COLUMN sys_dept.status IS '状态';
COMMENT ON COLUMN sys_dept.create_by IS '创建人';
COMMENT ON COLUMN sys_dept.create_by_name IS '创建人';
COMMENT ON COLUMN sys_dept.create_time IS '创建时间';
COMMENT ON COLUMN sys_dept.update_by IS '修改人';
COMMENT ON COLUMN sys_dept.update_by_name IS '修改人';
COMMENT ON COLUMN sys_dept.update_time IS '修改时间';
COMMENT ON COLUMN sys_dept.remark IS '备注';
COMMENT ON COLUMN sys_dept.del_flag IS '删除标志';

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS sys_dict;
CREATE TABLE sys_dict (
  id INTEGER GENERATED ALWAYS AS IDENTITY, -- 主键自增
  dict_key VARCHAR(20) NOT NULL, -- 字典key
  status SMALLINT NOT NULL DEFAULT 0, -- 状态
  create_by VARCHAR(20) DEFAULT NULL, -- 创建人
  create_by_name VARCHAR(20) DEFAULT NULL, -- 创建人
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 创建时间
  update_by VARCHAR(20) DEFAULT NULL, -- 修改人
  update_by_name VARCHAR(20) DEFAULT NULL, -- 修改人
  update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 修改时间
  remark VARCHAR(255) DEFAULT NULL, -- 备注
  del_flag SMALLINT DEFAULT 0, -- 删除标志
  PRIMARY KEY (id),
  CONSTRAINT uni_dict_key UNIQUE (dict_key, del_flag) -- 字典Key唯一
);

-- 添加表注释
COMMENT ON TABLE sys_dict IS '字典';
COMMENT ON COLUMN sys_dict.dict_key IS '字典key';
COMMENT ON COLUMN sys_dict.status IS '状态';
COMMENT ON COLUMN sys_dict.create_by IS '创建人';
COMMENT ON COLUMN sys_dict.create_by_name IS '创建人';
COMMENT ON COLUMN sys_dict.create_time IS '创建时间';
COMMENT ON COLUMN sys_dict.update_by IS '修改人';
COMMENT ON COLUMN sys_dict.update_by_name IS '修改人';
COMMENT ON COLUMN sys_dict.update_time IS '修改时间';
COMMENT ON COLUMN sys_dict.remark IS '备注';
COMMENT ON COLUMN sys_dict.del_flag IS '删除标志';

-- ----------------------------
-- Table structure for sys_dict_detail
-- ----------------------------
DROP TABLE IF EXISTS sys_dict_detail;
CREATE TABLE sys_dict_detail (
  id INTEGER GENERATED ALWAYS AS IDENTITY, -- 主键自增
  pid INTEGER NOT NULL, -- 字典ID
  dict_key VARCHAR(30) NOT NULL, -- 字典Key
  dict_label VARCHAR(32) NOT NULL, -- 字典标签
  dict_value VARCHAR(30) NOT NULL, -- 字典Value
  sort_num INTEGER NOT NULL DEFAULT 0, -- 排序
  status SMALLINT NOT NULL DEFAULT 0, -- 状态
  create_by VARCHAR(20) DEFAULT NULL, -- 创建人
  create_by_name VARCHAR(20) DEFAULT NULL, -- 创建人
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 创建时间
  update_by VARCHAR(20) DEFAULT NULL, -- 修改人
  update_by_name VARCHAR(20) DEFAULT NULL, -- 修改人
  update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 修改时间
  remark VARCHAR(255) DEFAULT NULL, -- 备注
  del_flag SMALLINT DEFAULT 0, -- 删除标志
  PRIMARY KEY (id),
  CONSTRAINT uni_label UNIQUE (pid, dict_label, del_flag) -- 字典Label唯一
);

-- 添加表注释
COMMENT ON TABLE sys_dict_detail IS '字典详情';
COMMENT ON COLUMN sys_dict_detail.pid IS '字典ID';
COMMENT ON COLUMN sys_dict_detail.dict_key IS '字典Key';
COMMENT ON COLUMN sys_dict_detail.dict_label IS '字典标签';
COMMENT ON COLUMN sys_dict_detail.dict_value IS '字典Value';
COMMENT ON COLUMN sys_dict_detail.sort_num IS '排序';
COMMENT ON COLUMN sys_dict_detail.status IS '状态';
COMMENT ON COLUMN sys_dict_detail.create_by IS '创建人';
COMMENT ON COLUMN sys_dict_detail.create_by_name IS '创建人';
COMMENT ON COLUMN sys_dict_detail.create_time IS '创建时间';
COMMENT ON COLUMN sys_dict_detail.update_by IS '修改人';
COMMENT ON COLUMN sys_dict_detail.update_by_name IS '修改人';
COMMENT ON COLUMN sys_dict_detail.update_time IS '修改时间';
COMMENT ON COLUMN sys_dict_detail.remark IS '备注';
COMMENT ON COLUMN sys_dict_detail.del_flag IS '删除标志';

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS sys_menu;
CREATE TABLE sys_menu (
  id INTEGER GENERATED ALWAYS AS IDENTITY, -- 主键自增
  menu_name VARCHAR(20) NOT NULL, -- 菜单名称
  menu_icon VARCHAR(255) DEFAULT NULL, -- 菜单图标
  sort_num INTEGER NOT NULL DEFAULT 0, -- 显示顺序
  permission VARCHAR(255) DEFAULT NULL, -- 权限标识
  path VARCHAR(255) DEFAULT NULL, -- 路由地址
  component_name VARCHAR(255) DEFAULT NULL, -- 组件名称
  component VARCHAR(255) DEFAULT NULL, -- 组件路径
  status SMALLINT NOT NULL DEFAULT 0, -- 菜单状态
  affix BOOLEAN DEFAULT FALSE,
  visible BOOLEAN DEFAULT FALSE, -- 是否显示
  cache BOOLEAN DEFAULT FALSE, -- 是否缓存
  embed BOOLEAN DEFAULT FALSE, -- 是否内嵌
  embed_url VARCHAR(255) DEFAULT NULL, -- 内嵌链接
  dot BOOLEAN DEFAULT FALSE, -- 是否显示小红点
  badge VARCHAR(5) DEFAULT NULL, -- 徽标内容
  menu_type SMALLINT NOT NULL, -- 菜单类型
  parent_id INTEGER DEFAULT NULL, -- 父菜单ID
  show_header BOOLEAN DEFAULT NULL, -- 是否显示Header
  show_footer BOOLEAN DEFAULT NULL, -- 是否显示Footer
  create_by VARCHAR(20) DEFAULT NULL, -- 创建人
  create_by_name VARCHAR(20) DEFAULT NULL, -- 创建人
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 创建时间
  update_by VARCHAR(20) DEFAULT NULL, -- 修改人
  update_by_name VARCHAR(20) DEFAULT NULL, -- 修改人
  update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 修改时间
  remark VARCHAR(255) DEFAULT NULL, -- 备注
  del_flag SMALLINT DEFAULT 0, -- 删除标志
  always_show BOOLEAN DEFAULT NULL, -- 是否保持显示
  PRIMARY KEY (id)
);

-- 添加表注释
COMMENT ON TABLE sys_menu IS '菜单';
COMMENT ON COLUMN sys_menu.menu_name IS '菜单名称';
COMMENT ON COLUMN sys_menu.menu_icon IS '菜单图标';
COMMENT ON COLUMN sys_menu.sort_num IS '显示顺序';
COMMENT ON COLUMN sys_menu.permission IS '权限标识';
COMMENT ON COLUMN sys_menu.path IS '路由地址';
COMMENT ON COLUMN sys_menu.component_name IS '组件名称';
COMMENT ON COLUMN sys_menu.component IS '组件路径';
COMMENT ON COLUMN sys_menu.status IS '菜单状态';
COMMENT ON COLUMN sys_menu.visible IS '是否显示';
COMMENT ON COLUMN sys_menu.cache IS '是否缓存';
COMMENT ON COLUMN sys_menu.embed IS '是否内嵌';
COMMENT ON COLUMN sys_menu.embed_url IS '内嵌链接';
COMMENT ON COLUMN sys_menu.dot IS '是否显示小红点';
COMMENT ON COLUMN sys_menu.badge IS '徽标内容';
COMMENT ON COLUMN sys_menu.menu_type IS '菜单类型';
COMMENT ON COLUMN sys_menu.parent_id IS '父菜单ID';
COMMENT ON COLUMN sys_menu.show_header IS '是否显示Header';
COMMENT ON COLUMN sys_menu.show_footer IS '是否显示Footer';
COMMENT ON COLUMN sys_menu.create_by IS '创建人';
COMMENT ON COLUMN sys_menu.create_by_name IS '创建人';
COMMENT ON COLUMN sys_menu.create_time IS '创建时间';
COMMENT ON COLUMN sys_menu.update_by IS '修改人';
COMMENT ON COLUMN sys_menu.update_by_name IS '修改人';
COMMENT ON COLUMN sys_menu.update_time IS '修改时间';
COMMENT ON COLUMN sys_menu.remark IS '备注';
COMMENT ON COLUMN sys_menu.del_flag IS '删除标志';
COMMENT ON COLUMN sys_menu.always_show IS '是否保持显示';

-- 插入菜单数据
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (10, '系统设置', 'shezhi', 10, NULL, '/system', NULL, NULL, 0, FALSE, TRUE, NULL, FALSE, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, '2023-06-05 08:18:37', '1', '2024-04-17 12:59:02', NULL, 0, FALSE);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (20, '系统监控', 'monitor', 20, NULL, '/monitor', NULL, NULL, 0, FALSE, TRUE, FALSE, FALSE, NULL, FALSE, NULL, 1, NULL, NULL, NULL, '1', '2023-06-11 08:18:27', '1', '2024-04-17 12:59:17', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (30, '系统工具', 'gongju', 30, NULL, '/tools', NULL, NULL, 0, FALSE, TRUE, NULL, FALSE, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, '2023-06-05 08:18:38', '1', '2023-10-20 09:19:52', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (40, '组件', 'table_layout', 40, NULL, '/components', NULL, NULL, 0, FALSE, TRUE, FALSE, FALSE, NULL, FALSE, NULL, 1, NULL, NULL, NULL, '1', '2023-06-30 02:02:47', '1', '2024-04-17 12:59:53', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (50, '一级菜单', 'shortcut', 50, NULL, '/one', 'First', 'first', 0, FALSE, TRUE, FALSE, FALSE, NULL, FALSE, NULL, 2, NULL, TRUE, TRUE, '1', '2023-09-05 08:46:45', '1', '2024-04-17 13:00:23', NULL, 0, FALSE);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (60, '多级菜单', 'multi-dir', 60, NULL, '/first', NULL, NULL, 0, FALSE, TRUE, FALSE, FALSE, NULL, FALSE, NULL, 1, NULL, TRUE, TRUE, '1', '2023-07-31 03:01:10', '1', '2024-04-17 13:00:25', NULL, 0, FALSE);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (70, '关于', 'yingyongkaifazhe', 999, NULL, 'https://gitee.com/zhaoeryu/eu-backend-web', '', '', 0, FALSE, TRUE, FALSE, FALSE, NULL, FALSE, NULL, 2, NULL, FALSE, FALSE, NULL, '2023-06-05 08:18:38', '1', '2024-04-17 13:00:18', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (1001, '用户管理', NULL, 20, 'system:user:list', '/users', 'Users', 'system/users', 0, FALSE, TRUE, TRUE, FALSE, NULL, FALSE, NULL, 2, 10, TRUE, TRUE, NULL, '2023-06-05 08:18:37', '1', '2024-04-17 13:01:59', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (1002, '部门管理', NULL, 30, 'system:dept:list', '/depts', 'Depts', 'system/depts', 0, FALSE, TRUE, TRUE, FALSE, NULL, NULL, NULL, 2, 10, TRUE, TRUE, NULL, '2023-06-05 08:18:37', '1', '2024-04-17 13:02:02', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (1003, '岗位管理', NULL, 40, 'system:post:list', '/posts', 'Posts', 'system/posts', 0, FALSE, TRUE, TRUE, FALSE, NULL, NULL, NULL, 2, 10, TRUE, TRUE, NULL, '2023-06-05 08:18:37', '1', '2024-04-17 13:02:07', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (1004, '角色管理', NULL, 50, 'system:role:list', '/roles', 'Roles', 'system/roles', 0, FALSE, TRUE, TRUE, FALSE, NULL, NULL, NULL, 2, 10, TRUE, TRUE, NULL, '2023-06-05 08:18:37', '1', '2024-04-17 13:02:11', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (1005, '角色成员', NULL, 999, 'system:role:assign', '/roles/auth-user/:roleId', 'AuthUser', 'system/roles/auth-user', 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 2, 10, TRUE, TRUE, NULL, '2023-06-05 08:18:37', '1', '2024-04-17 13:17:10', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (1006, '菜单管理', NULL, 60, 'system:menu:list', '/menus', 'Menus', 'system/menus', 0, FALSE, TRUE, TRUE, FALSE, NULL, NULL, NULL, 2, 10, TRUE, TRUE, NULL, '2023-06-05 08:18:37', '1', '2024-04-17 13:02:23', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (1007, '字典管理', NULL, 70, 'system:dict:list', '/dicts', 'Dicts', 'system/dicts', 0, FALSE, TRUE, TRUE, FALSE, NULL, NULL, NULL, 2, 10, TRUE, TRUE, NULL, '2023-06-05 08:18:37', '1', '2024-04-17 13:02:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (1008, '任务管理', NULL, 80, 'system:job:list', '/jobs', 'Jobs', 'system/jobs', 0, FALSE, TRUE, TRUE, FALSE, NULL, FALSE, NULL, 2, 10, TRUE, TRUE, NULL, '2023-06-05 08:18:37', '1', '2024-04-17 13:02:33', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (1009, '通知公告', 'tongzhi', 90, NULL, '/sysNotice', 'SysNotice', 'system/sysNotice', 0, FALSE, TRUE, TRUE, FALSE, NULL, FALSE, NULL, 2, 10, TRUE, TRUE, NULL, '2023-08-30 05:40:34', '1', '2024-04-17 13:19:05', NULL, 0, FALSE);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2000, '在线用户', NULL, 10, 'monitor:online:list', '/online', 'OnlineUser', 'monitor/online', 0, FALSE, TRUE, FALSE, FALSE, NULL, FALSE, 'New', 2, 20, TRUE, TRUE, '1', '2023-06-11 08:20:20', '1', '2024-04-17 13:09:15', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2001, '操作日志', NULL, 20, 'system:sysOperLog:list', '/oper-logs', 'OperLogs', 'system/oper-logs', 0, FALSE, TRUE, TRUE, FALSE, NULL, FALSE, NULL, 2, 20, TRUE, TRUE, NULL, '2023-06-05 08:18:37', '1', '2024-04-17 13:15:33', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2002, 'Druid', NULL, 30, NULL, 'http://localhost:8000/druid/datasource.html', NULL, NULL, 0, FALSE, TRUE, FALSE, FALSE, NULL, FALSE, NULL, 2, 20, NULL, NULL, '1', '2023-07-12 03:46:41', NULL, '2024-04-17 13:09:20', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2100, '代码生成', NULL, 10, 'tools:gen', '/gen', 'Gen', 'tools/generate', 0, FALSE, TRUE, TRUE, FALSE, NULL, FALSE, NULL, 2, 30, TRUE, TRUE, NULL, '2023-06-05 08:18:38', '1', '2024-04-17 13:15:48', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2101, '系统接口', NULL, 20, NULL, '/swagger', NULL, 'tools/swagger', 0, FALSE, TRUE, TRUE, TRUE, 'http://localhost:8000/doc.html', FALSE, NULL, 2, 30, FALSE, FALSE, NULL, '2023-06-05 08:18:38', '1', '2024-04-17 13:14:45', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2201, '富文本编辑器', NULL, 10, NULL, '/editor', 'Editor', 'components/editor', 0, FALSE, TRUE, FALSE, FALSE, NULL, FALSE, NULL, 2, 40, TRUE, TRUE, '1', '2023-06-30 02:07:33', '1', '2024-04-17 13:09:32', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2202, '文件上传', NULL, 20, NULL, '/upload-file', NULL, 'components/demo-upload-file', 0, FALSE, TRUE, FALSE, FALSE, NULL, FALSE, NULL, 2, 40, TRUE, TRUE, '1', '2023-06-30 02:08:28', '1', '2024-04-17 13:09:35', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2300, '二级菜单1', NULL, 10, NULL, 'second1', 'Second1', 'first/second1', 0, FALSE, TRUE, FALSE, FALSE, NULL, FALSE, NULL, 2, 60, TRUE, TRUE, '1', '2023-07-31 03:03:39', '1', '2024-04-17 13:09:50', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2301, '二级菜单2', NULL, 20, NULL, 'second2', NULL, NULL, 0, FALSE, TRUE, FALSE, FALSE, NULL, FALSE, NULL, 1, 60, TRUE, TRUE, '1', '2023-07-31 03:02:37', '1', '2024-04-17 13:09:53', NULL, 0, FALSE);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2302, '二级菜单3', NULL, 30, NULL, 'second3', NULL, NULL, 0, FALSE, TRUE, FALSE, FALSE, NULL, FALSE, NULL, 1, 60, TRUE, TRUE, '1', '2023-08-03 12:39:57', '1', '2024-04-17 13:09:56', NULL, 0, FALSE);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2400, '三级菜单1', NULL, 10, NULL, 'third1', 'Second2Third1', 'first/second2/third1', 0, FALSE, TRUE, FALSE, FALSE, NULL, FALSE, NULL, 2, 2301, TRUE, TRUE, '1', '2023-07-31 03:05:59', '1', '2024-04-17 13:10:10', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2401, '三级菜单2', NULL, 20, NULL, 'third2', 'Second2Third2', 'first/second2/third2', 0, FALSE, TRUE, FALSE, FALSE, NULL, FALSE, '', 2, 2301, TRUE, TRUE, '1', '2023-07-31 03:06:23', '1', '2024-04-17 13:10:13', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2410, '三级菜单1', NULL, 10, NULL, 'third1', NULL, NULL, 0, FALSE, TRUE, FALSE, FALSE, NULL, FALSE, NULL, 1, 2302, TRUE, TRUE, '1', '2023-08-03 12:40:33', NULL, '2024-04-17 13:10:31', NULL, 0, FALSE);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2411, '四级菜单1', NULL, 10, NULL, 'four1', 'Second3Third1Four1', 'first/second3/third1/four1', 0, FALSE, TRUE, FALSE, FALSE, NULL, FALSE, NULL, 2, 2410, TRUE, TRUE, '1', '2023-08-03 12:41:14', '1', '2024-04-17 13:10:37', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2500, '修改', NULL, 10, 'system:user:edit', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1001, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2501, '新增', NULL, 20, 'system:user:add', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1001, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2502, '导出', NULL, 30, 'system:user:export', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1001, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2503, '重置密码', NULL, 40, 'system:user:resetPwd', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1001, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2504, '删除', NULL, 50, 'system:user:del', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1001, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2505, '导入', NULL, 60, 'system:user:import', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1001, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2506, '修改', NULL, 10, 'system:dept:edit', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1002, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2507, '新增', NULL, 20, 'system:dept:add', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1002, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2508, '删除', NULL, 30, 'system:dept:del', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1002, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2509, '修改', NULL, 10, 'system:post:edit', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1003, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2510, '新增', NULL, 20, 'system:post:add', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1003, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2511, '删除', NULL, 30, 'system:post:del', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1003, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2512, '导出', NULL, 40, 'system:post:export', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1003, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2513, '导入', NULL, 50, 'system:post:import', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1003, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2514, '修改', NULL, 10, 'system:role:edit', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1004, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2515, '新增', NULL, 20, 'system:role:add', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1004, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2516, '删除', NULL, 30, 'system:role:del', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1004, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2517, '导出', NULL, 40, 'system:role:export', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1004, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2518, '导入', NULL, 50, 'system:role:import', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1004, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2519, '修改', NULL, 10, 'system:menu:edit', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1006, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2520, '新增', NULL, 20, 'system:menu:add', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1006, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2521, '删除', NULL, 30, 'system:menu:del', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1006, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2522, '修改', NULL, 10, 'system:dict:edit', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1007, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2523, '新增', NULL, 20, 'system:dict:add', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1007, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2524, '删除', NULL, 30, 'system:dict:del', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1007, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2525, '导出', NULL, 40, 'system:dict:export', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1007, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2526, '导入', NULL, 50, 'system:dict:import', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1007, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2527, '字典详情修改', NULL, 10, 'system:dict-detail:edit', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1007, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2528, '字典详情新增', NULL, 20, 'system:dict-detail:add', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1007, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2529, '字典详情删除', NULL, 30, 'system:dict-detail:del', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1007, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2530, '字典详情导出', NULL, 40, 'system:dict-detail:export', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1007, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2531, '字典详情导入', NULL, 50, 'system:dict-detail:import', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1007, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2532, '字典详情列表', NULL, 60, 'system:dict-detail:list', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1007, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2533, '修改', NULL, 10, 'system:job:edit', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1008, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2534, '新增', NULL, 20, 'system:job:add', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1008, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2535, '删除', NULL, 30, 'system:job:del', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1008, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2536, '执行任务', NULL, 40, 'system:job:exec', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1008, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2537, '任务日志导出', NULL, 10, 'system:job-log:export', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1008, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2538, '任务日志删除', NULL, 20, 'system:job-log:del', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1008, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2539, '任务日志列表', NULL, 30, 'system:job-log:list', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1008, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2540, '修改', NULL, 10, 'system:sysNotice:edit', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1009, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2541, '新增', NULL, 20, 'system:sysNotice:add', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1009, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2542, '删除', NULL, 30, 'system:sysNotice:del', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1009, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2543, '导出', NULL, 40, 'system:sysNotice:export', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1009, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2544, '详情', NULL, 50, 'system:sysNotice:query', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 1009, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2545, '踢TA下线', NULL, 10, 'monitor:online:kickout', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 2000, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2546, '强制登出', NULL, 20, 'monitor:online:logout', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 2000, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);
INSERT INTO sys_menu (id, menu_name, menu_icon, sort_num, permission, path, component_name, component, status, affix, visible, cache, embed, embed_url, dot, badge, menu_type, parent_id, show_header, show_footer, create_by, create_time, update_by, update_time, remark, del_flag, always_show)
OVERRIDING SYSTEM VALUE
VALUES (2547, '导出', NULL, 10, 'system:sysOperLog:export', NULL, NULL, NULL, 0, FALSE, FALSE, FALSE, FALSE, NULL, FALSE, NULL, 3, 2001, NULL, NULL, NULL, '2024-04-17 13:48:27', NULL, '2024-04-17 13:48:27', NULL, 0, NULL);

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS sys_notice;
CREATE TABLE sys_notice (
  id VARCHAR(20) NOT NULL,
  title VARCHAR(64) NOT NULL, -- 标题
  type SMALLINT NOT NULL, -- 公告类型
  description VARCHAR(1024) NOT NULL, -- 公告描述
  content TEXT NOT NULL, -- 公告内容
  status SMALLINT NOT NULL, -- 公告状态
  publisher VARCHAR(32) DEFAULT NULL, -- 发布人
  create_by VARCHAR(20) DEFAULT NULL, -- 创建人
  create_by_name VARCHAR(20) DEFAULT NULL, -- 创建人
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 创建时间
  update_by VARCHAR(20) DEFAULT NULL, -- 修改人
  update_by_name VARCHAR(20) DEFAULT NULL, -- 修改人
  update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 修改时间
  remark VARCHAR(255) DEFAULT NULL, -- 备注
  del_flag SMALLINT DEFAULT 0, -- 删除标志
  PRIMARY KEY (id)
);

-- 添加表注释
COMMENT ON TABLE sys_notice IS '通知公告';
COMMENT ON COLUMN sys_notice.title IS '标题';
COMMENT ON COLUMN sys_notice.type IS '公告类型';
COMMENT ON COLUMN sys_notice.description IS '公告描述';
COMMENT ON COLUMN sys_notice.content IS '公告内容';
COMMENT ON COLUMN sys_notice.status IS '公告状态';
COMMENT ON COLUMN sys_notice.publisher IS '发布人';
COMMENT ON COLUMN sys_notice.create_by IS '创建人';
COMMENT ON COLUMN sys_notice.create_by_name IS '创建人';
COMMENT ON COLUMN sys_notice.create_time IS '创建时间';
COMMENT ON COLUMN sys_notice.update_by IS '修改人';
COMMENT ON COLUMN sys_notice.update_by_name IS '修改人';
COMMENT ON COLUMN sys_notice.update_time IS '修改时间';
COMMENT ON COLUMN sys_notice.remark IS '备注';
COMMENT ON COLUMN sys_notice.del_flag IS '删除标志';

-- 插入通知数据
INSERT INTO sys_notice (id, title, type, description, content, status, publisher, create_by, create_time, update_by, update_time, remark, del_flag) VALUES ('1696788131262599170', '新版发布通知', 0, '🔥EuBackend v1.0.0版本采用Vue2+ElementUI全新发布！', '<h1>新版发布通知</h1><h3>🔥EuBackend v1.0.0版本采用Vue2+ElementUI全新发布！</h3><ul><li>测试内容测试内容测试内容测试内容</li><li>测试内容测试内容测试内容测试内容</li><li>测试内容测试内容测试内容测试内容</li></ul><pre><code >This is My Editor</code></pre><p><br></p>', 0, 'Eu.z', '1', '2023-08-30 07:33:09', '1', '2023-08-30 08:08:25', NULL, 0);
INSERT INTO sys_notice (id, title, type, description, content, status, publisher, create_by, create_time, update_by, update_time, remark, del_flag) VALUES ('1696797959468982273', '「通知公告」功能上线啦', 1, '🔥EuBackend v1.1.0 新增「通知公告」功能，欢迎使用！', '<h1>「通知公告」功能上线啦</h1><h3>🔥EuBackend v1.1.0 新增「通知公告」功能，欢迎使用！</h3><ol><li>系统设置/通知公告</li><li>右上角通知图标</li></ol>', 0, '系统管理员', '1', '2023-08-30 08:12:13', NULL, '2023-08-30 08:12:13', NULL, 0);

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS sys_oper_log;
CREATE TABLE sys_oper_log (
  id VARCHAR(20) NOT NULL,
  title VARCHAR(32) NOT NULL, -- 操作模块
  business_type SMALLINT NOT NULL, -- 业务类型
  method VARCHAR(255) NOT NULL, -- 执行方法
  req_method VARCHAR(10) NOT NULL, -- Http请求方式
  oper_name VARCHAR(32) DEFAULT NULL, -- 操作人名称
  dept_name VARCHAR(32) DEFAULT NULL, -- 操作人部门
  req_url VARCHAR(1024) NOT NULL, -- 请求URL
  req_ip VARCHAR(32) NOT NULL, -- 请求IP
  req_region VARCHAR(32) DEFAULT NULL, -- 请求地域
  req_params TEXT DEFAULT NULL, -- 请求参数
  resp_result TEXT DEFAULT NULL, -- 响应结果
  browser VARCHAR(32) NOT NULL, -- 浏览器
  os VARCHAR(32) NOT NULL, -- 操作系统
  status SMALLINT NOT NULL, -- 操作状态
  error_msg VARCHAR(255) DEFAULT NULL, -- 错误消息
  error_stack TEXT, -- 异常堆栈
  exec_time INTEGER NOT NULL, -- 执行时长
  create_by VARCHAR(20) DEFAULT NULL, -- 创建人
  create_by_name VARCHAR(20) DEFAULT NULL, -- 创建人
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 创建时间
  update_by VARCHAR(20) DEFAULT NULL, -- 修改人
  update_by_name VARCHAR(20) DEFAULT NULL, -- 修改人
  update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 修改时间
  remark VARCHAR(255) DEFAULT NULL, -- 备注
  del_flag SMALLINT DEFAULT 0, -- 删除标志
  PRIMARY KEY (id)
);

-- 添加表注释
COMMENT ON TABLE sys_oper_log IS '操作日志';
COMMENT ON COLUMN sys_oper_log.title IS '操作模块';
COMMENT ON COLUMN sys_oper_log.business_type IS '业务类型';
COMMENT ON COLUMN sys_oper_log.method IS '执行方法';
COMMENT ON COLUMN sys_oper_log.req_method IS 'Http请求方式';
COMMENT ON COLUMN sys_oper_log.oper_name IS '操作人名称';
COMMENT ON COLUMN sys_oper_log.dept_name IS '操作人部门';
COMMENT ON COLUMN sys_oper_log.req_url IS '请求URL';
COMMENT ON COLUMN sys_oper_log.req_ip IS '请求IP';
COMMENT ON COLUMN sys_oper_log.req_region IS '请求地域';
COMMENT ON COLUMN sys_oper_log.req_params IS '请求参数';
COMMENT ON COLUMN sys_oper_log.resp_result IS '响应结果';
COMMENT ON COLUMN sys_oper_log.browser IS '浏览器';
COMMENT ON COLUMN sys_oper_log.os IS '操作系统';
COMMENT ON COLUMN sys_oper_log.status IS '操作状态';
COMMENT ON COLUMN sys_oper_log.error_msg IS '错误消息';
COMMENT ON COLUMN sys_oper_log.error_stack IS '异常堆栈';
COMMENT ON COLUMN sys_oper_log.exec_time IS '执行时长';
COMMENT ON COLUMN sys_oper_log.create_by IS '创建人';
COMMENT ON COLUMN sys_oper_log.create_by_name IS '创建人';
COMMENT ON COLUMN sys_oper_log.create_time IS '创建时间';
COMMENT ON COLUMN sys_oper_log.update_by IS '修改人';
COMMENT ON COLUMN sys_oper_log.update_by_name IS '修改人';
COMMENT ON COLUMN sys_oper_log.update_time IS '修改时间';
COMMENT ON COLUMN sys_oper_log.remark IS '备注';
COMMENT ON COLUMN sys_oper_log.del_flag IS '删除标志';

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS sys_post;
CREATE TABLE sys_post (
  id INTEGER GENERATED ALWAYS AS IDENTITY, -- 主键自增
  post_name VARCHAR(20) NOT NULL, -- 岗位名称
  code VARCHAR(20) NOT NULL, -- 岗位编码
  status SMALLINT NOT NULL DEFAULT 0, -- 状态
  create_by VARCHAR(20) DEFAULT NULL, -- 创建人
  create_by_name VARCHAR(20) DEFAULT NULL, -- 创建人
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 创建时间
  update_by VARCHAR(20) DEFAULT NULL, -- 修改人
  update_by_name VARCHAR(20) DEFAULT NULL, -- 修改人
  update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 修改时间
  remark VARCHAR(255) DEFAULT NULL, -- 备注
  del_flag SMALLINT DEFAULT 0, -- 删除标志
  PRIMARY KEY (id),
  CONSTRAINT uni_post_name UNIQUE (post_name, del_flag), -- 岗位名称唯一
  CONSTRAINT uni_code UNIQUE (code, del_flag) -- 岗位编码唯一
);

-- 添加表注释
COMMENT ON TABLE sys_post IS '岗位';
COMMENT ON COLUMN sys_post.post_name IS '岗位名称';
COMMENT ON COLUMN sys_post.code IS '岗位编码';
COMMENT ON COLUMN sys_post.status IS '状态';
COMMENT ON COLUMN sys_post.create_by IS '创建人';
COMMENT ON COLUMN sys_post.create_by_name IS '创建人';
COMMENT ON COLUMN sys_post.create_time IS '创建时间';
COMMENT ON COLUMN sys_post.update_by IS '修改人';
COMMENT ON COLUMN sys_post.update_by_name IS '修改人';
COMMENT ON COLUMN sys_post.update_time IS '修改时间';
COMMENT ON COLUMN sys_post.remark IS '备注';
COMMENT ON COLUMN sys_post.del_flag IS '删除标志';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS sys_role;
CREATE TABLE sys_role (
  id INTEGER GENERATED ALWAYS AS IDENTITY, -- 主键自增
  role_key VARCHAR(30) NOT NULL, -- 角色Key
  role_name VARCHAR(20) NOT NULL, -- 角色名称
  description VARCHAR(255) DEFAULT NULL, -- 角色描述
  status SMALLINT NOT NULL DEFAULT 0, -- 状态
  data_scope SMALLINT DEFAULT NULL, -- 数据权限
  create_by VARCHAR(20) DEFAULT NULL, -- 创建人
  create_by_name VARCHAR(20) DEFAULT NULL, -- 创建人
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 创建时间
  update_by VARCHAR(20) DEFAULT NULL, -- 修改人
  update_by_name VARCHAR(20) DEFAULT NULL, -- 修改人
  update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 修改时间
  remark VARCHAR(255) DEFAULT NULL, -- 备注
  del_flag SMALLINT DEFAULT 0, -- 删除标志
  PRIMARY KEY (id),
  CONSTRAINT uni_role_key UNIQUE (role_key, del_flag), -- 角色Key唯一
  CONSTRAINT uni_role_name UNIQUE (role_name, del_flag) -- 角色名称唯一
);

-- 添加表注释
COMMENT ON TABLE sys_role IS '角色';
COMMENT ON COLUMN sys_role.role_key IS '角色Key';
COMMENT ON COLUMN sys_role.role_name IS '角色名称';
COMMENT ON COLUMN sys_role.description IS '角色描述';
COMMENT ON COLUMN sys_role.status IS '状态';
COMMENT ON COLUMN sys_role.data_scope IS '数据权限';
COMMENT ON COLUMN sys_role.create_by IS '创建人';
COMMENT ON COLUMN sys_role.create_by_name IS '创建人';
COMMENT ON COLUMN sys_role.create_time IS '创建时间';
COMMENT ON COLUMN sys_role.update_by IS '修改人';
COMMENT ON COLUMN sys_role.update_by_name IS '修改人';
COMMENT ON COLUMN sys_role.update_time IS '修改时间';
COMMENT ON COLUMN sys_role.remark IS '备注';
COMMENT ON COLUMN sys_role.del_flag IS '删除标志';

-- 插入角色数据
INSERT INTO sys_role (id, role_key, role_name, description, status, data_scope, create_by, create_time, update_by, update_time, remark, del_flag)
OVERRIDING SYSTEM VALUE
VALUES (1, 'admin', '系统管理员', '拥有网站的全部权限', 0, NULL, NULL, '2023-06-05 07:53:20', NULL, '2023-06-05 07:53:20', NULL, 0);

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS sys_role_dept;
CREATE TABLE sys_role_dept (
  role_id INTEGER NOT NULL, -- 角色ID
  dept_id INTEGER NOT NULL, -- 部门ID
  PRIMARY KEY (role_id, dept_id)
);

-- 添加表注释
COMMENT ON TABLE sys_role_dept IS '角色和部门的关系';
COMMENT ON COLUMN sys_role_dept.role_id IS '角色ID';
COMMENT ON COLUMN sys_role_dept.dept_id IS '部门ID';

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS sys_role_menu;
CREATE TABLE sys_role_menu (
  role_id INTEGER NOT NULL, -- 角色ID
  menu_id INTEGER NOT NULL, -- 菜单ID
  PRIMARY KEY (role_id, menu_id)
);

-- 添加表注释
COMMENT ON TABLE sys_role_menu IS '角色和菜单的关系';
COMMENT ON COLUMN sys_role_menu.role_id IS '角色ID';
COMMENT ON COLUMN sys_role_menu.menu_id IS '菜单ID';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user (
  id VARCHAR(20) NOT NULL,
  username VARCHAR(20) NOT NULL, -- 登录名
  nickname VARCHAR(10) NOT NULL, -- 昵称
  avatar VARCHAR(512) DEFAULT NULL, -- 头像
  mobile VARCHAR(11) DEFAULT NULL, -- 手机号
  email VARCHAR(50) DEFAULT NULL, -- 邮箱
  password VARCHAR(128) NOT NULL, -- 密码
  sex SMALLINT DEFAULT NULL, -- 性别
  admin SMALLINT NOT NULL DEFAULT 0, -- 是否管理员
  dept_id INTEGER DEFAULT NULL, -- 部门
  status SMALLINT NOT NULL DEFAULT 0, -- 账号状态
  login_ip VARCHAR(128) DEFAULT NULL, -- 登录IP
  login_time TIMESTAMP DEFAULT NULL, -- 登录时间
  last_active_time TIMESTAMP DEFAULT NULL, -- 最后一次活跃时间
  password_reset_time TIMESTAMP DEFAULT NULL, -- 最后一次密码重置时间
  create_by VARCHAR(20) DEFAULT NULL, -- 创建人
  create_by_name VARCHAR(20) DEFAULT NULL, -- 创建人
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 创建时间
  update_by VARCHAR(20) DEFAULT NULL, -- 修改人
  update_by_name VARCHAR(20) DEFAULT NULL, -- 修改人
  update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 修改时间
  remark VARCHAR(255) DEFAULT NULL, -- 备注
  del_flag SMALLINT DEFAULT 0, -- 删除标志
  PRIMARY KEY (id),
  CONSTRAINT uni_username UNIQUE (username, del_flag) -- 登录名唯一
);

-- 添加表注释
COMMENT ON TABLE sys_user IS '用户';
COMMENT ON COLUMN sys_user.username IS '登录名';
COMMENT ON COLUMN sys_user.nickname IS '昵称';
COMMENT ON COLUMN sys_user.avatar IS '头像';
COMMENT ON COLUMN sys_user.mobile IS '手机号';
COMMENT ON COLUMN sys_user.email IS '邮箱';
COMMENT ON COLUMN sys_user.password IS '密码';
COMMENT ON COLUMN sys_user.sex IS '性别';
COMMENT ON COLUMN sys_user.admin IS '是否管理员';
COMMENT ON COLUMN sys_user.dept_id IS '部门';
COMMENT ON COLUMN sys_user.status IS '账号状态';
COMMENT ON COLUMN sys_user.login_ip IS '登录IP';
COMMENT ON COLUMN sys_user.login_time IS '登录时间';
COMMENT ON COLUMN sys_user.last_active_time IS '最后一次活跃时间';
COMMENT ON COLUMN sys_user.password_reset_time IS '最后一次密码重置时间';
COMMENT ON COLUMN sys_user.create_by IS '创建人';
COMMENT ON COLUMN sys_user.create_by_name IS '创建人';
COMMENT ON COLUMN sys_user.create_time IS '创建时间';
COMMENT ON COLUMN sys_user.update_by IS '修改人';
COMMENT ON COLUMN sys_user.update_by_name IS '修改人';
COMMENT ON COLUMN sys_user.update_time IS '修改时间';
COMMENT ON COLUMN sys_user.remark IS '备注';
COMMENT ON COLUMN sys_user.del_flag IS '删除标志';

-- 插入用户数据
INSERT INTO sys_user (id, username, nickname, avatar, mobile, email, password, sex, admin, dept_id, status, login_ip, login_time, last_active_time, password_reset_time, create_by, create_time, update_by, update_time, remark, del_flag) 
VALUES ('1', 'admin', '系统管理员', 'http://localhost:8000/static/png/1780557025503002624.png', '18500000000', 'admin@eu.com', '$2a$05$QMz7BBsK6eUl4VpUt9fFZeoonk.qUEFVR2vLwKOs6p1kP4aVQVPCm', 1, 1, NULL, 0, '127.0.0.1', '2023-10-20 15:38:30', '2023-10-20 17:28:33', '2023-09-20 22:32:04', NULL, '2023-06-05 05:59:02', '1', '2023-10-20 09:28:33', NULL, 0);

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS sys_user_post;
CREATE TABLE sys_user_post (
  post_id INTEGER NOT NULL, -- 岗位ID
  user_id VARCHAR(20) NOT NULL, -- 用户ID
  PRIMARY KEY (post_id, user_id)
);

-- 添加表注释
COMMENT ON TABLE sys_user_post IS '用户和岗位的关系';
COMMENT ON COLUMN sys_user_post.post_id IS '岗位ID';
COMMENT ON COLUMN sys_user_post.user_id IS '用户ID';

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS sys_user_role;
CREATE TABLE sys_user_role (
  role_id INTEGER NOT NULL, -- 角色ID
  user_id VARCHAR(20) NOT NULL, -- 用户ID
  PRIMARY KEY (role_id, user_id)
);

-- 添加表注释
COMMENT ON TABLE sys_user_role IS '用户和角色的关系';
COMMENT ON COLUMN sys_user_role.role_id IS '角色ID';
COMMENT ON COLUMN sys_user_role.user_id IS '用户ID';

-- 插入用户角色数据
INSERT INTO sys_user_role (role_id, user_id) VALUES (1, '1');




