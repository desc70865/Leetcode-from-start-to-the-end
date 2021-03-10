/* 
Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:

Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
 

Example 2:

Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 

Note:

The input string length won't exceed 1000.
 */

class Solution {
    public int countSubstrings(String s) {
        char[] chs = s.toCharArray();
        int len = chs.length;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += expand(chs, i, i);
            sum += expand(chs, i, i + 1);
        }
        return sum;
    }

    private int expand(char[] chs, int l, int r) {
        int ans = 0;
        while (0 <= l && r < chs.length && chs[l--] == chs[r--]) ans++;
        return ans;
    }
}