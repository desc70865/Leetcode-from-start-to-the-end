/* 
You have a cubic storeroom where the width, length, and height of the room are all equal to n units. You are asked to place n boxes in this room where each box is a cube of unit side length. There are however some rules to placing the boxes:

You can place the boxes anywhere on the floor.
If box x is placed on top of the box y, then each side of the four vertical sides of the box y must either be adjacent to another box or to a wall.
Given an integer n, return the minimum possible number of boxes touching the floor.

 

Example 1:



Input: n = 3
Output: 3
Explanation: The figure above is for the placement of the three boxes.
These boxes are placed in the corner of the room, where the corner is on the left side.
Example 2:



Input: n = 4
Output: 3
Explanation: The figure above is for the placement of the four boxes.
These boxes are placed in the corner of the room, where the corner is on the left side.
Example 3:



Input: n = 10
Output: 6
Explanation: The figure above is for the placement of the ten boxes.
These boxes are placed in the corner of the room, where the corner is on the back side.
 

Constraints:

1 <= n <= 109
 */

/*
 * 作者：keylol
 * 链接：https://leetcode-cn.com/problems/building-boxes/solution/yao-you-ya-by-keylol-l0on/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class Solution {
    static int SIZE = 2000;
    static int[] base;
    static int[] sum;
    
    static {
        base = new int[SIZE];
        for (int i = 1; i < SIZE; i++) {
            base[i] += base[i - 1] + i;
        }
        sum = new int[SIZE];
        for (int i = 1; i < SIZE; i++) {
            sum[i] += sum[i - 1] + base[i];
        }
    }
    
    public int minimumBoxes(int n) {
        int idx = Arrays.binarySearch(sum, n);
        if (idx < 0) {
            idx = -(idx + 1);
        }
        if (n == sum[idx]) return base[idx];
        int diff = n - sum[idx - 1];
        int k = Arrays.binarySearch(base, diff);
        if (k < 0) {
            k = -(k + 1);
        }
        return base[idx - 1] + k;
    }
}