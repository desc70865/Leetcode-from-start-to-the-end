/* 
Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

"abc" -> "bcd" -> ... -> "xyz"
Given a list of non-empty strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

Example:

Input: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
Output: 
[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
 */

class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s: strings) {
            map.computeIfAbsent(f(s), z -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }

    private String f(String s) {
        char[] chs = s.toCharArray();
        char base = chs[0];
        for (int i = 0; i < chs.length; i++) {
            chs[i] = offset(chs[i], base);
        }
        return new String(chs);
    }

    private char offset(char a, char b) {
        return (char) ((a - b + 26) % 26 + 97);
    }
}