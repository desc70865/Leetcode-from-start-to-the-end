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
        int[] dp = new int[n+1];
        dp[0] = 1; dp[1] = 1;
        for (int i = 2; i <= n; ++i) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }
}



class Solution {
    public int numTrees(int n) {
        return (int) (Combination(2 * n, n) / (n + 1));
    }
    
    private long Combination(int n, int k) { // C(n, k)
        long res = 1;
        for (int i = k + 1; i <= n; i++) {
            res = res * i / (i - k);
        }
        return res;
    }
}



class Solution {
    static long[][] nCr;
    static int[] Catalan;
    static int size = 20;

    static {
        nCr = new long[size * 2][size];
        Catalan = new int[size];

        for (int i = 1; i < size; i++) {
            Catalan[i] = (int) (nCr(i * 2, i) / (i + 1));
        }
    }

    public static long nCr(int n, int r) {
        if (r == 0 || r == n) return 1;
        if (nCr[n][r] > 0) return nCr[n][r];
        return nCr[n][r] = nCr(n - 1, r) + nCr(n - 1, r - 1);
    }

    public int numTrees(int n) {
        return Catalan[n];
    }
}