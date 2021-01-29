/* 
You have n binary tree nodes numbered from 0 to n - 1 where node i has two children leftChild[i] and rightChild[i], return true if and only if all the given nodes form exactly one valid binary tree.

If node i has no left child then leftChild[i] will equal -1, similarly for the right child.

Note that the nodes have no values and that we only use the node numbers in this problem.

 

Example 1:



Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,-1,-1,-1]
Output: true
Example 2:



Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,3,-1,-1]
Output: false
Example 3:



Input: n = 2, leftChild = [1,0], rightChild = [-1,-1]
Output: false
Example 4:



Input: n = 6, leftChild = [1,-1,-1,4,-1,-1], rightChild = [2,-1,-1,5,-1,-1]
Output: false
 

Constraints:

1 <= n <= 10^4
leftChild.length == rightChild.length == n
-1 <= leftChild[i], rightChild[i] <= n - 1
 */

class Solution {
    UnionFind uf;

    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            if (helper(leftChild, i) || helper(rightChild, i)) return false;
        }
        return uf.size == 1;
    }

    private boolean helper(int[] arr, int idx) {
        if (arr[idx] == -1) {
            return false;
        }
        return uf.find(arr[idx]) != arr[idx] || uf.union(idx, arr[idx]);
    }
}

class UnionFind {
    int p[];
    int size;

    public UnionFind(int n) {
        this.p = new int[n];
        this.size = n;
        for (int i = 1; i < n; i++) {
            p[i] = i;
        }
    }

    public int find(int x) {
        return p[x] == x ? x : (p[x] = find(p[x]));
    }

    public boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) {
            return true;
        } else {
            p[b] = a;
            this.size--;
            return false;
        }
    }   
}