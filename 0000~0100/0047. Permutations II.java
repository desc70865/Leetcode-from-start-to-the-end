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
    List<List<Integer>> res;
    boolean[] visited;
    int N;

    public List<List<Integer>> permuteUnique(int[] nums) {
        res = new ArrayList<>();
        N = nums.length;
        visited = new boolean[N];
        Arrays.sort(nums);
        dfs(nums, 0, new ArrayList<>());
        return res;
    }

    private void dfs(int[] nums, int idx, List<Integer> p) {
        if (idx == N) {
            res.add(new ArrayList<>(p));
            return;
        }
        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            if (i > 0 && nums[i] == nums[i - 1] && ! visited[i - 1]) continue;
            visited[i] = true;
            p.add(nums[i]);
            dfs(nums, idx + 1, p);
            p.remove(p.size() - 1);
            visited[i] = false;
        }
    }
}