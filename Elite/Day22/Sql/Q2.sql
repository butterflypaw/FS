/* List all employees whose job title is either "MANAGER" or "ANALYST".

Expected Output Columns:
+------------+-----------+
| ename      | job       |
+------------+-----------+


*/
USE test; 
SELECT ename, job
FROM emp
WHERE job = "MANAGER" or job = "ANALYST"