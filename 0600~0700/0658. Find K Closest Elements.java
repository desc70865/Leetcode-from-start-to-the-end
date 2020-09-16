/* 
Given a sorted array arr, two integers k and x, find the k closest elements to x in the array. The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.

 

Example 1:

Input: arr = [1,2,3,4,5], k = 4, x = 3
Output: [1,2,3,4]
Example 2:

Input: arr = [1,2,3,4,5], k = 4, x = -1
Output: [1,2,3,4]
 

Constraints:

1 <= k <= arr.length
1 <= arr.length <= 10^4
Absolute value of elements in the array and x will not exceed 104
 */

class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int N = arr.length;
        if (x <= arr[0]) return res(arr, 0, k);
        else if (x >= arr[N - 1]) return res(arr, N - k, k);
        
        int l = N - k;
        if (l < k) {
            while (l > 0 && dis(arr[l - 1], x) <= dis(arr[l + k - 1], x)) l--;
        } else {
            l = binarySearch(arr, x);
            if (l > 0 && arr[l] - x >= x - arr[l - 1]) l--;
            int r = l;
            while (r - l < k - 1) {
                if (r == N - 1 || l > 0 && x - arr[l-1] <= arr[r+1] - x) l--;
                else r++;
            }
        }
        
        return res(arr, l, k);
    }
    
    private List<Integer> res(int[] A, int l, int k) {
        List<Integer> res = new ArrayList<>();
        for (int i = l; i < l + k; i++) res.add(A[i]);
        return res;
    }
    
    private int dis(int a, int b) {
        return Math.abs(a - b);
    }
    
    private int binarySearch(int[] A, int k) {
        int l = 0, r = A.length - 1;
        while (l <= r) {
            int mid = l + r >> 1;
            if (A[mid] < k) l = mid + 1;
            else r = mid - 1;
        }
        return r + 1;
    }
}



class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int N = arr.length;
        if (x <= arr[0]) return res(arr, 0, k);
        else if (x >= arr[N - 1]) return res(arr, N - k, k);
        
        int l = N - k;
        while (l > 0 && dis(arr[l - 1], x) <= dis(arr[l + k - 1], x)) l--;

        return res(arr, l, k);
    }
    
    private List<Integer> res(int[] A, int l, int k) {
        List<Integer> res = new ArrayList<>();
        for (int i = l; i < l + k; i++) res.add(A[i]);
        return res;
    }
    
    private int dis(int a, int b) {
        return Math.abs(a - b);
    }
}