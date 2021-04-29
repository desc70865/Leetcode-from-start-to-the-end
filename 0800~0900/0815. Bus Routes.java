/* 
You are given an array routes representing bus routes where routes[i] is a bus route that the ith bus repeats forever.

For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in the sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
You will start at the bus stop source (You are not on any bus initially), and you want to go to the bus stop target. You can travel between bus stops by buses only.

Return the least number of buses you must take to travel from source to target. Return -1 if it is not possible.

 

Example 1:

Input: routes = [[1,2,7],[3,6,7]], source = 1, target = 6
Output: 2
Explanation: The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
Example 2:

Input: routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
Output: -1
 

Constraints:

1 <= routes.length <= 500.
1 <= routes[i].length <= 105
All the values of routes[i] are unique.
sum(routes[i].length) <= 105
0 <= routes[i][j] < 106
0 <= source, target < 106
 */

class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) return 0;
        int n = routes.length;
        for (int[] r: routes) Arrays.sort(r);
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (connected(routes[i], routes[j])) {
                    map.computeIfAbsent(i, z -> new ArrayList<>()).add(j);
                    map.computeIfAbsent(j, z -> new ArrayList<>()).add(i);
                }
            }
        }
        Set<Integer> visited = new HashSet<>();
        Set<Integer> ends = new HashSet<>();
        Deque<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (Arrays.binarySearch(routes[i], source) >= 0) {
                visited.add(i);
                queue.offer(new int[] {i, 0});
            }
            if (Arrays.binarySearch(routes[i], target) >= 0) {
                ends.add(i);
            }
        }
        while (queue.size() > 0) {
            int[] point = queue.poll();
            if (ends.contains(point[0])) return point[1] + 1;
            if (! map.containsKey(point[0])) continue;
            for (Integer next: map.get(point[0])) {
                if (visited.contains(next)) continue;
                visited.add(next);
                queue.offer(new int[] {next, point[1] + 1});
            }
        }
        return -1;
    }

    private boolean connected(int[] A, int[] B) {
        for (int i = 0, j = 0; i < A.length && j < B.length;) {
            if (A[i] == B[j]) return true;
            if (A[i] < B[j]) i++;
            else j++;
        }
        return false;
    }
}