/* 
Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.

Note:
The number of people is less than 1,100.

 
Example

Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 */

class Solution {
    public int[][] reconstructQueue(int[][] people) {
        
    }
}



class Solution {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // if the heights are equal, compare k-values
                return o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0];
            }
        });

        List<int[]> output = new LinkedList<>();
        for (int[] p : people) {
            output.add(p[1], p);
        }

        int n = people.length;
        return output.toArray(new int[n][2]);
    }
}



class Solution {
    public int[][] reconstructQueue(int[][] people) {
        quickSort(people, 0, people.length-1);
        
        List<int[]> res = new ArrayList<>();
        for (int[] p : people) {
            res.add(p[1], p);
        }
        
        return res.toArray(new int[people.length][2]);
    }
    
    private void quickSort(int[][] people, int left, int right) {
        if (left >= right) {
            return;
        }
        int[] pivot = people[left];
        int l = left;
        int r = right;
        
        while (l < r) {
            while (l < r && (people[r][0] < pivot[0] ||
                (people[r][0] == pivot[0] && people[r][1] >= pivot[1]))) {
                r--;
            }
            if (l < r) {
                people[l++] = people[r];
            }
            while (l < r && (people[l][0] > pivot[0] ||
                (people[l][0] == pivot[0] && people[l][1] <= pivot[1]))) {
                l++;
            }
            if (l < r) {
                people[r--] = people[l];
            }
        }
        
        people[l] = pivot;
        quickSort(people, left, l - 1);
        quickSort(people, l + 1, right);
    }
}