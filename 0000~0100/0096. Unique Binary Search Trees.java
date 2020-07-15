/* 
Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?

Example:

Input: 3
Output: 5
Explanation:
Given n = 3, there are a total of 5 unique BST's:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
 */

class Solution {
    public int numTrees(int n) {
        int[] nums = {1, 1, 2, 5, 14, 42, 132, 429, 1430, 4862, 16796, 58786, 208012, 742900, 2674440, 9694845, 35357670, 129644790, 477638700, 1767263190};
        return nums[n];
    }
}

// 查表

class Solution {
    public int numTrees(int n) {
        long res = 1;
        for (int i = n + 1; i <= 2*n; ++i) res = res * i / (i - n);
        // for (int i = 2*n; i >= n+1; --i) res = res * i / (2*n + 1 - i);
        return (int)(res / (n + 1));
    }
}

// 通项

class Solution {
    public int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1; dp[1] = 1;
        for (int i = 2; i <= n; ++i) {
            for (int j = 0; j < i; ++j) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }
}

// 递推

class Solution {
    public int numTrees(int n) {
        return (int) (Combination(2*n, n) / (n + 1));
    }
    
    private long Combination(int n, int k) { // C(n, k)
        long res = 1;
        for (int i = k+1; i <= n; ++i) res = res * i / (i - k);
        return res;
    }
}