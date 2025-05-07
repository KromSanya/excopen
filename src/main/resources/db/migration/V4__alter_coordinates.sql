ALTER TABLE coordinates
    ALTER COLUMN zoom TYPE DOUBLE PRECISION;

-- 1. Удаляем старые столбцы 'places' и 'topics' (если они есть)
ALTER TABLE description
DROP COLUMN IF EXISTS places,
DROP COLUMN IF EXISTS topics;

-- 2. Добавляем новые столбцы 'places' и 'topics' с типом TEXT[] (массивы строк)
ALTER TABLE description
ADD COLUMN places TEXT[], -- Массив строк для мест
ADD COLUMN topics TEXT[]; -- Массив строк для тем