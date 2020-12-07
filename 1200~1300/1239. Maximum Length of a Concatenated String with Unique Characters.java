/* 
Given an array of strings arr. String s is a concatenation of a sub-sequence of arr which have unique characters.

Return the maximum possible length of s.

 

Example 1:

Input: arr = ["un","iq","ue"]
Output: 4
Explanation: All possible concatenations are "","un","iq","ue","uniq" and "ique".
Maximum length is 4.
Example 2:

Input: arr = ["cha","r","act","ers"]
Output: 6
Explanation: Possible solutions are "chaers" and "acters".
Example 3:

Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
Output: 26
 

Constraints:

1 <= arr.length <= 16
1 <= arr[i].length <= 26
arr[i] contains only lower case English letters.
 */

class Solution {
    public int maxLength(List<String> arr) {
        return dfs(arr, 0, 0);
    }

    private int dfs(List<String> arr, int idx, int map) {
        if (idx == arr.size()) {
            return 0;
        }
        int code = encode(arr.get(idx));
        if (code < 0 || (code & map) > 0) return dfs(arr, idx + 1, map);
        return Math.max(dfs(arr, idx + 1, map | code) + arr.get(idx).length(), dfs(arr, idx + 1, map));
    }

    private int encode(String s) {
        int bit = 0;
        for (char c: s.toCharArray()) {
            int cur = 1 << c - 97;
            if ((bit & cur) == 0) {
                bit |= cur;
            } else {
                return -1;
            }
        }
        return bit;
    }
}