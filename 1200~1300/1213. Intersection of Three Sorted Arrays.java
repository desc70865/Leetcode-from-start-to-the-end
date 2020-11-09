/* 
Given three integer arrays arr1, arr2 and arr3 sorted in strictly increasing order, return a sorted array of only the integers that appeared in all three arrays.

 

Example 1:

Input: arr1 = [1,2,3,4,5], arr2 = [1,2,5,7,9], arr3 = [1,3,4,5,8]
Output: [1,5]
Explanation: Only 1 and 5 appeared in the three arrays.
 

Constraints:

1 <= arr1.length, arr2.length, arr3.length <= 1000
1 <= arr1[i], arr2[i], arr3[i] <= 2000
 */

class Solution {
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        Map<Integer, Integer> map = new HashMap<>();
        addAll(map, arr1);
        addAll(map, arr2);
        addAll(map, arr3);
        List<Integer> res = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if (entry.getValue() == 3) {
                res.add(entry.getKey());
            }
        }
        return res;
    }

    private void addAll(Map<Integer, Integer> map, int[] arr) {
        for (int num: arr) {
            map.merge(num, 1, Integer::sum);
        }
    }
}