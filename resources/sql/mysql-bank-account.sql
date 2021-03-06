CREATE TABLE `bank_account` (
  `PK_ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `BANK_ACCOUNT_CODE` varchar(50) NOT NULL DEFAULT '' COMMENT '账户编号',
  `BANK_ACCOUNT_CARD` varchar(200) NOT NULL DEFAULT '' COMMENT '账户银行卡号',
  `BANK_ACCOUNT_PASSWORD` varchar(200) NOT NULL DEFAULT '' COMMENT '账户密码',
  `BANK_ACCOUNT_NAME` varchar(200) NOT NULL DEFAULT '' COMMENT '账户名称',
  `BANK_ACCOUNT_ID_CARD` varchar(200) NOT NULL DEFAULT '' COMMENT '储户身份证号码',
  `BANK_ACCOUNT_MONEY` varchar(50) NOT NULL DEFAULT '0.00' COMMENT '账户余额',
  `BANK_ACCOUNT_CREATE_TIME` datetime DEFAULT NULL COMMENT '账户创建时间',
  `BANK_ACCOUNT_UPDATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '账户创建时间',
  PRIMARY KEY (`PK_ID`),
  KEY `BANK_ACCOUNT_CODE` (`BANK_ACCOUNT_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;