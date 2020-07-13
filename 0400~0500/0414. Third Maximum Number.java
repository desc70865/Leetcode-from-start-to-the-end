/* 
Given a non-empty array of integers, return the third maximum number in this array. If it does not exist, return the maximum number. The time complexity must be in O(n).

Example 1:
Input: [3, 2, 1]

Output: 1

Explanation: The third maximum is 1.
Example 2:
Input: [1, 2]

Output: 2

Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
Example 3:
Input: [2, 2, 3, 1]

Output: 1

Explanation: Note that the third maximum here means the third maximum distinct number.
Both numbers with value 2 are both considered as second maximum.
 */

class Solution {
    public int thirdMax(int[] nums) {
        int LEN = nums.length - 1;
        Arrays.sort(nums);
        int cnt = 1;
        for (int i = LEN-1; i >= 0; i--) {
            if (nums[i] != nums[i+1]) {
                cnt++;
            }
            if (cnt == 3) {
                return nums[i];
            }
        }
        return nums[LEN];
    }
}

// PriorityQueue<Integer> heap = new PriorityQueue<Integer>((n1, n2) -> n1 - n2);

class Solution {
    public int thirdMax(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num: nums) {
            set.add(num);
        }
        int LEN = set.size(), index = 0;
        if (LEN == 1) {
            return nums[0];
        }
        int[] arr = new int[LEN];
        for (int num: set) {
            arr[index++] = num;
        }
        Arrays.sort(arr);
        return LEN == 2 ? arr[LEN-1] : arr[LEN - 3];
    }
}



class Solution {
    private int[] aux;
    private int cnt = 0;
    public int thirdMax(int[] nums) {
        aux = new int[3];
        Arrays.fill(aux, Integer.MIN_VALUE);
        for (int num: nums) {
            if (num > aux[2]) {
                update(num);
            }
        }
        return cnt < 3 ? aux[0] : aux[2];
    }
    
    private void update(int x) {
        if (aux[1] == x || aux[0] == x) {
            return;
        }
        aux[2] = x;
        cnt++;
        if (aux[1] < x) {
            swap(1, 2);
            if (aux[0] < x) {
                swap(0, 1);
            }
        }
    }
    
    private void swap(int i, int j) {
        int temp = aux[i];
        aux[i] = aux[j];
        aux[j] = temp;
    }
}



class Solution {
    public int thirdMax(int[] nums) {
        int max = nums[0];
        int secondMax = Integer.MIN_VALUE + 1;
        int thirdMax = Integer.MIN_VALUE;
        boolean thirdMaxUpdated = false;
        int maxChanges = 0;

        if (nums.length == 1) {
            return max;
        } else if (nums.length == 2) {
            return nums[1] > max ? nums[1] : max;
        }
        
        for (int i = 1; i < nums.length; i++) {
            int currentNum = nums[i];
            if (max < currentNum) {
                thirdMax = secondMax;
                secondMax = max;
                max = currentNum;
                maxChanges++;
            } else if (secondMax < currentNum && currentNum < max 
            || (nums.length <= 3 && currentNum <= Integer.MIN_VALUE + 1 && currentNum >= thirdMax)) {
                thirdMax = secondMax;
                secondMax = currentNum;
                maxChanges++;
            } else if (currentNum > thirdMax && currentNum < secondMax 
            || (nums.length <= 3 && currentNum <= Integer.MIN_VALUE)) {
                thirdMax = currentNum;
                thirdMaxUpdated = true;
            }

        }
        
        if (thirdMaxUpdated || maxChanges >= 2) {
            return thirdMax;
        } else {
            return max;
        }
    }
}

// STUPID~

class Solution {
    public int thirdMax(int[] nums) {
        int max = nums[0];
        int secondMax = Integer.MIN_VALUE + 1;
        int thirdMax = Integer.MIN_VALUE;
        boolean thirdMaxUpdated = false;
        int maxChanges = 0;

        if (nums.length == 1) {
            return max;
        } else if (nums.length == 2) {
            return nums[1] > max ? nums[1] : max;
        }
        
        for (int i = 1; i < nums.length; i++) {
            int currentNum = nums[i];
            if (max < currentNum) {
                thirdMax = secondMax;
                secondMax = max;
                max = currentNum;
                maxChanges++;
            } else if (secondMax < currentNum && currentNum < max) {
                thirdMax = secondMax;
                secondMax = currentNum;
                maxChanges++;
            } else if (thirdMax < currentNum && currentNum < secondMax) {
                thirdMax = currentNum;
                thirdMaxUpdated = true;
            }
        }
        
        if (thirdMaxUpdated || maxChanges >= 2) {
            return thirdMax;
        } else {
            return max;
        }
    }
}