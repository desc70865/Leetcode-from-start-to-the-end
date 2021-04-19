/* 
You are given an array of transactions transactions where transactions[i] = [fromi, toi, amounti] indicates that the person with ID = fromi gave amounti $ to the person with ID = toi.

Return the minimum number of transactions required to settle the debt.

 

Example 1:

Input: transactions = [[0,1,10],[2,0,5]]
Output: 2
Explanation:
Person #0 gave person #1 $10.
Person #2 gave person #0 $5.
Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
Example 2:

Input: transactions = [[0,1,10],[1,0,1],[1,2,5],[2,0,5]]
Output: 1
Explanation:
Person #0 gave person #1 $10.
Person #1 gave person #0 $1.
Person #1 gave person #2 $5.
Person #2 gave person #0 $5.
Therefore, person #1 only need to give person #0 $4, and all debt is settled.
 

Constraints:

1 <= transactions.length <= 8
transactions[i].length == 3
0 <= fromi, toi <= 20
fromi != toi
1 <= amounti <= 100
 */

class Solution {
    public int minTransfers(int[][] transactions) {
        int[] m = new int[20];
        for (int[] t: transactions) {
            m[t[0]] += t[2];
            m[t[1]] -= t[2];
        }
        int n = removeElement(m, 0);
        int[] sum = new int[1 << n];
        for (int i = 1; i < (1 << n); i++) {
            for (int j = 0; j < n; j++) {
                if ((i & 1 << j) != 0) {
                    sum[i] = sum[i ^ 1 << j] + m[j];
                    break;
                }
            }
        }
        int[] dp = new int[1 << n];
        for (int i = 1; i < (1 << n); i++) {
            if (sum[i] != 0) continue;
            dp[i] = 1;
            for (int j = (i - 1 & i); j != 0; j = (j - 1 & i)) {
                if (dp[j] != 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return n - dp[(1 << n) - 1];
    }

    public int removeElement(int[] nums, int val) {
        int l = 0;
        for (int r = 0; r < nums.length; ) {
            while (r < nums.length && nums[r] == val) {
                r++;
            }
            if (r < nums.length) {
                nums[l++] = nums[r++];
            }
        }
        return l;
    }
}

// 状态压缩DP，位运算