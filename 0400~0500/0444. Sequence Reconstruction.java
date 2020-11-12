/* 
Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs. The org sequence is a permutation of the integers from 1 to n, with 1 ≤ n ≤ 104. Reconstruction means building a shortest common supersequence of the sequences in seqs (i.e., a shortest sequence so that all sequences in seqs are subsequences of it). Determine whether there is only one sequence that can be reconstructed from seqs and it is the org sequence.

 

Example 1:

Input: org = [1,2,3], seqs = [[1,2],[1,3]]
Output: false
Explanation: [1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be reconstructed.
Example 2:

Input: org = [1,2,3], seqs = [[1,2]]
Output: false
Explanation: The reconstructed sequence can only be [1,2].
Example 3:

Input: org = [1,2,3], seqs = [[1,2],[1,3],[2,3]]
Output: true
Explanation: The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].
Example 4:

Input: org = [4,1,5,2,6,3], seqs = [[5,2,6,3],[4,1,5,2]]
Output: true
 

Constraints:

1 <= n <= 10^4
org is a permutation of {1,2,...,n}.
1 <= segs[i].length <= 10^5
seqs[i][j] fits in a 32-bit signed integer.
 

UPDATE (2017/1/8):
The seqs parameter had been changed to a list of list of strings (instead of a 2d array of strings). Please reload the code definition to get the latest changes.
 */

class Solution {
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        // 没有依赖关系
        if (seqs == null || org == null) throw new IllegalArgumentException("invalid param");

        int verNum = org.length;
        // 图
        Node[] graph = new Node[verNum + 1];
        // 入度
        int[] degree = new int[verNum + 1];
        // 标记结点是否在边中出现
        boolean[] flag = new boolean[verNum + 1];
        for (List<Integer> seq : seqs) {
            // 输入存在为空的情况
            if (seq.isEmpty()) continue;
            int from = -1;
            for (int to : seq) {
                // 结点索引为负数或大于规定的结点数目
                if (to > verNum || to <= 0) {
                    return false;
                }
                if (from != -1) {
                    //Node temp = graph[from];
                    //while (temp != null) {
                      //  if (temp.ver == to) break;
                        //temp = temp.next;
                    //}
                    // 存在重复，不加入图
                    //if (temp != null && temp.ver == to) continue;

                    graph[from] = new Node(to, graph[from]);
                    degree[to]++;
                }
                flag[to] = true;
                from = to;
            }
        }
        // 初始化队列
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= verNum; i++) {
            if (flag[i] && degree[i] == 0) queue.add(i);
        }
        // org遍历索引
        int idx = 0;
        while (!queue.isEmpty()) {
            // 无法唯一决定依赖
            if (queue.size() != 1) return false;
            // 判断是否与org相符
            int cur = queue.poll();
            // org短于序列或值不相等
            if (idx >= org.length || cur != org[idx++]) return false;

            Node temp = graph[cur];
            while (temp != null) {
                // 入度为0，加入
                if (--degree[temp.ver] == 0) queue.add(temp.ver);
                temp = temp.next;
            }
        }
        // org长于序列
        if (idx < org.length) return false;
        // 存在环或孤点
        for (int i = 1; i <= verNum; i++) {
            if (flag[i] && degree[i] > 0) return false;
        }
        return true;
    }
}

class Node {
    int ver;
    Node next;

    Node(int ver, Node next) {
        this.ver = ver;
        this.next = next;
    }
}