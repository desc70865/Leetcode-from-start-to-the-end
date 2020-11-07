/* 
Given an array arr that represents a permutation of numbers from 1 to n. You have a binary string of size n that initially has all its bits set to zero.

At each step i (assuming both the binary string and arr are 1-indexed) from 1 to n, the bit at position arr[i] is set to 1. You are given an integer m and you need to find the latest step at which there exists a group of ones of length m. A group of ones is a contiguous substring of 1s such that it cannot be extended in either direction.

Return the latest step at which there exists a group of ones of length exactly m. If no such group exists, return -1.

 

Example 1:

Input: arr = [3,5,1,2,4], m = 1
Output: 4
Explanation:
Step 1: "00100", groups: ["1"]
Step 2: "00101", groups: ["1", "1"]
Step 3: "10101", groups: ["1", "1", "1"]
Step 4: "11101", groups: ["111", "1"]
Step 5: "11111", groups: ["11111"]
The latest step at which there exists a group of size 1 is step 4.
Example 2:

Input: arr = [3,1,5,4,2], m = 2
Output: -1
Explanation:
Step 1: "00100", groups: ["1"]
Step 2: "10100", groups: ["1", "1"]
Step 3: "10101", groups: ["1", "1", "1"]
Step 4: "10111", groups: ["1", "111"]
Step 5: "11111", groups: ["11111"]
No group of size 2 exists during any step.
Example 3:

Input: arr = [1], m = 1
Output: 1
Example 4:

Input: arr = [2,1], m = 2
Output: 2
 

Constraints:

n == arr.length
1 <= n <= 10^5
1 <= arr[i] <= n
All integers in arr are distinct.
1 <= m <= arr.length
 */

class Solution {
    public int findLatestStep(int[] arr, int m) {
        int len = arr.length;
        if (m == len) return m;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(1, len);
        for (int i = len - 1; i >= m - 1; i--) {
            int idx = arr[i];
            int start = map.floorKey(idx);
            int end = map.get(start);
            if (idx > start) {
                if (idx - start == m) return i;
                map.put(start, idx - 1);
            }
            if (end > idx) {
                if (end - idx == m) return i;
                map.put(idx + 1, end);
            }
        }
        return -1;
    }
}



class Solution {
    public int findLatestStep(int[] arr, int m) {
        int len = arr.length;
        if (m == len) return m;
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        set.add(len + 1);
        for (int i = len - 1; i >= m - 1; i--) {
            int idx = arr[i];
            int L = set.lower(idx);
            int R = set.higher(idx);
            set.add(idx);
            if (idx - L - 1 == m || R - idx - 1 == m) return i;
        }
        return -1;
    }
}



class Solution {
    public int findLatestStep(int[] arr, int m) {
        int n = arr.length;
        if (m == n) {
            return n;
        }
        int[][] segments = new int[1][2];
        segments[0][0] = 1;
        segments[0][1] = n;
        for (int i = n - 1; i >= 0; --i) {
            for (int j = 0; j < segments.length; ++j) {
                int[] s = segments[j];
                if (arr[i] < s[0] || s[1] < arr[i]) continue;
                int a = arr[i] - s[0];
                int b = s[1] - arr[i];
                if (a == m || b == m) return i;
                if (arr[i] == s[0]) s[0]++;
                else if (arr[i] == s[1]) s[1]--;
                else {
                    if (a > m && b > m) {
                        int[][] nextSeg = new int[segments.length + 1][2];
                        int k;
                        for (k = 0; k < j; ++k) nextSeg[k] = segments[k];
                        nextSeg[k++] = new int[]{s[0], arr[i] - 1};
                        nextSeg[k++] = new int[]{arr[i] + 1, s[1]};
                        for (; k < nextSeg.length; ++k) nextSeg[k] = segments[k - 1];
                        segments = nextSeg;
                    } else if (a > m) {
                        s[1] = arr[i] - 1;
                    } else if (b > m) {
                        s[0] = arr[i] + 1;
                    } else {
                        if (segments.length == 1) return -1;
                        int[][] nextSeg = new int[segments.length - 1][2];
                        for (int k = 0; k < nextSeg.length; ++k) {
                            nextSeg[k] = segments[k < j ? k : k + 1];
                        }
                        segments = nextSeg;
                    }
                }
                break;
            }
        }
        return -1;
    }
}