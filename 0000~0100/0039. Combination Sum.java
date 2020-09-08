/* 
Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]
Example 2:

Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
 */

class Solution {
    int[] set;
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> p = new ArrayList<>();
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        this.set = candidates;
        search(target, 0);
        return res;
    }

    private void search(int t, int s) {
        if (t == 0) {
            res.add(new ArrayList<>(p));
            return ;
        }
        for (int i = s; i < set.length; i++) {
            if (t < set[i]) continue;
            p.add(set[i]);
            search(t - set[i], i);
            p.remove(p.size() - 1);
        }
    }
}