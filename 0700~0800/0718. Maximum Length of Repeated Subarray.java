/* 
Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.

Example 1:

Input:
A: [1,2,3,2,1]
B: [3,2,1,4,7]
Output: 3
Explanation: 
The repeated subarray with maximum length is [3, 2, 1].
 

Note:

1 <= len(A), len(B) <= 1000
0 <= A[i], B[i] < 100
 */

class Solution {
    public int findLength(int[] A, int[] B) {
        int[] dp = new int[B.length + 1];
        int ans = 0;
        for (int i = A.length - 1; i >= 0; i--) {
            for (int j = 0; j < B.length; j++) {
                if (A[i] == B[j]) {
                    dp[j] = dp[j + 1] + 1;
                    ans = Math.max(ans, dp[j]);
                } else {
                    dp[j] = 0;
                }
            }
        }
        return ans;
    }
}

// 压缩 dp[][]
// 修改遍历方向, 简化更新方式

class Solution {
    public int findLength(int[] A, int[] B) {
        int n = A.length, m = B.length;
        int[][] dp = new int[n+1][m+1];
        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (A[i] == B[j]) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }
        return ans;
    }
}

// Rabin-Karp ?

class Solution {
    int mod = 1000000009;
    int base = 113;

    public int findLength(int[] A, int[] B) {
        int left = 1, right = Math.min(A.length, B.length) + 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (check(A, B, mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left - 1;
    }

    public boolean check(int[] A, int[] B, int len) {
        long hashA = 0;
        for (int i = 0; i < len; i++) {
            hashA = (hashA * base + A[i]) % mod;
        }
        Set<Long> bucketA = new HashSet<Long>();
        bucketA.add(hashA);
        
        // 滑窗 更新 hash 对比
        long mult = qPow(base, len - 1);
        for (int i = len; i < A.length; i++) {
            hashA = hash(hashA, A, i, len, mult);
            bucketA.add(hashA);
        }
        long hashB = 0;
        for (int i = 0; i < len; i++) {
            hashB = (hashB * base + B[i]) % mod;
        }
        if (bucketA.contains(hashB)) {
            return true;
        }
        for (int i = len; i < B.length; i++) {
            hashB = hash(hashB, B, i, len, mult);
            if (bucketA.contains(hashB)) {
                return true;
            }
        }
        return false;
    }
    
    // 将S[a:b](b-a == len) 视作 base 进制数滑窗
    // hash(S[1:len+1]) = (hash(S[0:len]) − base^len−1 × S[0]) × base + S[len]
    private long hash(long hash, int[] X, int i, int len, long mult) {
        return ((hash - X[i - len] * mult % mod + mod) % mod * base + X[i]) % mod;
    }
    
    // 使用快速幂计算 x^n % mod 的值: x^n + tmp % mod
    public long qPow(long x, long n) {
        long ret = 1;
        while (n != 0) {
            if ((n & 1) != 0) {
                ret = ret * x % mod;
            }
            x = x * x % mod;
            n >>= 1;
        }
        return ret;
    }
}