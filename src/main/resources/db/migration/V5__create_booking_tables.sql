-- Таблица бронируемых слотов для туров
CREATE TABLE booking_slots (
    id BIGSERIAL PRIMARY KEY,
    tour_id BIGINT NOT NULL REFERENCES tours(id) ON DELETE CASCADE,
    date_time TIMESTAMP NOT NULL,
    available_slots INTEGER NOT NULL,
    max_participants INTEGER NOT NULL
);

-- Таблица бронирований пользователей
CREATE TABLE bookings (
    id BIGSERIAL PRIMARY KEY,
    booking_slot_id BIGINT NOT NULL REFERENCES booking_slots(id) ON DELETE CASCADE,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    participants INTEGER NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

-- Таблица расписания туров (для автогенерации слотов)
CREATE TABLE tour_schedules (
    id BIGSERIAL PRIMARY KEY,
    tour_id BIGINT NOT NULL REFERENCES tours(id) ON DELETE CASCADE,
    start_date DATE NOT NULL,
    end_date DATE,
    time TIME NOT NULL,
    repeat_interval INTEGER NOT NULL DEFAULT 1,
    max_participants INTEGER NOT NULL
);
