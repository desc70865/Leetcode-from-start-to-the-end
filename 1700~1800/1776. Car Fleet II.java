/* 
There are n cars traveling at different speeds in the same direction along a one-lane road. You are given an array cars of length n, where cars[i] = [positioni, speedi] represents:

positioni is the distance between the ith car and the beginning of the road in meters. It is guaranteed that positioni < positioni+1.
speedi is the initial speed of the ith car in meters per second.
For simplicity, cars can be considered as points moving along the number line. Two cars collide when they occupy the same position. Once a car collides with another car, they unite and form a single car fleet. The cars in the formed fleet will have the same position and the same speed, which is the initial speed of the slowest car in the fleet.

Return an array answer, where answer[i] is the time, in seconds, at which the ith car collides with the next car, or -1 if the car does not collide with the next car. Answers within 10-5 of the actual answers are accepted.

 

Example 1:

Input: cars = [[1,2],[2,1],[4,3],[7,2]]
Output: [1.00000,-1.00000,3.00000,-1.00000]
Explanation: After exactly one second, the first car will collide with the second car, and form a car fleet with speed 1 m/s. After exactly 3 seconds, the third car will collide with the fourth car, and form a car fleet with speed 2 m/s.
Example 2:

Input: cars = [[3,4],[5,4],[6,3],[9,1]]
Output: [2.00000,1.00000,1.50000,-1.00000]
 

Constraints:

1 <= cars.length <= 105
1 <= positioni, speedi <= 106
positioni < positioni+1
 */

class Solution {
    public double[] getCollisionTimes(int[][] cars) {
        int len = cars.length;
        int[] collision = new int[len];
        Arrays.fill(collision, -1);
        double[] ans = new double[len];
        ans[len - 1] = -1;
        for (int i = len - 2; i >= 0; i--) {
            ans[i] = helper(ans, collision, cars, i + 1, i);
        }
        return ans;
    }

    private double helper(double[] ans, int[] collision, int[][] cars, int pre, int cur) {
        if (collision[pre] == -1) {
            if (cars[pre][1] >= cars[cur][1]) {
                return -1;
            } else {
                collision[cur] = pre;
                return f(cars, pre, cur);
            }
        }
        double d = f(cars, pre, cur);
        if (d > 0 && d <= ans[pre]) {
            collision[cur] = pre;
            return d;
        } else {
            return helper(ans, collision, cars, collision[pre], cur);
        }
    }

    private double f(int[][] cars, int pre, int cur) {
        double m = cars[cur][0] - cars[pre][0];
        double n = cars[pre][1] - cars[cur][1];
        return m / n;
    }
}



class Solution {
    public double[] getCollisionTimes(int[][] cars) {
        int len = cars.length;
        double[] ans = new double[len];
        int[] stack = new int[len];
        int idx = -1;
        for (int i = len - 1; i >= 0; i--) {
            while (idx >= 0) {
                if (cars[stack[idx]][1] >= cars[i][1]) {
                    idx--;
                } else {
                    if (ans[stack[idx]] < 0) break;
                    if (f(cars, i, stack[idx]) <= ans[stack[idx]]) break;
                    idx--;
                }
            }
            if (idx < 0) {
                ans[i] = -1;
            } else {
                ans[i] = f(cars, i, stack[idx]);
            }
            stack[++idx] = i;
        }
        return ans;
    }

    private double f(int[][] C, int i, int j) {
        double m = C[j][0] - C[i][0];
        double n = C[i][1] - C[j][1];
        return m / n;
    }
}