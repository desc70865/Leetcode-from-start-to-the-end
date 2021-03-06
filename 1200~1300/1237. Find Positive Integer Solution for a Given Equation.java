/* 
Given a function  f(x, y) and a value z, return all positive integer pairs x and y where f(x,y) == z.

The function is constantly increasing, i.e.:

f(x, y) < f(x + 1, y)
f(x, y) < f(x, y + 1)
The function interface is defined like this: 

interface CustomFunction {
public:
  // Returns positive integer f(x, y) for any given positive integer x and y.
  int f(int x, int y);
};
For custom testing purposes you're given an integer function_id and a target z as input, where function_id represent one function from an secret internal list, on the examples you'll know only two functions from the list.  

You may return the solutions in any order.

 

Example 1:

Input: function_id = 1, z = 5
Output: [[1,4],[2,3],[3,2],[4,1]]
Explanation: function_id = 1 means that f(x, y) = x + y
Example 2:

Input: function_id = 2, z = 5
Output: [[1,5],[5,1]]
Explanation: function_id = 2 means that f(x, y) = x * y
 

Constraints:

1 <= function_id <= 9
1 <= z <= 100
It's guaranteed that the solutions of f(x, y) == z will be on the range 1 <= x, y <= 1000
It's also guaranteed that f(x, y) will fit in 32 bit signed integer if 1 <= x, y <= 1000
 */

/*
 * // This is the custom function interface.
 * // You should not implement it, or speculate about its implementation
 * class CustomFunction {
 *     // Returns f(x, y) for any given positive integers x and y.
 *     // Note that f(x, y) is increasing with respect to both x and y.
 *     // i.e. f(x, y) < f(x + 1, y), f(x, y) < f(x, y + 1)
 *     public int f(int x, int y);
 * };
 */

class Solution {
    public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
        
    }
}



class Solution {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
        int x = 2, y = 512, step = 256, t;
        while ((t = check(customfunction, 1, y, z)) != 0 && step > 0) {
            if (t > 0) y -= step;
            else y += step;
            step /= 2;
        }
        if (t == 0) add(1, y);
        while (x < 1000 && ++y > 1) {
            while (y > 1 && (t = check(customfunction, x, --y, z)) > 0) ;
            if (t == 0) add(x, y);
            x++;
        }
        return res;
    }

    private int check(CustomFunction customfunction, int i, int j, int k) {
        return customfunction.f(i, j) - k;
    }

    private void add(int i, int j) {
        List<Integer> item = new ArrayList<>();
        item.add(i);
        item.add(j);
        res.add(item);
    }
}