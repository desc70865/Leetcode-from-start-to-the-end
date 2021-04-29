/* 
Given an array of integers arr, write a function that returns true if and only if the number of occurrences of each value in the array is unique.

 

Example 1:

Input: arr = [1,2,2,1,1,3]
Output: true
Explanation: The value 1 has 3 occurrences, 2 has 2 and 3 has 1. No two values have the same number of occurrences.
Example 2:

Input: arr = [1,2]
Output: false
Example 3:

Input: arr = [-3,0,1,-3,1,1,1,-3,10,0]
Output: true
 

Constraints:

1 <= arr.length <= 1000
-1000 <= arr[i] <= 1000
 */

class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        Arrays.sort(arr);
        int cnt = 1;
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) cnt++;
            else {
                if (! set.add(cnt)) return false;
                cnt = 1;
            }
        }
        return set.add(cnt);
    }
}



class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: arr) map.merge(num, 1, Integer::sum);
        Set<Integer> set = new HashSet<>();
        for (Integer v: map.values()) if (! set.add(v)) return false;
        return true;
    }
}



class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        int[] map = new int[2001];
        for (int num: arr) map[num + 1000]++;
        Set<Integer> set = new HashSet<>();
        for (int num: map) if (num != 0 && ! set.add(num)) return false;
        return true;
    }
}



class Solution {
    static final int SIZE = 2000;
    static final int LEN = 1000;

    public boolean uniqueOccurrences(int[] arr) {
        int[] bucket = new int[SIZE + 1];
        int size = 0;
        for (int e: arr) {
            if (bucket[e + LEN]++ == 0) {
                size++;
            }
        }
        if (size * (size + 1) / 2 > arr.length) return false;
        int[] cnt = new int[LEN];
        for (int e: bucket) {
            if (e == 0) continue;
            if (cnt[e]++ > 0) return false;
        }
        return true;
    }
}