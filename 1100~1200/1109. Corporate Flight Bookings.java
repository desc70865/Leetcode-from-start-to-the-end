/* 
There are n flights, and they are labeled from 1 to n.

We have a list of flight bookings.  The i-th booking bookings[i] = [i, j, k] means that we booked k seats from flights labeled i to j inclusive.

Return an array answer of length n, representing the number of seats booked on each flight in order of their label.

 

Example 1:

Input: bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
Output: [10,55,45,25,25]
 

Constraints:

1 <= bookings.length <= 20000
1 <= bookings[i][0] <= bookings[i][1] <= n <= 20000
1 <= bookings[i][2] <= 10000
 */

class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        
    }
}



class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] res = new int[n];
        for (int[] arr: bookings) {
            for (int i = arr[0]; i <= arr[1]; i++) {
                res[i-1] += arr[2];
            }
        }
        return res;
    }
}



class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] arr = new int[n];
        
        for (int i=0; i < bookings.length; i++) {
            int start = bookings[i][0];
            int end = bookings[i][1];
            
            int seats = bookings[i][2];
            arr[start-1] += seats;
            if (end < n) arr[end] -= seats;
        }
        
        for (int i=1; i < n; i++) {
            arr[i] += arr[i-1];
        }
        
        return arr;
    }
}