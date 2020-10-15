/* 
Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

Example 1:

Input: [1,3,4,2,2]
Output: 2
Example 2:

Input: [3,1,3,4,2]
Output: 3
Note:

You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once.
 */

class Solution {
    public int findDuplicate(int[] nums) {
        Set<Integer> hashset = new HashSet<>();
        for (int num : nums) {
            if (! hashset.contains(num)) {
                hashset.add(num);
            } else {
                return num;
            }
        }
        return -1;
    }
}



class Solution {
    public int findDuplicate(int[] nums) {
        int slow = 0, fast = 0, p = 0;

        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        while (slow != p) {
            slow = nums[slow];
            p = nums[p];
        }
        
        return slow;
    }
}



class Solution {
    public int findDuplicate(int[] nums) {
        int n = nums.length;
        int l = 1, r = n - 1, ans = -1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            int cnt = 0;
            for (int i = 0; i < n; ++i) {
                if (nums[i] <= mid) {
                    cnt++;
                }
            }
            if (cnt <= mid) {
                l = mid + 1;
            } else {
                r = mid - 1;
                ans = mid;
            }
        }
        return ans;
    }
}