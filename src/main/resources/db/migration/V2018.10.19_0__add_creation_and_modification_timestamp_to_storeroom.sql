--
-- Add Creation and modification timestamp to JOBAPPLICATION Table
--

-- Warning: This operation can lock this table some seconds
ALTER TABLE STOREROOMS
ADD S_CREATION TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
ADD S_MODIFICATION TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW();

COMMENT ON COLUMN STOREROOMS.S_CREATION
IS 'Time instant when it is created';

COMMENT ON COLUMN STOREROOMS.S_MODIFICATION
IS 'Instant of the last update';