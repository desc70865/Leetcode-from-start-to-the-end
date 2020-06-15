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
        if(s1.length()==1) return s1.equals(s2);
        
        String s11;
        String s12;
        String s21;
        String s22;
        for(int i = 1; i <= s1.length(); i++){
            s11 = s1.substring(0, i);
            s12 = s1.substring(i);
            s21 = s2.substring(0,i);
            s22 = s2.substring(i);
            if(isScramble(s11,s21) && isScramble(s12,s22)) return true;
            if(isScramble(s11,s22) && isScramble(s12,s21)) return true;
        }
        return false;
    }
}

// wtf


class Solution {
    public boolean isScramble(String s1, String s2) {
        int n = s1.length();
        boolean dp[][][] = new boolean[n][n][n + 1];
        for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) dp[i][j][1] = (s1.charAt(i) == s2.charAt(j));
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                for (int j = 0; j <= n - len; j++) {
                    for (int k = 1; k <= len - 1; k++) {
                        if (dp[i][j][k] && dp[i + k][j + k][len - k]) {
                            dp[i][j][len] = true; // AB = ~A~B
                            break;
                        }
                        if (dp[i][j + len - k][k] && dp[i + k][j][len - k]) {
                            dp[i][j][len] = true; // AB = ~B~A
                            break;
                        }
                    }
                }
            }
        }
        return dp[0][0][n];
    }
}


class Solution {
    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        if (s1.equals(s2)) return true;

        int[] letters = new int[26]; // 字符匹配
        for (int i = 0; i < s1.length(); i++) {
            letters[s1.charAt(i) - 'a']++;
            letters[s2.charAt(i) - 'a']--;
        }
        for (int i : letters) {
            if (i != 0) return false; // 剪枝
        }

        // 遍历
        for (int i = 1; i < s1.length(); i++) {
            
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) &&
            isScramble(s1.substring(i), s2.substring(i))) {
                return true;
            }
            
            if (isScramble(s1.substring(i), s2.substring(0, s2.length() - i)) &&
               isScramble(s1.substring(0, i), s2.substring(s2.length() - i)) ) {
                return true;
            }
        }
        return false;
    }
}