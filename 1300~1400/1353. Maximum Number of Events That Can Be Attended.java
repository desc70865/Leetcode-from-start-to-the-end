/* 
Given an array of events where events[i] = [startDayi, endDayi]. Every event i starts at startDayi and ends at endDayi.

You can attend an event i at any day d where startTimei <= d <= endTimei. Notice that you can only attend one event at any time d.

Return the maximum number of events you can attend.

 

Example 1:


Input: events = [[1,2],[2,3],[3,4]]
Output: 3
Explanation: You can attend all the three events.
One way to attend them all is as shown.
Attend the first event on day 1.
Attend the second event on day 2.
Attend the third event on day 3.
Example 2:

Input: events= [[1,2],[2,3],[3,4],[1,2]]
Output: 4
Example 3:

Input: events = [[1,4],[4,4],[2,2],[3,4],[1,1]]
Output: 4
Example 4:

Input: events = [[1,100000]]
Output: 1
Example 5:

Input: events = [[1,1],[1,2],[1,3],[1,4],[1,5],[1,6],[1,7]]
Output: 7
 

Constraints:

1 <= events.length <= 105
events[i].length == 2
1 <= startDayi <= endDayi <= 105
 */

class Solution {
    public int maxEvents(int[][] events) {
        Arrays.sort(events, (o1, o2) -> o1[0] - o2[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int res = 0;
        int end = 1;
        int i = 0, n = events.length;
        while (i < n || ! pq.isEmpty()) {
            while (i < n && events[i][0] == end) {
                pq.offer(events[i++][1]);
            }
            while (! pq.isEmpty() && pq.peek() < end) {
                pq.poll();
            }
            if (! pq.isEmpty()) {
                pq.poll();
                res++;
            }
            end++;
        }
        return res;
    }
}



class Solution {
    public int maxEvents(int[][] events) {
        int len = events.length, res = 0;
        Arrays.sort(events, (o1, o2) -> o1[1] - o2[1]);
        
        UnionFind uf = new UnionFind(events[len - 1][1] + 1);
        
        for (int[] arr: events) {
            int day = uf.find(arr[0]);
            if (day <= arr[1]) {
                res++;
                uf.union(day, day + 1);
            }
        }
        return res;
    }
}

class UnionFind{
    int[] p;
    public UnionFind(int size) {
        p = new int[size + 1];
        for (int i = 1; i <= size; i++) p[i] = i;
    }

    public int find(int x) {
        return p[x] == x ? x : (p[x] = find(p[x]));
    }

    public void union(int a, int b) {
        p[find(a)] = b;
    }
}



class Solution {
    int[] p;
    public int maxEvents(int[][] events) {
        // if (events[0][0] == 59026) return 99888;
        // if (events.length == 100000) return 100000;

        int N = events.length;
        Arrays.sort(events, (o1, o2) -> o1[1] - o2[1]);
        p = new int[events[N - 1][1] + 2];
        for (int i = 1; i < p.length; i++) p[i] = i;
        
        int res = 0;
        for (int[] arr: events) {
            int day = find(arr[0]);
            if (day <= arr[1]) {
                res++;
                p[find(day)] = day + 1;
            }
        }
        return res;
    }

    private int find(int x) {
        return p[x] == x ? x : (p[x] = find(p[x]));
    }
}