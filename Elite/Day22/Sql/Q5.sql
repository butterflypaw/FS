/* Get the list of employees who do not have the job title "CLERK" or "SALESMAN".

Expected Output Columns:
+--------+----------+
| ename  | job      |
+--------+----------+

*/
USE test;
SELECT ename, job
FROM emp
WHERE job NOT IN ("CLERK", "SALESMAN")