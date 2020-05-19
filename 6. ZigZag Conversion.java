/* 
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

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

P     I    N
A   L S  I G
Y A   H R
P     I
 */
 
 class Solution {
    public String convert(String s, int numRows) {

        if (numRows == 1) return s;

        StringBuilder ret = new StringBuilder();
        int length = s.length();
        int cycleLen = 2 * (numRows - 1); // Z循环步长

        for (int i = 0; i < numRows; i++) { // 行
            for (int j = 0; j + i < length; j += cycleLen) { // 列
                ret.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < length) // 节点以外另一次插入
                    ret.append(s.charAt(j + cycleLen - i)); // 以 j + numRows - 1 为对称中心
            }
        }
        return ret.toString();
    }
}

// 当然在Z字两端判断 step *= -1 也是简洁的写法
// 
// 可惜输出不含空格,不然会有趣很多
// 可以先求转置数组再转置输出,大致可节省一半的插入时间
