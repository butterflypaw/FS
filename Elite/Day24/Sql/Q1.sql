/* Find employees who belong to a department with no employees.

Expected Output Columns:
------------------------
+---------+
| dname   |
+---------+

*/
USE test;
SELECT dname
FROM dept d
LEFT JOIN emp e ON e.deptno = d.deptno
WHERE ename IS NULL;