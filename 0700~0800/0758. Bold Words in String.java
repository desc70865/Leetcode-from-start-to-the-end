/* 
Given a set of keywords words and a string S, make all appearances of all keywords in S bold. Any letters between <b> and </b> tags become bold.

The returned string should use the least number of tags possible, and of course the tags should form a valid combination.

For example, given that words = ["ab", "bc"] and S = "aabcd", we should return "a<b>abc</b>d". Note that returning "a<b>a<b>b</b>c</b>d" would use more tags, so it is incorrect.

Constraints:

words has length in range [0, 50].
words[i] has length in range [1, 10].
S has length in range [0, 500].
All characters in words[i] and S are lowercase letters.
Note: This question is the same as 616: https://leetcode.com/problems/add-bold-tag-in-string/
 */

class Solution {
    public String boldWords(String[] dict, String s) {
        int len = s.length();
        int[] B = new int[len + 1];
        for (String word: dict) {
            int idx = s.indexOf(word);
            while (idx >= 0) {
                B[idx]++;
                B[idx + word.length()]--;
                idx = s.indexOf(word, idx + 1);
            }
        }
        for (int i = 1; i <= len; i++) {
            B[i] += B[i - 1];
        }
        boolean flag = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (B[i] > 0 && ! flag) {
                sb.append("<b>");
                flag = true;
            } else if (B[i] == 0 && flag) {
                sb.append("</b>");
                flag = false;
            }
            sb.append(s.charAt(i));
        }
        if (flag) sb.append("</b>");
        return sb.toString();
    }
}