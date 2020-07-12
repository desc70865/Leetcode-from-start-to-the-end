/* 
Given an array arr of integers, check if there exists two integers N and M such that N is the double of M ( i.e. N = 2 * M).

More formally check if there exists two indices i and j such that :

i != j
0 <= i, j < arr.length
arr[i] == 2 * arr[j]
 

Example 1:

Input: arr = [10,2,5,3]
Output: true
Explanation: N = 10 is the double of M = 5,that is, 10 = 2 * 5.
Example 2:

Input: arr = [7,1,14,11]
Output: true
Explanation: N = 14 is the double of M = 7,that is, 14 = 2 * 7.
Example 3:

Input: arr = [3,1,7,11]
Output: false
Explanation: In this case does not exist N and M, such that N = 2 * M.
 

Constraints:

2 <= arr.length <= 500
-10^3 <= arr[i] <= 10^3
 */

class Solution {
    public boolean checkIfExist(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int num: arr) {
            if (set.contains(num)) {
                return true;
            }
            if (num % 2 == 0) {
                set.add(num / 2);
            }
            set.add(num * 2);
        }
        return false;
    }
}



class Solution {
    public boolean checkIfExist(int[] arr) {
        // edge case
        if (arr[0] == 0 && arr[1] == 0) {
            return true;
        }
        
        for (int i = 0; i < arr.length; i++) {
            if (searchFor(arr[i]*2, arr) && arr[i] != 0) {
                return true;
            }
        }
        return false;
    }
    
    public boolean searchFor(int findMe, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == findMe) {
                return true;
            }
        }
        return false;
    }
}



class Solution {
    public boolean checkIfExist(int[] arr) {
        Arrays.sort(arr);
        int index = 0;
        while (++index < arr.length && arr[index] < 0) {
            for (int i = 0; i < index; i++) {
                if (arr[i] == arr[index] * 2) {
                    return true;
                } else if (arr[i] < arr[index] * 2) {
                    continue;
                } else {
                    break;
                }
            }
        }
        if (index-- >= arr.length) {
            return false;
        }
        index--;
        while (++index < arr.length) {
            for (int j = index+1; j < arr.length; j++) {
                if (arr[j] == arr[index] * 2) {
                    return true;
                } else if (arr[j] < arr[index] * 2) {
                    continue;
                } else {
                    break;
                }
            }
        }
        return false;
    }
}