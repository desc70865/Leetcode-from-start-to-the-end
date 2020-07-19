/* 
Given a binary string s (a string consisting only of '0' and '1's).

Return the number of substrings with all characters 1's.

Since the answer may be too large, return it modulo 10^9 + 7.

 

Example 1:

Input: s = "0110111"
Output: 9
Explanation: There are 9 substring in total with only 1's characters.
"1" -> 5 times.
"11" -> 3 times.
"111" -> 1 time.
Example 2:

Input: s = "101"
Output: 2
Explanation: Substring "1" is shown 2 times in s.
Example 3:

Input: s = "111111"
Output: 21
Explanation: Each substring contains only 1's characters.
Example 4:

Input: s = "000"
Output: 0
 

Constraints:

s[i] == '0' or s[i] == '1'
1 <= s.length <= 10^5
 */

class Solution {
    public int numSub(String s) {
        char[] arr = s.toCharArray();
        int len = 0;
        long res = 0;
        for (char c: arr) {
            if (c == '0') {
                if (len != 0) {
                    res += cnt(len);
                }
                len = 0;
            } else {
                len++;
            }
        }
        if (len != 0) {
            res += cnt(len);
        }
        
        return (int) res;
    }
    
    private static long cnt(int x) {
    	long t = (long) x;
        return t * (t + 1) / 2 % 1000000007;
    }
}



class Solution {
    public int numSub(String s) {
        char[] chars = s.toCharArray();
        long cnt = 0, ans = 0, mod = 1000_000_000 + 7;
        for (char c: chars) {
            if (c == '0') {
                cnt = 0;
            } else {
                ans += ++cnt;
            }
        }
        return (int)(ans % mod);
    }
}