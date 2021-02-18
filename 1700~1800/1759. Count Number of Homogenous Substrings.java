/* 
Given a string s, return the number of homogenous substrings of s. Since the answer may be too large, return it modulo 109 + 7.

A string is homogenous if all the characters of the string are the same.

A substring is a contiguous sequence of characters within a string.

 

Example 1:

Input: s = "abbcccaa"
Output: 13
Explanation: The homogenous substrings are listed as below:
"a"   appears 3 times.
"aa"  appears 1 time.
"b"   appears 2 times.
"bb"  appears 1 time.
"c"   appears 3 times.
"cc"  appears 2 times.
"ccc" appears 1 time.
3 + 1 + 2 + 1 + 3 + 2 + 1 = 13.
Example 2:

Input: s = "xy"
Output: 2
Explanation: The homogenous substrings are "x" and "y".
Example 3:

Input: s = "zzzzz"
Output: 15
 

Constraints:

1 <= s.length <= 105
s consists of lowercase letters.
 */

class Solution {
    int MOD = 1_000_000_007;
    
    public int countHomogenous(String s) {
        long sum = 0;
        char[] chs = s.toCharArray();
        int len = chs.length;
        int cur = 0;
        for (int i = 0; i < len; i++) {
            if (i > 0 && chs[i] == chs[i - 1]) {
                cur++;
            } else {
                cur = 1;
            }
            sum += cur;
        }
        return (int) (sum % MOD);
    }
}