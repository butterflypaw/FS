/* Write a SQL query to find employees whose name contains ‘A’.

Expected Output Columns:
------------------------
+--------+-------+
| ename  | empno |
+--------+-------+

*/
USE test;
SELECT ename, empno
FROM emp
WHERE ename LIKE "%A%"