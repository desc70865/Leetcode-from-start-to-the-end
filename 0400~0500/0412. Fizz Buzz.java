/* 
Write a program that outputs the string representation of numbers from 1 to n.

But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”. For numbers which are multiples of both three and five output “FizzBuzz”.

Example:

n = 15,

Return:
[
    "1",
    "2",
    "Fizz",
    "4",
    "Buzz",
    "Fizz",
    "7",
    "8",
    "Fizz",
    "Buzz",
    "11",
    "Fizz",
    "13",
    "14",
    "FizzBuzz"
]
 */

class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            boolean triple = (i % 3 == 0), fivefold = (i % 5 == 0);
            if (triple || fivefold) {
                StringBuilder sb = new StringBuilder();
                if (triple) {
                    sb.append("Fizz");
                }
                if (fivefold) {
                    sb.append("Buzz");
                }
                list.add(sb.toString());
            } else {
                list.add(String.valueOf(i));
            }
        }
        return list;
    }
}



class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<>();
        String Fizz = "Fizz", Buzz = "Buzz", FizzBuzz = "FizzBuzz";
        for (int i = 1; i <= n; i++) {
            boolean triple = (i % 3 == 0), fivefold = (i % 5 == 0);
            if (triple || fivefold) {
                if (! fivefold) {
                    list.add(Fizz);
                } else if (! triple) {
                    list.add(Buzz);
                } else {
                    list.add(FizzBuzz);
                }
            } else {
                list.add(String.valueOf(i));
            }
        }
        return list;
    }
}



import java.util.AbstractList;

class Solution {
    
    public List<String> fizzBuzz(int n) {
        
        return new AbstractList<String>() {
            @Override
            public String get(int i) {
                i++;
                if (i % 3 == 0 && i % 5 == 0) return "FizzBuzz";
                if (i % 3 == 0) return "Fizz";
                if (i % 5 == 0) return "Buzz";
                return String.valueOf(i);
            }
            
            @Override
            public int size() {
                return n;
            }
        };
    }
}