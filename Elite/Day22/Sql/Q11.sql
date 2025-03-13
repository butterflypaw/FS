/* Get employees who are not managers and have a salary below 2000.

Expected Output Columns:
+------+----------+------------+
|ename |    job   |   sal      |
+------+----------+------------+

*/
USE test;
SELECT ename, job, sal
FROM emp
WHERE job != 'MANAGER' AND sal < 2000;