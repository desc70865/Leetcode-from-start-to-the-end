/* 
Given a string s, find the number of different non-empty palindromic subsequences in s, and return that number modulo 10^9 + 7.

A subsequence of a string s is obtained by deleting 0 or more characters from s.

A sequence is palindromic if it is equal to the sequence reversed.

Two sequences A_1, A_2, ... and B_1, B_2, ... are different if there is some i for which A_i != B_i.

Example 1:

Input: 
s = 'bccb'
Output: 6
Explanation: 
The 6 different non-empty palindromic subsequences are 'b', 'c', 'bb', 'cc', 'bcb', 'bccb'.
Note that 'bcb' is counted only once, even though it occurs twice.
Example 2:

Input: 
s = 'abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba'
Output: 104860361
Explanation: 
There are 3104860382 different non-empty palindromic subsequences, which is 104860361 modulo 10^9 + 7.
Note:

The length of s will be in the range [1, 1000].
Each character s[i] will be in the set {'a', 'b', 'c', 'd'}.
 */

class Solution {
    static final int MOD = 1_000_000_007;

    public int countPalindromicSubsequences(String s) {
        char[] t = s.toCharArray();
        int n = t.length;
        int[] y = new int[n];
        int[] z = new int[n];
        int ans = 0;
        for (int r = 0; r < n; ++r) {
            int x = 1;
            for (int l = r - 1; l >= 0; --l) {
                if (t[l] == t[r]) {
                    int zz = z[l];
                    int yy = y[l];
                    z[l] = yy + 1;
                    z[l] %= MOD;
                    y[l] += x;
                    y[l] %= MOD;
                    x = z[l] - zz + MOD;
                    x %= MOD;
                } else {
                    y[l] += x;
                    y[l] %= MOD;
                }
            }
            // System.out.println(Arrays.toString(y));
            // System.out.println(Arrays.toString(z));
            // System.out.println(x);
            ans += x;
            ans %= MOD;
        }
        return ans;
    }
}



/* 
class Solution:
    def countPalindromicSubsequences(self, s: str) -> int:
        m = 1000000007
        n = len(s)
        # effective value
        z = [0] * n
        # choice in (l, r]
        y = [0] * n
        a = 0
        for r in range(n):
            # increment by s[r]
            x = 1
            for l in range(r - 1, -1, -1):
                if (s[l] == s[r]):
                    x, y[l], z[l] = y[l] + 1 - z[l], y[l] + x, y[l] + 1
                else:
                    y[l] += x
            a += x
        return a % m
 */