/* 
Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

Example 1:

Input: s = "egg", t = "add"
Output: true
Example 2:

Input: s = "foo", t = "bar"
Output: false
Example 3:

Input: s = "paper", t = "title"
Output: true
Note:
You may assume both s and t have the same length.
 */

class Solution {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Integer> S = new HashMap<>();
        Map<Character, Integer> T = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (S.putIfAbsent(s.charAt(i), i) != T.putIfAbsent(t.charAt(i), i)) {
                return false;
            }
        }
        return true;
    }
}



class Solution {
    public boolean isIsomorphic(String s, String t) {
        char[] chs = s.toCharArray();
        char[] cht = t.toCharArray();
        int len = chs.length;
        int[] idxS = new int[128];
        int[] idxT = new int[128];
        for (int i = len - 1; i >= 0; i--) {
            idxS[chs[i]] = i;
            idxT[cht[i]] = i;
        }
        for (int i = 0; i < len; i++) {
            if (idxS[chs[i]] != idxT[cht[i]]) {
                return false;
            }
        }
        return true;
    }
}