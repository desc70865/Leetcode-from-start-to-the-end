/* 
Given two arrays A and B of equal size, the advantage of A with respect to B is the number of indices i for which A[i] > B[i].

Return any permutation of A that maximizes its advantage with respect to B.

 

Example 1:

Input: A = [2,7,11,15], B = [1,10,4,11]
Output: [2,11,7,15]
Example 2:

Input: A = [12,24,8,32], B = [13,25,32,11]
Output: [24,32,8,12]
 

Note:

1 <= A.length = B.length <= 10000
0 <= A[i] <= 10^9
0 <= B[i] <= 10^9
 */

/*
 * 作者：keylol
 * 链接：https://leetcode-cn.com/problems/advantage-shuffle/solution/java-16ms-ji-bai-zai-zuo-de-ge-wei-la-ji-e57h/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class Solution {
    static final int BASE = 1 << 16;
    
    public int[] advantageCount(int[] A, int[] B) {
        int len = A.length;
        Arrays.sort(A);
        long[] aux = new long[len];
        for (int i = 0; i < len; i++) {
            aux[i] = ((long) B[i] << 16) + i;
        }
        Arrays.sort(aux);
        int left = 0;
        int right = len - 1;
        for (int num: A) {
            if (num > aux[left] >> 16) {
                B[(int) (aux[left++] % BASE)] = num;
            } else {
                B[(int) (aux[right--] % BASE)] = num;
            }
        }
        return B;
    }
}