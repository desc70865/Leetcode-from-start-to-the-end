/* 
Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.

 

Example 1:

Input: "abab"
Output: True
Explanation: It's the substring "ab" twice.
Example 2:

Input: "aba"
Output: False
Example 3:

Input: "abcabcabcabc"
Output: True
Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
 */

class Solution {
    public boolean repeatedSubstringPattern(String s) {
        return (s + s).substring(1, s.length() * 2 - 1).contains(s);
    }
}

// KMP

class Solution {
    public boolean repeatedSubstringPattern(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        
        int[] lps = new int[len];
        lps[0] = 0;
        // lps[i] 是 chars[i] 上一次出现的位置 start from 1, 首次出现则为 0.
        
        int i = 1;
        int j = 0;
        while (i < len) {
            if (chars[i] == chars[j]) lps[i++] = ++j; // 当前位置是否继续匹配
            else if (j == 0) lps[i++] = 0; // 已经搜索到起点,确定为首次出现
            else j = lps[j - 1]; // 假设前一个字符可复用,向前搜索其上次出现位置的后一个字符是否匹配
            // when xy -> xz, check is there z after pre-x
        }
        
        int diff = len - lps[len - 1];
        return diff < len && len % diff == 0;
    }
}



/* void getNext(const string p, vector<int> &next) {
    next[0] = -1;
    int lenP = p.size();
    int j = 0;
    for (int i = 1; i < lenP; i++) {
        if (p[i] == p[j]) {
            j++;
            next[i] = j - 1;
        } else {
            next[i] = j;
            j = 0;
        }
    }
} */