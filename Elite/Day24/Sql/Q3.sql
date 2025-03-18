/* Find the department name where ‘JONES’ works.

Expected Output Columns:
------------------------
+----------+
| dname    |
+----------+

*/
USE test;
SELECT dname
FROM emp e
JOIN dept d ON e.deptno = d.deptno
WHERE ename = 'JONES'