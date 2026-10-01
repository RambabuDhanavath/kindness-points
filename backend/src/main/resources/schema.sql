-- ============================================================
-- Kindness Points — MySQL database schema
--
-- Create the database, then load this file:
--   CREATE DATABASE kindness_points;
--   mysql -u root -p kindness_points < schema.sql
-- ============================================================

CREATE DATABASE IF NOT EXISTS kindness_points;
USE kindness_points;

-- Players (kids). Guardians oversee accounts of younger players.
CREATE TABLE users (
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    name             VARCHAR(100) NOT NULL,
    guardian_email   VARCHAR(255),
    points_balance   INT NOT NULL DEFAULT 0,
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Types of good deeds and their point rewards.
CREATE TABLE deed_types (
    code             VARCHAR(32) PRIMARY KEY,
    label            VARCHAR(120) NOT NULL,
    points           INT NOT NULL
);

INSERT INTO deed_types (code, label, points) VALUES
    ('HELP_ELDERLY', 'Helped an elderly person', 10),
    ('EXERCISE',     'Walking / exercise milestone', 5),
    ('EDUCATIONAL',  'Watched an educational video', 3),
    ('VOLUNTEER',    'Volunteered at a community drive', 15),
    ('OTHER',        'Other good deed', 2);

-- Deeds logged by players, with photo proof and verification status.
CREATE TABLE deeds (
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id          BIGINT NOT NULL,
    deed_type        VARCHAR(32) NOT NULL,
    description      TEXT,
    photo_url        VARCHAR(500),
    status           ENUM('PENDING','VERIFIED','REJECTED')
                     NOT NULL DEFAULT 'PENDING',
    points_awarded   INT NOT NULL DEFAULT 0,
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (deed_type) REFERENCES deed_types (code)
);

-- Ledger of every points movement (earn and spend).
CREATE TABLE point_transactions (
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id          BIGINT NOT NULL,
    delta            INT NOT NULL,
    reason           VARCHAR(255),
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

-- Verified NGOs that can receive donations.
CREATE TABLE ngos (
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    name             VARCHAR(150) NOT NULL,
    category         ENUM('ORPHANAGE','OLD_AGE_HOME','WELFARE') NOT NULL,
    description      TEXT,
    verified         BOOLEAN NOT NULL DEFAULT FALSE,
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Donations: points converted into real money for NGOs.
-- 100 points = $1 (see DonationService.POINTS_PER_DOLLAR).
CREATE TABLE donations (
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id          BIGINT NOT NULL,
    ngo_id           BIGINT NOT NULL,
    points_converted INT NOT NULL,
    amount_usd       DECIMAL(10,2) NOT NULL,
    status           ENUM('PLEDGED','PAID') NOT NULL DEFAULT 'PLEDGED',
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (ngo_id) REFERENCES ngos (id)
);
