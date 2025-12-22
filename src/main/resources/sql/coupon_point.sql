-- ================================
-- Coupon & Point History (FINAL)
-- user_id = testuser1
-- ================================

SET NAMES utf8mb4;

-- ----------------
-- coupon 테이블
-- ----------------
DROP TABLE IF EXISTS coupon;

CREATE TABLE coupon (
  coupon_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id VARCHAR(50) NOT NULL,

  coupon_type ENUM('AMOUNT', 'PERCENT', 'SHIPPING') NOT NULL,
  discount_value INT NOT NULL,

  valid_from DATETIME NOT NULL,
  valid_to DATETIME NOT NULL,

  is_used TINYINT(1) NOT NULL DEFAULT 0,
  used_at DATETIME NULL,

  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

  CONSTRAINT fk_coupon_user
    FOREIGN KEY (user_id) REFERENCES `user`(user_id)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_unicode_ci;

-- ----------------
-- point_history 테이블
-- ----------------
DROP TABLE IF EXISTS point_history;

CREATE TABLE point_history (
  point_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id VARCHAR(50) NOT NULL,

  earned_points INT NOT NULL DEFAULT 0,
  used_points INT NOT NULL DEFAULT 0,
  expired_points INT NOT NULL DEFAULT 0,

  earn_method ENUM('PURCHASE', 'EVENT', 'ETC') NULL,
  expire_reason VARCHAR(255) NULL,

  valid_from DATETIME NULL,
  valid_to DATETIME NULL,

  occurred_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

  CONSTRAINT fk_point_history_user
    FOREIGN KEY (user_id) REFERENCES `user`(user_id),

  CHECK (
    earned_points >= 0
    AND used_points >= 0
    AND expired_points >= 0
  )
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_unicode_ci;

-- ----------------
-- coupon 더미 데이터
-- ----------------
INSERT INTO coupon (
  user_id, coupon_type, discount_value,
  valid_from, valid_to, is_used, used_at
) VALUES
  ('testuser1', 'AMOUNT', 3000,
   '2025-01-01 00:00:00', '2025-12-31 23:59:59',
   0, NULL),

  ('testuser1', 'PERCENT', 10,
   '2025-01-01 00:00:00', '2025-06-30 23:59:59',
   1, '2025-02-10 12:30:00'),

  ('testuser1', 'SHIPPING', 3000,
   '2025-01-01 00:00:00', '2025-03-31 23:59:59',
   0, NULL);

-- ----------------
-- point_history 더미 데이터
-- ----------------
INSERT INTO point_history (
  user_id, earned_points, used_points, expired_points,
  earn_method, expire_reason,
  valid_from, valid_to, occurred_at
) VALUES
  ('testuser1', 1000, 0, 0,
   'PURCHASE', NULL,
   '2025-01-01 00:00:00', '2025-12-31 23:59:59',
   '2025-01-01 10:00:00'),

  ('testuser1', 0, 500, 0,
   NULL, NULL,
   NULL, NULL,
   '2025-02-01 12:00:00'),

  ('testuser1', 0, 0, 300,
   NULL, '유효기간 만료',
   '2025-01-01 00:00:00', '2025-03-31 23:59:59',
   '2025-04-01 00:00:00'),

  ('testuser1', 2000, 0, 0,
   'EVENT', NULL,
   '2025-01-15 00:00:00', '2025-07-15 23:59:59',
   '2025-01-15 09:00:00');
