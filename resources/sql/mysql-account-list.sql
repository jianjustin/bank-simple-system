CREATE TABLE `account_list` (
  `PK_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `ACCOUNT_LIST_CODE` varchar(50) NOT NULL DEFAULT '' COMMENT '账户流水编号',
  `ACCOUNT_LIST_IN_CODE` varchar(50) DEFAULT '''''' COMMENT '流水入金账号',
  `ACCOUNT_LIST_OUT_CODE` varchar(50) DEFAULT '''''' COMMENT '流水出金账号',
  `ACCOUNT_LIST_TYPE` varchar(10) NOT NULL DEFAULT '0' COMMENT '流水类型(0-存款；1-取款；2-转账)',
  `ACCOUNT_LIST_MONEY` varchar(50) DEFAULT '' COMMENT '账户流水金额',
  `ACCOUNT_LIST_STATUS` varchar(10) NOT NULL DEFAULT '0' COMMENT '账户流水状态(0-创建；1-处理中；2-完成)',
  `ACCOUNT_LIST_CREATE_DATE` datetime NOT NULL COMMENT '账户流水创建时间',
  `ACCOUNT_LIST_UPDATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '账户流水修改时间',
  PRIMARY KEY (`PK_ID`),
  KEY `ACCOUNT_LIST_CODE` (`ACCOUNT_LIST_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;