/* 
Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5
Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4
Note:
You may assume k is always valid, 1 ≤ k ≤ array's length.
 */

class Solution {
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}



class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num: nums) {
            pq.offer(num);
            if (pq.size() > k) pq.poll();
        }
        return pq.peek();
    }
}



class Solution {
    public int findKthLargest(int[] nums, int k) {
        return findKthLargest(nums, 0, nums.length - 1, k);
    }

    private int findKthLargest(int[] nums, int L, int R, int k) {
        if (L == R) return nums[L];
        int pivot = nums[L] + nums[R] >> 1;
        int l = L, r = R;
        while (true) {
            while (l <= r && nums[l] > pivot) l++;
            while (l <= r && nums[r] < pivot) r--;
            if (l <= r) swap(nums, l++, r--);
            else break;
        }
        // [r, ?, l]
        if (k <= r - L + 1) return findKthLargest(nums, L, r, k);
        else if (k >= l - L + 1) return findKthLargest(nums, l, R, k - l + L);
        else return nums[l - 1]; // || nums[r + 1]
    }

    private void swap(int[] nums, int l, int r) {
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }
}