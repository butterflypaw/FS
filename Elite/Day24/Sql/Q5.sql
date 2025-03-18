/* Retrieve employees who have a commission greater than their salary.

Expected Output Columns:
------------------------
+--------+-------+---------+---------+
| ename  | empno | sal     | comm    |
+--------+-------+---------+---------+

*/
USE test;
SELECT ename, empno, sal, comm
FROM emp
WHERE comm > sal;