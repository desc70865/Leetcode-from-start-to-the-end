/* 
Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

Examples:
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Your job is to output the median array for each window in the original array.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Median
---------------               -----
[1  3  -1] -3  5  3  6  7       1
 1 [3  -1  -3] 5  3  6  7       -1
 1  3 [-1  -3  5] 3  6  7       -1
 1  3  -1 [-3  5  3] 6  7       3
 1  3  -1  -3 [5  3  6] 7       5
 1  3  -1  -3  5 [3  6  7]      6
Therefore, return the median sliding window as [1,-1,-1,3,5,6].

Note:
You may assume k is always valid, ie: k is always smaller than input array's size for non-empty array.
Answers within 10^-5 of the actual value will be accepted as correct.
 */

class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        MedianFinder mf = new MedianFinder();
        int len = nums.length;
        double[] ans = new double[len - k + 1];
        for (int i = 0; i < len; i++) {
            mf.addNum((double) nums[i]);
            if (mf.size == k) {
                // mf.print();
                ans[i - k + 1] = mf.getMedian();
                mf.removeNum((double) nums[i - k + 1]);
            }
        }
        return ans;
    }
}

class MedianFinder {
    public int size;
    private PriorityQueue<Double> minheap;
    private PriorityQueue<Double> maxheap;

    public MedianFinder() {
        size = 0;
        minheap = new PriorityQueue<>();
        maxheap = new PriorityQueue<>(Collections.reverseOrder());
    }
    
    public void addNum(double num) {
        size++;
        maxheap.offer(num);
        minheap.offer(maxheap.poll());
        balance();
    }

    public void removeNum(double num) {
        size--;
        if (num <= maxheap.peek()) {
            maxheap.remove(num);
        } else {
            minheap.remove(num);
        }
        balance();
    }
    
    public double getMedian() {
        if ((size & 1) == 0) {
            return (maxheap.peek() + minheap.peek()) / 2;
        } else {
            return maxheap.peek();
        }
    }

    private void balance() {
        if (minheap.size() > maxheap.size()) {
            maxheap.offer(minheap.poll());
        } else if (minheap.size() < maxheap.size() - 1) {
            minheap.offer(maxheap.poll());
        }
    }

    public void print() {
        for (double num: minheap) {
            System.out.print(num + " ");
        }
        System.out.print("| ");
        for (double num: maxheap) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}