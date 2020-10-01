/*
Given a string, find the length of the longest substring without repeating characters.

Example 1:
Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of 3. 

Example 2:
Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.

Example 3:
Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3. 

Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
*/

public class Solution {
	public int lengthOfLongestSubstring(String s) {
		int n = s.length(), ans = 0;
		Map<Character, Integer> map = new HashMap<>(); // current index of character
		// try to extend the range [i, j]
		for (int j = 0, i = 0; j < n; j++) {
			if (map.containsKey(s.charAt(j))) { // detect repeat
				i = Math.max(map.get(s.charAt(j)), i); // update i if it's in front of old j+1
			}
			ans = Math.max(ans, j - i + 1); // max[i, j]
			map.put(s.charAt(j), j + 1); // real insert
		}
		return ans;
	}
}

// 插入hash表，更新最大非重复区间:检测左边界,比较更新后的区间
// 循环包含 if 判断和赋值

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] cnt = new int[128];
        char[] str = s.toCharArray();
        int N = str.length;
        int l = 0, r = 0;
        int res = 0;
        for (; r < N; r++) {
            if (++cnt[str[r]] == 1) continue;
            res = Math.max(res, r - l);
            while (cnt[str[r]] > 1) cnt[str[l++]]--;
        }
        return Math.max(res, r - l);
    }
}