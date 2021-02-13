/* 
Given a pattern and a string s, return true if s matches the pattern.

A string s matches a pattern if there is some bijective mapping of single characters to strings such that if each character in pattern is replaced by the string it maps to, then the resulting string is s. A bijective mapping means that no two characters map to the same string, and no character maps to two different strings.

 

Example 1:

Input: pattern = "abab", s = "redblueredblue"
Output: true
Explanation: One possible mapping is as follows:
'a' -> "red"
'b' -> "blue"
Example 2:

Input: pattern = "aaaa", s = "asdasdasdasd"
Output: true
Explanation: One possible mapping is as follows:
'a' -> "asd"
Example 3:

Input: pattern = "abab", s = "asdasdasdasd"
Output: true
Explanation: One possible mapping is as follows:
'a' -> "a"
'b' -> "sdasd"
Note that 'a' and 'b' cannot both map to "asd" since the mapping is a bijection.
Example 4:

Input: pattern = "aabb", s = "xyzabcxzyabc"
Output: false
 

Constraints:

1 <= pattern.length, s.length <= 20
pattern and s consist of only lower-case English letters.
 */

class Solution {
    Map<Character, String> map = new HashMap<>();

    public boolean wordPatternMatch(String pattern, String str) {
        return dfs(pattern.toCharArray(), 0, str);
    }

    private boolean dfs(char[] pattern, int idx, String str) {
        if (idx == pattern.length) {
            return str.length() == 0;
        }
        char key = pattern[idx];
        if (map.containsKey(key)) {
            String val = map.get(key);
            return str.startsWith(val) ? dfs(pattern, idx + 1, str.substring(val.length())) : false;
        }
        for (int size = 1; size <= str.length() - (pattern.length - 1 - idx); size++) {
            String val = str.substring(0, size);
            if (map.containsValue(val)) {
                continue;
            }
            map.put(key, val);
            if (dfs(pattern, idx + 1, str.substring(size))) {
                return true;
            }
            map.remove(key);
        }
        return false;
    }
}