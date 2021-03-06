
CREATE TABLE GSU_USER(
	GSU_USR_ID			INT				NOT NULL PRIMARY KEY,
	GSU_USR_NOMBRE		VARCHAR(128)	NOT NULL,
	GSU_USR_APELLIDO	VARCHAR(200)	NOT NULL,
	GSU_USR_LOGIN		VARCHAR(32)		NOT NULL,
	GSU_USR_PASSWORD	VARCHAR(200)	NOT NULL,
	GSU_USR_DATE		TIMESTAMP		NOT NULL,
	GSU_USR_EMAIL		VARCHAR(64)		NOT NULL,
	GSU_USR_ESTADO		CHAR(1)			NOT NULL
);

CREATE TABLE GSU_ADDRESS(
	GSU_USR_ID			INT				NOT NULL,
	GSU_ADR_ID			INT				NOT NULL,
	GSU_ADR_ADDRESS		VARCHAR(256)	NOT NULL,
	GSU_ADR_TYPE		CHAR(1)			NOT NULL,
	GSU_ADR_MAIN		INT				NOT NULL,
	CONSTRAINT PRIMARY KEY(GSU_USR_ID, GSU_ADR_ID)
);

ALTER TABLE GSU_ADDRESS ADD CONSTRAINT GSU_ADDRESS_FK01 FOREIGN KEY(GSU_USR_ID) REFERENCES GSU_USER(GSU_USR_ID);

CREATE TABLE GSU_SESSION(
	GSU_USR_ID			INT,
	GSU_SES_ID			INT,
	GSU_SES_DATE		TIMESTAMP,
	GSU_SES_IP			VARCHAR(128),
	GSU_SES_TOKEN		VARCHAR(256),
	GSU_SES_END_DATE	TIMESTAMP,
	CONSTRAINT PRIMARY KEY(GSU_USR_ID, GSU_SES_ID)
);

ALTER TABLE GSU_SESSION ADD CONSTRAINT GSU_SESSION_FK01 FOREIGN KEY(GSU_USR_ID) REFERENCES GSU_USER(GSU_USR_ID);
