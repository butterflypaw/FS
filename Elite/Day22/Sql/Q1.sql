/* Retrieve the names and salaries of employees who have the job title "SALESMAN" and earn more than 1500.

Expected Output Columns:
+------------+-----------+
| ename      | sal       |
+------------+-----------+


*/
USE test;
SELECT ename, sal
FROM emp
WHERE job = "SALESMAN" and sal > 1500;