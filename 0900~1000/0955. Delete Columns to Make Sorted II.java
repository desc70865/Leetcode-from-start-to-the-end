/* 
We are given an array A of N lowercase letter strings, all of the same length.

Now, we may choose any set of deletion indices, and for each string, we delete all the characters in those indices.

For example, if we have an array A = ["abcdef","uvwxyz"] and deletion indices {0, 2, 3}, then the final array after deletions is ["bef","vyz"].

Suppose we chose a set of deletion indices D such that after deletions, the final array has its elements in lexicographic order (A[0] <= A[1] <= A[2] ... <= A[A.length - 1]).

Return the minimum possible value of D.length.

 

Example 1:

Input: ["ca","bb","ac"]
Output: 1
Explanation: 
After deleting the first column, A = ["a", "b", "c"].
Now A is in lexicographic order (ie. A[0] <= A[1] <= A[2]).
We require at least 1 deletion since initially A was not in lexicographic order, so the answer is 1.
Example 2:

Input: ["xc","yb","za"]
Output: 0
Explanation: 
A is already in lexicographic order, so we don't need to delete anything.
Note that the rows of A are not necessarily in lexicographic order:
ie. it is NOT necessarily true that (A[0][0] <= A[0][1] <= ...)
Example 3:

Input: ["zyx","wvu","tsr"]
Output: 3
Explanation: 
We have to delete every column.
 

Note:

1 <= A.length <= 100
1 <= A[i].length <= 100
 */

/*
 * 作者：keylol
 * 链接：https://leetcode-cn.com/problems/delete-columns-to-make-sorted-ii/solution/sao-miao-by-keylol/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class Solution {
    public int minDeletionSize(String[] A) {
        int m = A.length;
        int n = A[0].length();
        char[][] dict = new char[m][n];
        for (int i = 0; i < m; i++) {
            dict[i] = A[i].toCharArray();
        }
        boolean[] done = new boolean[m];
        int res = 0;
        for (int j = 0; j < n; j++) {
            int i = 1;
            boolean[] next = new boolean[m];
            boolean end = true;
            while (i < m) {
                next[i - 1] = done[i - 1] | (dict[i - 1][j] < dict[i][j]);
                end &= next[i - 1];
                if (! done[i - 1] && dict[i - 1][j] > dict[i][j]) {
                    res++;
                    break;
                }
                i++;
            }
            if (i < m) continue;
            if (end) break;
            done = next;
        }
        return res;
    }
}