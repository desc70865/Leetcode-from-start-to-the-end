/* 
You are given a string s and two integers x and y. You can perform two types of operations any number of times.

Remove substring "ab" and gain x points.
For example, when removing "ab" from "cabxbae" it becomes "cxbae".
Remove substring "ba" and gain y points.
For example, when removing "ba" from "cabxbae" it becomes "cabxe".
Return the maximum points you can gain after applying the above operations on s.

 

Example 1:

Input: s = "cdbcbbaaabab", x = 4, y = 5
Output: 19
Explanation:
- Remove the "ba" underlined in "cdbcbbaaabab". Now, s = "cdbcbbaaab" and 5 points are added to the score.
- Remove the "ab" underlined in "cdbcbbaaab". Now, s = "cdbcbbaa" and 4 points are added to the score.
- Remove the "ba" underlined in "cdbcbbaa". Now, s = "cdbcba" and 5 points are added to the score.
- Remove the "ba" underlined in "cdbcba". Now, s = "cdbc" and 5 points are added to the score.
Total score = 5 + 4 + 5 + 5 = 19.
Example 2:

Input: s = "aabbaaxybbaabb", x = 5, y = 4
Output: 20
 

Constraints:

1 <= s.length <= 105
1 <= x, y <= 104
s consists of lowercase English letters.
 */

class Solution {
    public int maximumGain(String s, int x, int y) {
        if (s.length() <= 1) {
            return 0;
        }
        int max, min;
        char head, tail;
        if (x >= y) {
            max = x;
            min = y;
            head = 'a';
            tail = 'b';
        } else {
            max = y;
            min = x;
            head = 'b';
            tail = 'a';
        }
        int ans = 0;
        int countH = 0, countT = 0;
        for (char c: s.toCharArray()) {
            if (c == head) {
                countH++;
            } else if (c == tail) {
                if (countH > 0) {
                    ans += max;
                    countH--;
                } else {
                    countT++;
                }
            } else {
                ans += Math.min(countH, countT) * min;
                countH = countT = 0;
            }
        }
        ans += Math.min(countH, countT) * min;
        return ans;
    }
}