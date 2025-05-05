BEGIN;

-- 1) Удаляем старые текстовые колонки (если они есть)
ALTER TABLE description
  DROP COLUMN IF EXISTS places,
  DROP COLUMN IF EXISTS topics;

-- 2) Добавляем новые массивные колонки
ALTER TABLE description
  ADD COLUMN places text[] NOT NULL DEFAULT '{}'::text[],
  ADD COLUMN topics text[] NOT NULL DEFAULT '{}'::text[];

COMMIT;