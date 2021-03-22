/* 
Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.

Note:
If n is the length of array, assume the following constraints are satisfied:

1 ≤ n ≤ 1000
1 ≤ m ≤ min(50, n)
Examples:

Input:
nums = [7,2,5,10,8]
m = 2

Output:
18

Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8],
where the largest sum among the two subarrays is only 18.
 */

class Solution {
    public int splitArray(int[] nums, int m) {
        
    }
}



class Solution {
    public int splitArray(int[] nums, int m) {
        int low = 0, high = 0;
        for (int num: nums) {
            high += num;
            low = Math.max(low, num);
        }
        while (low < high) {
            int mid = low + high >> 1;
            if (check(nums, mid, m)) high = mid;
            else low = mid + 1;
        }
        return low;
    }

    public boolean check(int[] nums, int threshold, int m) {
        int sum = 0, cnt = 1;
        for (int num: nums) {
            sum += num;
            if (sum > threshold) {
                if (++cnt > m) return false;
                sum = num;
            }
        }
        return true;
    }
}