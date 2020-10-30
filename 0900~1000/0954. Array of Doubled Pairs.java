/* 
Given an array of integers A with even length, return true if and only if it is possible to reorder it such that A[2 * i + 1] = 2 * A[2 * i] for every 0 <= i < len(A) / 2.

 

Example 1:

Input: A = [3,1,3,6]
Output: false
Example 2:

Input: A = [2,1,2,6]
Output: false
Example 3:

Input: A = [4,-2,2,-4]
Output: true
Explanation: We can take two groups, [-2,-4] and [2,4] to form [-2,-4,2,4] or [2,4,-2,-4].
Example 4:

Input: A = [1,2,4,16,8,4]
Output: false
 

Constraints:

0 <= A.length <= 3 * 104
A.length is even.
-105 <= A[i] <= 105
 */

class Solution {
    Map<Integer, Integer> map;
    public boolean canReorderDoubled(int[] A) {
        Arrays.sort(A);
        map = new HashMap<>();
        for (int num: A) {
            if (num >= 0) break;
            if (! helper(num)) return false;
        }
        if (map.size() > 0) return false;
        int N = A.length;
        for (int i = N - 1; i >= 0; i--) {
            if (A[i] <= 0) break;
            if (! helper(A[i])) return false;
        }
        return map.size() == 0;
    }

    private boolean helper(int num) {
        if (map.containsKey(num)) {
            if (map.get(num) == 1) map.remove(num);
            else map.merge(num, -1, Integer::sum);
        } else {
            if (num % 2 != 0) return false;
            map.merge(num / 2, 1, Integer::sum);
        }
        return true;
    }
}