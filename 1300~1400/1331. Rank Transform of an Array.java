/* 
Given an array of integers arr, replace each element with its rank.

The rank represents how large the element is. The rank has the following rules:

Rank is an integer starting from 1.
The larger the element, the larger the rank. If two elements are equal, their rank must be the same.
Rank should be as small as possible.
 

Example 1:

Input: arr = [40,10,20,30]
Output: [4,1,2,3]
Explanation: 40 is the largest element. 10 is the smallest. 20 is the second smallest. 30 is the third smallest.
Example 2:

Input: arr = [100,100,100]
Output: [1,1,1]
Explanation: Same elements share the same rank.
Example 3:

Input: arr = [37,12,28,9,100,56,80,5,12]
Output: [5,3,4,2,8,6,7,1,3]
 

Constraints:

0 <= arr.length <= 105
-109 <= arr[i] <= 109
 */

class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int[] A = arr.clone();
        Arrays.sort(A);
        int idx = 1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: A) {
            if (map.containsKey(num)) continue;
            map.put(num, idx++);
        }
        int N = A.length;
        for (int i = 0; i < N; i++) {
            arr[i] = map.get(arr[i]);
        }
        return arr;
    }
}

// copy

class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i=0;i<arr.length;i++){
            max = arr[i]>max?arr[i]:max;
            min = arr[i]<min?arr[i]:min;
        }
        int[] count = new int[max - min+1];
        for(int num : arr){
            count[num - min] = 1;
        }
        for(int i=1;i<max - min + 1;i++){
            count[i] += count[i-1];
        }
        int[] res = new int[arr.length];
        for(int i=0;i<arr.length;i++){
            res[i] = count[arr[i]-min];
        }
        return res;
    }
}