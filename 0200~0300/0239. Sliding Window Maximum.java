/* 
You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

Return the max sliding window.

 

Example 1:

Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]
Explanation: 
Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Example 2:

Input: nums = [1], k = 1
Output: [1]
Example 3:

Input: nums = [1,-1], k = 1
Output: [1,-1]
Example 4:

Input: nums = [9,11], k = 2
Output: [11]
Example 5:

Input: nums = [4,-2], k = 2
Output: [4]
 

Constraints:

1 <= nums.length <= 105
-104 <= nums[i] <= 104
1 <= k <= nums.length
 */

class Solution {
    Deque<Integer> q;
    public int[] maxSlidingWindow(int[] nums, int k) {
        int N = nums.length;
        if (N == 0 || k == 0) return new int[0];
        q = new LinkedList<>();
        int[] res = new int[N - k + 1];
        for (int i = 0; i < k; i++) update(nums, i);
        res[0] = nums[q.peekFirst()];
        for (int i = k; i < N; i++) {
            if (q.peekFirst() == i - k) q.removeFirst();
            update(nums, i);
            res[i - k + 1] = nums[q.peekFirst()];
        }
        return res;
    }

    private void update(int[] A, int i) {
        if (! q.isEmpty() && A[i] >= A[q.peekFirst()]) {
            q.clear();
            q.addLast(i);
            return;
        }
        while (! q.isEmpty() && A[q.peekLast()] < A[i]) q.removeLast();
        q.addLast(i);
    }
}



class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int N = nums.length;
        if (N == 0 || N - k + 1 <= 0) return new int[0];
        int l = 0, r = k - 1;
        int max = -1;
        int[] res = new int[N - k + 1];
        while (r < N) {
            if (l > max) {
                max = l;
                for (int i = l + 1; i <= r; i++) {
                    if (nums[i] > nums[max]) max = i;
                }
            } else {
                if (nums[r] > nums[max]) max = r;
            }
            res[l] = nums[max];
            l++;
            r++;
        }
        return res;
    }
}



class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int N = nums.length;
        if (k == 1) return nums;
        int maxIdx = 0;
        for (int i = 0; i < k; i++) {
            if (nums[i] > nums[maxIdx]) maxIdx = i;
        }
        int[] res = new int[N - k + 1];
        for (int l = 0, r = k - 1; r < N; l++, r++) {
            if (l > maxIdx) {
                maxIdx = l;
                for (int i = l + 1; i <= r; i++) {
                    if (nums[i] > nums[maxIdx]) maxIdx = i;
                }
            } else if (nums[r] > nums[maxIdx]) {
                maxIdx = r;
            }
            res[l] = nums[maxIdx];
        }
        return res;
    }
}