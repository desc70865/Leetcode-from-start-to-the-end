/* 
Given a binary string s (a string consisting only of '0's and '1's), we can split s into 3 non-empty strings s1, s2, s3 (s1+ s2+ s3 = s).

Return the number of ways s can be split such that the number of characters '1' is the same in s1, s2, and s3.

Since the answer may be too large, return it modulo 10^9 + 7.

 

Example 1:

Input: s = "10101"
Output: 4
Explanation: There are four ways to split s in 3 parts where each part contain the same number of letters '1'.
"1|010|1"
"1|01|01"
"10|10|1"
"10|1|01"
Example 2:

Input: s = "1001"
Output: 0
Example 3:

Input: s = "0000"
Output: 3
Explanation: There are three ways to split s in 3 parts.
"0|0|00"
"0|00|0"
"00|0|0"
Example 4:

Input: s = "100100010100110"
Output: 12
 

Constraints:

s[i] == '0' or s[i] == '1'
3 <= s.length <= 10^5
 */

class Solution {
    static final int MOD = 1_000_000_007;

    public int numWays(String s) {
        char[] str = s.toCharArray();
        long len = str.length;
        int cnt = 0;
        for (char c: str) cnt += c & 1;
        if (cnt % 3 != 0) return 0;
        if (cnt == 0) {
            return (int) ((len - 1) * (len - 2) / 2 % MOD);
        }
        int t = 0, k = cnt / 3;
        long a = 0, b = 0;
        for (char c: str) {
            t += c & 1;
            if (t == k) a++;
            else if (t == k * 2) b++;
            else if (t > k * 2) break;
        }
        return (int) (a * b % MOD);
    }
}



class Solution {
    static final int MOD = 1_000_000_007;

    public int numWays(String s) {
        long len = s.length();
        int sum = 0;
        int[] map = new int[100000];
        for (char c: s.toCharArray()) {
            sum += c & 1;
            map[sum]++;
        }
        if (sum % 3 != 0) return 0;
        if (sum == 0) return (int) ((len - 1) * (len - 2) / 2 % MOD);
        int k = sum / 3;
        return (int) ((long) map[k] * map[k * 2] % MOD);
    }
}