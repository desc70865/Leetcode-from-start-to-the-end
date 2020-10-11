/* 
Return the lexicographically smallest subsequence of s that contains all the distinct characters of s exactly once.

Note: This question is the same as 316: https://leetcode.com/problems/remove-duplicate-letters/

 

Example 1:

Input: s = "bcabc"
Output: "abc"
Example 2:

Input: s = "cbacdcbc"
Output: "acdb"
 

Constraints:

1 <= s.length <= 1000
s consists of lowercase English letters.
 */

class Solution {
    public String smallestSubsequence(String s) {
        int[] map = new int[128];
        Arrays.fill(map, -1);
        char[] str = s.toCharArray();
        int N = str.length;
        for (int i = 0; i < N; i++) map[str[i]] = i;
        boolean[] v = new boolean[128];
        char[] res = new char[26];
        int idx = 0;
        for (int i = 0; i < N; i++) {
            if (v[str[i]]) continue;
            while (idx > 0 && str[i] < res[idx - 1] && map[res[idx - 1]] > i) v[res[--idx]] = false;
            res[idx++] = str[i];
            v[str[i]] = true;
        }
        return new String(Arrays.copyOfRange(res, 0, idx));
    }
}