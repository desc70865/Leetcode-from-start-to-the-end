/* 
Given an integer n represented as a string, return the smallest good base of n.

We call k >= 2 a good base of n, if all digits of n base k are 1's.

 

Example 1:

Input: n = "13"
Output: "3"
Explanation: 13 base 3 is 111.
Example 2:

Input: n = "4681"
Output: "8"
Explanation: 4681 base 8 is 11111.
Example 3:

Input: n = "1000000000000000000"
Output: "999999999999999999"
Explanation: 1000000000000000000 base 999999999999999999 is 11.
 

Constraints:

n is an integer in the range [3, 1018].
n does not contain any leading zeros.
 */

class Solution {
    public String smallestGoodBase(String n) {
        long num = Long.parseLong(n);
        for (int power = 59; power > 1; --power) {
            long base = (long) Math.pow(num, 1.0 / power);
            if (base <= 1) {
                continue;
            }
            long sum = 0L;
            for (int i = 0; i <= power; ++i) {
                sum = sum * base + 1;
            }
            if (sum == num) {
                return String.valueOf(base);
            }
        }
        return String.valueOf(num - 1);
    }
}