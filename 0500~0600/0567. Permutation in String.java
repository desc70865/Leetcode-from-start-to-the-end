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
        if (s1.length() > s2.length()) {
            return false;
        }
        char[] chs = new StringBuilder(s1).append(s2).toString().toCharArray();
        int sum = 0, len = s1.length();
        for (int i = 0; i < len || i < chs.length - len && sum != 0; i++) {
            sum += hash(chs[i]) - hash(chs[i + len]);
        }
        return sum == 0;
    }

    private int hash(char c) {
        return 1 << (c - 97);
    }
}