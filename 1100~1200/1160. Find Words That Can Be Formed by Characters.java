/* 
You are given an array of strings words and a string chars.

A string is good if it can be formed by characters from chars (each character can only be used once).

Return the sum of lengths of all good strings in words.

 

Example 1:

Input: words = ["cat","bt","hat","tree"], chars = "atach"
Output: 6
Explanation: 
The strings that can be formed are "cat" and "hat" so the answer is 3 + 3 = 6.
Example 2:

Input: words = ["hello","world","leetcode"], chars = "welldonehoneyr"
Output: 10
Explanation: 
The strings that can be formed are "hello" and "world" so the answer is 5 + 5 = 10.
 

Note:

1 <= words.length <= 1000
1 <= words[i].length, chars.length <= 100
All strings contain lowercase English letters only.
 */

class Solution {
    int[] base;
    public int countCharacters(String[] words, String chars) {
        base = new int[26];
        for (char c: chars.toCharArray()) base[c - 97]++;
        int res = 0;
        for (String word: words) if (isValid(word)) res += word.length();
        return res;
    }

    private boolean isValid(String s) {
        int[] copy = base.clone();
        for (char c: s.toCharArray()) if (--copy[c - 97] < 0) return false;
        return true;
    }
}