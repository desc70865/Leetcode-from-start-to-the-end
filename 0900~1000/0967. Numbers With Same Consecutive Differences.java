/* 
Return all non-negative integers of length N such that the absolute difference between every two consecutive digits is K.

Note that every number in the answer must not have leading zeros except for the number 0 itself. For example, 01 has one leading zero and is invalid, but 0 is valid.

You may return the answer in any order.

 

Example 1:

Input: N = 3, K = 7
Output: [181,292,707,818,929]
Explanation: Note that 070 is not a valid number, because it has leading zeroes.
Example 2:

Input: N = 2, K = 1
Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
 

Note:

1 <= N <= 9
0 <= K <= 9
 */

class Solution {
    public int[] numsSameConsecDiff(int N, int K) {
        
    }
}



class Solution {
    List<Integer> list = new ArrayList<>();
    public int[] numsSameConsecDiff(int N, int K) {
        if (N == 1) list.add(0);
        for (int i = 1; i < 10; i++) helper(N, K, i);
        return list.stream().mapToInt(Integer::valueOf).toArray();
    }
    
    public void helper(int N, int K,int v) {
        if (N == 1) {
            list.add(v);
            return ;
        }
        
        int rem = v % 10, p = v * 10;
        if (rem >= K) helper(N-1, K, p + rem - K);
        if (K == 0) return ;
        if (rem + K < 10) helper(N-1, K, p + rem + K);
    }
}



class Solution {
    int[] trans = new int[1556];
    int idx = 0;
    public int[] numsSameConsecDiff(int N, int K) {
        for (int i = (N == 1 ? 0 : 1); i < 10; i++) helper(N, K, i);
        return Arrays.copyOf(trans, idx);
    }
    
    public void helper(int N, int K,int v) {
        if (N == 1) {
            trans[idx++] = v;
            return ;
        }
        
        int rem = v % 10, p = v * 10 + rem;
        if (rem >= K) helper(N-1, K, p - K);
        if (K == 0) return ;
        if (rem + K < 10) helper(N-1, K, p + K);
    }
}