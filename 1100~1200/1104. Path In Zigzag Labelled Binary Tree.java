/* 
In an infinite binary tree where every node has two children, the nodes are labelled in row order.

In the odd numbered rows (ie., the first, third, fifth,...), the labelling is left to right, while in the even numbered rows (second, fourth, sixth,...), the labelling is right to left.



Given the label of a node in this tree, return the labels in the path from the root of the tree to the node with that label.

 

Example 1:

Input: label = 14
Output: [1,3,4,14]
Example 2:

Input: label = 26
Output: [1,2,6,10,26]
 

Constraints:

1 <= label <= 10^6
 */

class Solution {
    public List<Integer> pathInZigZagTree(int label) {
        
    }
}



class Solution {
    public List<Integer> pathInZigZagTree(int label) {
        ArrayList<Integer> integers = new ArrayList<>(); // 0.初始化存放结果的变量
        var a = (int) (Math.log(label) / Math.log(2)); // 2.计算label所在的层
        while (label > 1) { // 5.循环直到遇到特殊情况1
            integers.add(label); // 3.将label的结果添加到数组中
            label = (int) (3 * Math.pow(2, --a) - label / 2 - 1); // 4.计算下一个label的值
        }
        integers.add(1); // 6.添加特殊情况 1
        Collections.reverse(integers); // 7.翻转数组
        return integers; // 1.返回结果
    }
}