/* 
Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.

Example 1:

Input:
s = "aaabb", k = 3

Output:
3

The longest substring is "aaa", as 'a' is repeated 3 times.
Example 2:

Input:
s = "ababbc", k = 2

Output:
5

The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 */

class Solution {
    public int longestSubstring(String s, int k) {
        if (s == null || s.length() < k)
            return 0;
        if (k <= 1)
            return s.length();
        return longestSubstring(s.toCharArray(), k, 0, s.length() - 1);
    }
    
    public int longestSubstring(char[] s, int k, int low, int high) {
        if (low > high)
            return 0;
        int[] map = new int[26];
        for (int i = low; i <= high; i++)
            map[s[i] - 'a']++;
        boolean valid = true;
        for (int i = 0; i < 26; i++) {
            if (0 < map[i] && map[i] < k) {
                valid = false;
                break;
            }
        }
        if (valid)
            return high - low + 1;
        int left = low;
        int res = 0;
        for (int right = low; right <= high; right++) {
            if (map[s[right] - 'a'] < k) {
                res = Math.max(longestSubstring(s, k, left, right - 1), res);
                left = right + 1;
            }
        }
        res = Math.max(longestSubstring(s, k, left, high), res);
        return res;
    }
}



class Solution {
    public int longestSubstring(String s, int k) {
        int[] map = new int[26];
        char[] chs = s.toCharArray();
        for (char c: chs) map[c - 97]++;
        List<Integer> split = new ArrayList<>();
        for (int i = 0; i < chs.length; i++) {
            if (map[chs[i] - 97] < k) split.add(i);
        }
        if (split.size() == 0) return s.length();
        int ans = 0, left = 0;
        split.add(s.length());
        for (int i = 0; i < split.size(); i++) {
            int right = split.get(i);
            if (right - left > ans) ans = Math.max(ans, longestSubstring(s.substring(left, right), k));
            left = split.get(i) + 1;
        }
        return ans;
    }
}