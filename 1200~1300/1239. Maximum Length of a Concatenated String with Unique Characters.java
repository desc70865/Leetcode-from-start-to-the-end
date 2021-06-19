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
    Set<Integer> set = new HashSet<>();

    public int maxLength(List<String> arr) {
        for (String word: arr) {
            encode(word);
        }
        return dfs(new ArrayList<>(set), 0, 0);
    }

    private int dfs(List<Integer> list, int idx, int mask) {
        if (idx == list.size()) {
            return 0;
        }
        int code = list.get(idx);
        if ((code & mask) > 0) {
            return dfs(list, idx + 1, mask);
        }
        return Math.max(
            dfs(list, idx + 1, mask | code) + Integer.bitCount(list.get(idx)),
            dfs(list, idx + 1, mask)
        );
    }

    private void encode(String s) {
        int mask = 0;
        for (char c: s.toCharArray()) {
            int bit = 1 << c - 97;
            if ((mask & bit) == 0) {
                mask |= bit;
            } else {
                return;
            }
        }
        set.add(mask);
    }
}