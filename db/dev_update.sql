-- 增加角色ID 代理商客户
ALTER TABLE `member_info`
MODIFY COLUMN `role_id`  tinyint(2) NOT NULL DEFAULT 1 COMMENT '用户角色：0-管理员 1-普通用户 2-代理商客户' AFTER `member_type`;

-- 代理商客户表
CREATE TABLE `agents_customer` (
  `agents_customer_id` int(10) NOT NULL,
  `member_id` int(10) NOT NULL,
  `customer_name` varchar(20) NOT NULL,
  `agents_rate` decimal(6,2) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`agents_customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

