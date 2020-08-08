/* 
Given a binary tree, return the vertical order traversal of its nodes values.

For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and (X+1, Y-1).

Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes, we report the values of the nodes in order from top to bottom (decreasing Y coordinates).

If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.

Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.

 

Example 1:



Input: [3,9,20,null,null,15,7]
Output: [[9],[3,15],[20],[7]]
Explanation: 
Without loss of generality, we can assume the root node is at position (0, 0):
Then, the node with value 9 occurs at position (-1, -1);
The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
The node with value 20 occurs at position (1, -1);
The node with value 7 occurs at position (2, -2).
Example 2:



Input: [1,2,3,4,5,6,7]
Output: [[4],[2],[1,5,6],[3],[7]]
Explanation: 
The node with value 5 and the node with value 6 have the same position according to the given scheme.
However, in the report "[1,5,6]", the node value of 5 comes first since 5 is smaller than 6.
 

Note:

The tree will have between 1 and 1000 nodes.
Each node's value will be between 0 and 1000.
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        
    }
}



class Solution {
    List<Integer>[] tmp = new ArrayList[2000];

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        helper(root, 0);
        for (int i = 0; i < 2000; i++) if (tmp[i] != null) res.add(new ArrayList(tmp[i]));
        return res;
    }

    private void helper(TreeNode node, int x) {
        if (node == null) return;
        add(x + 1000, node.val);
        helper(node.left, x - 1);
        helper(node.right, x + 1);
    }

    private void add(int idx, int val) {
        if (tmp[idx] == null) tmp[idx] = new ArrayList<>();
        tmp[idx].add(val);
    }
}



class Location implements Comparable<Location>{
    public int x;
    public int y;
    public int val;
    public Location(int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }
    
    @Override
    public int compareTo(Location that) {
        // left to right 
        if (this.x != that.x) {
            return this.x - that.x;
        // root to leaves     
        } else if (this.y != that.y) {
            return this.y - that.y;
        // smaller val first when two nodes at same position    
        } else {
            return this.val - that.val;
        }
    }
}

class Solution {
    List<Location> locations = new ArrayList<>();
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        // populate List<Location> locations
        helper(root, 0, 0);
        Collections.sort(locations);
        
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        int prev = locations.get(0).x;
        
        for (Location loc: locations) {
            // copy the same x (vertical level)
            if (loc.x != prev) {
                prev = loc.x;
                res.add(new ArrayList<>());
            }
            // since each new empty array is added in the end of res, index is res.size() - 1
            res.get(res.size() - 1).add(loc.val);
        }
        return res;
    }
    
    public void helper(TreeNode node, int x, int y){
        if (node == null) return;
        
        helper(node.left, x-1, y+1);
        locations.add(new Location(x, y, node.val));
        helper(node.right, x+1, y+1);
    }
}