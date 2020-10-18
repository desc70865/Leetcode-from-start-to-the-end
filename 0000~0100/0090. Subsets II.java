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
    List<List<Integer>> res;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        res = new ArrayList<>();
        Arrays.sort(nums);
        helper(nums, 0, new ArrayList<Integer>());
        return res;
    }
    
    private void helper(int[] nums, int idx, ArrayList<Integer> p) {
        if (idx <= nums.length) res.add(new ArrayList<>(p));
        for (int i = idx; i < nums.length; i++) {
            if (i > idx && nums[i - 1] == nums[i]) continue;
            p.add(nums[i]);
            helper(nums, i + 1, p);
            p.remove(p.size() - 1);
        }
    }
}



class Solution {
    private List<List<Integer>> res;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        res = new ArrayList<>();
        Arrays.sort(nums);
        helper(nums, 0, new ArrayList<Integer>());
        return res;
    }

    private void helper(int[] nums, int idx, ArrayList<Integer> p) {
        if (idx == nums.length) {
            res.add(new ArrayList<>(p));
            return;
        }
        if (p.size() > 0 && nums[idx] == p.get(p.size() - 1)) ;
        else helper(nums, idx + 1, p);
        p.add(nums[idx]);
        helper(nums, idx + 1, p);
        p.remove(p.size() - 1);
    }
}