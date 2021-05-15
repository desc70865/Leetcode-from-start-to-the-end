/* 
An undirected graph of n nodes is defined by edgeList, where edgeList[i] = [ui, vi, disi] denotes an edge between nodes ui and vi with distance disi. Note that there may be multiple edges between two nodes.

Given an array queries, where queries[j] = [pj, qj, limitj], your task is to determine for each queries[j] whether there is a path between pj and qj such that each edge on the path has a distance strictly less than limitj .

Return a boolean array answer, where answer.length == queries.length and the jth value of answer is true if there is a path for queries[j] is true, and false otherwise.

 

Example 1:


Input: n = 3, edgeList = [[0,1,2],[1,2,4],[2,0,8],[1,0,16]], queries = [[0,1,2],[0,2,5]]
Output: [false,true]
Explanation: The above figure shows the given graph. Note that there are two overlapping edges between 0 and 1 with distances 2 and 16.
For the first query, between 0 and 1 there is no path where each distance is less than 2, thus we return false for this query.
For the second query, there is a path (0 -> 1 -> 2) of two edges with distances less than 5, thus we return true for this query.
Example 2:


Input: n = 5, edgeList = [[0,1,10],[1,2,5],[2,3,9],[3,4,13]], queries = [[0,4,14],[1,4,13]]
Output: [true,false]
Exaplanation: The above figure shows the given graph.
 

Constraints:

2 <= n <= 105
1 <= edgeList.length, queries.length <= 105
edgeList[i].length == 3
queries[j].length == 3
0 <= ui, vi, pj, qj <= n - 1
ui != vi
pj != qj
1 <= disi, limitj <= 109
There may be multiple edges between two nodes.
 */

class Solution {
    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        int size = queries.length;
        int[][] query = new int[size][4];
        for (int i = 0; i < size; ++i) {
            System.arraycopy(queries[i], 0, query[i], 0, 3);
            query[i][3] = i;
        }
        // Arrays.sort(query, (a, b) -> a[2] - b[2]);
        // Arrays.sort(edgeList, (a, b) -> a[2] - b[2]);
        quickSort(query, 0, size - 1);
        quickSort(edgeList, 0, edgeList.length - 1);

        UnionFind uf = new UnionFind(n + 1);
        boolean[] ans = new boolean[size];
        for (int i = 0, j = 0; j < size;) {
            while (i < edgeList.length && edgeList[i][2] < query[j][2]) {
                uf.union(edgeList[i][0], edgeList[i][1]);
                ++i;
            }
            ans[query[j][3]] = uf.isConnected(query[j][0], query[j][1]);
            ++j;
        }
        return ans;
    }

    private void quickSort(int[][] arr, int left, int right) {
        if (left >= right) return;
        int pivot = partition(arr, left, right);
        quickSort(arr, left, pivot - 1);
        quickSort(arr, pivot + 1, right);
    }
    
    private int partition(int[][] arr, int l, int r) {
        int[] pivot = arr[l];
        while (l < r) {
            while (l < r) {
                if (cmp(arr[r], pivot)) {
                    arr[l++] = arr[r];
                    break;
                }
                r--;
            }
            while (l < r) {
                if (cmp(pivot, arr[l])) {
                    arr[r--] = arr[l];
                    break;
                }
                l++;
            }
        }
        arr[l] = pivot;
        return l;
    }

    private boolean cmp(int[] a, int[] b) {
        return a[2] < b[2];
    }
}

class UnionFind {
    int[] p;

    public UnionFind(int size) {
        this.p = new int[size];
        for (int i = 1; i < size; i++) {
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
            return false;
        }
        p[a] = b;
        return true;
    }

    public boolean isConnected(int a, int b) {
        return find(a) == find(b);
    }
}