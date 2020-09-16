/* 
Given a positive 32-bit integer n, you need to find the smallest 32-bit integer which has exactly the same digits existing in the integer n and is greater in value than n. If no such positive 32-bit integer exists, you need to return -1.

Example 1:

Input: 12
Output: 21
 

Example 2:

Input: 21
Output: -1
 */

class Solution {
    public int nextGreaterElement(int n) {
        int K = (int) (Math.log(n) / Math.log(10));
        int[] base = new int[K + 1];
        int idx = K;
        while (n > 0) {
            base[idx--] = n % 10;
            n /= 10;
        }
        idx = K;
        while (idx > 0 && base[idx - 1] >= base[idx]) idx--;
        if (--idx < 0) return -1;

        for (int j = K; j >= 0; j--) {
            if (base[j] > base[idx]) {
                swap(base, idx, j);
                sort(base, idx+1, K);
                break;
            }
        }
        long res = 0;
        for (int i = 0; i <= K; i++) {
            res *= 10;
            res += base[i];
        }
        if (res > Integer.MAX_VALUE) return -1;
        return (int) res;
    }

    private void swap(int[] A, int i, int j) {
        int t = A[i];
        A[i] = A[j];
        A[j] = t;
    }

    public void sort(int[] arr, int l, int r) {
        if (l >= r) return;
        int p = partition(arr, l, r);
        sort(arr, l, p-1);
        sort(arr, p+1, r);
    }

    public int partition(int[] arr, int l, int r) {
        int temp = arr[l];
        while (l < r) {
            while (l < r) {
                if (arr[r] < temp) {
                    arr[l] = arr[r];
                    break;
                }
                r--;
            }
            while (l < r) {
                if (arr[l] > temp) {
                    arr[r] = arr[l];
                    break;
                }
                l++;
            }
        }
        arr[l] = temp;
        return l;
    }
}