/* 
There are n items each belonging to zero or one of m groups where group[i] is the group that the i-th item belongs to and it's equal to -1 if the i-th item belongs to no group. The items and the groups are zero indexed. A group can have no item belonging to it.

Return a sorted list of the items such that:

The items that belong to the same group are next to each other in the sorted list.
There are some relations between these items where beforeItems[i] is a list containing all the items that should come before the i-th item in the sorted array (to the left of the i-th item).
Return any solution if there is more than one solution and return an empty list if there is no solution.

 

Example 1:



Input: n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems = [[],[6],[5],[6],[3,6],[],[],[]]
Output: [6,3,4,1,5,2,0,7]
Example 2:

Input: n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems = [[],[6],[5],[6],[3],[],[4],[]]
Output: []
Explanation: This is the same as example 1 except that 4 needs to be before 6 in the sorted list.
 

Constraints:

1 <= m <= n <= 3 * 104
group.length == beforeItems.length == n
-1 <= group[i] <= m - 1
0 <= beforeItems[i].length <= n - 1
0 <= beforeItems[i][j] <= n - 1
i != beforeItems[i][j]
beforeItems[i] does not contain duplicates elements.
 */

class Solution {
    public static int findex;
    public static int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        // keep all non grouped items into separate groups,
        // so that we find the cycle among all items that belong to individual groups
        for (int i = 0; i < group.length; i++) {
            if (group[i] == -1) group[i] = m++;
        }

        ArrayList<Integer>[] groupMap = new ArrayList[m];
        ArrayList<Integer>[] groupItemMap = new ArrayList[m];
        for (int i = 0; i < m; i++) {
            groupMap[i] = new ArrayList<>();
            groupItemMap[i] = new ArrayList<>();
        }

        ArrayList<Integer>[] items = new ArrayList[n];
        // prepare group graph, group to item relation from given items
        for (int i = 0; i < n; i++) {
            items[i] = new ArrayList<>();
            for (int j : beforeItems.get(i)) {
                // if dependent group is not the same group we are walking through
                if (group[j] != group[i]) groupMap[group[i]].add(group[j]);
                items[i].add(j);
            }
            groupItemMap[group[i]].add(i);
        }

        int[] sortedGroups = new int[m];
        // if groups cannot be topsorted, stop
        if (!topsortGroups(groupMap, sortedGroups)) return new int[]{};

        int[] answer = new int[n];
        // if items cannot be topsorted, stop
        if (!topsortItems(groupItemMap, items, answer, sortedGroups)) return new int[]{};
        return answer;
    }

    private static boolean topsortGroups(ArrayList<Integer>[] groupMap, int[] sortedGroups) {
        int[] visited = new int[sortedGroups.length];
        findex = 0;
        for (int i = 0; i < sortedGroups.length; i++) {
            if (!dfs(groupMap, visited, sortedGroups, i)) return false;
        }
        return true;
    }

    private static boolean topsortItems(ArrayList<Integer>[] groupItemMap, ArrayList<Integer>[] items, int[] answer, int[] sortedGroups) {
        int[] visited = new int[items.length];
        findex = 0;
        for (int i = 0; i < sortedGroups.length; i++) {
            // fetch items belong to group i
            for (int item : groupItemMap[sortedGroups[i]]) {
                if (!dfs(items, visited, answer, item)) return false;
            }
        }
        return true;
    }

    private static boolean dfs(ArrayList<Integer>[] graph, int[] visited, int[] buffer, int node) {
        if (visited[node] == 1) return true;
        if (visited[node] == -1) return false; // cycle
        visited[node] = -1;
        for (int i : graph[node]) {
            if (!dfs(graph, visited, buffer, i)) return false;
        }
        buffer[findex++] = node;
        visited[node] = 1;
        return true;
    }
}