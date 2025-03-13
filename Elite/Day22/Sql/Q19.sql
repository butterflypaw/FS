/* Perform Sum of salaries and commissions by dept 30 

Expected Output Columns:
+----------+------------+
| TotalPay | TotalBonus |
+----------+------------+

*/
USE test;
SELECT sum(sal) AS TotalPay, SUM(comm) AS TotalBonus
FROM emp
WHERE deptno = 30;