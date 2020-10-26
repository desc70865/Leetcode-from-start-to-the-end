/* 
Given two integers A and B, return any string S such that:

S has length A + B and contains exactly A 'a' letters, and exactly B 'b' letters;
The substring 'aaa' does not occur in S;
The substring 'bbb' does not occur in S.
 

Example 1:

Input: A = 1, B = 2
Output: "abb"
Explanation: "abb", "bab" and "bba" are all correct answers.
Example 2:

Input: A = 4, B = 1
Output: "aabaa"
 

Note:

0 <= A <= 100
0 <= B <= 100
It is guaranteed such an S exists for the given A and B.
 */

class Solution {
    public String strWithout3a3b(int A, int B) {
        StringBuilder sb = new StringBuilder();
        if (B - A >= 2) {
            sb.append("bb");
            B -= 2;
        }
        while (A > 0 || B > 0) {
            if (A > B) {
                if (B > 0) {
                    sb.append("aab");
                    A -= 2;
                    B--;
                } else {
                    if (A == 1) sb.append('a');
                    else sb.append("aa");
                    break;
                }
            } else if (A < B) {
                if (B > 0) {
                    sb.append("abb");
                    B -= 2;
                    A--;
                } else {
                    if (B == 1) sb.append('b');
                    else sb.append("bb");
                    break;
                }
            } else {
                sb.append("ab");
                A--;
                B--;
            }
        }
        return sb.toString();
    }
}