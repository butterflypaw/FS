/* List employees with mgr 7788 or 7566 and sal < 3000

Expected Output Columns:
+----------+------+---------+
| Employee | Boss | Salary  |
+----------+------+---------+

*/
USE test;
SELECT ename AS Employee, mgr AS Boss, sal AS Salary
FROM emp
WHERE mgr IN(7788, 7566) AND sal < 3000;