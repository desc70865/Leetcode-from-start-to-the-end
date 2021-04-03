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
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        dfs(nums, 0, new ArrayList<>(), new boolean[nums.length]);
        return ans;
    }

    private void dfs(int[] nums, int count, List<Integer> tmpList, boolean[] v) {
        if (count == nums.length) {
            ans.add(new ArrayList<>(tmpList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (v[i]) continue;
            if (i > 0 && nums[i] == nums[i - 1] && ! v[i - 1]) continue;
            v[i] = true;
            tmpList.add(nums[i]);
            dfs(nums, count + 1, tmpList, v);
            tmpList.remove(tmpList.size() - 1);
            v[i] = false;
        }
    }
}