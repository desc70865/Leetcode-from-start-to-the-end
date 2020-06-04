/* 
Share
Given an array of strings, group anagrams together.

Example:

Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note:

All inputs will be in lowercase.
The order of your output does not matter.
 */

class Solution {
	public List<List<String>> groupAnagrams(String[] strs) {
		if (strs == null || strs.length == 0)
			return new ArrayList<>();
		// 可选排序
		// Arrays.sort(strs);
		Map<String, List<String>> map = new HashMap<>();
		for (String str : strs) {
 
			char[] cs = str.toCharArray();
			// 转为数组进行排序
			Arrays.sort(cs);
			String sortStr = String.valueOf(cs);
			// 还原String作为key
			if (!map.containsKey(sortStr))
				map.put(sortStr, new ArrayList<>());
			map.get(sortStr).add(str);
		}
		// 注意ArrayList构造方法的应用
		return new ArrayList<>(map.values());
	}
}

// 使用标准排序作为键值
// 使用字符统计