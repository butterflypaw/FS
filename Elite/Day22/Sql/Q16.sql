/* Retrieve employees with exactly 5-letter names starting with 'K'.

Expected Output Columns:
+-------+--------+----------+------+------------+---------+---------+--------+
| empno | ename  | job      | mgr  | hiredate   | sal     | comm    | deptno |
+-------+--------+----------+------+------------+---------+---------+--------+

*/
USE test;
SELECT *
FROM emp
WHERE ename LIKE 'K____';