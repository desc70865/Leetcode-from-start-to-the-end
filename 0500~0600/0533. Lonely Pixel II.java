/* 
Given a picture consisting of black and white pixels, and a positive integer N, find the number of black pixels located at some specific row R and column C that align with all the following rules:

Row R and column C both contain exactly N black pixels.
For all rows that have a black pixel at column C, they should be exactly the same as row R
The picture is represented by a 2D char array consisting of 'B' and 'W', which means black and white pixels respectively.

Example:
Input:                                            
[['W', 'B', 'W', 'B', 'B', 'W'],    
 ['W', 'B', 'W', 'B', 'B', 'W'],    
 ['W', 'B', 'W', 'B', 'B', 'W'],    
 ['W', 'W', 'B', 'W', 'B', 'W']] 

N = 3
Output: 6
Explanation: All the bold 'B' are the black pixels we need (all 'B's at column 1 and 3).
        0    1    2    3    4    5         column index                                            
0    [['W', 'B', 'W', 'B', 'B', 'W'],    
1     ['W', 'B', 'W', 'B', 'B', 'W'],    
2     ['W', 'B', 'W', 'B', 'B', 'W'],    
3     ['W', 'W', 'B', 'W', 'B', 'W']]    
row index

Take 'B' at row R = 0 and column C = 1 as an example:
Rule 1, row R = 0 and column C = 1 both have exactly N = 3 black pixels. 
Rule 2, the rows have black pixel at column C = 1 are row 0, row 1 and row 2. They are exactly the same as row R = 0.

Note:
The range of width and height of the input 2D array is [1,200].
 */

public class Solution {

    private boolean[] checkCol;
    private int[] cnt1 , cnt2;
    private long[] hash1 , hash2 , hash3;
    
    public int findBlackPixel(char[][] picture, int N) {
     
        if (picture.length == 0)
            return 0;
        if (picture[0].length == 0)
            return 0;
        
        int i , j , n = picture.length , m = picture[0].length , ans = 0;
        checkCol = new boolean[m];
        cnt1 = new int[n];
        cnt2 = new int[m];
        hash1 = new long[n];
        hash2 = new long[n];
        hash3 = new long[n];
        
        for (i = 0;i < n;i ++) {
            long value1 = 37 , value2 = 43 , value3 = 53;
            for (j = 0;j < m;j ++) {
                value1 = value1 * 31 + picture[i][j];
                value2 = value2 * 47 + picture[i][j]; 
                value3 = value3 * 57 + picture[i][j];
            }
            hash1[i] = value1;
            hash2[i] = value2;
            hash3[i] = value3;
        }
        
        Arrays.fill(checkCol , true);
        
        for (i = 0;i < n;i ++)
            for (j = 0;j < m;j ++) {
                if (picture[i][j] == 'B') {
                    cnt1[i] ++;
                    cnt2[j] ++;
                }
            }
        
        for (j = 0;j < m;j ++) {
            long value1 = - 1 , value2 = - 1 , value3 = - 1;
            boolean first = true;
            for (i = 0;i < n;i ++) {
                if (picture[i][j] == 'B') {
                    if (first) {
                        first = false;
                        value1 = hash1[i];
                        value2 = hash2[i];
                        value3 = hash3[i];
                    } else {
                        if (hash1[i] != value1 ||
                            hash2[i] != value2 || 
                            hash3[i] != value3) {
                            checkCol[j] = false;
                            break;
                        }
                    }
                }
            }
        }
        
        for (i = 0;i < n;i ++)
            for (j = 0;j < m;j ++) {
                if (picture[i][j] == 'B' && (cnt1[i] == N) && (cnt2[j] == N) && checkCol[j]) {
                    ans ++;
                }
            }
        return ans;
        
    }
}