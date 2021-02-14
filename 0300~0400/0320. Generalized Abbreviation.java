/* 
A word's generalized abbreviation can be constructed by taking any number of non-overlapping substrings and replacing them with their respective lengths. For example, "abcde" can be abbreviated into "a3e" ("bcd" turned into "3"), "1bcd1" ("a" and "e" both turned into "1"), and "23" ("ab" turned into "2" and "cde" turned into "3").

Given a string word, return a list of all the possible generalized abbreviations of word. Return the answer in any order.

 

Example 1:

Input: word = "word"
Output: ["4","3d","2r1","2rd","1o2","1o1d","1or1","1ord","w3","w2d","w1r1","w1rd","wo2","wo1d","wor1","word"]
Example 2:

Input: word = "a"
Output: ["1","a"]
 

Constraints:

1 <= word.length <= 15
word consists of only lowercase English letters.
 */

class Solution {
    List<String> res = new ArrayList<>();

    public List<String> generateAbbreviations(String word) {
        dfs(word.toCharArray(), 0, 0, new StringBuilder());
        return res;
    }

    private void dfs(char[] chs, int idx, int cur, StringBuilder sb) {
        if (idx == chs.length) {
            int len = sb.length();
            if (cur > 0) {
                sb.append(cur);
            }
            res.add(sb.toString());
            if (cur > 0) {
                sb.setLength(len);
            }
            return;
        }
        dfs(chs, idx + 1, cur + 1, sb);
        int len = sb.length();
        if (cur > 0) {
            sb.append(cur);
        }
        sb.append(chs[idx]);
        dfs(chs, idx + 1, 0, sb);
        sb.setLength(len);
    }
}