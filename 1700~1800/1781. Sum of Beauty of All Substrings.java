/* 
The beauty of a string is the difference in frequencies between the most frequent and least frequent characters.

For example, the beauty of "abaacc" is 3 - 1 = 2.
Given a string s, return the sum of beauty of all of its substrings.

 

Example 1:

Input: s = "aabcb"
Output: 5
Explanation: The substrings with non-zero beauty are ["aab","aabc","aabcb","abcb","bcb"], each with beauty equal to 1.
Example 2:

Input: s = "aabcbaa"
Output: 17
 

Constraints:

1 <= s.length <= 500
s consists of only lowercase English letters.
 */

class Solution {
    public int beautySum(String s) {
        int[] map = new int[26];
        char[] chs = s.toCharArray();
        int len = chs.length;
        int[] bit = new int[len];
        for (int i = 0; i < len; i++) {
            bit[i] = chs[i] - 'a';
        }
        int sum = 0;
        for (int i = 0; i < len - 2; i++) {
            Arrays.fill(map, 0);
            map[bit[i]]++;
            map[bit[i + 1]]++;
            for (int j = i + 2; j < len; j++) {
                map[bit[j]]++;
                int min = 501, max = 0;
                for (int k = 0; k < 26; k++) {
                    if (map[k] > 0) {
                        min = Math.min(min, map[k]);
                        max = Math.max(max, map[k]);
                    }
                }
                sum += max - min;
            }
        }
        return sum;
    }
}