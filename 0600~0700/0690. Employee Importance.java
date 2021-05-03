/* 
You are given a data structure of employee information, which includes the employee's unique id, his importance value and his direct subordinates' id.

For example, employee 1 is the leader of employee 2, and employee 2 is the leader of employee 3. They have importance value 15, 10 and 5, respectively. Then employee 1 has a data structure like [1, 15, [2]], and employee 2 has [2, 10, [3]], and employee 3 has [3, 5, []]. Note that although employee 3 is also a subordinate of employee 1, the relationship is not direct.

Now given the employee information of a company, and an employee id, you need to return the total importance value of this employee and all his subordinates.

Example 1:

Input: [[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
Output: 11
Explanation:
Employee 1 has importance value 5, and he has two direct subordinates: employee 2 and employee 3. They both have importance value 3. So the total importance value of employee 1 is 5 + 3 + 3 = 11.
 

Note:

One employee has at most one direct leader and may have several subordinates.
The maximum number of employees won't exceed 2000.
 */

/*
// Definition for Employee.
class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
};
*/

class Solution {
    Map<Integer, Employee> map = new HashMap<>();

    public int getImportance(List<Employee> employees, int id) {
        for (Employee e: employees) {
            map.put(e.id, e);
        }
        return dfs(id);
    }

    private int dfs(int id) {
        Employee employee = map.get(id);
        int ans = employee.importance;
        if (employee.subordinates == null) return ans;
        for (int subID: employee.subordinates) {
            ans += dfs(subID);
        }
        return ans;
    }
}



class Solution {
    Map<Integer, Employee> map = new HashMap<>();

    public int getImportance(List<Employee> employees, int id) {
        for (Employee e: employees) {
            map.put(e.id, e);
        }
        return bfs(id);
    }

    private int bfs(int id) {
        Deque<Integer> queue = new ArrayDeque<>();
        int ans = 0;
        queue.offer(id);
        while (!queue.isEmpty()) {
            Employee employee = map.get(queue.poll());
            ans += employee.importance;
            for (int subID: employee.subordinates) {
                queue.offer(subID);
            }
        }
        return ans;
    }
}