--
-- Add Creation and modification timestamp to JOBAPPLICATION Table
--

-- Warning: This operation can lock this table some seconds
ALTER TABLE PRODUCTS
ADD P_CREATION TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
ADD P_MODIFICATION TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW();

COMMENT ON COLUMN PRODUCTS.P_CREATION
IS 'Time instant when it is created';

COMMENT ON COLUMN PRODUCTS.P_MODIFICATION
IS 'Instant of the last update';