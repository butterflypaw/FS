/* Retrieve employees who do not report to any manager.

Expected Output Columns:
+------+----------+------------+
|ename |    job   |   mgr      |
+------+----------+------------+

*/
USE test;
SELECT ename, job, mgr
FROM emp
WHERE mgr IS NULL;