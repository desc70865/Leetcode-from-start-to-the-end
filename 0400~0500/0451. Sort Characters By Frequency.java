/* 
Given a string, sort it in decreasing order based on the frequency of characters.

Example 1:

Input:
"tree"

Output:
"eert"

Explanation:
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
Example 2:

Input:
"cccaaa"

Output:
"cccaaa"

Explanation:
Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
Note that "cacaca" is incorrect, as the same characters must be together.
Example 3:

Input:
"Aabb"

Output:
"bbAa"

Explanation:
"bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.
 */

class Solution {
    public String frequencySort(String s) {
        char[] chs = s.toCharArray();
        int[][] map = new int[128][2];
        for (int i = 0; i < 128; ++i) {
            map[i][0] = i;
        }
        for (char c: chs) {
            ++map[c][1];
        }
        Arrays.sort(map, (a, b) -> b[1] - a[1]);
        int idx = 0;
        for (int[] m: map) {
            char c = (char) m[0];
            for (int i = 0; i < m[1]; ++i) {
                chs[idx++] = c;
            }
        }
        return new String(chs);
    }
}