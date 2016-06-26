/*
Navicat MySQL Data Transfer

Source Server         : local_db
Source Server Version : 50709
Source Host           : localhost:3306
Source Database       : meibo_db

Target Server Type    : MYSQL
Target Server Version : 50709
File Encoding         : 65001

Date: 2016-06-26 23:59:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for media_blog_channel
-- ----------------------------
DROP TABLE IF EXISTS `media_blog_channel`;
CREATE TABLE `media_blog_channel` (
  `channel_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nickname` varchar(16) COLLATE utf8_unicode_ci NOT NULL,
  `fans_count` int(10) NOT NULL,
  `head_image` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `qr_code` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `register_date` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `auth_type` tinyint(1) NOT NULL,
  `auth_info` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `desc` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type_id` int(10) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`channel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of media_blog_channel
-- ----------------------------
INSERT INTO `media_blog_channel` VALUES ('1', 'test', '100', 'dsfsdfsdf', 'erwerwrw', '2015年注册', '1', '342342', null, '1', '1', '2016-05-13 05:06:25', '2016-05-13 05:06:25');
INSERT INTO `media_blog_channel` VALUES ('2', 'test', '100', 'dsfsdfsdf', 'erwerwrw', '2015年注册', '1', '342342', 'desc', '2', '1', '2016-05-13 05:08:06', '2016-05-31 01:52:06');
INSERT INTO `media_blog_channel` VALUES ('3', '微博1', '100', 'blogMediaImage/1463087333594537601.jpg', 'blogQRCode/1463087333600296983.jpg', '2015年', '1', '认证信息', '测试desc', '37', '1', '2016-05-13 05:08:53', '2016-05-13 05:08:53');
INSERT INTO `media_blog_channel` VALUES ('4', '吃喝玩乐', '324332', 'blogMediaImage/1463677851659997569.png', 'blogQRCode/1463677851664759756.png', '2020年注册', '1', '广州吃喝玩乐购网站go.panyuhui.com官方微博', '广东人在广州 荔湾 越秀 白云 番禺 天河 海珠 黄埔 花都 萝岗 南沙 增城 从化', '3', '1', '2016-05-20 01:10:55', '2016-06-03 08:48:45');
INSERT INTO `media_blog_channel` VALUES ('5', 'test微博', '324332', 'blogMediaImage/1463677851659997569.png', 'blogQRCode/1463677851664759756.png', '2020年注册', '1', '广州吃喝玩乐购网站go.panyuhui.com官方微博', '广东人在广州 荔湾 越秀 白云 番禺 天河 海珠 黄埔 花都 萝岗 南沙 增城 从化', '3', '1', '2016-06-03 08:57:38', '2016-06-03 08:57:38');

-- ----------------------------
-- Table structure for media_blog_info
-- ----------------------------
DROP TABLE IF EXISTS `media_blog_info`;
CREATE TABLE `media_blog_info` (
  `blog_media_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `channel_id` int(10) NOT NULL,
  `area_id` int(10) NOT NULL,
  `publish_price` decimal(10,2) NOT NULL,
  `forward_price` decimal(10,2) NOT NULL,
  `remark` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `audit_user` int(10) DEFAULT NULL,
  `audit_date` datetime DEFAULT NULL,
  `audit_status` tinyint(1) NOT NULL DEFAULT '0',
  `status` tinyint(1) DEFAULT '1',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_user` int(10) NOT NULL,
  PRIMARY KEY (`blog_media_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of media_blog_info
-- ----------------------------
INSERT INTO `media_blog_info` VALUES ('1', '5', '2', '9999.00', '9999.00', '备注', '12', '2016-05-25 00:28:23', '1', '1', '2016-05-13 05:08:53', '2016-06-03 08:57:38', '11');
INSERT INTO `media_blog_info` VALUES ('2', '1', '1', '1000.00', '1000.00', 'remark', '12', '2016-05-31 01:52:48', '1', '1', '2016-05-31 01:53:01', '2016-05-31 01:53:01', '11');
INSERT INTO `media_blog_info` VALUES ('3', '2', '3', '1200.00', '1200.00', 'remark', '12', '2016-05-31 01:53:15', '1', '1', '2016-05-31 01:53:21', '2016-05-31 01:53:21', '11');
INSERT INTO `media_blog_info` VALUES ('4', '3', '4', '1200.00', '1400.00', 'remark', '12', '2016-05-31 01:53:41', '1', '1', '2016-05-31 01:53:45', '2016-05-31 01:53:45', '11');

-- ----------------------------
-- Table structure for media_blog_type
-- ----------------------------
DROP TABLE IF EXISTS `media_blog_type`;
CREATE TABLE `media_blog_type` (
  `id` tinyint(4) unsigned NOT NULL AUTO_INCREMENT COMMENT '微博媒体类型ID',
  `name` varchar(10) COLLATE utf8_unicode_ci NOT NULL COMMENT '微博媒体类型名称',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '启用标志位：1-启用 0-停用',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `sort_index` tinyint(4) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of media_blog_type
-- ----------------------------
INSERT INTO `media_blog_type` VALUES ('1', '母婴', '1', '2016-04-20 02:42:15', '2016-04-20 02:42:15', '1');
INSERT INTO `media_blog_type` VALUES ('2', '旅游', '1', '2016-04-20 02:42:19', '2016-04-20 02:42:19', '2');
INSERT INTO `media_blog_type` VALUES ('3', '生活', '1', '2016-04-20 02:42:22', '2016-04-20 02:42:22', '3');
INSERT INTO `media_blog_type` VALUES ('4', '热门', '1', '2016-04-20 02:42:33', '2016-04-20 02:42:33', '4');
INSERT INTO `media_blog_type` VALUES ('5', '时尚', '1', '2016-04-20 02:43:03', '2016-04-20 02:43:03', '5');
INSERT INTO `media_blog_type` VALUES ('6', '游戏', '1', '2016-04-20 02:43:07', '2016-04-20 02:43:07', '6');
INSERT INTO `media_blog_type` VALUES ('7', '娱乐', '1', '2016-04-20 02:43:10', '2016-04-20 02:43:10', '7');
INSERT INTO `media_blog_type` VALUES ('8', '创意', '1', '2016-04-20 02:43:15', '2016-04-20 02:43:15', '8');
INSERT INTO `media_blog_type` VALUES ('9', '搞笑', '1', '2016-04-20 02:43:21', '2016-04-20 02:43:21', '9');
INSERT INTO `media_blog_type` VALUES ('10', '红人', '1', '2016-04-20 02:43:26', '2016-04-20 02:43:26', '10');
INSERT INTO `media_blog_type` VALUES ('11', '影音', '1', '2016-04-20 02:43:32', '2016-04-20 02:43:32', '11');
INSERT INTO `media_blog_type` VALUES ('12', '教育', '1', '2016-04-20 02:43:39', '2016-04-20 02:43:39', '12');
INSERT INTO `media_blog_type` VALUES ('13', '情感', '1', '2016-04-20 02:43:42', '2016-04-20 02:43:42', '13');
INSERT INTO `media_blog_type` VALUES ('14', '互联网科技', '1', '2016-04-20 02:43:49', '2016-04-20 02:43:49', '14');
INSERT INTO `media_blog_type` VALUES ('15', '新闻', '1', '2016-04-20 02:43:55', '2016-04-20 02:43:55', '15');
INSERT INTO `media_blog_type` VALUES ('16', '星座', '1', '2016-04-20 02:43:58', '2016-04-20 02:43:58', '16');
INSERT INTO `media_blog_type` VALUES ('17', '财经', '1', '2016-04-20 02:44:05', '2016-04-20 02:44:05', '17');
INSERT INTO `media_blog_type` VALUES ('18', '文学', '1', '2016-04-20 02:44:08', '2016-04-20 02:44:08', '18');
INSERT INTO `media_blog_type` VALUES ('19', '数码IT', '1', '2016-04-20 02:44:14', '2016-04-20 02:44:14', '19');
INSERT INTO `media_blog_type` VALUES ('20', '房产家居', '1', '2016-04-20 02:44:21', '2016-04-20 02:44:21', '20');
INSERT INTO `media_blog_type` VALUES ('21', '健康养生', '1', '2016-04-20 02:44:28', '2016-04-20 02:44:28', '21');
INSERT INTO `media_blog_type` VALUES ('22', '体育', '1', '2016-04-20 02:44:31', '2016-04-20 02:44:31', '22');
INSERT INTO `media_blog_type` VALUES ('23', '美食', '1', '2016-04-20 02:44:36', '2016-04-20 02:44:36', '23');
INSERT INTO `media_blog_type` VALUES ('24', '摄影', '1', '2016-04-20 02:44:39', '2016-04-20 02:44:39', '24');
INSERT INTO `media_blog_type` VALUES ('25', '汽车', '1', '2016-04-20 02:44:44', '2016-04-20 02:44:44', '25');
INSERT INTO `media_blog_type` VALUES ('26', '综合', '1', '2016-04-20 02:44:47', '2016-04-20 02:44:47', '26');
INSERT INTO `media_blog_type` VALUES ('27', '职场', '1', '2016-04-20 02:44:52', '2016-04-20 02:44:52', '27');
INSERT INTO `media_blog_type` VALUES ('28', '模特', '1', '2016-04-20 02:45:01', '2016-04-20 02:45:01', '28');
INSERT INTO `media_blog_type` VALUES ('29', '语录', '1', '2016-04-20 02:45:08', '2016-04-20 02:45:08', '29');
INSERT INTO `media_blog_type` VALUES ('30', '购物', '1', '2016-04-20 02:45:39', '2016-04-20 02:45:39', '30');
INSERT INTO `media_blog_type` VALUES ('31', '校园', '1', '2016-04-20 02:45:44', '2016-04-20 02:45:44', '31');
INSERT INTO `media_blog_type` VALUES ('32', '段子手', '1', '2016-04-20 02:45:47', '2016-04-20 02:45:47', '32');
INSERT INTO `media_blog_type` VALUES ('33', '记者', '1', '2016-04-20 02:45:55', '2016-04-20 02:45:55', '33');
INSERT INTO `media_blog_type` VALUES ('34', '科技', '1', '2016-04-20 02:46:03', '2016-04-20 02:46:03', '34');
INSERT INTO `media_blog_type` VALUES ('35', '英语', '1', '2016-04-20 02:46:07', '2016-04-20 02:46:07', '35');
INSERT INTO `media_blog_type` VALUES ('36', '其他', '1', '2016-04-20 02:46:12', '2016-04-20 02:46:12', '36');
INSERT INTO `media_blog_type` VALUES ('37', '分类1', '1', '2016-05-13 04:35:20', '2016-05-13 05:26:47', '37');

-- ----------------------------
-- Table structure for media_flow_info
-- ----------------------------
DROP TABLE IF EXISTS `media_flow_info`;
CREATE TABLE `media_flow_info` (
  `id` int(8) unsigned NOT NULL AUTO_INCREMENT,
  `package_id` int(8) NOT NULL,
  `package_name` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `carrier_type` tinyint(1) unsigned NOT NULL COMMENT '运营商类型：1-电信 2-联通 3-移动',
  `carrier_name` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `explain` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `platform_id` int(8) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of media_flow_info
-- ----------------------------
INSERT INTO `media_flow_info` VALUES ('2', '165', '5M', '1.00', '1', '电信', 'LJ流量包', '2', '1', '2016-05-24 17:41:37', '2016-05-24 17:41:37');
INSERT INTO `media_flow_info` VALUES ('3', '172', '10M', '3.00', '3', '移动', 'LJ流量包', '2', '1', '2016-05-24 17:41:37', '2016-05-24 17:41:37');
INSERT INTO `media_flow_info` VALUES ('4', '160', '20M', '3.00', '2', '联通', 'lj流量', '2', '1', '2016-05-24 17:41:37', '2016-05-24 17:41:37');
INSERT INTO `media_flow_info` VALUES ('5', '2', '2M', '3.00', '1', 'd', 'd', '2', '1', '2016-05-30 00:59:19', '2016-05-30 00:59:19');
INSERT INTO `media_flow_info` VALUES ('6', '3', 'd', '3.00', '2', '32', '32', '2', '1', '2016-05-30 00:59:31', '2016-05-30 00:59:31');
INSERT INTO `media_flow_info` VALUES ('7', '324', '3', '3.00', '3', '32', '32', '2', '1', '2016-05-30 00:59:41', '2016-05-30 00:59:43');

-- ----------------------------
-- Table structure for media_news_channel
-- ----------------------------
DROP TABLE IF EXISTS `media_news_channel`;
CREATE TABLE `media_news_channel` (
  `channel_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `channel_name` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `news_type` tinyint(4) NOT NULL,
  `link_url` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `pic_url` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`channel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='新闻媒体渠道定义表';

-- ----------------------------
-- Records of media_news_channel
-- ----------------------------
INSERT INTO `media_news_channel` VALUES ('2', '腾讯网', '19', 'www.qq.com', 'newsMediaImage/1462090577322246804.jpg', '1', '2016-05-01 16:16:20', '2016-05-01 22:17:13');
INSERT INTO `media_news_channel` VALUES ('3', '在家点点', '19', 'www.qq.com', '', '0', '2016-05-13 02:58:21', '2016-05-13 02:58:21');
INSERT INTO `media_news_channel` VALUES ('4', '测试频道1', '20', 'www.qq.com', '', '0', '2016-05-13 02:59:12', '2016-05-13 02:59:12');
INSERT INTO `media_news_channel` VALUES ('5', '测试频道2', '21', 'www.qq.com', 'newsMediaImage/1463079640307521379.jpg', '1', '2016-05-13 03:00:40', '2016-05-13 03:34:00');
INSERT INTO `media_news_channel` VALUES ('6', '测试频道2324', '21', 'www.qq.com', 'newsMediaImage/1463501738132911844.png', '0', '2016-05-18 00:15:41', '2016-05-18 00:15:41');

-- ----------------------------
-- Table structure for media_news_column
-- ----------------------------
DROP TABLE IF EXISTS `media_news_column`;
CREATE TABLE `media_news_column` (
  `news_column_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `news_column_name` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`news_column_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of media_news_column
-- ----------------------------
INSERT INTO `media_news_column` VALUES ('1', '随便', '1', '2016-05-01 16:14:30', '2016-05-01 22:17:13');
INSERT INTO `media_news_column` VALUES ('2', '测试栏目1', '0', '2016-05-13 02:59:12', '2016-05-13 02:59:12');
INSERT INTO `media_news_column` VALUES ('3', '测试栏目2', '1', '2016-05-13 03:00:40', '2016-05-13 03:34:00');

-- ----------------------------
-- Table structure for media_news_info
-- ----------------------------
DROP TABLE IF EXISTS `media_news_info`;
CREATE TABLE `media_news_info` (
  `news_media_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `channel_id` int(10) NOT NULL COMMENT '新闻媒体渠道ID',
  `column_id` int(10) NOT NULL COMMENT '新闻媒体栏目ID',
  `area_id` int(8) NOT NULL COMMENT '地区',
  `include_type` tinyint(1) NOT NULL COMMENT '收录类型',
  `quote_price` decimal(16,2) NOT NULL COMMENT '报价',
  `title` varchar(20) COLLATE utf8_unicode_ci DEFAULT '' COMMENT '标题',
  `allow_link` tinyint(1) NOT NULL COMMENT '是否允许带链接',
  `allow_qrcode` tinyint(1) NOT NULL COMMENT '是否允许带二维码',
  `allow_contactway` tinyint(1) NOT NULL COMMENT '是否允许带联系方式',
  `image_url` varchar(40) COLLATE utf8_unicode_ci DEFAULT '' COMMENT '本地图片地址',
  `remark` varchar(20) COLLATE utf8_unicode_ci DEFAULT '' COMMENT '备注',
  `audit_user` int(10) DEFAULT NULL,
  `audit_date` datetime DEFAULT NULL,
  `audit_status` tinyint(1) NOT NULL COMMENT '审核状态：0-待审核 1-审核通过 2-审核拒绝',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '启用标志位：0-停用 1-启用',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_user` int(10) NOT NULL COMMENT '创建人',
  PRIMARY KEY (`news_media_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of media_news_info
-- ----------------------------
INSERT INTO `media_news_info` VALUES ('3', '2', '1', '1', '1', '1000.00', '23字以内', '1', '1', '1', 'newsMediaImage/1462090577322246804.jpg', 'test', '11', '2016-05-01 22:17:14', '1', '1', '2016-05-01 16:16:21', '2016-05-25 00:22:44', '11');
INSERT INTO `media_news_info` VALUES ('4', '2', '1', '2', '1', '5000.00', '21字', '1', '1', '1', 'newsMediaImage/1462090577322246804.jpg', 'sdfs', null, '2016-05-31 02:28:44', '1', '1', '2016-05-02 04:07:07', '2016-05-31 02:29:11', '11');
INSERT INTO `media_news_info` VALUES ('5', '2', '1', '2', '1', '1000.00', '23字以内', '1', '1', '1', '', 'test', null, '2016-05-31 02:28:47', '1', '1', '2016-05-10 14:52:20', '2016-05-31 02:29:12', '11');
INSERT INTO `media_news_info` VALUES ('6', '2', '1', '3', '1', '100.00', '23字以内', '1', '1', '1', 'newsMediaImage/1462863169136497315.jpg', 'test', null, '2016-05-31 02:28:50', '1', '1', '2016-05-10 14:52:49', '2016-05-31 02:29:13', '11');
INSERT INTO `media_news_info` VALUES ('7', '3', '1', '13', '1', '1000.00', '23字以内', '1', '1', '1', '', 'test', null, '2016-05-31 02:28:53', '1', '1', '2016-05-13 02:58:21', '2016-05-31 02:29:13', '11');
INSERT INTO `media_news_info` VALUES ('8', '4', '2', '3', '1', '1000.00', '23字以内', '1', '1', '1', '', 'test', null, '2016-05-31 02:28:56', '1', '1', '2016-05-13 02:59:12', '2016-05-31 02:29:15', '11');
INSERT INTO `media_news_info` VALUES ('9', '5', '3', '3', '1', '1.00', '招待费', '0', '0', '0', 'newsMediaImage/1463079640307521379.jpg', 'test', '11', '2016-05-13 03:34:00', '1', '1', '2016-05-13 03:00:40', '2016-05-31 02:29:16', '11');
INSERT INTO `media_news_info` VALUES ('10', '5', '3', '3', '1', '1000.00', '23字以内', '1', '1', '1', 'newsMediaImage/1463500816946136006.png', 'test', null, '2016-05-31 02:29:00', '1', '1', '2016-05-18 00:00:20', '2016-05-31 02:29:18', '12');
INSERT INTO `media_news_info` VALUES ('11', '6', '3', '1', '1', '1000.00', '23字以内', '1', '1', '1', 'newsMediaImage/1463501738132911844.png', 'test', null, '2016-05-31 02:29:02', '1', '1', '2016-05-18 00:15:41', '2016-05-31 02:29:09', '12');

-- ----------------------------
-- Table structure for media_news_type
-- ----------------------------
DROP TABLE IF EXISTS `media_news_type`;
CREATE TABLE `media_news_type` (
  `id` tinyint(4) unsigned NOT NULL AUTO_INCREMENT COMMENT '新闻媒体类型ID',
  `name` varchar(10) COLLATE utf8_unicode_ci NOT NULL COMMENT '新闻媒体类型名称',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '启用标志位：1-启用 0-停用',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `sort_index` tinyint(4) NOT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='新闻媒体类型';

-- ----------------------------
-- Records of media_news_type
-- ----------------------------
INSERT INTO `media_news_type` VALUES ('1', '娱乐', '1', '2016-04-19 17:23:33', '2016-04-19 17:23:33', '1');
INSERT INTO `media_news_type` VALUES ('2', '汽车', '1', '2016-04-19 17:23:38', '2016-04-19 17:23:38', '2');
INSERT INTO `media_news_type` VALUES ('3', '生活消费', '1', '2016-04-19 17:23:44', '2016-04-19 17:23:44', '3');
INSERT INTO `media_news_type` VALUES ('4', '旅游', '1', '2016-04-19 17:23:49', '2016-04-19 17:23:49', '4');
INSERT INTO `media_news_type` VALUES ('5', '艺术收藏', '1', '2016-04-19 17:23:56', '2016-04-19 17:23:56', '5');
INSERT INTO `media_news_type` VALUES ('6', '房产家居', '1', '2016-04-19 17:24:05', '2016-04-19 17:24:05', '6');
INSERT INTO `media_news_type` VALUES ('7', '能源照明', '1', '2016-04-19 17:24:13', '2016-04-19 17:24:13', '7');
INSERT INTO `media_news_type` VALUES ('8', '健康医疗', '1', '2016-04-19 17:24:20', '2016-04-19 17:24:20', '8');
INSERT INTO `media_news_type` VALUES ('9', '游戏', '1', '2016-04-19 17:24:24', '2016-04-19 17:24:24', '9');
INSERT INTO `media_news_type` VALUES ('10', 'IT科技', '1', '2016-04-19 17:24:28', '2016-04-19 17:24:28', '10');
INSERT INTO `media_news_type` VALUES ('11', '女性时尚', '1', '2016-04-19 17:24:35', '2016-04-19 17:24:35', '11');
INSERT INTO `media_news_type` VALUES ('12', '财经商业', '1', '2016-04-19 17:24:41', '2016-04-19 17:24:41', '12');
INSERT INTO `media_news_type` VALUES ('13', '新闻资讯', '1', '2016-04-19 17:24:47', '2016-04-19 17:24:47', '13');
INSERT INTO `media_news_type` VALUES ('14', '地方新闻', '1', '2016-04-19 17:24:52', '2016-04-19 17:24:52', '14');
INSERT INTO `media_news_type` VALUES ('15', '教育培训', '1', '2016-04-19 17:24:58', '2016-04-19 17:24:58', '15');
INSERT INTO `media_news_type` VALUES ('16', '母婴亲自', '1', '2016-04-19 17:25:06', '2016-04-19 17:25:06', '16');
INSERT INTO `media_news_type` VALUES ('17', '门户网站', '1', '2016-04-19 17:25:12', '2016-04-19 17:25:12', '17');
INSERT INTO `media_news_type` VALUES ('19', '全站', '1', '2016-05-01 16:16:20', '2016-05-01 22:17:13', '18');
INSERT INTO `media_news_type` VALUES ('20', '测试类型1', '0', '2016-05-13 02:59:12', '2016-05-13 02:59:12', '19');
INSERT INTO `media_news_type` VALUES ('21', '测试类型2', '1', '2016-05-13 03:00:40', '2016-05-13 03:34:00', '20');

-- ----------------------------
-- Table structure for media_wechat_channel
-- ----------------------------
DROP TABLE IF EXISTS `media_wechat_channel`;
CREATE TABLE `media_wechat_channel` (
  `channel_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nickname` varchar(16) COLLATE utf8_unicode_ci NOT NULL,
  `account` varchar(16) COLLATE utf8_unicode_ci NOT NULL,
  `fans_count` int(10) NOT NULL,
  `head_image` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `qr_code` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `desc` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `authentication` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `type_id` int(10) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`channel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of media_wechat_channel
-- ----------------------------
INSERT INTO `media_wechat_channel` VALUES ('1', '微信用户1', 'testAcc', '100', 'wechatMediaImage/1463144877700705601.jpg', 'wechatQRCode/1463144877715836775.jpg', '测试desc', 'dsfsd', '24', '1', '2016-05-13 21:07:57', '2016-05-13 21:08:51');

-- ----------------------------
-- Table structure for media_wechat_info
-- ----------------------------
DROP TABLE IF EXISTS `media_wechat_info`;
CREATE TABLE `media_wechat_info` (
  `wechat_media_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `channel_id` int(10) NOT NULL,
  `area_id` int(8) NOT NULL,
  `first_price` decimal(10,2) NOT NULL,
  `second_price` decimal(10,2) NOT NULL,
  `other_price` decimal(10,2) NOT NULL,
  `remark` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `audit_user` int(10) DEFAULT NULL,
  `audit_date` datetime DEFAULT NULL,
  `audit_status` tinyint(2) NOT NULL DEFAULT '0',
  `status` tinyint(2) NOT NULL DEFAULT '1',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_user` int(10) NOT NULL,
  PRIMARY KEY (`wechat_media_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of media_wechat_info
-- ----------------------------
INSERT INTO `media_wechat_info` VALUES ('1', '1', '3', '99999.00', '150.00', '100.00', '备注', '12', '2016-05-23 02:31:15', '1', '1', '2016-05-13 21:07:57', '2016-05-31 02:39:30', '11');

-- ----------------------------
-- Table structure for media_wechat_type
-- ----------------------------
DROP TABLE IF EXISTS `media_wechat_type`;
CREATE TABLE `media_wechat_type` (
  `id` tinyint(4) unsigned NOT NULL AUTO_INCREMENT COMMENT '微信媒体类型ID',
  `name` varchar(10) COLLATE utf8_unicode_ci NOT NULL COMMENT '微信媒体名称',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '启用标志位：1-启用 0-停用',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `sort_index` tinyint(4) unsigned NOT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='微信媒体类型';

-- ----------------------------
-- Records of media_wechat_type
-- ----------------------------
INSERT INTO `media_wechat_type` VALUES ('1', '财经', '1', '2016-04-20 02:32:52', '2016-04-20 02:32:52', '1');
INSERT INTO `media_wechat_type` VALUES ('2', '星座', '1', '2016-04-20 02:32:54', '2016-04-20 02:32:54', '2');
INSERT INTO `media_wechat_type` VALUES ('3', '科技', '1', '2016-04-20 02:32:58', '2016-04-20 02:32:58', '3');
INSERT INTO `media_wechat_type` VALUES ('4', '生活', '1', '2016-04-20 02:33:01', '2016-04-20 02:33:01', '4');
INSERT INTO `media_wechat_type` VALUES ('5', '汽车', '1', '2016-04-20 02:33:09', '2016-04-20 02:33:09', '5');
INSERT INTO `media_wechat_type` VALUES ('6', '女性', '1', '2016-04-20 02:33:13', '2016-04-20 02:33:13', '6');
INSERT INTO `media_wechat_type` VALUES ('7', '阅读', '1', '2016-04-20 02:33:17', '2016-04-20 02:33:17', '7');
INSERT INTO `media_wechat_type` VALUES ('8', '娱乐八卦', '1', '2016-04-20 02:33:21', '2016-04-20 02:33:21', '8');
INSERT INTO `media_wechat_type` VALUES ('9', '视觉艺术', '1', '2016-04-20 02:33:31', '2016-04-20 02:33:31', '9');
INSERT INTO `media_wechat_type` VALUES ('10', '母婴亲子', '1', '2016-04-20 02:33:43', '2016-04-20 02:33:43', '10');
INSERT INTO `media_wechat_type` VALUES ('11', '旅行', '1', '2016-04-20 02:33:53', '2016-04-20 02:33:53', '11');
INSERT INTO `media_wechat_type` VALUES ('12', '新闻资讯', '1', '2016-04-20 02:33:58', '2016-04-20 02:33:58', '12');
INSERT INTO `media_wechat_type` VALUES ('13', '时尚', '1', '2016-04-20 02:34:03', '2016-04-20 02:34:03', '13');
INSERT INTO `media_wechat_type` VALUES ('14', '互联网', '1', '2016-04-20 02:34:07', '2016-04-20 02:34:07', '14');
INSERT INTO `media_wechat_type` VALUES ('15', '电商', '1', '2016-04-20 02:34:13', '2016-04-20 02:34:13', '15');
INSERT INTO `media_wechat_type` VALUES ('16', '美食', '1', '2016-04-20 02:34:17', '2016-04-20 02:34:17', '16');
INSERT INTO `media_wechat_type` VALUES ('17', '健康养生', '1', '2016-04-20 02:34:24', '2016-04-20 02:34:24', '17');
INSERT INTO `media_wechat_type` VALUES ('18', '家居房产', '1', '2016-04-20 02:34:31', '2016-04-20 02:34:31', '18');
INSERT INTO `media_wechat_type` VALUES ('19', '情感', '1', '2016-04-20 02:34:36', '2016-04-20 02:34:36', '19');
INSERT INTO `media_wechat_type` VALUES ('20', '游戏动漫', '1', '2016-04-20 02:34:41', '2016-04-20 02:34:41', '20');
INSERT INTO `media_wechat_type` VALUES ('21', '教育培训', '1', '2016-04-20 02:34:47', '2016-04-20 02:34:47', '21');
INSERT INTO `media_wechat_type` VALUES ('22', '职场', '1', '2016-04-20 02:34:56', '2016-04-20 02:34:56', '22');
INSERT INTO `media_wechat_type` VALUES ('23', '其他', '1', '2016-04-20 02:35:02', '2016-04-20 02:35:47', '23');
INSERT INTO `media_wechat_type` VALUES ('24', '分类1', '1', '2016-05-13 21:06:30', '2016-05-13 21:25:47', '24');

-- ----------------------------
-- Table structure for member_account
-- ----------------------------
DROP TABLE IF EXISTS `member_account`;
CREATE TABLE `member_account` (
  `account_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '会员账户ID',
  `member_id` int(10) NOT NULL COMMENT '会员ID',
  `account_type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '账户类型',
  `available_balance` decimal(16,2) NOT NULL DEFAULT '0.00' COMMENT '可用余额',
  `lock_balance` decimal(16,2) NOT NULL DEFAULT '0.00' COMMENT '锁定余额',
  `total_balance` decimal(16,2) NOT NULL DEFAULT '0.00' COMMENT '总计余额',
  `total_recharge_amount` decimal(16,2) NOT NULL DEFAULT '0.00' COMMENT '累计充值金额',
  `total_consume_amount` decimal(16,2) NOT NULL DEFAULT '0.00' COMMENT '累计消费金额',
  `invoice_amount` decimal(16,2) NOT NULL DEFAULT '0.00' COMMENT '可开票金额',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '启用标志位：1-启用 0-停用',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='会员账户表';

-- ----------------------------
-- Records of member_account
-- ----------------------------
INSERT INTO `member_account` VALUES ('2', '11', '1', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '1', '2016-04-16 03:29:25', '2016-04-16 03:29:25');
INSERT INTO `member_account` VALUES ('3', '12', '1', '12085.00', '0.00', '12085.00', '12000.00', '23316.00', '0.00', '1', '2016-04-20 01:53:49', '2016-05-31 23:55:26');
INSERT INTO `member_account` VALUES ('4', '13', '1', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '1', '2016-05-13 02:51:17', '2016-05-13 02:51:17');
INSERT INTO `member_account` VALUES ('5', '14', '1', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '1', '2016-05-13 02:53:10', '2016-05-13 02:53:10');
INSERT INTO `member_account` VALUES ('6', '15', '1', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '1', '2016-05-15 15:34:18', '2016-05-15 15:34:18');
INSERT INTO `member_account` VALUES ('7', '16', '1', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '1', '2016-05-15 16:00:12', '2016-05-15 16:00:12');
INSERT INTO `member_account` VALUES ('8', '17', '1', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '1', '2016-05-15 16:03:04', '2016-05-15 16:03:04');
INSERT INTO `member_account` VALUES ('9', '18', '1', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '1', '2016-05-15 16:04:26', '2016-05-15 16:04:26');
INSERT INTO `member_account` VALUES ('10', '19', '1', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '1', '2016-05-15 16:06:24', '2016-05-15 16:06:24');

-- ----------------------------
-- Table structure for member_company
-- ----------------------------
DROP TABLE IF EXISTS `member_company`;
CREATE TABLE `member_company` (
  `member_company_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `member_id` int(10) NOT NULL,
  `company_name` varchar(16) COLLATE utf8_unicode_ci NOT NULL,
  `biz_license` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `company_tel` varchar(16) COLLATE utf8_unicode_ci NOT NULL,
  `company_addr` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `legal_person_name` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `legal_person_tel` varchar(16) COLLATE utf8_unicode_ci NOT NULL,
  `biz_charge_name` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `biz_charge_tel` varchar(16) COLLATE utf8_unicode_ci NOT NULL,
  `tech_charge_name` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `tech_charge_tel` varchar(16) COLLATE utf8_unicode_ci NOT NULL,
  `website_url` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `status` tinyint(1) NOT NULL COMMENT '状态：0-待审核 1-审核通过 2-审核失败',
  `audit_user` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `audit_msg` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `audit_date` datetime DEFAULT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`member_company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of member_company
-- ----------------------------
INSERT INTO `member_company` VALUES ('1', '12', 'parkson0002', '1342342', '021-64640000', '上海市徐汇区', '张三', '13600000000', '李四', '13800000000', '赵六', '13900000000', 'www.baidu.com', '0', null, null, null, '2016-06-14 09:17:16', '2016-06-14 10:50:42');

-- ----------------------------
-- Table structure for member_detail
-- ----------------------------
DROP TABLE IF EXISTS `member_detail`;
CREATE TABLE `member_detail` (
  `member_detail_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `member_id` int(10) NOT NULL,
  `is_auth_company` tinyint(1) NOT NULL DEFAULT '0',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`member_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of member_detail
-- ----------------------------

-- ----------------------------
-- Table structure for member_info
-- ----------------------------
DROP TABLE IF EXISTS `member_info`;
CREATE TABLE `member_info` (
  `member_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `login_name` varchar(12) COLLATE utf8_unicode_ci NOT NULL COMMENT '登录名',
  `login_pwd` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '登录密码',
  `mobile_num` varchar(12) COLLATE utf8_unicode_ci NOT NULL COMMENT '手机号',
  `member_type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '会员类型：1-注册用户 2-供应商 3-管理员 4-代理商',
  `role_id` tinyint(2) NOT NULL DEFAULT '1' COMMENT '用户角色：0-管理员 1-普通用户',
  `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '使用标志位：1-启用 0-停用',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`member_id`),
  UNIQUE KEY `member_status_loginname` (`status`,`login_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户信息表';

-- ----------------------------
-- Records of member_info
-- ----------------------------
INSERT INTO `member_info` VALUES ('11', 'pr0001', '14e1b600b1fd579f47433b88e8d85291', '13611920057', '2', '0', '1', '2016-04-16 03:29:25', '2016-05-13 22:49:58');
INSERT INTO `member_info` VALUES ('12', 'parkson0001', '14e1b600b1fd579f47433b88e8d85291', '13611920000', '1', '0', '1', '2016-04-20 01:53:49', '2016-06-03 08:44:04');
INSERT INTO `member_info` VALUES ('13', 'admin0001', '14e1b600b1fd579f47433b88e8d85291', '13611920052', '3', '0', '1', '2016-05-13 02:51:17', '2016-05-13 22:50:33');
INSERT INTO `member_info` VALUES ('14', 'agent0001', '14e1b600b1fd579f47433b88e8d85291', '13611920111', '4', '0', '1', '2016-05-13 02:53:10', '2016-05-13 22:51:07');
INSERT INTO `member_info` VALUES ('17', 'kimi11111', '29852fd8f42d63ef579aa46d8cd15183', '13611900001', '1', '1', '1', '2016-05-15 16:03:04', '2016-05-15 16:03:04');
INSERT INTO `member_info` VALUES ('18', 'kimi11112', '29852fd8f42d63ef579aa46d8cd15183', '13611900002', '1', '1', '1', '2016-05-15 16:04:25', '2016-05-15 16:04:25');
INSERT INTO `member_info` VALUES ('19', 'kimi11113', '29852fd8f42d63ef579aa46d8cd15183', '13611900003', '1', '1', '1', '2016-05-15 16:06:24', '2016-05-15 16:06:24');
INSERT INTO `member_info` VALUES ('20', 'test0001', '14e1b600b1fd579f47433b88e8d85291', '12312321312', '1', '1', '1', '2016-05-15 18:12:13', '2016-05-15 18:12:23');

-- ----------------------------
-- Table structure for order_blog_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_blog_detail`;
CREATE TABLE `order_blog_detail` (
  `order_detail_id` int(12) unsigned NOT NULL AUTO_INCREMENT,
  `order_id` int(10) NOT NULL,
  `execute_date` varchar(12) COLLATE utf8_unicode_ci NOT NULL,
  `resource_link` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `text_content` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `file_url` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `remark` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of order_blog_detail
-- ----------------------------
INSERT INTO `order_blog_detail` VALUES ('1', '3', '924234', '32423', 'content info', 'blogOrderUpload/1463867223143703784.png', '32432', '2016-05-22 05:47:00', '2016-05-22 05:47:03');
INSERT INTO `order_blog_detail` VALUES ('2', '4', '924234', '32423', 'content info', 'blogOrderUpload/1463867266452352296.png', '32432', '2016-05-22 05:47:43', '2016-05-22 05:47:46');
INSERT INTO `order_blog_detail` VALUES ('3', '5', '924234', '32423', 'content info', 'blogOrderUpload/1463867393961315341.png', '32432', '2016-05-22 05:49:54', '2016-05-22 05:49:53');
INSERT INTO `order_blog_detail` VALUES ('4', '6', '924234', '32423', 'content info', 'blogOrderUpload/1463867600395098667.png', '32432', '2016-05-22 05:53:20', '2016-05-22 05:53:20');
INSERT INTO `order_blog_detail` VALUES ('5', '7', '924234', '32423', 'content info', 'blogOrderUpload/1463868160607692668.png', '32432', '2016-05-22 06:01:15', '2016-05-22 06:02:40');

-- ----------------------------
-- Table structure for order_blog_info
-- ----------------------------
DROP TABLE IF EXISTS `order_blog_info`;
CREATE TABLE `order_blog_info` (
  `order_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `order_code` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `member_id` int(10) NOT NULL,
  `trans_amount` decimal(12,2) NOT NULL,
  `order_status` tinyint(2) NOT NULL COMMENT '订单状态：1-未支付 2-已支付 3-已受理 4-投放中 5-投放结束 6-已退款 7-订单拒绝',
  `trans_type` tinyint(1) NOT NULL DEFAULT '3' COMMENT '关联order_type的order_type_id',
  `order_date` datetime NOT NULL,
  `pay_date` datetime DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of order_blog_info
-- ----------------------------
INSERT INTO `order_blog_info` VALUES ('1', '216052205420600127576', '12', '0.00', '1', '1', '2016-05-22 05:42:06', null, '2016-05-22 05:42:06', '2016-05-22 05:42:06');
INSERT INTO `order_blog_info` VALUES ('2', '216052205434000120094', '12', '0.00', '1', '1', '2016-05-22 05:43:41', null, '2016-05-22 05:43:41', '2016-05-22 05:43:40');
INSERT INTO `order_blog_info` VALUES ('3', '216052205465900123467', '12', '0.00', '2', '1', '2016-05-22 05:47:00', '2016-05-22 05:47:03', '2016-05-22 05:47:00', '2016-05-22 05:47:03');
INSERT INTO `order_blog_info` VALUES ('4', '216052205474300128570', '12', '0.00', '2', '1', '2016-05-22 05:47:43', '2016-05-22 05:47:47', '2016-05-22 05:47:43', '2016-05-22 05:47:46');
INSERT INTO `order_blog_info` VALUES ('5', '216052205491900123518', '12', '0.00', '2', '1', '2016-05-22 05:49:54', '2016-05-22 05:49:58', '2016-05-22 05:49:54', '2016-05-22 05:49:57');
INSERT INTO `order_blog_info` VALUES ('6', '216052205531100124842', '12', '9999.00', '2', '1', '2016-05-22 05:53:20', '2016-05-22 05:53:21', '2016-05-22 05:53:20', '2016-05-22 05:53:20');
INSERT INTO `order_blog_info` VALUES ('7', '216052206005700120854', '12', '9999.00', '2', '1', '2016-05-22 06:01:15', '2016-05-22 06:02:41', '2016-05-22 06:01:15', '2016-05-22 06:02:40');

-- ----------------------------
-- Table structure for order_blog_split
-- ----------------------------
DROP TABLE IF EXISTS `order_blog_split`;
CREATE TABLE `order_blog_split` (
  `order_split_id` int(12) unsigned NOT NULL AUTO_INCREMENT,
  `order_id` int(10) NOT NULL,
  `blog_media_id` int(10) NOT NULL,
  `media_member_id` int(10) NOT NULL,
  `price_type` tinyint(1) NOT NULL,
  `trans_amount` decimal(12,2) NOT NULL,
  `launch_url` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `reject_msg` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `order_status` tinyint(1) NOT NULL COMMENT '订单状态：1-未支付 2-已支付 3-已受理 4-投放中 5-投放结束 6-已退款 7-订单拒绝',
  `accept_date` datetime DEFAULT NULL,
  `reject_date` datetime DEFAULT NULL,
  `launch_date` datetime DEFAULT NULL,
  `finish_date` datetime DEFAULT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_split_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of order_blog_split
-- ----------------------------
INSERT INTO `order_blog_split` VALUES ('1', '3', '1', '11', '1', '9999.00', null, null, '2', null, null, null, null, '2016-05-22 05:47:00', '2016-05-22 05:47:03');
INSERT INTO `order_blog_split` VALUES ('2', '4', '1', '11', '1', '9999.00', null, null, '2', null, null, null, null, '2016-05-22 05:47:43', '2016-05-22 05:47:46');
INSERT INTO `order_blog_split` VALUES ('3', '5', '1', '11', '1', '9999.00', null, null, '2', null, null, null, null, '2016-05-22 05:49:54', '2016-05-22 05:49:58');
INSERT INTO `order_blog_split` VALUES ('4', '6', '1', '11', '1', '9999.00', null, null, '2', null, null, null, null, '2016-05-22 05:53:20', '2016-05-22 05:53:20');
INSERT INTO `order_blog_split` VALUES ('5', '7', '1', '11', '1', '9999.00', null, null, '2', null, null, null, null, '2016-05-22 06:01:15', '2016-05-22 06:02:40');

-- ----------------------------
-- Table structure for order_flow_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_flow_detail`;
CREATE TABLE `order_flow_detail` (
  `order_detail_id` int(12) unsigned NOT NULL AUTO_INCREMENT,
  `order_id` int(10) NOT NULL,
  `dx_package_id` int(8) DEFAULT NULL,
  `lt_package_id` int(8) DEFAULT NULL,
  `yd_package_id` int(8) DEFAULT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of order_flow_detail
-- ----------------------------
INSERT INTO `order_flow_detail` VALUES ('1', '1', '165', '160', '172', '2016-05-27 09:38:27', '2016-05-27 09:38:27');
INSERT INTO `order_flow_detail` VALUES ('2', '2', '165', '160', '172', '2016-05-27 09:48:55', '2016-05-27 09:48:55');
INSERT INTO `order_flow_detail` VALUES ('3', '3', '165', '160', '172', '2016-05-30 00:51:13', '2016-05-30 00:51:13');
INSERT INTO `order_flow_detail` VALUES ('4', '4', '2', '160', '172', '2016-05-30 00:59:55', '2016-05-30 00:59:55');

-- ----------------------------
-- Table structure for order_flow_info
-- ----------------------------
DROP TABLE IF EXISTS `order_flow_info`;
CREATE TABLE `order_flow_info` (
  `order_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `order_code` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `member_id` int(10) NOT NULL,
  `trans_amount` decimal(12,2) NOT NULL,
  `order_status` tinyint(2) NOT NULL COMMENT '订单状态：1-未支付 2-已支付 8-已完成',
  `order_name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `trans_type` tinyint(1) NOT NULL DEFAULT '5' COMMENT '关联order_type的order_type_id',
  `serial_number` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `order_date` datetime NOT NULL,
  `pay_date` datetime DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of order_flow_info
-- ----------------------------
INSERT INTO `order_flow_info` VALUES ('1', '416052709382700128616', '12', '3.00', '2', '13611920055流量充值10M', '5', '', '2016-05-27 09:38:27', '2016-05-27 09:52:46', '2016-05-27 09:38:27', '2016-05-27 09:52:45');
INSERT INTO `order_flow_info` VALUES ('2', '416052709485500123722', '12', '6.00', '2', '测试活动', '5', '', '2016-05-27 09:48:56', '2016-05-27 09:53:11', '2016-05-27 09:48:56', '2016-05-27 09:53:11');
INSERT INTO `order_flow_info` VALUES ('3', '416053000511300123320', '12', '3.00', '2', '13611920055流量充值10M', '5', '', '2016-05-30 00:51:13', '2016-05-30 00:51:14', '2016-05-30 00:51:13', '2016-05-30 00:51:13');
INSERT INTO `order_flow_info` VALUES ('4', '416053000595500128989', '12', '3.00', '2', '13611920055流量充值10M', '5', '', '2016-05-30 00:59:56', '2016-05-30 00:59:56', '2016-05-30 00:59:56', '2016-05-30 00:59:56');

-- ----------------------------
-- Table structure for order_flow_split
-- ----------------------------
DROP TABLE IF EXISTS `order_flow_split`;
CREATE TABLE `order_flow_split` (
  `order_split_id` int(12) unsigned NOT NULL AUTO_INCREMENT,
  `order_id` int(10) NOT NULL,
  `package_id` int(10) NOT NULL,
  `dest_mobile` varchar(12) COLLATE utf8_unicode_ci NOT NULL,
  `trans_amount` decimal(12,2) NOT NULL,
  `order_status` tinyint(1) NOT NULL COMMENT '订单状态：1-未支付 2-已支付 8-充值成功',
  `finish_date` datetime DEFAULT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_split_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of order_flow_split
-- ----------------------------
INSERT INTO `order_flow_split` VALUES ('1', '1', '172', '13611920055', '3.00', '2', null, '2016-05-27 09:38:27', '2016-05-27 09:52:46');
INSERT INTO `order_flow_split` VALUES ('2', '2', '172', '13611920055', '3.00', '2', null, '2016-05-27 09:48:56', '2016-05-27 09:53:11');
INSERT INTO `order_flow_split` VALUES ('3', '2', '172', '13917079805', '3.00', '2', null, '2016-05-27 09:48:56', '2016-05-27 09:53:11');
INSERT INTO `order_flow_split` VALUES ('4', '3', '172', '13611920055', '3.00', '2', null, '2016-05-30 00:51:13', '2016-05-30 00:51:13');
INSERT INTO `order_flow_split` VALUES ('5', '4', '172', '13611920055', '3.00', '2', null, '2016-05-30 00:59:56', '2016-05-30 00:59:56');

-- ----------------------------
-- Table structure for order_news_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_news_detail`;
CREATE TABLE `order_news_detail` (
  `order_detail_id` int(12) unsigned NOT NULL AUTO_INCREMENT,
  `order_id` int(10) NOT NULL,
  `execute_date` varchar(12) COLLATE utf8_unicode_ci NOT NULL,
  `title` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `resource_link` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `file_url` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `remark` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of order_news_detail
-- ----------------------------
INSERT INTO `order_news_detail` VALUES ('4', '4', '924234', '3234234费', '32423', 'newsOrderUpload/1464112771003273978.docx', '32432', '2016-05-25 01:59:31', '2016-05-25 01:59:31');
INSERT INTO `order_news_detail` VALUES ('5', '5', '924234', '3234234费', '32423', 'newsOrderUpload/1464112876078732773.pdf', '32432', '2016-05-25 02:01:16', '2016-05-25 02:01:16');
INSERT INTO `order_news_detail` VALUES ('6', '6', '924234', '3234234费', '32423', 'newsOrderUpload/1464112890404067217.pdf', '32432', '2016-05-25 02:01:30', '2016-05-25 02:01:30');

-- ----------------------------
-- Table structure for order_news_info
-- ----------------------------
DROP TABLE IF EXISTS `order_news_info`;
CREATE TABLE `order_news_info` (
  `order_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `order_code` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `member_id` int(10) NOT NULL,
  `trans_amount` decimal(12,2) NOT NULL,
  `order_status` tinyint(2) NOT NULL COMMENT '订单状态：1-未支付 2-已支付 3-已受理 4-投放中 5-投放结束 6-已退款 7-订单拒绝',
  `trans_type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '关联order_type的order_type_id',
  `order_date` datetime NOT NULL,
  `pay_date` datetime DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of order_news_info
-- ----------------------------
INSERT INTO `order_news_info` VALUES ('4', '116052501593000123425', '12', '1101.00', '1', '1', '2016-05-25 01:59:31', '2016-05-25 01:59:31', '2016-05-25 01:59:31', '2016-05-25 01:59:59');
INSERT INTO `order_news_info` VALUES ('5', '116052502011500120817', '12', '1101.00', '2', '1', '2016-05-25 02:01:16', '2016-05-25 02:01:16', '2016-05-25 02:01:16', '2016-05-25 02:01:16');
INSERT INTO `order_news_info` VALUES ('6', '116052502013000127931', '12', '1101.00', '1', '1', '2016-05-25 02:01:30', null, '2016-05-25 02:01:30', '2016-05-25 02:01:30');

-- ----------------------------
-- Table structure for order_news_split
-- ----------------------------
DROP TABLE IF EXISTS `order_news_split`;
CREATE TABLE `order_news_split` (
  `order_split_id` int(12) unsigned NOT NULL AUTO_INCREMENT,
  `order_id` int(10) NOT NULL,
  `news_media_id` int(10) NOT NULL,
  `media_member_id` int(10) NOT NULL,
  `trans_amount` decimal(12,2) NOT NULL,
  `launch_url` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `reject_msg` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `order_status` tinyint(1) NOT NULL COMMENT '订单状态：1-未支付 2-已支付 3-已受理 4-投放中 5-投放结束 6-已退款 7-订单拒绝',
  `accept_date` datetime DEFAULT NULL,
  `reject_date` datetime DEFAULT NULL,
  `launch_date` datetime DEFAULT NULL,
  `finish_date` datetime DEFAULT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_split_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of order_news_split
-- ----------------------------
INSERT INTO `order_news_split` VALUES ('10', '4', '3', '11', '1000.00', null, null, '2', null, null, null, null, '2016-05-25 01:59:31', '2016-05-25 01:59:31');
INSERT INTO `order_news_split` VALUES ('11', '4', '6', '11', '100.00', null, null, '2', null, null, null, null, '2016-05-25 01:59:31', '2016-05-25 01:59:31');
INSERT INTO `order_news_split` VALUES ('12', '4', '9', '11', '1.00', null, null, '2', null, null, null, null, '2016-05-25 01:59:31', '2016-05-25 01:59:31');
INSERT INTO `order_news_split` VALUES ('13', '5', '3', '11', '1000.00', null, null, '2', null, null, null, null, '2016-05-25 02:01:16', '2016-05-25 02:01:16');
INSERT INTO `order_news_split` VALUES ('14', '5', '6', '11', '100.00', null, null, '2', null, null, null, null, '2016-05-25 02:01:16', '2016-05-25 02:01:16');
INSERT INTO `order_news_split` VALUES ('15', '5', '9', '11', '1.00', null, null, '2', null, null, null, null, '2016-05-25 02:01:16', '2016-05-25 02:01:16');
INSERT INTO `order_news_split` VALUES ('16', '6', '3', '11', '1000.00', null, null, '1', null, null, null, null, '2016-05-25 02:01:30', '2016-05-25 02:01:30');
INSERT INTO `order_news_split` VALUES ('17', '6', '6', '11', '100.00', null, null, '1', null, null, null, null, '2016-05-25 02:01:30', '2016-05-25 02:01:30');
INSERT INTO `order_news_split` VALUES ('18', '6', '9', '11', '1.00', null, null, '1', null, null, null, null, '2016-05-25 02:01:30', '2016-05-25 02:01:30');

-- ----------------------------
-- Table structure for order_recharge_info
-- ----------------------------
DROP TABLE IF EXISTS `order_recharge_info`;
CREATE TABLE `order_recharge_info` (
  `order_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `order_code` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `member_id` int(10) NOT NULL,
  `trans_amount` decimal(12,2) NOT NULL,
  `order_status` tinyint(2) NOT NULL COMMENT '订单状态：1-未支付 2-已支付 9-审核失败',
  `platform_id` int(4) NOT NULL,
  `trans_type` tinyint(1) NOT NULL COMMENT '关联order_type的order_type_id',
  `voucher_num` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `audit_msg` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `edit_msg` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `audit_user` int(10) DEFAULT NULL,
  `finish_date` datetime DEFAULT NULL,
  `order_date` datetime NOT NULL,
  `pay_date` datetime DEFAULT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of order_recharge_info
-- ----------------------------
INSERT INTO `order_recharge_info` VALUES ('2', '716053122501700123055', '12', '1000.00', '2', '3', '7', 'offlineRechargeVoucherNum001', '收到转账金额1000.00', '实收金额1000.00', '12', '2016-05-31 23:50:20', '2016-05-31 22:50:18', null, '2016-05-31 22:50:18', '2016-05-31 23:50:19');
INSERT INTO `order_recharge_info` VALUES ('3', '716053123541100129808', '12', '10000.00', '2', '3', '7', 'offlineRechargeVoucherNum001', '收到转账金额1000.00', null, '12', '2016-05-31 23:55:26', '2016-05-31 23:54:11', null, '2016-05-31 23:54:11', '2016-05-31 23:55:26');
INSERT INTO `order_recharge_info` VALUES ('4', '716060100030900127243', '12', '10000.00', '1', '3', '7', 'offlineRechargeVoucherNum002', null, null, null, null, '2016-06-01 00:03:10', null, '2016-06-01 00:03:10', '2016-06-01 00:03:09');

-- ----------------------------
-- Table structure for order_type
-- ----------------------------
DROP TABLE IF EXISTS `order_type`;
CREATE TABLE `order_type` (
  `order_type_id` tinyint(2) unsigned NOT NULL AUTO_INCREMENT COMMENT '订单类型ID',
  `order_type_name` varchar(8) COLLATE utf8_unicode_ci NOT NULL COMMENT '订单类型名称',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '启用标志位：1-启用 0-停用',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `sort_index` tinyint(2) NOT NULL,
  PRIMARY KEY (`order_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='订单类型定义表';

-- ----------------------------
-- Records of order_type
-- ----------------------------
INSERT INTO `order_type` VALUES ('1', '新闻订单', '1', '2016-04-15 04:03:12', '2016-04-15 04:03:12', '1');
INSERT INTO `order_type` VALUES ('2', '微博订单', '1', '2016-04-15 04:03:23', '2016-05-22 04:05:11', '2');
INSERT INTO `order_type` VALUES ('3', '微信订单', '1', '2016-04-15 04:03:28', '2016-05-22 04:05:10', '3');
INSERT INTO `order_type` VALUES ('4', '新闻套餐订单', '1', '2016-04-15 04:03:40', '2016-04-15 04:03:40', '4');
INSERT INTO `order_type` VALUES ('5', '流量订单', '1', '2016-04-15 04:03:47', '2016-04-15 04:03:47', '5');
INSERT INTO `order_type` VALUES ('6', '在线充值', '1', '2016-04-15 04:04:05', '2016-04-15 04:04:31', '6');
INSERT INTO `order_type` VALUES ('7', '转账充值', '1', '2016-04-15 04:04:22', '2016-04-15 04:04:22', '7');
INSERT INTO `order_type` VALUES ('8', '退款', '1', '2016-04-15 04:04:27', '2016-04-15 04:04:27', '8');

-- ----------------------------
-- Table structure for order_wechat_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_wechat_detail`;
CREATE TABLE `order_wechat_detail` (
  `order_detail_id` int(12) unsigned NOT NULL AUTO_INCREMENT,
  `order_id` int(10) NOT NULL,
  `execute_date` varchar(12) COLLATE utf8_unicode_ci NOT NULL,
  `title` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `resource_link` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `original_link` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `text_content` varchar(512) COLLATE utf8_unicode_ci NOT NULL,
  `file_url` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `remark` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of order_wechat_detail
-- ----------------------------
INSERT INTO `order_wechat_detail` VALUES ('1', '3', '924234', '3234234费', '32423', 'www.baidu.cn', 'content info', 'wechatOrderUpload/1463939485312988004.png', '32432', '2016-05-23 01:51:25', '2016-05-23 01:52:11');
INSERT INTO `order_wechat_detail` VALUES ('2', '4', '924234', '3234234费', '32423', 'www.baidu.cn', 'content info', 'wechatOrderUpload/1463939533701439497.png', '32432', '2016-05-23 01:52:14', '2016-05-23 01:52:13');

-- ----------------------------
-- Table structure for order_wechat_info
-- ----------------------------
DROP TABLE IF EXISTS `order_wechat_info`;
CREATE TABLE `order_wechat_info` (
  `order_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `order_code` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `member_id` int(10) NOT NULL,
  `trans_amount` decimal(12,2) NOT NULL,
  `order_status` tinyint(2) NOT NULL COMMENT '订单状态：1-未支付 2-已支付 3-已受理 4-投放中 5-投放结束 6-已退款 7-订单拒绝',
  `trans_type` tinyint(1) NOT NULL DEFAULT '2' COMMENT '关联order_type的order_type_id',
  `order_date` datetime NOT NULL,
  `pay_date` datetime DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of order_wechat_info
-- ----------------------------
INSERT INTO `order_wechat_info` VALUES ('1', '316052301472700125992', '12', '99999.00', '1', '1', '2016-05-23 01:47:27', null, '2016-05-23 01:47:27', '2016-05-23 01:47:27');
INSERT INTO `order_wechat_info` VALUES ('2', '316052301511700129287', '12', '99999.00', '1', '1', '2016-05-23 01:51:18', null, '2016-05-23 01:51:18', '2016-05-23 01:51:17');
INSERT INTO `order_wechat_info` VALUES ('3', '316052301512500126693', '12', '99999.00', '1', '1', '2016-05-23 01:51:25', null, '2016-05-23 01:51:25', '2016-05-23 01:51:25');
INSERT INTO `order_wechat_info` VALUES ('4', '316052301521300121689', '12', '99999.00', '1', '1', '2016-05-23 01:52:14', null, '2016-05-23 01:52:14', '2016-05-23 01:52:13');

-- ----------------------------
-- Table structure for order_wechat_split
-- ----------------------------
DROP TABLE IF EXISTS `order_wechat_split`;
CREATE TABLE `order_wechat_split` (
  `order_split_id` int(12) unsigned NOT NULL AUTO_INCREMENT,
  `order_id` int(10) NOT NULL,
  `wechat_media_id` int(10) NOT NULL,
  `media_member_id` int(10) NOT NULL,
  `price_type` tinyint(1) NOT NULL,
  `trans_amount` decimal(12,2) NOT NULL,
  `launch_url` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `reject_msg` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `order_status` tinyint(1) NOT NULL COMMENT '订单状态：1-未支付 2-已支付 3-已受理 4-投放中 5-投放结束 6-已退款 7-订单拒绝',
  `accept_date` datetime DEFAULT NULL,
  `reject_date` datetime DEFAULT NULL,
  `launch_date` datetime DEFAULT NULL,
  `finish_date` datetime DEFAULT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_split_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of order_wechat_split
-- ----------------------------
INSERT INTO `order_wechat_split` VALUES ('1', '1', '1', '11', '1', '99999.00', null, null, '1', null, null, null, null, '2016-05-23 01:47:27', '2016-05-23 01:47:27');
INSERT INTO `order_wechat_split` VALUES ('2', '2', '1', '11', '1', '99999.00', null, null, '1', null, null, null, null, '2016-05-23 01:51:18', '2016-05-23 01:51:17');
INSERT INTO `order_wechat_split` VALUES ('3', '3', '1', '11', '1', '99999.00', null, null, '1', null, null, null, null, '2016-05-23 01:51:25', '2016-05-23 01:51:25');
INSERT INTO `order_wechat_split` VALUES ('4', '4', '1', '11', '1', '99999.00', null, null, '3', '2016-05-23 02:06:32', null, null, null, '2016-05-23 01:52:14', '2016-05-23 02:06:31');

-- ----------------------------
-- Table structure for system_area_info
-- ----------------------------
DROP TABLE IF EXISTS `system_area_info`;
CREATE TABLE `system_area_info` (
  `area_id` int(6) NOT NULL AUTO_INCREMENT,
  `area_name` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `initial_letters` char(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  `is_hot` tinyint(1) NOT NULL DEFAULT '0',
  `level` tinyint(1) NOT NULL COMMENT '级别：0-国 1-大区 2-省 3-市',
  `pre_area_id` int(6) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`area_id`)
) ENGINE=InnoDB AUTO_INCREMENT=393 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of system_area_info
-- ----------------------------
INSERT INTO `system_area_info` VALUES ('1', '全国', 'Q', '0', '0', '0', '1', '2016-05-23 02:44:47', '2016-05-23 04:40:08');
INSERT INTO `system_area_info` VALUES ('2', '安徽', 'A', '0', '2', '1', '1', '2016-05-23 02:49:09', '2016-05-23 04:04:37');
INSERT INTO `system_area_info` VALUES ('3', '北京', 'B', '1', '2', '1', '1', '2016-05-23 02:49:43', '2016-05-27 10:23:31');
INSERT INTO `system_area_info` VALUES ('4', '重庆', 'C', '1', '2', '1', '1', '2016-05-23 02:49:55', '2016-05-27 10:23:45');
INSERT INTO `system_area_info` VALUES ('5', '福建', 'F', '1', '2', '1', '1', '2016-05-23 02:50:08', '2016-05-27 10:23:32');
INSERT INTO `system_area_info` VALUES ('6', '甘肃', 'G', '0', '2', '1', '1', '2016-05-23 02:50:17', '2016-05-23 04:04:37');
INSERT INTO `system_area_info` VALUES ('7', '广东', 'G', '1', '2', '1', '1', '2016-05-23 02:50:26', '2016-05-27 10:23:39');
INSERT INTO `system_area_info` VALUES ('8', '广西', 'G', '0', '2', '1', '1', '2016-05-23 02:50:35', '2016-05-23 04:04:37');
INSERT INTO `system_area_info` VALUES ('9', '贵州', 'G', '0', '2', '1', '1', '2016-05-23 02:50:44', '2016-05-23 04:04:37');
INSERT INTO `system_area_info` VALUES ('10', '澳门', 'A', '0', '2', '1', '1', '2016-05-23 02:51:30', '2016-05-23 04:04:37');
INSERT INTO `system_area_info` VALUES ('11', '海南', 'H', '0', '2', '1', '1', '2016-05-23 02:51:39', '2016-05-23 04:04:37');
INSERT INTO `system_area_info` VALUES ('12', '河北', 'H', '0', '2', '1', '1', '2016-05-23 02:51:48', '2016-05-23 04:04:37');
INSERT INTO `system_area_info` VALUES ('13', '河南', 'H', '0', '2', '1', '1', '2016-05-23 02:51:56', '2016-05-23 04:04:37');
INSERT INTO `system_area_info` VALUES ('14', '湖北', 'H', '0', '2', '1', '1', '2016-05-23 02:52:08', '2016-05-23 04:04:37');
INSERT INTO `system_area_info` VALUES ('15', '湖南', 'H', '0', '2', '1', '1', '2016-05-23 02:52:18', '2016-05-23 04:04:37');
INSERT INTO `system_area_info` VALUES ('16', '吉林', 'J', '0', '2', '1', '1', '2016-05-23 02:52:26', '2016-05-23 04:04:37');
INSERT INTO `system_area_info` VALUES ('17', '江苏', 'J', '1', '2', '1', '1', '2016-05-23 02:52:32', '2016-05-27 10:23:46');
INSERT INTO `system_area_info` VALUES ('18', '江西', 'J', '0', '2', '1', '1', '2016-05-23 02:52:38', '2016-05-23 04:04:37');
INSERT INTO `system_area_info` VALUES ('19', '黑龙江', 'H', '0', '2', '1', '1', '2016-05-23 02:52:45', '2016-05-23 04:04:37');
INSERT INTO `system_area_info` VALUES ('20', '辽宁', 'L', '0', '2', '1', '1', '2016-05-23 02:52:52', '2016-05-23 04:04:37');
INSERT INTO `system_area_info` VALUES ('21', '宁夏', 'N', '0', '2', '1', '1', '2016-05-23 02:52:59', '2016-05-23 04:04:37');
INSERT INTO `system_area_info` VALUES ('22', '青海', 'Q', '0', '2', '1', '1', '2016-05-23 02:53:06', '2016-05-23 04:04:37');
INSERT INTO `system_area_info` VALUES ('23', '山东', 'S', '0', '2', '1', '1', '2016-05-23 02:53:13', '2016-05-23 04:04:37');
INSERT INTO `system_area_info` VALUES ('24', '山西', 'S', '0', '2', '1', '1', '2016-05-23 02:53:19', '2016-05-23 04:04:37');
INSERT INTO `system_area_info` VALUES ('25', '陕西', 'S', '0', '2', '1', '1', '2016-05-23 02:53:31', '2016-05-23 04:04:37');
INSERT INTO `system_area_info` VALUES ('26', '上海', 'S', '1', '2', '1', '1', '2016-05-23 02:53:39', '2016-05-27 10:23:26');
INSERT INTO `system_area_info` VALUES ('27', '四川', 'S', '0', '2', '1', '1', '2016-05-23 02:53:49', '2016-05-23 04:04:37');
INSERT INTO `system_area_info` VALUES ('28', '内蒙古', 'N', '0', '2', '1', '1', '2016-05-23 02:54:01', '2016-05-23 04:04:37');
INSERT INTO `system_area_info` VALUES ('29', '天津', 'T', '0', '2', '1', '1', '2016-05-23 02:54:10', '2016-05-23 04:04:37');
INSERT INTO `system_area_info` VALUES ('30', '西藏', 'X', '0', '2', '1', '1', '2016-05-23 02:54:19', '2016-05-23 04:04:37');
INSERT INTO `system_area_info` VALUES ('31', '新疆', 'X', '0', '2', '1', '1', '2016-05-23 02:54:26', '2016-05-23 04:04:37');
INSERT INTO `system_area_info` VALUES ('32', '云南', 'Y', '0', '2', '1', '1', '2016-05-23 02:54:33', '2016-05-23 04:04:37');
INSERT INTO `system_area_info` VALUES ('33', '浙江', 'Z', '1', '2', '1', '1', '2016-05-23 02:54:40', '2016-05-27 10:23:53');
INSERT INTO `system_area_info` VALUES ('34', '香港', 'X', '0', '2', '1', '1', '2016-05-23 02:54:47', '2016-05-23 04:04:37');
INSERT INTO `system_area_info` VALUES ('35', '台湾', 'T', '0', '2', '1', '1', '2016-05-23 02:55:05', '2016-05-23 04:04:37');
INSERT INTO `system_area_info` VALUES ('36', '黄山市', null, '0', '3', '2', '1', '2016-05-23 02:55:53', '2016-05-23 02:56:04');
INSERT INTO `system_area_info` VALUES ('37', '芜湖市', null, '0', '3', '2', '1', '2016-05-23 02:56:12', '2016-05-23 02:56:12');
INSERT INTO `system_area_info` VALUES ('38', '铜陵市', null, '0', '3', '2', '1', '2016-05-23 02:56:21', '2016-05-23 02:56:21');
INSERT INTO `system_area_info` VALUES ('39', '合肥市', null, '0', '3', '2', '1', '2016-05-23 02:56:27', '2016-05-23 02:56:27');
INSERT INTO `system_area_info` VALUES ('40', '滁州市', null, '0', '3', '2', '1', '2016-05-23 02:56:41', '2016-05-23 02:56:41');
INSERT INTO `system_area_info` VALUES ('41', '宣城市', null, '0', '3', '2', '1', '2016-05-23 02:56:48', '2016-05-23 02:56:48');
INSERT INTO `system_area_info` VALUES ('42', '安庆市', null, '0', '3', '2', '1', '2016-05-23 02:56:55', '2016-05-23 02:56:55');
INSERT INTO `system_area_info` VALUES ('43', '池州市', null, '0', '3', '2', '1', '2016-05-23 02:57:01', '2016-05-23 02:57:01');
INSERT INTO `system_area_info` VALUES ('44', '六安市', null, '0', '3', '2', '1', '2016-05-23 02:57:15', '2016-05-23 02:57:15');
INSERT INTO `system_area_info` VALUES ('45', '毫州市', null, '0', '3', '2', '1', '2016-05-23 02:57:21', '2016-05-23 02:57:21');
INSERT INTO `system_area_info` VALUES ('46', '蚌埠市', null, '0', '3', '2', '1', '2016-05-23 02:57:43', '2016-05-23 02:57:43');
INSERT INTO `system_area_info` VALUES ('47', '阜阳市', null, '0', '3', '2', '1', '2016-05-23 02:57:51', '2016-05-23 02:57:51');
INSERT INTO `system_area_info` VALUES ('48', '淮北市', null, '0', '3', '2', '1', '2016-05-23 02:57:58', '2016-05-23 02:57:58');
INSERT INTO `system_area_info` VALUES ('49', '马鞍山市', null, '0', '3', '2', '1', '2016-05-23 02:58:05', '2016-05-23 02:58:05');
INSERT INTO `system_area_info` VALUES ('50', '宿州市', null, '0', '3', '2', '1', '2016-05-23 02:58:11', '2016-05-23 02:58:11');
INSERT INTO `system_area_info` VALUES ('51', '淮南市', null, '0', '3', '2', '1', '2016-05-23 02:58:22', '2016-05-23 02:58:22');
INSERT INTO `system_area_info` VALUES ('52', '北京市', null, '0', '3', '3', '1', '2016-05-23 02:58:46', '2016-05-23 02:58:46');
INSERT INTO `system_area_info` VALUES ('53', '重庆市', null, '0', '3', '4', '1', '2016-05-23 02:59:18', '2016-05-23 02:59:18');
INSERT INTO `system_area_info` VALUES ('54', '漳州市', null, '0', '3', '5', '1', '2016-05-23 02:59:28', '2016-05-23 02:59:28');
INSERT INTO `system_area_info` VALUES ('55', '福州市', null, '0', '3', '5', '1', '2016-05-23 02:59:34', '2016-05-23 02:59:34');
INSERT INTO `system_area_info` VALUES ('56', '厦门市', null, '0', '3', '5', '1', '2016-05-23 02:59:39', '2016-05-23 02:59:39');
INSERT INTO `system_area_info` VALUES ('57', '泉州市', null, '0', '3', '5', '1', '2016-05-23 02:59:45', '2016-05-23 02:59:45');
INSERT INTO `system_area_info` VALUES ('58', '龙岩市', null, '0', '3', '5', '1', '2016-05-23 02:59:51', '2016-05-23 02:59:51');
INSERT INTO `system_area_info` VALUES ('59', '三明市', null, '0', '3', '5', '1', '2016-05-23 02:59:57', '2016-05-23 02:59:57');
INSERT INTO `system_area_info` VALUES ('60', '宁德市', null, '0', '3', '5', '1', '2016-05-23 03:00:04', '2016-05-23 03:00:04');
INSERT INTO `system_area_info` VALUES ('61', '南平市', null, '0', '3', '5', '1', '2016-05-23 03:00:11', '2016-05-23 03:00:11');
INSERT INTO `system_area_info` VALUES ('62', '莆田市', null, '0', '3', '5', '1', '2016-05-23 03:00:25', '2016-05-23 03:00:25');
INSERT INTO `system_area_info` VALUES ('63', '兰州市', null, '0', '3', '6', '1', '2016-05-23 03:00:34', '2016-05-23 03:00:34');
INSERT INTO `system_area_info` VALUES ('64', '临夏回族自治州', null, '0', '3', '6', '1', '2016-05-23 03:01:03', '2016-05-23 03:01:03');
INSERT INTO `system_area_info` VALUES ('65', '平凉市', null, '0', '3', '6', '1', '2016-05-23 03:01:12', '2016-05-23 03:01:12');
INSERT INTO `system_area_info` VALUES ('66', '武威市', null, '0', '3', '6', '1', '2016-05-23 03:01:19', '2016-05-23 03:01:19');
INSERT INTO `system_area_info` VALUES ('67', '定西市', null, '0', '3', '6', '1', '2016-05-23 03:01:26', '2016-05-23 03:01:26');
INSERT INTO `system_area_info` VALUES ('68', '酒泉市', null, '0', '3', '6', '1', '2016-05-23 03:01:33', '2016-05-23 03:01:33');
INSERT INTO `system_area_info` VALUES ('69', '张掖市', null, '0', '3', '6', '1', '2016-05-23 03:01:39', '2016-05-23 03:01:39');
INSERT INTO `system_area_info` VALUES ('70', '陇南市', null, '0', '3', '6', '1', '2016-05-23 03:01:45', '2016-05-23 03:01:45');
INSERT INTO `system_area_info` VALUES ('71', '庆阳市', null, '0', '3', '6', '1', '2016-05-23 03:01:51', '2016-05-23 03:01:51');
INSERT INTO `system_area_info` VALUES ('72', '天水市', null, '0', '3', '6', '1', '2016-05-23 03:02:05', '2016-05-23 03:02:05');
INSERT INTO `system_area_info` VALUES ('73', '白银市', null, '0', '3', '6', '1', '2016-05-23 03:02:12', '2016-05-23 03:02:12');
INSERT INTO `system_area_info` VALUES ('74', '甘南藏族自治州', null, '0', '3', '6', '1', '2016-05-23 03:02:29', '2016-05-23 03:02:29');
INSERT INTO `system_area_info` VALUES ('75', '金昌市', null, '0', '3', '6', '1', '2016-05-23 03:02:36', '2016-05-23 03:02:36');
INSERT INTO `system_area_info` VALUES ('76', '嘉谷关市', null, '0', '3', '6', '1', '2016-05-23 03:03:20', '2016-05-23 03:03:20');
INSERT INTO `system_area_info` VALUES ('77', '佛山市', null, '0', '3', '7', '1', '2016-05-23 03:03:37', '2016-05-23 03:03:37');
INSERT INTO `system_area_info` VALUES ('78', '韶关市', null, '0', '3', '7', '1', '2016-05-23 03:03:44', '2016-05-23 03:03:44');
INSERT INTO `system_area_info` VALUES ('79', '中山市', null, '0', '3', '7', '1', '2016-05-23 03:03:52', '2016-05-23 03:03:52');
INSERT INTO `system_area_info` VALUES ('80', '广州市', null, '0', '3', '7', '1', '2016-05-23 03:03:59', '2016-05-23 03:03:59');
INSERT INTO `system_area_info` VALUES ('81', '惠州市', null, '0', '3', '7', '1', '2016-05-23 03:04:04', '2016-05-23 03:04:04');
INSERT INTO `system_area_info` VALUES ('82', '珠海市', null, '0', '3', '7', '1', '2016-05-23 03:04:11', '2016-05-23 03:04:11');
INSERT INTO `system_area_info` VALUES ('83', '东莞市', null, '0', '3', '7', '1', '2016-05-23 03:04:16', '2016-05-23 03:04:16');
INSERT INTO `system_area_info` VALUES ('84', '深圳市', null, '0', '3', '7', '1', '2016-05-23 03:04:23', '2016-05-23 03:04:23');
INSERT INTO `system_area_info` VALUES ('85', '河源市', null, '0', '3', '7', '1', '2016-05-23 03:04:29', '2016-05-23 03:04:29');
INSERT INTO `system_area_info` VALUES ('86', '江门市', null, '0', '3', '7', '1', '2016-05-23 03:04:40', '2016-05-23 03:04:40');
INSERT INTO `system_area_info` VALUES ('87', '汕头市', null, '0', '3', '7', '1', '2016-05-23 03:04:46', '2016-05-23 03:04:46');
INSERT INTO `system_area_info` VALUES ('88', '湛江市', null, '0', '3', '7', '1', '2016-05-23 03:04:52', '2016-05-23 03:04:52');
INSERT INTO `system_area_info` VALUES ('89', '潮州市', null, '0', '3', '7', '1', '2016-05-23 03:04:58', '2016-05-23 03:04:58');
INSERT INTO `system_area_info` VALUES ('90', '云浮市', null, '0', '3', '7', '1', '2016-05-23 03:05:06', '2016-05-23 03:05:06');
INSERT INTO `system_area_info` VALUES ('91', '肇庆市', null, '0', '3', '7', '1', '2016-05-23 03:05:22', '2016-05-23 03:05:22');
INSERT INTO `system_area_info` VALUES ('92', '梅州市', null, '0', '3', '7', '1', '2016-05-23 03:05:28', '2016-05-23 03:05:28');
INSERT INTO `system_area_info` VALUES ('93', '汕尾市', null, '0', '3', '7', '1', '2016-05-23 03:05:34', '2016-05-23 03:05:34');
INSERT INTO `system_area_info` VALUES ('94', '阳江市', null, '0', '3', '7', '1', '2016-05-23 03:05:40', '2016-05-23 03:05:40');
INSERT INTO `system_area_info` VALUES ('95', '揭阳市', null, '0', '3', '7', '1', '2016-05-23 03:05:54', '2016-05-23 03:05:54');
INSERT INTO `system_area_info` VALUES ('96', '茂名市', null, '0', '3', '7', '1', '2016-05-23 03:06:00', '2016-05-23 03:06:00');
INSERT INTO `system_area_info` VALUES ('97', '清远市', null, '0', '3', '7', '1', '2016-05-23 03:06:10', '2016-05-23 03:06:10');
INSERT INTO `system_area_info` VALUES ('98', '梧州市', null, '0', '3', '8', '1', '2016-05-23 03:06:16', '2016-05-23 03:06:16');
INSERT INTO `system_area_info` VALUES ('99', '南宁市', null, '0', '3', '8', '1', '2016-05-23 03:06:23', '2016-05-23 03:06:23');
INSERT INTO `system_area_info` VALUES ('100', '百色市', null, '0', '3', '8', '1', '2016-05-23 03:06:29', '2016-05-23 03:06:29');
INSERT INTO `system_area_info` VALUES ('101', '河池市', null, '0', '3', '8', '1', '2016-05-23 03:06:36', '2016-05-23 03:06:36');
INSERT INTO `system_area_info` VALUES ('102', '崇左市', null, '0', '3', '8', '1', '2016-05-23 03:06:44', '2016-05-23 03:06:44');
INSERT INTO `system_area_info` VALUES ('103', '贺州市', null, '0', '3', '8', '1', '2016-05-23 03:06:55', '2016-05-23 03:06:55');
INSERT INTO `system_area_info` VALUES ('104', '玉林市', null, '0', '3', '8', '1', '2016-05-23 03:07:03', '2016-05-23 03:07:03');
INSERT INTO `system_area_info` VALUES ('105', '桂林市', null, '0', '3', '8', '1', '2016-05-23 03:07:08', '2016-05-23 03:07:08');
INSERT INTO `system_area_info` VALUES ('106', '柳州市', null, '0', '3', '8', '1', '2016-05-23 03:07:14', '2016-05-23 03:07:14');
INSERT INTO `system_area_info` VALUES ('107', '来宾市', null, '0', '3', '8', '1', '2016-05-23 03:07:20', '2016-05-23 03:07:20');
INSERT INTO `system_area_info` VALUES ('108', '防城港市', null, '0', '3', '8', '1', '2016-05-23 03:07:27', '2016-05-23 03:07:27');
INSERT INTO `system_area_info` VALUES ('109', '钦州市', null, '0', '3', '8', '1', '2016-05-23 03:07:36', '2016-05-23 03:07:36');
INSERT INTO `system_area_info` VALUES ('110', '北海市', null, '0', '3', '8', '1', '2016-05-23 03:07:42', '2016-05-23 03:07:42');
INSERT INTO `system_area_info` VALUES ('111', '贵港市', null, '0', '3', '8', '1', '2016-05-23 03:07:52', '2016-05-23 03:07:52');
INSERT INTO `system_area_info` VALUES ('112', '贵阳市', null, '0', '3', '9', '1', '2016-05-23 03:08:02', '2016-05-23 03:08:02');
INSERT INTO `system_area_info` VALUES ('113', '铜仁市', null, '0', '3', '9', '1', '2016-05-23 03:08:09', '2016-05-23 03:08:09');
INSERT INTO `system_area_info` VALUES ('114', '黔西南布衣族苗族', null, '0', '3', '9', '1', '2016-05-23 03:09:13', '2016-05-23 03:09:13');
INSERT INTO `system_area_info` VALUES ('115', '黔东南苗族同族自', null, '0', '3', '9', '1', '2016-05-23 03:09:44', '2016-05-23 03:09:44');
INSERT INTO `system_area_info` VALUES ('116', '遵义市', null, '0', '3', '9', '1', '2016-05-23 03:09:53', '2016-05-23 03:09:53');
INSERT INTO `system_area_info` VALUES ('117', '六盘水市', null, '0', '3', '9', '1', '2016-05-23 03:10:04', '2016-05-23 03:10:04');
INSERT INTO `system_area_info` VALUES ('118', '黔南布衣族苗族自', null, '0', '3', '9', '1', '2016-05-23 03:10:22', '2016-05-23 03:10:22');
INSERT INTO `system_area_info` VALUES ('119', '毕节市', null, '0', '3', '9', '1', '2016-05-23 03:10:27', '2016-05-23 03:10:27');
INSERT INTO `system_area_info` VALUES ('120', '安顺市', null, '0', '3', '9', '1', '2016-05-23 03:10:40', '2016-05-23 03:10:40');
INSERT INTO `system_area_info` VALUES ('121', '海口市', null, '0', '3', '11', '1', '2016-05-23 03:10:55', '2016-05-23 03:10:55');
INSERT INTO `system_area_info` VALUES ('122', '三亚市', null, '0', '3', '11', '1', '2016-05-23 03:11:01', '2016-05-23 03:11:01');
INSERT INTO `system_area_info` VALUES ('123', '万宁市', null, '0', '3', '11', '1', '2016-05-23 03:11:06', '2016-05-23 03:11:06');
INSERT INTO `system_area_info` VALUES ('124', '陵水黎族自治县', null, '0', '3', '11', '1', '2016-05-23 03:11:24', '2016-05-23 03:11:24');
INSERT INTO `system_area_info` VALUES ('125', '保亭黎族苗族自治', null, '0', '3', '11', '1', '2016-05-23 03:11:45', '2016-05-23 03:11:45');
INSERT INTO `system_area_info` VALUES ('126', '琼海市', null, '0', '3', '11', '1', '2016-05-23 03:11:51', '2016-05-23 03:11:51');
INSERT INTO `system_area_info` VALUES ('127', '澄迈县', null, '0', '3', '11', '1', '2016-05-23 03:12:01', '2016-05-23 03:12:01');
INSERT INTO `system_area_info` VALUES ('128', '昌江黎族自治县', null, '0', '3', '11', '1', '2016-05-23 03:12:15', '2016-05-23 03:12:15');
INSERT INTO `system_area_info` VALUES ('129', '屯昌县', null, '0', '3', '11', '1', '2016-05-23 03:12:23', '2016-05-23 03:12:23');
INSERT INTO `system_area_info` VALUES ('130', '临高县', null, '0', '3', '11', '1', '2016-05-23 03:12:30', '2016-05-23 03:12:30');
INSERT INTO `system_area_info` VALUES ('131', '詹州市', null, '0', '3', '11', '1', '2016-05-23 03:12:50', '2016-05-23 03:12:50');
INSERT INTO `system_area_info` VALUES ('132', '文昌市', null, '0', '3', '11', '1', '2016-05-23 03:12:56', '2016-05-23 03:12:56');
INSERT INTO `system_area_info` VALUES ('133', '乐东黎族自治县', null, '0', '3', '11', '1', '2016-05-23 03:13:08', '2016-05-23 03:13:08');
INSERT INTO `system_area_info` VALUES ('134', '五指山市', null, '0', '3', '11', '1', '2016-05-23 03:13:14', '2016-05-23 03:13:14');
INSERT INTO `system_area_info` VALUES ('135', '东方市', null, '0', '3', '11', '1', '2016-05-23 03:13:20', '2016-05-23 03:13:20');
INSERT INTO `system_area_info` VALUES ('136', '琼中黎族苗族自治', null, '0', '3', '11', '1', '2016-05-23 03:13:35', '2016-05-23 03:13:35');
INSERT INTO `system_area_info` VALUES ('137', '白沙黎族自治县', null, '0', '3', '11', '1', '2016-05-23 03:13:51', '2016-05-23 03:13:51');
INSERT INTO `system_area_info` VALUES ('138', '定安县', null, '0', '3', '11', '1', '2016-05-23 03:14:01', '2016-05-23 03:14:01');
INSERT INTO `system_area_info` VALUES ('139', '沧州市', null, '0', '3', '12', '1', '2016-05-23 03:14:09', '2016-05-23 03:14:09');
INSERT INTO `system_area_info` VALUES ('140', '张家口市', null, '0', '3', '12', '1', '2016-05-23 03:14:14', '2016-05-23 03:14:14');
INSERT INTO `system_area_info` VALUES ('141', '秦皇岛市', null, '0', '3', '12', '1', '2016-05-23 03:14:22', '2016-05-23 03:14:22');
INSERT INTO `system_area_info` VALUES ('142', '保定市', null, '0', '3', '12', '1', '2016-05-23 03:14:29', '2016-05-23 03:14:29');
INSERT INTO `system_area_info` VALUES ('143', '石家庄市', null, '0', '3', '12', '1', '2016-05-23 03:14:36', '2016-05-23 03:14:36');
INSERT INTO `system_area_info` VALUES ('144', '唐山市', null, '0', '3', '12', '1', '2016-05-23 03:14:43', '2016-05-23 03:14:43');
INSERT INTO `system_area_info` VALUES ('145', '衡水市', null, '0', '3', '12', '1', '2016-05-23 03:14:50', '2016-05-23 03:14:50');
INSERT INTO `system_area_info` VALUES ('146', '邢台市', null, '0', '3', '12', '1', '2016-05-23 03:14:57', '2016-05-23 03:14:57');
INSERT INTO `system_area_info` VALUES ('147', '承德市', null, '0', '3', '12', '1', '2016-05-23 03:15:02', '2016-05-23 03:15:02');
INSERT INTO `system_area_info` VALUES ('148', '邯郸市', null, '0', '3', '12', '1', '2016-05-23 03:15:19', '2016-05-23 03:15:19');
INSERT INTO `system_area_info` VALUES ('149', '廊坊市', null, '0', '3', '12', '1', '2016-05-23 03:15:30', '2016-05-23 03:15:30');
INSERT INTO `system_area_info` VALUES ('150', '郑州市', null, '0', '3', '13', '1', '2016-05-23 03:19:59', '2016-05-23 03:19:59');
INSERT INTO `system_area_info` VALUES ('151', '洛阳市', null, '0', '3', '13', '1', '2016-05-23 03:20:08', '2016-05-23 03:20:08');
INSERT INTO `system_area_info` VALUES ('152', '南阳市', null, '0', '3', '13', '1', '2016-05-23 03:20:13', '2016-05-23 03:20:13');
INSERT INTO `system_area_info` VALUES ('153', '新乡市', null, '0', '3', '13', '1', '2016-05-23 03:20:19', '2016-05-23 03:20:19');
INSERT INTO `system_area_info` VALUES ('154', '安阳市', null, '0', '3', '13', '1', '2016-05-23 03:20:26', '2016-05-23 03:20:26');
INSERT INTO `system_area_info` VALUES ('155', '信阳市', null, '0', '3', '13', '1', '2016-05-23 03:20:35', '2016-05-23 03:20:35');
INSERT INTO `system_area_info` VALUES ('156', '平顶山市', null, '0', '3', '13', '1', '2016-05-23 03:20:42', '2016-05-23 03:20:42');
INSERT INTO `system_area_info` VALUES ('157', '漯河市', null, '0', '3', '13', '1', '2016-05-23 03:20:48', '2016-05-23 03:20:48');
INSERT INTO `system_area_info` VALUES ('158', '周口市', null, '0', '3', '13', '1', '2016-05-23 03:21:13', '2016-05-23 03:21:13');
INSERT INTO `system_area_info` VALUES ('159', '濮阳市', null, '0', '3', '13', '1', '2016-05-23 03:21:20', '2016-05-23 03:21:20');
INSERT INTO `system_area_info` VALUES ('160', '驻马店市', null, '0', '3', '13', '1', '2016-05-23 03:21:26', '2016-05-23 03:21:26');
INSERT INTO `system_area_info` VALUES ('161', '鹤壁市', null, '0', '3', '13', '1', '2016-05-23 03:21:32', '2016-05-23 03:21:32');
INSERT INTO `system_area_info` VALUES ('162', '开封市', null, '0', '3', '13', '1', '2016-05-23 03:21:38', '2016-05-23 03:21:38');
INSERT INTO `system_area_info` VALUES ('163', '许昌市', null, '0', '3', '13', '1', '2016-05-23 03:21:44', '2016-05-23 03:21:44');
INSERT INTO `system_area_info` VALUES ('164', '三门峡市', null, '0', '3', '13', '1', '2016-05-23 03:21:52', '2016-05-23 03:21:52');
INSERT INTO `system_area_info` VALUES ('165', '商丘市', null, '0', '3', '13', '1', '2016-05-23 03:21:58', '2016-05-23 03:21:58');
INSERT INTO `system_area_info` VALUES ('166', '济源市', null, '0', '3', '13', '1', '2016-05-23 03:22:05', '2016-05-23 03:22:05');
INSERT INTO `system_area_info` VALUES ('167', '焦作市', null, '0', '3', '13', '1', '2016-05-23 03:22:13', '2016-05-23 03:22:13');
INSERT INTO `system_area_info` VALUES ('168', '武汉市', null, '0', '3', '14', '1', '2016-05-23 03:22:24', '2016-05-23 03:22:24');
INSERT INTO `system_area_info` VALUES ('169', '神农架林区', null, '0', '3', '14', '1', '2016-05-23 03:22:38', '2016-05-23 03:22:38');
INSERT INTO `system_area_info` VALUES ('170', '恩施土家族苗族自', null, '0', '3', '14', '1', '2016-05-23 03:22:55', '2016-05-23 03:22:55');
INSERT INTO `system_area_info` VALUES ('171', '咸宁市', null, '0', '3', '14', '1', '2016-05-23 03:23:07', '2016-05-23 03:23:07');
INSERT INTO `system_area_info` VALUES ('172', '荆州市', null, '0', '3', '14', '1', '2016-05-23 03:23:15', '2016-05-23 03:23:15');
INSERT INTO `system_area_info` VALUES ('173', '宜昌市', null, '0', '3', '14', '1', '2016-05-23 03:23:21', '2016-05-23 03:23:21');
INSERT INTO `system_area_info` VALUES ('174', '仙桃市', null, '0', '3', '14', '1', '2016-05-23 03:23:27', '2016-05-23 03:23:27');
INSERT INTO `system_area_info` VALUES ('175', '黄石市', null, '0', '3', '14', '1', '2016-05-23 03:23:33', '2016-05-23 03:23:33');
INSERT INTO `system_area_info` VALUES ('176', '孝感市', null, '0', '3', '14', '1', '2016-05-23 03:23:41', '2016-05-23 03:23:41');
INSERT INTO `system_area_info` VALUES ('177', '襄阳市', null, '0', '3', '14', '1', '2016-05-23 03:23:48', '2016-05-23 03:23:48');
INSERT INTO `system_area_info` VALUES ('178', '天门市', null, '0', '3', '14', '1', '2016-05-23 03:23:54', '2016-05-23 03:23:54');
INSERT INTO `system_area_info` VALUES ('179', '鄂州市', null, '0', '3', '14', '1', '2016-05-23 03:24:00', '2016-05-23 03:24:00');
INSERT INTO `system_area_info` VALUES ('180', '荆门市', null, '0', '3', '14', '1', '2016-05-23 03:24:07', '2016-05-23 03:24:07');
INSERT INTO `system_area_info` VALUES ('181', '黄冈市', null, '0', '3', '14', '1', '2016-05-23 03:24:15', '2016-05-23 03:24:15');
INSERT INTO `system_area_info` VALUES ('182', '随州市', null, '0', '3', '14', '1', '2016-05-23 03:24:20', '2016-05-23 03:24:20');
INSERT INTO `system_area_info` VALUES ('183', '潜江市', null, '0', '3', '14', '1', '2016-05-23 03:24:26', '2016-05-23 03:24:26');
INSERT INTO `system_area_info` VALUES ('184', '十堰市', null, '0', '3', '14', '1', '2016-05-23 03:24:36', '2016-05-23 03:24:36');
INSERT INTO `system_area_info` VALUES ('185', '长沙市', null, '0', '3', '15', '1', '2016-05-23 03:24:46', '2016-05-23 03:24:46');
INSERT INTO `system_area_info` VALUES ('186', '张家界市', null, '0', '3', '15', '1', '2016-05-23 03:24:52', '2016-05-23 03:24:52');
INSERT INTO `system_area_info` VALUES ('187', '怀化市', null, '0', '3', '15', '1', '2016-05-23 03:25:00', '2016-05-23 03:25:00');
INSERT INTO `system_area_info` VALUES ('188', '邵阳市', null, '0', '3', '15', '1', '2016-05-23 03:25:06', '2016-05-23 03:25:06');
INSERT INTO `system_area_info` VALUES ('189', '益阳市', null, '0', '3', '15', '1', '2016-05-23 03:25:13', '2016-05-23 03:25:13');
INSERT INTO `system_area_info` VALUES ('190', '常德市', null, '0', '3', '15', '1', '2016-05-23 03:25:33', '2016-05-23 03:25:33');
INSERT INTO `system_area_info` VALUES ('191', '娄底市', null, '0', '3', '15', '1', '2016-05-23 03:25:38', '2016-05-23 03:25:38');
INSERT INTO `system_area_info` VALUES ('192', '永州市', null, '0', '3', '15', '1', '2016-05-23 03:25:44', '2016-05-23 03:25:44');
INSERT INTO `system_area_info` VALUES ('193', '岳阳市', null, '0', '3', '15', '1', '2016-05-23 03:25:51', '2016-05-23 03:25:51');
INSERT INTO `system_area_info` VALUES ('194', '株洲市', null, '0', '3', '15', '1', '2016-05-23 03:25:56', '2016-05-23 03:25:56');
INSERT INTO `system_area_info` VALUES ('195', '衡阳市', null, '0', '3', '15', '1', '2016-05-23 03:26:04', '2016-05-23 03:26:04');
INSERT INTO `system_area_info` VALUES ('196', '郴州市', null, '0', '3', '15', '1', '2016-05-23 03:27:28', '2016-05-23 03:27:28');
INSERT INTO `system_area_info` VALUES ('197', '湘西土家族苗族自', null, '0', '3', '15', '1', '2016-05-23 03:27:43', '2016-05-23 03:27:43');
INSERT INTO `system_area_info` VALUES ('198', '湘潭市', null, '0', '3', '15', '1', '2016-05-23 03:27:52', '2016-05-23 03:27:52');
INSERT INTO `system_area_info` VALUES ('199', '白城市', null, '0', '3', '16', '1', '2016-05-23 03:28:02', '2016-05-23 03:28:02');
INSERT INTO `system_area_info` VALUES ('200', '延边朝鲜族自治州', null, '0', '3', '16', '1', '2016-05-23 03:28:17', '2016-05-23 03:28:17');
INSERT INTO `system_area_info` VALUES ('201', '四平市', null, '0', '3', '16', '1', '2016-05-23 03:28:31', '2016-05-23 03:28:31');
INSERT INTO `system_area_info` VALUES ('202', '松原市', null, '0', '3', '16', '1', '2016-05-23 03:28:37', '2016-05-23 03:28:37');
INSERT INTO `system_area_info` VALUES ('203', '吉林市', null, '0', '3', '16', '1', '2016-05-23 03:28:44', '2016-05-23 03:28:44');
INSERT INTO `system_area_info` VALUES ('204', '白山市', null, '0', '3', '16', '1', '2016-05-23 03:28:51', '2016-05-23 03:28:51');
INSERT INTO `system_area_info` VALUES ('205', '长春市', null, '0', '3', '16', '1', '2016-05-23 03:28:57', '2016-05-23 03:28:57');
INSERT INTO `system_area_info` VALUES ('206', '通化市', null, '0', '3', '16', '1', '2016-05-23 03:29:03', '2016-05-23 03:29:03');
INSERT INTO `system_area_info` VALUES ('207', '辽源市', null, '0', '3', '16', '1', '2016-05-23 03:29:13', '2016-05-23 03:29:13');
INSERT INTO `system_area_info` VALUES ('208', '宿迁市', null, '0', '3', '17', '1', '2016-05-23 03:29:23', '2016-05-23 03:29:23');
INSERT INTO `system_area_info` VALUES ('209', '南京市', null, '0', '3', '17', '1', '2016-05-23 03:29:29', '2016-05-23 03:29:29');
INSERT INTO `system_area_info` VALUES ('210', '徐州市', null, '0', '3', '17', '1', '2016-05-23 03:29:34', '2016-05-23 03:29:34');
INSERT INTO `system_area_info` VALUES ('211', '淮安市', null, '0', '3', '17', '1', '2016-05-23 03:29:40', '2016-05-23 03:29:40');
INSERT INTO `system_area_info` VALUES ('212', '苏州市', null, '0', '3', '17', '1', '2016-05-23 03:29:46', '2016-05-23 03:29:46');
INSERT INTO `system_area_info` VALUES ('213', '连云港市', null, '0', '3', '17', '1', '2016-05-23 03:29:53', '2016-05-23 03:29:53');
INSERT INTO `system_area_info` VALUES ('214', '泰州市', null, '0', '3', '17', '1', '2016-05-23 03:30:02', '2016-05-23 03:30:02');
INSERT INTO `system_area_info` VALUES ('215', '常州市', null, '0', '3', '17', '1', '2016-05-23 03:30:15', '2016-05-23 03:30:15');
INSERT INTO `system_area_info` VALUES ('216', '扬州市', null, '0', '3', '17', '1', '2016-05-23 03:30:21', '2016-05-23 03:30:21');
INSERT INTO `system_area_info` VALUES ('217', '镇江市', null, '0', '3', '17', '1', '2016-05-23 03:30:28', '2016-05-23 03:30:28');
INSERT INTO `system_area_info` VALUES ('218', '盐城市', null, '0', '3', '17', '1', '2016-05-23 03:30:34', '2016-05-23 03:30:34');
INSERT INTO `system_area_info` VALUES ('219', '无锡市', null, '0', '3', '17', '1', '2016-05-23 03:30:41', '2016-05-23 03:30:41');
INSERT INTO `system_area_info` VALUES ('220', '南通市', null, '0', '3', '17', '1', '2016-05-23 03:32:29', '2016-05-23 03:32:29');
INSERT INTO `system_area_info` VALUES ('221', '赣州市', null, '0', '3', '18', '1', '2016-05-23 03:32:37', '2016-05-23 03:32:37');
INSERT INTO `system_area_info` VALUES ('222', '上饶市', null, '0', '3', '18', '1', '2016-05-23 03:32:43', '2016-05-23 03:32:43');
INSERT INTO `system_area_info` VALUES ('223', '南昌市', null, '0', '3', '18', '1', '2016-05-23 03:32:49', '2016-05-23 03:32:49');
INSERT INTO `system_area_info` VALUES ('224', '吉安市', null, '0', '3', '18', '1', '2016-05-23 03:33:00', '2016-05-23 03:33:00');
INSERT INTO `system_area_info` VALUES ('225', '新余市', null, '0', '3', '18', '1', '2016-05-23 03:33:06', '2016-05-23 03:33:06');
INSERT INTO `system_area_info` VALUES ('226', '九江市', null, '0', '3', '18', '1', '2016-05-23 03:33:12', '2016-05-23 03:33:12');
INSERT INTO `system_area_info` VALUES ('227', '抚州市', null, '0', '3', '18', '1', '2016-05-23 03:33:19', '2016-05-23 03:33:19');
INSERT INTO `system_area_info` VALUES ('228', '宜春市', null, '0', '3', '18', '1', '2016-05-23 03:33:25', '2016-05-23 03:33:25');
INSERT INTO `system_area_info` VALUES ('229', '萍乡市', null, '0', '3', '18', '1', '2016-05-23 03:33:37', '2016-05-23 03:33:37');
INSERT INTO `system_area_info` VALUES ('230', '景德镇市', null, '0', '3', '18', '1', '2016-05-23 03:33:43', '2016-05-23 03:33:43');
INSERT INTO `system_area_info` VALUES ('231', '鹰潭市', null, '0', '3', '18', '1', '2016-05-23 03:33:52', '2016-05-23 03:33:52');
INSERT INTO `system_area_info` VALUES ('232', '伊春市', null, '0', '3', '19', '1', '2016-05-23 03:34:03', '2016-05-23 03:34:03');
INSERT INTO `system_area_info` VALUES ('233', '双鸭山市', null, '0', '3', '19', '1', '2016-05-23 03:34:10', '2016-05-23 03:34:10');
INSERT INTO `system_area_info` VALUES ('234', '牡丹江市', null, '0', '3', '19', '1', '2016-05-23 03:34:18', '2016-05-23 03:34:18');
INSERT INTO `system_area_info` VALUES ('235', '佳木斯市', null, '0', '3', '19', '1', '2016-05-23 03:34:25', '2016-05-23 03:34:25');
INSERT INTO `system_area_info` VALUES ('236', '鸡西市', null, '0', '3', '19', '1', '2016-05-23 03:34:32', '2016-05-23 03:34:32');
INSERT INTO `system_area_info` VALUES ('237', '哈尔滨市', null, '0', '3', '19', '1', '2016-05-23 03:34:40', '2016-05-23 03:34:40');
INSERT INTO `system_area_info` VALUES ('238', '鹤岗市', null, '0', '3', '19', '1', '2016-05-23 03:34:46', '2016-05-23 03:34:46');
INSERT INTO `system_area_info` VALUES ('239', '绥化市', null, '0', '3', '19', '1', '2016-05-23 03:34:57', '2016-05-23 03:34:57');
INSERT INTO `system_area_info` VALUES ('240', '黑河市', null, '0', '3', '19', '1', '2016-05-23 03:35:04', '2016-05-23 03:35:04');
INSERT INTO `system_area_info` VALUES ('241', '大兴安岭地区', null, '0', '3', '19', '1', '2016-05-23 03:35:17', '2016-05-23 03:35:17');
INSERT INTO `system_area_info` VALUES ('242', '大庆市', null, '0', '3', '19', '1', '2016-05-23 03:35:23', '2016-05-23 03:35:23');
INSERT INTO `system_area_info` VALUES ('243', '齐齐哈尔市', null, '0', '3', '19', '1', '2016-05-23 03:35:31', '2016-05-23 03:35:31');
INSERT INTO `system_area_info` VALUES ('244', '七台河市', null, '0', '3', '19', '1', '2016-05-23 03:35:40', '2016-05-23 03:35:40');
INSERT INTO `system_area_info` VALUES ('245', '大连市', null, '0', '3', '20', '1', '2016-05-23 03:35:50', '2016-05-23 03:35:50');
INSERT INTO `system_area_info` VALUES ('246', '沈阳市', null, '0', '3', '20', '1', '2016-05-23 03:36:12', '2016-05-23 03:36:12');
INSERT INTO `system_area_info` VALUES ('247', '本溪市', null, '0', '3', '20', '1', '2016-05-23 03:36:17', '2016-05-23 03:36:17');
INSERT INTO `system_area_info` VALUES ('248', '铁岭市', null, '0', '3', '20', '1', '2016-05-23 03:36:25', '2016-05-23 03:36:25');
INSERT INTO `system_area_info` VALUES ('249', '营口市', null, '0', '3', '20', '1', '2016-05-23 03:36:31', '2016-05-23 03:36:31');
INSERT INTO `system_area_info` VALUES ('250', '鞍山市', null, '0', '3', '20', '1', '2016-05-23 03:36:36', '2016-05-23 03:36:36');
INSERT INTO `system_area_info` VALUES ('251', '辽阳市', null, '0', '3', '20', '1', '2016-05-23 03:36:42', '2016-05-23 03:36:42');
INSERT INTO `system_area_info` VALUES ('252', '葫芦岛市', null, '0', '3', '20', '1', '2016-05-23 03:36:48', '2016-05-23 03:36:48');
INSERT INTO `system_area_info` VALUES ('253', '盘锦市', null, '0', '3', '20', '1', '2016-05-23 03:36:55', '2016-05-23 03:36:55');
INSERT INTO `system_area_info` VALUES ('254', '锦州市', null, '0', '3', '20', '1', '2016-05-23 03:37:02', '2016-05-23 03:37:02');
INSERT INTO `system_area_info` VALUES ('255', '阜新市', null, '0', '3', '20', '1', '2016-05-23 03:37:13', '2016-05-23 03:37:13');
INSERT INTO `system_area_info` VALUES ('256', '丹东市', null, '0', '3', '20', '1', '2016-05-23 03:37:19', '2016-05-23 03:37:19');
INSERT INTO `system_area_info` VALUES ('257', '抚顺市', null, '0', '3', '20', '1', '2016-05-23 03:37:25', '2016-05-23 03:37:25');
INSERT INTO `system_area_info` VALUES ('258', '朝阳市', null, '0', '3', '20', '1', '2016-05-23 03:37:34', '2016-05-23 03:37:34');
INSERT INTO `system_area_info` VALUES ('259', '吴忠市', null, '0', '3', '21', '1', '2016-05-23 03:37:43', '2016-05-23 03:37:43');
INSERT INTO `system_area_info` VALUES ('260', '石嘴山市', null, '0', '3', '21', '1', '2016-05-23 03:38:01', '2016-05-23 03:38:01');
INSERT INTO `system_area_info` VALUES ('261', '固原市', null, '0', '3', '21', '1', '2016-05-23 03:38:06', '2016-05-23 03:38:06');
INSERT INTO `system_area_info` VALUES ('262', '银川市', null, '0', '3', '21', '1', '2016-05-23 03:38:12', '2016-05-23 03:38:12');
INSERT INTO `system_area_info` VALUES ('263', '中卫市', null, '0', '3', '21', '1', '2016-05-23 03:38:22', '2016-05-23 03:38:22');
INSERT INTO `system_area_info` VALUES ('264', '海西蒙古族藏族自', null, '0', '3', '22', '1', '2016-05-23 03:38:38', '2016-05-23 03:38:38');
INSERT INTO `system_area_info` VALUES ('265', '黄南藏族自治州', null, '0', '3', '22', '1', '2016-05-23 03:38:57', '2016-05-23 03:38:57');
INSERT INTO `system_area_info` VALUES ('266', '海东市', null, '0', '3', '22', '1', '2016-05-23 03:39:02', '2016-05-23 03:39:02');
INSERT INTO `system_area_info` VALUES ('267', '西宁市', null, '0', '3', '22', '1', '2016-05-23 03:39:07', '2016-05-23 03:39:07');
INSERT INTO `system_area_info` VALUES ('268', '海南藏族自治州', null, '0', '3', '22', '1', '2016-05-23 03:39:18', '2016-05-23 03:39:18');
INSERT INTO `system_area_info` VALUES ('269', '海北藏族自治州', null, '0', '3', '22', '1', '2016-05-23 03:39:28', '2016-05-23 03:39:28');
INSERT INTO `system_area_info` VALUES ('270', '玉树藏族自治州', null, '0', '3', '22', '1', '2016-05-23 03:39:39', '2016-05-23 03:39:39');
INSERT INTO `system_area_info` VALUES ('271', '果洛藏族自治州', null, '0', '3', '22', '1', '2016-05-23 03:39:55', '2016-05-23 03:39:55');
INSERT INTO `system_area_info` VALUES ('272', '聊城市', null, '0', '3', '23', '1', '2016-05-23 03:40:02', '2016-05-23 03:40:02');
INSERT INTO `system_area_info` VALUES ('273', '潍坊市', null, '0', '3', '23', '1', '2016-05-23 03:40:11', '2016-05-23 03:40:11');
INSERT INTO `system_area_info` VALUES ('274', '济宁市', null, '0', '3', '23', '1', '2016-05-23 03:40:16', '2016-05-23 03:40:16');
INSERT INTO `system_area_info` VALUES ('275', '泰安市', null, '0', '3', '23', '1', '2016-05-23 03:40:22', '2016-05-23 03:40:22');
INSERT INTO `system_area_info` VALUES ('276', '烟台市', null, '0', '3', '23', '1', '2016-05-23 03:40:28', '2016-05-23 03:40:28');
INSERT INTO `system_area_info` VALUES ('277', '德州市', null, '0', '3', '23', '1', '2016-05-23 03:40:33', '2016-05-23 03:40:33');
INSERT INTO `system_area_info` VALUES ('278', '青岛市', null, '0', '3', '23', '1', '2016-05-23 03:40:39', '2016-05-23 03:40:39');
INSERT INTO `system_area_info` VALUES ('279', '临沂市', null, '0', '3', '23', '1', '2016-05-23 03:42:27', '2016-05-23 03:42:27');
INSERT INTO `system_area_info` VALUES ('280', '东营市', null, '0', '3', '23', '1', '2016-05-23 03:42:33', '2016-05-23 03:42:33');
INSERT INTO `system_area_info` VALUES ('281', '济南市', null, '0', '3', '23', '1', '2016-05-23 03:42:39', '2016-05-23 03:42:39');
INSERT INTO `system_area_info` VALUES ('282', '滨州市', null, '0', '3', '23', '1', '2016-05-23 03:42:45', '2016-05-23 03:42:45');
INSERT INTO `system_area_info` VALUES ('283', '菏泽市', null, '0', '3', '23', '1', '2016-05-23 03:42:57', '2016-05-23 03:42:57');
INSERT INTO `system_area_info` VALUES ('284', '淄博市', null, '0', '3', '23', '1', '2016-05-23 03:43:57', '2016-05-23 03:43:57');
INSERT INTO `system_area_info` VALUES ('285', '日照市', null, '0', '3', '23', '1', '2016-05-23 03:44:04', '2016-05-23 03:44:04');
INSERT INTO `system_area_info` VALUES ('286', '威海市', null, '0', '3', '23', '1', '2016-05-23 03:44:09', '2016-05-23 03:44:09');
INSERT INTO `system_area_info` VALUES ('287', '枣庄市', null, '0', '3', '23', '1', '2016-05-23 03:44:14', '2016-05-23 03:44:14');
INSERT INTO `system_area_info` VALUES ('288', '莱芜市', null, '0', '3', '23', '1', '2016-05-23 03:44:31', '2016-05-23 03:44:31');
INSERT INTO `system_area_info` VALUES ('289', '忻州市', null, '0', '3', '24', '1', '2016-05-23 03:44:41', '2016-05-23 03:44:41');
INSERT INTO `system_area_info` VALUES ('290', '长治市', null, '0', '3', '24', '1', '2016-05-23 03:44:46', '2016-05-23 03:44:46');
INSERT INTO `system_area_info` VALUES ('291', '晋中市', null, '0', '3', '24', '1', '2016-05-23 03:44:53', '2016-05-23 03:44:53');
INSERT INTO `system_area_info` VALUES ('292', '大同市', null, '0', '3', '24', '1', '2016-05-23 03:44:58', '2016-05-23 03:44:58');
INSERT INTO `system_area_info` VALUES ('293', '阳泉市', null, '0', '3', '24', '1', '2016-05-23 03:45:06', '2016-05-23 03:45:06');
INSERT INTO `system_area_info` VALUES ('294', '朔州市', null, '0', '3', '24', '1', '2016-05-23 03:45:20', '2016-05-23 03:45:20');
INSERT INTO `system_area_info` VALUES ('295', '临汾市', null, '0', '3', '24', '1', '2016-05-23 03:45:26', '2016-05-23 03:45:26');
INSERT INTO `system_area_info` VALUES ('296', '太原市', null, '0', '3', '24', '1', '2016-05-23 03:45:33', '2016-05-23 03:45:33');
INSERT INTO `system_area_info` VALUES ('297', '吕梁市', null, '0', '3', '24', '1', '2016-05-23 03:45:39', '2016-05-23 03:45:39');
INSERT INTO `system_area_info` VALUES ('298', '运城市', null, '0', '3', '24', '1', '2016-05-23 03:45:45', '2016-05-23 03:45:45');
INSERT INTO `system_area_info` VALUES ('299', '晋城市', null, '0', '3', '24', '1', '2016-05-23 03:45:54', '2016-05-23 03:45:54');
INSERT INTO `system_area_info` VALUES ('300', '安康市', null, '0', '3', '25', '1', '2016-05-23 03:46:00', '2016-05-23 03:46:00');
INSERT INTO `system_area_info` VALUES ('301', '榆林市', null, '0', '3', '25', '1', '2016-05-23 03:46:08', '2016-05-23 03:46:08');
INSERT INTO `system_area_info` VALUES ('302', '渭南市', null, '0', '3', '25', '1', '2016-05-23 03:46:15', '2016-05-23 03:46:15');
INSERT INTO `system_area_info` VALUES ('303', '汉中市', null, '0', '3', '25', '1', '2016-05-23 03:46:22', '2016-05-23 03:46:22');
INSERT INTO `system_area_info` VALUES ('304', '宝鸡市', null, '0', '3', '25', '1', '2016-05-23 03:46:28', '2016-05-23 03:46:28');
INSERT INTO `system_area_info` VALUES ('305', '西安市', null, '0', '3', '25', '1', '2016-05-23 03:46:35', '2016-05-23 03:46:35');
INSERT INTO `system_area_info` VALUES ('306', '咸阳市', null, '0', '3', '25', '1', '2016-05-23 03:46:41', '2016-05-23 03:46:41');
INSERT INTO `system_area_info` VALUES ('307', '商洛市', null, '0', '3', '25', '1', '2016-05-23 03:46:48', '2016-05-23 03:46:48');
INSERT INTO `system_area_info` VALUES ('308', '延安市', null, '0', '3', '25', '1', '2016-05-23 03:46:56', '2016-05-23 03:46:56');
INSERT INTO `system_area_info` VALUES ('309', '铜川市', null, '0', '3', '25', '1', '2016-05-23 03:47:06', '2016-05-23 03:47:06');
INSERT INTO `system_area_info` VALUES ('310', '上海市', null, '0', '3', '26', '1', '2016-05-23 03:47:14', '2016-05-23 03:47:14');
INSERT INTO `system_area_info` VALUES ('311', '成都市', null, '0', '3', '27', '1', '2016-05-23 03:47:23', '2016-05-23 03:47:23');
INSERT INTO `system_area_info` VALUES ('312', '阿坝藏族羌族自治', null, '0', '3', '27', '1', '2016-05-23 03:48:49', '2016-05-23 03:48:49');
INSERT INTO `system_area_info` VALUES ('313', '广元市', null, '0', '3', '27', '1', '2016-05-23 03:48:56', '2016-05-23 03:48:56');
INSERT INTO `system_area_info` VALUES ('314', '乐山市', null, '0', '3', '27', '1', '2016-05-23 03:49:02', '2016-05-23 03:49:02');
INSERT INTO `system_area_info` VALUES ('315', '宜宾市', null, '0', '3', '27', '1', '2016-05-23 03:49:17', '2016-05-23 03:49:17');
INSERT INTO `system_area_info` VALUES ('316', '达州市', null, '0', '3', '27', '1', '2016-05-23 03:49:25', '2016-05-23 03:49:25');
INSERT INTO `system_area_info` VALUES ('317', '眉山市', null, '0', '3', '27', '1', '2016-05-23 03:49:32', '2016-05-23 03:49:32');
INSERT INTO `system_area_info` VALUES ('318', '绵阳市', null, '0', '3', '27', '1', '2016-05-23 03:49:38', '2016-05-23 03:49:38');
INSERT INTO `system_area_info` VALUES ('319', '自贡市', null, '0', '3', '27', '1', '2016-05-23 03:49:45', '2016-05-23 03:49:45');
INSERT INTO `system_area_info` VALUES ('320', '攀枝花市', null, '0', '3', '27', '1', '2016-05-23 03:49:52', '2016-05-23 03:49:52');
INSERT INTO `system_area_info` VALUES ('321', '德阳市', null, '0', '3', '27', '1', '2016-05-23 03:49:59', '2016-05-23 03:49:59');
INSERT INTO `system_area_info` VALUES ('322', '南充市', null, '0', '3', '27', '1', '2016-05-23 03:50:08', '2016-05-23 03:50:08');
INSERT INTO `system_area_info` VALUES ('323', '内江市', null, '0', '3', '27', '1', '2016-05-23 03:50:14', '2016-05-23 03:50:14');
INSERT INTO `system_area_info` VALUES ('324', '资阳市', null, '0', '3', '27', '1', '2016-05-23 03:50:20', '2016-05-23 03:50:20');
INSERT INTO `system_area_info` VALUES ('325', '巴中市', null, '0', '3', '27', '1', '2016-05-23 03:50:26', '2016-05-23 03:50:26');
INSERT INTO `system_area_info` VALUES ('326', '遂宁市', null, '0', '3', '27', '1', '2016-05-23 03:50:35', '2016-05-23 03:50:35');
INSERT INTO `system_area_info` VALUES ('327', '乌兰察布市', null, '0', '3', '28', '1', '2016-05-23 03:50:49', '2016-05-23 03:50:49');
INSERT INTO `system_area_info` VALUES ('328', '锡林郭勒盟', null, '0', '3', '28', '1', '2016-05-23 03:50:58', '2016-05-23 03:50:58');
INSERT INTO `system_area_info` VALUES ('329', '乌海市', null, '0', '3', '28', '1', '2016-05-23 03:51:04', '2016-05-23 03:51:04');
INSERT INTO `system_area_info` VALUES ('330', '赤峰市', null, '0', '3', '28', '1', '2016-05-23 03:51:09', '2016-05-23 03:51:09');
INSERT INTO `system_area_info` VALUES ('331', '包头市', null, '0', '3', '28', '1', '2016-05-23 03:51:16', '2016-05-23 03:51:16');
INSERT INTO `system_area_info` VALUES ('332', '呼伦贝尔市', null, '0', '3', '28', '1', '2016-05-23 03:51:33', '2016-05-23 03:51:33');
INSERT INTO `system_area_info` VALUES ('333', '通辽市', null, '0', '3', '28', '1', '2016-05-23 03:51:40', '2016-05-23 03:51:40');
INSERT INTO `system_area_info` VALUES ('334', '鄂尔多斯市', null, '0', '3', '28', '1', '2016-05-23 03:51:48', '2016-05-23 03:51:48');
INSERT INTO `system_area_info` VALUES ('335', '呼和浩特市', null, '0', '3', '28', '1', '2016-05-23 03:51:58', '2016-05-23 03:51:58');
INSERT INTO `system_area_info` VALUES ('336', '兴安盟', null, '0', '3', '28', '1', '2016-05-23 03:52:05', '2016-05-23 03:52:05');
INSERT INTO `system_area_info` VALUES ('337', '阿拉善盟', null, '0', '3', '28', '1', '2016-05-23 03:52:12', '2016-05-23 03:52:12');
INSERT INTO `system_area_info` VALUES ('338', '巴彦淖尔市', null, '0', '3', '28', '1', '2016-05-23 03:52:30', '2016-05-23 03:52:30');
INSERT INTO `system_area_info` VALUES ('339', '天津市', null, '0', '3', '29', '1', '2016-05-23 03:52:37', '2016-05-23 03:52:37');
INSERT INTO `system_area_info` VALUES ('340', '日喀则地区', null, '0', '3', '30', '1', '2016-05-23 03:52:57', '2016-05-23 03:52:57');
INSERT INTO `system_area_info` VALUES ('341', '林芝地区', null, '0', '3', '30', '1', '2016-05-23 03:53:04', '2016-05-23 03:53:04');
INSERT INTO `system_area_info` VALUES ('342', '拉萨市', null, '0', '3', '30', '1', '2016-05-23 03:53:09', '2016-05-23 03:53:09');
INSERT INTO `system_area_info` VALUES ('343', '山南地区', null, '0', '3', '30', '1', '2016-05-23 03:53:17', '2016-05-23 03:53:17');
INSERT INTO `system_area_info` VALUES ('344', '阿里地区', null, '0', '3', '30', '1', '2016-05-23 03:53:23', '2016-05-23 03:53:23');
INSERT INTO `system_area_info` VALUES ('345', '昌都地区', null, '0', '3', '30', '1', '2016-05-23 03:53:32', '2016-05-23 03:53:32');
INSERT INTO `system_area_info` VALUES ('346', '昌吉回族自治州', null, '0', '3', '31', '1', '2016-05-23 03:53:49', '2016-05-23 03:53:49');
INSERT INTO `system_area_info` VALUES ('347', '克孜勒苏柯尔克孜', null, '0', '3', '31', '1', '2016-05-23 03:54:17', '2016-05-23 03:54:17');
INSERT INTO `system_area_info` VALUES ('348', '阿克苏地区', null, '0', '3', '31', '1', '2016-05-23 03:54:24', '2016-05-23 03:54:24');
INSERT INTO `system_area_info` VALUES ('349', '哈密地区', null, '0', '3', '31', '1', '2016-05-23 03:54:29', '2016-05-23 03:54:29');
INSERT INTO `system_area_info` VALUES ('350', '巴音郭楞蒙古自治', null, '0', '3', '31', '1', '2016-05-23 03:54:49', '2016-05-23 03:54:49');
INSERT INTO `system_area_info` VALUES ('351', '伊犁哈萨克自治州', null, '0', '3', '31', '1', '2016-05-23 03:55:01', '2016-05-23 03:55:01');
INSERT INTO `system_area_info` VALUES ('352', '塔城地区', null, '0', '3', '31', '1', '2016-05-23 03:55:08', '2016-05-23 03:55:08');
INSERT INTO `system_area_info` VALUES ('353', '博尔塔拉蒙古自治', null, '0', '3', '31', '1', '2016-05-23 03:55:23', '2016-05-23 03:55:23');
INSERT INTO `system_area_info` VALUES ('354', '乌鲁木齐市', null, '0', '3', '31', '1', '2016-05-23 03:55:32', '2016-05-23 03:55:32');
INSERT INTO `system_area_info` VALUES ('355', '和田地区', null, '0', '3', '31', '1', '2016-05-23 03:55:41', '2016-05-23 03:55:41');
INSERT INTO `system_area_info` VALUES ('356', '喀什地区', null, '0', '3', '31', '1', '2016-05-23 03:55:48', '2016-05-23 03:55:48');
INSERT INTO `system_area_info` VALUES ('357', '吐鲁番地区', null, '0', '3', '31', '1', '2016-05-23 03:55:59', '2016-05-23 03:55:59');
INSERT INTO `system_area_info` VALUES ('358', '阿勒泰地区', null, '0', '3', '31', '1', '2016-05-23 03:56:11', '2016-05-23 03:56:11');
INSERT INTO `system_area_info` VALUES ('359', '克拉玛依市', null, '0', '3', '31', '1', '2016-05-23 03:56:19', '2016-05-23 03:56:19');
INSERT INTO `system_area_info` VALUES ('360', '图木舒克市', null, '0', '3', '31', '1', '2016-05-23 03:56:28', '2016-05-23 03:56:28');
INSERT INTO `system_area_info` VALUES ('361', '北屯市', null, '0', '3', '31', '1', '2016-05-23 03:56:33', '2016-05-23 03:56:33');
INSERT INTO `system_area_info` VALUES ('362', '阿拉尔市', null, '0', '3', '31', '1', '2016-05-23 03:56:45', '2016-05-23 03:56:45');
INSERT INTO `system_area_info` VALUES ('363', '五家渠市', null, '0', '3', '31', '1', '2016-05-23 03:56:52', '2016-05-23 03:56:52');
INSERT INTO `system_area_info` VALUES ('364', '石河子市', null, '0', '3', '31', '1', '2016-05-23 03:57:01', '2016-05-23 03:57:01');
INSERT INTO `system_area_info` VALUES ('365', '曲靖市', null, '0', '3', '32', '1', '2016-05-23 03:57:11', '2016-05-23 03:57:11');
INSERT INTO `system_area_info` VALUES ('366', '保山市', null, '0', '3', '32', '1', '2016-05-23 03:57:17', '2016-05-23 03:57:17');
INSERT INTO `system_area_info` VALUES ('367', '临沧市', null, '0', '3', '32', '1', '2016-05-23 03:57:23', '2016-05-23 03:57:23');
INSERT INTO `system_area_info` VALUES ('368', '玉溪市', null, '0', '3', '32', '1', '2016-05-23 03:57:28', '2016-05-23 03:57:28');
INSERT INTO `system_area_info` VALUES ('369', '大理白族自治州', null, '0', '3', '32', '1', '2016-05-23 03:57:38', '2016-05-23 03:57:38');
INSERT INTO `system_area_info` VALUES ('370', '普洱市', null, '0', '3', '32', '1', '2016-05-23 03:57:43', '2016-05-23 03:57:43');
INSERT INTO `system_area_info` VALUES ('371', '昆明市', null, '0', '3', '32', '1', '2016-05-23 03:57:48', '2016-05-23 03:57:48');
INSERT INTO `system_area_info` VALUES ('372', '红河哈尼族彝族自', null, '0', '3', '32', '1', '2016-05-23 03:57:59', '2016-05-23 03:57:59');
INSERT INTO `system_area_info` VALUES ('373', '楚雄彝族自治州', null, '0', '3', '32', '1', '2016-05-23 03:58:26', '2016-05-23 03:58:35');
INSERT INTO `system_area_info` VALUES ('374', '昭通市', null, '0', '3', '32', '1', '2016-05-23 03:58:47', '2016-05-23 03:58:47');
INSERT INTO `system_area_info` VALUES ('375', '文山壮族苗族自治', null, '0', '3', '32', '1', '2016-05-23 03:59:04', '2016-05-23 03:59:04');
INSERT INTO `system_area_info` VALUES ('376', '德宏傣族景颇族自', null, '0', '3', '32', '1', '2016-05-23 03:59:14', '2016-05-23 03:59:14');
INSERT INTO `system_area_info` VALUES ('377', '西双版纳傣族自治', null, '0', '3', '32', '1', '2016-05-23 03:59:26', '2016-05-23 03:59:26');
INSERT INTO `system_area_info` VALUES ('378', '丽江市', null, '0', '3', '32', '1', '2016-05-23 03:59:51', '2016-05-23 03:59:51');
INSERT INTO `system_area_info` VALUES ('379', '迪庆藏族自治州', null, '0', '3', '32', '1', '2016-05-23 04:00:03', '2016-05-23 04:00:03');
INSERT INTO `system_area_info` VALUES ('380', '温州市', null, '0', '3', '33', '1', '2016-05-23 04:00:09', '2016-05-23 04:00:09');
INSERT INTO `system_area_info` VALUES ('381', '宁波市', null, '0', '3', '33', '1', '2016-05-23 04:00:15', '2016-05-23 04:00:15');
INSERT INTO `system_area_info` VALUES ('382', '金华市', null, '0', '3', '33', '1', '2016-05-23 04:00:20', '2016-05-23 04:00:20');
INSERT INTO `system_area_info` VALUES ('383', '丽水市', null, '0', '3', '33', '1', '2016-05-23 04:00:25', '2016-05-23 04:00:25');
INSERT INTO `system_area_info` VALUES ('384', '嘉兴市', null, '0', '3', '33', '1', '2016-05-23 04:00:37', '2016-05-23 04:00:37');
INSERT INTO `system_area_info` VALUES ('385', '台州市', null, '0', '3', '33', '1', '2016-05-23 04:00:44', '2016-05-23 04:00:44');
INSERT INTO `system_area_info` VALUES ('386', '绍兴市', null, '0', '3', '33', '1', '2016-05-23 04:00:49', '2016-05-23 04:00:49');
INSERT INTO `system_area_info` VALUES ('387', '衢州市', null, '0', '3', '33', '1', '2016-05-23 04:01:29', '2016-05-23 04:01:29');
INSERT INTO `system_area_info` VALUES ('388', '杭州市', null, '0', '3', '33', '1', '2016-05-23 04:01:35', '2016-05-23 04:01:35');
INSERT INTO `system_area_info` VALUES ('389', '湖州市', null, '0', '3', '33', '1', '2016-05-23 04:01:41', '2016-05-23 04:01:41');
INSERT INTO `system_area_info` VALUES ('390', '舟山市', null, '0', '3', '33', '1', '2016-05-23 04:01:50', '2016-05-23 04:01:50');
INSERT INTO `system_area_info` VALUES ('391', '香港岛', null, '0', '3', '34', '1', '2016-05-23 04:02:00', '2016-05-23 04:02:00');
INSERT INTO `system_area_info` VALUES ('392', '台湾地区', null, '0', '3', '35', '1', '2016-05-23 04:02:22', '2016-05-23 04:02:22');

-- ----------------------------
-- Table structure for system_params_info
-- ----------------------------
DROP TABLE IF EXISTS `system_params_info`;
CREATE TABLE `system_params_info` (
  `system_params_id` int(4) unsigned NOT NULL AUTO_INCREMENT,
  `param_key` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `param_value` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `desc` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`system_params_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of system_params_info
-- ----------------------------
INSERT INTO `system_params_info` VALUES ('1', 'FLOW_RECHARGE_SWITCH', '2', '流量充值开关：1-未开启 2-debug 3-release', '1', '2016-05-25 04:15:39', '2016-05-27 09:35:25');
INSERT INTO `system_params_info` VALUES ('2', 'MEIBO_STAGE_RATE', '0.2', '镁播平台收取扣点', '1', '2016-05-31 01:19:10', '2016-05-31 01:34:48');

-- ----------------------------
-- Table structure for system_platform_info
-- ----------------------------
DROP TABLE IF EXISTS `system_platform_info`;
CREATE TABLE `system_platform_info` (
  `platform_id` int(4) unsigned NOT NULL AUTO_INCREMENT,
  `platform_name` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `explain` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`platform_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of system_platform_info
-- ----------------------------
INSERT INTO `system_platform_info` VALUES ('1', '云信短信平台', '短信通道', '1', '2016-05-24 16:30:23', '2016-05-24 16:30:23');
INSERT INTO `system_platform_info` VALUES ('2', '云信流量平台', '流量充值通道', '1', '2016-05-24 16:30:39', '2016-05-24 16:30:39');
INSERT INTO `system_platform_info` VALUES ('3', '支付宝转账', '转账充值', '1', '2016-05-30 00:05:59', '2016-05-30 03:38:49');

-- ----------------------------
-- Table structure for system_trans_dtl
-- ----------------------------
DROP TABLE IF EXISTS `system_trans_dtl`;
CREATE TABLE `system_trans_dtl` (
  `trans_detail_id` int(16) unsigned NOT NULL AUTO_INCREMENT,
  `trans_date` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `trans_time` varchar(6) COLLATE utf8_unicode_ci NOT NULL,
  `trans_amount` decimal(12,2) NOT NULL,
  `trans_status` tinyint(1) NOT NULL COMMENT '交易状态：1-初始状态 2-交易成功 3-交易失败',
  `trans_type` tinyint(2) NOT NULL COMMENT '交易类型：1-新闻媒体',
  `trans_code` tinyint(4) NOT NULL COMMENT '交易编码：1-消费 2-充值',
  `member_id` int(10) NOT NULL,
  `order_id` int(10) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`trans_detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of system_trans_dtl
-- ----------------------------
INSERT INTO `system_trans_dtl` VALUES ('1', '20160518', '022806', '1101.00', '1', '1', '1', '12', '3', '2016-05-18 02:28:06', '2016-05-18 02:28:06');
INSERT INTO `system_trans_dtl` VALUES ('2', '20160522', '054703', '0.00', '1', '1', '1', '12', '3', '2016-05-22 05:47:03', '2016-05-22 05:47:03');
INSERT INTO `system_trans_dtl` VALUES ('3', '20160522', '054746', '0.00', '1', '1', '1', '12', '4', '2016-05-22 05:47:46', '2016-05-22 05:47:46');
INSERT INTO `system_trans_dtl` VALUES ('4', '20160522', '054954', '0.00', '1', '1', '1', '12', '5', '2016-05-22 05:49:57', '2016-05-22 05:49:57');
INSERT INTO `system_trans_dtl` VALUES ('5', '20160522', '055320', '9999.00', '1', '1', '1', '12', '6', '2016-05-22 05:53:20', '2016-05-22 05:53:20');
INSERT INTO `system_trans_dtl` VALUES ('6', '20160522', '060240', '9999.00', '1', '1', '1', '12', '7', '2016-05-22 06:02:40', '2016-05-22 06:02:40');
INSERT INTO `system_trans_dtl` VALUES ('7', '20160525', '015931', '1101.00', '1', '1', '1', '12', '4', '2016-05-25 01:59:31', '2016-05-25 01:59:31');
INSERT INTO `system_trans_dtl` VALUES ('8', '20160525', '020116', '1101.00', '1', '1', '1', '12', '5', '2016-05-25 02:01:16', '2016-05-25 02:01:16');
INSERT INTO `system_trans_dtl` VALUES ('9', '20160527', '095245', '3.00', '1', '5', '1', '12', '1', '2016-05-27 09:52:45', '2016-05-27 09:52:45');
INSERT INTO `system_trans_dtl` VALUES ('10', '20160527', '095311', '6.00', '1', '5', '1', '12', '2', '2016-05-27 09:53:11', '2016-05-27 09:53:11');
INSERT INTO `system_trans_dtl` VALUES ('11', '20160530', '005113', '3.00', '1', '5', '1', '12', '3', '2016-05-30 00:51:13', '2016-05-30 00:51:13');
INSERT INTO `system_trans_dtl` VALUES ('12', '20160530', '005956', '3.00', '1', '5', '1', '12', '4', '2016-05-30 00:59:56', '2016-05-30 00:59:56');
INSERT INTO `system_trans_dtl` VALUES ('13', '20160531', '225648', '1000.00', '1', '7', '2', '12', '2', '2016-05-31 22:56:48', '2016-05-31 22:56:48');
INSERT INTO `system_trans_dtl` VALUES ('14', '20160531', '235019', '1000.00', '1', '7', '2', '12', '2', '2016-05-31 23:50:19', '2016-05-31 23:50:19');
INSERT INTO `system_trans_dtl` VALUES ('15', '20160531', '235526', '10000.00', '1', '7', '2', '12', '3', '2016-05-31 23:55:26', '2016-05-31 23:55:26');
