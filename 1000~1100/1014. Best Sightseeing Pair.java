/* 
Given an array A of positive integers, A[i] represents the value of the i-th sightseeing spot, and two sightseeing spots i and j have distance j - i between them.

The score of a pair (i < j) of sightseeing spots is (A[i] + A[j] + i - j) : the sum of the values of the sightseeing spots, minus the distance between them.

Return the maximum score of a pair of sightseeing spots.

 

Example 1:

Input: [8,1,5,2,6]
Output: 11
Explanation: i = 0, j = 2, A[i] + A[j] + i - j = 8 + 5 + 0 - 2 = 11
 

Note:

2 <= A.length <= 50000
1 <= A[i] <= 1000
 */

class Solution {
    public int maxScoreSightseeingPair(int[] A) {
        int len = A.length;
        for (int i = 1; i < len; i++) {
            A[i] -= i;
        }
        int max = 0;
        int curMax = A[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            max = Math.max(max, i * 2 + A[i] + curMax);
            curMax = Math.max(A[i], curMax);
        }
        return max;
    }
}



class Solution {
    public int maxScoreSightseeingPair(int[] A) {
        int max = 0;
        int curMax = A[0];
        int len = A.length;
        for (int i = 1; i < len; i++) {
            max = Math.max(max, curMax + A[i] - i);
            curMax = Math.max(A[i] + i, curMax);
        }
        return max;
    }
}