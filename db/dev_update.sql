-- ���ӽ�ɫID �����̿ͻ�
ALTER TABLE `member_info`
MODIFY COLUMN `role_id`  tinyint(2) NOT NULL DEFAULT 1 COMMENT '�û���ɫ��0-����Ա 1-��ͨ�û� 2-�����̿ͻ�' AFTER `member_type`;

-- �����̿ͻ���
CREATE TABLE `agent_customer` (
  `agent_customer_id` int(10) NOT NULL COMMENT '�����̿ͻ�id',
  `member_id` int(10) NOT NULL COMMENT '��ԱID',
  `customer_name` varchar(20) NOT NULL COMMENT '�ͻ�����',
  `agent_rate` decimal(6,2) NOT NULL COMMENT '�۵���',
  `total_consume` decimal(16,2) NOT NULL DEFAULT '0.00' COMMENT '�ͻ��ۼ�����',
  `total_income` decimal(16,2) NOT NULL DEFAULT '0.00' COMMENT '�������ۼ�����',
  `agent_member_id` int(10) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '���ñ�־λ��0-ͣ�� 1-����',
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`agent_customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


