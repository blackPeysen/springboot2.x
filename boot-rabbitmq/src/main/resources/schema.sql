--判断这张表是否存在，若存在，则跳过创建表操作
CREATE TABLE IF NOT EXISTS `t_order` (
  `id` int(10) unsigned PRIMARY KEY AUTO_INCREMENT,
  `context` varchar(100) NOT NULL,
  `message_id` varchar(128) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `t_message` (
  `message_id` varchar(128) NOT NULL,
  `message_context` varchar(4000) NOT NULL,
  `try_count` int(4) default '0',       --重试次数
  `status` varchar(10) default '',      --消息投递状态（0：投递中；1：投递成功；2：投递失败）
  `next_retry` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00', --下一次投递时间
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00', --创建时间
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00', --更新时间
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

