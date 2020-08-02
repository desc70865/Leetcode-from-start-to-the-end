/* 
You have k lists of sorted integers in ascending order. Find the smallest range that includes at least one number from each of the k lists.

We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.

 

Example 1:

Input: [[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
Output: [20,24]
Explanation: 
List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
List 2: [0, 9, 12, 20], 20 is in range [20,24].
List 3: [5, 18, 22, 30], 22 is in range [20,24].
 

Note:

The given list may contain duplicates, so ascending order means >= here.
1 <= k <= 3500
-105 <= value of elements <= 105.
 */

class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        
    }
}



/*
Algo:
1. Take minval and maxVal amoing first element in all given arrays. Also, capture which array holds that in var minK.
2. Have an array (say dp) of size k (given k list) to store the minIndex on all given arrays.
3. while(dp[minK] of any one array is done) then we are done coverig all given array
    Until then, 
        a. Get next value from array minK with index dp[minK]
        b. Compare that value with all K list and update their minIndex in dp accordingly.
        c. Also, capture next minVal, maxVal and minK
*/

class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        int k = nums.size();
        if (k == 1) {
            return new int[] {nums.get(0).get(0), nums.get(0).get(0)};
        }
        int[] dp = new int[k];
        
        int minK = 0;
        int minVal = nums.get(0).get(0);
        int maxVal = nums.get(0).get(0);
        
        //Find minVal, minIndex sort list and maxVal
        for (int i = 0; i < k; i++) {
            int val = nums.get(i).get(0);
            if (val < minVal) {
                minVal = val;
                minK = i;
            }
            if (val > maxVal) {
                maxVal = val;
            }
        }
        int[] res = new int[] { minVal, maxVal };
        
        //Do until we cover anyone list completely
        boolean done = false;
        while (!done) {
            List<Integer> nextNums = nums.get(minK);
            dp[minK]++;
            if (dp[minK] == nextNums.size()) {
                done = true;
                continue;
            }
            int next = nextNums.get(dp[minK]);
            minVal = next;
            
            //Check all k list for next minVal and maxVal
            for (int i = 0;i < k ;i++) {
                nextNums = nums.get(i);
                int currIdx = dp[i];
                int currSize = nextNums.size();
                while (currIdx < currSize && nextNums.get(currIdx) <= next) {
                    dp[i] = currIdx;
                    currIdx++;
                } 
                int val = nextNums.get(dp[i]);
                if (val < minVal) {
                    minVal=val;
                    minK = i;
                }
                if (val > maxVal) {
                    maxVal = val;
                }
            }
                if (maxVal - minVal < res[1] - res[0]) res = new int[]{minVal, maxVal};
        }
        return res;
   }
}

//O(min(length of k list) * k)