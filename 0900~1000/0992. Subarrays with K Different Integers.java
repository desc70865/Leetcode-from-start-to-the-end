/* 
Given an array A of positive integers, call a (contiguous, not necessarily distinct) subarray of A good if the number of different integers in that subarray is exactly K.

(For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.)

Return the number of good subarrays of A.

 

Example 1:

Input: A = [1,2,1,2,3], K = 2
Output: 7
Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
Example 2:

Input: A = [1,2,1,3,4], K = 3
Output: 3
Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
 

Note:

1 <= A.length <= 20000
1 <= A[i] <= A.length
1 <= K <= A.length
 */

class Solution {
    public int subarraysWithKDistinct(int[] A, int K) {
        return helper(A, K) - helper(A, K - 1);
    }

    private int helper(int[] arr, int k) {
        int len = arr.length;
        if (len == 0 || k == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int l = 0, r = 0; r < len; ans += ++r - l) {
            map.merge(arr[r], 1, Integer::sum);
            while (map.size() > k) {
                remove(map, arr[l++]);
            }
        }
        return ans;
    }

    private void remove(Map<Integer, Integer> map, int k) {
        if (map.merge(k, -1, Integer::sum) == 0) {
            map.remove(k);
        }
    }
}



class Solution {
    public int subarraysWithKDistinct(int[] A, int K) {
        return helper(A, K) - helper(A, K - 1);
    }

    private int helper(int[] arr, int k) {
        int len = arr.length;
        if (len == 0 || k == 0) {
            return 0;
        }
        int[] map = new int[20001];
        int ans = 0;
        for (int l = 0, r = 0, size = 0; r < len; ans += r - l) {
            if (map[arr[r++]]++ == 0) {
                size++;
            }
            while (size > k) {
                if (--map[arr[l++]] == 0) {
                    size--;
                }
            }
        }
        return ans;
    }
}



class Solution {
    public int subarraysWithKDistinct(int[] A, int K) {
        int len = A.length;
        int[] count = new int[len + 1];
        int[] lastIndexOf = new int[len + 1];
        int ans = 0;
        for (int l = 0, r = 0, size = 0; r < len; r++) {
            lastIndexOf[A[r]] = r;
            if (count[A[r]]++ == 0) {
                size++;
            }
            while (size > K) {
                if (--count[A[l++]] == 0) {
                    size--;
                }
            }
            if (size == K) {
                for (int x = l; lastIndexOf[A[x]] != x++; ans++) ;
                ans++;
            }
        }
        return ans;
    }
}