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

class Solution{
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];
        Deque<Integer> queue = new ArrayDeque<>();
        for (int right = 0; right < nums.length; right++) {
            while (! queue.isEmpty() && nums[right] >= nums[queue.peekLast()]) {
                queue.removeLast();
            }
            queue.offerLast(right);
            int left = right - k + 1;
            if (queue.peekFirst() < left) {
                queue.removeFirst();
            }
            if (right + 1 >= k) {
                ans[left] = nums[queue.peekFirst()];
            }
        }
        return ans;
    }
}



class Solution{
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        int[] ans = new int[len - k + 1];
        int[] queue = new int[len];
        for (int head = 0, rear = 0, left = 1 - k, right = 0; right < len; left++, right++) {
            while (head < rear && nums[right] >= nums[queue[rear - 1]]) {
                rear--;
            }
            queue[rear++] = right;
            if (queue[head] < left) {
                head++;
            }
            if (right + 1 >= k) {
                ans[left] = nums[queue[head]];
            }
        }
        return ans;
    }
}



class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        int[] ans = new int[len - k + 1];
        for (int left = 0, right = k - 1, maxIndex = maxNum(nums, 0, k - 1); ;) {
            if (left <= maxIndex) {
                ans[left++] = nums[maxIndex];
                right++;
                if (right == len) break;
                if (nums[right] >= nums[maxIndex]) maxIndex = right; 
            } else {
                if (nums[left] >= nums[maxIndex] - 1) {
                    maxIndex = left;
                } else if (nums[right] >= nums[maxIndex] - 1) {
                    maxIndex = right;
                } else {
                    maxIndex = maxNum(nums, left, right);
                }
            }
        }
        return ans;
    }

    public int maxNum(int[] nums, int start, int end) {
        int ans = start;
        for (int i = start + 1; i <= end; i++) {
            if (nums[i] >= nums[ans]) {
                ans = i;
            }
        }
        return ans;
    }
}