/* 
Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
 */

class Solution {
    private List<List<Integer>> res = new ArrayList<List<Integer>>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        helper(nums, 0, new ArrayList<Integer>());
        return res;
    }
    
    private void helper(int[] nums, int index, ArrayList<Integer> p) {
        if (index <= nums.length) res.add(new ArrayList<>(p));
        for (int i=index; i < nums.length; i++) {
            if (i == index || nums[i-1] != nums[i]) {
                p.add(nums[i]);
                helper(nums, i+1, p);
                p.remove(p.size() - 1);
            }
        }
    }
}

class Solution {
    private List<List<Integer>> res = new ArrayList<List<Integer>>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        helper(nums, 0, new ArrayList<Integer>());
        return res;
    }
    
    private void helper(int[] nums, int index, ArrayList<Integer> p) {
        if (index <= nums.length) res.add(new ArrayList<>(p));
        for (int i=index; i < nums.length; i++) {
            p.add(nums[i]);
            helper(nums, i+1, p);
            p.remove(p.size() - 1);
            while (i + 1 < nums.length && nums[i] == nums[i+1]) ++i;
        }
    }
}