/* 
Given an array of integers arr and an integer d. In one step you can jump from index i to index:

i + x where: i + x < arr.length and 0 < x <= d.
i - x where: i - x >= 0 and 0 < x <= d.
In addition, you can only jump from index i to index j if arr[i] > arr[j] and arr[i] > arr[k] for all indices k between i and j (More formally min(i, j) < k < max(i, j)).

You can choose any index of the array and start jumping. Return the maximum number of indices you can visit.

Notice that you can not jump outside of the array at any time.

 

Example 1:


Input: arr = [6,4,14,6,8,13,9,7,10,6,12], d = 2
Output: 4
Explanation: You can start at index 10. You can jump 10 --> 8 --> 6 --> 7 as shown.
Note that if you start at index 6 you can only jump to index 7. You cannot jump to index 5 because 13 > 9. You cannot jump to index 4 because index 5 is between index 4 and 6 and 13 > 9.
Similarly You cannot jump from index 3 to index 2 or index 1.
Example 2:

Input: arr = [3,3,3,3,3], d = 3
Output: 1
Explanation: You can start at any index. You always cannot jump to any index.
Example 3:

Input: arr = [7,6,5,4,3,2,1], d = 1
Output: 7
Explanation: Start at index 0. You can visit all the indicies. 
Example 4:

Input: arr = [7,1,7,1,7,1], d = 2
Output: 2
Example 5:

Input: arr = [66], d = 1
Output: 1
 

Constraints:

1 <= arr.length <= 1000
1 <= arr[i] <= 10^5
1 <= d <= arr.length
 */

class Solution {
    int[] dp;

    public int maxJumps(int[] arr, int d) {
        int len = arr.length;
        dp = new int[len];
        Arrays.fill(dp, -1);
        for (int i = 0; i < len; i++) {
            dfs(arr, i, d, len);
        }
        int max = 0;
        for (int e: dp) {
            max = Math.max(max, e);
        }
        return max;
    }

    private void dfs(int[] arr, int cur, int d, int len) {
        if (dp[cur] != -1) return;
        dp[cur] = 1;
        for (int i = cur - 1; i >= 0 && cur - i <= d && arr[cur] > arr[i]; i--) {
            dfs(arr, i, d, len);
            dp[cur] = Math.max(dp[cur], dp[i] + 1);
        }
        for (int i = cur + 1; i < len && i - cur <= d && arr[cur] > arr[i]; i++) {
            dfs(arr, i, d, len);
            dp[cur] = Math.max(dp[cur], dp[i] + 1);
        }
    }
}



class Solution {
    public int maxJumps(int[] arr, int d) {
        int len = arr.length;
        Integer[] sort = new Integer[len];
        for (int i = 0; i < len; i++) {
            sort[i] = i;
        }
        Arrays.sort(sort, Comparator.comparingInt(o -> arr[o]));
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        int ans = 0;
        for (int i = 0; i < len; i++) {
            int cur = sort[i];
            for (int j = cur - 1; j >= 0 && j >= (cur - d) && arr[j] < arr[cur]; j--) {
                dp[cur] = Math.max(dp[j] + 1, dp[cur]);
            }
            for (int j = cur + 1; j < len && j <= (cur + d) && arr[j] < arr[cur]; j++) {
                dp[cur] = Math.max(dp[j] + 1, dp[cur]);
            }
            ans = Math.max(ans, dp[cur]);
        }
        return ans;
    }
}



class Solution {
   public int maxJumps(int[] arr, int d) {
        int len = arr.length;
        int[] dp = new int[len], stack = new int[len];
        for (int i = 0, top = -1; i <= len; i++) {
            while (top >= 0 && (i == len || arr[stack[top]] < arr[i])) {
                int r = top, l = r - 1;
                while (l >= 0 && arr[stack[l]] == arr[stack[r]]) l--;
                for (int cur = l + 1; cur <= r; cur++) {
                    if (l >= 0 && stack[cur] - stack[l] <= d) dp[stack[l]] = Math.max(dp[stack[l]], 1 + dp[stack[cur]]);
                    if (i < len && i - stack[cur] <= d) dp[i] = Math.max(dp[i], 1 + dp[stack[cur]]);
                }
                top -= r - l;
            }
            stack[++top] = i;
        }
        int ans = 0;
        for (int e: dp) {
            ans = Math.max(ans, e);
        }
        return ans + 1;
    }
}