/* 
There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

Example 1:

Input: 2, [[1,0]] 
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished   
             course 0. So the correct course order is [0,1] .
Example 2:

Input: 4, [[1,0],[2,0],[3,1],[3,2]]
Output: [0,1,2,3] or [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both     
             courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. 
             So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
Note:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
 */

class Solution {
    // DAG -> 拓扑排序 -> 序列
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) {
            return new int[0];
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] inDegrees = new int[numCourses];
        // 入度表
        for (int[] p: prerequisites) {
            inDegrees[p[0]]++;
            map.computeIfAbsent(p[1], z -> new ArrayList<>()).add(p[0]);
        }        
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < inDegrees.length; i++) {
            if (inDegrees[i] == 0) queue.offer(i);
        }
        int idx = 0;
        int[] res = new int[numCourses];
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            res[idx++] = curr;
            if (!map.containsKey(curr)) continue;
            for (int next: map.get(curr)) {
                if (--inDegrees[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        return idx == numCourses ? res : new int[0];
    }
}



class Solution {
    int[] visited;
    int[] result;
    int[] courses;
    int[] next;
    int[] course;
    int index = 0;
    
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        visited = new int[numCourses]; // all 0s at first, 0: untouched
        result = new int[numCourses];
        courses = new int[numCourses];
        Arrays.fill(courses, -1);
        next = new int[prerequisites.length];
        course = new int[prerequisites.length];
       
        for (int i = 0; i < prerequisites.length; i++) {
            next[i] = courses[prerequisites[i][0]];
            courses[prerequisites[i][0]] = i;
            course[i] = prerequisites[i][1];
        }
        
        for (int i = 0; i < numCourses; i++) {
            if (cycle(i)) {
                return new int[0]; // has cycle: return empty array
            }
        }
       
        return result;
    }
   
    private boolean cycle(int num) {
        if (visited[num] > 0) return visited[num] == 1;   
        visited[num] = 1; // touched
        for (int i = courses[num]; i != -1; i = next[i]) {
            if (cycle(course[i])) {
                return true; // has cycle
            }
        }
        visited[num] = 2; // checked
        result[index++] = num;
        return false; // will not form cycle
    }
}