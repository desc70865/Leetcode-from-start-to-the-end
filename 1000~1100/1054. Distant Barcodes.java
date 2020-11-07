/* 
In a warehouse, there is a row of barcodes, where the i-th barcode is barcodes[i].

Rearrange the barcodes so that no two adjacent barcodes are equal.  You may return any answer, and it is guaranteed an answer exists.

 

Example 1:

Input: [1,1,1,2,2,2]
Output: [2,1,2,1,2,1]
Example 2:

Input: [1,1,1,1,2,2,3,3]
Output: [1,3,1,3,2,1,2,1]
 

Note:

1 <= barcodes.length <= 10000
1 <= barcodes[i] <= 10000
 */

class Solution {
    int idx = 0;

    public int[] rearrangeBarcodes(int[] barcodes) {
        int min = 10001;
        int max = 0;
        for (int num: barcodes) {
            min = Math.min(num, min);
            max = Math.max(num, max);
        }
        int len = max - min + 1;
        int[][] cnt = new int[len][2];
        for (int i = 0; i < len; i++) {
            cnt[i][1] = i + min;
        }
        for (int num: barcodes) {
            cnt[num - min][0]++;
        }
        Arrays.sort(cnt, (a, b) -> b[0] - a[0]);
        for (int i = 0; i < barcodes.length; i += 2) {
            barcodes[i] = next(cnt);
        }
        for (int i = 1; i < barcodes.length; i += 2) {
            barcodes[i] = next(cnt);
        }
        return barcodes;
    }

    private int next(int[][] cnt) {
        while (cnt[idx][0] == 0) idx++;
        cnt[idx][0]--;
        return cnt[idx][1];
    }
}