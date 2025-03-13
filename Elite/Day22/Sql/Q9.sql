/* Retrieve employees who are part of department numbers between 20 and 40.

Expected Output Columns:
+--------+----------+
| ename  | deptno   |
+--------+----------+

*/
USE test;
SELECT ename, deptno
FROM emp
WHERE deptno BETWEEN 20 and 40