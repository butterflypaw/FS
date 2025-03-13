/* Retrieve employees whose names do not end with 'n'.

Expected Output Columns:
+-------+--------+----------+------+------------+---------+---------+--------+
| empno | ename  | job      | mgr  | hiredate   | sal     | comm    | deptno |
+-------+--------+----------+------+------------+---------+---------+--------+

*/
USE test;
SELECT *
FROM emp
WHERE ename NOT LIKE "%n";