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
        Arrays.sort(people, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
        List<int[]> list = new ArrayList<>();
        for (int[] p: people) list.add(p[1], p);
        return list.toArray(new int[0][2]);
    }
}



class Solution {
    public int[][] reconstructQueue(int[][] people) {
        quickSort(people, 0, people.length - 1);
        List<int[]> list = new ArrayList<>();
        for (int[] p: people) list.add(p[1], p);
        return list.toArray(new int[0][2]);
    }
    
    private void quickSort(int[][] arr, int left, int right) {
        if (left >= right) return;
        int pivot = partition(arr, left, right);
        quickSort(arr, left, pivot - 1);
        quickSort(arr, pivot + 1, right);
    }
    
    private int partition(int[][] arr, int l, int r) {
        int[] pivot = arr[l];
        while (l < r) {
            while (l < r) {
                if (cmp(arr[r], pivot)) {
                    arr[l++] = arr[r];
                    break;
                }
                r--;
            }
            while (l < r) {
                if (cmp(pivot, arr[l])) {
                    arr[r--] = arr[l];
                    break;
                }
                l++;
            }
        }
        arr[l] = pivot;
        return l;
    }

    private boolean cmp(int[] a, int[] b) {
        return a[0] == b[0] ? a[1] < b[1] : b[0] < a[0];
    }
}



class Solution {
    public int[][] reconstructQueue(int[][] people) {
        TreeMap<Integer, TreeSet<Integer>> map = new TreeMap<>();
        for (int[] p: people) {
            map.computeIfAbsent(p[0], z -> new TreeSet<>()).add(p[1]);
        }
        List<int[]> list = new ArrayList<>();
        for (int k: map.descendingKeySet()) {
            for (int v: map.get(k)) {
                list.add(v, new int[] {k, v});
            }
        }
        return list.toArray(new int[0][2]);
    }
}



class Solution {
    static final int BASE = 1048576;
    
    public int[][] reconstructQueue(int[][] people) {
        int len = people.length;
        if (len == 0) return new int[0][0];
        long[] aux = new long[len];
        for (int i = 0; i < len; i++) {
            aux[i] = ((long) people[i][0] << 20) + BASE - 1 - people[i][1];
        }
        Arrays.sort(aux);
        List<int[]> list = new ArrayList<>();
        for (int i = len - 1; i >= 0; i--) {
            int a = (int) (aux[i] >> 20);
            int b = (int) (BASE - 1 - aux[i] % BASE);
            list.add(b, new int[] {a, b});
        }
        return list.toArray(new int[0][2]);
    }
}