/* Get clerks or analysts hired between 1996 and 2000

Expected Output Columns:
+------+-------+------------+
| ID   | Name  | StartDate  |
+------+-------+------------+

*/
USE test;
SELECT empno AS ID, ename AS Name, hiredate AS StartDate
FROM emp
WHERE job IN ('CLERK', 'ANALYST') AND year(hiredate) BETWEEN 1996 AND 2000;