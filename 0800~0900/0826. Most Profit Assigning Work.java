/* 
We have jobs: difficulty[i] is the difficulty of the ith job, and profit[i] is the profit of the ith job. 

Now we have some workers. worker[i] is the ability of the ith worker, which means that this worker can only complete a job with difficulty at most worker[i]. 

Every worker can be assigned at most one job, but one job can be completed multiple times.

For example, if 3 people attempt the same job that pays $1, then the total profit will be $3.  If a worker cannot complete any job, his profit is $0.

What is the most profit we can make?

Example 1:

Input: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
Output: 100 
Explanation: Workers are assigned jobs of difficulty [4,4,6,6] and they get profit of [20,20,30,30] seperately.
Notes:

1 <= difficulty.length = profit.length <= 10000
1 <= worker.length <= 10000
difficulty[i], profit[i], worker[i]  are in range [1, 10^5]
 */

class Solution {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        
    }
}



class Solution {
    private int[] diff;
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        diff = difficulty;
        int sum = 0, cur;
        for (int p: worker) {
            cur = getIndex(p);
            if (-1 == p) continue;
            sum += profit[cur];
        }
        return sum;
    }
    
    private int getIndex(int p) {
        if (p < diff[0]) return -1;
        int l = 0, r = diff.length-1, mid;
        while (l < r) {
            mid = (l + r) / 2;
            if (mid > p) {
                r = mid - 1;
            } else if (mid < p) {
                l = mid;
            } else {
                return mid;
            }
        }
        return l;
    }
}



class Solution {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int LEN = 0, curMax = 0, sum = 0;
        
        for (int w: worker) LEN = Math.max(LEN, w);
        int res[] = new int[LEN + 1];
        
        for (int i = 0; i < difficulty.length; i++) {
            if (difficulty[i] > LEN) continue;
            res[difficulty[i]] = Math.max(profit[i], res[difficulty[i]]);
        }
        
        for (int i = 0; i <= LEN; i++) {
            if (res[i] > curMax) curMax = res[i];
            else res[i] = curMax;
        }
        
        for (int w: worker) sum += res[w];
        
        return sum;
    }
}