/* 
You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.

What is the maximum number of envelopes can you Russian doll? (put one inside other)

Note:
Rotation is not allowed.

Example:

Input: [[5,4],[6,4],[6,7],[2,3]]
Output: 3 
Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 */

/*
 * 作者：keylol
 * 链接：https://leetcode-cn.com/problems/russian-doll-envelopes/solution/5ms-100-by-keylol-6fr6/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        quickSort(envelopes, 0, envelopes.length - 1);
        return lengthOfLIS(envelopes);
    }

    public int lengthOfLIS(int[][] nums) {
        int[] dp = new int[nums.length];
        int R = 0;
        for (int[] num: nums) {
            int k = num[1];
            if (R == 0 || k > dp[R - 1]) {
                dp[R++] = k;
            } else {
                dp[binarySearch(dp, 0, R - 1, k)] = k;
            }
        }
        return R;
    }

    private int binarySearch(int[] arr, int l, int r, int target) {
        while (l <= r) {
            int m = l + r >> 1;
            if (arr[m] < target) {
                l = m + 1;
            } else if (arr[m] > target) {
                r = m - 1;
            } else {
                return m;
            }
        }
        return l;
    }
    
    private void quickSort(int[][] arr, int left, int right) {
        if (left >= right) return;
        int pivot = partition(arr, left, right);
        quickSort(arr, left, pivot - 1);
        quickSort(arr, pivot + 1, right);
    }
    
    private int partition(int[][] arr, int l, int r) {
        int[] pivot = arr[l];
        while (l < r) {
            while (l < r) {
                if (arr[r][0] < pivot[0] || arr[r][0] == pivot[0] && arr[r][1] > pivot[1]) {
                    arr[l++] = arr[r];
                    break;
                }
                r--;
            }
            while (l < r) {
                if (arr[l][0] > pivot[0] || arr[l][0] == pivot[0] && arr[l][1] < pivot[1]) {
                    arr[r--] = arr[l];
                    break;
                }
                l++;
            }
        }
        arr[l] = pivot;
        return l;
    }
}