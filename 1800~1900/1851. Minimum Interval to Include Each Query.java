/* 
You are given a 2D integer array intervals, where intervals[i] = [lefti, righti] describes the ith interval starting at lefti and ending at righti (inclusive). The size of an interval is defined as the number of integers it contains, or more formally righti - lefti + 1.

You are also given an integer array queries. The answer to the jth query is the size of the smallest interval i such that lefti <= queries[j] <= righti. If no such interval exists, the answer is -1.

Return an array containing the answers to the queries.

 

Example 1:

Input: intervals = [[1,4],[2,4],[3,6],[4,4]], queries = [2,3,4,5]
Output: [3,3,1,4]
Explanation: The queries are processed as follows:
- Query = 2: The interval [2,4] is the smallest interval containing 2. The answer is 4 - 2 + 1 = 3.
- Query = 3: The interval [2,4] is the smallest interval containing 3. The answer is 4 - 2 + 1 = 3.
- Query = 4: The interval [4,4] is the smallest interval containing 4. The answer is 4 - 4 + 1 = 1.
- Query = 5: The interval [3,6] is the smallest interval containing 5. The answer is 6 - 3 + 1 = 4.
Example 2:

Input: intervals = [[2,3],[2,5],[1,8],[20,25]], queries = [2,19,5,22]
Output: [2,-1,4,6]
Explanation: The queries are processed as follows:
- Query = 2: The interval [2,3] is the smallest interval containing 2. The answer is 3 - 2 + 1 = 2.
- Query = 19: None of the intervals contain 19. The answer is -1.
- Query = 5: The interval [2,5] is the smallest interval containing 5. The answer is 5 - 2 + 1 = 4.
- Query = 22: The interval [20,25] is the smallest interval containing 22. The answer is 25 - 20 + 1 = 6.
 

Constraints:

1 <= intervals.length <= 105
1 <= queries.length <= 105
queries[i].length == 2
1 <= lefti <= righti <= 107
1 <= queries[j] <= 107
 */

class Solution {
    public int[] minInterval(int[][] intervals, int[] queries) {
        int[][] query = new int[queries.length][2];
        for (int i = 0; i < query.length; ++i) {
            query[i][0] = queries[i];
            query[i][1] = i;
        }
        Arrays.sort(query, (a, b) -> a[0] - b[0]);
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int[] ans = new int[query.length];
        Arrays.fill(ans, -1);
        int idx = 0;
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> (a[1] - a[0]) - (b[1] - b[0]));
        for (int i = 0; i < query.length; ++i) {
            while (idx < intervals.length && intervals[idx][0] <= query[i][0]) {
                queue.offer(intervals[idx++]);
            }
            while (queue.size() > 0 && queue.peek()[1] < query[i][0]) {
                queue.poll();
            }
            if (queue.size() > 0) {
                ans[query[i][1]] = queue.peek()[1] - queue.peek()[0] + 1;
            }
        }
        return ans;
    }
}