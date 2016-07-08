-- ���ӽ�ɫID �����̿ͻ�
ALTER TABLE `member_info`
MODIFY COLUMN `role_id`  tinyint(2) NOT NULL DEFAULT 1 COMMENT '�û���ɫ��0-����Ա 1-��ͨ�û� 2-�����̿ͻ�' AFTER `member_type`;

-- �����̿ͻ���
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

