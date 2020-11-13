/* 
A Stepping Number is an integer such that all of its adjacent digits have an absolute difference of exactly 1. For example, 321 is a Stepping Number while 421 is not.

Given two integers low and high, find and return a sorted list of all the Stepping Numbers in the range [low, high] inclusive.

 

Example 1:

Input: low = 0, high = 21
Output: [0,1,2,3,4,5,6,7,8,9,10,12,21]
 

Constraints:

0 <= low <= high <= 2 * 10^9
 */

class Solution {
    public List<Integer> countSteppingNumbers(int low, int high) {
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        if (low == 0) {
            result.add(0);
        }
        for (int i = 1; i <= 9; i++) {
            queue.offer(i);
        }
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur >= low && cur <= high) {
                result.add(cur);
            }
            if (cur > high / 10) {
                continue;
            }
            int r = cur % 10;
            if (r != 9 && cur * 10 + r + 1 <= high) {
                queue.offer(cur * 10 + r + 1);
            }
            if (r != 0 && cur * 10 + r - 1 <= high) {
                queue.offer(cur * 10 + r - 1);
            }
        }
        Collections.sort(result);
        return result;
    }
}



class Solution {
    static final int INF = (int) 2e9; 
    static List<Integer> list;

    static {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i <= 9; i++) {
            dfs(set, i);
        }
        list = new ArrayList<>(set);
        Collections.sort(list);
    }

    private static void dfs(Set<Integer> set, int num) {
        set.add(num);
        if (num > INF / 10) {
            return;
        }
        for (int i = -1; i <= 1; i += 2) {
            int d = num % 10 + i;
            if (d < 0 || d > 9) {
                continue;
            }
            int next = num * 10 + d;
            if (next <= INF && ! set.contains(next)) {
                dfs(set, next);
            }
        }
    }

    public List<Integer> countSteppingNumbers(int low, int high) {
        int l = Collections.binarySearch(list, low - 1);
        l = l >= 0 ? l + 1 : - l - 1;
        int r = Collections.binarySearch(list, high + 1);
        r = r >= 0 ? r : - r - 1;
        return list.subList(l, r);
    }
}