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

/*
 * 作者：keylol
 * 链接：https://leetcode-cn.com/problems/unique-number-of-occurrences/solution/zao-an-da-gong-gou-by-keylol/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
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