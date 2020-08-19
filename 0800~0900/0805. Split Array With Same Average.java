/* 
In a given integer array A, we must move every element of A to either list B or list C. (B and C initially start empty.)

Return true if and only if after such a move, it is possible that the average value of B is equal to the average value of C, and B and C are both non-empty.

Example :
Input: 
[1,2,3,4,5,6,7,8]
Output: true
Explanation: We can split the array into [1,4,5,8] and [2,3,6,7], and both of them have the average of 4.5.
Note:

The length of A will be in the range [1, 30].
A[i] will be in the range of [0, 10000].
 */

class Solution {
    public boolean splitArraySameAverage(int[] A) {
        
    }
}



class Solution {
    int k = 0, avg = 0, cnt = 0, len = 0;
    Set<Integer> set = new HashSet<>();
    public boolean splitArraySameAverage(int[] A) {
        Arrays.sort(A);
        len = A.length;
        System.out.println(len);
        for (int i = 0; i < len; i++) {
            avg += A[i];
            A[i] *= len;
        }
        
        while (A[k] < avg) k++;
        if (A[k] == avg && len > 1) return true;
        
        dfs(A, 0, 0);
        dfs2(A, k, 0);
        
        return cnt > 1;
    }

    private void dfs(int[] A, int idx, int sum) {
        if (idx == k) {
            if (sum > 0) set.add(sum);
            return;
        }
        dfs(A, idx+1, sum);
        dfs(A, idx+1, sum + avg - A[idx]);
    }

    private void dfs2(int[] A, int idx, int sum) {
        if (idx == len) {
            if (set.contains(sum)) cnt++;
            return;
        }
        dfs2(A, idx+1, sum);
        dfs2(A, idx+1, sum + A[idx] - avg);
    }
}



class Solution {
    public boolean splitArraySameAverage(int[] A) {
        int sum = 0;
        for (int num: A) sum += num;
        int len = A.length, k = len / gcd(sum, len);
        Arrays.sort(A);
        for (int i = k; i <= len / 2; i += k) {
            if (dfs(A, i, sum * i / len, 0)) return true;
        }
        return false;
    }
    
    private boolean dfs(int[] A, int len, int res, int startIndex) {
        if (len == 0) return res == 0;
        for (int i = startIndex; i < A.length; i++) {
            if (i > startIndex && A[i] == A[i - 1]) continue;
            if (dfs(A, len - 1, res - A[i], i + 1)) return true;
        }
        return false;
    }
    
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}