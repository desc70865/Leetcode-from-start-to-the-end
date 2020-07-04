/* 
Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0.
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

According to the example above:

equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
 

The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
 */

class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        
    }
}



class Solution {
    private Map<String, Map<String, Double>> graph;
    
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        
        buildGraph(equations, values);
        double[] result = new double[queries.size()];
        
        for (int i = 0; i < queries.size(); i++) {
            List<String> tmp = queries.get(i);
            String key = tmp.get(0), value = tmp.get(1);
            result[i] = getPathWeight(key, value, new HashSet<>());
        }
        
        return result;
    }
    
    private void buildGraph(List<List<String>> equations, double[] values) {
        graph = new HashMap<>();
        String key, val;
        
        for (int i = 0; i < equations.size(); i++) {
            key = equations.get(i).get(0);
            val = equations.get(i).get(1);
            graph.computeIfAbsent(key, x -> new HashMap<>()).put(val, 1 * values[i]);
            graph.computeIfAbsent(val, x -> new HashMap<>()).put(key, 1 / values[i]);
            /* 
            graph.putIfAbsent(key, new HashMap<>());
            graph.putIfAbsent(val, new HashMap<>());
            graph.get(key).put(val, 1 * values[i]);
            graph.get(val).put(key, 1 / values[i]);
             */
        }
        
        return;
    }
    
    private double getPathWeight(String start, String end, Set<String> visited) {
        
        if (! graph.containsKey(start) || ! graph.containsKey(end)) {
            return -1.0;
        } else if (start.equals(end)) {
            return 1.0;
        } else if (graph.get(start).containsKey(end)) {
            return graph.get(start).get(end);
        }
        
        visited.add(start);
        
        for (Map.Entry<String, Double> neighbour : graph.get(start).entrySet()) {
            String key = neighbour.getKey();
            if (visited.contains(key)) {
                continue;
            }
            double productWeight = getPathWeight(key, end, visited);
            if (productWeight != -1.0) {
                /* 
                graph.computeIfAbsent(key, x -> new HashMap<>()).put(end, 1 * productWeight);
                graph.computeIfAbsent(end, x -> new HashMap<>()).put(key, 1 / productWeight);
                 */
                return neighbour.getValue() * productWeight;
            }
        }
        
        return -1.0;
    }
}



class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int count = 0;
        
        Map<String, Integer> map = new HashMap<>();
        for (List<String> list : equations) {
            for (String s : list) {
                if (! map.containsKey(s)) {
                    map.put(s,count++);
                }
            }
        }
        
        double[][] graph = new double[count+1][count+1];
        
        for (String s : map.keySet()) {
            int x = map.get(s);
            graph[x][x] = 1.0;
        }
        int index = 0;
        for (List<String> list : equations) {
            String a = list.get(0);
            String b = list.get(1);
            int aa = map.get(a);
            int bb = map.get(b);
            double value = values[index++];
            graph[aa][bb] = value;
            graph[bb][aa] = 1 / value;
        }
        
        // Floyd -> Dijkstra
        int n = count+1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (graph[j][k] != 0) {
                        continue;
                    } else if (j == k) { // error
                        graph[j][k] = 1.0;
                    } else if (graph[j][i] != 0 && graph[i][k] != 0) {
                        graph[j][k] = graph[j][i] * graph[i][k];
                    }
                }
            }
        }
        
        double[] res = new double[queries.size()];
        for (int i = 0; i < res.length; i++) {
            List<String> q = queries.get(i);
            String a = q.get(0);
            String b = q.get(1);
            if (map.containsKey(a) && map.containsKey(b)) {
                double ans = graph[map.get(a)][map.get(b)];
                res[i] = ans == 0 ? -1.0 : ans;
            } else {
                res[i] = -1.0;
            }
        }
        return res;
    }
}


// XXXX


class Solution {
    private Map<String, Map<String, Double>> graph;
    
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        
        buildGraph(equations, values);
        double[] result = new double[queries.size()];
        
        for (int i = 0; i < queries.size(); i++) {
            List<String> tmp = queries.get(i);
            String key = tmp.get(0), val = tmp.get(1);
            if (! graph.containsKey(key) || ! graph.containsKey(val)) {
                result[i] = -1.0;
                continue;
            }
            result[i] = bfs(new HashMap<String, Double>(){{
                    put(key, 1.0);
                }}, new HashMap<String, Double>(){{
                    put(val, 1.0);
                }}, new HashSet<>(), false);
        }
        
        return result;
    }
    
    private void buildGraph(List<List<String>> equations, double[] values) {
        graph = new HashMap<>();
        String key, val;
        
        for (int i = 0; i < equations.size(); i++) {
            key = equations.get(i).get(0);
            val = equations.get(i).get(1);
            graph.computeIfAbsent(key, x -> new HashMap<>()).put(val, 1 * values[i]);
            graph.computeIfAbsent(val, x -> new HashMap<>()).put(key, 1 / values[i]);
        }
        
        return;
    }
    
    private double bfs(Map<String, Double> beginMap, Map<String, Double> endMap, Set<String> visited, boolean rev) {
        Map<String, Double> nextMap = new HashMap<>();
        for (Map.Entry<String, Double> entry : beginMap.entrySet()) {
            String str = entry.getKey();
            if (visited.contains(str)) {
                continue;
            }
            visited.add(str);
            double val = entry.getValue();
            if (endMap.containsKey(str)) {
                return val * endMap.get(str);
            }
            
            for (Map.Entry<String, Double> map : graph.get(str).entrySet()) {
                String key = map.getKey();
                if (nextMap.containsKey(key)) {
                    continue;
                }
                double cal = map.getValue();
                if (rev) {
                    cal = 1 / cal;
                }
                nextMap.put(key, cal * val);
            }
        }
        
        if (nextMap.isEmpty()) {
            return -1.0;
        }
        
        if (nextMap.size() > endMap.size()) {
            return bfs(endMap, nextMap, visited, !rev);
        } else {
            return bfs(nextMap, endMap, visited, rev);
        }
    }
}