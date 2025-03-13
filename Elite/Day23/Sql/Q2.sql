
/* Find the name and salary of the highest-paid employee.

Expected Output Columns:
------------------------
+-------+---------+
| ename | sal     |
+-------+---------+

*/
USE test;
SELECT ename, sal
FROM emp
WHERE sal = (SELECT MAX(sal) from emp);