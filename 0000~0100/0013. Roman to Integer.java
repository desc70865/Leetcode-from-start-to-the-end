/* 
Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9. 
X can be placed before L (50) and C (100) to make 40 and 90. 
C can be placed before D (500) and M (1000) to make 400 and 900.
Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.

Example 1:

Input: "III"
Output: 3
Example 2:

Input: "IV"
Output: 4
Example 3:

Input: "IX"
Output: 9
Example 4:

Input: "LVIII"
Output: 58
Explanation: L = 50, V= 5, III = 3.
Example 5:

Input: "MCMXCIV"
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 */

class Solution {
    public int romanToInt(String s) {
        int res = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if(c == 'I'){
                if(res >= 5)
                    res += -1;
                else
                    res += 1;
            }else if(c == 'V'){
                res += 5;
            }else if(c == 'X'){
                if(res >= 50)
                    res += -10;
                else 
                    res += 10;
            }else if(c == 'L'){
                res += 50;
            }else if(c == 'C'){
                if(res >= 500)
                    res += -100;
                else
                    res += 100;
            }else if(c == 'D'){
                res += 500;
            }else if(c == 'M'){
                res += 1000;
            }
        }
        return res;
    }
}

class Solution {
    public int romanToInt(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int prev = romanTable(s.charAt(s.length() - 1));
        int curr = 0;
        int result = prev;
        for (int i = s.length() - 2; i >= 0; i --) {
            curr = romanTable(s.charAt(i));
            if (curr < prev) {
                result -= curr;
                prev = 0;
            } else {
                result += curr;
                prev = curr;
            }
        }
        return result;
    }

    public int romanTable(char c){
        int num = 0;
        switch(c){
            case 'I':
                num = 1;
                break;
            case 'V':
                num = 5;
                break;
             case 'X':
                num = 10;
                break;
             case 'L':
                 num = 50;
                 break;
             case 'C':
                 num = 100;
                 break;
             case 'D':
                 num = 500;
                 break;
             case 'M':
                 num = 1000;
                 break;
             default:
                 num = 0;
                 break;
        }
        return num;
    }
}

// 从右往左读取,遇4,9则相减