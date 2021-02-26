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
        Map<String, List<String>> map = new HashMap<>();
        for (String s: strs) {
            map.computeIfAbsent(f(s), z -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }

    private String f(String s) {
        int[] map = new int[26];
        for (char c: s.toCharArray()) map[c - 97]++;
        return Arrays.toString(map);
    }
}