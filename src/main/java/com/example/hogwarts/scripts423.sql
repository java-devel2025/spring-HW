-- JOIN-запросы

-- 1. Все студенты с факультетами
SELECT s.name,
       s.age,
       f.name AS faculty_name
FROM student s
LEFT JOIN faculty f
    ON s.faculty_id = f.id;


-- 2. Только студенты с аватарками
SELECT s.name,
       s.age
FROM student s
INNER JOIN avatar a
    ON s.id = a.student_id;