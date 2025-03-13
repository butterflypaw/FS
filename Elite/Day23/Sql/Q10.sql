/* Find employees who have the same manager as ‘BLAKE’.

Expected Output Columns:
------------------------
+-------+------+
| ename | mgr  |
+-------+------+

*/
USE test;
SELECT ename, mgr
FROM emp
WHERE mgr = (SELECT mgr FROM emp WHERE ename = 'BLAKE') and ename != 'BLAKE'