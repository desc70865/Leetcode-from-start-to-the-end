/* 
There are n soldiers standing in a line. Each soldier is assigned a unique rating value.

You have to form a team of 3 soldiers amongst them under the following rules:

Choose 3 soldiers with index (i, j, k) with rating (rating[i], rating[j], rating[k]).
A team is valid if:  (rating[i] < rating[j] < rating[k]) or (rating[i] > rating[j] > rating[k]) where (0 <= i < j < k < n).
Return the number of teams you can form given the conditions. (soldiers can be part of multiple teams).

 

Example 1:

Input: rating = [2,5,3,4,1]
Output: 3
Explanation: We can form three teams given the conditions. (2,3,4), (5,4,1), (5,3,1). 
Example 2:

Input: rating = [2,1,3]
Output: 0
Explanation: We can't form any team given the conditions.
Example 3:

Input: rating = [1,2,3,4]
Output: 4
 

Constraints:

n == rating.length
1 <= n <= 200
1 <= rating[i] <= 10^5
 */

class Solution {
    public int numTeams(int[] rating) {
        Node[] arr = new Node[rating.length];
        for (int i = 0; i < rating.length; i++) {
            arr[i] = new Node(rating[i], i);
        }
        
        mergeSort(arr, 0, arr.length - 1, new Node[arr.length]);
        
        int answer = 0;
        for (int i = 0; i < arr.length; i++) {
            int right = rating.length - 1 - arr[i].index;
            int rightSmaller = arr[i].rightSmaller;
            int rightLarger = right - rightSmaller;
            
            int larger = rating.length - 1 - i;
            int smaller = i;
            int leftLarger = larger - rightLarger;
            int leftSmaller = smaller - rightSmaller;
            answer += leftSmaller * rightLarger;
            answer += leftLarger * rightSmaller;
        }
        
        return answer;
    }
    
    private void mergeSort(Node[] arr, int left, int right, Node[] aux) {
        if (left == right) {
            return;
        }
        
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid, aux);
        mergeSort(arr, mid + 1, right, aux);
        for (int i = left; i <= right; i++) {
            aux[i] = arr[i];
        }
        
        int lp = left;
        int rp = mid + 1;
        int index = lp;
        int rightSmaller = 0;
        while (lp <= mid && rp <= right) {
            if (aux[lp].val < aux[rp].val) {
                aux[lp].rightSmaller += rightSmaller;
                
                arr[index] = aux[lp];
                lp++;
            } else {
                rightSmaller++;
                
                arr[index] = aux[rp];
                rp++;
            }
            index++;
        }
        
        while (lp <= mid) {
            aux[lp].rightSmaller += rightSmaller;
            arr[index] = aux[lp];
            
            lp++;
            index++;
        }
        
        while (rp <= mid) {
            arr[index] = aux[rp];
            
            rp++;
            index++;
        }
    }
    
    private class Node {
        int val;
        int index;
        int rightSmaller;
        Node(int val, int index) {
            this.val = val;
            this.index = index;
            this.rightSmaller = 0;
        }
    }
}