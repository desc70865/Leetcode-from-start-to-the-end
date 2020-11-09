/* 
Design a data structure that accepts a stream of integers and checks if it has a pair of integers that sum up to a particular value.

Implement the TwoSum class:

TwoSum() Initializes the TwoSum object, with an empty array initially.
void add(int number) Adds number to the data structure.
boolean find(int value) Returns true if there exists any pair of numbers whose sum is equal to value, otherwise, it returns false.
 

Example 1:

Input
["TwoSum", "add", "add", "add", "find", "find"]
[[], [1], [3], [5], [4], [7]]
Output
[null, null, null, null, true, false]

Explanation
TwoSum twoSum = new TwoSum();
twoSum.add(1);   // [] --> [1]
twoSum.add(3);   // [1] --> [1,3]
twoSum.add(5);   // [1,3] --> [1,3,5]
twoSum.find(4);  // 1 + 3 = 4, return true
twoSum.find(7);  // No two integers sum up to 7, return false
 

Constraints:

-105 <= number <= 105
-231 <= value <= 231 - 1
At most 5 * 104 calls will be made to add and find.
 */

class TwoSum {

    /** Initialize your data structure here. */
    public TwoSum() {

    }
    
    /** Add the number to an internal data structure.. */
    public void add(int number) {

    }
    
    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {

    }
}

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */



class TwoSum {

    private final List<Integer> list;
    
    private long min = Integer.MAX_VALUE;
    private long max = Integer.MIN_VALUE;
    private long minSum = Integer.MAX_VALUE;
    private long maxSum = Integer.MIN_VALUE;

    /**
     * Initialize your data structure here.
     */
    public TwoSum() {
        list = new LinkedList<>();
    }

    /**
     * Add the number to an internal data structure..
     */
    public void add(int number) {
        list.add(number);
        if (number < min) {
            min = number;
            minSum = min << 1;
        }
        if (number > max) {
            max = number;
            maxSum = max << 1;
        }
    }

    /**
     * Find if there exists any pair of numbers which sum is equal to the value.
     */
    public boolean find(int value) {
        if (value < minSum || value > maxSum) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        for (int num: list) {
            if (set.contains(value - num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }
}