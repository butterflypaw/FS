/* Get Max and min salaries in dept 20 or 40

Expected Output Columns:
+------------+-----------+
| HighestPay | LowestPay |
+------------+-----------+

*/
USE test;
SELECT MAX(sal) AS HighestPay, MIN(sal) AS LowestPay
FROM emp
WHERE deptno = 20 OR deptno = 40;