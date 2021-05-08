/* 
You are given an integer array jobs, where jobs[i] is the amount of time it takes to complete the ith job.

There are k workers that you can assign jobs to. Each job should be assigned to exactly one worker. The working time of a worker is the sum of the time it takes to complete all jobs assigned to them. Your goal is to devise an optimal assignment such that the maximum working time of any worker is minimized.

Return the minimum possible maximum working time of any assignment.

 

Example 1:

Input: jobs = [3,2,3], k = 3
Output: 3
Explanation: By assigning each person one job, the maximum time is 3.
Example 2:

Input: jobs = [1,2,4,7,8], k = 2
Output: 11
Explanation: Assign the jobs the following way:
Worker 1: 1, 2, 8 (working time = 1 + 2 + 8 = 11)
Worker 2: 4, 7 (working time = 4 + 7 = 11)
The maximum working time is 11.
 

Constraints:

1 <= k <= jobs.length <= 12
1 <= jobs[i] <= 107
 */

class Solution {
    public int minimumTimeRequired(int[] jobs, int k) {
        int n = jobs.length;
        int[] sum = new int[1 << n];
        for (int i = 1; i < (1 << n); i++) {
            int x = Integer.numberOfTrailingZeros(i);
            sum[i] = sum[i - (1 << x)] + jobs[x];
        }
        int[][] dp = new int[k][1 << n];
        dp[0] = sum;
        for (int i = 1; i < k; ++i) {
            for (int j = 0; j < (1 << n); ++j) {
                int cur = Integer.MAX_VALUE;
                for (int x = j; x != 0; x = (x - 1) & j) {
                    cur = Math.min(cur, Math.max(dp[i - 1][j - x], sum[x]));
                }
                dp[i][j] = cur;
            }
        }
        return dp[k - 1][(1 << n) - 1];
    }
}



class Solution {
    public int minimumTimeRequired(int[] jobs, int k) {
        int n = jobs.length;
        int[] sum = new int[1 << n];
        for (int i = 1; i < (1 << n); i++) {
            int x = Integer.numberOfTrailingZeros(i);
            sum[i] = sum[i - (1 << x)] + jobs[x];
        }
        int[] dp = new int[1 << n];
        dp = sum;
        for (int i = 1; i < k; ++i) {
            int[] dq = new int[1 << n];
            for (int j = 0; j < (1 << n); ++j) {
                int cur = Integer.MAX_VALUE;
                for (int x = j; x != 0; x = (x - 1) & j) {
                    cur = Math.min(cur, Math.max(dp[j - x], sum[x]));
                }
                dq[j] = cur;
            }
            dp = dq;
        }
        return dp[(1 << n) - 1];
    }
}



class Solution {
    int[] jobs;
    int k;
    int ans = Integer.MAX_VALUE;

    public int minimumTimeRequired(int[] jobs, int k) {
        Arrays.sort(jobs);
        this.jobs = jobs;
        this.k = k;
        dfs(jobs.length - 1, new int[k], 0);
        return ans;
    }

    private void dfs(int index, int[] time, int maxTime) {
        if (maxTime >= ans) {
            return;
        }
        if (index < 0) {
            ans = maxTime;
            return;
        }
        int[] nextTime = new int[k];
        System.arraycopy(time, 0, nextTime, 0, k);
        Arrays.sort(nextTime);
        for (int i = 0, pre = -1; i < k; ++i) {
            if (nextTime[i] == pre) {
                continue;
            }
            pre = nextTime[i];
            nextTime[i] += jobs[index];
            dfs(index - 1, nextTime, Math.max(maxTime, nextTime[i]));
            nextTime[i] -= jobs[index];
        }
    }
}