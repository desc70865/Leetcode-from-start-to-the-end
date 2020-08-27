/* 
Given an integer array, your task is to find all the different possible increasing subsequences of the given array, and the length of an increasing subsequence should be at least 2.

 

Example:

Input: [4, 6, 7, 7]
Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 

Constraints:

The length of the given array will not exceed 15.
The range of integer in the given array is [-100,100].
The given array may contain duplicates, and two equal integers should also be considered as a special case of increasing sequence.
 */

class Solution {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> findSubsequences(int[] nums) {
        dfs(nums, 0, new Stack<>());
        return res;
    }

    private void dfs(int[] nums, int i, Stack<Integer> p) {
        if (i == nums.length) {
            if (p.size() > 1) res.add(new ArrayList<>(p));
            return ;
        }

        if (p.isEmpty() || nums[i] >= p.peek()) {
            p.push(nums[i]);
            dfs(nums, i+1, p);
            p.pop();
        }
        if (p.isEmpty() || nums[i] != p.peek()) {
            dfs(nums, i+1, p);
        }
    }
}



class Solution {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> findSubsequences(int[] nums) {
        dfs(nums, 0, new ArrayList<Integer>());
        return res;
    }

    private void dfs(int[] nums, int i, ArrayList<Integer> p) {
        if (i == nums.length) {
            if (p.size() > 1) res.add(new ArrayList<>(p));
            return ;
        }
        
        int len = p.size(), k = len > 0 ? p.get(len - 1) : -1;

        if (len == 0 || nums[i] >= k) {
            p.add(nums[i]);
            dfs(nums, i+1, p);
            p.remove(len);
        }
        if (len == 0 || nums[i] != k) {
            dfs(nums, i+1, p);
        }
    }
}