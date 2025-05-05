-- Убираем NOT NULL с колонки contacts_phone, если ограничение существует
DO $$
BEGIN
    IF EXISTS (
        SELECT 1
        FROM information_schema.columns
        WHERE table_name = 'users'
          AND column_name = 'contacts_phone'
          AND is_nullable = 'NO'
    ) THEN
        EXECUTE 'ALTER TABLE users ALTER COLUMN contacts_phone DROP NOT NULL';
        RAISE NOTICE 'NOT NULL constraint removed from contacts_phone';
    ELSE
        RAISE NOTICE 'contacts_phone already allows NULL values';
    END IF;
END $$;