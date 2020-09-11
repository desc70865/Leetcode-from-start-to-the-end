/* 
Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

Each number in candidates may only be used once in the combination.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
Example 2:

Input: candidates = [2,5,2,1,2], target = 5,
A solution set is:
[
  [1,2,2],
  [5]
]
 */

class Solution {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(candidates, target, 0, new ArrayList<>());
        return res;
    }

    private void dfs(int[] A, int t, int idx, List<Integer> p) {
        if (t == 0) {
            res.add(new ArrayList<>(p));
            return ;
        }
        for (int i = idx; i < A.length; i++) {
            if (A[i] > t) return ;
            if (i > idx && A[i] == A[i - 1]) continue;
            p.add(A[i]);
            dfs(A, t - A[i], i + 1, p);
            p.remove(p.size() - 1);
        }
    }
}