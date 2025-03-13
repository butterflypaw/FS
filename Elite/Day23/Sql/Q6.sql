/* Find departments that have at least one employee earning more than 3000.

Expected Output Columns:
------------------------
+--------+------------+
| deptno | dname      |
+--------+------------+

*/
USE test;
SELECT DISTINCT(d.deptno) , d.dname
FROM dept d
JOIN emp e on d.deptno = e.deptno
WHERE d.deptno IN (SELECT deptno FROM emp WHERE sal > 3000)