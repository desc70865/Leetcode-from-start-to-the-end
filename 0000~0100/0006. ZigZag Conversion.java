/* 
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
=======

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:
string convert(string s, int numRows);
Example 1:
Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"
Example 2:
Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:
=======

Write the code that will take a string and make this conversion given a number of rows:

string convert(string s, int numRows);
Example 1:

Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"

Example 2:

Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:

P     I    N
A   L S  I G
Y A   H R
P     I
 */

class Solution {
    public String convert(String s, int numRows) {
        if (numRows <= 1) return s;
        if (s == null || s.length() <= numRows) return s;
        char[] str = s.toCharArray();
        int len = str.length;
        int T = (numRows - 1) * 2;
        char[] res = new char[len];
        int idx = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; i + j < len; j += T) {
                res[idx++] = str[i + j];
                if (i == 0 || i == numRows - 1) continue;
                if (j + T - i < len) {
                    res[idx++] = str[j + T - i];
                }
            }
        }
        return new String(res);
    }
}

// 对定长String的构造，char[]优于StringBuilder