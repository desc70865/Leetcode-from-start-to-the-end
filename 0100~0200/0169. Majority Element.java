/* 
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

Example 1:

Input: [3,2,3]
Output: 3
Example 2:

Input: [2,2,1,1,1,2,2]
Output: 2
 */

class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}



class Solution {
    public int majorityElement(int[] nums) {
       return findMajority(nums, 0);
    }
    
    public int findMajority(int[] nums, int startIndex){
        int pivot = nums[startIndex];
        int count = 1;
        
        for (int i = startIndex+1; i < nums.length; i++) {
            if (nums[i] == pivot) {
                count++;
            } else {
                count--;
            }
            
            if (count == 0) {
                return findMajority(nums, i+1); 
            }
        }
        return pivot;
    }
}