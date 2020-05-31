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
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
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
                if (i > curIdx && candidates[i] == candidates[i - 1]) // 去重
                    continue;
                if (target < candidates[i])
                    return;
                sum += candidates[i];
                if (target < sum)
                    return;

                tmp.add(candidates[i]);
                // 不可重复选取第 i 个元素
                dfsCore(res, i+1, sum, tmp, candidates, target);
                
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

// 需要去重