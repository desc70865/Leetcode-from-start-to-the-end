/* 
There are n workers.  The i-th worker has a quality[i] and a minimum wage expectation wage[i].

Now we want to hire exactly k workers to form a paid group.  When hiring a group of k workers, we must pay them according to the following rules:

Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
Every worker in the paid group must be paid at least their minimum wage expectation.
Return the least amount of money needed to form a paid group satisfying the above conditions.

 

Example 1:

Input: quality = [10,20,5], wage = [70,50,30], k = 2
Output: 105.00000
Explanation: We pay 70 to 0-th worker and 35 to 2-th worker.
Example 2:

Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], k = 3
Output: 30.66667
Explanation: We pay 4 to 0-th worker, 13.33333 to 2-th and 3-th workers seperately. 
 

Note:

1 <= k <= n <= 10000, where n = quality.length = wage.length
1 <= quality[i] <= 10000
1 <= wage[i] <= 10000
Answers within 10-5 of the correct answer will be considered correct.
 */

class Solution {
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int n = quality.length;
        Integer[] workers = new Integer[n];
        for (int i = 0; i < n; ++i) {
            workers[i] = i;
        }
        Arrays.sort(workers, (a, b) -> Double.compare((double) wage[a] / quality[a], (double) wage[b] / quality[b]));
        double ans = 1e9;
        int sum = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int idx: workers) {
            int score = quality[idx];
            sum += score;
            pq.offer(score);
            if (pq.size() == k) {
                ans = Math.min(ans, (double) sum * wage[idx] / quality[idx]);
                sum -= pq.poll();
            }
        }
        return ans;
    }
}