CREATE TABLE STOREROOMS(
  S_ID           uuid NOT NULL,
  S_NAME         VARCHAR(25) NOT NULL,
  CONSTRAINT PK_STOREROOMS
  PRIMARY KEY (S_ID),
  CONSTRAINT UQ_STOREROOMS_NAME
  UNIQUE (S_NAME)
);
COMMENT ON TABLE STOREROOMS
IS 'Storeroom';
COMMENT ON COLUMN STOREROOMS.S_ID
IS 'Private storeroom identifier';
COMMENT ON COLUMN STOREROOMS.S_NAME
IS 'Name of the storeroom';