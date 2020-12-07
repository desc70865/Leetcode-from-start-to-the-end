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
    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 > len2) return false;
        int[] map = new int[26];
        for (char c: s1.toCharArray()) {
            map[c - 97]++;
        }
        char[] chs = s2.toCharArray();
        for (int i = 0; i < len1; i++) {
            map[chs[i] - 97]--;
        }
        int diff = 0;
        for (int num: map) {
            if (num != 0) diff++;
        }
        if (diff == 0) return true;
        for (int i = len1; i < len2; i++) {
            int a = ++map[chs[i - len1] - 97];
            int b = --map[chs[i] - 97];
            if (a == 0) diff--;
            else if (a == 1) diff++;
            if (b == 0) diff--;
            else if (b == -1) diff++;
            if (diff == 0) return true;
        }
        return false;
    }
}