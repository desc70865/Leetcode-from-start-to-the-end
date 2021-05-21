/* 
Let f(x) be the number of zeroes at the end of x!. (Recall that x! = 1 * 2 * 3 * ... * x, and by convention, 0! = 1.)

For example, f(3) = 0 because 3! = 6 has no zeroes at the end, while f(11) = 2 because 11! = 39916800 has 2 zeroes at the end. Given k, find how many non-negative integers x have the property that f(x) = k.

Example 1:
Input: k = 0
Output: 5
Explanation: 0!, 1!, 2!, 3!, and 4! end with k = 0 zeroes.

Example 2:
Input: k = 5
Output: 0
Explanation: There is no x such that x! ends in k = 5 zeroes.
Note:

k will be an integer in the range [0, 109].
 */

class Solution {
    public int preimageSizeFZF(int k) {
        int n = 0;
        while (n < k) {
            n = n * 5 + 1;
        }
        while (k != 0) {
            n = (n - 1) / 5;
            if (k / n == 5) {
                return 0;
            }
            k %= n;
        }
        return 5;
    }
}

// 进制转换、通向、变进制数
// 二分