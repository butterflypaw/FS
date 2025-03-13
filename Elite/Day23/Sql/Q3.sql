/* Find employees who earn more than the highest-paid employee in department 10.

Expected Output Columns:
------------------------
+-------+---------+--------+
| ename | sal     | deptno |
+-------+---------+--------+

*/
USE test;
SELECT ename, sal, deptno
FROM emp
WHERE sal > (SELECT MAX(sal) FROM emp WHERE deptno = 10);