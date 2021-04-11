/* 
Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 

Example:

Input: n = 10
Output: 12
Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
Note:  

1 is typically treated as an ugly number.
n does not exceed 1690.
 */

class Ugly {
    private static int LEN = 1690;
    public static int[] nums;
    
    public Ugly() {
        nums = new int[LEN];
        nums[0] = 1;
        for (int i = 1, a = 0, b = 0, c = 0; i < LEN; i++) {
            nums[i] = Math.min(nums[a] * 2, Math.min(nums[b] * 3, nums[c] * 5));
            if (nums[i] == nums[a] * 2) a++;
            if (nums[i] == nums[b] * 3) b++;
            if (nums[i] == nums[c] * 5) c++;
        }
    }
}

class Solution {
    static Ugly ugly = new Ugly();

    public int nthUglyNumber(int n) {
        return ugly.nums[n - 1];
    }
}