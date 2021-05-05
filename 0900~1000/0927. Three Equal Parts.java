/* 
You are given an array arr which consists of only zeros and ones, divide the array into three non-empty parts such that all of these parts represent the same binary value.

If it is possible, return any [i, j] with i + 1 < j, such that:

arr[0], arr[1], ..., arr[i] is the first part,
arr[i + 1], arr[i + 2], ..., arr[j - 1] is the second part, and
arr[j], arr[j + 1], ..., arr[arr.length - 1] is the third part.
All three parts have equal binary values.
If it is not possible, return [-1, -1].

Note that the entire part is used when considering what binary value it represents. For example, [1,1,0] represents 6 in decimal, not 3. Also, leading zeros are allowed, so [0,1,1] and [1,1] represent the same value.

 

Example 1:

Input: arr = [1,0,1,0,1]
Output: [0,3]
Example 2:

Input: arr = [1,1,0,1,1]
Output: [-1,-1]
Example 3:

Input: arr = [1,1,0,0,1]
Output: [0,2]
 

Constraints:

3 <= arr.length <= 3 * 104
arr[i] is 0 or 1
 */

class Solution {
    static int[] ERROR = {-1, -1};

    public int[] threeEqualParts(int[] arr) {
        int bit = 0;
        for (int e: arr) {
            bit += e;
        }
        if (bit % 3 != 0) {
            return ERROR;
        }
        if (bit == 0) {
            return new int[] {0, 2};
        }
        int T = bit / 3;
        int n = arr.length;
        bit = 0;
        int[] pos = new int[6];
        for (int i = 0; i < n; ++i) {
            if (arr[i] == 0) continue;
            ++bit;
            if (bit == 1) pos[0] = pos[1] = i;
            else if (bit == T + 1) pos[2] = pos[3] = i;
            else if (bit == T * 2 + 1) pos[4] = pos[5] = i;
            else if (bit == T * 1) pos[1] = i;
            else if (bit == T * 2) pos[3] = i;
            else if (bit == T * 3) pos[5] = i;
        }
        int tail = n - 1 - pos[5];
        if (pos[4] - pos[3] <= tail || pos[2] - pos[1] <= tail) {
            return ERROR;
        }
        // System.out.println(Arrays.toString(pos));
        for (int i = pos[5] + tail, j = pos[3] + tail, k = pos[1] + tail, rem = T; rem > 0; --i, --j, --k) {
            if (arr[i] != arr[j] || arr[j] != arr[k]) {
                return ERROR;
            }
            rem -= arr[i];
        }
        return new int[] {pos[1] + tail, pos[3] + tail + 1};
    }
}