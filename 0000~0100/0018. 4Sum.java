/* 
Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

Note:

The solution set must not contain duplicate quadruplets.

Example:

Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.

A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
 */

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ret = new ArrayList<>();

        if (nums == null || nums.length < 3)
            return ret;
        int len = nums.length;
        Arrays.sort(nums);
        
        for (int i = 0; i < len; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            for (int j = i + 1; j < len; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;
                int begin = j + 1;
                int end = len - 1;
                while (begin < end) {
                    int sum = nums[i] + nums[j] + nums[begin] + nums[end];
                    if (sum == target) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[begin]);
                        list.add(nums[end]);
                        ret.add(list);
                        begin++;
                        end--;
                        while (begin < end && nums[begin] == nums[begin - 1])
                            begin++;
                        while (begin < end && nums[end] == nums[end + 1])
                            end--;
                    } else if (sum > target)
                        end--;
                    else
                        begin++;
                }
            }
        }
        return ret;
    }
}

// 我都困了