/* 
We have an integer array nums, where all the integers in nums are 0 or 1. You will not be given direct access to the array, instead, you will have an API ArrayReader which have the following functions:

int query(int a, int b, int c, int d): where 0 <= a < b < c < d < ArrayReader.length(). The function returns the distribution of the value of the 4 elements and returns:
4 : if the values of the 4 elements are the same (0 or 1).
2 : if three elements have a value equal to 0 and one element has value equal to 1 or vice versa.
0 : if two element have a value equal to 0 and two elements have a value equal to 1.
int length(): Returns the size of the array.
You are allowed to call query() 2 * n times at most where n is equal to ArrayReader.length().

Return any index of the most frequent value in nums, in case of tie, return -1.

Follow up: What is the minimum number of calls needed to find the majority element?

 

Example 1:

Input: nums = [0,0,1,0,1,1,1,1]
Output: 5
Explanation: The following calls to the API
reader.length() // returns 8 because there are 8 elements in the hidden array.
reader.query(0,1,2,3) // returns 2 this is a query that compares the elements nums[0], nums[1], nums[2], nums[3]
// Three elements have a value equal to 0 and one element has value equal to 1 or viceversa.
reader.query(4,5,6,7) // returns 4 because nums[4], nums[5], nums[6], nums[7] have the same value.
we can infer that the most frequent value is found in the last 4 elements.
Index 2, 4, 6, 7 is also a correct answer.
Example 2:

Input: nums = [0,0,1,1,0]
Output: 0
Example 3:

Input: nums = [1,0,1,0,1,0,1,0]
Output: -1
 

Constraints:

5 <= nums.length <= 10^5
0 <= nums[i] <= 1
 */

/**
 * // This is the ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface ArrayReader {
 *   public:
 *     // Compares 4 different elements in the array
 *     // return 4 if the values of the 4 elements are the same (0 or 1).
 *     // return 2 if three elements have a value equal to 0 and one element has value equal to 1 or vice versa.
 *     // return 0 : if two element have a value equal to 0 and two elements have a value equal to 1.
 *     public int query(int a, int b, int c, int d);
 *
 *     // Returns the length of the array
 *     public int length();
 * };
 */

class Solution {
    public int guessMajority(ArrayReader reader) {
        int cnt0 = 1, cnt1 = 0;
        int q0234 = reader.query(0, 2, 3, 4);
        int q1234 = reader.query(1, 2, 3, 4);
        int start, p, q;
        int where0 = -1, where1 = -1;
        if (q0234 == q1234) {
            // nums[1] = 0
            ++cnt0;
            start = 2;
            p = 0;
            q = 1;
            where0 = 0;
        } else {
            // nums[1] = 1
            ++cnt1;
            int q0134 = reader.query(0, 1, 3, 4);
            if (q0134 == q1234) {
                // nums[2] = 0
                ++cnt0;
                start = 3;
                p = 0;
                q = 2;
                where0 = 0;
                where1 = 1;
            } else {
                // nums[2] = 1，为了方便后续编码可以交换一下
                // 即 nums[0] = 1, nums[1] = nums[2] = 0
                cnt0 = 2;
                cnt1 = 1;
                start = 3;
                p = 1;
                q = 2;
                where0 = 1;
                where1 = 0;
            }
        }

        int n = reader.length();
        int i, curr;
        for (i = start; i + 1 < n; i += 2) {
            curr = reader.query(p, q, i, i + 1);
            if (curr == 0) {
                cnt1 += 2;
                if (where1 == -1) {
                    where1 = i;
                }
            } else if (curr == 2) {
                ++cnt0;
                ++cnt1;
            } else {
                cnt0 += 2;
            }
        }

        if (i != n) {
            int q0123 = reader.query(0, 1, 2, 3);
            int extra = reader.query(1, 2, 3, n - 1);
            int status = (q0123 == extra ? 1 : 0) ^ (p == 0 ? 1 : 0);
            if (status == 0) {
                ++cnt0;
            } else {
                ++cnt1;
            }
        }

        if (cnt0 == cnt1) {
            return -1;
        }
        return cnt0 < cnt1 ? where1 : where0;

    }
}