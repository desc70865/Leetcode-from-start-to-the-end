/* 
Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in dict. If two such substrings overlap, you need to wrap them together by only one pair of closed bold tag. Also, if two substrings wrapped by bold tags are consecutive, you need to combine them.
Example 1:

Input: 
s = "abcxyz123"
dict = ["abc","123"]
Output:
"<b>abc</b>xyz<b>123</b>"
 

Example 2:

Input: 
s = "aaabbcc"
dict = ["aaa","aab","bc"]
Output:
"<b>aaabbc</b>c"
 

Constraints:

The given dict won't contain duplicates, and its length won't exceed 100.
All the strings in input have length in range [1, 1000].
Note: This question is the same as 758: https://leetcode.com/problems/bold-words-in-string/
 */

class Solution {
    public String addBoldTag(String s, String[] dict) {
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