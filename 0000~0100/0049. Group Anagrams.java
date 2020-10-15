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
            String key = f(s.toCharArray());
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
        // List<List<String>> res = new ArrayList<>();
        // for (Map.Entry<String, List<String>> entry: map.entrySet()) {
        //     res.add(entry.getValue());
        // }
        // return res;
    }

    private String f(char[] s) {
        Arrays.sort(s);
        return new String(s);
    }
}