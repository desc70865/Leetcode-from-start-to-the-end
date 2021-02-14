/* 
Numbers can be regarded as product of its factors. For example,

8 = 2 x 2 x 2;
  = 2 x 4.
Write a function that takes an integer n and return all possible combinations of its factors.

Note:

You may assume that n is always positive.
Factors should be greater than 1 and less than n.
Example 1:

Input: 1
Output: []
Example 2:

Input: 37
Output:[]
Example 3:

Input: 12
Output:
[
  [2, 6],
  [2, 2, 3],
  [3, 4]
]
Example 4:

Input: 32
Output:
[
  [2, 16],
  [2, 2, 8],
  [2, 2, 2, 4],
  [2, 2, 2, 2, 2],
  [2, 4, 4],
  [4, 8]
]
 */

class Solution {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> getFactors(int n) {
        dfs(n, 2, new ArrayList<>());
        return res;
    }

    private void dfs(int n, int k, List<Integer> list) {
        for (; k * k <= n; k++) {
            if (n % k == 0) {
                list.add(k);
                list.add(n / k);
                res.add(new ArrayList<>(list));
                list.remove(list.size() - 1);
                dfs(n / k, k, list);
                list.remove(list.size() - 1);
            }
        }
    }
}