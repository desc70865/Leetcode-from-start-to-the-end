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

class NumArray {
    int[] nums;
    int[] sum;
    int N;
    public NumArray(int[] nums) {
        this.nums = nums;
        N = nums.length;
        sum = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
    }
    
    public void update(int i, int val) {
        int k = val - nums[i];
        nums[i++] = val;
        for (; i <= N; i++) sum[i] += k;
    }
    
    public int sumRange(int i, int j) {
        return sum[j + 1] - sum[i];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */

class BITree{
    private int[] sums = null;
    public BITree(int n) {
        this.sums = new int[n]; // all 0 by default
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
        int asum = 0;
        while (i > 0) {
            asum += this.sums[i];
            i -= lowbit(i);
        }
        return asum;
    }
}

class NumArray {
    private BITree tree = null;
    private int[] nums = null;

    public NumArray(int[] nums) {
        int N = nums.length;
        this.nums = nums;
        this.tree = new BITree(N + 1);
        for (int i = 0; i < N; i++) {
            this.tree.update(i+1, nums[i]);
        }
    }
    
    public void update(int i, int val) {
        int delta = val - this.nums[i];
        this.tree.update(i+1, delta);
        this.nums[i] = val; // do not forget this line, to update nums!!!
    }
    
    public int sumRange(int i, int j) {
        return this.tree.query(j+1) - this.tree.query(i);
    }
}