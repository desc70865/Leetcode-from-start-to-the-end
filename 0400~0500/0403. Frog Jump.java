/* 
A frog is crossing a river. The river is divided into some number of units, and at each unit, there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.

Given a list of stones' positions (in units) in sorted ascending order, determine if the frog can cross the river by landing on the last stone. Initially, the frog is on the first stone and assumes the first jump must be 1 unit.

If the frog's last jump was k units, its next jump must be either k - 1, k, or k + 1 units. The frog can only jump in the forward direction.

 

Example 1:

Input: stones = [0,1,3,5,6,8,12,17]
Output: true
Explanation: The frog can jump to the last stone by jumping 1 unit to the 2nd stone, then 2 units to the 3rd stone, then 2 units to the 4th stone, then 3 units to the 6th stone, 4 units to the 7th stone, and 5 units to the 8th stone.
Example 2:

Input: stones = [0,1,2,3,4,8,9,11]
Output: false
Explanation: There is no way to jump to the last stone as the gap between the 5th and 6th stone is too large.
 

Constraints:

2 <= stones.length <= 2000
0 <= stones[i] <= 231 - 1
stones[0] == 0
 */

class Solution {
    public boolean canCross(int[] stones) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int stone: stones) {
            map.put(stone, new HashSet<>());
        }
        map.get(0).add(0);
        for (int stone: stones) {
            for (int step: map.get(stone)) {
                for (int p = Math.max(1, step - 1); p <= step + 1; p++) {
                    final int move = p;
                    map.computeIfPresent(stone + p, (k, set) -> {
                        // use magic
                        // set.add(k - stone);
                        // or you can use final
                        set.add(move);
                        return set;
                    });
                }
            }
        }
        return map.get(stones[stones.length - 1]).size() > 0;
    }
}



class Solution {
    Boolean[][] dp;

    public boolean canCross(int[] stones) {
        int n = stones.length;
        dp = new Boolean[n][n];
        for (int i = 1; i < n; i++) {
            if (stones[i] - stones[i - 1] > i) {
                return false;
            }
        }
        return dfs(stones, 0, 1);
    }
    
    private boolean dfs(int[] stones, int idx, int k) {
        if (dp[idx][k] != null) return dp[idx][k];
        if (idx == stones.length - 1) return true;
        if (k == 0) return false;
        for (int next = idx + 1; next < stones.length; next++) {
            if (stones[idx] + k < stones[next]) break;
            if (stones[idx] + k > stones[next]) continue;
            for (int step = k - 1; step <= k + 1; step++) {
                if (dfs(stones, next, step)) return dp[idx][k] = true;
            }
        }
        return dp[idx][k] = false;
    }
}



class Solution {
    public boolean canCross(int[] stones) {
        int n = stones.length;
        boolean[][] dp = new boolean[n][n];
        dp[0][0] = true;
        for (int i = 1; i < n; i++) {
            if (stones[i] - stones[i - 1] > i) {
                return false;
            }
        }
        for (int r = 1; r < n; r++) {
            for (int l = r - 1; l >= 0; l--) {
                int k = stones[r] - stones[l];
                if (k > l + 1) {
                    break;
                }
                dp[r][k] = dp[l][k - 1] || dp[l][k] || dp[l][k + 1];
                if (r == n - 1 && dp[r][k]) {
                    return true;
                }
            }
        }
        return false;
    }
}