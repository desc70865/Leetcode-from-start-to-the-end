
The Numbers table keeps the value of number and its frequency.

+----------+-------------+
|  Number  |  Frequency  |
+----------+-------------|
|  0       |  7          |
|  1       |  1          |
|  2       |  3          |
|  3       |  1          |
+----------+-------------+
In this table, the numbers are 0, 0, 0, 0, 0, 0, 0, 1, 2, 2, 2, 3, so the median is (0 + 0) / 2 = 0.

+--------+
| median |
+--------|
| 0.0000 |
+--------+
Write a query to find the median of all numbers and name the result as median.


# Write your MySQL query statement below
SELECT 
AVG(Number)median 
FROM
(SELECT n1.Number FROM Numbers n1 JOIN Numbers n2 ON n1.Number>=n2.Number 
 GROUP BY 
 n1.Number 
 HAVING 
 SUM(n2.Frequency)>=(SELECT SUM(Frequency) FROM Numbers)/2 
 AND 
 SUM(n2.Frequency)-AVG(n1.Frequency)<=(SELECT SUM(Frequency) FROM Numbers)/2
)s