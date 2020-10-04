/* 
Given the array orders, which represents the orders that customers have done in a restaurant. More specifically orders[i]=[customerNamei,tableNumberi,foodItemi] where customerNamei is the name of the customer, tableNumberi is the table customer sit at, and foodItemi is the item customer orders.

Return the restaurant's “display table”. The “display table” is a table whose row entries denote how many of each food item each table ordered. The first column is the table number and the remaining columns correspond to each food item in alphabetical order. The first row should be a header whose first column is “Table”, followed by the names of the food items. Note that the customer names are not part of the table. Additionally, the rows should be sorted in numerically increasing order.

 

Example 1:

Input: orders = [["David","3","Ceviche"],["Corina","10","Beef Burrito"],["David","3","Fried Chicken"],["Carla","5","Water"],["Carla","5","Ceviche"],["Rous","3","Ceviche"]]
Output: [["Table","Beef Burrito","Ceviche","Fried Chicken","Water"],["3","0","2","1","0"],["5","0","1","0","1"],["10","1","0","0","0"]] 
Explanation:
The displaying table looks like:
Table,Beef Burrito,Ceviche,Fried Chicken,Water
3    ,0           ,2      ,1            ,0
5    ,0           ,1      ,0            ,1
10   ,1           ,0      ,0            ,0
For the table 3: David orders "Ceviche" and "Fried Chicken", and Rous orders "Ceviche".
For the table 5: Carla orders "Water" and "Ceviche".
For the table 10: Corina orders "Beef Burrito". 
Example 2:

Input: orders = [["James","12","Fried Chicken"],["Ratesh","12","Fried Chicken"],["Amadeus","12","Fried Chicken"],["Adam","1","Canadian Waffles"],["Brianna","1","Canadian Waffles"]]
Output: [["Table","Canadian Waffles","Fried Chicken"],["1","2","0"],["12","0","3"]] 
Explanation: 
For the table 1: Adam and Brianna order "Canadian Waffles".
For the table 12: James, Ratesh and Amadeus order "Fried Chicken".
Example 3:

Input: orders = [["Laura","2","Bean Burrito"],["Jhon","2","Beef Burrito"],["Melissa","2","Soda"]]
Output: [["Table","Bean Burrito","Beef Burrito","Soda"],["2","1","1","1"]]
 

Constraints:

1 <= orders.length <= 5 * 10^4
orders[i].length == 3
1 <= customerNamei.length, foodItemi.length <= 20
customerNamei and foodItemi consist of lowercase and uppercase English letters and the space character.
tableNumberi is a valid integer between 1 and 500.
 */

import java.util.AbstractList;

class Solution {
    static class Table extends AbstractList<String> implements Comparable<Table> {
        final int[] array;

        public Table(String name, int foodSize) {
            super();
            this.array = new int[foodSize + 1];
            array[0] = Integer.parseInt(name);
        }

        @Override
        public String get(int index) {
            return Integer.toString(array[index]);
        }

        @Override
        public int size() {
            return array.length;
        }

        @Override
        public int compareTo(Table o) {
            return this.array[0] - o.array[0];
        }
    }

    static class Food implements Comparable<Food> {
        final String name;
        int id;

        Food(String name) {
            super();
            this.name = name;
        }

        @Override
        public int compareTo(Food o) {
            return this.name.compareTo(o.name);
        }
    }

    final Map<String, Table> tableMap = new HashMap<>();
    final List tableList = new ArrayList<>();
    final Map<String, Food> foodMap = new HashMap<>();
    final List<Food> foodList = new ArrayList<>();

    void addOrder(String name, Food food) {
        Table table = tableMap.get(name);
        if (table == null) {
            table = new Table(name, foodList.size());
            tableMap.put(name, table);
            tableList.add(table);
        }
        table.array[food.id]++;
    }

    Food getFood(String name) {
        Food food = foodMap.get(name);
        if (food == null) {
            food = new Food(name);
            foodMap.put(name, food);
            this.foodList.add(food);
        }
        return food;
    }

    private List solve(List<List> orders) {
        for (List order : orders) {
            order.set(2, this.getFood((String) order.get(2)));
        }
        Collections.sort(this.foodList);
        for (int i = 0; i < this.foodList.size(); i++)
            this.foodList.get(i).id = i + 1;
        List<String> title = new ArrayList<>(foodList.size() + 1);
        title.add("Table");
        for (Food food : foodList)
            title.add(food.name);
        this.tableList.add(title);
        for (List order : orders) {
            String table = (String) order.get(1);
            Food food = (Food) order.get(2);
            order.set(2, food.name);
            this.addOrder(table, food);
        }
        Collections.sort(this.tableList.subList(1, this.tableList.size()));
        return this.tableList;
    }

    public List<List<String>> displayTable(List<List<String>> orders) {
        return this.solve((List<List>) (List) orders);
    }
}