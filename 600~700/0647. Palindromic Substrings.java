/* 
Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:

Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
 

Example 2:

Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 

Note:

The input string length won't exceed 1000.
 */

class Solution {
    int count = 0;
    
    public int countSubstrings(String s) {
        if(s == null || s.length() == 0){
            return 1;
        }
        
        for(int i = 0; i<s.length(); i++){
            // 有奇数char的substring
            extendSubstring(s, i, i); 
            // 有偶数char的substring
            extendSubstring(s, i, i+1);
        }
        
        return count;
    }
    
    private void extendSubstring(String s, int l, int r){
        while(l>=0 && r<s.length() && s.charAt(l)==s.charAt(r)){
            count++;
            l--;
            r++;
        }
    }
}

// 利用回文特性中心扩展