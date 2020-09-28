/* 
In a list of songs, the i-th song has a duration of time[i] seconds. 

Return the number of pairs of songs for which their total duration in seconds is divisible by 60.  Formally, we want the number of indices i, j such that i < j with (time[i] + time[j]) % 60 == 0.

 

Example 1:

Input: [30,20,150,100,40]
Output: 3
Explanation: Three pairs have a total duration divisible by 60:
(time[0] = 30, time[2] = 150): total duration 180
(time[1] = 20, time[3] = 100): total duration 120
(time[1] = 20, time[4] = 40): total duration 60
Example 2:

Input: [60,60,60]
Output: 3
Explanation: All three pairs have a total duration of 120, which is divisible by 60.
 

Note:

1 <= time.length <= 60000
1 <= time[i] <= 500
 */

class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        int[] aux = new int[60];
        for (int t: time) {
            aux[t % 60]++;
        }
        int res = 0;
        for (int i = 1; i < 30; i++) {
            res += aux[i] * aux[60 - i];
        }
        res += aux[0] * (aux[0] - 1) / 2;
        res += aux[30] * (aux[30] - 1) / 2;
        return res;
    }
}