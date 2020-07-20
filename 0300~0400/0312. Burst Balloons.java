/* 
Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note:

You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
Example:

Input: [3,1,5,8]
Output: 167 
Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
             coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 */

class Solution {
    public int maxCoins(int[] nums) {
        
    }
}



class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] rec = new int[n + 2][n + 2];
        int[] val = new int[n + 2];
        val[0] = val[n + 1] = 1;
        for (int i = 1; i <= n; i++) {
            val[i] = nums[i - 1];
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 2; j <= n + 1; j++) {
                for (int k = i + 1; k < j; k++) {
                    int sum = val[i] * val[k] * val[j];
                    sum += rec[i][k] + rec[k][j];
                    rec[i][j] = Math.max(rec[i][j], sum);
                }
            }
        }
        return rec[0][n + 1];
    }
}



class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int len = n + 2;
        int[] res = new int[len];
        res[0] = 1;
        res[n + 1] = 1;
        System.arraycopy(nums, 0, res, 1, n);
        int[][] dp = new int[len][len];
        
        for (int gap = 2; gap < len; gap++) {
            for (int left = 0; left < len - gap; left++) {
                int cur = 0;
                int right = left + gap;
                for (int i = left + 1; i < right; i++) {
                    cur = Math.max(cur, dp[left][i] + dp[i][right] + res[left] * res[i] * res[right]);
                }
                dp[left][right] = cur;
            }
        }
        
        return dp[0][len-1];
    }
}



class Solution {
    private int[][] dp;
    
    public int maxCoins(int[] nums) {
        dp = new int[nums.length+2][nums.length+2];
        int[] tmp = new int[nums.length+2];
        System.arraycopy(nums, 0, tmp, 1, nums.length);
        tmp[0] = tmp[tmp.length-1] = 1;
        for(int i = 2;i<tmp.length;i++) {
            for(int j = 0;j<tmp.length-i;j++) {
                dp[j][i+j] = helper(tmp,j,j+i);
            }
        }
        return dp[0][tmp.length-1];
    }
    
    public int helper(int[] tmp,int start,int end) {
        if(dp[start][end]!=0) 
            return dp[start][end];
        int max = 0;
        for(int i = start+1;i<end;i++) {
            max = Math.max(max, dp[start][i]+dp[i][end]+tmp[i]*tmp[start]*tmp[end]);
        }
        return max;
    }
}



// sort int[] nums -> int[][2] tmp
// forEach -> remove