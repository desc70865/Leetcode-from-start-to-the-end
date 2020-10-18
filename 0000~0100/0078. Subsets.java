/* 
Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
 */

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int N = 1 << nums.length;
        for (int i = 0; i < N; i++) res.add(f(nums, i));
        return res;
    }

    private List<Integer> f(int[] A, int x) {
        List<Integer> p = new ArrayList<>();
        while (x != 0) {
            int cur = lowbit(x);
            p.add(A[log2(cur)]);
            x ^= cur;
        }
        return p;
    }

    private int log2(int x) {
        return (int) (Math.log(x) / Math.log(2));
    }

    private int lowbit(int x) {
        return x & (-x);
    }
}



class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int N = 1 << nums.length;
        for (int i = 0; i < N; i++) res.add(f(nums, i));
        return res;
    }

    private List<Integer> f(int[] A, int idx) {
        List<Integer> p = new ArrayList<>();
        for (int i = 0; i < A.length && idx > 0; i++) {
            if ((1 << i & idx) == 0) continue;
            idx -= 1 << i;
            p.add(A[i]);
        }
        return p;
    }
}