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
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> tmp = new ArrayList<>();
		// 排序去重
		Arrays.sort(candidates);
		dfsCore(res, 0, 0, tmp, candidates, target);
		return res;
	}

	private void dfsCore(List<List<Integer>> res, 
			int curIdx, int sum, List<Integer> tmp, int[] candidates,
			int target) {
        if (sum < target){
            for (int i = curIdx; i < candidates.length; i++) {
                // 剪枝
                if (target < candidates[i])
                    return;
                sum += candidates[i];
                if (target < sum)
                    return;

                tmp.add(candidates[i]);
                // 可重复选取第 i 个元素
                dfsCore(res, i, sum, tmp, candidates, target);
                
                // 回溯
                tmp.remove(tmp.size() - 1);
                sum -= candidates[i];
            }
        } else {
            if (sum == target)
                res.add(new ArrayList<Integer>(tmp));
            return;
        }
	}
}

// 这不是背包问题吗? interesting
// 除了dfs还有动态规划
// 受oj用例影响剪枝未必最优,但dfs显然在空间复杂度上不占优势
// java list类的声明及修改方法.