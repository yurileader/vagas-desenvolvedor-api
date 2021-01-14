CREATE TABLE CANDIDATO (
    ID  INT NOT NULL AUTO_INCREMENT,
    NOME VARCHAR(70) NOT NULL,
    EMAIL VARCHAR (100) NOT NULL,
    TELEFONE VARCHAR(20),
    DATA_NASCIMENTO DATE NOT NULL,
    URL_LINKEDIN VARCHAR (100),
    URL_GITHUB VARCHAR(100),
    CONSTRAINT PK_CANDIDATO PRIMARY KEY ( ID )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE TECNOLOGIA (
    ID INT NOT NULL AUTO_INCREMENT,
    NOME VARCHAR(20) NOT NULL,
    CONSTRAINT PK_TECNOLOGIA_ID PRIMARY KEY ( ID )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE CANDIDATO_TECNOLOGIA (
    ID_CANDIDATO INT NOT NULL,
    ID_TECNOLOGIA INT NOT NULL,
    PRIMARY KEY (ID_CANDIDATO, ID_TECNOLOGIA),

    CONSTRAINT FK_CANDIDATO_ID FOREIGN KEY ( ID_CANDIDATO ) REFERENCES TECNOLOGIA ( ID ),
    CONSTRAINT FK_TECNOLOGIA_ID FOREIGN KEY ( ID_TECNOLOGIA ) REFERENCES TECNOLOGIA ( ID )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
