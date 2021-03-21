/* 
Given an array of integers arr, you are initially positioned at the first index of the array.

In one step you can jump from index i to index:

i + 1 where: i + 1 < arr.length.
i - 1 where: i - 1 >= 0.
j where: arr[i] == arr[j] and i != j.
Return the minimum number of steps to reach the last index of the array.

Notice that you can not jump outside of the array at any time.

 

Example 1:

Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
Output: 3
Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of the array.
Example 2:

Input: arr = [7]
Output: 0
Explanation: Start index is the last index. You don't need to jump.
Example 3:

Input: arr = [7,6,9,6,9,6,9,7]
Output: 1
Explanation: You can jump directly from index 0 to index 7 which is last index of the array.
Example 4:

Input: arr = [6,1,9]
Output: 2
Example 5:

Input: arr = [11,22,7,7,7,7,7,7,7,22,13]
Output: 3
 

Constraints:

1 <= arr.length <= 5 * 104
-108 <= arr[i] <= 108
 */

class Solution {
    static final int INF = Integer.MAX_VALUE;

    public int minJumps(int[] arr) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            map.computeIfAbsent(arr[i], z -> new ArrayList<>()).add(i);
        }
        int[] ans = new int[len];
        Arrays.fill(ans, INF);
        ans[len - 1] = 0;
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(len - 1);
        boolean[] v = new boolean[len];
        while (ans[0] == INF) {
            int cur = queue.poll();
            if (cur - 1 >= 0 && ans[cur - 1] == INF) {
                ans[cur - 1] = ans[cur] + 1;
                queue.offer(cur - 1);
            }
            if (cur + 1 < len && ans[cur + 1] == INF) {
                ans[cur + 1] = ans[cur] + 1;
                queue.offer(cur + 1);
            }
            // if did not reach from nearby
            if (v[cur]) continue;
            for (int next: map.get(arr[cur])) {
                if (ans[next] == INF) {
                    ans[next] = ans[cur] + 1;
                    v[next] = true;
                    queue.offer(next);
                }
            }
        }
        return ans[0];
    }
}