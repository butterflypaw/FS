/* Get all the details of employees who do not belong to department 20.

*/
USE test;
Select *
FROM emp
WHERE deptno != 20;