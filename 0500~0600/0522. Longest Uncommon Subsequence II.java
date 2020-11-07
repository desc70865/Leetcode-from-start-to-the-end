/* 
Given a list of strings, you need to find the longest uncommon subsequence among them. The longest uncommon subsequence is defined as the longest subsequence of one of these strings and this subsequence should not be any subsequence of the other strings.

A subsequence is a sequence that can be derived from one sequence by deleting some characters without changing the order of the remaining elements. Trivially, any string is a subsequence of itself and an empty string is a subsequence of any string.

The input will be a list of strings, and the output needs to be the length of the longest uncommon subsequence. If the longest uncommon subsequence doesn't exist, return -1.

Example 1:
Input: "aba", "cdc", "eae"
Output: 3
Note:

All the given strings' lengths will not exceed 10.
The length of the given list will be in the range of [2, 50].
 */

class Solution {
    public int findLUSlength(String[] strs) {
        int res = -1;
        int len = strs.length;
        Set<String> set = new HashSet<>();
        Arrays.sort(strs, (a, b) -> a.length() - b.length());
        for (int i = 0, j; i < len; i++) {
            if (strs[i].length() <= res) continue;
            if (! set.add(strs[i])) continue;
            for (j = i + 1; j < len; j++) {
                if (isSubOf(strs[i], strs[j])) break;
            }
            if (j == len) res = Math.max(res, strs[i].length());
        }
        return res;
    }

    private boolean isSubOf(String a, String b) {
        int i = 0;
        for (int j = 0; i < a.length() && j < b.length(); j++) {
            if (a.charAt(i) == b.charAt(j)) {
                if (++i == a.length()) return true;
            }
        }
        return false;
    }
}