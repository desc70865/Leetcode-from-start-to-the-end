/* 
Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.

Example 1:

Input: "eceba"
Output: 3
Explanation: t is "ece" which its length is 3.
Example 2:

Input: "ccaabbb"
Output: 5
Explanation: t is "aabbb" which its length is 5.
 */

class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        char[] chs = s.toCharArray();
        int[] map = new int[128];
        int L = 0;
        int max = 0;
        int cnt = 0;
        for (int i = 0; i < chs.length; i++) {
            if (map[chs[i]]++ == 0) cnt++;
            while (cnt > 2) {
                if (map[chs[L++]]-- == 1) cnt--;
            }
            max = Math.max(max, i - L + 1);
        }
        return max;
    }
}