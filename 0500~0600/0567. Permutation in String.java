/* 
Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.

 

Example 1:

Input: s1 = "ab" s2 = "eidbaooo"
Output: True
Explanation: s2 contains one permutation of s1 ("ba").
Example 2:

Input:s1= "ab" s2 = "eidboaoo"
Output: False
 

Constraints:

The input strings only contain lower case letters.
The length of both given strings is in range [1, 10,000].
 */

class Solution {
    int[] map = new int[26];
    int diff = 0;

    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;
        for (char c: s1.toCharArray()) {
            add(c);
        }
        int len = s1.length();
        char[] chs = s2.toCharArray();
        for (int i = 0; i < len; i++) {
            remove(chs[i]);
        }
        for (int i = len; i < chs.length && diff != 0; i++) {
            add(chs[i - len]);
            remove(chs[i]);
        }
        return diff == 0;
    }

    private void add(char c) {
        if (map[c - 97] == 0) {
            diff++;
        } else if (map[c - 97] == -1) {
            diff--;
        }
        map[c - 97]++;
    }

    private void remove(char c) {
        if (map[c - 97] == 0) {
            diff++;
        } else if (map[c - 97] == 1) {
            diff--;
        }
        map[c - 97]--;
    }
}