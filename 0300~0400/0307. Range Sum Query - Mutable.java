/* 
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

The update(i, val) function modifies nums by updating the element at index i to val.

Example:

Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8
 

Constraints:

The array is only modifiable by the update function.
You may assume the number of calls to update and sumRange function is distributed evenly.
0 <= i <= j <= nums.length - 1
 */

class BITree{
    private int[] sums = null;

    public BITree(int len) {
        this.sums = new int[len]; // all 0 by default
    }
    
    public int lowbit(int x) {
        return x & (-x);
    }
    
    public void update(int i, int delta) {
        while (i < this.sums.length) {
            this.sums[i] += delta;
            i += lowbit(i);
        }
    }
    
    public int query(int i) {
        int ans = 0;
        while (i > 0) {
            ans += this.sums[i];
            i -= lowbit(i);
        }
        return ans;
    }
}

class NumArray {
    private BITree tree = null;
    private int[] nums = null;

    public NumArray(int[] nums) {
        this.nums = nums;
        int len = nums.length;
        this.tree = new BITree(len + 1);
        for (int i = 0; i < len; i++) {
            this.tree.update(i + 1, nums[i]);
        }
    }
    
    public void update(int i, int val) {
        this.tree.update(i + 1, val - this.nums[i]);
        this.nums[i] = val; // do not forget this line, to update nums!!!
    }
    
    public int sumRange(int i, int j) {
        return this.tree.query(j + 1) - this.tree.query(i);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.preSumRange(left,right);
 */