/* 
You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

 

Follow up:

You may only use constant extra space.
Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.
 

Example 1:

https://assets.leetcode.com/uploads/2019/02/14/116_sample.png

Input: root = [1,2,3,4,5,6,7]
Output: [1,#,2,3,#,4,5,6,7,#]
Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
 */

/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

// Queue<Pair<Node, Integer>>
// if (!depth.equals) pre.next = null; else pre.next = cur;
// pre = cur;
// $&^*

class Solution {
    public Node connect(Node root) {
        dfs(root, null);
        return root;
    }

    private void dfs(Node node, Node next) {
        if (node != null) {
            node.next = next;
            dfs(node.left, node.right);
            dfs(node.right, node.next != null ? node.next.left : null);
        }
    }
}

class Solution {
    public void connect_r(Node root){
		if (root == null) return;
		Node left = root.left;
		Node right = root.right;
		while (left != null) {
			left.next = right;
			left = left.right;
			right = right.left;
		}
		connect_r(root.left);
		connect_r(root.right);
	}

	public Node connect(Node root) {
		connect_r(root);
		return root;
	}
}



class Solution {
    public Node connect(Node root) {
        if (root == null) return root;
        Deque<Node> q = new LinkedList<>();
        q.offerLast(root);
        while (! q.isEmpty()) {
            int N = q.size();
            Node cur = q.pollFirst();
            if (cur.left != null) q.offerLast(cur.left);
            if (cur.right != null) q.offerLast(cur.right);
            for (int i = 1; i < N; i++) {
                cur.next = q.pollFirst();
                cur = cur.next;
                if (cur.left != null) q.offerLast(cur.left);
                if (cur.right != null) q.offerLast(cur.right);
            }
        }
        return root;
    }
}