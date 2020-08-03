/* 
Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.

Note:

The length of both num1 and num2 is < 5100.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */

class Solution {
    public String addStrings(String num1, String num2) {
        
    }
}



class Solution {
    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        StringBuffer ans = new StringBuffer();
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = x + y + add;
            ans.append(result % 10);
            add = result / 10;
            i--;
            j--;
        }
        return ans.reverse().toString();
    }
}



class Solution {
    public String addStrings(String num1, String num2) {
        int i = num1.length()-1;
        int j = num2.length()-1;
        StringBuilder str = new StringBuilder();
        int carry = 0;
        int sum = 0;
        while (i >=0 || j >=0) {
            sum = carry;
            
            if (i >= 0) sum += num1.charAt(i) - '0';
            if (j >= 0) sum += num2.charAt(j) - '0';
            
            str.append(sum % 10);
            carry = sum / 10;
            
            i--;
            j--;
        }
        if (carry != 0) str.append(carry);
        return str.reverse().toString();
    }
}