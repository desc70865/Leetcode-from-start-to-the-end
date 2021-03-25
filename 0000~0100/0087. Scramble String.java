/* 
Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.

Below is one possible representation of s1 = "great":

    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
To scramble the string, we may choose any non-leaf node and swap its two children.

For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".

    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
We say that "rgeat" is a scrambled string of "great".

Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".

    rgtae
   /    \
  rg    tae
 / \    /  \
r   g  ta  e
       / \
      t   a
We say that "rgtae" is a scrambled string of "great".

Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.

Example 1:

Input: s1 = "great", s2 = "rgeat"
Output: true

Example 2:

Input: s1 = "abcde", s2 = "caebd"
Output: false
 */

class Solution {
    public boolean isScramble(String s1, String s2) {
        char[] chs = s1.toCharArray();
        char[] cht = s2.toCharArray();
        int len = chs.length;
        boolean dp[][][] = new boolean[len][len][len + 1];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                dp[i][j][1] = chs[i] == cht[j];
            }
        }
        for (int intervalSize = 2; intervalSize <= len; intervalSize++) {
            for (int i = 0; i <= len - intervalSize; i++) {
                for (int j = 0; j <= len - intervalSize; j++) {
                    for (int k = 1, l = intervalSize - 1; k < intervalSize; k++, l--) {
                        if (dp[i][j][k] && dp[i + k][j + k][l]) {
                            dp[i][j][intervalSize] = true; // AB = ~A~B
                            break;
                        }
                        if (dp[i][j + l][k] && dp[i + k][j][l]) {
                            dp[i][j][intervalSize] = true; // AB = ~B~A
                            break;
                        }
                    }
                }
            }
        }
        return dp[0][0][len];
    }
}



class Solution {
    Map<String, Map<String, Boolean>> map = new HashMap<>();

    public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2)) {
            return true;
        }
        if (hashCode(s1) != hashCode(s2)) {
            return false;
        }
        int len = s1.length();
        if (len <= 3) {
            return true;
        }
        if (map.containsKey(s1) && map.get(s1).containsKey(s2)) {
            return map.get(s1).get(s2);
        }
        for (int i = 1; i < len; i++) {
            String s1L = s1.substring(0, i);
            String s1R = s1.substring(i);
            if (isScramble(s1L, s2.substring(0, i)) && isScramble(s1R, s2.substring(i))
            || isScramble(s1L, s2.substring(len - i)) && isScramble(s1R, s2.substring(0, len - i))) {
                map.computeIfAbsent(s1, z -> new HashMap<>()).put(s2, true);
                return true;
            }
        }
        map.computeIfAbsent(s1, z -> new HashMap<>()).put(s2, false);
        return false;
    }

    private int hashCode(String s) {
        int hash = 0;
        for (char c: s.toCharArray()) {
            hash += 1 << c - 'a';
        }
        return hash;
    }
}