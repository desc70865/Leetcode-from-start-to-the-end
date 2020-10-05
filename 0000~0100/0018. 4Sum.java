/* 
Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

Note:

The solution set must not contain duplicate quadruplets.

Example:

Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.

A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
 */

class Solution {
    List<List<Integer>> res;
    public List<List<Integer>> fourSum(int[] nums, int target) {
        res = new ArrayList<>();
        Arrays.sort(nums);
        int N = nums.length;
        for (int i = 0; i < N; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < N; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                helper(nums, i, j, target, j + 1, N - 1);
            }
        }
        return res;
    }

    private void helper(int[] A, int i, int j, int target, int l, int r) {
        target -= A[i] + A[j];
        List<Integer> p = new ArrayList<>();
        p.add(A[i]);
        p.add(A[j]);
        while (l < r) {
            if (A[l] + A[r] == target) {
                p.add(A[l++]);
                p.add(A[r--]);
                res.add(new ArrayList<>(p));
                p.remove(3);
                p.remove(2);
                while (l < r && A[l] == A[l - 1]) l++;
                while (l < r && A[r] == A[r + 1]) r--;
            } else if (A[l] + A[r] < target) l++;
            else r--;
        }
    }
}



class Solution {
    List<List<Integer>> res;
    public List<List<Integer>> fourSum(int[] nums, int target) {
        res = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, 0, 4, target, new ArrayList<>());
        return res;
    }

    private void dfs(int[] A, int idx, int rem, int target, List<Integer> p) {
        if (rem == 0) {
            if (target == 0) res.add(new ArrayList<>(p));
            return;
        }
        for (int i = idx; i < A.length; i++) {
            if (i > idx && A[i] == A[i - 1]) continue;
            if (A[i] > 0 && target < A[i]) return;
            p.add(A[i]);
            dfs(A, i + 1, rem - 1, target - A[i], p);
            p.remove(p.size() - 1);
        }
    }
}