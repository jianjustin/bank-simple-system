CREATE TABLE bankSystemDb.dbo.bank_account
(
    PK_ID BIGINT NOT NULL identity(1,1),
    BANK_ACCOUNT_CODE varchar(50) PRIMARY KEY NOT NULL,
    BANK_ACCOUNT_CARD varchar(200) NOT NULL,
    BANK_ACCOUNT_PASSWORD varchar(200) NOT NULL,
    BANK_ACCOUNT_NAME varchar(200) DEFAULT '',
    BANK_ACCOUNT_ID_CARD varchar(200) DEFAULT '',
    BANK_ACCOUNT_MONEY varchar(50) DEFAULT 0.00,
    BANK_ACCOUNT_CREATE_TIME DATETIME DEFAULT (getdate()),
    BANK_ACCOUNT_UPDATE_TIME timestamp
)
CREATE UNIQUE INDEX bank_account_PK_ID_uindex ON bankSystemDb.dbo.bank_account (PK_ID)
EXEC sp_addextendedproperty 'MS_Description', '银行账户', 'SCHEMA', 'dbo', 'TABLE', 'bank_account'
EXEC sp_addextendedproperty 'MS_Description', '主键ID', 'SCHEMA', 'dbo', 'TABLE', 'bank_account', 'COLUMN', 'PK_ID'
EXEC sp_addextendedproperty 'MS_Description', '账户编号(用于跟踪流水)', 'SCHEMA', 'dbo', 'TABLE', 'bank_account', 'COLUMN', 'BANK_ACCOUNT_CODE'
EXEC sp_addextendedproperty 'MS_Description', '账户卡号', 'SCHEMA', 'dbo', 'TABLE', 'bank_account', 'COLUMN', 'BANK_ACCOUNT_CARD'
EXEC sp_addextendedproperty 'MS_Description', '账户密码', 'SCHEMA', 'dbo', 'TABLE', 'bank_account', 'COLUMN', 'BANK_ACCOUNT_PASSWORD'
EXEC sp_addextendedproperty 'MS_Description', '储户名称', 'SCHEMA', 'dbo', 'TABLE', 'bank_account', 'COLUMN', 'BANK_ACCOUNT_NAME'
EXEC sp_addextendedproperty 'MS_Description', '身份证号码', 'SCHEMA', 'dbo', 'TABLE', 'bank_account', 'COLUMN', 'BANK_ACCOUNT_ID_CARD'
EXEC sp_addextendedproperty 'MS_Description', '账户余额', 'SCHEMA', 'dbo', 'TABLE', 'bank_account', 'COLUMN', 'BANK_ACCOUNT_MONEY'
EXEC sp_addextendedproperty 'MS_Description', '账户创建时间', 'SCHEMA', 'dbo', 'TABLE', 'bank_account', 'COLUMN', 'BANK_ACCOUNT_CREATE_TIME'
EXEC sp_addextendedproperty 'MS_Description', '账户最后修改时间', 'SCHEMA', 'dbo', 'TABLE', 'bank_account', 'COLUMN', 'BANK_ACCOUNT_UPDATE_TIME'


