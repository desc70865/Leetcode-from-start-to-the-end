/* 
SQL架构
Table: Players

+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| player_id      | int     |
| player_name    | varchar |
+----------------+---------+
player_id is the primary key for this table.
Each row in this table contains the name and the ID of a tennis player.
 

Table: Championships

+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| year          | int     |
| Wimbledon     | int     |
| Fr_open       | int     |
| US_open       | int     |
| Au_open       | int     |
+---------------+---------+
year is the primary key for this table.
Each row of this table containts the IDs of the players who won one each tennis tournament of the grand slam.
 

Write an SQL query to report the number of grand slam tournaments won by each player. Do not include the players who did not win any tournament.

Return the result table in any order.

The query result format is in the following example:

 

Players table:
+-----------+-------------+
| player_id | player_name |
+-----------+-------------+
| 1         | Nadal       |
| 2         | Federer     |
| 3         | Novak       |
+-----------+-------------+

Championships table:
+------+-----------+---------+---------+---------+
| year | Wimbledon | Fr_open | US_open | Au_open |
+------+-----------+---------+---------+---------+
| 2018 | 1         | 1       | 1       | 1       |
| 2019 | 1         | 1       | 2       | 2       |
| 2020 | 2         | 1       | 2       | 2       |
+------+-----------+---------+---------+---------+

Result table:
+-----------+-------------+-------------------+
| player_id | player_name | grand_slams_count |
+-----------+-------------+-------------------+
| 2         | Federer     | 5                 |
| 1         | Nadal       | 7                 |
+-----------+-------------+-------------------+

Player 1 (Nadal) won 7 titles: Wimbledon (2018, 2019), Fr_open (2018, 2019, 2020), US_open (2018), and Au_open (2018).
Player 2 (Federer) won 5 titles: Wimbledon (2020), US_open (2019, 2020), and Au_open (2019, 2020).
Player 3 (Novak) did not win anything, we did not include them in the result table.
 */

/* Write your PL/SQL query statement below */
SELECT P.PLAYER_ID, P.PLAYER_NAME, COUNT(T.PLAYER_ID) grand_slams_count
FROM
    PLAYERS P,
    (SELECT Wimbledon PLAYER_ID FROM Championships
    UNION ALL
    SELECT Fr_open FROM Championships
    UNION ALL
    SELECT US_open FROM Championships
    UNION ALL
    SELECT Au_open FROM Championships
    ) T
WHERE P.PLAYER_ID = T.PLAYER_ID
GROUP BY P.PLAYER_ID, P.PLAYER_NAME



/* Write your PL/SQL query statement below */
SELECT player_id, player_name, sum(grand_slams_count) AS grand_slams_count
FROM
(SELECT Wimbledon AS player_id, count(*) AS grand_slams_count FROM Championships GROUP BY Wimbledon
UNION ALL
SELECT Fr_open AS player_id, count(*) AS grand_slams_count FROM Championships GROUP BY Fr_open
UNION ALL
SELECT US_open AS player_id, count(*) AS grand_slams_count FROM Championships GROUP BY US_open
UNION ALL
SELECT Au_open AS player_id, count(*) AS grand_slams_count FROM Championships GROUP BY Au_open) t
JOIN Players using(player_id)
GROUP BY player_id