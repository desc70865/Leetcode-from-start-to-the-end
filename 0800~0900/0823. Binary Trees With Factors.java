/* 
Given an array of unique integers, arr, where each integer arr[i] is strictly greater than 1.

We make a binary tree using these integers, and each number may be used for any number of times. Each non-leaf node's value should be equal to the product of the values of its children.

Return the number of binary trees we can make. The answer may be too large so return the answer modulo 109 + 7.

 

Example 1:

Input: arr = [2,4]
Output: 3
Explanation: We can make these trees: [2], [4], [4, 2, 2]
Example 2:

Input: arr = [2,4,5,10]
Output: 7
Explanation: We can make these trees: [2], [4], [5], [10], [4, 2, 2], [10, 2, 5], [10, 5, 2].
 

Constraints:

1 <= arr.length <= 1000
2 <= arr[i] <= 109
 */

class Solution {
    static final int MOD = 1_000_000_007;

    public int numFactoredBinaryTrees(int[] arr) {
        Arrays.sort(arr);
        int len = arr.length;
        Map<Integer, Integer> map = new HashMap<>(len);
        for (int i = 0; i < len; i++) {
            map.put(arr[i], i);
        }
        long[] cnt = new long[len];
        long sum = 0;
        for (int i = 0; i < len; i++) {
            long curSum = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] % arr[j] == 0 && map.containsKey(arr[i] / arr[j])) {
                    curSum += cnt[map.get(arr[i] / arr[j])] * cnt[j];
                }
            }
            cnt[i] = curSum % MOD;
            sum += cnt[i];
        }
        return (int) (sum % MOD);
    }
}