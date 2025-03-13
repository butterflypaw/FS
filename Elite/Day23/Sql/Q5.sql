/* Find employee who have the same job title as 'FORD'.

Expected Output Columns:
------------------------
+-------+---------+
| ename | job     |
+-------+---------+

*/
USE test;
SELECT ename, job
FROM emp
WHERE job IN (SELECT job FROM emp WHERE ename = 'FORD')