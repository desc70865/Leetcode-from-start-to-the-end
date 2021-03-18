/* 
Given an array of integers, return indices of the two numbers such that they add up to a specific target.
You may assume that each input would have exactly one solution, and you may not use the same element twice.
Example:
Given nums = [2, 7, 11, 15], target = 9,
=======

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:

Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
 */
 
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}



class Solution {
    public int[] twoSum(int[] nums, int target) {
        int mod = 2047, c = 0;
        int[] res = new int[mod + 1];
        
        for (int i = 0; i < nums.length; i++) {
            c = (target - nums[i]) & mod;
            if (res[c] > 0) return new int[]{ res[c] - 1, i };
            res[nums[i] & mod] = i + 1;
        }
        return null;
    }
}



class Solution {
    static final long HALF = 2147483648L;
    static final long OFFSET = 10;

    public int[] twoSum(int[] nums, int target) {
        int len = nums.length;
        long[] arr = new long[len];
        for (int i = 0; i < len; i++) {
            arr[i] = ((long) nums[i] + HALF << OFFSET) + i;
        }
        Arrays.sort(arr);
        for (int l = 0, r = len - 1; l < r;) {
            long d = (arr[l] >> OFFSET) + (arr[r] >> OFFSET) - 4294967296L - target;
            if (d == 0) return new int[] {(int) (arr[l] % 1024), (int) (arr[r] % 1024)};
            else if (d < 0) l++;
            else r--;
        }
        return new int[0];
    }
}