/* 
Given a collection of distinct integers, return all possible permutations.

Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
 */

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(nums == null || nums.length == 0){
            return res;
        }
        boolean [] used = new boolean[nums.length];
        helper(nums,used,new ArrayList<Integer>(), res);
        return res;
    }
    private void helper(int[] nums, boolean [] used, List<Integer> item, List<List<Integer>> res){
        if(item.size() == nums.length){
            res.add(new ArrayList<Integer>(item));
            return;
        }
        for(int i = 0; i<nums.length; i++){
            if(!used[i]){
                used[i] = true; // 遍历
                item.add(nums[i]);
                helper(nums,used,item,res);
                item.remove(item.size()-1); // 移除最后一个(本次循环添加的)元素 # 从后往前移除
                used[i] = false; // 还原
            }
        }
    }
}

// 计算排列组合实际考虑的是遍历,DFS递归实现是通用解