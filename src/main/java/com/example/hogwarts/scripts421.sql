-- Ограничения для Student и Faculty

-- 1. Возраст студента не меньше 16
ALTER TABLE student
ADD CONSTRAINT student_age_check
CHECK (age >= 16);

-- 2. Имя студента NOT NULL
ALTER TABLE student
ALTER COLUMN name SET NOT NULL;

-- 3. Имя студента UNIQUE
ALTER TABLE student
ADD CONSTRAINT student_name_unique
UNIQUE (name);

-- 4. DEFAULT возраст = 20
ALTER TABLE student
ALTER COLUMN age SET DEFAULT 20;

-- 5. Составная уникальность (name + color) у факультета
ALTER TABLE faculty
ADD CONSTRAINT faculty_name_color_unique
UNIQUE (name, color);