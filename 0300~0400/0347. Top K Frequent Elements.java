/* 
Given a non-empty array of integers, return the k most frequent elements.

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]
Note:

You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
It's guaranteed that the answer is unique, in other words the set of the top k frequent elements is unique.
You can return the answer in any order.
 */

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums) {
            map.merge(num, 1, Integer::sum);
        }
        int[][] bucket = new int[map.size()][2];
        int idx = 0;
        for (int key: map.keySet()) {
            bucket[idx][0] = key;
            bucket[idx][1] = map.get(key);
            ++idx;
        }
        quickSelect(bucket, 0, bucket.length - 1, k);
        int[] ans = new int[k];
        for (int i = 0; i < k; ++i) {
            ans[i] = bucket[i][0];
        }
        return ans;
    }

    private int quickSelect(int[][] arr, int low, int high, int k) {
        int partition = partition(arr, low, high);
        if (partition == k - 1) {
            return arr[partition][1];
        } else if (partition < k - 1) {
            return quickSelect(arr, partition + 1, high, k);
        } else {
            return quickSelect(arr, low, partition - 1, k);
        }
    }

    private int partition(int[][] arr, int low, int high) {
        int pivot = arr[low][1], pivotIdx = high;
        for (int i = high; i >= low; --i) {
            if (arr[i][1] < pivot) {
                int[] tmp = arr[i];
                arr[i] = arr[pivotIdx];
                arr[pivotIdx] = tmp;
                --pivotIdx;
            }
        }
        int[] tmp = arr[low];
        arr[low] = arr[pivotIdx];
        arr[pivotIdx] = tmp;
        return pivotIdx;
    }
}