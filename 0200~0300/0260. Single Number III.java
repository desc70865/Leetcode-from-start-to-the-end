/* 
Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.

Example:

Input:  [1,2,1,3,2,5]
Output: [3,5]
Note:

The order of the result is not important. So in the above example, [5, 3] is also correct.
Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
 */

class Solution {
    public int[] singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num: nums) {
            if (! set.add(num)) set.remove(num);
        }
        return set.stream().mapToInt(Number::intValue).toArray();
    }
}



class Solution {
    public int[] singleNumber(int[] nums) {
        int bitmask = 0;
        for (int num : nums) {
            bitmask ^= num;
        }
        int diff = bitmask & (-bitmask);
        // bitmask = a ^ b
        // a != b
        // bitmask != 0
        // diff = lowtbit(bitmask)
        int a = 0;
        for (int num : nums) {
            if ((num & diff) != 0) {
                a ^= num;
            }
        }
        return new int[]{a, bitmask^a};
    }
}