/* 
There are N rooms and you start in room 0.  Each room has a distinct number in 0, 1, 2, ..., N-1, and each room may have some keys to access the next room. 

Formally, each room i has a list of keys rooms[i], and each key rooms[i][j] is an integer in [0, 1, ..., N-1] where N = rooms.length.  A key rooms[i][j] = v opens the room with number v.

Initially, all the rooms start locked (except for room 0). 

You can walk back and forth between rooms freely.

Return true if and only if you can enter every room.

Example 1:

Input: [[1],[2],[3],[]]
Output: true
Explanation:  
We start in room 0, and pick up key 1.
We then go to room 1, and pick up key 2.
We then go to room 2, and pick up key 3.
We then go to room 3.  Since we were able to go to every room, we return true.
Example 2:

Input: [[1,3],[3,0,1],[2],[0]]
Output: false
Explanation: We can't enter the room with number 2.
Note:

1 <= rooms.length <= 1000
0 <= rooms[i].length <= 1000
The number of keys in all rooms combined is at most 3000.
 */

class Solution {
    Set<Integer> keys = new HashSet<>();
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        check(rooms, 0);
        return keys.size() == rooms.size();
    }

    private void check(List<List<Integer>> rooms, int i) {
        keys.add(i);
        List<Integer> list = rooms.get(i);
        for (int j = 0; j < list.size(); j++) {
            int k = list.get(j);
            if (! keys.contains(k)) {
                check(rooms, k);
            }
        }
    }
}



class Solution {
    int counter;
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int[] visited = new int[rooms.size()];
        dfs(0, visited, rooms);
        return counter == rooms.size();
    }
    
    private void dfs(int r, int[] visited, List<List<Integer>> rooms) {
        if (visited[r] == 1) return;
        visited[r] = 1;
        counter++;
        for (Integer l: rooms.get(r)) {
            dfs(l, visited, rooms);
        }
    }
}



class Solution {
    boolean[] visit;
    int count = 0;
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int len = rooms.size();
        visit = new boolean[len];
        check(rooms, 0);
        return count == len;
    }

    private void check(List<List<Integer>> rooms, int i) {
        visit[i] = true;
        count++;
        for (Integer k: rooms.get(i)) {
            if (visit[k]) continue;
            check(rooms, k);
        }
    }
}