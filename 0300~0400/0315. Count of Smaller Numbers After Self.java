/* 
You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Input: [5,2,6,1]
Output: [2,1,1,0] 
Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
 */

class Solution {
    public List<Integer> countSmaller(int[] nums) {
        
    }
}

// 树状数组 # Binary Indexed Tree
// 二叉索引树

class Solution {
    public List<Integer> countSmaller(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<>();
        }
        
        // translate nums[] values to ints >= 1, so that BIT is happy
        int minVal = Integer.MAX_VALUE;
        for (int i: nums) {
            minVal = Math.min(minVal, i);
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] += -minVal + 1;
        }
        
        // could've combined, in prev loops, but separate for clarity
        int maxVal = Integer.MIN_VALUE;
        for (int i: nums) {
            maxVal = Math.max(maxVal, i);
        }
        
        int[] BIT = new int[maxVal+1]; // ignore BIT[0], want to focus on BIT[1]...BIT[maxVal]
        
        List<Integer> res = new ArrayList<>();
        
        // build BIT going backwards through nums[]
        for(int i = nums.length - 1; i >= 0; i--) {
            // get psum[nums[i]-1]
            res.add(compute(nums[i]-1, BIT));
            // update psum[nums[i]]
            update(nums[i], BIT);
        }
        Collections.reverse(res);
        return res;
    }
    
    // finds psum[val]
    private int compute(int val, int[] BIT) {
        int res = 0;
        for (; val > 0; val -= (val & -val)) {
            res += BIT[val];
        }
        return res;
    }
    
    // effectively updates psum[val]
    private void update(int val, int[] BIT) {
        for (; val < BIT.length; val += (val & -val)) {
            BIT[val]++;
        }
    }
}