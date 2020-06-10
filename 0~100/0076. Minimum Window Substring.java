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
        int m = s.length(), n = t.length();
        if (n == 0 || n > m) return "";
        int[] letterCnt = new int[128];
        char[] sArr = s.toCharArray();
        int left = 0, cnt = 0, minLeft = -1, minLen = Integer.MAX_VALUE;
        // 使用 char 数组替代 hash 表
        for (char c : t.toCharArray()) ++letterCnt[c]; // ASCII 整数
        for (int i = 0; i < m; ++i) {
            if (--letterCnt[sArr[i]] >= 0) ++cnt;
            while (cnt == n) { // 更新滑窗
                if (minLen > i - left + 1) {
                    minLen = i - left + 1;
                    minLeft = left;
                    // 按题设要求可提前返回
                    if (minLen == n) return s.substring(minLeft, minLeft + minLen);
                }
                if (++letterCnt[sArr[left]] > 0) --cnt; // 收缩判断 -> 终止当前while循环
                ++left; // 滑动
            }
        }
        return minLeft == -1 ? "" : s.substring(minLeft, minLeft + minLen);
    }
}

// 下方为 hash 写法
// 注意其中的默认更新+1方式

class Solution {
    public String minWindow(String s, String t) {
        char[] sArr = s.toCharArray();

        Map<Character, Integer> window = new HashMap<>(t.length() >> 1);
        Map<Character, Integer> tMap = new HashMap<>(t.length() >> 1);
        // 初始化Map
        for (char ch : t.toCharArray()) {
            tMap.put(ch, tMap.getOrDefault(ch, 0) + 1);
        }

        // 滑动窗口中满足条件且不重复的字符个数
        int numOfValidUnrepeatedCharInWindow = 0;
        // 窗口边界，[left, right)
        int left = 0, right = 0;
        // 最小覆盖子串的起始索引和长度
        int minLeft = 0, minLen = s.length() + 1;
        // 扩大窗口
        while (right < s.length()) {
            // 进入窗口的字符，并右移窗口右边界
            char enterChar = sArr[right++];
            // 判断字符串t中是否有该字符
            if (tMap.containsKey(enterChar)) {
                // 如果有该字符，更新数据
                // 窗口中该字符个数加1
                window.put(enterChar, window.getOrDefault(enterChar, 0) + 1);
                // 判断窗口中该字符的个数是否等于满足条件
                if (window.get(enterChar).equals(tMap.get(enterChar))) {
                    // 满足条件，个数加1
                    numOfValidUnrepeatedCharInWindow++;
                }
            }
            // 判断窗口是否满足缩小的条件
            while (numOfValidUnrepeatedCharInWindow == tMap.size()) {
                // 判断当前窗口长度是否小于最小长度
                if (minLen > right - left) {
                    minLen = right - left;
                    minLeft = left;
                }
                // 移出窗口的字符，并右移窗口左边界
                char outChar = sArr[left++];
                // 判断窗口中是否有该字符
                if (window.containsKey(outChar)) {
                    // 判断该字符个数是否刚好满足条件
                    if (window.get(outChar).equals(tMap.get(outChar))) {
                        // 不满足条件，个数减1
                        numOfValidUnrepeatedCharInWindow--;
                    }
                    // 字符个数减1
                    window.put(outChar, window.get(outChar) - 1);
                }
            }
        }
        return minLeft + minLen > s.length() ? "" : s.substring(minLeft, minLeft + minLen);
    }
}