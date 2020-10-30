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
    public static boolean isNumber(String s) {
        if (s == null || s.trim().equals("")) {
            return false;
        }
        s = s.trim();
        boolean num = false;
        boolean e = false;
        boolean point = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 非法字母排除
            if ((c <'0' || c >'9') && c!='e' && c!='+' && c!='-' && c!='.') {
                return false;
            }
            if (c>='0' && c<='9') {
                num =true;
            }
            // e只能出现一次。且在数字之间
            else if (c == 'e') {
                e = true;
                if (i == 0 || i!= s.lastIndexOf("e") || i==s.length()-1 || !num) {
                    return false;
                }
                num = false;
            }
            else if (c == '+'|| c == '-') { // 符号只能出现在首位或者e之后
                if (i > 0 && s.charAt(i - 1) != 'e') {
                    return false;
                }
            } else if (c == '.') { // e之后不能有小数点,不能重复
                if ((e && i > s.indexOf("e")) || point ) {
                    return false;
                }
                point = true;
            }
        }
       return num;
    }
}


class Solution {
    public boolean isNumber(String s) {
        String regex = "\\s*(\\+|-)?(\\d+|(\\d+\\.\\d*)|(\\d*\\.\\d+))(e(\\+|-)?\\d+)?\\s*";
        return s.matches(regex);
    }
}

// https://blog.csdn.net/kenden23/article/details/18696083
// Finite automata machine


/* class Solution {
public:
	bool isNumber(const char *s) {
		enum InputType {
			INVALID,		// 0 Include: Alphas, '(', '&' ans so on
			SPACE,		// 1
			SIGN,		// 2 '+','-'
			DIGIT,		// 3 numbers
			DOT,			// 4 '.'
			EXPONENT,		// 5 'e' 'E'
		};
		int transTable[][6] = {
		//0INVA,1SPA,2SIG,3DI,4DO,5E
			-1,  0,  3,  1,  2, -1,//0初始无输入或者只有space的状态
			-1,  8, -1,  1,  4,  5,//1输入了数字之后的状态
			-1, -1, -1,  4, -1, -1,//2前面无数字，只输入了Dot的状态
			-1, -1, -1,  1,  2, -1,//3输入了符号状态
			-1,  8, -1,  4, -1,  5,//4前面有数字和有dot的状态
			-1, -1,  6,  7, -1, -1,//5'e' or 'E'输入后的状态
			-1, -1, -1,  7, -1, -1,//6输入e之后输入Sign的状态
			-1,  8, -1,  7, -1, -1,//7输入e后输入数字的状态
			-1,  8, -1, -1, -1, -1,//8前面有有效数输入之后，输入space的状态
		};
		int state = 0;
		while (*s)
		{
			InputType input = INVALID;
			if (*s == ' ') input = SPACE;
			else if (*s == '+' || *s == '-') input = SIGN;
			else if (isdigit(*s)) input = DIGIT;
			else if (*s == '.') input = DOT;
			else if (*s == 'e' || *s == 'E') input = EXPONENT;
			state = transTable[state][input];
			if (state == -1) return false;
			++s;
		}
		return state == 1 || state == 4 || state == 7 || state == 8;
	}
}; */