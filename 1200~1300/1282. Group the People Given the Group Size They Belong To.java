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
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < groupSizes.length; i++) {
            int key = groupSizes[i];
            if (! map.containsKey(key)) tmp = new ArrayList<>();
            else tmp = map.get(key);
            tmp.add(i);
            map.put(key, tmp);
        }

        for (Map.Entry<Integer, List<Integer>> entry: map.entrySet()) {
            int key = entry.getKey();
            List<Integer> list = entry.getValue(), l = new ArrayList<>();
            for (Integer n: list) {
                l.add(n);
                if (l.size() == key) {
                    res.add(new ArrayList<>(l));
                    l = new ArrayList<>();
                }
            }
        }
        return res;
    }
}



class Solution {
    private List<List<Integer>> result = new ArrayList<>();
    private boolean[] isVisited;
    
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        isVisited = new boolean[groupSizes.length];
        for (int i = 0; i < groupSizes.length; i++) {
            if (!isVisited[i]) findGroup(groupSizes, i);
        }
        return result;
    }
    
    public void findGroup(int[] groupSizez, int i){
        List<Integer> list = new ArrayList<Integer>();
        int max = groupSizez[i--];
        while (list.size() < max && ++i < groupSizez.length) {
            if (groupSizez[i] == max && !isVisited[i]) {
                isVisited[i] = true;
                list.add(i);
            }
        }
        result.add(list);
    }
}