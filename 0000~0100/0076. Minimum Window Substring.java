/* 
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

Example:

Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"
Note:

If there is no such window in S that covers all characters in T, return the empty string "".
If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 */

class Solution {
    public String minWindow(String s, String t) {
        int sLen = s.length(), tLen = t.length();
        if (tLen == 0 || tLen > sLen) return "";
        char[] chs = s.toCharArray();
        int[] map = new int[128];
        for (char c: t.toCharArray()) map[c]++;
        int minLeft = -1, minLen = Integer.MAX_VALUE;
        for (int right = 0, left = 0, cnt = 0; right < sLen; right++) {
            if (--map[chs[right]] >= 0) ++cnt;
            while (cnt == tLen) {
                if (minLen > right - left + 1) {
                    minLen = right - left + 1;
                    minLeft = left;
                    if (minLen == tLen) return s.substring(minLeft, minLeft + minLen);
                }
                if (++map[chs[left++]] > 0) --cnt;
            }
        }
        return minLeft == -1 ? "" : s.substring(minLeft, minLeft + minLen);
    }
}

// 下方为 hash 写法
// 注意其中的默认更新+1方式

class Solution {
    public String minWindow(String s, String t) {
        char[] chs = s.toCharArray();
        int sLen = chs.length;
        Map<Character, Integer> window = new HashMap<>(t.length() >> 1);
        Map<Character, Integer> map = new HashMap<>(t.length() >> 1);
        for (char c: t.toCharArray()) {
            map.merge(c, 1, Integer::sum);
        }
        int numOfValidUnrepeatedCharInWindow = 0;
        int left = 0, right = 0;
        int minLeft = 0, minLen = sLen + 1;
        while (right < sLen) {
            char cur = chs[right++];
            if (map.containsKey(cur)) {
                window.merge(cur, 1, Integer::sum);
                if (window.get(cur).equals(map.get(cur))) {
                    numOfValidUnrepeatedCharInWindow++;
                }
            }
            while (numOfValidUnrepeatedCharInWindow == map.size()) {
                if (minLen > right - left) {
                    minLen = right - left;
                    minLeft = left;
                }
                char del = chs[left++];
                if (window.containsKey(del)) {
                    if (window.get(del).equals(map.get(del))) {
                        numOfValidUnrepeatedCharInWindow--;
                    }
                    window.merge(del, -1, Integer::sum);
                }
            }
        }
        return minLeft + minLen > sLen ? "" : s.substring(minLeft, minLeft + minLen);
    }
}