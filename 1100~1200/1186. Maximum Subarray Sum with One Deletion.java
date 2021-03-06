/* 
Given an array of integers, return the maximum sum for a non-empty subarray (contiguous elements) with at most one element deletion. In other words, you want to choose a subarray and optionally delete one element from it so that there is still at least one element left and the sum of the remaining elements is maximum possible.

Note that the subarray needs to be non-empty after deleting one element.

 

Example 1:

Input: arr = [1,-2,0,3]
Output: 4
Explanation: Because we can choose [1, -2, 0, 3] and drop -2, thus the subarray [1, 0, 3] becomes the maximum value.
Example 2:

Input: arr = [1,-2,-2,3]
Output: 3
Explanation: We just choose [3] and it's the maximum sum.
Example 3:

Input: arr = [-1,-1,-1,-1]
Output: -1
Explanation: The final subarray needs to be non-empty. You can't choose [-1] and delete -1 from it, then get an empty subarray to make the sum equals to 0.
 

Constraints:

1 <= arr.length <= 10^5
-10^4 <= arr[i] <= 10^4
 */

class Solution {
    static final int INF = -200001;
    public int maximumSum(int[] arr) {
        int N = arr.length;
        int[] sum = new int[N];
        int[] del = new int[N];
        sum[0] = arr[0];
        del[0] = INF;
        int max = arr[0];
        for (int i = 1; i < N; i++) {
            sum[i] = arr[i] + Math.max(sum[i - 1], 0);
            del[i] = Math.max(del[i - 1] + arr[i], sum[i - 1]);
            max = Math.max(max, Math.max(sum[i], del[i]));
        }
        return max;
    }
}



class Solution {
    public int maximumSum(int[] arr) {
        int N = arr.length;
        int[] preSum = new int[N];
        preSum[0] = Math.max(arr[0], 0);
        for (int i = 1; i < N; i++) {
            preSum[i] = Math.max(preSum[i - 1] + arr[i], 0);
        }
        int max = arr[0];
        int sum = 0;
        for (int i = N - 1; i >= 0; i--) {
            sum = Math.max(sum, 0) + arr[i];
            max = Math.max(max, sum);
            if (i >= 2) {
                max = Math.max(max, sum + preSum[i - 2]);
            }
        }
        return max;
    }
}