/* 
You are given an array of distinct positive integers locations where locations[i] represents the position of city i. You are also given integers start, finish and fuel representing the starting city, ending city, and the initial amount of fuel you have, respectively.

At each step, if you are at city i, you can pick any city j such that j != i and 0 <= j < locations.length and move to city j. Moving from city i to city j reduces the amount of fuel you have by |locations[i] - locations[j]|. Please notice that |x| denotes the absolute value of x.

Notice that fuel cannot become negative at any point in time, and that you are allowed to visit any city more than once (including start and finish).

Return the count of all possible routes from start to finish.

Since the answer may be too large, return it modulo 10^9 + 7.

 

Example 1:

Input: locations = [2,3,6,8,4], start = 1, finish = 3, fuel = 5
Output: 4
Explanation: The following are all possible routes, each uses 5 units of fuel:
1 -> 3
1 -> 2 -> 3
1 -> 4 -> 3
1 -> 4 -> 2 -> 3
Example 2:

Input: locations = [4,3,1], start = 1, finish = 0, fuel = 6
Output: 5
Explanation: The following are all possible routes:
1 -> 0, used fuel = 1
1 -> 2 -> 0, used fuel = 5
1 -> 2 -> 1 -> 0, used fuel = 5
1 -> 0 -> 1 -> 0, used fuel = 3
1 -> 0 -> 1 -> 0 -> 1 -> 0, used fuel = 5
Example 3:

Input: locations = [5,2,1], start = 0, finish = 2, fuel = 3
Output: 0
Explanation: It's impossible to get from 0 to 2 using only 3 units of fuel since the shortest route needs 4 units of fuel.
Example 4:

Input: locations = [2,1,5], start = 0, finish = 0, fuel = 3
Output: 2
Explanation: There are two possible routes, 0 and 0 -> 1 -> 0.
Example 5:

Input: locations = [1,2,3], start = 0, finish = 2, fuel = 40
Output: 615088286
Explanation: The total number of possible routes is 2615088300. Taking this number modulo 10^9 + 7 gives us 615088286.
 

Constraints:

2 <= locations.length <= 100
1 <= locations[i] <= 10^9
All integers in locations are distinct.
0 <= start, finish < locations.length
1 <= fuel <= 200
 */

class Solution {
    static final int MOD = 1_000_000_007;

    int[][] dp;
    int[] locations;
    int finish;

    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        this.dp = new int[locations.length][fuel + 1];
        for (int i = 0; i < locations.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        this.locations = locations;
        this.finish = finish;
        return dfs(start, fuel);
    }

    private int dfs(int pos, int rem) {
        if (dp[pos][rem] >= 0) {
            return dp[pos][rem];
        }
        dp[pos][rem] = pos == finish ? 1 : 0;
        if (dis(pos, finish) > rem) {
            return dp[pos][rem];
        }
        for (int next = 0; next < locations.length; next++) {
            if (next == pos) continue;
            int cost = dis(pos, next);
            if (cost > rem) continue;
            dp[pos][rem] += dfs(next, rem - cost);
            dp[pos][rem] %= MOD;
        }
        return dp[pos][rem];
    }

    private int dis(int x, int y) {
        return Math.abs(locations[x] - locations[y]);
    }
}



class Solution {
    static final int MOD = 1_000_000_007;

    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        int len = locations.length;
        int[][] dp = new int[fuel+1][len];
        dp[fuel][start] = 1;
        // dp[f][j] -> 剩余 f 燃料时抵达 j
        for (int f = fuel; f > 0; f--) {
            for (int j = 0; j < len; j++) {
                if (dp[f][j] == 0)
                    continue;
                for (int k = 0; k < len; k++) {
                    if (j == k)
                        continue;
                    int nf = f - Math.abs(locations[j] - locations[k]);
                    if (nf < 0)
                        continue;
                    // nf < f
                    dp[nf][k] += dp[f][j];
                    dp[nf][k] %= MOD;
                }
            }
        }
        int ans = 0;
        for (int f = 0; f <= fuel; f++) {
            ans += dp[f][finish];
            ans %= MOD;
        }
        return ans;
    }
}



class Solution {
    static final int MOD = 1_000_000_007;
    
    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        int len = locations.length;
        int startPos = locations[start];
        int endPos = locations[finish];
        Arrays.sort(locations);
        for (int i = 0; i < len; i++) {
            if (startPos == locations[i])
                start = i;
            if (endPos == locations[i])
                finish = i;
        }
        long[][] dpL = new long[len][fuel + 1];
        long[][] dpR = new long[len][fuel + 1];
        dpL[start][0] = dpR[start][0] = 1;
        for (int used = 0; used <= fuel; used++) {
            for (int city = len - 2; city >= 0; city--) {
                int delta = locations[city + 1] - locations[city];
                if (used >= delta) {
                    dpL[city][used] = ((used == delta ? 0 : dpL[city + 1][used - delta]) * 2 + dpR[city + 1][used - delta]) % MOD;
                }
            }
            for (int city = 1; city < len; city++) {
                int delta = locations[city] - locations[city - 1];
                if (used >= delta) {
                    dpR[city][used] = ((used == delta ? 0 : dpR[city - 1][used - delta]) * 2 + dpL[city - 1][used - delta]) % MOD;
                }
            }
        }
        long res = start == finish ? -1 : 0;
        for (int used = 0; used <= fuel; used++) {
            res += dpL[finish][used] + dpR[finish][used];
        }
        return (int) (res % MOD);
    }
}