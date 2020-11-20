/* 
Given an array of integers arr and an integer k. Find the least number of unique integers after removing exactly k elements.

 

Example 1:

Input: arr = [5,5,4], k = 1
Output: 1
Explanation: Remove the single 4, only 5 is left.
Example 2:
Input: arr = [4,3,1,1,3,3,2], k = 3
Output: 2
Explanation: Remove 4, 2 and either one of the two 1s or three 3s. 1 and 3 will be left.
 

Constraints:

1 <= arr.length <= 10^5
1 <= arr[i] <= 10^9
0 <= k <= arr.length
 */

class Solution {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: arr) {
            map.merge(num, 1, Integer::sum);
        }
        int sum = map.size();
        List<Integer> list = new ArrayList<>(map.values());
        Collections.sort(list);
        for (int num: list) {
            if (k >= num) {
                k -= num;
                sum--;
            } else {
                break;
            }
        }
        return sum;
    }
}



class Solution {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Arrays.sort(arr);
        int pre = arr[0];
        int cnt = 0;
        int sum = 0;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num: arr) {
            if (num == pre) cnt++;
            else {
                map.merge(cnt, 1, Integer::sum);
                sum++;
                cnt = 1;
                pre = num;
            }
        }
        map.merge(cnt, 1, Integer::sum);
        sum++;
        // System.out.println(sum);
        for (int key: map.keySet()) {
            if (k < key) break;
            for (int i = 0; i < map.get(key); i++) {
                if (k >= key) {
                    k -= key;
                    sum--;
                } else {
                    break;
                }
            }
        }
        return sum;
    }
}