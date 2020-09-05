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
        List<Integer> res = new ArrayList<>();
        
        int[] m = new int[26];
        char[] str = S.toCharArray();
        int len = str.length;
        for (int i = 0; i < len; i++) m[str[i] - 'a'] = i;
        
        int p = 0, s = -1;
        for (int i = 0; i < len; i++) {
            p = Math.max(p, m[str[i] - 'a']);
            if (p == i) {
                res.add(p - s);
                s = p;
            }
        }
        
        return res;
    }
}