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
    public double getMinDistSum(int[][] positions) {
        
    }
}

// 随机或指定初始位置及步长
// 四向搜索更新最小距离和并缩短步长 # 精度限制

class Solution {
    private int[][] positions;
    private boolean finish = false;
    private final int LOW = 0, HIGH = 100;
    private final double E = 0.000001;
    
    class Point {
        double x;
        double y;
        double sumDis = 0;
        
        public Point(double _x, double _y) {
            x = _x;
            y = _y;
            sumDis = calcDistance(positions, this);
        }
        public void update(Point t) {
            x = t.x;
            y = t.y;
            sumDis = t.sumDis;
        }
    }
    
    public double getMinDistSum(int[][] positions) {
        this.positions = positions;
        
        int xMin = HIGH, xMax = LOW, yMin = HIGH, yMax = LOW;
        for (int[] nums: positions) {
            xMin = Math.min(xMin, nums[0]);
            xMax = Math.max(xMax, nums[0]);
            yMin = Math.min(yMin, nums[1]);
            yMax = Math.max(yMax, nums[1]);
        }
        
        int x0 = (xMax + xMin) / 2;
        int y0 = (yMax + yMin) / 2;
        double step = (xMax - x0 + yMax - y0) / 2;
        
        int x[] = { 1, -1, 0, 0 };
        int y[] = { 0, 0, 1, -1 };
        Point res = new Point(x0, y0), t;
        
        while (step > E) {
            finish = false;
            for (int i = 0; i < 4; i++) {
                double a = res.x + step * x[i], b = res.y + step * y[i];
                /* if (check(a) || check(b)) {
                    continue;
                } */
                t = new Point(a, b);
                if (t.sumDis < res.sumDis) {
                    res.update(t);
                    finish = true;
                    break;
                }
            }
            if (!finish) {
                step /= 2;
            }
        }
        return res.sumDis;
    }
    
    private boolean check(double x) {
        return x < LOW || x > HIGH;
    }
    
    private double calcDistance(int[][] arr, Point p) {
        double sum = 0;
        for (int[] nums: arr) {
            sum += thisSqrt(p.x - nums[0], p.y - nums[1]);
        }
        return sum;
    }
    
    private double thisSqrt(double x, double y) {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
}