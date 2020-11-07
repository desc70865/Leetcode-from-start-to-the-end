/* 
Given an integer array nums, return the sum of divisors of the integers in that array that have exactly four divisors.

If there is no such integer in the array, return 0.

 

Example 1:

Input: nums = [21,4,7]
Output: 32
Explanation:
21 has 4 divisors: 1, 3, 7, 21
4 has 3 divisors: 1, 2, 4
7 has 2 divisors: 1, 7
The answer is the sum of divisors of 21 only.
 

Constraints:

1 <= nums.length <= 10^4
1 <= nums[i] <= 10^5
 */

class Solution {
    public int sumFourDivisors(int[] nums) {
        int sum = 0;
        for (int num: nums) sum += get(num);
        return sum;
    }

    private int get(int x) {
        if (x < 6) return 0;
        int p = -1;
        int count = 0;
        for (int i = 2; i * i <= x && count < 2; i++) {
            if (i * i == x) {
                return 0;
            }
            if (x % i == 0) {
                p = i;
                count++;
            }
        }
        if (count != 1) return 0;
        return 1 + x + p + x / p;
    }
}