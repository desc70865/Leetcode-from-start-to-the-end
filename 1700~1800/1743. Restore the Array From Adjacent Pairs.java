/* 
There is an integer array nums that consists of n unique elements, but you have forgotten it. However, you do remember every pair of adjacent elements in nums.

You are given a 2D integer array adjacentPairs of size n - 1 where each adjacentPairs[i] = [ui, vi] indicates that the elements ui and vi are adjacent in nums.

It is guaranteed that every adjacent pair of elements nums[i] and nums[i+1] will exist in adjacentPairs, either as [nums[i], nums[i+1]] or [nums[i+1], nums[i]]. The pairs can appear in any order.

Return the original array nums. If there are multiple solutions, return any of them.

 

Example 1:

Input: adjacentPairs = [[2,1],[3,4],[3,2]]
Output: [1,2,3,4]
Explanation: This array has all its adjacent pairs in adjacentPairs.
Notice that adjacentPairs[i] may not be in left-to-right order.
Example 2:

Input: adjacentPairs = [[4,-2],[1,4],[-3,1]]
Output: [-2,4,1,-3]
Explanation: There can be negative numbers.
Another solution is [-3,1,4,-2], which would also be accepted.
Example 3:

Input: adjacentPairs = [[100000,-100000]]
Output: [100000,-100000]
 

Constraints:

nums.length == n
adjacentPairs.length == n - 1
adjacentPairs[i].length == 2
2 <= n <= 105
-105 <= nums[i], ui, vi <= 105
There exists some nums that has adjacentPairs as its pairs.
 */

class Solution {
    public int[] restoreArray(int[][] adjacentPairs) {
        Map<Integer, Node> map = new HashMap<>();
        for (int[] pair: adjacentPairs) {
            for (int num: pair) {
                map.putIfAbsent(num, new Node(num));
            }
            union(map.get(pair[0]), map.get(pair[1]));
        }
        Node s = new Node(-1);
        for (Node x: map.values()) {
            if (x.b == null) {
                s = x;
                break;
            }
        }
        int len = adjacentPairs.length + 1;
        int[] ans = new int[len];
        ans[0] = s.val;
        for (int i = 1; i < len; i++) {
            if (i > 1 && ans[i - 2] == s.a.val) {
                s = s.b;
            } else {
                s = s.a;
            }
            ans[i] = s.val;
        }
        return ans;
    }
    
    private void union(Node a, Node b) {
        if (a.a == null) {
            a.a = b;
        } else {
            a.b = b;
        }
        if (b.a == null) {
            b.a = a;
        } else {
            b.b = a;
        }
    }
}

class Node {
    int val;
    Node a, b;
    
    public Node(int val) {
        this.val = val;
    }
}