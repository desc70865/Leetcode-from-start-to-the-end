/* 

You are given an array nums consisting of non-negative integers. You are also given a queries array, where queries[i] = [xi, mi].

The answer to the ith query is the maximum bitwise XOR value of xi and any element of nums that does not exceed mi. In other words, the answer is max(nums[j] XOR xi) for all j such that nums[j] <= mi. If all elements in nums are larger than mi, then the answer is -1.

Return an integer array answer where answer.length == queries.length and answer[i] is the answer to the ith query.

 

Example 1:

Input: nums = [0,1,2,3,4], queries = [[3,1],[1,3],[5,6]]
Output: [3,3,7]
Explanation:
1) 0 and 1 are the only two integers not greater than 1. 0 XOR 3 = 3 and 1 XOR 3 = 2. The larger of the two is 3.
2) 1 XOR 2 = 3.
3) 5 XOR 2 = 7.
Example 2:

Input: nums = [5,2,4,6,6,3], queries = [[12,4],[8,1],[6,3]]
Output: [15,-1,5]
 

Constraints:

1 <= nums.length, queries.length <= 105
queries[i].length == 2
0 <= nums[j], xi, mi <= 109
 */

class Solution {
    int[][] trie;
    int idx;

    public int[] maximizeXor(int[] nums, int[][] queries) {
        int size = queries.length;
        Integer[] sort = new Integer[size];
        for (int i = 0; i < size; i++) {
            sort[i] = i;
        }
        Arrays.sort(sort, (a, b) -> queries[a][1] - queries[b][1]);
        int[] ans = new int[size];

        int len = nums.length;
        Arrays.sort(nums);
        trie = new int[len * 31][2];
        int pos = 0;
        for (int i = 0; i < size; i++) {
            int k = sort[i];
            int curThreshold = queries[k][1];
            while (pos < len && nums[pos] <= curThreshold) {
                insert(nums[pos++]);
            }
            int curX = queries[k][0];
            ans[k] = idx == 0 ? -1 : curX ^ query(curX);
        }
        return ans;
    }
    
    private void insert(int x) {
        int node = 0;
        for (int offset = 30; offset >= 0; offset--) {
            int bit = x >> offset & 1;
            if (trie[node][bit] == 0) {
                trie[node][bit] = ++idx;
            }
            node = trie[node][bit];
        }
    }

    private int query(int x) {
        int node = 0, ans = 0;
        for (int offset = 30; offset >= 0; offset--) {
            int bit = x >> offset & 1;
            ans <<= 1;
            if (trie[node][bit ^ 1] != 0) {
                ans += bit ^ 1;
                node = trie[node][bit ^ 1];
            } else {
                ans += bit;
                node = trie[node][bit];
            }
        }
        return ans;
    }
}



class Solution {
    static final int INF = 1 << 30;

    public static int[] maximizeXor(int[] nums, int[][] queries) {
        // sort & de-dup
        Arrays.sort(nums);
        int len = 0;
        for (int i = 0, prev = -1; i < nums.length; i++) {
            if (nums[i] != prev) {
                prev = nums[len++] = nums[i];
            }
        }
        // for loop
        final int querySize = queries.length;
        int[] ans = new int[querySize];
        for (int i = 0; i < querySize; i++) {
            int threshold = queries[i][1];
            int r = binarySearch(nums, 0, len, threshold) - 1;
            if (r < 0) {
                ans[i] = -1;
                continue;
            }
            int mod = INF;
            while (mod > 1 && 0 == (mod & threshold)) mod >>>= 1;
            ans[i] = query(nums, 0, r, mod, queries[i][0]);
        }
        return ans;
    }

    private static int query(int[] nums, int l, int r, int threshold, int x) {
        for (; threshold != 0 && l < r; threshold >>>= 1) {
            if ((threshold & nums[l]) == (threshold & nums[r])) {
                continue;
            }
            int mid = binarySearch(nums, l, r + 1, nums[l] | (threshold - 1));
            if (0 == (x & threshold)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return nums[l] ^ x;
    }

    private static int binarySearch(int[] arr, int l, int r, int target) {
        return Math.abs(Arrays.binarySearch(arr, l, r, target) + 1);
    }
}