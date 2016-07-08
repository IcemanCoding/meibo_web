-- 增加角色ID 代理商客户
ALTER TABLE `member_info`
MODIFY COLUMN `role_id`  tinyint(2) NOT NULL DEFAULT 1 COMMENT '用户角色：0-管理员 1-普通用户 2-代理商客户' AFTER `member_type`;

-- 代理商客户表
CREATE TABLE `agent_customer` (
  `agent_customer_id` int(10) NOT NULL COMMENT '代理商客户id',
  `member_id` int(10) NOT NULL COMMENT '会员ID',
  `customer_name` varchar(20) NOT NULL COMMENT '客户名称',
  `agent_rate` decimal(6,2) NOT NULL COMMENT '扣点额度',
  `total_consume` decimal(16,2) NOT NULL DEFAULT '0.00' COMMENT '客户累计消费',
  `total_income` decimal(16,2) NOT NULL DEFAULT '0.00' COMMENT '代理商累计收益',
  `agent_member_id` int(10) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '启用标志位：0-停用 1-启用',
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`agent_customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


