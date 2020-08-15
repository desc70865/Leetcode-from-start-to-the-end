/* 
Given a non-empty integer array, find the minimum number of moves required to make all array elements equal, where a move is incrementing a selected element by 1 or decrementing a selected element by 1.

You may assume the array's length is at most 10,000.

Example:

Input:
[1,2,3]

Output:
2

Explanation:
Only two moves are needed (remember each move increments or decrements one element):

[1,2,3]  =>  [2,2,3]  =>  [2,2,2]
 */

class Solution {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int k = nums[nums.length / 2], res = 0;
        for (int num: nums) res += Math.abs(num - k);
        return res;
    }
}



class Solution {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int l = 0, r = nums.length - 1, res = 0;
        while (l <= r) res += nums[r--] - nums[l++];
        return res;
    }
}



class Solution {
    public int minMoves2(int[] nums) {
        int res = 0, k = quickSelect(nums, nums.length/2+1, 0, nums.length-1);
        for (int num: nums) res += Math.abs(num - k);
        return res;
    }
    
    // @return kth biggest num in A[]
    public int quickSelect(int[] A, int k, int start, int end) {
        int l = start, r = end, pivot = A[(l+r)/2];
        while (l <= r) {
            while (A[l] < pivot) l++;
            while (A[r] > pivot) r--;
            if (l >= r) break;
            swap(A, l++, r--);
        }
        if (l-start+1 > k) return quickSelect(A, k, start, l-1);
        if (l-start+1 == k && l == r) return A[l];
        return quickSelect(A, k-r+start-1, r+1, end);
    }
    
    public void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}