DO $$
BEGIN
    -- Проверка существования столбца перед переименованием
    IF EXISTS (
        SELECT 1 FROM information_schema.columns
        WHERE table_name = 'bookings' AND column_name = 'booking_slot_id'
    ) THEN
        EXECUTE 'ALTER TABLE bookings RENAME COLUMN booking_slot_id TO tour_id';
    END IF;

    -- Проверка существования таблицы и столбца перед добавлением
    IF EXISTS (
        SELECT 1 FROM information_schema.tables
        WHERE table_name = 'tours'
    ) THEN
        IF NOT EXISTS (
            SELECT 1 FROM information_schema.columns
            WHERE table_name = 'tours' AND column_name = 'free_seats'
        ) THEN
            EXECUTE 'ALTER TABLE tours ADD COLUMN free_seats INTEGER NOT NULL DEFAULT 0';
        END IF;
    END IF;
END $$;

ALTER TABLE bookings
DROP CONSTRAINT IF EXISTS bookings_booking_slot_id_fkey;

ALTER TABLE bookings
ADD CONSTRAINT fk_bookings_tour_id
FOREIGN KEY (tour_id) REFERENCES tours(id);