/* 
Alice and Bob have candy bars of different sizes: A[i] is the size of the i-th bar of candy that Alice has, and B[j] is the size of the j-th bar of candy that Bob has.

Since they are friends, they would like to exchange one candy bar each so that after the exchange, they both have the same total amount of candy.  (The total amount of candy a person has is the sum of the sizes of candy bars they have.)

Return an integer array ans where ans[0] is the size of the candy bar that Alice must exchange, and ans[1] is the size of the candy bar that Bob must exchange.

If there are multiple answers, you may return any one of them.  It is guaranteed an answer exists.

 

Example 1:

Input: A = [1,1], B = [2,2]
Output: [1,2]
Example 2:

Input: A = [1,2], B = [2,3]
Output: [1,2]
Example 3:

Input: A = [2], B = [1,3]
Output: [2,3]
Example 4:

Input: A = [1,2,5], B = [2,4]
Output: [5,4]
 

Note:

1 <= A.length <= 10000
1 <= B.length <= 10000
1 <= A[i] <= 100000
1 <= B[i] <= 100000
It is guaranteed that Alice and Bob have different total amounts of candy.
It is guaranteed there exists an answer.
 */

class Solution {
    public int[] fairCandySwap(int[] A, int[] B) {
        int m = A.length, n = B.length;
        Arrays.sort(A);
        Arrays.sort(B);
        int diff = 0;
        for (int num: A) diff += num;
        for (int num: B) diff -= num;
        diff /= 2;
        int l = 0, r = 0;
        while (l < m && r < n) {
            if (A[l] - B[r] < diff) l++;
            else if (A[l] - B[r] > diff) r++;
            else return new int[] {A[l], B[r]};
        }
        return new int[2];
    }
}



class Solution {
    public int[] fairCandySwap(int[] A, int[] B) {
        int diff = 0;
        Set<Integer> set = new HashSet<>();
        for (int num: A) diff += num;
        for (int num: B) {
            diff -= num;
            set.add(num);
        }
        diff /= 2;
        for (int num: A) {
            if (set.contains(num - diff)) return new int[] {num, num - diff};
        }
        return new int[2];
    }
}



class Solution {
    public int[] fairCandySwap(int[] A, int[] B) {
        int diff = 0;
        boolean[] v = new boolean[100001];
        for (int num: A) diff += num;
        for (int num: B) {
            diff -= num;
            v[num] = true;
        }
        diff /= 2;
        for (int num: A) {
            if (num < diff || num - diff > 100000) continue;
            if (v[num - diff]) return new int[] {num, num - diff};
        }
        return new int[2];
    }
}