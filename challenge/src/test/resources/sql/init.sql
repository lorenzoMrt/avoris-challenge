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


INSERT INTO searches (id,search_id,hotel_id,check_in,check_out,ages) VALUES
	 (1,'019109d4-2c3b-7743-b287-d1d67756e674','123aBc','2023-12-29','2023-12-31','[30,21,15]'),
	 (2,'01910a57-e4ed-77ea-a1b8-a055e920d53d','123aBc','2023-12-29','2023-12-31','[30,21,15]');
