/* 
Given a string s and an integer k, return the length of the longest substring of s that contains at most k distinct characters.

 

Example 1:

Input: s = "eceba", k = 2
Output: 3
Explanation: The substring is "ece" with length 3.
Example 2:

Input: s = "aa", k = 1
Output: 2
Explanation: The substring is "aa" with length 2.
 

Constraints:

1 <= s.length <= 5 * 104
0 <= k <= 50
 */

class Solution {
    int[] map = new int[128];
    int size = 0;

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        char[] chs = s.toCharArray();
        int l = 0, r = 0;
        int max = 0;
        for (; r < chs.length; r++) {
            add(chs[r]);
            while (size > k) {
                remove(chs[l++]);
            }
            max = Math.max(max, r - l + 1);
        }
        return max;
    }

    private void add(char c) {
        if (map[c]++ == 0) {
            size++;
        }
    }

    private void remove(char c) {
        if (map[c]-- == 1) {
            size--;
        }
    }
}