/* 
A delivery company wants to build a new service centre in a new city. The company knows the positions of all the customers in this city on a 2D-Map and wants to build the new centre in a position such that the sum of the euclidean distances to all customers is minimum.

Given an array positions where positions[i] = [xi, yi] is the position of the ith customer on the map, return the minimum sum of the euclidean distances to all customers.

In other words, you need to choose the position of the service centre [xcentre, ycentre] such that the following formula is minimized:


Answers within 10^-5 of the actual value will be accepted.

 

Example 1:


Input: positions = [[0,1],[1,0],[1,2],[2,1]]
Output: 4.00000
Explanation: As shown, you can see that choosing [xcentre, ycentre] = [1, 1] will make the distance to each customer = 1, the sum of all distances is 4 which is the minimum possible we can achieve.
Example 2:


Input: positions = [[1,1],[3,3]]
Output: 2.82843
Explanation: The minimum possible sum of distances = sqrt(2) + sqrt(2) = 2.82843
Example 3:

Input: positions = [[1,1]]
Output: 0.00000
Example 4:

Input: positions = [[1,1],[0,0],[2,0]]
Output: 2.73205
Explanation: At the first glance, you may think that locating the centre at [1, 0] will achieve the minimum sum, but locating it at [1, 0] will make the sum of distances = 3.
Try to locate the centre at [1.0, 0.5773502711] you will see that the sum of distances is 2.73205.
Be careful with the precision!
Example 5:

Input: positions = [[0,1],[3,2],[4,5],[7,6],[8,9],[11,1],[2,12]]
Output: 32.94036
Explanation: You can use [4.3460852395, 4.9813795505] as the position of the centre.
 

Constraints:

1 <= positions.length <= 50
positions[i].length == 2
0 <= positions[i][0], positions[i][1] <= 100
 */

class Solution {
    static final double epsilon = 1e-6;

    public double getMinDistSum(int[][] positions) {
        double l = 0, r = 100;
        while (r - l > epsilon) {
            double mL = l + (r - l) / 3.0;
            double mR = r - (r - l) / 3.0;
            if (triSearch(positions, mL) > triSearch(positions, mR)) {
                l = mL;
            } else {
                r = mR;
            }
        }
        return triSearch(positions, l);
    }

    private double triSearch(int[][] positions, double x) {
        double d = 0, u = 100;
        while (u - d > epsilon) {
            double mD = d + (u - d) / 3;
            double mU = u - (u - d) / 3;
            if (sum(positions, x, mD) > sum(positions, x, mU)) {
                d = mD;
            } else {
                u = mU;
            }
        }
        return sum(positions, x, d);
    }

    private double sum(int[][] positions, double x, double y) {
        double res = 0;
        for (int[] p: positions) {
            res += Math.sqrt((p[0] - x) * (p[0] - x) + (p[1] - y) * (p[1] - y));
        }
        return res;
    }
}



class Solution {
    static final double epsilon = 1e-8;
    static final int[] dir = {0, 1, 0, -1, 0};

    double ans = 0.0;

    public double getMinDistSum(int[][] positions) {
        double[] centre = new double[2];
        for (int[] p: positions) {
            centre[0] += p[0];
            centre[1] += p[1];
        }
        int n = positions.length;
        centre[0] /= n;
        centre[1] /= n;
        this.ans = sum(centre,positions);
        search(centre, positions, 1.0);
        return ans;
    }

    private void search(double[] c, int[][] positions, double step) {
        if (step <= epsilon) {
            return;
        }
        double[] next = new double[2];
        int k = 0;
        for (; k < 4; ++k) {
            next[0] = c[0] + step * dir[k];
            next[1] = c[1] + step * dir[k + 1];
            double sum = sum(next, positions);
            if (sum < ans) {
                c = next;
                ans = sum;
                break;
            }
        }
        search(c, positions, k >= 4 ? step / 2.0 : step);
    }

    public double sum(double[] c, int[][] positions) {
        double res = 0;
        for (int[] p: positions) {
            res += Math.sqrt((p[0] - c[0]) * (p[0] - c[0]) + (p[1] - c[1]) * (p[1] - c[1]));
        }
        return res;
    }
}