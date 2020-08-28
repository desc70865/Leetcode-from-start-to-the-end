/* 
Given a function rand7 which generates a uniform random integer in the range 1 to 7, write a function rand10 which generates a uniform random integer in the range 1 to 10.

Do NOT use system's Math.random().

 

Example 1:

Input: 1
Output: [7]
Example 2:

Input: 2
Output: [8,4]
Example 3:

Input: 3
Output: [8,1,10]
 

Note:

rand7 is predefined.
Each testcase has one argument: n, the number of times that rand10 is called.
 

Follow up:

What is the expected value for the number of calls to rand7() function?
Could you minimize the number of calls to rand7()?
 */

/**
 * The rand7() API is already defined in the parent class SolBase.
 * public int rand7();
 * @return a random integer in the range 1 to 7
 */
class Solution extends SolBase {
    public int rand10() {
        
    }
}



class Solution extends SolBase {
    public int rand10() {
        int base = (rand7()-1) * 7;
        int bias = rand7();
        int randNum = base + bias;
        // (base + bias) ~ U[1, 49]
        while (randNum > 40) {
            // reject sampling for this time
            // re-sample for next time
            return rand10();
        }
        // after rejecting sampling (rejected num greater than 40)
        // now randNum ~ U[1, 40]
        return randNum % 10 + 1;    // U[0, 9] -> U[1, 10]
    }
}



class Solution extends SolBase {
    public int rand10() {
        int row, col, idx;
        do {
            row = rand7();
            col = rand7();
            idx = col + (row - 1) * 7;
        } while (idx > 40);
        return 1 + (idx - 1) % 10;
    }
}