/* 
You want to schedule a list of jobs in d days. Jobs are dependent (i.e To work on the i-th job, you have to finish all the jobs j where 0 <= j < i).

You have to finish at least one task every day. The difficulty of a job schedule is the sum of difficulties of each day of the d days. The difficulty of a day is the maximum difficulty of a job done in that day.

Given an array of integers jobDifficulty and an integer d. The difficulty of the i-th job is jobDifficulty[i].

Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1.

 

Example 1:


Input: jobDifficulty = [6,5,4,3,2,1], d = 2
Output: 7
Explanation: First day you can finish the first 5 jobs, total difficulty = 6.
Second day you can finish the last job, total difficulty = 1.
The difficulty of the schedule = 6 + 1 = 7 
Example 2:

Input: jobDifficulty = [9,9,9], d = 4
Output: -1
Explanation: If you finish a job per day you will still have a free day. you cannot find a schedule for the given jobs.
Example 3:

Input: jobDifficulty = [1,1,1], d = 3
Output: 3
Explanation: The schedule is one job per day. total difficulty will be 3.
Example 4:

Input: jobDifficulty = [7,1,7,1,7,1], d = 3
Output: 15
Example 5:

Input: jobDifficulty = [11,111,22,222,33,333,44,444], d = 6
Output: 843
 

Constraints:

1 <= jobDifficulty.length <= 300
0 <= jobDifficulty[i] <= 1000
1 <= d <= 10
 */

class Solution {
    public int minDifficulty(int[] jobDifficulty, int d) {
        int m = jobDifficulty.length;
        if (m < d) {
            return -1;
        }
        int[][] max = new int[m][m];
        for (int l = 0; l < m; l++) {
            int t = 0;
            for (int r = l; r < m; r++) {
                max[l][r] = t = Math.max(t, jobDifficulty[r]);
            }
        }
        int[] dp = new int[m];
        System.arraycopy(max[0], 0, dp, 0, m);
        for (int t = 1; t < d; t++) {
            for (int r = m - 1; r >= t; r--) {
                dp[r] = Integer.MAX_VALUE;
                for (int l = t; l <= r; l++) {
                    dp[r] = Math.min(dp[r], dp[l - 1] + max[l][r]);
                }
            }
        }
        return dp[m - 1];
    }
}



class Solution {
    public int minDifficulty(int[] jobDifficulty, int d) {
        int m = jobDifficulty.length;
        if (m < d) {
            return -1;
        }
        int[] max = new int[m];
        for (int i = 0, curMax = 0; i < m; i++) {
            max[i] = curMax = Math.max(curMax, jobDifficulty[i]);
        }
        int[] stack = new int[m];
        int[] prevMin = new int[m];
        for (int t = 1; t < d; t++) {
            stack[0] = t - 1;
            int prevMax = max[t - 1];
            max[t - 1] = Integer.MAX_VALUE;
            for (int j = t, k = 0; j < m; j++) {
                int tmp = prevMax;
                while (stack[k] >= t && jobDifficulty[j] >= jobDifficulty[stack[k]]) {
                    tmp = Math.min(tmp, prevMin[k--]);
                }
                prevMax = max[j];
                max[j] = Math.min(tmp + jobDifficulty[j], max[stack[k++]]);
                stack[k] = j;
                prevMin[k] = Math.min(tmp, prevMax);
            }
        }
        return max[m - 1];
    }
}