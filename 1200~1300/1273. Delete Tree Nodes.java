/* 
A tree rooted at node 0 is given as follows:

The number of nodes is nodes;
The value of the i-th node is value[i];
The parent of the i-th node is parent[i].
Remove every subtree whose sum of values of nodes is zero.

After doing so, return the number of nodes remaining in the tree.

 

Example 1:


Input: nodes = 7, parent = [-1,0,0,1,2,2,2], value = [1,-2,4,0,-2,-1,-1]
Output: 2
Example 2:

Input: nodes = 7, parent = [-1,0,0,1,2,2,2], value = [1,-2,4,0,-2,-1,-2]
Output: 6
Example 3:

Input: nodes = 5, parent = [-1,0,1,0,0], value = [-672,441,18,728,378]
Output: 5
Example 4:

Input: nodes = 5, parent = [-1,0,0,1,1], value = [-686,-842,616,-739,-746]
Output: 5
 

Constraints:

1 <= nodes <= 10^4
parent.length == nodes
0 <= parent[i] <= nodes - 1
parent[0] == -1 which indicates that 0 is the root.
value.length == nodes
-10^5 <= value[i] <= 10^5
The given input is guaranteed to represent a valid tree.
 */

class Solution {
    public int deleteTreeNodes(int nodes, int[] parent, int[] value) {
        List<List<Integer>> graph = new ArrayList<>(nodes);
        for (int i = 0; i < nodes; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 1; i < nodes; i++) {
            graph.get(parent[i]).add(i);
        }
        return dfs(graph, value, new int[nodes], 0);
    }

    private int dfs(List<List<Integer>> graph, int[] value, int[] cnt, int idx) {
        for (int child: graph.get(idx)) {
            cnt[idx] += dfs(graph, value, cnt, child);
            value[idx] += value[child];
        }
        return value[idx] == 0 ? 0 : cnt[idx] + 1;
    }
}