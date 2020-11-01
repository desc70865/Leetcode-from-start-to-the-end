/* 
In a row of trees, the i-th tree produces fruit with type tree[i].

You start at any tree of your choice, then repeatedly perform the following steps:

Add one piece of fruit from this tree to your baskets.  If you cannot, stop.
Move to the next tree to the right of the current tree.  If there is no tree to the right, stop.
Note that you do not have any choice after the initial choice of starting tree: you must perform step 1, then step 2, then back to step 1, then step 2, and so on until you stop.

You have two baskets, and each basket can carry any quantity of fruit, but you want each basket to only carry one type of fruit each.

What is the total amount of fruit you can collect with this procedure?

 

Example 1:

Input: [1,2,1]
Output: 3
Explanation: We can collect [1,2,1].
Example 2:

Input: [0,1,2,2]
Output: 3
Explanation: We can collect [1,2,2].
If we started at the first tree, we would only collect [0, 1].
Example 3:

Input: [1,2,3,2,2]
Output: 4
Explanation: We can collect [2,3,2,2].
If we started at the first tree, we would only collect [1, 2].
Example 4:

Input: [3,3,3,1,2,1,1,2,3,3,4]
Output: 5
Explanation: We can collect [1,2,1,1,2].
If we started at the first tree or the eighth tree, we would only collect 4 fruits.
 

Note:

1 <= tree.length <= 40000
0 <= tree[i] < tree.length
 */

class Solution {
    Map<Integer, Integer> map;

    public int totalFruit(int[] tree) {
        map = new HashMap<>();
        int L = 0;
        int max = 0;
        for (int R = 0; R < tree.length; R++) {
            map.merge(tree[R], 1, Integer::sum);
            while (map.size() > 2) {
                remove(tree[L++]);
            }
            max = Math.max(max, R - L + 1);
        }
        return max;
    }

    private void remove(int x) {
        map.merge(x, -1, Integer::sum);
        if (map.get(x) == 0) {
            map.remove(x);
        }
    }
}



class Solution {
    public int totalFruit(int[] tree) {
        int A = tree[0];
        int R = 1;
        int N = tree.length;
        while (R < N && tree[R] == A) R++;
        if (R == N) return N;
        int B = tree[R];
        int L = 0;
        int max = 0;
        while (R < N) {
            if (tree[R] != A && tree[R] != B) {
                A = tree[R - 1];
                B = tree[R];
                max = Math.max(max, R - L);
                L = R - 1;
                while (tree[L - 1] == A) L--;
            }
            R++;
        }
        return Math.max(max, R - L);
    }
}