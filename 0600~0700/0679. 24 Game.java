/* 
You have 4 cards each containing a number from 1 to 9. You need to judge whether they could operated through *, /, +, -, (, ) to get the value of 24.

Example 1:
Input: [4, 1, 8, 7]
Output: True
Explanation: (8-4) * (7-1) = 24
Example 2:
Input: [1, 2, 1, 2]
Output: False
Note:
The division operator / represents real division, not integer division. For example, 4 / (1 - 2/3) = 12.
Every operation done is between two numbers. In particular, we cannot use - as a unary operator. For example, with [1, 1, 1, 1] as input, the expression -1 - 1 - 1 - 1 is not allowed.
You cannot concatenate numbers together. For example, if the input is [1, 2, 1, 2], we cannot write this as 12 + 12.
 */

class Solution {
    public boolean judgePoint24(int[] A) {
        float res = 0f, tmp = 0f;
        char[] ops = { '+', '-', '*', '/' };

        for (int i1 = 0; i1 < 4; i1++) {
            for (int i2 = 0; i2 < 4; i2++) {
                if (i1 == i2) continue;
                for (int i3 = 0; i3 < 4; i3++) {
                    if (i3 == i1 || i3 == i2) continue;
                    int i4 = 6 - i1 - i2 - i3;

                    for (int o1 = 0; o1 < 4; o1++) {
                        for (int o2 = 0; o2 < 4; o2++) {
                            for (int o3 = 0; o3 < 4; o3++) {
                                // ab#cd##
                                res = calculate(A[i1], A[i2], ops[o1]);
                                tmp = calculate(A[i3], A[i4], ops[o2]);
                                res = calculate(res, tmp, ops[o3]);
                                if (check(res)) return true;
                                // abc##d#
                                res = calculate(A[i2], A[i3], ops[o1]);
                                res = calculate(A[i1], res, ops[o2]);
                                res = calculate(res, A[i4], ops[o3]);
                                if (check(res)) return true;
                                // abcd###
                                res = calculate(A[i3], A[i4], ops[o1]);
                                res = calculate(A[i2], res, ops[o2]);
                                res = calculate(A[i1], res, ops[o3]);
                                if (check(res)) return true;
                                // ab#c#d#
                                res = calculate(A[i1], A[i2], ops[o1]);
                                res = calculate(res, A[i3], ops[o2]);
                                res = calculate(res, A[i4], ops[o3]);
                                if (check(res)) return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private float calculate(float a, float b, char operator) {
        switch (operator) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return b == 0 ? -10 : a / b;
            default:  return -10;
        }
    }
    
    private boolean check(float a) {
        return Math.abs(a - 24) < 0.001;
    }
}



class Solution {
    private final int LEN = 4;
    double[] A = new double[LEN];
    public boolean judgePoint24(int[] nums) {
        for (int i = 0; i < LEN; i++) A[i] = (double) nums[i];
        return helper(0, LEN);
    }
    
    boolean helper(int visited, int k) {
        if (k == 1) return Math.abs(A[log2(visited ^ 15)] - 24) < 0.0001;
            /* for (int i = 0; i < A.length; i++) {
                if ((visited & (1 << i)) == 0) {
                    return Math.abs(A[i] - 24) < 0.0001;
                }
            } */
        
        for (int i = 0; i < LEN; i++) {
            if ((visited & (1 << i)) != 0) continue;
            
            double prev = A[i];
            for (int j = i+1; j < LEN; j++) {
                if ((visited & (1 << j)) != 0) continue;
                
                int p = visited | (1 << j);
                
                A[i] = prev + A[j];
                if (helper(p, k-1)) return true;
                
                A[i] = prev - A[j];
                if (helper(p, k-1)) return true;
                
                A[i] = -prev + A[j];
                if (helper(p, k-1)) return true;
                
                A[i] = prev * A[j];
                if (helper(p, k-1)) return true;
                
                A[i] = prev / A[j];
                if (helper(p, k-1)) return true;
                
                A[i] = A[j] / prev;
                if (helper(p, k-1)) return true;
            }
            A[i] = prev;
        }
        
        return false;
    }

    private int log2(int k) {
        return (int) (Math.log(k) / Math.log(2));
    }
}