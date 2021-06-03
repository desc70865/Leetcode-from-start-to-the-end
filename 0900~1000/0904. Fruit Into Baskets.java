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
    public int totalFruit(int[] tree) {
        int ans = 1;
        for (int sum = 1, last = -1, i = 1; i < tree.length; ++i) {
            if (tree[i] == tree[i - 1]) {
                ++sum;
            } else {
                if (last == -1 || tree[last] == tree[i]) {
                    ++sum;
                } else {
                    sum = i - last;
                }
                last = i - 1;
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }
}



class Solution {
    public int totalFruit(int[] tree) {
        int x = tree[0];
        int R = 1;
        int n = tree.length;
        while (R < n && tree[R] == x) {
            ++R;
        }
        if (R == n) {
            return n;
        }
        int y = tree[R];
        int L = 0;
        int max = 0;
        while (R < n) {
            if (tree[R] != x && tree[R] != y) {
                x = tree[R - 1];
                y = tree[R];
                max = Math.max(max, R - L);
                L = R - 1;
                while (tree[L - 1] == x) {
                    --L;
                }
            }
            ++R;
        }
        return Math.max(max, R - L);
    }
}