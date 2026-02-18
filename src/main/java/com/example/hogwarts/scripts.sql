-- 1. Студенты от 10 до 20 лет
SELECT * FROM student
WHERE age BETWEEN 10 AND 20;

-- 2. Только имена студентов
SELECT name FROM student;

-- 3. Студенты, у которых в имени есть буква О
SELECT * FROM student
WHERE name ILIKE '%о%';

-- 4. Студенты, у которых возраст меньше id
SELECT * FROM student
WHERE age < id;

-- 5. Студенты, отсортированные по возрасту
SELECT * FROM student
ORDER BY age;