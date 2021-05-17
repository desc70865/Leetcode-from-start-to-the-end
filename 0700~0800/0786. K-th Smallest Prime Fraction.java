/* 
You are given a sorted integer array arr containing 1 and prime numbers, where all the integers of arr are unique. You are also given an integer k.

For every i and j where 0 <= i < j < arr.length, we consider the fraction arr[i] / arr[j].

Return the kth smallest fraction considered. Return your answer as an array of integers of size 2, where answer[0] == arr[i] and answer[1] == arr[j].

 

Example 1:

Input: arr = [1,2,3,5], k = 3
Output: [2,5]
Explanation: The fractions to be considered in sorted order are:
1/5, 1/3, 2/5, 1/2, 3/5, and 2/3.
The third fraction is 2/5.
Example 2:

Input: arr = [1,7], k = 1
Output: [1,7]
 

Constraints:

2 <= arr.length <= 1000
1 <= arr[i] <= 3 * 104
arr[0] == 1
arr[i] is a prime number for i > 0.
All the numbers of arr are unique and sorted in strictly increasing order.
1 <= k <= arr.length * (arr.length - 1) / 2
 */

class Solution {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) ->
            arr[a[0]] * arr[b[1]] - arr[a[1]] * arr[b[0]]);
        for (int i = 1; i < arr.length; ++i) {
            pq.offer(new int[] {0, i});
        }
        while (--k > 0) {
            int[] frac = pq.poll();
            if (++frac[0] < frac[1]) {
                pq.offer(frac);
            }
        }
        return new int[] {arr[pq.peek()[0]], arr[pq.peek()[1]]};
    }
}



class Solution {
	static final double INF = 1e-9;
    int[] ans = new int[] {0, 1};
	
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        for (double left = 0.0, right = 1.0; right - left > INF;) {
            double mid = (left + right) / 2;
            int cnt = count(arr, mid);
            if (cnt > k) {
                right = mid;
            } else if (cnt < k) {
                left = mid;
            } else {
                return ans;
            }
        }
        return ans;
    }
	
    public int count(int[] arr, double target) {
        int cnt = 0;
        ans = new int[] {0, 1};
        for (int idx = 0, p = 1; p < arr.length; ++p) {
            while (arr[idx] < arr[p] * target && idx < p) {
                ++idx;
            }
            cnt += idx;
            if (idx > 0 && ans[0] * arr[p] < arr[idx - 1] * ans[1]) {
                ans[0] = arr[idx - 1];
                ans[1] = arr[p];
            }
        }
        return cnt;
    }
}