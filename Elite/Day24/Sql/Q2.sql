/* Find the department that has the most employees.

Expected Output Columns:
------------------------
+--------+----------------+
| deptno | employee_count |
+--------+----------------+

*/
USE test;
SELECT e.deptno, COUNT(e.deptno) AS employee_count
FROM emp e
JOIN dept d ON d.deptno = e.deptno
GROUP BY e.deptno
ORDER BY COUNT(e.deptno) DESC
LIMIT 1