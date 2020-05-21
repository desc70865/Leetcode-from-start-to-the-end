<<<<<<< HEAD

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
=======
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

>>>>>>> 53a3b336f8b1900d5cb2aa776824caccfd0dcd71
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

// 插入hash表，更新最大非重复区间:检测更新左边界,比较更新后的区间大小
<<<<<<< HEAD
// 循环包含 if 判断和赋值
=======
// 循环包含 if 判断和赋值
>>>>>>> 53a3b336f8b1900d5cb2aa776824caccfd0dcd71
