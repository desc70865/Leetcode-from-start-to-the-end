/* 
Shuffle a set of numbers without duplicates.

Example:

// Init an array with set 1, 2, and 3.
int[] nums = {1,2,3};
Solution solution = new Solution(nums);

// Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
solution.shuffle();

// Resets the array back to its original configuration [1,2,3].
solution.reset();

// Returns the random shuffling of array [1,2,3].
solution.shuffle();
 */

class Solution {

    private int[] nums;
    private int[] clone;
    public Solution(int[] nums) {
        this.nums = nums;
        this.clone = nums.clone();
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int j;
        Random random = new Random();
        for (int i = nums.length-1; i > 0; i--) {
            j = random.nextInt(i+1);
            swap(i, j);
        }
        return clone;
    }
    
    private void swap(int i, int j) {
        if (i == j) {
            return;
        }
        int temp = clone[i];
        clone[i] = clone[j];
        clone[j] = temp;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */



class Solution {

    private int[] nums;
    public Solution(int[] nums) {
        this.nums = nums;
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int j;
        int[] clone = new int[nums.length];
        Random random = new Random();
        for (int i = 0; i < nums.length; i++) {
            // [0, i]
            j = random.nextInt(i+1);
            clone[i] = clone[j];
            clone[j] = nums[i];
        }
        return clone;
    }
}