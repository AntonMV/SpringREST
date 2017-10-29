DROP TABLE IF EXISTS CONTACT;
CREATE TABLE CONTACT (
ID INT NOT NULL AUTO_INCREMENT
, FIRST_NAМE VARCHAR(60) NOT NULL
, LAST_NAМE VARCHAR(40) NOT NULL
, BIRTH_DATE DATE
, VERSION INT NOT NULL DEFAULT 0
, UNIQUE UQ_CONTACT_l (FIRST_NAМE, LAST_NAМE)
, PRIMARY KEY(ID)
);