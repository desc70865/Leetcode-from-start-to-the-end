/* 
Given an arbitrary ransom note string and another string containing letters from all the magazines, write a function that will return true if the ransom note can be constructed from the magazines ; otherwise, it will return false.

Each letter in the magazine string can only be used once in your ransom note.

 

Example 1:

Input: ransomNote = "a", magazine = "b"
Output: false
Example 2:

Input: ransomNote = "aa", magazine = "ab"
Output: false
Example 3:

Input: ransomNote = "aa", magazine = "aab"
Output: true
 

Constraints:

You may assume that both strings contain only lowercase letters.
 */

class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] arr = new int[26];
        char[] m = magazine.toCharArray();
        char[] r = ransomNote.toCharArray();
        for (char c : m) {
            arr[c - 'a']++;
        }
        for (char c : r) {
            if (arr[c - 'a']-- <= 0) {
                return false;
            }
        }
        return true;
    }
}



class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        if (magazine.length() < ransomNote.length()) {
            return false;
        }
        int[] arr = new int[26];
        for (char c : magazine.toCharArray()) {
            arr[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            if (arr[c - 'a']-- <= 0) {
                return false;
            }
        }
        return true;
    }
}



class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        if (magazine.length() < ransomNote.length()) {
            return false;
        }
        int[] freq = new int[26];
        for (char ch:ransomNote.toCharArray()) {
            freq[ch-'a'] = magazine.indexOf(ch, freq[ch-'a'])+1;
            if (freq[ch-'a'] == 0) {
                return false;
            }
        }
        return true;
    }
}