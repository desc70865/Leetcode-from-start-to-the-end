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
        int[][] map = new int[128][2];
        for (int i = 0; i < 128; i++) map[i][1] = i;
        for (char c: s.toCharArray()) map[c][0]++;
        Arrays.sort(map, (a, b) -> b[0] - a[0]);
        StringBuilder sb = new StringBuilder();
        for (int[] p: map) {
            if (p[0] == 0) break;
            char c = (char) p[1];
            for (int k = 0; k < p[0]; k++) sb.append(c);
        }
        return sb.toString();
    }
}