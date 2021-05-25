/* 
Given a positive integer n, return the number of the integers in the range [0, n] whose binary representations do not contain consecutive ones.

 

Example 1:

Input: n = 5
Output: 5
Explanation:
Here are the non-negative integers <= 5 with their corresponding binary representations:
0 : 0
1 : 1
2 : 10
3 : 11
4 : 100
5 : 101
Among them, only integer 3 disobeys the rule (two consecutive ones) and the other 5 satisfy the rule. 
Example 2:

Input: n = 1
Output: 2
Example 3:

Input: n = 2
Output: 3
 

Constraints:

1 <= n <= 109
 */

class Solution {
    static int[] f;

    static {
        f = new int[32];
        f[0] = 1;
        f[1] = 2;
        for (int i = 2; i < f.length; ++i) {
            f[i] = f[i - 1] + f[i - 2];
        }
    }

    public int findIntegers(int n) {
        int sum = 0;
        for (int i = 30, bit = 0; i >= 0; --i) {
            if ((n & (1 << i)) != 0) {
                sum += f[i];
                if (bit == 1) {
                    --sum;
                    break;
                }
                bit = 1;
            } else {
                bit = 0;
            }
        }
        return sum + 1;
    }
}