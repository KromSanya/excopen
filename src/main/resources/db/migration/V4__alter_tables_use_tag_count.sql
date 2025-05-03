-- Для таблицы users
ALTER TABLE users
    ALTER COLUMN preferences_vector TYPE vector(${tagCount}),
    ALTER COLUMN second_vector TYPE vector(${tagCount});

-- Для таблицы tours
ALTER TABLE tours
    ALTER COLUMN vector_representation TYPE vector(${tagCount});