/* 
Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].

You need to return the number of important reverse pairs in the given array.

Example1:

Input: [1,3,2,3,1]
Output: 2
Example2:

Input: [2,4,3,5,1]
Output: 3
Note:
The length of the given array will not exceed 50,000.
All the numbers in the input array are in the range of 32-bit integer.
 */

class Solution {
    List<Double> list;
    public int reversePairs(int[] nums) {
        list = new ArrayList<>();
        int res = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            res += search((double) nums[i] / 2);
            insert(nums[i]);
        }
        return res;
    }

    // @return
    // number of elements <= k
    private int search(double k) {
        int l = 0, r = list.size() - 1;
        while (l <= r) {
            int mid = l + r >> 1;
            if (list.get(mid) < k) l = mid + 1;
            else r = mid - 1;
        }
        return r + 1;
    }

    private void insert(double k) {
        int i = search(k);
        list.add(i, k);
    }
}



class Solution {
    public int reversePairs(int[] nums) {
        return reversePairsSub(nums, 0, nums.length - 1);
    }
    
    private int reversePairsSub(int[] nums, int l, int r) {
        if (l >= r) return 0;
        
        int m = l + r >> 1;
        int res = reversePairsSub(nums, l, m) + reversePairsSub(nums, m + 1, r);
        
        int i = l, j = m + 1, k = 0, p = m + 1;
        int[] merge = new int[r - l + 1];
        
        while (i <= m) {
            while (p <= r && nums[i] > 2L * nums[p]) p++;
            res += p - (m + 1);
            
            while (j <= r && nums[i] >= nums[j]) merge[k++] = nums[j++];
            merge[k++] = nums[i++];
        }
        
        while (j <= r) merge[k++] = nums[j++];
        
        System.arraycopy(merge, 0, nums, l, merge.length);
        
        return res;
    }
}