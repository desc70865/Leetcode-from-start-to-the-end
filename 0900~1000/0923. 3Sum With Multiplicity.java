/* 
Given an integer array A, and an integer target, return the number of tuples i, j, k  such that i < j < k and A[i] + A[j] + A[k] == target.

As the answer can be very large, return it modulo 109 + 7.

 

Example 1:

Input: A = [1,1,2,2,3,3,4,4,5,5], target = 8
Output: 20
Explanation: 
Enumerating by the values (A[i], A[j], A[k]):
(1, 2, 5) occurs 8 times;
(1, 3, 4) occurs 8 times;
(2, 2, 4) occurs 2 times;
(2, 3, 3) occurs 2 times.
Example 2:

Input: A = [1,1,2,2,2,2], target = 5
Output: 12
Explanation: 
A[i] = 1, A[j] = A[k] = 2 occurs 12 times:
We choose one 1 from [1,1] in 2 ways,
and two 2s from [2,2,2,2] in 6 ways.
 

Constraints:

3 <= A.length <= 3000
0 <= A[i] <= 100
0 <= target <= 300
 */

class Solution {
    static final int MOD = 1_000_000_007;
    public int threeSumMulti(int[] A, int target) {
        long sum = 0;
        int[] map = new int[101];
        for (int num: A) map[num]++;
        for (int i = 0; i <= 100; i++) {
            if (i * 3 > target) break;
            if (map[i] == 0) continue;
            if (map[i] > 2 && i * 3 == target) {
                int k = map[i];
                sum += (long) k * (k - 1) * (k - 2) / 6;
            } else if (map[i] > 1) {
                int k = target - 2 * i;
                if (i < k && k <= 100) sum += map[k] * f(map[i]);
            }
            sum += map[i] * helper(map, i, target);
        }
        return (int) (sum % MOD);
    }

    private int helper(int[] map, int i, int target) {
        target -= i;
        int R = Math.min(100, target - (i + 1));
        int L = target - R;
        int sum = 0;
        while (L <= R) {
            if (L == R) {
                sum += f(map[L]);
                break;
            }
            sum += map[L++] * map[R--];
        }
        return sum;
    }

    private int f(int x) {
        return (x - 1) * x / 2;
    }
}