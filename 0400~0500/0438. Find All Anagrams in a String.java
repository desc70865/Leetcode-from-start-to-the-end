/* 
Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.

Example 1:

Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
 */

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int n = p.length();
        int m = s.length();
        if (m < n) return res;
        int[] map = new int[26];
        for (char c: p.toCharArray()) map[c - 97]++;
        char[] str = s.toCharArray();
        int cnt = 0;
        for (int i = 0; i < n - 1; i++) {
            if (map[str[i] - 97]-- > 0) cnt++;
        }
        for (int i = n - 1; i < m; i++) {
            if (map[str[i] - 97]-- > 0) cnt++;
            int k = i - n + 1;
            if (cnt == n) {
                res.add(k);
                while (i < m - 1 && str[k] == str[i + 1]) {
                    res.add(++k);
                    i++;
                }
            }
            if (map[str[k] - 97]++ >= 0) cnt--;
        }
        return res;
    }
}