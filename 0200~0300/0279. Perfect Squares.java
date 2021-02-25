/* 
Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

Example 1:

Input: n = 12
Output: 3 
Explanation: 12 = 4 + 4 + 4.
Example 2:

Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.
 */

class Solution {
    public int numSquares(int n) {
        if (this.isSquare(n))
            return 1;
        
        while (n % 4 == 0)
            n >>= 2;
        if (n % 8 == 7)
            return 4;
        
        for (int i = 1; i * i < n; ++i) {
            if (this.isSquare(n - i * i))
                return 2;
        }
        
        return 3;
    }

    protected boolean isSquare(int n) {
        int sq = (int) Math.sqrt(n);
        return n == sq * sq;
    }
}

// BFS
// update queue
// search for min depth
// DFS
// search for all answer

class Solution {
    public int numSquares(int n) {
        ArrayList<Integer> square_nums = new ArrayList<Integer>();
        for (int i = 1; i * i <= n; ++i) {
            square_nums.add(i * i);
        }

        Set<Integer> queue = new HashSet<Integer>();
        queue.add(n);

        int level = 0;
        while (queue.size() > 0) {
            level += 1;
            Set<Integer> next_queue = new HashSet<Integer>();

            for (Integer remainder : queue) {
                for (Integer square : square_nums) {
                    if (remainder.equals(square)) {
                        return level;
                    } else if (remainder < square) {
                        break;
                    } else {
                        next_queue.add(remainder - square);
                    }
                }
            }
            queue = next_queue;
        }
        return level;
    }
}