/* 
Given a string s of lowercase letters, you need to find the maximum number of non-empty substrings of s that meet the following conditions:

The substrings do not overlap, that is for any two substrings s[i..j] and s[k..l], either j < k or i > l is true.
A substring that contains a certain character c must also contain all occurrences of c.
Find the maximum number of substrings that meet the above conditions. If there are multiple solutions with the same number of substrings, return the one with minimum total length. It can be shown that there exists a unique solution of minimum total length.

Notice that you can return the substrings in any order.

 

Example 1:

Input: s = "adefaddaccc"
Output: ["e","f","ccc"]
Explanation: The following are all the possible substrings that meet the conditions:
[
  "adefaddaccc"
  "adefadda",
  "ef",
  "e",
  "f",
  "ccc",
]
If we choose the first string, we cannot choose anything else and we'd get only 1. If we choose "adefadda", we are left with "ccc" which is the only one that doesn't overlap, thus obtaining 2 substrings. Notice also, that it's not optimal to choose "ef" since it can be split into two. Therefore, the optimal way is to choose ["e","f","ccc"] which gives us 3 substrings. No other solution of the same number of substrings exist.
Example 2:

Input: s = "abbaccd"
Output: ["d","bb","cc"]
Explanation: Notice that while the set of substrings ["d","abba","cc"] also has length 3, it's considered incorrect since it has larger total length.
 

Constraints:

1 <= s.length <= 10^5
s contains only lowercase English letters.
 */

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        char[] t = s.toCharArray();
        int n = t.length;
        int[][] map = new int[26][2];
        for (int i = 0; i < 26; ++i) {
            Arrays.fill(map[i], -1);
        }
        for (int i = 0; i < n; ++i) {
            int c = t[i] - 'a';
            if (map[c][0] == -1) {
                map[c][0] = i;
            }
            map[c][1] = i;
        }
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < 26; ++i) {
            if (map[i][0] == -1) {
                continue;
            }
            int p = map[i][0];
            for (; p <= map[i][1]; ++p) {
                int c = t[p] - 'a';
                if (map[i][0] <= map[c][0] && map[c][1] <= map[i][1]) {
                    continue;
                }
                if (map[i][0] > map[c][0]) {
                    break;
                }
                map[i][1] = Math.max(map[i][1], map[c][1]);
            }
            if (p > map[i][1]) {
                list.add(map[i]);
            }
        }
        Collections.sort(list, (a, b) -> a[1] == b[1] ? b[0] - a[0] : a[1] - b[1]);
        List<String> ans = new ArrayList<>();
        int end = -1;
        for (int[] seg: list) {
            if (seg[0] == -1) {
                continue;
            }
            if (seg[0] > end) {
                end = seg[1];
                ans.add(s.substring(seg[0], seg[1] + 1));
            }
        }
        return ans;
    }
}