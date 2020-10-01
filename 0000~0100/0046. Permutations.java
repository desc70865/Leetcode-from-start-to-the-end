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
    List<List<Integer>> res;
    boolean[] v;
    public List<List<Integer>> permute(int[] nums) {
        res = new ArrayList<>();
        int N = nums.length;
        v = new boolean[N];
        helper(nums, N, new ArrayList<>());
        return res;
    }

    private void helper(int[] nums, int rem, ArrayList<Integer> p) {
        if (rem == 0) {
            res.add(new ArrayList<>(p));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (v[i]) continue;
            v[i] = true;
            p.add(nums[i]);
            helper(nums, rem - 1, p);
            p.remove(p.size() - 1);
            v[i] = false;
        }
    }
}