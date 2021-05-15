/* 
A binary expression tree is a kind of binary tree used to represent arithmetic expressions. Each node of a binary expression tree has either zero or two children. Leaf nodes (nodes with 0 children) correspond to operands (numbers), and internal nodes (nodes with 2 children) correspond to the operators '+' (addition), '-' (subtraction), '*' (multiplication), and '/' (division).

For each internal node with operator o, the infix expression that it represents is (A o B), where A is the expression the left subtree represents and B is the expression the right subtree represents.

You are given a string s, an infix expression containing operands, the operators described above, and parentheses '(' and ')'.

Return any valid binary expression tree, which its in-order traversal reproduces s after omitting the parenthesis from it (see examples below).

Please note that order of operations applies in s. That is, expressions in parentheses are evaluated first, and multiplication and division happen before addition and subtraction.

Operands must also appear in the same order in both s and the in-order traversal of the tree.

 

Example 1:


Input: s = "3*4-2*5"
Output: [-,*,*,3,4,2,5]
Explanation: The tree above is the only valid tree whose inorder traversal produces s.
Example 2:


Input: s = "2-3/(5*2)+1"
Output: [+,-,1,2,/,null,null,null,null,3,*,null,null,5,2]
Explanation: The inorder traversal of the tree above is 2-3/5*2+1 which is the same as s without the parenthesis. The tree also produces the correct result and its operands are in the same order as they appear in s.
The tree below is also a valid binary expression tree with the same inorder traversal as s, but it not a valid answer because it does not evaluate to the same value.

The third tree below is also not valid. Although it produces the same result and is equivalent to the above trees, its inorder traversal does not produce s and its operands are not in the same order as s.

Example 3:

Input: s = "1+2+3+4+5"
Output: [+,+,5,+,4,null,null,+,3,null,null,1,2]
Explanation: The tree [+,+,5,+,+,null,null,1,2,3,4] is also one of many other valid trees.
 

Constraints:

1 <= s.length <= 1000
s consists of digits and the characters '+', '-', '*', and '/'.
Operands in s are exactly 1 digit.
It is guaranteed that s is a valid expression.
 */

/**
 * Definition for a binary tree node.
 * class Node {
 *     char val;
 *     Node left;
 *     Node right;
 *     Node() {this.val = ' ';}
 *     Node(char val) { this.val = val; }
 *     Node(char val, Node left, Node right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    char[] expression;

    public Node expTree(String s) {
        this.expression = s.toCharArray();
        return buildTree(0, expression.length - 1);
    }

    private Node buildTree(int left, int right) {
        char operator = '+';
        Node node = null;
        Node[] nodes = new Node[right - left + 1];
        char[] operators = new char[right - left + 1];
        int size = -1;
        for (int l = left; l <= right; ++l) {
            if (expression[l] == '(') {
                int r = l + 1;
                for (int bracket = 1; bracket > 0; ++r) {
                    if (expression[r] == '(') {
                        ++bracket;
                    } else if (expression[r] == ')') {
                        --bracket;
                    }
                }
                --r;
                node = buildTree(l + 1, r - 1);
                l = r;
            } else if (expression[l] >= '0') {
                node = new Node(expression[l]);
            } else {
                size = buildTreeHelper(operators, nodes, operator, node, size);
                node = null;
                operator = expression[l];
            }
        }
        size = buildTreeHelper(operators, nodes, operator, node, size);
        Node ans = nodes[0];
        for (int i = 1; i <= size; ++i) {
            ans = new Node(operators[i], ans, nodes[i]);
        }
        return ans;
    }

    private int buildTreeHelper(char[] operators, Node[] nodes, char operator, Node node, int size) {
        if (operator == '*' || operator == '/') {
            nodes[size] = new Node(operator, nodes[size], node);
        } else {
            ++size;
            nodes[size] = node;
            operators[size] = operator;
        }
        return size;
    }
}

// https://leetcode-cn.com/problems/basic-calculator-iii/