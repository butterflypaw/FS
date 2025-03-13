/* Retrieve employees who were hired between the years 1995 and 1999.

Expected Output Columns:
+--------+----------+
| ename  | hiredate |
+--------+----------+

*/
USE test;
SELECT ename, hiredate
FROM emp
WHERE YEAR(hiredate) >= 1995 AND YEAR(hiredate) <= 1999