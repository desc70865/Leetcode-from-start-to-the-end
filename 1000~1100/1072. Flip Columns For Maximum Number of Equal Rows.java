/* 
Given a matrix consisting of 0s and 1s, we may choose any number of columns in the matrix and flip every cell in that column.  Flipping a cell changes the value of that cell from 0 to 1 or from 1 to 0.

Return the maximum number of rows that have all values equal after some number of flips.

 

Example 1:

Input: [[0,1],[1,1]]
Output: 1
Explanation: After flipping no values, 1 row has all values equal.
Example 2:

Input: [[0,1],[1,0]]
Output: 2
Explanation: After flipping values in the first column, both rows have equal values.
Example 3:

Input: [[0,0,0],[0,0,1],[1,1,0]]
Output: 2
Explanation: After flipping values in the first two columns, the last two rows have equal values.
 

Note:

1 <= matrix.length <= 300
1 <= matrix[i].length <= 300
All matrix[i].length's are equal
matrix[i][j] is 0 or 1
 */

/*
 * 作者：keylol
 * 链接：https://leetcode-cn.com/problems/flip-columns-for-maximum-number-of-equal-rows/solution/ling-yi-chong-si-lu-by-keylol/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class Solution {
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] line: matrix) {
            map.merge(Arrays.hashCode(line[0] == 0 ? line : reverse(line)), 1, Integer::sum);
        }
        return map.values().stream().max(Integer::compareTo).get();
    }

    private int[] reverse(int[] nums) {
        for (int i = 0; i < nums.length; i++) nums[i] ^= 1;
        return nums;
    }
}



class Solution {
    static final int LEN = 63;
    static final int MOD = 1_000_000_007;

    public int maxEqualRowsAfterFlips(int[][] matrix) {
        Map<Integer, Integer> map = new HashMap<>();
        int len = matrix[0].length;
        int k = (len - 1) / LEN + 1;
        for (int[] line: matrix) {
            map.merge(encode(line, k, line[0]), 1, Integer::sum);
        }
        int max = 0;
        for (int v: map.values()) {
            max = Math.max(max, v);
        }
        return max;
    }

    private int encode(int[] nums, int k, int reverse) {
        long[] arr = new long[k];
        for (int i = 0; i < nums.length; i++) {
            arr[i / LEN] <<= 1;
            arr[i / LEN] += nums[i] ^ reverse;
        }
        // System.out.println(Arrays.toString(arr));
        long hash = 0;
        for (long num: arr) {
            hash += num;
            hash %= MOD;
        }
        return (int) hash;
    }
}



class Solution {
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        int len = matrix.length;
        int[] index = new int[len];
        for (int i = 0; i < len; i++) {
            index[i] = i;
        }
        return radixSort(matrix, index, 0, len - 1, 1);
    }
    
    // base on the first element at each line
    public int radixSort(int[][] arr, int[] index, int start, int end, int cur) {
        if (start > end) return 0;
        if (cur == arr[0].length) return end - start + 1;
        
        int i = start, j = end;
        
        while (i <= j) {
            while(i <= j && arr[index[i]][cur] == arr[index[i]][0]) i++;
            while(i <= j && arr[index[j]][cur] != arr[index[j]][0]) j--;
            if (i > j) break;
            // Swap.
            int temp = index[i];
            index[i] = index[j];
            index[j] = temp;
        }
        
        return Math.max(
            radixSort(arr, index, start, j, cur + 1),
            radixSort(arr, index, i, end, cur + 1)
        );
    }
}