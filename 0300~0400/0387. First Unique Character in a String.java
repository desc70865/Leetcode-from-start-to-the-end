/* 
Given a string, find the first non-repeating character in it and return its index. If it doesn't exist, return -1.

Examples:

s = "leetcode"
return 0.

s = "loveleetcode"
return 2.
 

Note: You may assume the string contains only lowercase English letters.
 */

class Solution {
    public int firstUniqChar(String s) {
        int[] cnt = new int[26];
        char[] tmp = s.toCharArray();
        for (char c: tmp) {
            cnt[c - 'a']++;
        }
        for (int i = 0; i < tmp.length; i++) {
            if (cnt[tmp[i] - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
}

// indexOf
// lastIndexOf

class Solution {
    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        
        int res = Integer.MAX_VALUE;
        
        for (char c = 'a'; c <= 'z'; c++) {
            int in = s.indexOf(c);
            if (in != -1 && in == s.lastIndexOf(c)) {
                res = Math.min(res, in);
            }
        }
        
        return res == Integer.MAX_VALUE ? -1: res;
    }
}