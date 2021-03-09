/* 
A string S of lowercase English letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.

 

Example 1:

Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 

Note:

S will have length in range [1, 500].
S will consist of lowercase English letters ('a' to 'z') only.
 */

class Solution {
    public List<Integer> partitionLabels(String S) {
        int[] map = new int[26];
        char[] chs = S.toCharArray();
        int len = chs.length;
        for (int i = 0; i < len; i++) map[chs[i] - 'a'] = i;
        int l = -1, r = 0;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            r = Math.max(r, map[chs[i] - 'a']);
            if (r == i) {
                res.add(r - l);
                l = r;
            }
        }
        return res;
    }
}