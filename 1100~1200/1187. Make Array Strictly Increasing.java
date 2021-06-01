/* 
Given two integer arrays arr1 and arr2, return the minimum number of operations (possibly zero) needed to make arr1 strictly increasing.

In one operation, you can choose two indices 0 <= i < arr1.length and 0 <= j < arr2.length and do the assignment arr1[i] = arr2[j].

If there is no way to make arr1 strictly increasing, return -1.

 

Example 1:

Input: arr1 = [1,5,3,6,7], arr2 = [1,3,2,4]
Output: 1
Explanation: Replace 5 with 2, then arr1 = [1, 2, 3, 6, 7].
Example 2:

Input: arr1 = [1,5,3,6,7], arr2 = [4,3,1]
Output: 2
Explanation: Replace 5 with 3 and then replace 3 with 4. arr1 = [1, 3, 4, 6, 7].
Example 3:

Input: arr1 = [1,5,3,6,7], arr2 = [1,6,3,3]
Output: -1
Explanation: You can't make arr1 strictly increasing.
 

Constraints:

1 <= arr1.length, arr2.length <= 2000
0 <= arr1[i], arr2[i] <= 10^9
 */

class Solution {
    static final int INF = 1_000_000_007;

    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        Arrays.sort(arr2);
        int m = 1;
        for (int i = 1; i < arr2.length; ++i) {
            if (arr2[m - 1] != arr2[i]) {
                arr2[m++] = arr2[i];
            }
        }
        int n = arr1.length + 2;
        int[] arr = new int[n];
        arr[0] = -1;
        System.arraycopy(arr1, 0, arr, 1, n - 2);
        arr[n - 1] = INF + 2;
        int[] dp = new int[n];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int i = 1; i < n; ++i) {
            int j = bs(arr2, arr[i], m);
            for (int k = 1; k <= Math.min(i - 1, j); ++k) {
                if (arr[i - 1 - k] < arr2[j - k]) {
                    dp[i] = Math.min(dp[i], dp[i - k - 1] + k);
                }
            }
            if (arr[i - 1] < arr[i]) {
                dp[i] = Math.min(dp[i], dp[i - 1]);
            }
        }
        int ans = dp[n - 1];
        return ans >= INF ? -1 : ans;
    }

    private int bs(int[] list, int target, int len) {
        int lo = 0, hi = len;
        while (lo < hi) {
            int mi = lo + hi >> 1;
            if (list[mi] >= target) {
                hi = mi;
            } else {
                lo = mi + 1;
            }
        }
        return lo;
    }
}