/* List all salary grades where the grade is between 2 and 4.

Expected Output Columns:
+------+----------+------------+
|grade |    losal |   hisal    |
+------+----------+------------+

*/
USE test;
SELECT grade, losal, hisal
FROM salgrade
WHERE grade BETWEEN 2 AND 4