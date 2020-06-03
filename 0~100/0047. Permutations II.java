/* 
Given a collection of numbers that might contain duplicates, return all possible unique permutations.

Example:

Input: [1,1,2]
Output:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
 */

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0)
            return res;
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        helper(nums,used,new ArrayList<Integer>(), res);
        return res;
    }
    private static void helper(int[] nums, boolean [] used, List<Integer> item, List<List<Integer>> res){
        if (item.size() == nums.length) {
            res.add(new ArrayList<Integer>(item));
            return;
        }
        for (int i = 0; i<nums.length; i++) {
            if (used[i] == true) continue;
            if (i > 0 && nums[i] == nums[i-1] && used[i-1] == false) continue; 
            // 添加这行去重 当与前一个元素相同且前者已经被使用 # 前提 nums 是有序的
            used[i] = true; // 遍历
            item.add(nums[i]);
            helper(nums,used,item,res);
            item.remove(item.size()-1); // 移除最后一个(本次循环添加的)元素 # 从后往前移除
            used[i] = false; // 还原
        }
    }
}

// 函数名是个问题