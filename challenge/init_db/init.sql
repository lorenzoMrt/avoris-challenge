CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TABLE IF NOT EXISTS searches (
    id INT PRIMARY KEY NOT NULL,
    search_id UUID NOT NULL,
    hotel_id TEXT NOT NULL,
    check_in DATE NOT NULL,
    check_out DATE NOT NULL,
    ages JSONB NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_search_id ON searches(search_id);
