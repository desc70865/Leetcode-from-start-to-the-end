/* 
There is an infrastructure of n cities with some number of roads connecting these cities. Each roads[i] = [ai, bi] indicates that there is a bidirectional road between cities ai and bi.

The network rank of two different cities is defined as the total number of directly connected roads to either city. If a road is directly connected to both cities, it is only counted once.

The maximal network rank of the infrastructure is the maximum network rank of all pairs of different cities.

Given the integer n and the array roads, return the maximal network rank of the entire infrastructure.

 

Example 1:



Input: n = 4, roads = [[0,1],[0,3],[1,2],[1,3]]
Output: 4
Explanation: The network rank of cities 0 and 1 is 4 as there are 4 roads that are connected to either 0 or 1. The road between 0 and 1 is only counted once.
Example 2:



Input: n = 5, roads = [[0,1],[0,3],[1,2],[1,3],[2,3],[2,4]]
Output: 5
Explanation: There are 5 roads that are connected to cities 1 or 2.
Example 3:

Input: n = 8, roads = [[0,1],[1,2],[2,3],[2,4],[5,6],[5,7]]
Output: 5
Explanation: The network rank of 2 and 5 is 5. Notice that all the cities do not have to be connected.
 

Constraints:

2 <= n <= 100
0 <= roads.length <= n * (n - 1) / 2
roads[i].length == 2
0 <= ai, bi <= n-1
ai != bi
Each pair of cities has at most one road connecting them.
 */

class Solution {
    public int maximalNetworkRank(int n, int[][] roads) {
        int[][] map = new int[n][n];
        int[][] cnt = new int[n][2];
        for (int i = 0; i < n; i++) cnt[i][0] = i;
        for (int[] r: roads) {
            map[r[0]][r[1]]++;
            map[r[1]][r[0]]++;
            cnt[r[0]][1]++;
            cnt[r[1]][1]++;
        }
        Arrays.sort(cnt, (a, b) -> a[1] - b[1]);
        int x = cnt[n-1][0], y = cnt[n-2][0];
        int a = cnt[n-1][1], b = cnt[n-2][1];
        if (map[x][y] == 0) return a + b;
        for (int i = n - 3; i >= 0; i--) {
            if (cnt[i][1] == b) {if (map[x][cnt[i][0]] == 0) return a + b;}
            else break;
        }
        return a + b - 1;
    }
}



class Solution {
    public int maximalNetworkRank(int n, int[][] roads) {
        int[][] map = new int[n][n];
        int[] degree = new int[n];
        for (int[] road: roads) {
            map[road[0]][road[1]]++;
            map[road[1]][road[0]]++;
            degree[road[0]]++;
            degree[road[1]]++;
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int tmp = degree[i] + degree[j] - map[i][j];
                ans = Math.max(ans, tmp);
            }
        }
        return ans;
    }
}