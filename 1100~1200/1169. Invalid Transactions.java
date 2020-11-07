/* 
A transaction is possibly invalid if:

the amount exceeds $1000, or;
if it occurs within (and including) 60 minutes of another transaction with the same name in a different city.
Each transaction string transactions[i] consists of comma separated values representing the name, time (in minutes), amount, and city of the transaction.

Given a list of transactions, return a list of transactions that are possibly invalid.  You may return the answer in any order.

 

Example 1:

Input: transactions = ["alice,20,800,mtv","alice,50,100,beijing"]
Output: ["alice,20,800,mtv","alice,50,100,beijing"]
Explanation: The first transaction is invalid because the second transaction occurs within a difference of 60 minutes, have the same name and is in a different city. Similarly the second one is invalid too.
Example 2:

Input: transactions = ["alice,20,800,mtv","alice,50,1200,mtv"]
Output: ["alice,50,1200,mtv"]
Example 3:

Input: transactions = ["alice,20,800,mtv","bob,50,1200,mtv"]
Output: ["bob,50,1200,mtv"]
 

Constraints:

transactions.length <= 1000
Each transactions[i] takes the form "{name},{time},{amount},{city}"
Each {name} and {city} consist of lowercase English letters, and have lengths between 1 and 10.
Each {time} consist of digits, and represent an integer between 0 and 1000.
Each {amount} consist of digits, and represent an integer between 0 and 2000.
 */

class Solution {
    public List<String> invalidTransactions(String[] transactions) {
        int len = transactions.length;
        Node[] record = new Node[len];
        for (int i = 0; i < len; i++) {
            record[i] = new Node(transactions[i].split(","));
        }
        List<String> res = new ArrayList<>();
        boolean[] unvalid = new boolean[len];
        for (int i = 0; i < len; i++) {
            if (unvalid[i]) continue;
            if (record[i].money > 1000) {
                res.add(transactions[i]);
                unvalid[i] = true;
                continue;
            }
            for (int j = 0; j < len; j++) {
                if (i == j) continue;
                if (record[j].name.equals(record[i].name) && ! record[i].city.equals(record[j].city))
                    if (Math.abs(record[i].time - record[j].time) <= 60) {
                        res.add(transactions[i]);
                        unvalid[i] = true;
                        if (! unvalid[j]) {
                            res.add(transactions[j]);
                            unvalid[j] = true;
                        }
                        break;
                    }
            }
        }
        return res;
    }
}

class Node {
    String name;
    Integer time;
    Integer money;
    String city;

    public Node(String[] s) {
        this.name = s[0];
        this.time = Integer.parseInt(s[1]);
        this.money = Integer.parseInt(s[2]);
        this.city = s[3];
    }
}