/* 
Given two strings s and t , write a function to determine if t is an anagram of s.

Example 1:

Input: s = "anagram", t = "nagaram"
Output: true
Example 2:

Input: s = "rat", t = "car"
Output: false
Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you adapt your solution to such case?
 */

class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a']++;
            arr[t.charAt(i) - 'a']--;
        }
        for (int e : arr) {
            if (e != 0) {
                return false;
            }
        }
        return true;
    }
}



class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] sC = s.toCharArray();
        char[] tC = t.toCharArray();
        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            arr[sC[i] - 'a']++;
            arr[tC[i] - 'a']--;
        }
        for (int e : arr) {
            if (e != 0) {
                return false;
            }
        }
        return true;
    }
}



class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] arr = new int[26];
        for (char c : s.toCharArray()) {
            arr[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            arr[c - 'a']--;
        }
        for (int e : arr) {
            if (e != 0) {
                return false;
            }
        }
        return true;
    }
}