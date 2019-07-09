--判断这张表是否存在，若存在，则跳过创建表操作
CREATE TABLE IF NOT EXISTS `t_order`
  `id` int(40) NOT NULL,
  `context` varchar(255) default NULL,
  `message_id` int(255) default NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
