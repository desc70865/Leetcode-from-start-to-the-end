/* 
Validate if a given string can be interpreted as a decimal number.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
" -90e3   " => true
" 1e" => false
"e3" => false
" 6e-1" => true
" 99e2.5 " => false
"53.5e93" => true
" --6 " => false
"-+3" => false
"95a54e53" => false

Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one. However, here is a list of characters that can be in a valid decimal number:

Numbers 0-9
Exponent - "e"
Positive/negative sign - "+"/"-"
Decimal point - "."
Of course, the context of these characters also matters in the input.

Update (2015-02-10):
The signature of the C++ function had been updated. If you still see your function signature accepts a const char * argument, please click the reload button to reset your code definition.
 */

class Solution {
    static final int MASK = 0b101101000;
    static final int[][] FSM = {{ 0,  1,  6,  2, -1},
                                {-1, -1,  6,  2, -1},
                                {-1, -1,  3, -1, -1},
                                { 8, -1,  3, -1,  4},
                                {-1,  7,  5, -1, -1},
                                { 8, -1,  5, -1, -1},
                                { 8, -1,  6,  3,  4},
                                {-1, -1,  5, -1, -1},
                                { 8, -1, -1, -1, -1}};
    
    public boolean isNumber(String s) {
        int state = 0;
        for (char c: s.toCharArray()) {
            int next = map(c);
            if (next < 0) return false;
            state = FSM[state][next];
            if (state < 0) return false;
        }
        return (MASK & (1 << state)) > 0;
    }

    public int map(char c) {
        switch (c) {
            case ' ': return 0;
            case '+':
            case '-': return 1;
            case '.': return 3;
            case 'e': 
            case 'E': return 4;
            default: if (c >= '0' && c <= '9') return 2;
        }
        return -1;
    }
}