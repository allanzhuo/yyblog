/*
Navicat MySQL Data Transfer

Target Server Type    : MYSQL
Target Server Version : 50639
File Encoding         : 65001

Date: 2018-07-25 21:13:27
项目相关脚本还不完善，目前还有很多冗余的字段或者是表，是我接下来要做的，各位可以先试着看一下，谢谢
如果项目对您有帮助，记得给项目点个Star哈
2.0版本将全部重做，遵循的数据库规范如下，推荐参考。
https://www.cnblogs.com/laoyeye/p/9925449.html
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `time` int(11) DEFAULT NULL COMMENT '响应时间',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数1,数组所有内容',
  `params2` varchar(5000) DEFAULT NULL COMMENT '请求参数2,数组第一个的内容',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1735 DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('800', '2', 'test', '查询文章列表', '165', 'net.laoyeye.yyblog.web.admin.ArticleController.listArticle()', '[net.laoyeye.yyblog.model.query.ArticleQuery@4c8f8701]', '{\"limit\":10,\"page\":1}', '0:0:0:0:0:0:0:1', '2018-07-08 22:45:35');
INSERT INTO `sys_log` VALUES ('801', '2', 'test', '打开文章列表页面', '3', 'net.laoyeye.yyblog.web.admin.ArticleController.index()', '[]', null, '0:0:0:0:0:0:0:1', '2018-07-08 22:45:48');
INSERT INTO `sys_log` VALUES ('802', '2', 'test', '查询文章列表', '291', 'net.laoyeye.yyblog.web.admin.ArticleController.listArticle()', '[net.laoyeye.yyblog.model.query.ArticleQuery@56a4cb05]', '{\"limit\":10,\"page\":1}', '0:0:0:0:0:0:0:1', '2018-07-08 22:45:49');
INSERT INTO `sys_log` VALUES ('803', '2', 'test', '打开文章列表页面', '2', 'net.laoyeye.yyblog.web.admin.ArticleController.index()', '[]', null, '0:0:0:0:0:0:0:1', '2018-07-08 22:46:48');
INSERT INTO `sys_log` VALUES ('804', '2', 'test', '查询文章列表', '60', 'net.laoyeye.yyblog.web.admin.ArticleController.listArticle()', '[net.laoyeye.yyblog.model.query.ArticleQuery@5a841c86]', '{\"limit\":10,\"page\":1}', '0:0:0:0:0:0:0:1', '2018-07-08 22:46:49');
INSERT INTO `sys_log` VALUES ('805', '2', 'test', '进入编辑文章页面', '62', 'net.laoyeye.yyblog.web.admin.ArticleController.edit()', '[{cateList=[net.laoyeye.yyblog.model.CateDO@140e86db, net.laoyeye.yyblog.model.CateDO@7bf86156, net.laoyeye.yyblog.model.CateDO@30219390], editArticle=net.laoyeye.yyblog.model.ArticleDO@58ac7a4, articleTags=github}, 152933278046718]', null, '0:0:0:0:0:0:0:1', '2018-07-08 22:47:10');
INSERT INTO `sys_log` VALUES ('806', '2', 'test', '打开文章列表页面', '1', 'net.laoyeye.yyblog.web.admin.ArticleController.index()', '[]', null, '0:0:0:0:0:0:0:1', '2018-07-08 22:47:18');
INSERT INTO `sys_log` VALUES ('807', '2', 'test', '查询文章列表', '211', 'net.laoyeye.yyblog.web.admin.ArticleController.listArticle()', '[net.laoyeye.yyblog.model.query.ArticleQuery@6758b58f]', '{\"limit\":10,\"page\":1}', '0:0:0:0:0:0:0:1', '2018-07-08 22:47:19');
INSERT INTO `sys_log` VALUES ('808', '2', 'test', '打开笔记列表页面', '1', 'net.laoyeye.yyblog.web.admin.NoteController.index()', '[]', null, '0:0:0:0:0:0:0:1', '2018-07-08 22:47:21');
INSERT INTO `sys_log` VALUES ('809', '2', 'test', '查询笔记列表', '18', 'net.laoyeye.yyblog.web.admin.NoteController.listNote()', '[net.laoyeye.yyblog.model.query.NoteQuery@1e2ea036]', '{\"limit\":10,\"page\":1}', '0:0:0:0:0:0:0:1', '2018-07-08 22:47:21');
INSERT INTO `sys_log` VALUES ('810', '2', 'test', '打开文章列表页面', '1', 'net.laoyeye.yyblog.web.admin.ArticleController.index()', '[]', null, '0:0:0:0:0:0:0:1', '2018-07-08 22:47:23');
INSERT INTO `sys_log` VALUES ('811', '2', 'test', '查询文章列表', '289', 'net.laoyeye.yyblog.web.admin.ArticleController.listArticle()', '[net.laoyeye.yyblog.model.query.ArticleQuery@64e9d0d]', '{\"limit\":10,\"page\":1}', '0:0:0:0:0:0:0:1', '2018-07-08 22:47:24');
INSERT INTO `sys_log` VALUES ('812', '2', 'test', '打开文章页面', '14', 'net.laoyeye.yyblog.web.admin.ArticleController.blog()', '[{cateList=[net.laoyeye.yyblog.model.CateDO@5508f863, net.laoyeye.yyblog.model.CateDO@1e876ee, net.laoyeye.yyblog.model.CateDO@12e317c9]}]', '{\"cateList\":[{\"code\":\"dxc\",\"id\":152759695653794,\"name\":\"多线程\"},{\"code\":\"maven\",\"id\":152776367270658,\"name\":\"maven\"},{\"code\":\"git\",\"id\":152933163827668,\"name\":\"开源\"}]}', '0:0:0:0:0:0:0:1', '2018-07-08 22:47:25');
INSERT INTO `sys_log` VALUES ('813', '2', 'test', '打开新增笔记页面', '0', 'net.laoyeye.yyblog.web.admin.NoteController.note()', '[]', null, '0:0:0:0:0:0:0:1', '2018-07-08 22:47:27');
INSERT INTO `sys_log` VALUES ('814', '2', 'test', '打开文章列表页面', '1', 'net.laoyeye.yyblog.web.admin.ArticleController.index()', '[]', null, '0:0:0:0:0:0:0:1', '2018-07-08 22:47:29');
INSERT INTO `sys_log` VALUES ('815', '2', 'test', '查询文章列表', '219', 'net.laoyeye.yyblog.web.admin.ArticleController.listArticle()', '[net.laoyeye.yyblog.model.query.ArticleQuery@2d0e16ca]', '{\"limit\":10,\"page\":1}', '0:0:0:0:0:0:0:1', '2018-07-08 22:47:29');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `create_user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '首页', null, 'blog:manage:index', '0', null, '1', '152336334290854', '2018-07-07 14:38:35', '152336334290854', '2018-07-07 14:38:40');
INSERT INTO `sys_menu` VALUES ('2', '0', '发布内容', null, 'blog:blog:addIndex', '0', 'fa', '2', '152336334290854', '2018-07-07 14:38:35', '152336334290854', '2018-07-07 14:38:40');
INSERT INTO `sys_menu` VALUES ('3', '1', '发布博文', '#blog', 'blog:blog:index', '1', null, '3', '152336334290854', '2018-07-07 14:38:35', '152336334290854', '2018-07-07 14:38:40');
INSERT INTO `sys_menu` VALUES ('4', '1', '发布笔记', '#note', 'blog:note:index', '1', null, '4', '152336334290854', '2018-07-07 14:38:35', '152336334290854', '2018-07-07 14:38:40');
INSERT INTO `sys_menu` VALUES ('5', '2', '保存文章', null, 'blog:blog:add', '2', null, '5', '152336334290854', '2018-07-07 14:38:35', '152336334290854', '2018-07-07 14:38:40');
INSERT INTO `sys_menu` VALUES ('6', '3', '保存笔记', null, 'blog:note:add', '2', null, '6', '152336334290854', '2018-07-07 14:38:35', '152336334290854', '2018-07-07 14:38:40');
INSERT INTO `sys_menu` VALUES ('7', '0', '内容管理', null, 'blog:content:index', '0', null, '7', '152336334290854', '2018-07-07 14:38:35', '152336334290854', '2018-07-07 14:38:40');
INSERT INTO `sys_menu` VALUES ('8', '6', '博文管理', null, 'blog:blogs:index', '1', null, '8', '152336334290854', '2018-07-07 14:38:35', '152336334290854', '2018-07-07 14:38:40');
INSERT INTO `sys_menu` VALUES ('9', '6', '笔记管理', null, 'blog:notes:index', '1', null, '9', '152336334290854', '2018-07-07 14:38:35', '152336334290854', '2018-07-07 14:38:40');
INSERT INTO `sys_menu` VALUES ('10', '7', '修改打赏状态', null, 'blog:blogs:appreciable', '2', null, '10', '152336334290854', '2018-07-07 14:38:35', '152336334290854', '2018-07-07 14:38:40');
INSERT INTO `sys_menu` VALUES ('11', '7', '修改评论状态', null, 'blog:blogs:commented', '2', null, '11', '152336334290854', '2018-07-07 14:38:35', '152336334290854', '2018-07-07 14:38:40');
INSERT INTO `sys_menu` VALUES ('12', '7', '修改置顶状态', null, 'blog:blogs:top', '2', null, '12', '152336334290854', '2018-07-07 14:38:35', '152336334290854', '2018-07-07 14:38:40');
INSERT INTO `sys_menu` VALUES ('13', '7', '进入编辑文章页面', null, 'blog:blogs:editIndex', '2', null, '13', '152336334290854', '2018-07-07 14:38:35', '152336334290854', '2018-07-07 14:38:40');
INSERT INTO `sys_menu` VALUES ('14', '12', '编辑提交', null, 'blog:blog:edit', '2', null, '14', '152336334290854', '2018-07-07 14:38:35', '152336334290854', '2018-07-07 14:38:40');
INSERT INTO `sys_menu` VALUES ('15', '7', '删除文章', null, 'blog:blogs:delete', '2', null, '15', '152336334290854', '2018-07-07 14:38:35', '152336334290854', '2018-07-07 14:38:40');
INSERT INTO `sys_menu` VALUES ('16', '12', '上传博客封面', null, 'blog:blog:cover', '2', null, '16', '152336334290854', '2018-07-07 14:38:35', '152336334290854', '2018-07-07 14:38:40');
INSERT INTO `sys_menu` VALUES ('17', '12', '上传博客图片', null, 'blog:blog:upload', '2', null, '17', '152336334290854', '2018-07-07 14:38:35', '152336334290854', '2018-07-07 14:38:40');
INSERT INTO `sys_menu` VALUES ('18', '0', '类别管理', null, 'blog:cate:index', '0', null, '18', '152336334290854', '2018-07-07 14:38:35', '152336334290854', '2018-07-07 14:38:40');
INSERT INTO `sys_menu` VALUES ('19', '0', '用户管理', null, 'sys:user:index', '0', null, '19', '152336334290854', '2018-07-07 14:38:35', '152336334290854', '2018-07-07 14:38:40');
INSERT INTO `sys_menu` VALUES ('20', '0', '系统设置', null, 'sys:sys:index', '0', null, '20', '152336334290854', '2018-07-07 14:38:35', '152336334290854', '2018-07-07 14:38:40');
INSERT INTO `sys_menu` VALUES ('21', '20', '网站设置', null, 'sys:set:index', '1', null, '21', '152336334290854', '2018-07-07 14:38:35', '152336334290854', '2018-07-07 14:38:40');
INSERT INTO `sys_menu` VALUES ('22', '20', '打赏设置', null, 'sys:qrcode:index', '1', null, '22', '152336334290854', '2018-07-07 14:38:35', '152336334290854', '2018-07-07 14:38:40');
INSERT INTO `sys_menu` VALUES ('23', '20', '计划任务', null, 'sys:task:index', '1', null, '23', '152336334290854', '2018-07-07 14:38:35', '152336334290854', '2018-07-07 14:38:40');
INSERT INTO `sys_menu` VALUES ('24', '20', '系统日志', null, 'sys:log:index', '1', null, '24', '152336334290854', '2018-07-07 14:38:35', '152336334290854', '2018-07-07 14:38:40');
INSERT INTO `sys_menu` VALUES ('25', '0', '评论管理', null, 'blog:comment:index', '1', null, '25', '152336334290854', '2018-07-07 14:38:35', '152336334290854', '2018-07-07 14:38:40');
INSERT INTO `sys_menu` VALUES ('26', '0', '留言管理', null, 'blog:message:index', '1', null, '26', '152336334290854', '2018-07-07 14:38:35', '152336334290854', '2018-07-07 14:38:40');
INSERT INTO `sys_menu` VALUES ('27', '23', '增加', null, 'sys:task:add', '2', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('28', '23', '修改', null, 'sys:task:update', '2', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('29', '23', '删除', null, 'sys:task:delete', '2', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('30', '24', '删除', null, 'sys:log:delete', '2', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('31', '20', '在线用户', null, 'sys:online:index', '1', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('32', '31', '强制退出', null, 'sys:online:forceout', '2', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('33', '22', '上传打赏码', null, 'sys:qrcode:upload', '2', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('34', '7', '资源管理', null, 'blog:file:index', '1', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('35', '7', '关于内容', null, 'blog:about:index', '1', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('36', '35', '修改', null, 'blog:about:update', '2', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(100) DEFAULT NULL COMMENT '角色标识',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_user` bigint(255) DEFAULT NULL COMMENT '创建用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', 'admin', '拥有最高权限', '2', '2017-08-12 00:43:52', null, '2017-08-12 19:14:59');
INSERT INTO `sys_role` VALUES ('2', '临时管理员', null, '只拥有查看权限', null, null, null, null);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('2', '1', '2');
INSERT INTO `sys_role_menu` VALUES ('3', '1', '3');
INSERT INTO `sys_role_menu` VALUES ('4', '1', '4');
INSERT INTO `sys_role_menu` VALUES ('5', '1', '5');
INSERT INTO `sys_role_menu` VALUES ('6', '1', '6');
INSERT INTO `sys_role_menu` VALUES ('7', '1', '7');
INSERT INTO `sys_role_menu` VALUES ('8', '1', '8');
INSERT INTO `sys_role_menu` VALUES ('9', '1', '9');
INSERT INTO `sys_role_menu` VALUES ('10', '1', '10');
INSERT INTO `sys_role_menu` VALUES ('11', '1', '11');
INSERT INTO `sys_role_menu` VALUES ('12', '1', '12');
INSERT INTO `sys_role_menu` VALUES ('13', '1', '13');
INSERT INTO `sys_role_menu` VALUES ('14', '1', '14');
INSERT INTO `sys_role_menu` VALUES ('15', '1', '15');
INSERT INTO `sys_role_menu` VALUES ('16', '1', '16');
INSERT INTO `sys_role_menu` VALUES ('17', '2', '1');
INSERT INTO `sys_role_menu` VALUES ('18', '2', '2');
INSERT INTO `sys_role_menu` VALUES ('19', '2', '3');
INSERT INTO `sys_role_menu` VALUES ('21', '2', '7');
INSERT INTO `sys_role_menu` VALUES ('22', '2', '8');
INSERT INTO `sys_role_menu` VALUES ('24', '1', '17');
INSERT INTO `sys_role_menu` VALUES ('25', '1', '18');
INSERT INTO `sys_role_menu` VALUES ('26', '1', '19');
INSERT INTO `sys_role_menu` VALUES ('27', '1', '20');
INSERT INTO `sys_role_menu` VALUES ('28', '1', '21');
INSERT INTO `sys_role_menu` VALUES ('29', '1', '22');
INSERT INTO `sys_role_menu` VALUES ('30', '1', '23');
INSERT INTO `sys_role_menu` VALUES ('31', '1', '24');
INSERT INTO `sys_role_menu` VALUES ('32', '1', '25');
INSERT INTO `sys_role_menu` VALUES ('33', '1', '26');
INSERT INTO `sys_role_menu` VALUES ('34', '1', '27');
INSERT INTO `sys_role_menu` VALUES ('35', '1', '28');
INSERT INTO `sys_role_menu` VALUES ('36', '1', '29');
INSERT INTO `sys_role_menu` VALUES ('37', '1', '30');
INSERT INTO `sys_role_menu` VALUES ('38', '1', '31');
INSERT INTO `sys_role_menu` VALUES ('39', '1', '32');
INSERT INTO `sys_role_menu` VALUES ('40', '1', '33');
INSERT INTO `sys_role_menu` VALUES ('41', '2', '20');
INSERT INTO `sys_role_menu` VALUES ('42', '2', '22');
INSERT INTO `sys_role_menu` VALUES ('43', '2', '23');
INSERT INTO `sys_role_menu` VALUES ('44', '2', '24');
INSERT INTO `sys_role_menu` VALUES ('45', '2', '31');
INSERT INTO `sys_role_menu` VALUES ('46', '1', '34');
INSERT INTO `sys_role_menu` VALUES ('47', '1', '35');
INSERT INTO `sys_role_menu` VALUES ('48', '2', '35');
INSERT INTO `sys_role_menu` VALUES ('49', '2', '13');
INSERT INTO `sys_role_menu` VALUES ('50', '1', '36');


-- ----------------------------
-- Table structure for sys_task
-- ----------------------------
DROP TABLE IF EXISTS `sys_task`;
CREATE TABLE `sys_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `job_name` varchar(255) DEFAULT NULL COMMENT '任务名',
  `description` varchar(255) DEFAULT NULL COMMENT '任务描述',
  `cron_expression` varchar(255) DEFAULT NULL COMMENT 'cron表达式',
  `bean_class` varchar(255) DEFAULT NULL COMMENT '任务执行时调用哪个类的方法 包名+类名',
  `job_status` varchar(255) DEFAULT NULL COMMENT '任务状态',
  `job_group` varchar(255) DEFAULT NULL COMMENT '任务分组',
  `create_user` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_task
-- ----------------------------
INSERT INTO `sys_task` VALUES ('27', 'helloword', '跑批配置测试', '0/10 * * * * ?', 'net.laoyeye.yyblog.quartz.task.HelloWorldJob', '0', 'group', 'zhangzhuo', '2018-06-29 23:31:42', 'zhangzhuo', '2018-06-29 23:31:42');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `enable` tinyint(1) DEFAULT '1',
  `open_id` varchar(32) DEFAULT NULL COMMENT 'qq登录api的openid',
  `wx_open_id` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=154043494244827 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('2', 'test', '临时账号', '14e1b600b1fd579f47433b88e8d85291', 'http://t2.hddhhn.com/uploads/tu/201803/9999/1015e4a69c.jpg', null, '1', null, null, null, null);
INSERT INTO `sys_user` VALUES ('152336334290854', 'admin', '小卖铺的老爷爷', '14e1b600b1fd579f47433b88e8d85291', 'http://t2.hddhhn.com/uploads/tu/201803/9999/1015e4a69c.jpg', '', '1', '', null,'2018-07-23 23:56:26', '2018-07-16 18:47:51');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '152336334290854', '1');
INSERT INTO `sys_user_role` VALUES ('2', '2', '2');
INSERT INTO `sys_user_role` VALUES ('3', '153162241151717', '1');

-- ----------------------------
-- Table structure for t_about
-- ----------------------------
DROP TABLE IF EXISTS `t_about`;
CREATE TABLE `t_about` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `tab` varchar(50) NOT NULL,
  `content` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_about
-- ----------------------------
INSERT INTO `t_about` VALUES ('1', '关于「博客系统」', 'about_blog', '<p> </p><div class=\"layui-row layui-col-space20 layui-mt10\">此处可去后台 内容管理-&gt; 关于内容 处设置&nbsp;&nbsp;<br></div>');
INSERT INTO `t_about` VALUES ('2', '关于「我」', 'about_me', '<p>此处可去后台 内容管理-> 关于内容 处设置</p>');
INSERT INTO `t_about` VALUES ('3', '关于「网站」', 'about_website', '<p></p><p></p><p></p><p>此处可去后台 内容管理-&gt; 关于内容 处设置</p><p></p><p><br></p><p></p><p><br></p><p></p><p><br></p>');

-- ----------------------------
-- Table structure for t_article
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
  `id` bigint(20) unsigned NOT NULL,
  `title` varchar(100) NOT NULL,
  `cate_id` bigint(20) NOT NULL,
  `cover` varchar(255) DEFAULT NULL,
  `summary` varchar(300) DEFAULT NULL,
  `content` text NOT NULL,
  `text_content` text NOT NULL,
  `views` int(11) NOT NULL DEFAULT '0' COMMENT '浏览数',
  `approve_cnt` int(11) NOT NULL DEFAULT '0' COMMENT '点赞数',
  `commented` tinyint(1) NOT NULL DEFAULT '1',
  `appreciable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否开放赞赏',
  `draft` tinyint(1) NOT NULL DEFAULT '1',
  `top` tinyint(1) NOT NULL DEFAULT '0' COMMENT '排序顺序，置顶文章用',
  `author_id` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_article
-- ----------------------------
INSERT INTO `t_article` VALUES ('152759704083198', 'Java多线程与并发编程学习', '152759695653794', 'http://images.laoyeye.net/1527597023959928.png', '一、线程三大特性多线程有三大特性，原子性、可见性、有序性1.1什么是原子性即一个操作或者多个操作要么全部执行并且执行的过程不会被任何因素打断，要么就都不执行。一个很经典的例子就是银行账户转账问题：&nbsp;比如从账户A向账户B转1000元，那么必然包括2个操作：从账户A减去1000元，往账户B加上1000元。这2个操作必须要具备原子性才能保证不出现一些意外的问题。我们操作数据也是如此，比如i=i+1；其中就包括，读取i的值，计算i，写入i。这行代码在Java中是不具备原子性的，则', '<p style=\"text-indent:2em;\">\n	本文原由小卖铺的老爷爷发表于<a href=\"https://www.cnblogs.com/laoyeye/p/7636788.html\" target=\"_blank\">博客园</a>\n</p>\n<h1 style=\"text-indent:2em;\">\n	一、线程三大特性\n</h1>\n<p style=\"text-indent:2em;\">\n	多线程有三大特性，原子性、可见性、有序性\n</p>\n<h2 style=\"text-indent:2em;\">\n	1.1 什么是原子性\n</h2>\n<p style=\"text-indent:2em;\">\n	即一个操作或者多个操作 要么全部执行并且执行的过程不会被任何因素打断，要么就都不执行。<br />\n一个很经典的例子就是银行账户转账问题：&nbsp;<br />\n比如从账户A向账户B转1000元，那么必然包括2个操作：从账户A减去1000元，往账户B加上1000元。这2个操作必须要具备原子性才能保证不出现一些意外的问题。<br />\n我们操作数据也是如此，比如i = i+1；其中就包括，读取i的值，计算i，写入i。这行代码在Java中是不具备原子性的，则多线程运行肯定会出问题，所以也需要我们使用同步和lock这些东西来确保这个特性了。&nbsp;<br />\n原子性其实就是保证数据一致、线程安全一部分，\n</p>\n<h2 style=\"text-indent:2em;\">\n	1.2 什么是可见性\n</h2>\n<p style=\"text-indent:2em;\">\n	当多个线程访问同一个变量时，一个线程修改了这个变量的值，其他线程能够立即看得到修改的值。<br />\n若两个线程在不同的cpu，那么线程1改变了i的值还没刷新到主存，线程2又使用了i，那么这个i值肯定还是之前的，线程1对变量的修改线程没看到这就是可见性问题。&nbsp;\n</p>\n<h2 style=\"text-indent:2em;\">\n	1.3什么是有序性\n</h2>\n<p style=\"text-indent:2em;\">\n	程序执行的顺序按照代码的先后顺序执行。<br />\n一般来说处理器为了提高程序运行效率，可能会对输入代码进行优化，它不保证程序中各个语句的执行先后顺序同代码中的顺序一致，但是它会保证程序最终执行结果和代码顺序执行的结果是一致的。如下：<br />\nint a = 10;    //语句1<br />\nint r = 2;    //语句2<br />\na = a + 3;    //语句3<br />\nr = a*a;     //语句4<br />\n则因为重排序，他还可能执行顺序为 2-1-3-4，1-3-2-4<br />\n但绝不可能 2-1-4-3，因为这打破了依赖关系。<br />\n显然重排序对单线程运行是不会有任何问题，而多线程就不一定了，所以我们在多线程编程时就得考虑这个问题了。\n</p>\n<h1 style=\"text-indent:2em;\">\n	二：Java内存模型\n</h1>\n<p style=\"text-indent:2em;\">\n	共享内存模型指的就是Java内存模型(简称JMM)，JMM决定一个线程对共享变量的写入时,能对另一个线程可见。从抽象的角度来看，JMM定义了线程和主内存之间的抽象关系：线程之间的共享变量存储在主内存（main memory）中，每个线程都有一个私有的本地内存（local memory），本地内存中存储了该线程以读/写共享变量的副本。本地内存是JMM的一个抽象概念，并不真实存在。它涵盖了缓存，写缓冲区，寄存器以及其他的硬件和编译器优化。\n</p>\n<p style=\"text-indent:2em;\">\n	<img src=\"http://images2017.cnblogs.com/blog/1075594/201710/1075594-20171008105817356-514001318.png\" alt=\"\" /> \n</p>\n<p style=\"text-indent:2em;\">\n	从上图来看，线程A与线程B之间如要通信的话，必须要经历下面2个步骤：<br />\n1. 首先，线程A把本地内存A中更新过的共享变量刷新到主内存中去。<br />\n2. 然后，线程B到主内存中去读取线程A之前已更新过的共享变量。 <br />\n下面通过示意图来说明这两个步骤：&nbsp;\n</p>\n<p>\n	<img src=\"http://images2017.cnblogs.com/blog/1075594/201710/1075594-20171008105852871-1594576754.png\" alt=\"\" /> \n</p>\n<p style=\"text-indent:2em;\">\n	如上图所示，本地内存A和B有主内存中共享变量x的副本。假设初始时，这三个内存中的x值都为0。线程A在执行时，把更新后的x值（假设值为1）临时存放在自己的本地内存A中。当线程A和线程B需要通信时，线程A首先会把自己本地内存中修改后的x值刷新到主内存中，此时主内存中的x值变为了1。随后，线程B到主内存中去读取线程A更新后的x值，此时线程B的本地内存的x值也变为了1。<br />\n从整体来看，这两个步骤实质上是线程A在向线程B发送消息，而且这个通信过程必须要经过主内存。JMM通过控制主内存与每个线程的本地内存之间的交互，来为java程序员提供内存可见性保证。\n</p>\n<p style=\"text-indent:2em;\">\n	<span style=\"color:#ff0000;\"><strong>总结</strong><strong><span style=\"font-family:宋体;\">：什么是</span></strong><strong>J</strong><strong>ava<span style=\"font-family:宋体;\">内存模型：</span><span style=\"font-family:Calibri;\">java</span><span style=\"font-family:宋体;\">内存模型</span></strong><strong>简称</strong><strong>jmm<span style=\"font-family:宋体;\">，</span></strong><strong>定</strong><strong><span style=\"font-family:宋体;\">义了</span></strong><strong>一个线程</strong><strong><span style=\"font-family:宋体;\">对</span></strong><strong>另一个</strong><strong><span style=\"font-family:宋体;\">线程可见。</span></strong><strong>共享</strong><strong><span style=\"font-family:宋体;\">变量存放在主内存中，每个线程都有自己的</span></strong><strong>本地</strong><strong><span style=\"font-family:宋体;\">内存，</span></strong><strong>当</strong><strong><span style=\"font-family:宋体;\">多个线程同时访问一个数据的时候，可能本地内存没有及时刷新到主内存，所以就会发生线程</span></strong><strong>安全</strong><strong><span style=\"font-family:宋体;\">问题。</span></strong></span> \n</p>\n<h1 style=\"text-indent:2em;\">\n	三、Volatile关键字\n</h1>\n<h2 style=\"text-indent:2em;\">\n	3.1 什么是Volatile\n</h2>\n<p style=\"text-indent:2em;\">\n	Volatile 关键字的作用是变量在多个线程之间可见。\n</p>\n<div class=\"cnblogs_code\">\n<pre>\n\n\n\n\n<pre class=\"layui-code prettyprint\">class ThreadVolatileDemo extends Thread { public boolean flag = true;\n    @Override public void run() {\n        System.out.println(\"开始执行子线程....\"); while (flag) {\n        }\n        System.out.println(\"线程停止\");\n    } public void setRuning(boolean flag) { this.flag = flag;\n    }\n\n} public class ThreadVolatile { public static void main(String[] args) throws InterruptedException {\n        ThreadVolatileDemo threadVolatileDemo = new ThreadVolatileDemo();\n        threadVolatileDemo.start();\n        Thread.sleep(3000);\n        threadVolatileDemo.setRuning(false);\n        System.out.println(\"flag 已经设置成false\");\n        Thread.sleep(1000);\n        System.out.println(threadVolatileDemo.flag);\n\n    }\n}</pre>\n</pre>\n</div>\n<p style=\"text-indent:2em;\">\n	运行结果:\n</p>\n<p>\n	<img src=\"http://images2017.cnblogs.com/blog/1075594/201710/1075594-20171008112922918-542106488.png\" alt=\"\" /> \n</p>\n<p style=\"text-indent:2em;\">\n	<strong><span style=\"color:#ff0000;\">threadVolatileDemo.flag值也是false,可是为什么程序还是一直在运行呢？</span></strong> \n</p>\n<p style=\"text-indent:2em;\">\n	<strong><span style=\"color:#ff0000;\">原因:线程之间是不可见的，读取的是副本，没有及时读取到主内存结果。</span></strong><br />\n<strong><span style=\"color:#ff0000;\">解决办法：使用Volatile关键字将解决线程之间可见性, 强制线程每次读取该值的时候都去“主内存”中取值。</span></strong> \n</p>\n<h2 style=\"text-indent:2em;\">\n	3.2 Volatile非原子性\n</h2>\n<div class=\"cnblogs_code\">\n<pre><span style=\"color:#0000ff;\">public</span> <span style=\"color:#0000ff;\">class</span> VolatileNoAtomic <span style=\"color:#0000ff;\">extends</span><span style=\"color:#000000;\"> Thread { </span><span style=\"color:#0000ff;\">private</span> <span style=\"color:#0000ff;\">static</span> <span style=\"color:#0000ff;\">volatile</span> <span style=\"color:#0000ff;\">int</span><span style=\"color:#000000;\"> count; </span><span style=\"color:#008000;\">//</span><span style=\"color:#008000;\"> private static AtomicInteger count = new AtomicInteger(0);</span> <span style=\"color:#0000ff;\">private</span> <span style=\"color:#0000ff;\">static</span> <span style=\"color:#0000ff;\">void</span><span style=\"color:#000000;\"> addCount() { </span><span style=\"color:#0000ff;\">for</span> (<span style=\"color:#0000ff;\">int</span> i = 0; i &lt; 1000; i++<span style=\"color:#000000;\">) {\n            count</span>++<span style=\"color:#000000;\">; </span><span style=\"color:#008000;\">//</span><span style=\"color:#008000;\"> count.incrementAndGet();</span> <span style=\"color:#000000;\"> }\n        System.out.println(count);\n    } </span><span style=\"color:#0000ff;\">public</span> <span style=\"color:#0000ff;\">void</span><span style=\"color:#000000;\"> run() {\n        addCount();\n    } </span><span style=\"color:#0000ff;\">public</span> <span style=\"color:#0000ff;\">static</span> <span style=\"color:#0000ff;\">void</span><span style=\"color:#000000;\"> main(String[] args) {\n\n        VolatileNoAtomic[] arr </span>= <span style=\"color:#0000ff;\">new</span> VolatileNoAtomic[100<span style=\"color:#000000;\">]; </span><span style=\"color:#008000;\"> //</span><span style=\"color:#008000;\"> 初始化10个线程</span> <span style=\"color:#0000ff;\">for</span> (<span style=\"color:#0000ff;\">int</span> i = 0; i &lt; 10; i++<span style=\"color:#000000;\">) {\n            arr[i] </span>= <span style=\"color:#0000ff;\">new</span><span style=\"color:#000000;\"> VolatileNoAtomic();\n        } </span><span style=\"color:#0000ff;\">for</span> (<span style=\"color:#0000ff;\">int</span> i = 0; i &lt; 10; i++<span style=\"color:#000000;\">) {\n            arr[i].start();\n        }\n    }\n\n}</span></pre>\n</div>\n<p style=\"text-indent:2em;\">\n	<span style=\"font-family:宋体;\">运行结果</span>:\n</p>\n<p>\n	<img src=\"http://images2017.cnblogs.com/blog/1075594/201710/1075594-20171008113355653-747283103.png\" alt=\"\" /> \n</p>\n<p style=\"text-indent:2em;\">\n	<span style=\"color:#ff0000;\"><strong>结果发现数据不同步，因为Volatile不用具备原子性。所以Volatile只能解决将将结果刷新到主内存中去，并不能解决并发原子性问题。</strong></span> \n</p>\n<h2 style=\"text-indent:2em;\">\n	3.3 使用AtomicInteger<span style=\"font-family:宋体;\">原子类</span> \n</h2>\n<p style=\"text-indent:2em;\">\n	AtomicInteger是一个提供原子操作的Integer类，通过线程安全的方式操作加减，因此十分适合高并发情况下的使用，来源于java并发包。\n</p>\n<p style=\"text-indent:2em;\">\n	修改上面的代码：\n</p>\n<div class=\"cnblogs_code\">\n<pre><span style=\"color:#0000ff;\">public</span> <span style=\"color:#0000ff;\">class</span> VolatileNoAtomic <span style=\"color:#0000ff;\">extends</span><span style=\"color:#000000;\"> Thread { </span><span style=\"color:#0000ff;\"> private</span> <span style=\"color:#0000ff;\">static</span> AtomicInteger atomicInteger = <span style=\"color:#0000ff;\">new</span> AtomicInteger(0<span style=\"color:#000000;\">);\n\n    @Override </span><span style=\"color:#0000ff;\">public</span> <span style=\"color:#0000ff;\">void</span><span style=\"color:#000000;\"> run() { </span><span style=\"color:#0000ff;\">for</span> (<span style=\"color:#0000ff;\">int</span> i = 0; i &lt; 1000; i++<span style=\"color:#000000;\">) { </span><span style=\"color:#008000;\">//</span><span style=\"color:#008000;\">等同于count++ </span> <span style=\"color:#000000;\"> atomicInteger.incrementAndGet();\n        }\n        System.out.println(atomicInteger);\n    } </span><span style=\"color:#0000ff;\">public</span> <span style=\"color:#0000ff;\">static</span> <span style=\"color:#0000ff;\">void</span><span style=\"color:#000000;\"> main(String[] args) { </span><span style=\"color:#008000;\">//</span><span style=\"color:#008000;\"> 初始化10个线程</span> VolatileNoAtomic[] volatileNoAtomic = <span style=\"color:#0000ff;\">new</span> VolatileNoAtomic[10<span style=\"color:#000000;\">]; </span><span style=\"color:#0000ff;\">for</span> (<span style=\"color:#0000ff;\">int</span> i = 0; i &lt; 10; i++<span style=\"color:#000000;\">) { </span><span style=\"color:#008000;\">//</span><span style=\"color:#008000;\"> 创建</span> volatileNoAtomic[i] = <span style=\"color:#0000ff;\">new</span><span style=\"color:#000000;\"> VolatileNoAtomic();\n        } </span><span style=\"color:#0000ff;\">for</span> (<span style=\"color:#0000ff;\">int</span> i = 0; i &lt; volatileNoAtomic.length; i++<span style=\"color:#000000;\">) {\n            volatileNoAtomic[i].start();\n        }\n    }\n\n}</span></pre>\n</div>\n<p style=\"text-indent:2em;\">\n	运行结果：\n</p>\n<p>\n	<img src=\"http://images2017.cnblogs.com/blog/1075594/201710/1075594-20171008120251356-1010404030.png\" alt=\"\" /> \n</p>\n<h2 style=\"text-indent:2em;\">\n	3.4 volatile与synchronized区别\n</h2>\n<p style=\"text-indent:2em;\">\n	仅靠volatile不能保证线程的安全性。（原子性）<br />\n①volatile轻量级，只能修饰变量。synchronized重量级，还可修饰方法<br />\n②volatile只能保证数据的可见性，不能用来同步（没有原子性，不能保证线程安全），因为多个线程并发访问volatile修饰的变量不会阻塞。<br />\n③synchronized不仅保证可见性，而且还保证原子性，因为，只有获得了锁的线程才能进入临界区，从而保证临界区中的所有语句都全部执行。多个线程争抢synchronized锁对象时，会出现阻塞。<br />\n总结：<br />\n线程安全性包括两个方面，①可见性。②原子性。<br />\n从上面自增的例子中可以看出：仅仅使用volatile并不能保证线程安全性。而synchronized则可实现线程的安全性。\n</p>\n<h1 style=\"text-indent:2em;\">\n	四、ThreadLocal\n</h1>\n<h2 style=\"text-indent:2em;\">\n	4.1、什么是ThreadLocal\n</h2>\n<p style=\"text-indent:2em;\">\n	ThreadLocal提高一个线程的局部变量，<span style=\"color:#ff0000;\">访问某个线程拥有自己局部变量。</span><br />\n<span style=\"color:#000000;\">当使用ThreadLocal维护变量时，ThreadLocal为每个使用该变量的线程提供独立的变量副本，所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。</span><br />\nThreadLocal的接口方法<br />\nThreadLocal类接口很简单，只有4个方法，我们先来了解一下：\n</p>\n<p style=\"text-indent:2em;\">\n	<br />\n</p>\n<ul>\n	<li>\n		void set(Object value)设置当前线程的线程局部变量的值。\n	</li>\n	<li>\n		public Object get()该方法返回当前线程所对应的线程局部变量。\n	</li>\n	<li>\n		public void remove()将当前线程局部变量的值删除，目的是为了减少内存的占用，该方法是JDK 5.0新增的方法。需要指出的是，当线程结束后，对应该线程的局部变量将自动被垃圾回收，所以显式调用该方法清除线程的局部变量并不是必须的操作，但它可以加快内存回收的速度。\n	</li>\n	<li>\n		protected Object initialValue()返回该线程局部变量的初始值，该方法是一个protected的方法，显然是为了让子类覆盖而设计的。这个方法是一个延迟调用方法，在线程第1次调用get()或set(Object)时才执行，并且仅执行1次。ThreadLocal中的缺省实现直接返回一个null。\n	</li>\n</ul>\n<p>\n	<br />\n</p>\n<p style=\"text-indent:2em;\">\n	<br />\n案例:创建三个线程，每个线程生成自己独立序列号。<br />\n代码:\n</p>\n<div class=\"cnblogs_code\">\n<pre><span style=\"color:#0000ff;\">class</span><span style=\"color:#000000;\"> Res { </span><span style=\"color:#008000;\">//</span><span style=\"color:#008000;\"> 生成序列号共享变量 </span><span style=\"color:#008000;\">//</span><span style=\"color:#008000;\">public static Integer count = 0; </span><span style=\"color:#008000;\"> //</span><span style=\"color:#008000;\"> 设置本地局部变量，与其他线程互不影响</span> <span style=\"color:#0000ff;\">public</span> <span style=\"color:#0000ff;\">static</span> ThreadLocal&lt;Integer&gt; threadLocal = <span style=\"color:#0000ff;\">new</span> ThreadLocal&lt;Integer&gt;<span style=\"color:#000000;\">() { </span><span style=\"color:#008000;\"> //</span><span style=\"color:#008000;\"> 设置当前线程局部变量初始化值</span> <span style=\"color:#0000ff;\">protected</span><span style=\"color:#000000;\"> Integer initialValue() { </span><span style=\"color:#0000ff;\">return</span> 0<span style=\"color:#000000;\">;\n        };\n\n    }; </span><span style=\"color:#0000ff;\">public</span><span style=\"color:#000000;\"> Integer getNum() { </span><span style=\"color:#0000ff;\">int</span> count = <span style=\"color:#0000ff;\">this</span>.threadLocal.get() + 1<span style=\"color:#000000;\">; </span><span style=\"color:#0000ff;\">this</span><span style=\"color:#000000;\">.threadLocal.set(count); </span><span style=\"color:#0000ff;\">return</span><span style=\"color:#000000;\"> count;\n    }\n} </span><span style=\"color:#0000ff;\">public</span> <span style=\"color:#0000ff;\">class</span> ThreadLocaDemo2 <span style=\"color:#0000ff;\">extends</span><span style=\"color:#000000;\"> Thread { </span><span style=\"color:#0000ff;\">private</span><span style=\"color:#000000;\"> Res res; </span><span style=\"color:#0000ff;\">public</span><span style=\"color:#000000;\"> ThreadLocaDemo2(Res res) { </span><span style=\"color:#0000ff;\">this</span>.res =<span style=\"color:#000000;\"> res;\n    }\n\n    @Override </span><span style=\"color:#0000ff;\">public</span> <span style=\"color:#0000ff;\">void</span><span style=\"color:#000000;\"> run() { </span><span style=\"color:#0000ff;\">for</span> (<span style=\"color:#0000ff;\">int</span> i = 0; i &lt; 3; i++<span style=\"color:#000000;\">) {\n            System.out.println(Thread.currentThread().getName() </span>+ \"---\" + \"i---\" + i + \"--num:\" +<span style=\"color:#000000;\"> res.getNum());\n        }\n\n    } </span><span style=\"color:#0000ff;\">public</span> <span style=\"color:#0000ff;\">static</span> <span style=\"color:#0000ff;\">void</span><span style=\"color:#000000;\"> main(String[] args) {\n        Res res </span>= <span style=\"color:#0000ff;\">new</span><span style=\"color:#000000;\"> Res();\n        ThreadLocaDemo2 threadLocaDemo1 </span>= <span style=\"color:#0000ff;\">new</span><span style=\"color:#000000;\"> ThreadLocaDemo2(res);\n        ThreadLocaDemo2 threadLocaDemo2 </span>= <span style=\"color:#0000ff;\">new</span><span style=\"color:#000000;\"> ThreadLocaDemo2(res);\n        ThreadLocaDemo2 threadLocaDemo3 </span>= <span style=\"color:#0000ff;\">new</span><span style=\"color:#000000;\"> ThreadLocaDemo2(res);\n        threadLocaDemo1.start();\n        threadLocaDemo2.start();\n        threadLocaDemo3.start();\n    }\n\n}</span></pre>\n</div>\n<h2 style=\"text-indent:2em;\">\n	4.2、ThreadLocal实现原理\n</h2>\n<p style=\"text-indent:2em;\">\n	源码：\n</p>\n<div class=\"cnblogs_code\">\n<pre>    <span style=\"color:#0000ff;\">public</span> <span style=\"color:#0000ff;\">void</span><span style=\"color:#000000;\"> set(T value) {\n        Thread t </span>=<span style=\"color:#000000;\"> Thread.currentThread();\n        ThreadLocalMap map </span>=<span style=\"color:#000000;\"> getMap(t); </span><span style=\"color:#0000ff;\">if</span> (map != <span style=\"color:#0000ff;\">null</span><span style=\"color:#000000;\">)\n            map.set(</span><span style=\"color:#0000ff;\">this</span><span style=\"color:#000000;\">, value); </span><span style=\"color:#0000ff;\">else</span><span style=\"color:#000000;\"> createMap(t, value);\n    }</span></pre>\n</div>\n<p style=\"text-indent:2em;\">\n	从源码中我们可以看出，ThreadLoca通过map集合，Map.put(“当前线程”,值)；\n</p>\n<h1 style=\"text-indent:2em;\">\n	<strong>五、线程池</strong> \n</h1>\n<h2 style=\"text-indent:2em;\">\n	<strong>5.1&nbsp;<span style=\"font-family:宋体;\">什么是线程池？</span></strong> \n</h2>\n<p style=\"text-indent:2em;\">\n	<span>&nbsp;线程池是指在初始化一个多线程应用程序过程中创建一个线程集合，然后在需要执行新的任务时重用这些线程而不是新建一个线程。线程池中线程的数量通常完全取决于可用内存数量和应用程序的需求。然而，增加可用线程数量是可能的。线程池中的每个线程都有被分配一个任务，一旦任务已经完成了，线程回到池子中并等待下一次分配任务。</span> \n</p>\n<h2 style=\"text-indent:2em;\">\n	<strong>5.2&nbsp;<span style=\"font-family:宋体;\">线程池作用</span></strong> \n</h2>\n<p style=\"text-indent:2em;\">\n	基于以下几个原因在多线程应用程序中使用线程是必须的：\n</p>\n<p style=\"text-indent:2em;\">\n	1. 线程池改进了一个应用程序的响应时间。由于线程池中的线程已经准备好且等待被分配任务，应用程序可以直接拿来使用而不用新建一个线程。\n</p>\n<p style=\"text-indent:2em;\">\n	2. 线程池节省了CLR 为每个短生存周期任务创建一个完整的线程的开销并可以在任务完成后回收资源。\n</p>\n<p style=\"text-indent:2em;\">\n	3. 线程池根据当前在系统中运行的进程来优化线程时间片。\n</p>\n<p style=\"text-indent:2em;\">\n	4. 线程池允许我们开启多个任务而不用为每个线程设置属性。\n</p>\n<p style=\"text-indent:2em;\">\n	5. 线程池允许我们为正在执行的任务的程序参数传递一个包含状态信息的对象引用。\n</p>\n<p style=\"text-indent:2em;\">\n	6. 线程池可以用来解决处理一个特定请求最大线程数量限制问题。\n</p>\n<h2 style=\"text-indent:2em;\">\n	<strong>5.3 <span style=\"font-family:宋体;\">线程池四种创建方式</span></strong> \n</h2>\n<p style=\"text-indent:2em;\">\n	Java通过Executors（jdk1.5并发包）提供四种线程池，分别为：<br />\nnewCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。<br />\nnewFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。<br />\nnewScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。<br />\nnewSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。\n</p>\n<h2 style=\"text-indent:2em;\">\n	5.4 <span style=\"font-family:宋体;\">代码Demo</span><span style=\"font-family:宋体;\"><br />\n</span> \n</h2>\n<p style=\"text-indent:2em;\">\n	newCachedThreadPool\n</p>\n<p style=\"text-indent:2em;\">\n	创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。示例代码如下：\n</p>\n<div class=\"cnblogs_code\">\n<pre>ExecutorService cachedThreadPool =<span style=\"color:#000000;\"> Executors.newCachedThreadPool(); </span><span style=\"color:#0000ff;\">for</span> (<span style=\"color:#0000ff;\">int</span> i = 0; i &lt; 10; i++<span style=\"color:#000000;\">) { </span><span style=\"color:#0000ff;\">final</span> <span style=\"color:#0000ff;\">int</span> index =<span style=\"color:#000000;\"> i; </span><span style=\"color:#008000;\">//</span><span style=\"color:#008000;\"> try { </span><span style=\"color:#008000;\">//</span><span style=\"color:#008000;\"> Thread.sleep(index * 1000); </span><span style=\"color:#008000;\">//</span><span style=\"color:#008000;\"> } catch (InterruptedException e) { </span><span style=\"color:#008000;\">//</span><span style=\"color:#008000;\"> e.printStackTrace(); </span><span style=\"color:#008000;\">//</span><span style=\"color:#008000;\"> }</span> cachedThreadPool.execute(<span style=\"color:#0000ff;\">new</span><span style=\"color:#000000;\"> Runnable() { </span><span style=\"color:#0000ff;\">public</span> <span style=\"color:#0000ff;\">void</span><span style=\"color:#000000;\"> run() {\n                    System.out.println(Thread.currentThread().getName() </span>+ \"---\" +<span style=\"color:#000000;\"> index);\n                }\n            });\n        }</span></pre>\n</div>\n<p style=\"text-indent:2em;\">\n	总结: 线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。\n</p>\n<p style=\"text-indent:2em;\">\n	newFixedThreadPool\n</p>\n<p style=\"text-indent:2em;\">\n	创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。示例代码如下：\n</p>\n<div class=\"cnblogs_code\">\n<pre><span style=\"color:#008000;\">//</span><span style=\"color:#008000;\"> 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待</span> <span style=\"color:#0000ff;\">final</span> ExecutorService newCachedThreadPool = Executors.newFixedThreadPool(3<span style=\"color:#000000;\">); </span><span style=\"color:#0000ff;\">for</span> (<span style=\"color:#0000ff;\">int</span> i = 0; i &lt; 10; i++<span style=\"color:#000000;\">) { </span><span style=\"color:#0000ff;\">final</span> <span style=\"color:#0000ff;\">int</span> index =<span style=\"color:#000000;\"> i;\n            newCachedThreadPool.execute(</span><span style=\"color:#0000ff;\">new</span><span style=\"color:#000000;\"> Runnable() { </span><span style=\"color:#0000ff;\">public</span> <span style=\"color:#0000ff;\">void</span><span style=\"color:#000000;\"> run() { </span><span style=\"color:#0000ff;\">try</span><span style=\"color:#000000;\"> {\n                        Thread.sleep(</span>1000<span style=\"color:#000000;\">);\n                    } </span><span style=\"color:#0000ff;\">catch</span><span style=\"color:#000000;\"> (Exception e) { </span><span style=\"color:#008000;\">//</span><span style=\"color:#008000;\"> TODO: handle exception</span> <span style=\"color:#000000;\"> }\n                    System.out.println(</span>\"i:\" +<span style=\"color:#000000;\"> index);\n                }\n            });\n        }</span></pre>\n</div>\n<p style=\"text-indent:2em;\">\n	总结:因为线程池大小为3，每个任务输出index后sleep 2秒，所以每两秒打印3个数字。<br />\n定长线程池的大小最好根据系统资源进行设置。如Runtime.getRuntime().availableProcessors()\n</p>\n<p style=\"text-indent:2em;\">\n	newScheduledThreadPool\n</p>\n<p style=\"text-indent:2em;\">\n	创建一个定长线程池，支持定时及周期性任务执行。延迟执行示例代码如下：\n</p>\n<div class=\"cnblogs_code\">\n<pre><span style=\"color:#008000;\">//</span><span style=\"color:#008000;\"> 创建一个定长线程池，支持定时及周期性任务执行。延迟执行示例代码如下：</span> ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(5<span style=\"color:#000000;\">);\n        newScheduledThreadPool.schedule(</span><span style=\"color:#0000ff;\">new</span><span style=\"color:#000000;\"> Runnable() { </span><span style=\"color:#0000ff;\">public</span> <span style=\"color:#0000ff;\">void</span><span style=\"color:#000000;\"> run() {\n                System.out.println(</span>\"delay 3 seconds\"<span style=\"color:#000000;\">);\n            }\n        }, </span>3, TimeUnit.SECONDS);</pre>\n</div>\n<p style=\"text-indent:2em;\">\n	表示延迟3秒执行。\n</p>\n<p style=\"text-indent:2em;\">\n	newSingleThreadExecutor<br />\n创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。示例代码如下：\n</p>\n<div class=\"cnblogs_code\">\n<pre>    ExecutorService newSingleThreadExecutor =<span style=\"color:#000000;\"> Executors.newSingleThreadExecutor(); </span><span style=\"color:#0000ff;\">for</span> (<span style=\"color:#0000ff;\">int</span> i = 0; i &lt; 10; i++<span style=\"color:#000000;\">) { </span><span style=\"color:#0000ff;\">final</span> <span style=\"color:#0000ff;\">int</span> index =<span style=\"color:#000000;\"> i;\n            newSingleThreadExecutor.execute(</span><span style=\"color:#0000ff;\">new</span><span style=\"color:#000000;\"> Runnable() {\n\n                @Override </span><span style=\"color:#0000ff;\">public</span> <span style=\"color:#0000ff;\">void</span><span style=\"color:#000000;\"> run() {\n                    System.out.println(</span>\"index:\" +<span style=\"color:#000000;\"> index); </span><span style=\"color:#0000ff;\">try</span><span style=\"color:#000000;\"> {\n                        Thread.sleep(</span>200<span style=\"color:#000000;\">);\n                    } </span><span style=\"color:#0000ff;\">catch</span><span style=\"color:#000000;\"> (Exception e) { </span><span style=\"color:#008000;\">//</span><span style=\"color:#008000;\"> TODO: handle exception</span> <span style=\"color:#000000;\"> }\n                }\n            });\n        }</span></pre>\n</div>\n<p style=\"text-indent:2em;\">\n	注意: 结果依次输出，相当于顺序执行各个任务。\n</p>\n<p style=\"text-indent:2em;\">\n	最后推荐篇：<a id=\"cb_post_title_url\" class=\"postTitle2\" href=\"http://www.cnblogs.com/laoyeye/p/6589150.html\">JAVA线程池应用的DEMO</a> \n</p>', '本文原由小卖铺的老爷爷发表于博客园一、线程三大特性多线程有三大特性，原子性、可见性、有序性1.1什么是原子性即一个操作或者多个操作要么全部执行并且执行的过程不会被任何因素打断，要么就都不执行。一个很经典的例子就是银行账户转账问题：&nbsp;比如从账户A向账户B转1000元，那么必然包括2个操作：从账户A减去1000元，往账户B加上1000元。这2个操作必须要具备原子性才能保证不出现一些意外的问题。我们操作数据也是如此，比如i=i+1；其中就包括，读取i的值，计算i，写入i。这行代码在Java中是不具备原子性的，则多线程运行肯定会出问题，所以也需要我们使用同步和lock这些东西来确保这个特性了。&nbsp;原子性其实就是保证数据一致、线程安全一部分，1.2什么是可见性当多个线程访问同一个变量时，一个线程修改了这个变量的值，其他线程能够立即看得到修改的值。若两个线程在不同的cpu，那么线程1改变了i的值还没刷新到主存，线程2又使用了i，那么这个i值肯定还是之前的，线程1对变量的修改线程没看到这就是可见性问题。&nbsp;1.3什么是有序性程序执行的顺序按照代码的先后顺序执行。一般来说处理器为了提高程序运行效率，可能会对输入代码进行优化，它不保证程序中各个语句的执行先后顺序同代码中的顺序一致，但是它会保证程序最终执行结果和代码顺序执行的结果是一致的。如下：inta=10;//语句1intr=2;//语句2a=a+3;//语句3r=a*a;//语句4则因为重排序，他还可能执行顺序为2-1-3-4，1-3-2-4但绝不可能2-1-4-3，因为这打破了依赖关系。显然重排序对单线程运行是不会有任何问题，而多线程就不一定了，所以我们在多线程编程时就得考虑这个问题了。二：Java内存模型共享内存模型指的就是Java内存模型(简称JMM)，JMM决定一个线程对共享变量的写入时,能对另一个线程可见。从抽象的角度来看，JMM定义了线程和主内存之间的抽象关系：线程之间的共享变量存储在主内存（mainmemory）中，每个线程都有一个私有的本地内存（localmemory），本地内存中存储了该线程以读/写共享变量的副本。本地内存是JMM的一个抽象概念，并不真实存在。它涵盖了缓存，写缓冲区，寄存器以及其他的硬件和编译器优化。从上图来看，线程A与线程B之间如要通信的话，必须要经历下面2个步骤：1.首先，线程A把本地内存A中更新过的共享变量刷新到主内存中去。2.然后，线程B到主内存中去读取线程A之前已更新过的共享变量。下面通过示意图来说明这两个步骤：&nbsp;如上图所示，本地内存A和B有主内存中共享变量x的副本。假设初始时，这三个内存中的x值都为0。线程A在执行时，把更新后的x值（假设值为1）临时存放在自己的本地内存A中。当线程A和线程B需要通信时，线程A首先会把自己本地内存中修改后的x值刷新到主内存中，此时主内存中的x值变为了1。随后，线程B到主内存中去读取线程A更新后的x值，此时线程B的本地内存的x值也变为了1。从整体来看，这两个步骤实质上是线程A在向线程B发送消息，而且这个通信过程必须要经过主内存。JMM通过控制主内存与每个线程的本地内存之间的交互，来为java程序员提供内存可见性保证。总结：什么是Java内存模型：java内存模型简称jmm，定义了一个线程对另一个线程可见。共享变量存放在主内存中，每个线程都有自己的本地内存，当多个线程同时访问一个数据的时候，可能本地内存没有及时刷新到主内存，所以就会发生线程安全问题。三、Volatile关键字3.1什么是VolatileVolatile关键字的作用是变量在多个线程之间可见。classThreadVolatileDemoextendsThread{publicbooleanflag=true;@Overridepublicvoidrun(){System.out.println(\"开始执行子线程....\");while(flag){}System.out.println(\"线程停止\");}publicvoidsetRuning(booleanflag){this.flag=flag;}}publicclassThreadVolatile{publicstaticvoidmain(String[]args)throwsInterruptedException{ThreadVolatileDemothreadVolatileDemo=newThreadVolatileDemo();threadVolatileDemo.start();Thread.sleep(3000);threadVolatileDemo.setRuning(false);System.out.println(\"flag已经设置成false\");Thread.sleep(1000);System.out.println(threadVolatileDemo.flag);}}运行结果:threadVolatileDemo.flag值也是false,可是为什么程序还是一直在运行呢？原因:线程之间是不可见的，读取的是副本，没有及时读取到主内存结果。解决办法：使用Volatile关键字将解决线程之间可见性,强制线程每次读取该值的时候都去“主内存”中取值。3.2Volatile非原子性publicclassVolatileNoAtomicextendsThread{privatestaticvolatileintcount;//privatestaticAtomicIntegercount=newAtomicInteger(0);privatestaticvoidaddCount(){for(inti=0;i&lt;1000;i++){count++;//count.incrementAndGet();}System.out.println(count);}publicvoidrun(){addCount();}publicstaticvoidmain(String[]args){VolatileNoAtomic[]arr=newVolatileNoAtomic[100];//初始化10个线程for(inti=0;i&lt;10;i++){arr[i]=newVolatileNoAtomic();}for(inti=0;i&lt;10;i++){arr[i].start();}}}运行结果:结果发现数据不同步，因为Volatile不用具备原子性。所以Volatile只能解决将将结果刷新到主内存中去，并不能解决并发原子性问题。3.3使用AtomicInteger原子类AtomicInteger是一个提供原子操作的Integer类，通过线程安全的方式操作加减，因此十分适合高并发情况下的使用，来源于java并发包。修改上面的代码：publicclassVolatileNoAtomicextendsThread{privatestaticAtomicIntegeratomicInteger=newAtomicInteger(0);@Overridepublicvoidrun(){for(inti=0;i&lt;1000;i++){//等同于count++atomicInteger.incrementAndGet();}System.out.println(atomicInteger);}publicstaticvoidmain(String[]args){//初始化10个线程VolatileNoAtomic[]volatileNoAtomic=newVolatileNoAtomic[10];for(inti=0;i&lt;10;i++){//创建volatileNoAtomic[i]=newVolatileNoAtomic();}for(inti=0;i&lt;volatileNoAtomic.length;i++){volatileNoAtomic[i].start();}}}运行结果：3.4volatile与synchronized区别仅靠volatile不能保证线程的安全性。（原子性）①volatile轻量级，只能修饰变量。synchronized重量级，还可修饰方法②volatile只能保证数据的可见性，不能用来同步（没有原子性，不能保证线程安全），因为多个线程并发访问volatile修饰的变量不会阻塞。③synchronized不仅保证可见性，而且还保证原子性，因为，只有获得了锁的线程才能进入临界区，从而保证临界区中的所有语句都全部执行。多个线程争抢synchronized锁对象时，会出现阻塞。总结：线程安全性包括两个方面，①可见性。②原子性。从上面自增的例子中可以看出：仅仅使用volatile并不能保证线程安全性。而synchronized则可实现线程的安全性。四、ThreadLocal4.1、什么是ThreadLocalThreadLocal提高一个线程的局部变量，访问某个线程拥有自己局部变量。当使用ThreadLocal维护变量时，ThreadLocal为每个使用该变量的线程提供独立的变量副本，所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。ThreadLocal的接口方法ThreadLocal类接口很简单，只有4个方法，我们先来了解一下：voidset(Objectvalue)设置当前线程的线程局部变量的值。publicObjectget()该方法返回当前线程所对应的线程局部变量。publicvoidremove()将当前线程局部变量的值删除，目的是为了减少内存的占用，该方法是JDK5.0新增的方法。需要指出的是，当线程结束后，对应该线程的局部变量将自动被垃圾回收，所以显式调用该方法清除线程的局部变量并不是必须的操作，但它可以加快内存回收的速度。protectedObjectinitialValue()返回该线程局部变量的初始值，该方法是一个protected的方法，显然是为了让子类覆盖而设计的。这个方法是一个延迟调用方法，在线程第1次调用get()或set(Object)时才执行，并且仅执行1次。ThreadLocal中的缺省实现直接返回一个null。案例:创建三个线程，每个线程生成自己独立序列号。代码:classRes{//生成序列号共享变量//publicstaticIntegercount=0;//设置本地局部变量，与其他线程互不影响publicstaticThreadLocal&lt;Integer&gt;threadLocal=newThreadLocal&lt;Integer&gt;(){//设置当前线程局部变量初始化值protectedIntegerinitialValue(){return0;};};publicIntegergetNum(){intcount=this.threadLocal.get()+1;this.threadLocal.set(count);returncount;}}publicclassThreadLocaDemo2extendsThread{privateResres;publicThreadLocaDemo2(Resres){this.res=res;}@Overridepublicvoidrun(){for(inti=0;i&lt;3;i++){System.out.println(Thread.currentThread().getName()+\"---\"+\"i---\"+i+\"--num:\"+res.getNum());}}publicstaticvoidmain(String[]args){Resres=newRes();ThreadLocaDemo2threadLocaDemo1=newThreadLocaDemo2(res);ThreadLocaDemo2threadLocaDemo2=newThreadLocaDemo2(res);ThreadLocaDemo2threadLocaDemo3=newThreadLocaDemo2(res);threadLocaDemo1.start();threadLocaDemo2.start();threadLocaDemo3.start();}}4.2、ThreadLocal实现原理源码：publicvoidset(Tvalue){Threadt=Thread.currentThread();ThreadLocalMapmap=getMap(t);if(map!=null)map.set(this,value);elsecreateMap(t,value);}从源码中我们可以看出，ThreadLoca通过map集合，Map.put(“当前线程”,值)；五、线程池5.1&nbsp;什么是线程池？&nbsp;线程池是指在初始化一个多线程应用程序过程中创建一个线程集合，然后在需要执行新的任务时重用这些线程而不是新建一个线程。线程池中线程的数量通常完全取决于可用内存数量和应用程序的需求。然而，增加可用线程数量是可能的。线程池中的每个线程都有被分配一个任务，一旦任务已经完成了，线程回到池子中并等待下一次分配任务。5.2&nbsp;线程池作用基于以下几个原因在多线程应用程序中使用线程是必须的：1.线程池改进了一个应用程序的响应时间。由于线程池中的线程已经准备好且等待被分配任务，应用程序可以直接拿来使用而不用新建一个线程。2.线程池节省了CLR为每个短生存周期任务创建一个完整的线程的开销并可以在任务完成后回收资源。3.线程池根据当前在系统中运行的进程来优化线程时间片。4.线程池允许我们开启多个任务而不用为每个线程设置属性。5.线程池允许我们为正在执行的任务的程序参数传递一个包含状态信息的对象引用。6.线程池可以用来解决处理一个特定请求最大线程数量限制问题。5.3线程池四种创建方式Java通过Executors（jdk1.5并发包）提供四种线程池，分别为：newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。newFixedThreadPool创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。newScheduledThreadPool创建一个定长线程池，支持定时及周期性任务执行。newSingleThreadExecutor创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO,LIFO,优先级)执行。5.4代码DemonewCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。示例代码如下：ExecutorServicecachedThreadPool=Executors.newCachedThreadPool();for(inti=0;i&lt;10;i++){finalintindex=i;//try{//Thread.sleep(index*1000);//}catch(InterruptedExceptione){//e.printStackTrace();//}cachedThreadPool.execute(newRunnable(){publicvoidrun(){System.out.println(Thread.currentThread().getName()+\"---\"+index);}});}总结:线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。newFixedThreadPool创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。示例代码如下：//创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待finalExecutorServicenewCachedThreadPool=Executors.newFixedThreadPool(3);for(inti=0;i&lt;10;i++){finalintindex=i;newCachedThreadPool.execute(newRunnable(){publicvoidrun(){try{Thread.sleep(1000);}catch(Exceptione){//TODO:handleexception}System.out.println(\"i:\"+index);}});}总结:因为线程池大小为3，每个任务输出index后sleep2秒，所以每两秒打印3个数字。定长线程池的大小最好根据系统资源进行设置。如Runtime.getRuntime().availableProcessors()newScheduledThreadPool创建一个定长线程池，支持定时及周期性任务执行。延迟执行示例代码如下：//创建一个定长线程池，支持定时及周期性任务执行。延迟执行示例代码如下：ScheduledExecutorServicenewScheduledThreadPool=Executors.newScheduledThreadPool(5);newScheduledThreadPool.schedule(newRunnable(){publicvoidrun(){System.out.println(\"delay3seconds\");}},3,TimeUnit.SECONDS);表示延迟3秒执行。newSingleThreadExecutor创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO,LIFO,优先级)执行。示例代码如下：ExecutorServicenewSingleThreadExecutor=Executors.newSingleThreadExecutor();for(inti=0;i&lt;10;i++){finalintindex=i;newSingleThreadExecutor.execute(newRunnable(){@Overridepublicvoidrun(){System.out.println(\"index:\"+index);try{Thread.sleep(200);}catch(Exceptione){//TODO:handleexception}}});}注意:结果依次输出，相当于顺序执行各个任务。最后推荐篇：JAVA线程池应用的DEMO', '85', '1', '1', '1', '0', '0', '152336334290854', '2018-05-29 20:30:41', '2018-07-19 23:19:37');
INSERT INTO `t_article` VALUES ('152776394790103', '将jar包安装到maven仓库', '152776367270658', 'http://images.laoyeye.net/1527763730193334.jpg', '&lt;!--https://mvnrepository.com/artifact/ojdbc/ojdbc--&gt;&lt;!--(参数一)：下载到本地的ojdbc-10.2.0.4.0.jar包的真实存放路径--&gt;&lt;dependency&gt;&lt;groupId&gt;ojdbc&lt;/groupId&gt;-----------------(参数二)&lt;artifactId&gt;ojdbc&lt;/artifactId&gt;-----------(参', '<pre class=\"layui-code prettyprint\">&lt;!-- https://mvnrepository.com/artifact/ojdbc/ojdbc --&gt;\n&lt;!-- (参数一)：下载到本地的ojdbc-10.2.0.4.0.jar包的真实存放路径 --&gt;\n&lt;dependency&gt;\n&lt;groupId&gt;ojdbc&lt;/groupId&gt;-----------------(参数二)\n&lt;artifactId&gt;ojdbc&lt;/artifactId&gt;-----------(参数三)\n&lt;version&gt;10.2.0.4.0&lt;/version&gt;------------(参数四)\n&lt;/dependency&gt;</pre>\n语法：<br />\n<p>\n	<strong><span style=\"color:#E53333;\">mvn install:install-file -Dfile=jar包的位置(参数一) -DgroupId=groupId(参数二) -DartifactId=artifactId(参数三) -Dversion=version(参数四) -Dpackaging=jar</span></strong>\n</p>\n我把“ojdbc-10.2.0.4.0.jar”放到了“D:\\Program Files\\mvn\\”下<br />\n<p>\n	注意：“Program Files”中间有空格，所以要加双引号，另外三个参数，从上面复制过来即可，下面是我安装ojdbc-10.2.0.4.0.jar包使用的命令：\n</p>\n<strong><span style=\"color:#E53333;\">mvn install:install-file -Dfile=\"D:\\Program Files\\mvn\\ojdbc-10.2.0.4.0.jar\" -DgroupId=com.oracle -DartifactId=ojdbc14 -Dversion=10.2.0.4.0 -Dpackaging=jar</span></strong>\n<p>\n	需要注意以下几点：\n</p>\n<ol>\n	<li>\n		注意\"-\"不能缺少 install后面的\"-\"是没有空格的\n	</li>\n	<li>\n		注意\"-Dfile\"中jar包的路径和jar包的名字.\n	</li>\n	<li>\n		注意看cmd命令提示,查看本地repository中是否成功的复制了jar包.\n	</li>\n</ol>\n重点：Jar包默认都安装在“C:\\Users\\Administrator\\.m2\\repository\\”下，其实上面的(参数二，参数三，参数四)就是指定安装具体的安装路径。<br />\n（以后也可以根据自己需求进行更改参数二，三，四，其实就是更改安装路径）。<br />', '&lt;!--https://mvnrepository.com/artifact/ojdbc/ojdbc--&gt;&lt;!--(参数一)：下载到本地的ojdbc-10.2.0.4.0.jar包的真实存放路径--&gt;&lt;dependency&gt;&lt;groupId&gt;ojdbc&lt;/groupId&gt;-----------------(参数二)&lt;artifactId&gt;ojdbc&lt;/artifactId&gt;-----------(参数三)&lt;version&gt;10.2.0.4.0&lt;/version&gt;------------(参数四)&lt;/dependency&gt;语法：mvninstall:install-file-Dfile=jar包的位置(参数一)-DgroupId=groupId(参数二)-DartifactId=artifactId(参数三)-Dversion=version(参数四)-Dpackaging=jar我把“ojdbc-10.2.0.4.0.jar”放到了“D:\\ProgramFiles\\mvn\\”下注意：“ProgramFiles”中间有空格，所以要加双引号，另外三个参数，从上面复制过来即可，下面是我安装ojdbc-10.2.0.4.0.jar包使用的命令：mvninstall:install-file-Dfile=\"D:\\ProgramFiles\\mvn\\ojdbc-10.2.0.4.0.jar\"-DgroupId=com.oracle-DartifactId=ojdbc14-Dversion=10.2.0.4.0-Dpackaging=jar需要注意以下几点：注意\"-\"不能缺少install后面的\"-\"是没有空格的注意\"-Dfile\"中jar包的路径和jar包的名字.注意看cmd命令提示,查看本地repository中是否成功的复制了jar包.重点：Jar包默认都安装在“C:\\Users\\Administrator\\.m2\\repository\\”下，其实上面的(参数二，参数三，参数四)就是指定安装具体的安装路径。（以后也可以根据自己需求进行更改参数二，三，四，其实就是更改安装路径）。', '28', '0', '1', '1', '0', '0', '152336334290854', '2018-05-31 18:52:28', '2018-07-21 14:59:49');
INSERT INTO `t_article` VALUES ('152933278046718', 'yyblog一个面向学习的java开源博客系统', '152933163827668', 'http://images.laoyeye.net/1529332591056229.jpg', '一个面向学习的java开源博客系统目前博客后台管理系统已经完成，项目集成QQ登录，腾讯云对象存储等功能2018年6月18号，beat1.0版正式发布，预览地址在&nbsp;www.laoyeye.net&nbsp;。代码在：https://github.com/allanzhuo/yyblog本来这个版本是不准备上传git的，但是因为后面可能架构方面会做较大的调整，想着还是在git上做个记录吧。如果有需要的同学还请方便给我点个star目前前端的页面来自笔记博客，后面会做完全的重构，下个版本', '<p>\n	一个面向学习的java开源博客系统，减少过度封装，展现技术本质。\n</p>\n<p>\n	项目集成QQ登录，腾讯云对象存储，微信小程序，定时跑批配置，权限管理等功能\n</p>\n<p>\n	<br />\n</p>\n<h1>\n	初衷\n</h1>\n<p>\n	<br />\n</p>\n<h2>\n	<a href=\"file://C:/Users/Administrator/AppData/Local/Youdao/YNote/markdown/index.html#初衷\"></a> \n</h2>\n<p>\n	新学的技术，新钻研的前沿趋势却没有实践的机会。不一样的架构，不一样的思想始终无法应用于现有的项目上。相信很多同学都有遇到过这种情况吧。很多时候看过的东西只是简单的了解，又或是仅仅做了个Demo，这时候我们所了解的可能远远还达不到生产的级别，不经过实践永远不知道会遇到哪些问题。这是我目前遇到的问题，也是我做这个项目的初衷。并不是为了做一个产品，而是做一个实践，能让我所学能有所用。\n</p>\n<p>\n	目前系统分为beta版和1.x版两个分支。beta版为早期开发版本，今后不会再做更新，但是因为使用的技术相对基础，学习意义较大，所以单独开了分支。 1.x版为常规开发版本，今后相关更新也会基于此版本。最后在此也特别感谢<a href=\"https://github.com/miyakowork/NoteBlog/\">笔记博客</a>提供的开源前端模板。目前1.x版本前端不会改动，2.x的会全部重构。\n</p>\n<p>\n	项目开发结合实际情况，尽量遵守阿里巴巴开发规范，如有发现相关不合规问题，还请方便提醒下我修改，谢谢。\n</p>\n<p>\n	<strong><span style=\"color:#E53333;\">如果本项目对您有帮助的话，请 Star本项目</span></strong> \n</p>\n<h3>\n	<a href=\"file://C:/Users/Administrator/AppData/Local/Youdao/YNote/markdown/index.html#如果本项目对您有帮助的话请-star本项目\"></a> \n</h3>\n<p>\n	<strong><span style=\"color:#E53333;\">如需关注项目最新动态，也请方便 Star项目，这也是对项目最好的支持</span></strong> \n</p>\n<h3>\n	<a href=\"file://C:/Users/Administrator/AppData/Local/Youdao/YNote/markdown/index.html#如需关注项目最新动态也请方便-star项目这也是对项目最好的支持\"></a> \n</h3>\n<p>\n	<strong>2018年6月18日，beat预览版发布。</strong> \n</p>\n<p>\n	<strong>2018年7月21日，1.0开发版发布。</strong> \n</p>\n<p>\n	<strong>预览地址：&nbsp;</strong><a href=\"http://www.laoyeye.net/\"><strong>www.laoyeye.net</strong></a><strong>&nbsp; 。代码：</strong><a href=\"https://github.com/allanzhuo/yyblog\" target=\"_blank\"><strong>https://github.com/allanzhuo/yyblog</strong></a> \n</p>\n<p>\n	<br />\n</p>\n<h2>\n	<a href=\"file://C:/Users/Administrator/AppData/Local/Youdao/YNote/markdown/index.html#beta版\"></a>beta版\n</h2>\n<p>\n	<br />\n</p>\n<p>\n	beta版为早期开发版本，但是基本功能大部分是已经完成的。\n</p>\n<p>\n	<br />\n</p>\n<h4>\n	<a href=\"file://C:/Users/Administrator/AppData/Local/Youdao/YNote/markdown/index.html#功能小结\"></a>功能小结\n</h4>\n<ul>\n	<li>\n		项目基于Springboot 1.5 + mybatis + maven + layui 2.2\n	</li>\n	<li>\n		数据库密码采用MD5双重加密，cookie采用AES对称加密算法\n	</li>\n	<li>\n		登陆拦截采用springmvc的拦截器模式\n	</li>\n	<li>\n		集成QQ登录，腾讯云对象存储等功能\n	</li>\n</ul>\n<h4>\n	<a href=\"file://C:/Users/Administrator/AppData/Local/Youdao/YNote/markdown/index.html#项目运行\"></a>项目运行\n</h4>\n<p>\n	下载代码导入Eclipse或者IDEA后：\n</p>\n<p>\n	1、修改application.yml的数据库连接，数据库相关脚本在beta1分支中有上传\n</p>\n<p>\n	2、修改qqconnectconfig.properties的QQ互联的相关连接配置，主要是app_ID，app_KEY，和回调地址redirect_URI\n</p>\n<p>\n	3、修改腾讯云存储的配置，目前在COSClientUtils.java文件中配置即可\n</p>\n<p>\n	<br />\n</p>\n<h2>\n	<a href=\"file://C:/Users/Administrator/AppData/Local/Youdao/YNote/markdown/index.html#1x-版本\"></a>1.x 版本\n</h2>\n<p>\n	<br />\n</p>\n<p>\n	1.x 为常规开发版本，目前1.x版本仅作为单体项目开发，重点在于功能的完善，不做任何不必要的拆分。今后会在2.x版本,进行微服务化的演进。\n</p>\n<p>\n	<br />\n</p>\n<h3>\n	<a href=\"file://C:/Users/Administrator/AppData/Local/Youdao/YNote/markdown/index.html#项目技术\"></a>项目技术\n</h3>\n<ul>\n	<li>\n		核心框架：Spring Boot 1.x\n	</li>\n	<li>\n		安全框架：Apache Shiro 1.3\n	</li>\n	<li>\n		视图框架：Spring MVC\n	</li>\n	<li>\n		持久层框架：MyBatis\n	</li>\n	<li>\n		定时器：Quartz 2.2\n	</li>\n	<li>\n		数据库连接池：Druid 1.1\n	</li>\n	<li>\n		接口文档：Swagger2\n	</li>\n	<li>\n		前端框架：layui 2.3\n	</li>\n	<li>\n		模板引擎：thymeleaf\n	</li>\n	<li>\n		<br />\n	</li>\n</ul>\n<h3>\n	<a href=\"file://C:/Users/Administrator/AppData/Local/Youdao/YNote/markdown/index.html#软件需求\"></a>软件需求\n</h3>\n<ul>\n	<li>\n		JDK 1.8 +\n	</li>\n	<li>\n		MYSQL 5.6 +\n	</li>\n	<li>\n		MAVEN 3.0 +\n	</li>\n</ul>\n<h3>\n	<a href=\"file://C:/Users/Administrator/AppData/Local/Youdao/YNote/markdown/index.html#开发部署\"></a>开发部署\n</h3>\n<ol>\n	<li>\n		Star本项目。(#.#)\n	</li>\n	<li>\n		git下载源码\n	</li>\n	<li>\n		根据提供的脚本创建数据库，数据库编码为UTF-8\n	</li>\n	<li>\n		修改application.yml文件，更新数据库地址、账号和密码\n	</li>\n	<li>\n		登陆系统，初始账号：admin 密码为123456\n	</li>\n	<li>\n		后台设置自己的腾讯云COS配置\n	</li>\n</ol>\n<h3>\n	<a href=\"file://C:/Users/Administrator/AppData/Local/Youdao/YNote/markdown/index.html#近期待开发规划\"></a>近期待开发规划\n</h3>\n<ul>\n	<li>\n		新增权限管理页面，目前需数据库手动配置\n	</li>\n	<li>\n		新增登录验证码功能\n	</li>\n	<li>\n		新增图片上传自动添加水印功能\n	</li>\n	<li>\n		新增导入导出Excel报表的功能\n	</li>\n	<li>\n		新增评论留言邮件通知功能\n	</li>\n</ul>\n<h3>\n	<a href=\"file://C:/Users/Administrator/AppData/Local/Youdao/YNote/markdown/index.html#项目相关技术文档\"></a>项目相关技术文档\n</h3>\n<ul>\n	<li>\n		<a href=\"https://www.cnblogs.com/laoyeye/p/6914704.html\">JAVA实用案例之验证码开发</a> \n	</li>\n	<li>\n		<a href=\"https://www.cnblogs.com/laoyeye/p/7193309.html\">JAVA实用案例之图片水印开发</a> \n	</li>\n	<li>\n		<a href=\"https://www.cnblogs.com/laoyeye/p/6938889.html\">JAVA实用案例之文件导入导出（POI方式）</a> \n	</li>\n	<li>\n		<a href=\"https://www.cnblogs.com/laoyeye/p/7707149.html\">JAVA文件导出之jasperreport报表开发</a> \n	</li>\n	<li>\n		<a href=\"https://www.cnblogs.com/laoyeye/p/9047504.html\">利用Swagger2自动生成对外接口的文档</a> \n	</li>\n</ul>\n<h3>\n	<a href=\"file://C:/Users/Administrator/AppData/Local/Youdao/YNote/markdown/index.html#项目演示\"></a>项目演示\n</h3>\n<ul>\n	<li>\n		演示地址：1、前台：<a href=\"http://www.laoyeye.net/\">www.laoyeye.net</a>&nbsp;2、后台：<a href=\"http://www.laoyeye.net/management/index\">http://www.laoyeye.net/management/index</a> \n	</li>\n	<li>\n		账号/密码：test/123456 暂时只拥有部分菜单的查看权限\n	</li>\n</ul>\n<h3>\n	<a href=\"file://C:/Users/Administrator/AppData/Local/Youdao/YNote/markdown/index.html#项目展示\"></a>项目展示\n</h3>\n<p>\n	计划任务管理&nbsp;<img src=\"http://ouninjftw.bkt.clouddn.com/%E8%AE%A1%E5%88%92%E4%BB%BB%E5%8A%A1.png\" alt=\"image\" />系统日志管理&nbsp;<img src=\"http://ouninjftw.bkt.clouddn.com/%E7%B3%BB%E7%BB%9F%E6%97%A5%E5%BF%97.png\" alt=\"image\" />网站设置&nbsp;<img src=\"http://ouninjftw.bkt.clouddn.com/%E7%BD%91%E7%AB%99%E8%AE%BE%E7%BD%AE.png\" alt=\"image\" />打赏二维码设置&nbsp;<img src=\"http://ouninjftw.bkt.clouddn.com/%E6%89%93%E8%B5%8F.png\" alt=\"image\" /> \n</p>\n<p>\n	<br />\n</p>', '一个面向学习的java开源博客系统，减少过度封装，展现技术本质。项目集成QQ登录，腾讯云对象存储，微信小程序，定时跑批配置，权限管理等功能初衷新学的技术，新钻研的前沿趋势却没有实践的机会。不一样的架构，不一样的思想始终无法应用于现有的项目上。相信很多同学都有遇到过这种情况吧。很多时候看过的东西只是简单的了解，又或是仅仅做了个Demo，这时候我们所了解的可能远远还达不到生产的级别，不经过实践永远不知道会遇到哪些问题。这是我目前遇到的问题，也是我做这个项目的初衷。并不是为了做一个产品，而是做一个实践，能让我所学能有所用。目前系统分为beta版和1.x版两个分支。beta版为早期开发版本，今后不会再做更新，但是因为使用的技术相对基础，学习意义较大，所以单独开了分支。1.x版为常规开发版本，今后相关更新也会基于此版本。最后在此也特别感谢笔记博客提供的开源前端模板。目前1.x版本前端不会改动，2.x的会全部重构。项目开发结合实际情况，尽量遵守阿里巴巴开发规范，如有发现相关不合规问题，还请方便提醒下我修改，谢谢。如果本项目对您有帮助的话，请Star本项目如需关注项目最新动态，也请方便Star项目，这也是对项目最好的支持2018年6月18日，beat预览版发布。2018年7月21日，1.0开发版发布。预览地址：&nbsp;www.laoyeye.net&nbsp;。代码：https://github.com/allanzhuo/yyblogbeta版beta版为早期开发版本，但是基本功能大部分是已经完成的。功能小结项目基于Springboot1.5+mybatis+maven+layui2.2数据库密码采用MD5双重加密，cookie采用AES对称加密算法登陆拦截采用springmvc的拦截器模式集成QQ登录，腾讯云对象存储等功能项目运行下载代码导入Eclipse或者IDEA后：1、修改application.yml的数据库连接，数据库相关脚本在beta1分支中有上传2、修改qqconnectconfig.properties的QQ互联的相关连接配置，主要是app_ID，app_KEY，和回调地址redirect_URI3、修改腾讯云存储的配置，目前在COSClientUtils.java文件中配置即可1.x版本1.x为常规开发版本，目前1.x版本仅作为单体项目开发，重点在于功能的完善，不做任何不必要的拆分。今后会在2.x版本,进行微服务化的演进。项目技术核心框架：SpringBoot1.x安全框架：ApacheShiro1.3视图框架：SpringMVC持久层框架：MyBatis定时器：Quartz2.2数据库连接池：Druid1.1接口文档：Swagger2前端框架：layui2.3模板引擎：thymeleaf软件需求JDK1.8+MYSQL5.6+MAVEN3.0+开发部署Star本项目。(#.#)git下载源码根据提供的脚本创建数据库，数据库编码为UTF-8修改application.yml文件，更新数据库地址、账号和密码登陆系统，初始账号：admin密码为123456后台设置自己的腾讯云COS配置近期待开发规划新增权限管理页面，目前需数据库手动配置新增登录验证码功能新增图片上传自动添加水印功能新增导入导出Excel报表的功能新增评论留言邮件通知功能项目相关技术文档JAVA实用案例之验证码开发JAVA实用案例之图片水印开发JAVA实用案例之文件导入导出（POI方式）JAVA文件导出之jasperreport报表开发利用Swagger2自动生成对外接口的文档项目演示演示地址：1、前台：www.laoyeye.net&nbsp;2、后台：http://www.laoyeye.net/management/index账号/密码：test/123456暂时只拥有部分菜单的查看权限项目展示计划任务管理&nbsp;系统日志管理&nbsp;网站设置&nbsp;打赏二维码设置&nbsp;', '80', '2', '1', '1', '0', '1', '152336334290854', '2018-06-18 22:39:40', '2018-07-21 15:13:14');
INSERT INTO `t_article` VALUES ('153201605911289', 'd', '152776367270658', null, 'd', 'd', 'd', '4', '0', '0', '0', '1', '0', '2', '2018-07-20 00:00:59', '2018-07-21 13:21:48');
INSERT INTO `t_article` VALUES ('153215389119313', 't', '152776367270658', null, 't', 't', 't', '0', '0', '0', '0', '1', '0', '2', '2018-07-21 14:18:11', '2018-07-21 14:18:11');
INSERT INTO `t_article` VALUES ('153215625734689', 'mysql:Packet for query is too large (7077 > 1024).', '153215570959570', 'http://images.laoyeye.net/1532155768809333.jpg', '报错内容：###Errorupdatingdatabase.Cause:com.mysql.jdbc.PacketTooBigException:Packetforqueryistoolarge(7077&gt;1024).Youcanchangethisvalueontheserverbysettingthemax_allowed_packet\'variable.###Theerrormayinvolvenet.laoyeye.yyblog.mapper.ArticleMappe', '<p>\n	报错内容：\n</p>\n<pre class=\"layui-code prettyprint\">### Error updating database.  Cause: com.mysql.jdbc.PacketTooBigException: Packet for query is too large (7077 &gt; 1024). You can change this value on the server by setting the max_allowed_packet\' variable.\n### The error may involve net.laoyeye.yyblog.mapper.ArticleMapper.update-Inline\n### The error occurred while setting parameters</pre>\n<p>\n	原因：<strong><span style=\"color:#E53333;\">mysql限制了最大更新大小</span></strong>\n</p>\n<p>\n	解决方法：\n</p>\n<p style=\"font-family:Verdana, Arial, Helvetica, sans-serif;font-size:17px;background-color:#FFFFFF;\">\n	查看目前配置\n</p>\n<div class=\"wp_syntax\" style=\"margin:0px;padding:0px;font-family:Verdana, Arial, Helvetica, sans-serif;font-size:17px;background-color:#FFFFFF;\">\n	<div class=\"code\" style=\"margin:0px;padding:0px;\">\n		<p>\n			<br />\n		</p>\n<pre class=\"layui-code prettyprint\">show VARIABLES like \'%max_allowed_packet%\';</pre>\n		<p>\n			<br />\n		</p>\n	</div>\n</div>\n<p style=\"font-family:Verdana, Arial, Helvetica, sans-serif;font-size:17px;background-color:#FFFFFF;\">\n	显示的结果为：\n</p>\n<div class=\"wp_syntax\" style=\"margin:0px;padding:0px;font-family:Verdana, Arial, Helvetica, sans-serif;font-size:17px;background-color:#FFFFFF;\">\n	<div class=\"code\" style=\"margin:0px;padding:0px;\">\n		<p>\n			+--------------------+---------+\n		</p>\n		<p>\n			| Variable_name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; | Value&nbsp;&nbsp; |\n		</p>\n		<p>\n			+--------------------+---------+\n		</p>\n		<p>\n			| max_allowed_packet | 1024 |\n		</p>\n		<p>\n			+--------------------+---------+ &nbsp;\n		</p>\n	</div>\n</div>\n<p style=\"font-family:Verdana, Arial, Helvetica, sans-serif;font-size:17px;background-color:#FFFFFF;\">\n	以上说明目前的配置是：1024K\n</p>\n<p style=\"font-family:Verdana, Arial, Helvetica, sans-serif;font-size:17px;background-color:#FFFFFF;\">\n	<br />\n</p>\n<div id=\"blog_content\" class=\"blog_content\" style=\"margin:0px;padding:0px;font-family:Verdana, Arial, Helvetica, sans-serif;font-size:17px;background-color:#FFFFFF;\">\n	<p>\n		修改方法<br />\n1、修改配置文件<br />\n可以编辑my.cnf来修改（windows下my.ini）,在[mysqld]段或者mysql的server配置段进行修改。<br />\nmax_allowed_packet = 20M<br />\n如果找不到my.cnf可以通过<br />\nmysql --help | grep my.cnf<br />\n去寻找my.cnf文件。<br />\nlinux下该文件在/etc/下。<br />\n<br />\n2、在mysql命令行中修改<br />\n在mysql 命令行中运行\n	</p>\n<pre class=\"layui-code prettyprint\">set global max_allowed_packet = 2*1024*1024*10</pre>\n然后退出命令行，重启mysql服务，再进入。<br />\n<pre class=\"layui-code prettyprint\">show VARIABLES like \'%max_allowed_packet%\';</pre>\n查看下max_allowed_packet是否编辑成功<br />\n注意：该值设置过小将导致单个记录超过限制后写入数据库失败，且后续记录写入也将失败。<br />\n<br />\n三、如何重启Linux的mysql<br />\n1、使用 service 启动：service mysqld restart<br />\n2、使用 mysqld 脚本启动：/etc/inint.d/mysqld restart\n	<p>\n		<br />\n	</p>\n</div>\n<p>\n	<br />\n</p>\n<p>\n	<br />\n</p>', '报错内容：###Errorupdatingdatabase.Cause:com.mysql.jdbc.PacketTooBigException:Packetforqueryistoolarge(7077&gt;1024).Youcanchangethisvalueontheserverbysettingthemax_allowed_packet\'variable.###Theerrormayinvolvenet.laoyeye.yyblog.mapper.ArticleMapper.update-Inline###Theerroroccurredwhilesettingparameters原因：mysql限制了最大更新大小解决方法：查看目前配置showVARIABLESlike\'%max_allowed_packet%\';显示的结果为：+--------------------+---------+|Variable_name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|Value&nbsp;&nbsp;|+--------------------+---------+|max_allowed_packet|1024|+--------------------+---------+&nbsp;以上说明目前的配置是：1024K修改方法1、修改配置文件可以编辑my.cnf来修改（windows下my.ini）,在[mysqld]段或者mysql的server配置段进行修改。max_allowed_packet=20M如果找不到my.cnf可以通过mysql--help|grepmy.cnf去寻找my.cnf文件。linux下该文件在/etc/下。2、在mysql命令行中修改在mysql命令行中运行setglobalmax_allowed_packet=2*1024*1024*10然后退出命令行，重启mysql服务，再进入。showVARIABLESlike\'%max_allowed_packet%\';查看下max_allowed_packet是否编辑成功注意：该值设置过小将导致单个记录超过限制后写入数据库失败，且后续记录写入也将失败。三、如何重启Linux的mysql1、使用service启动：servicemysqldrestart2、使用mysqld脚本启动：/etc/inint.d/mysqldrestart', '8', '1', '1', '1', '0', '0', '152336334290854', '2018-07-21 14:57:37', '2018-07-21 14:59:46');

-- ----------------------------
-- Table structure for t_cate
-- ----------------------------
DROP TABLE IF EXISTS `t_cate`;
CREATE TABLE `t_cate` (
  `id` bigint(20) NOT NULL,
  `code` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_cate
-- ----------------------------
INSERT INTO `t_cate` VALUES ('152759695653794', 'dxc', '多线程');
INSERT INTO `t_cate` VALUES ('152776367270658', 'maven', 'maven');
INSERT INTO `t_cate` VALUES ('152933163827668', 'git', '开源');
INSERT INTO `t_cate` VALUES ('153215570959570', 'mysql', 'mysql');

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `article_id` bigint(20) NOT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `ip_addr` varchar(50) DEFAULT NULL,
  `ip_cn_addr` varchar(100) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  `enable` tinyint(1) NOT NULL DEFAULT '1',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=153241396670631 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
INSERT INTO `t_comment` VALUES ('152626195065750', '152518101502084', '152277118900681', '<p>1</p>', '127.0.0.1', '中国上海上海', null, '1', '2018-05-14 09:41:10');
INSERT INTO `t_comment` VALUES ('152626294338264', '152518101502084', '152277118900681', '<blockquote class=\"layui-elem-quote layui-quote-nm\">   <a href=\"javascript:;\" class=\"re\">回复@小卖铺的老爷爷</a>：undefined</blockquote><p>哈哈哈</p>', '127.0.0.1', '中国上海上海', null, '1', '2018-05-14 09:55:44');
INSERT INTO `t_comment` VALUES ('152626451544044', '152518101502084', '152277118900681', '<blockquote class=\"layui-elem-quote layui-quote-nm\">   <a href=\"javascript:;\" class=\"re\">回复@小卖铺的老爷爷</a>：<blockquote class=\"layui-elem-quote layui-quote-nm\">   <a href=\"javascript:;\" class=\"re\">回复@小卖铺的老爷爷</a>：undefined</blockquote><p>哈哈哈</p></blockquote><p>1111111111111111</p>', '127.0.0.1', '中国上海上海', null, '1', '2018-05-14 10:21:56');
INSERT INTO `t_comment` VALUES ('152629854337258', '152518101502084', '152277118900681', '<p>chongxin<br></p>', '101.81.12.7', '中国上海上海', null, '1', '2018-05-14 19:49:04');
INSERT INTO `t_comment` VALUES ('152776400603496', '152336334290854', '152759704083198', '<p>测试</p>', '116.231.32.33', '中国上海上海', null, '1', '2018-05-31 18:53:26');
INSERT INTO `t_comment` VALUES ('152776401560636', '152336334290854', '152759704083198', '<blockquote class=\"layui-elem-quote layui-quote-nm\">   <a href=\"javascript:;\" class=\"re\">回复@小卖铺的老爷爷</a>：<p>测试</p></blockquote><p>测试2</p>', '116.231.32.33', '中国上海上海', null, '1', '2018-05-31 18:53:36');
INSERT INTO `t_comment` VALUES ('153132252782160', '152336334290854', '152933278046718', '<p>测试</p>', '0:0:0:0:0:0:0:1', '中国上海上海', null, '1', '2018-07-11 23:22:23');
INSERT INTO `t_comment` VALUES ('153132276772851', '152336334290854', '152933278046718', '<p>正式版测试</p>', '0:0:0:0:0:0:0:1', '中国上海上海', null, '1', '2018-07-11 23:26:21');
INSERT INTO `t_comment` VALUES ('153158780992867', '153158776683481', '152933278046718', '<p>你好，有表的sql吗</p>', '124.79.241.231', '中国上海上海', null, '1', '2018-07-15 01:03:31');
INSERT INTO `t_comment` VALUES ('153241396670630', '153241389443029', '152933278046718', '<p>dddd</p>', '101.229.240.36', '中国上海上海', null, '1', '2018-07-24 14:32:47');

-- ----------------------------
-- Table structure for t_file
-- ----------------------------
DROP TABLE IF EXISTS `t_file`;
CREATE TABLE `t_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `url` varchar(500) DEFAULT NULL,
  `post` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_file
-- ----------------------------

-- ----------------------------
-- Table structure for t_keyword
-- ----------------------------
DROP TABLE IF EXISTS `t_keyword`;
CREATE TABLE `t_keyword` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `words` varchar(255) NOT NULL,
  `enable` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_keyword
-- ----------------------------

-- ----------------------------
-- Table structure for t_note
-- ----------------------------
DROP TABLE IF EXISTS `t_note`;
CREATE TABLE `t_note` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `text_content` varchar(999) DEFAULT NULL,
  `content` varchar(999) NOT NULL,
  `top` tinyint(1) NOT NULL DEFAULT '0',
  `is_show` tinyint(1) NOT NULL DEFAULT '1',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=153201606628530 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_note
-- ----------------------------
INSERT INTO `t_note` VALUES ('153201606628529', 'd', 'd', 'd', '0', '1', '2018-07-20 00:01:06', null);

-- ----------------------------
-- Table structure for t_setting
-- ----------------------------
DROP TABLE IF EXISTS `t_setting`;
CREATE TABLE `t_setting` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) NOT NULL,
  `value` varchar(600) NOT NULL,
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_setting
-- ----------------------------
INSERT INTO `t_setting` VALUES ('1', 'all_comment_open', '1', '是否全局开放评论');
INSERT INTO `t_setting` VALUES ('2', 'website_title', '小卖铺的老爷爷--未来面前，你我还都是孩子', '网站标题的文字');
INSERT INTO `t_setting` VALUES ('3', 'footer_words', '2015 - 2018', '页脚的文字');
INSERT INTO `t_setting` VALUES ('4', 'index_top_words', '未来面前，我们还都是孩子。', '首页置顶文字');
INSERT INTO `t_setting` VALUES ('5', 'menu_home', '主页', '导航菜单_首页');
INSERT INTO `t_setting` VALUES ('6', 'menu_note', '树洞', '导航菜单_笔记');
INSERT INTO `t_setting` VALUES ('7', 'menu_link', 'GitHub', '导航菜单_额外的链接');
INSERT INTO `t_setting` VALUES ('8', 'menu_link_icon', 'fa fa-github', '导航菜单_额外的链接的字体图标logo');
INSERT INTO `t_setting` VALUES ('9', 'menu_link_href', 'https://github.com/allanzhuo', '导航菜单_额外的链接url');
INSERT INTO `t_setting` VALUES ('10', 'menu_mine', '关于', '导航菜单_关于我');
INSERT INTO `t_setting` VALUES ('11', 'menu_link_show', '1', '是否显示额外的导航链接（譬如github）');
INSERT INTO `t_setting` VALUES ('12', 'wechat_pay', 'http://images.laoyeye.net/1523874512630026.png', '微信付款码');
INSERT INTO `t_setting` VALUES ('13', 'alipay', 'http://images.laoyeye.net/1523874492832284.png', '支付宝付款码');
INSERT INTO `t_setting` VALUES ('14', 'secret_id', 'AKIDNa', '腾讯云存储的secret_id');
INSERT INTO `t_setting` VALUES ('15', 'secret_key', 'Xps', '腾讯云存储的secret_key');
INSERT INTO `t_setting` VALUES ('16', 'bucket', 'yyblog', 'COS存储桶名称');
INSERT INTO `t_setting` VALUES ('17', 'region', 'ap-shanghai', 'bucket的区域');
INSERT INTO `t_setting` VALUES ('18', 'info_label', '<p>此处可去后台 偏好设置->网站设置->信息板内容 处自定义文案</p>', '信息板内容');
INSERT INTO `t_setting` VALUES ('19', 'menu_search', '搜索', '导航菜单_搜索');
INSERT INTO `t_setting` VALUES ('20', 'website_logo_words', '小卖铺的老爷爷', '网站logo的文字');
INSERT INTO `t_setting` VALUES ('21', 'comment_notice', '<span style=\"color:#FF5722;\">遵守国家法律法规，请勿回复无意义内容，请不要回复嵌套过多的楼层！</span>', '评论置顶公告');
INSERT INTO `t_setting` VALUES ('22', 'is_set_master', '0', '是否设置了网站管理员');
INSERT INTO `t_setting` VALUES ('23', 'info_panel_order', '0', '信息板位置');

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=153215625738285 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_tag
-- ----------------------------
INSERT INTO `t_tag` VALUES ('152776394791168', 'java');
INSERT INTO `t_tag` VALUES ('152933278052260', 'github');
INSERT INTO `t_tag` VALUES ('153215625738284', 'mysql');

-- ----------------------------
-- Table structure for t_tag_refer
-- ----------------------------
DROP TABLE IF EXISTS `t_tag_refer`;
CREATE TABLE `t_tag_refer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `refer_id` bigint(20) NOT NULL,
  `tag_id` bigint(20) NOT NULL,
  `is_show` tinyint(1) NOT NULL DEFAULT '0' COMMENT '默认tag不显示到文章',
  `type` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=153215719427081 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_tag_refer
-- ----------------------------
INSERT INTO `t_tag_refer` VALUES ('152776394791213', '152776394790103', '152776394791168', '1', '1');
INSERT INTO `t_tag_refer` VALUES ('152776425381090', '152759704083198', '152776394791168', '1', '1');
INSERT INTO `t_tag_refer` VALUES ('153215638567819', '153215625734689', '153215625738284', '1', '1');
INSERT INTO `t_tag_refer` VALUES ('153215719427080', '152933278046718', '152933278052260', '1', '1');

-- ----------------------------
-- Table structure for t_upload
-- ----------------------------
DROP TABLE IF EXISTS `t_upload`;
CREATE TABLE `t_upload` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `disk_path` varchar(255) NOT NULL,
  `virtual_path` varchar(255) NOT NULL,
  `upload` datetime DEFAULT NULL,
  `type` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_upload
-- ----------------------------
DROP TABLE IF EXISTS `blog_collect`;
CREATE TABLE `blog_collect` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '角色名称',
  `article_id` bigint(20) DEFAULT NULL COMMENT '角色标识',
  `enable` tinyint(1) DEFAULT '1',
  `create_user` bigint(255) DEFAULT NULL COMMENT '创建用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(255) DEFAULT NULL COMMENT '创建用户id',
  `update_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=153889662359263 DEFAULT CHARSET=utf8;

