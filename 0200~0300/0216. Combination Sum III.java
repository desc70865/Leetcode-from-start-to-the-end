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
    List<List<Integer>> lists = new ArrayList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        if (n < k) {
            return lists;
        }
        
        helper(k, n, 1, new ArrayList<Integer>());
        
        return lists;
    }
    
    public void helper(int k, int n, int start, List<Integer> list){
        if (n < 0) {
            return;
        }
        
        if (k == 0) {
            if (n == 0) {
                lists.add(new ArrayList<Integer>(list));
            }
            return;
        }
        
        for (int i = start; i <= 9; i++) {
            list.add(i);
            helper(k - 1, n - i, i + 1, list);
            list.remove(list.size() - 1);
        }
    }
}

// 排列组合
// if end: add.tmp return;
// for:
// add
// recursion
// remove

class Solution {
    List<List<Integer>> ans = new ArrayList<>();
    Deque<Integer> path = new ArrayDeque<>();
    
    public List<List<Integer>> combinationSum3(int k, int n) {
        dfs(k, n, 1);
        return ans;
    }
    
    private void dfs(int k, int n, int start) {
        if (k < 0 || n < 0) {
            return;
        }
        
        if (k == 0 && n == 0) {
            if (!path.isEmpty())
                ans.add(new ArrayList<>(path));
            return;
        }
        
        for (int i = start; i < 10; i++) {
            path.push(i);
            dfs(k - 1, n - i, ++start);
            path.pop();
        }
    }
}