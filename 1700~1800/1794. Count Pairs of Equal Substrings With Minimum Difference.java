/* 
You are given two strings firstString and secondString that are 0-indexed and consist only of lowercase English letters. Count the number of index quadruples (i,j,a,b) that satisfy the following conditions:

0 <= i <= j < firstString.length
0 <= a <= b < secondString.length
The substring of firstString that starts at the ith character and ends at the jth character (inclusive) is equal to the substring of secondString that starts at the ath character and ends at the bth character (inclusive).
j - a is the minimum possible value among all quadruples that satisfy the previous conditions.
Return the number of such quadruples.

 

Example 1:

Input: firstString = "abcd", secondString = "bccda"
Output: 1
Explanation: The quadruple (0,0,4,4) is the only one that satisfies all the conditions and minimizes j - a.
Example 2:

Input: firstString = "ab", secondString = "cd"
Output: 0
Explanation: There are no quadruples satisfying all the conditions.
 

Constraints:

1 <= firstString.length, secondString.length <= 2 * 105
Both strings consist only of lowercase English letters.
 */

class Solution {
    public int countQuadruples(String firstString, String secondString) {
        char[] s1 = firstString.toCharArray();
        char[] s2 = secondString.toCharArray();
        int[] map1 = new int[26];
        int[] map2 = new int[26];
        Arrays.fill(map1, -1);
        Arrays.fill(map2, -1);
        for (int i = s1.length - 1; i >= 0; --i) {
            map1[s1[i] - 97] = i;
        }
        for (int i = 0; i < s2.length; ++i) {
            map2[s2[i] - 97] = i;
        }
        final int INF = s1.length + s2.length;
        int min = INF;
        for (int i = 0; i < 26; ++i) {
            if (map1[i] == -1 || map2[i] == -1) continue;
            min = Math.min(min, map1[i] - map2[i]);
        }
        if (min == INF) {
            return 0;
        }
        int cnt = 0;
        for (int i = Math.max(0, min); i < s1.length && i - min < s2.length; ++i) {
            if (s1[i] == s2[i - min]) {
                ++cnt;
            }
        }
        return cnt;
    }
}