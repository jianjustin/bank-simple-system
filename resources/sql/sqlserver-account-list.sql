CREATE TABLE bankSystemDb.dbo.account_list
(
    PK_ID bigint NOT NULL,
    ACCOUNT_LIST_CODE varchar(50) PRIMARY KEY NOT NULL,
    ACCOUNT_LIST_IN_CODE varchar(50),
    ACCOUNT_LIST_OUT_CODE varchar(50),
    ACCOUNT_LIST_TYPE varchar(10) DEFAULT 0 NOT NULL,
    ACCOUNT_LIST_MONEY varchar(50) NOT NULL,
    ACCOUNT_LIST_STATUS varchar(10) DEFAULT 0,
    ACCOUNT_LIST_CREATE_DATE DATETIME DEFAULT (GETDATE()),
    ACCOUNT_LIST_UPDATE_DATE timestamp
)
CREATE UNIQUE INDEX account_list_PK_ID_uindex ON bankSystemDb.dbo.account_list (PK_ID)
EXEC sp_addextendedproperty 'MS_Description', '账户流水', 'SCHEMA', 'dbo', 'TABLE', 'account_list'
EXEC sp_addextendedproperty 'MS_Description', '主键ID', 'SCHEMA', 'dbo', 'TABLE', 'account_list', 'COLUMN', 'PK_ID'
EXEC sp_addextendedproperty 'MS_Description', '流水编号', 'SCHEMA', 'dbo', 'TABLE', 'account_list', 'COLUMN', 'ACCOUNT_LIST_CODE'
EXEC sp_addextendedproperty 'MS_Description', '入金账户编号', 'SCHEMA', 'dbo', 'TABLE', 'account_list', 'COLUMN', 'ACCOUNT_LIST_IN_CODE'
EXEC sp_addextendedproperty 'MS_Description', '出金账户编号', 'SCHEMA', 'dbo', 'TABLE', 'account_list', 'COLUMN', 'ACCOUNT_LIST_OUT_CODE'
EXEC sp_addextendedproperty 'MS_Description', '流水类型(0-存款；1-取款；2-转账)', 'SCHEMA', 'dbo', 'TABLE', 'account_list', 'COLUMN', 'ACCOUNT_LIST_TYPE'
EXEC sp_addextendedproperty 'MS_Description', '流水金额', 'SCHEMA', 'dbo', 'TABLE', 'account_list', 'COLUMN', 'ACCOUNT_LIST_MONEY'
EXEC sp_addextendedproperty 'MS_Description', '流水状态(0-创建；1-处理中；2-完成)', 'SCHEMA', 'dbo', 'TABLE', 'account_list', 'COLUMN', 'ACCOUNT_LIST_STATUS'
EXEC sp_addextendedproperty 'MS_Description', '流水创建时间', 'SCHEMA', 'dbo', 'TABLE', 'account_list', 'COLUMN', 'ACCOUNT_LIST_CREATE_DATE'
EXEC sp_addextendedproperty 'MS_Description', '流水最后修改时间', 'SCHEMA', 'dbo', 'TABLE', 'account_list', 'COLUMN', 'ACCOUNT_LIST_UPDATE_DATE'