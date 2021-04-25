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
                    ans = Math.max(ans, dp[j] = dp[j + 1] + 1);
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
        int[][] dp = new int[n + 1][m + 1];
        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (A[i] == B[j]) {
                    ans = Math.max(ans, dp[i][j] = dp[i + 1][j + 1] + 1);
                }
            }
        }
        return ans;
    }
}



class Solution {
    public int findLength(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        char[] s = new char[m + 1 + n];
        for (int i = 0; i < m; i++) s[i] = (char) (nums1[i] + 1);
        s[m] = 'z';
        for (int i = 0; i < n; i++) s[i + m + 1] = (char) (nums2[i] + 1);
        SuffixArray sa = new SuffixArray(s);
        int ans = 0;
        for (int i = 1; i <= m + n; i++) {
            if (sa.ht[i] > ans && (sa.sa[i] - m) * (sa.sa[i - 1] - m) < 0) {
                ans = sa.ht[i];
            }
        }
        return ans;
    }
}

class SuffixArray {
    int m, n;
    char[] s;
    int[] sa, rk, ht, c, x, y;

    public SuffixArray(char[] str) {
        this.s = str;
        this.n = s.length + 1;
        this.m = Math.max(123, n);
        this.sa = new int[n];
        this.rk = new int[n];
        this.ht = new int[n];
        this.c = new int[m];
        this.x = new int[n];
        this.y = new int[n];
        getSA();
        getHT();
    }

    public void getSA() {
        for (int i = 0; i < n - 1; i++) c[rk[i] = s[i]]++;
        for (int i = 1; i < m; i++) c[i] += c[i - 1];
        for (int i = n - 1; i >= 0; i--) sa[c[rk[i]]--] = i;
        for (int k = 1; k < n; k <<= 1) {
            for (int i = n - k; i < n; i++) y[i - n + k] = i;
            int j = 0;
            for (int i = 0; i < n; i++) if (sa[i] >= k) y[k + j++] = sa[i] - k;
            for (int i = 0; i < m; i++) c[i] = 0;
            for (int i = 0; i < n; i++) c[rk[y[i]]]++;
            for (int i = 1; i < m; i++) c[i] += c[i - 1];
            for (int i = n - 1; i >= 0; i--) sa[--c[rk[y[i]]]] = y[i];
            int p = 0;
            y[sa[0]] = p++;
            for (int i = 1; i < n; i++) y[sa[i]] = rk[sa[i]] == rk[sa[i - 1]] && rk[sa[i] + k] == rk[sa[i - 1] + k] ? p - 1 : p++;
            m = p;
            x = rk;
            rk = y;
            y = x;
            if (m == n) break;
        }
    }

    public void getHT() {
        for (int i = 0, k = 0; i < n - 1; i++) {
            if (k > 0) k--;
            int j = sa[rk[i] - 1];
            while (i + k < n - 1 && j + k < n - 1 && s[i + k] == s[j + k]) k++;
            ht[rk[i]] = k;
        }
    }
}