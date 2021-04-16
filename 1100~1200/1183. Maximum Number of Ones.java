/* 
Consider a matrix M with dimensions width * height, such that every cell has value 0 or 1, and any square sub-matrix of M of size sideLength * sideLength has at most maxOnes ones.

Return the maximum possible number of ones that the matrix M can have.

 

Example 1:

Input: width = 3, height = 3, sideLength = 2, maxOnes = 1
Output: 4
Explanation:
In a 3*3 matrix, no 2*2 sub-matrix can have more than 1 one.
The best solution that has 4 ones is:
[1,0,1]
[0,0,0]
[1,0,1]
Example 2:

Input: width = 3, height = 3, sideLength = 2, maxOnes = 2
Output: 6
Explanation:
[1,0,1]
[1,0,1]
[1,0,1]
 

Constraints:

1 <= width, height <= 100
1 <= sideLength <= width, height
0 <= maxOnes <= sideLength * sideLength
 */

class Solution {
    public int maximumNumberOfOnes(int width, int height, int sideLength, int maxOnes) {
        int a = width / sideLength, b = height / sideLength;
        int c = width % sideLength, d = height % sideLength;
        int common = c * d;
        if (maxOnes <= common) {
            return (a + 1) * (b + 1) * maxOnes;
        } else if (maxOnes >= (c + d) * sideLength - c * d) {
            return c * height + d * width - c * d + a * b * maxOnes;
        }
        int m = maxOnes - common;
        int s1 = 0, s2 = 0;
        if (a >= b) {
            s1 = Math.min(m, d * (sideLength - c));
            s2 = m - s1;
        } else {
            s2 = Math.min(m, c * (sideLength - d));
            s1 = m - s2;
        }
        return a * b * maxOnes + common + a * (common + s1) + b * (common + s2);
    }
}