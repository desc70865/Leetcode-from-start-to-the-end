/* 
Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Note:

All numbers will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: k = 3, n = 7
Output: [[1,2,4]]
Example 2:

Input: k = 3, n = 9
Output: [[1,2,6], [1,3,5], [2,3,4]]
 */

class Solution {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        dfs(k, n, 1, new ArrayList<>());
        return res;
    }

    private void dfs(int k, int n, int idx, ArrayList<Integer> p) {
        if (k == 0) {
            if (n == 0) res.add(new ArrayList<>(p));
            return ;
        }
        for (int i = idx; i < 10; i++) {
            if (i > n) return ;
            p.add(i);
            dfs(k - 1, n - i, i + 1, p);
            p.remove(p.size() - 1);
        }
    }
}