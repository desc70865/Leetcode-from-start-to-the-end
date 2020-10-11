/* 
Given a string and an integer k, you need to reverse the first k characters for every 2k characters counting from the start of the string. If there are less than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and left the other as original.
Example:
Input: s = "abcdefg", k = 2
Output: "bacdfeg"
Restrictions:
The string consists of lower English letters only.
Length of the given string and k will in the range [1, 10000]
 */

class Solution {
    public String reverseStr(String s, int k) {
        char[] str = s.toCharArray();
        int N = str.length;
        int idx = 0;
        while (idx < N) {
            reverse(str, idx, Math.min(idx + k - 1, N - 1));
            idx += k * 2;
        }
        return new String(str);
    }

    private void reverse(char[] s, int l, int r) {
        while (l < r) swap(s, l++, r--);
    }

    private void swap(char[] s, int i, int j) {
        char c = s[i];
        s[i] = s[j];
        s[j] = c;
    }
}