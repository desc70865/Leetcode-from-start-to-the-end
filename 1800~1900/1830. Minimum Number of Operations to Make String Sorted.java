/* 
You are given a string s (0-indexed)​​​​​​. You are asked to perform the following operation on s​​​​​​ until you get a sorted string:

Find the largest index i such that 1 <= i < s.length and s[i] < s[i - 1].
Find the largest index j such that i <= j < s.length and s[k] < s[i - 1] for all the possible values of k in the range [i, j] inclusive.
Swap the two characters at indices i - 1​​​​ and j​​​​​.
Reverse the suffix starting at index i​​​​​​.
Return the number of operations needed to make the string sorted. Since the answer can be too large, return it modulo 109 + 7.

 

Example 1:

Input: s = "cba"
Output: 5
Explanation: The simulation goes as follows:
Operation 1: i=2, j=2. Swap s[1] and s[2] to get s="cab", then reverse the suffix starting at 2. Now, s="cab".
Operation 2: i=1, j=2. Swap s[0] and s[2] to get s="bac", then reverse the suffix starting at 1. Now, s="bca".
Operation 3: i=2, j=2. Swap s[1] and s[2] to get s="bac", then reverse the suffix starting at 2. Now, s="bac".
Operation 4: i=1, j=1. Swap s[0] and s[1] to get s="abc", then reverse the suffix starting at 1. Now, s="acb".
Operation 5: i=2, j=2. Swap s[1] and s[2] to get s="abc", then reverse the suffix starting at 2. Now, s="abc".
Example 2:

Input: s = "aabaa"
Output: 2
Explanation: The simulation goes as follows:
Operation 1: i=3, j=4. Swap s[2] and s[4] to get s="aaaab", then reverse the substring starting at 3. Now, s="aaaba".
Operation 2: i=4, j=4. Swap s[3] and s[4] to get s="aaaab", then reverse the substring starting at 4. Now, s="aaaab".
Example 3:

Input: s = "cdbea"
Output: 63
Example 4:

Input: s = "leetcodeleetcodeleetcode"
Output: 982157772
 

Constraints:

1 <= s.length <= 3000
s​​​​​​ consists only of lowercase English letters.
 */

class Solution {
    static final int MOD = 1_000_000_007;
    static Base base = new Base();
    
    public int makeStringSorted(String s) {
        int[] cnt = new int[26];
        char[] chs = s.toCharArray();
        int len = chs.length;
        for (char c: chs) {
            cnt[c - 'a']++;
        }
        long r = 1;
        for (int m: cnt) {
            r *= base.r[m];
            r %= MOD;
        }
        long ans = 0;
        for (int i = 0; i < len - 1; i++) {
            long sum = 0;
            for (int j = 0; j < chs[i] - 'a'; j++) {
                sum += cnt[j];
            }
            ans += sum * base.f[len - i - 1] % MOD * r % MOD;
            r = r * cnt[chs[i] - 'a']-- % MOD;
        }
        return (int) (ans % MOD);
    }
}

class Base {
    static final int MOD = 1_000_000_007;
    static final int SIZE = 3001;
    long[] f, r;

    public Base() {
        f = new long[SIZE];
        r = new long[SIZE];
        f[0] = r[0] = 1;
        for (int i = 1; i < SIZE; i++) {
            f[i] = i * f[i - 1] % MOD;
            r[i] = quickPow(f[i], MOD - 2);
        }
    }

    private static long quickPow(long x, int n) {
        long ans = 1;
        while (n != 0) {
            if (n % 2 == 1) {
                ans *= x;
                ans %= MOD;
            }
            x *= x;
            x %= MOD;
            n >>= 1;
        }
        return ans;
    }
}