/* 
Consider the string s to be the infinite wraparound string of "abcdefghijklmnopqrstuvwxyz", so s will look like this: "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".

Now we have another string p. Your job is to find out how many unique non-empty substrings of p are present in s. In particular, your input is the string p and you need to output the number of different non-empty substrings of p in the string s.

Note: p consists of only lowercase English letters and the size of p might be over 10000.

Example 1:
Input: "a"
Output: 1

Explanation: Only the substring "a" of string "a" is in the string s.
Example 2:
Input: "cac"
Output: 2
Explanation: There are two substrings "a", "c" of string "cac" in the string s.
Example 3:
Input: "zab"
Output: 6
Explanation: There are six substrings "z", "a", "b", "za", "ab", "zab" of string "zab" in the string s.
 */

class Solution {
    public int findSubstringInWraproundString(String p) {
        if (p.length() == 0) return 0;
        char[] chs = p.toCharArray();
        int len = chs.length;
        int[] map = new int[26];
        char head = chs[0];
        int size = 0;
        for (int i = 0; i < len; i++) {
            if (i == 0 || next(chs[i - 1], chs[i])) {
                size++;
            } else {
                map[head - 97] = Math.max(map[head - 97], size);
                size = 1;
                head = chs[i];
            }
        }
        map[head - 97] = Math.max(map[head - 97], size);
        int ans = 0;
        for (int i = 0; i < 52; i++) {
            map[i % 26] = Math.max(map[i % 26], map[(i + 25) % 26] - 1);
        }
        for (int num: map) {
            ans += num;
        }
        // System.out.println(Arrays.toString(map));
        return ans;
    }

    private boolean next(char a, char b) {
        return a + 1 == b || a == 'z' && b == 'a';
    }
}