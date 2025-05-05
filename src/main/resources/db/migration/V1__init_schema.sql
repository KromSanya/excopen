-- V1__init_schema.sql

-- ====================================================================================
-- 0) Extensions
-- ====================================================================================
CREATE EXTENSION IF NOT EXISTS postgis;
CREATE EXTENSION IF NOT EXISTS vector;

-- ====================================================================================
-- 1) Users
-- ====================================================================================
CREATE TABLE IF NOT EXISTS users (
    id                BIGSERIAL PRIMARY KEY,
    google_id         VARCHAR(255),
    name              VARCHAR(255),
    surname           VARCHAR(255),
    email             VARCHAR(255),
    created_at        TIMESTAMP,
    updated_at        TIMESTAMP,
    preferences_vector VECTOR(32),
    second_vector      VECTOR(32),
    description       TEXT,
    city              VARCHAR(255),
    avatar_url        VARCHAR(512),
    contacts_vk       VARCHAR(255),
    contacts_telegram VARCHAR(255),
    contacts_phone    VARCHAR(255) NOT NULL,
    guide_rating      DOUBLE PRECISION DEFAULT 0.0,
    total_reviews     INTEGER DEFAULT 0,
    role              VARCHAR(32)
);

-- ====================================================================================
-- 2) Locations
-- ====================================================================================
CREATE TABLE IF NOT EXISTS locations (
    id         BIGSERIAL PRIMARY KEY,
    city       VARCHAR(255),
    region     VARCHAR(255),
    country    VARCHAR(255),
    image_url  VARCHAR(512),
    tour_count INTEGER DEFAULT 0
);

-- ====================================================================================
-- 3) Coordinates
-- ====================================================================================
CREATE TABLE IF NOT EXISTS coordinates (
    id    BIGSERIAL PRIMARY KEY,
    longitude  DOUBLE PRECISION NOT NULL,
    latitude   DOUBLE PRECISION NOT NULL,
    zoom  INTEGER
);

-- ====================================================================================
-- 4) Tours
-- ====================================================================================
CREATE TABLE IF NOT EXISTS tours (
    id                     BIGSERIAL PRIMARY KEY,
    title                  VARCHAR(255),
    location_id            BIGINT NOT NULL REFERENCES locations(id) ON DELETE CASCADE,
    price                  INTEGER NOT NULL,
    price_for_person       INTEGER NOT NULL,
    duration               DOUBLE PRECISION,
    route_length           DOUBLE PRECISION,
    created_at             TIMESTAMP,
    updated_at             TIMESTAMP,
    vector_representation  VECTOR(32),
    creator_id             BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    max_capacity           INTEGER,
    rating                 DOUBLE PRECISION DEFAULT 0.0,
    review_count           INTEGER DEFAULT 0,
    tour_type              VARCHAR(32),
    transport_type         VARCHAR(32),
    tour_accessibility     VARCHAR(32),
    date                   DATE,
    time                   TIME,
    by_city                BOOLEAN,
    contacts_vk            VARCHAR(255),
    contacts_telegram      VARCHAR(255),
    contacts_phone         VARCHAR(255) NOT NULL,
    coordinate_id          BIGINT NOT NULL REFERENCES coordinates(id),
    CONSTRAINT chk_by_city   CHECK (by_city IN (TRUE, FALSE))
);

-- ====================================================================================
-- 5) Description (one-to-one)
-- ====================================================================================
CREATE TABLE IF NOT EXISTS description (
    id             BIGSERIAL PRIMARY KEY,
    tour_id        BIGINT NOT NULL UNIQUE REFERENCES tours(id) ON DELETE CASCADE,
    main_info      TEXT,
    what_to_expect TEXT,
    org_details    TEXT,
    meeting_place  TEXT
);

-- ====================================================================================
-- 6) Tour Images
-- ====================================================================================
CREATE TABLE IF NOT EXISTS tour_images (
    id        BIGSERIAL PRIMARY KEY,
    tour_id   BIGINT NOT NULL REFERENCES tours(id) ON DELETE CASCADE,
    image_url VARCHAR(512)
);

-- ====================================================================================
-- 7) Tags and Tour-Tags
-- ====================================================================================
CREATE TABLE IF NOT EXISTS tags (
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS tour_tags (
    id      BIGSERIAL PRIMARY KEY,
    tour_id BIGINT NOT NULL REFERENCES tours(id) ON DELETE CASCADE,
    tag_id  BIGINT NOT NULL REFERENCES tags(id) ON DELETE CASCADE
);

-- ====================================================================================
-- 8) Reviews
-- ====================================================================================
CREATE TABLE IF NOT EXISTS reviews (
    id            BIGSERIAL PRIMARY KEY,
    tour_id       BIGINT NOT NULL REFERENCES tours(id) ON DELETE CASCADE,
    user_id       BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    with_children BOOLEAN,
    person_count  INTEGER,
    rating        DOUBLE PRECISION,
    positive_text TEXT,
    negative_text TEXT,
    created_at    TIMESTAMP,
    updated_at    TIMESTAMP
);

-- ====================================================================================
-- 9) Favorites
-- ====================================================================================
CREATE TABLE IF NOT EXISTS favorites (
    id      BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    tour_id BIGINT NOT NULL REFERENCES tours(id) ON DELETE CASCADE,
    UNIQUE(user_id, tour_id)
);

-- ====================================================================================
-- 10) Tour Schedules
-- ====================================================================================
CREATE TABLE IF NOT EXISTS tour_schedules (
    id               BIGSERIAL PRIMARY KEY,
    tour_id          BIGINT    NOT NULL REFERENCES tours(id) ON DELETE CASCADE,
    start_date       DATE      NOT NULL,
    end_date         DATE,
    time             TIME      NOT NULL,
    repeat_interval  INTEGER   NOT NULL,
    max_participants INTEGER   NOT NULL
);

-- ====================================================================================
-- 11) Booking Slots
-- ====================================================================================
CREATE TABLE IF NOT EXISTS booking_slots (
    id               BIGSERIAL PRIMARY KEY,
    tour_id          BIGINT    NOT NULL REFERENCES tours(id) ON DELETE CASCADE,
    date_time        TIMESTAMP NOT NULL,
    available_slots  INTEGER   NOT NULL,
    max_participants INTEGER   NOT NULL
);

-- ====================================================================================
-- 12) Bookings
-- ====================================================================================
CREATE TABLE IF NOT EXISTS bookings (
    id               BIGSERIAL PRIMARY KEY,
    booking_slot_id  BIGINT    NOT NULL REFERENCES booking_slots(id) ON DELETE CASCADE,
    user_id          BIGINT    NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    participants     INTEGER   NOT NULL,
    status           VARCHAR(50) NOT NULL,
    created_at       TIMESTAMP   NOT NULL
);
