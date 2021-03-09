/* 
There are n people whose IDs go from 0 to n - 1 and each person belongs exactly to one group. Given the array groupSizes of length n telling the group size each person belongs to, return the groups there are and the people's IDs each group includes.

You can return any solution in any order and the same applies for IDs. Also, it is guaranteed that there exists at least one solution. 

 

Example 1:

Input: groupSizes = [3,3,3,3,3,1,3]
Output: [[5],[0,1,2],[3,4,6]]
Explanation: 
Other possible solutions are [[2,1,6],[5],[0,4,3]] and [[5],[0,6,2],[4,3,1]].
Example 2:

Input: groupSizes = [2,1,3,3,3,2]
Output: [[1],[0,5],[2,3,4]]
 

Constraints:

groupSizes.length == n
1 <= n <= 500
1 <= groupSizes[i] <= n
 */

class Solution {
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < groupSizes.length; i++) {
            map.computeIfAbsent(groupSizes[i], z -> new ArrayList<>()).add(i);
        }
        List<List<Integer>> list = new ArrayList<>();
        for (int size: map.keySet()) {
            List<Integer> cur = map.get(size);
            for (int i = 0; i < cur.size(); i += size) {
                List<Integer> tmp = new ArrayList<>();
                for (int j = i; j < i + size; j++) {
                    tmp.add(cur.get(j));
                }
                list.add(tmp);
            }
        }
        return list;
    }
}



class Solution {
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        int len = groupSizes.length;
        for (int i = 0; i < len; i++) {
            groupSizes[i] <<= 9;
            groupSizes[i] += i;
        }
        Arrays.sort(groupSizes);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < len;) {
            List<Integer> list = new ArrayList<>();
            for (int j = groupSizes[i] / 512; j > 0; j--) {
                list.add(groupSizes[i++] % 512);
            }
            res.add(list);
        }
        return res;
    }
}