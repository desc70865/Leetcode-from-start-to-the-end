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
    public int numWays(String s) {
        int mod = 1_000_000_007;
        char[] str = s.toCharArray();
        int N = str.length;
        int cnt = 0, x = 0;
        int[] index = new int[N];
        for (char c: str) {
            if (c == '1') cnt++;
            index[x++] = cnt;
        }
        if (cnt == 0) {
            long sum = 0;
            while (N > 2) {
                sum += N - 2;
                N--;
            }
            return (int) (sum % mod);
        }
        if (cnt % 3 != 0) return 0;
        int k = cnt / 3;
        long a = 0, b = 0;
        for (int num: index) {
            if (num == k) a++;
            else if (num == k * 2) b++;
        }
        return (int) (a * b % mod);
    }
}



class Solution {
    public int numWays(String s) {
        int mod = 1_000_000_007;
        char[] str = s.toCharArray();
        int N = str.length;
        int cnt = 0;
        int[] index = new int[N];
        for (char c: str) {
            cnt += c & 1;
        }
        if (cnt % 3 != 0) return 0;
        if (cnt == 0) {
            return (int) ((long) (N - 1) * (N - 2) / 2 % mod);
        }
        int t = 0, k = cnt / 3;
        long a = 0, b = 0;
        for (char c: str) {
            t += c & 1;
            if (t == k) a++;
            else if (t == k * 2) b++;
        }
        return (int) (a * b % mod);
    }
}