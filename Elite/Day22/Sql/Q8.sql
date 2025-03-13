/* Retrieve employees who earn a commission between 300 and 1000.

Expected Output Columns:
+--------+----------+
| ename  | comm     |
+--------+----------+

*/
USE test;
SELECT ename, comm
FROM emp
WHERE comm BETWEEN 300 and 1000