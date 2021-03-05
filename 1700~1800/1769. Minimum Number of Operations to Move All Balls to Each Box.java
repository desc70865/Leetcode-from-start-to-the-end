/* 
You have n boxes. You are given a binary string boxes of length n, where boxes[i] is '0' if the ith box is empty, and '1' if it contains one ball.

In one operation, you can move one ball from a box to an adjacent box. Box i is adjacent to box j if abs(i - j) == 1. Note that after doing so, there may be more than one ball in some boxes.

Return an array answer of size n, where answer[i] is the minimum number of operations needed to move all the balls to the ith box.

Each answer[i] is calculated considering the initial state of the boxes.

 

Example 1:

Input: boxes = "110"
Output: [1,1,3]
Explanation: The answer for each box is as follows:
1) First box: you will have to move one ball from the second box to the first box in one operation.
2) Second box: you will have to move one ball from the first box to the second box in one operation.
3) Third box: you will have to move one ball from the first box to the third box in two operations, and move one ball from the second box to the third box in one operation.
Example 2:

Input: boxes = "001011"
Output: [11,8,5,4,3,4]
 

Constraints:

n == boxes.length
1 <= n <= 2000
boxes[i] is either '0' or '1'.
 */

class Solution {
    public int[] minOperations(String boxes) {
        char[] box = boxes.toCharArray();
        int len = box.length;
        int move = 0;
        int ball = 0;
        for (int i = 1; i < len; i++) {
            if (box[i] == '1') {
                ball++;
                move += i;
            }
        }
        int[] ans = new int[len];
        ans[0] = move;
        ball -= box[0] - '0';
        for (int i = 1; i < len; i++) {
            ans[i] = ans[i - 1] - ball;
            if (box[i] == '1') {
                ball -= 2;
            }
        }
        return ans;
    }
}



class Solution {
    public int[] minOperations(String boxes) {
        char[] box = boxes.toCharArray();
        int len = box.length;
        int[] l = new int[len], r = new int[len];
        int sum = box[0] - '0';
        for (int i = 1; i < len; i++) {
            l[i] = l[i - 1] + sum;
            sum += box[i] - '0';
        }
        sum = box[len - 1] - '0';
        for (int i = len - 2; i >= 0; i--) {
            r[i] = r[i + 1] + sum;
            sum += box[i] - '0';
        }
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = l[i] + r[i];
        }
        return ans;
    }
}