/* 
We are playing the Guess Game. The game is as follows:

I pick a number from 1 to n. You have to guess which number I picked.

Every time you guess wrong, I'll tell you whether the number is higher or lower.

You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):

-1 : My number is lower
 1 : My number is higher
 0 : Congrats! You got it!
Example :

Input: n = 10, pick = 6
Output: 6
 */

/** 
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return 	     -1 if num is lower than the guess number
 *			      1 if num is higher than the guess number
 *               otherwise return 0
 * int guess(int num);
 */

public class Solution extends GuessGame {
    public int guessNumber(int n) {
        return guessN(0, n);
    }
    
    private int guessN(int start, int end) {
        int mid = start + (end - start) / 2;
        int p = this.guess(mid);
        if (p < 0) {
            return guessN(start, mid - 1);
        } else if (p > 0) {
            return guessN(mid + 1, end);
        } else {
            return mid;
        }
    }
}



public class Solution extends GuessGame {
    public int guessNumber(int n) {
        int l = 0, r = n, mid, p;
        while (l <= r) {
            mid = l + (r - l) / 2;
            p = guess(mid);
            if (p < 0) {
                r = mid - 1;
            } else if (p > 0) {
                l = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}