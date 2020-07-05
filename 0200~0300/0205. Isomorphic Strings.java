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
        
    }
}



class Solution {
    public boolean isIsomorphic(String s, String t) {
        char[] arrS = new char[128];
        char[] arrT = new char[128];
        char[] S = s.toCharArray();
        char[] T = t.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (arrS[S[i]] == '\0' && arrT[T[i]] == '\0') {
                arrS[S[i]] = T[i];
                arrT[T[i]] = S[i];
                continue;
            }
            if (arrT[T[i]] != S[i]) {
                return false;
            }
        }
        return true;
    }
}



class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        
        char[] arr = new char[128];
        
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            char b = t.charAt(i);
            if (arr[a] < '0') {
                arr[a] = b;
            } else if (arr[a] == b) {
                continue;
            } else {
                return false;
            }
        }
        
        return checkRepeat(arr);
    }
    
    private boolean checkRepeat(char[] arr){
        Set<Character> set = new HashSet<Character>();
        int extra = 0;
        for (char c : arr) {
            if (c < '0') {
                extra++;
            } else {
                set.add(c);
            }
        }
        return set.size() + extra == arr.length;
    }
}



class Solution {
    public boolean isIsomorphic(String s, String t) {
        int[] mapS = new int[128];
        int[] mapT = new int[128];
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            if (mapS[c1] != mapT[c2]) {
                return false;
            }
            if (mapS[c1] == 0) {
                mapS[c1] = i + 1;
                mapT[c2] = i + 1;
            }
        }
        return true;
    }
}